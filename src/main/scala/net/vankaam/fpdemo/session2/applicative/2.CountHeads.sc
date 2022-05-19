import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._
import net.vankaam.fpdemo.session2.applicative.RandomInstance._
import net.vankaam.fpdemo.session2.applicative.ProbabilityInstance._

/**
 * Counts the number of heads, using a foldable container (List), and Applicative(T)
 * @return
 */
def countHeads[T[_]: Applicative](coins: List[T[Coin]]): T[Int] = {
  val heads: List[T[Int]] = coins.map(coinT => coinT.map(coin => if (coin == Head()) 1 else 0))
  heads.foldA
}

val probabilityCoinBag = Iterable.fill(15)(ProbabilityInstances.fairCoin).toList
// Use our generic countHeads method to count the number of heads in the coinBag
// Like the coins in our bag, our result is a Probability
val countHeadsProbability: Probability[Int] = countHeads(probabilityCoinBag)


val randomCoinBag = Iterable.fill(15)(RandomInstances.fairCoin).toList
// Use our generic countHeads method to count the number of heads in the coinBag
// Like the coins in our bag, our result is a Random instance from which we can draw samples
// Not that we use the same method as with Probability, but the effect is something completely different
val countHeadsRandom: Random[Int] = countHeads(randomCoinBag)

// Draw some samples from our random instance
countHeadsRandom.sample()
countHeadsRandom.sample()
countHeadsRandom.sample()