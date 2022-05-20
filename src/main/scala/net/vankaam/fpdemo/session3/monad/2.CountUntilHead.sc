import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._
import net.vankaam.fpdemo.session3.monad.RandomInstance._
import net.vankaam.fpdemo.session3.monad.ProbabilityInstance._

def countUntilHead[M[_]: Monad](coins: () => M[Coin], count: Int = 1): M[Int] = {
  coins()
    .flatMap(value => {
      if (value == Head()) {
        Monad[M].pure(count)
      } else {
        countUntilHead(coins, count + 1)
      }
    })
}

// This one will finish, as it is just based on samples
val randomUntilHead = countUntilHead(() => RandomInstances.fairCoin)

// This one will never finish
val probabilityUntilHead = countUntilHead(() => ProbabilityInstances.fairCoin)
