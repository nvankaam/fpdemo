import net.vankaam.fpdemo.session3.monad.EmployeeService

val employeeName = "Niels"

val employeeIO = EmployeeService.findEmployee(employeeName)
val payslip = employeeIO.flatMap(employee => EmployeeService.getPayslip(employee))

payslip.unsafeRunSync()
