package net.vankaam.fpdemo.session3.monad

import cats.effect.{IO, Timer}
import net.vankaam.fpdemo.model._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

object EmployeeService {

  implicit private val timer: Timer[IO] = IO.timer(ExecutionContext.global)

  /** Random instance generating a random employee
    */
  private val randomEmployee: Random[Employee] = new Random[Employee] {
    override def sample(): Employee = {
      val r = Math.random()
      if (r < 0.5) {
        Manager()
      } else {
        Developer()
      }
    }
  }

  /** IO Operation finding an employee
    */
  def findEmployee(name: String): IO[Employee] =
    // IO Sleep simulates some IO operation, eg. a database call
    IO.sleep(100.millis)
      .map(_ => randomEmployee.sample())

  /** IO Operation to get an employee's payslip
    * @return io operation of the payslip of the employee
    */
  def getPayslip(employee: Employee): IO[Payslip] = {
    // IO Sleep simulates some IO operation, eg. a database call
    IO.sleep(100.millis)
      .map(_ =>
        employee match {
          case Developer() => Payslip(10)
          case Manager()   => Payslip(100)
        }
      )
  }
}
