package net.vankaam.fpdemo.session1.monoid

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.prop.Configuration
import org.typelevel.discipline.scalatest.FunSuiteDiscipline

class EventProcessorSuite extends FunSuiteDiscipline with AnyFunSuiteLike with Configuration {

  test("GetBudget") {
    val remainingBudget = EventProcessorSample.sampleInstance.getRemainingBudget
    assert(remainingBudget == 100 - EventProcessorSample.sampleInstance.getImpressions.map(_.cpm).sum)
  }

  // TODO: Implement tests for reducePerCookie & countImpressionsPerCookie
}
