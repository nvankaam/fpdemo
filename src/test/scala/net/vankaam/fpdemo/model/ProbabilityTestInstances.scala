package net.vankaam.fpdemo.model

import cats.Eq
import org.scalacheck.{Arbitrary, Gen}
import spire.math.Rational

object ProbabilityTestInstances {
  implicit def probabilityArbitrary[A: Arbitrary]: Arbitrary[Probability[A]] =
    Arbitrary(for {
      size <- Gen.choose(1, 10)
      values <- Gen.listOfN(size, implicitly[Arbitrary[A]].arbitrary).map(_.distinct)
      weights <- Gen.listOfN(values.size, Gen.choose(1, 100))
    } yield {
      val sum = weights.sum
      Probability(
        weights.zipWithIndex
          .map({ case (weight, index) =>
            values(index) -> Rational.apply(weight, sum)
          })
          .toMap
      )
    })

  implicit def probabilityEq[T]: Eq[Probability[T]] = (x: Probability[T], y: Probability[T]) => x.data == y.data
}
