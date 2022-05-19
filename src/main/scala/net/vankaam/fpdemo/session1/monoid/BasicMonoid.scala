package net.vankaam.fpdemo.session1.monoid

import cats.kernel.{CommutativeMonoid, Monoid}

object BasicMonoid {
  /** Plus operation on two integers is a commutative monoid
   */
  val plusMonoid: CommutativeMonoid[Int] = new CommutativeMonoid[Int] {
    override def empty: Int = 0
    override def combine(x: Int, y: Int): Int = x + y
  }

  /** Concatenation of two sets is also a commutative monoid
   */
  def setConcatenation[T](): CommutativeMonoid[Set[T]] = new CommutativeMonoid[Set[T]] {
    override def empty: Set[T] = Set.empty
    override def combine(x: Set[T], y: Set[T]): Set[T] = x.concat(y)
  }

  /** List concatenation is a monoid, but not commutative as order does matter
   */
  def listConcatenation[T](): Monoid[List[T]] = new Monoid[List[T]] {
    override def empty: List[T] = List.empty
    override def combine(x: List[T], y: List[T]): List[T] = x.concat(y)
  }

}

