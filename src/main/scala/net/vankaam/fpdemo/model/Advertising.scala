package net.vankaam.fpdemo
package model

case class Cookie(id: String)

sealed trait TrackingEvent {
  def cookie: Cookie
  def campaignId: Int
  def timestamp: Int
}

case class Impression(cookie: Cookie, campaignId: Int, timestamp: Int, cpm: Int) extends TrackingEvent
case class Conversion(cookie: Cookie, campaignId: Int, timestamp: Int) extends TrackingEvent
