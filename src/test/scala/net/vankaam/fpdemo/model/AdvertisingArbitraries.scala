package net.vankaam.fpdemo.model

import org.scalacheck.{Arbitrary, Gen}

/**
 * Arbitraries define how property based tests generate random input instances
 */
object AdvertisingArbitraries {
  implicit val CookieArb:Arbitrary[Cookie] = Arbitrary(Gen.uuid.map(_.toString).map(Cookie))
}
