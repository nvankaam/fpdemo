package net.vankaam.fpdemo.session3
package monad

import cats.Monad
import net.vankaam.fpdemo.model.Probability
import spire.math.Rational

object ProbabilityInstance {
  implicit val probabilityM = new Monad[Probability] {
    override def pure[A](x: A): Probability[A] = Probability(Map(x -> Rational.one))

    override def flatMap[A, B](fa: Probability[A])(f: A => Probability[B]): Probability[B] = {
      Probability(
        fa.data.toVector
          .flatMap({ case (value, prob) =>
            f(value).data.map({ case (newValue, prob2) =>
              (newValue, prob * prob2)
            })
          })
          .groupMapReduce(_._1)(_._2)(_ + _)
      )
    }

    /* not tail recursive, just for demo purposes */
    override def tailRecM[A, B](a: A)(f: A => Probability[Either[A, B]]): Probability[B] = flatMap(f(a)) {
      case Left(a)  => tailRecM(a)(f)
      case Right(b) => pure(b)
    }
  }
}
