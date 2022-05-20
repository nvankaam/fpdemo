package net.vankaam.fpdemo.session3.monad

import cats.laws.discipline.MonadTests
import net.vankaam.fpdemo.model.Probability
import net.vankaam.fpdemo.model.ProbabilityTestInstances._
import net.vankaam.fpdemo.session3.monad.ProbabilityInstance.probabilityM
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.prop.Configuration
import org.typelevel.discipline.scalatest.FunSuiteDiscipline

class ProbabilitySuite extends FunSuiteDiscipline with AnyFunSuiteLike with Configuration {
  checkAll(
    "Probability.ApplicativeTests",
    MonadTests[Probability](ProbabilityInstance.probabilityM).stackUnsafeMonad[Int, Int, String]
  )
}
