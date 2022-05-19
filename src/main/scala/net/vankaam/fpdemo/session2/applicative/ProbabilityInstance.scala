package net.vankaam.fpdemo.session2.applicative

import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._

import spire.math.Rational


object ProbabilityInstance {
  /* Applicative implementation */
  implicit val probabilityA: Applicative[Probability] = new Applicative[Probability] {
    /**
     * Identity value is a probability of 1 with the passed value
     */
    override def pure[A](x: A): Probability[A] = Probability(Map(x -> Rational.one))

    /**
     * Implementations like this are the reason people think FP is wizardry.
     * We use the fact that Vector is also an applicative to compose the cross-product, and calculate the value for each combination
     * All cross products are then accumulated in the groupMapReduce to build an new Probability instance
     */
    override def ap[A, B](ff: Probability[A => B])(fa: Probability[A]): Probability[B] = {
      Probability(
        ff.data.toVector.map2(fa.data.toVector) // Compose cross-product
          ({case ((ff,fff),(fa,fap)) => ff(fa) -> fff * fap}) // Call the method from ff with the value from fa as argument, multiply the rational value
          .groupMapReduce(_._1)(_._2)(_ + _) // Group all equal values together. EG (Head() -> 1/2, Head() -> 1/2) becomes (Head() -> 1)
      )

    }
  }
}