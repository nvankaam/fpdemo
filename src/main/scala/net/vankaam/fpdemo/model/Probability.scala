package net.vankaam.fpdemo
package model

import spire.compat.fractional
import spire.math.Rational
import spire.implicits.literals

/**
 * Class representing a probability distribution of values of type T
 * See [[ProbabilityInstances]] on how to use
 * @param data the data type
 * @tparam T
 */
case class Probability[T](
                           data: Map[T, Rational]
                         ) {
  require(data.values.sum == Rational.one)

  override def toString: String = {
    f"Distribution: ${System.lineSeparator}" + data.toVector
      .sortBy(_._2)
      .reverse
      .map({ case (key, value) => s"$value -> $key ${}" })
      .mkString(System.lineSeparator) + System.lineSeparator
  }
}

/**
 * Example isnstance of Probability
 */
object ProbabilityInstances {
    val fairCoin: Probability[Coin] = Probability(Map(Head() -> r"1/2", Tails() -> r"1/2"))
    val biasedCoin: Probability[Coin] = Probability(Map(Head() -> r"3/4", Tails() -> r"1/4"))

    val fairDice:Probability[Int] = Probability(Map(
      1 -> r"1/6",
      2 -> r"1/6",
      3 -> r"1/6",
      4 -> r"1/6",
      5 -> r"1/6",
      6 -> r"1/6",
    ))
}