package net.vankaam.fpdemo.session3
package monad

import cats.Monad
import net.vankaam.fpdemo.model.Random

object RandomInstance {
  implicit val randomM = new Monad[Random] {
    override def pure[A](x: A): Random[A] = new Random[A] {
      override def sample(): A = x
    }

    override def flatMap[A, B](fa: Random[A])(f: A => Random[B]): Random[B] = f(fa.sample())

    /* not tail recursive, just for demo purposes */
    override def tailRecM[A, B](a: A)(f: A => Random[Either[A, B]]): Random[B] = flatMap(f(a)) {
      case Left(a)  => tailRecM(a)(f)
      case Right(b) => pure(b)
    }
  }
}
