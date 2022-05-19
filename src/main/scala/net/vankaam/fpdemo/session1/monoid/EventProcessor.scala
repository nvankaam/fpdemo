package net.vankaam.fpdemo.session1.monoid

import cats.Monoid
import net.vankaam.fpdemo.model._

object EventProcessor {
  /**
   * Monoid extractor that can be plugged into reducePerUser to get same output as countImpressionsPerCookie.
   * Much simpler to unit test if we have many pluggable methods.
   */
  def isImpression(t:TrackingEvent):Option[Int] = Option.when(t.isInstanceOf[Impression])(1)

  /**Another monoid extractor that can be plugged into reducePerUser to get the total costs per user
   */
  def getCost(t:TrackingEvent):Option[Int] = Option.when(t.isInstanceOf[Impression])(t.asInstanceOf[Impression].cpm)

}

class EventProcessor(data: Vector[TrackingEvent]) {
  val budget = 100

  def getRemainingBudget: Int = {
    getImpressions.map(_.cpm).fold(budget)((accumulator, elem) => accumulator - elem)
  }
  def getImpressions: Vector[Impression] = {
    data.filter(_.isInstanceOf[Impression]).map(_.asInstanceOf[Impression])
  }

  /* Specific implementation counting number of impressions per cookie for a campaign
   */
  def countImpressionsPerCookie(campaign: Int): Map[Cookie, Int] = {
    data.filter(_.campaignId == campaign).groupMapReduce(_.cookie)(x => if (x.isInstanceOf[Impression]) 1 else 0)(_ + _)
  }



  /**
   * Generic implementation using Monoid, where:
   *  - the accumulator implementation is abstracted by Monoid
   *  - The identity element is used to get rid of an optional value
   */
  def reducePerCookie[T: Monoid](extractM: TrackingEvent => Option[T], campaign: Int): Map[Cookie, T] = {
    data.filter(_.campaignId == campaign).groupMapReduce(_.cookie)(extractM.andThen(_.getOrElse(Monoid[T].empty)))(Monoid[T].combine)
  }


}

/** Companion object with some sample impressions
 */
object EventProcessorSample {
  val sampleDataset: Vector[TrackingEvent] = Vector(
    Impression(Cookie("1"), campaignId = 1, timestamp = 1, cpm = 2),
    Impression(Cookie("1"), campaignId = 2, timestamp = 2, cpm = 3),
    Impression(Cookie("1"), campaignId = 1, timestamp = 3, cpm = 2),
    Conversion(Cookie("1"), campaignId = 1, timestamp = 1),
    Impression(Cookie("2"), campaignId = 3, timestamp = 1, cpm = 2),
    Impression(Cookie("2"), campaignId = 4, timestamp = 2, cpm = 2),
    Conversion(Cookie("2"), campaignId = 5, timestamp = 3),
    Impression(Cookie("3"), campaignId = 2, timestamp = 1, cpm = 6),
    Impression(Cookie("3"), campaignId = 2, timestamp = 2, cpm = 7),
    Impression(Cookie("3"), campaignId = 2, timestamp = 3, cpm = 4),
    Impression(Cookie("3"), campaignId = 2, timestamp = 4, cpm = 5),
    Conversion(Cookie("3"), campaignId = 2, timestamp = 5),
    Impression(Cookie("4"), campaignId = 2, timestamp = 1, cpm = 3),
    Impression(Cookie("4"), campaignId = 2, timestamp = 2, cpm = 8),
    Impression(Cookie("4"), campaignId = 3, timestamp = 3, cpm = 3),
    Conversion(Cookie("5"), campaignId = 3, timestamp = 1),
    Impression(Cookie("5"), campaignId = 3, timestamp = 2, cpm = 4)
  )

  val sampleInstance = new EventProcessor(sampleDataset)
}

