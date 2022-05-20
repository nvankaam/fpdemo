import cats._
import cats.implicits._
import net.vankaam.fpdemo.model._
import net.vankaam.fpdemo.session3.monad.RandomInstance.randomM
import net.vankaam.fpdemo.session3.monad.ProbabilityInstance.probabilityM
import net.vankaam.fpdemo.session3.monad.EmployeeService

val employeeIO = EmployeeService.findEmployee("Niels")
val payslip = employeeIO.map(employee => EmployeeService.getPayslip(employee))
