package net.vankaam.fpdemo
package model

sealed trait Coin
case class Head() extends Coin
case class Tails() extends Coin
