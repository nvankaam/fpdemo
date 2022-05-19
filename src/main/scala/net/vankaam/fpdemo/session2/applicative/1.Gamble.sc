import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._
import net.vankaam.fpdemo.session2.applicative.RandomInstance._
import net.vankaam.fpdemo.session2.applicative.ProbabilityInstance._


/**
 * A generic gamble implementation using the Functor implementation from T
 * @return Boolean whether the guess was correct, wrapped in T
 */
def gamble[T[_]: Functor](coin: T[Coin], guess: Coin): T[Boolean] = {
  coin.map(x => x == guess)
}

val coinProbability = ProbabilityInstances.biasedCoin
// Like the type of the coin, our gable will now return a probability that the guess is right
val guessProbability: Probability[Boolean] = gamble(coinProbability,Head())


val coinRandom = RandomInstances.fairCoin
// We call the same method as above, but now we get a random instance that we can sample.
// It will return either true or false depending whether the guess was correct
val guessRandom: Random[Boolean] = gamble(coinRandom,Head())

guessRandom.sample()
guessRandom.sample()
guessRandom.sample()