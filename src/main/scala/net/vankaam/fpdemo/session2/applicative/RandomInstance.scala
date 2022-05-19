package net.vankaam.fpdemo.session2.applicative

import cats._
import net.vankaam.fpdemo.model._



object RandomInstance {

  /**
   * Applicative implementation of Random is very simple
   */
  implicit val randomApplicative: Applicative[Random] = new Applicative[Random] {
    /**
     * The identity element is the identity function:
     * https://www.scala-lang.org/api/current/scala/Predef$.html#identity[A](x:A):A
     */
    override def pure[A](x: A): Random[A] = new Random[A] {
      override def sample(): A = x
    }

    /**
     * Apply just calls both random instances, and invokes the method from the former with the value from the latter
     */
    override def ap[A, B](ff: Random[A => B])(fa: Random[A]): Random[B] = new Random[B] {
      override def sample(): B = {
        ff.sample()(fa.sample())
      }
    }
  }

}
