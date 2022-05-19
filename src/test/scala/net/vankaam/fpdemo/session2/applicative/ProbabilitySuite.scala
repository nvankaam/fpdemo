package net.vankaam.fpdemo.session2.applicative

import cats.laws.discipline.ApplicativeTests
import net.vankaam.fpdemo.model.Probability
import net.vankaam.fpdemo.model.ProbabilityTestInstances._
import net.vankaam.fpdemo.session2.applicative.ProbabilityInstance.probabilityA
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.prop.Configuration
import org.typelevel.discipline.scalatest.FunSuiteDiscipline

class ProbabilitySuite extends FunSuiteDiscipline with AnyFunSuiteLike with Configuration {
    checkAll(
      "Probability.ApplicativeTests",
      ApplicativeTests[Probability](ProbabilityInstance.probabilityA).applicative[Int, Int, String]
    )
}