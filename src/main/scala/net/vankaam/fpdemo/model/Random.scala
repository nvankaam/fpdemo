package net.vankaam.fpdemo
package model

import cats.Functor
import cats.implicits._
import spire.math.Rational

trait Random[T] {
  def sample(): T

  /** Converts a random instance into a probability by sampling the passed number of times
    * @param samples number of samples to use to generate the probability instance
    * @return A probability instance using the
    */
  def toProbability(samples: Int): Probability[T] = {
    Probability(
      (1 to samples)
        .foldLeft(Map.empty[T, Int])({ case (left, _) =>
          val r = sample()
          left.updated(r, left.getOrElse(r, 0) + 1)
        })
        .map({ case (value, numerator) => value -> Rational(numerator, samples) })
    )
  }

  /* ToString creates a probability instance to render the sample*/
  override lazy val toString: String = f"Random 1000 samples: ${toProbability(1000)}"
}

object RandomInstances {
  val r = new java.util.Random()

  /** Functor implementation that allows us to easily create multiple instances
    */
  private implicit val randomFunctor: Functor[Random] = new Functor[Random] {
    override def map[A, B](fa: Random[A])(f: A => B): Random[B] = new Random[B] {
      override def sample(): B = f(fa.sample())
    }
  }

  /* Creates a random double value greater than or equal to 0.0 and less than 1.0  */
  val double: Random[Double] = new Random[Double] {
    override def sample(): Double = r.nextDouble()
  }

  /* Uses functor of random to map the random double to a random coin */
  val fairCoin: Random[Coin] = double.map(x => {
    if (x < 0.5) {
      Head()
    } else {
      Tails()
    }
  })

}
