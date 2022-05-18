package net.vankaam.fpdemo.session1.monoid

import net.vankaam.fpdemo.session1.monoid.EventProcessorSample.sampleInstance

object Main {

  def main(args: Array[String]): Unit = {

    /* Example usage of Int monoid and the generic reducePerCookie method */
    val impressionsPerCookieCampaign1 = sampleInstance.reducePerCookie(EventProcessor.isImpression, 1)
    /* Another example usage of Int monoid and the same generic reducePerCookie to calculate spending per user */
    val getSpendingPerCookie = sampleInstance.reducePerCookie(EventProcessor.getCost, 1)
  }
}
