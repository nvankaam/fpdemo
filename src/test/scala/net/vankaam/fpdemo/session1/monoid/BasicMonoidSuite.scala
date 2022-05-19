package net.vankaam.fpdemo.session1.monoid

import cats.implicits._
import cats.kernel.laws.discipline._
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.prop.Configuration
import org.typelevel.discipline.scalatest.FunSuiteDiscipline

/**
 * Testing the laws of our monoid implementations
 */
class BasicMonoidSuite extends FunSuiteDiscipline with AnyFunSuiteLike with Configuration {
  checkAll("plusMonoid.CommutativeMonoidTests", CommutativeMonoidTests[Int](BasicMonoid.plusMonoid).monoid)
  checkAll("setConcatenation.CommutativeMonoidTests", CommutativeMonoidTests[Set[Int]](BasicMonoid.setConcatenation()).monoid)
  checkAll("listConcatenation.MonoidTests", MonoidTests[List[Int]](BasicMonoid.listConcatenation()).monoid)
}
