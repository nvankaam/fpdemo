package net.vankaam.fpdemo.model

sealed trait Employee {
  /* Productivity, measured in lines of code per hour */
  def productivity:Int
}
case class Developer() extends Employee {
  override def productivity: Int = 100
}
case class Manager() extends Employee {
  override def productivity: Int = 0
}

case class Payslip(salary:Int)