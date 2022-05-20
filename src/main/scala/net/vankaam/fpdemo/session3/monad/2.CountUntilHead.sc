import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._
import net.vankaam.fpdemo.session3.monad.RandomInstance._
import net.vankaam.fpdemo.session3.monad.ProbabilityInstance._

def countUntilHead[T[_]: Monad](coinGenerator: () => T[Coin], count: Int = 1): T[Int] = {
  coinGenerator().flatMap(coin => {
    if (coin == Head()) {
      Monad[T].pure(count)
    } else {
      countUntilHead(coinGenerator, count + 1)
    }
  })
}

val randomCoinGenerator: () => Random[Coin] = () => RandomInstances.fairCoin
val headCount = countUntilHead(randomCoinGenerator)

// This will run forever
//val probabilityCoinGenerator: () => Probability[Coin] = () => ProbabilityInstances.fairCoin
//val probHeadCount = countUntilHead(probabilityCoinGenerator)
