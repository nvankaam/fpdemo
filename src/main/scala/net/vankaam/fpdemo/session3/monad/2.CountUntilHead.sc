import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._


def countUntilHead[M[_]: Monad](coins: Iterator[M[Coin]], count: Int = 1): M[Int] = {
  coins
    .next()
    .flatMap(value => {
      if (value == Head()) {
        Monad[M].pure(count)
      } else {
        countUntilHead(coins, count + 1)
      }
    })
}

val infiniteRandomCoins = Iterator.continually(RandomInstances.fairCoin)
val infiniteProbabilityCoins = Iterator.continually(ProbabilityInstances.fairCoin)

val randomUntilHead = countUntilHead(infiniteRandomCoins)