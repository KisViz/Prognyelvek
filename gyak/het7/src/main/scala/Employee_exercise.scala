import java.util

trait Workable() {
    def work(): Unit = {
        println("Working...")
    }
}

                        //inde nem with kell, mert meg nem volt extends
class Employee(val name: String, val slary: Int) extends Workable {
    //igy automatikusan inicializalja a konstruktor az adattagokat, ha jol ertettem
    def displayInfo(): Unit = {
        println("Nev: " + name + ", fizetes: " + slary);
        work();
    }
}

class Employee2(nameParameter: String, slaryParameter: Int) {
    //es igy nezne ki ha kezzel csinalnam
    val name: String = nameParameter;
    val salary: Int = slaryParameter;

    def displayInfo(): Unit = {
        println("Nev: " + name + ", fizetes: " + salary);
    }
}

            //ide nem szabad val-t tenni           oroklodesnel ide is be kell irni, ami az osnek kell
final class Manager(name: String, slary: Int, val bonus: Int) extends Employee(name, slary) with Workable {
    override def displayInfo(): Unit = {
        println("Nev: " + name + ", fizetes: " + slary + ", bonus: " + bonus);
        work();
    }
}

object Company {
    private final val tomb = util.ArrayList[Employee]();

    final def addEmployee(employee: Employee): Unit = {
        println(employee.name + " - hozzaadva")

        tomb.add(employee);
    }

    final def getEmployeeInfo(index: Int): String = {
        tomb.get(index).displayInfo().toString;
    }

    final def fireEmployee(): Unit = {
        val tmp = tomb.removeLast();

        println(tmp.name + " - torolve")
    }
}

object Employee_exercise extends App {
    val employee1 = new Employee("Elso", 100);
    employee1.displayInfo();

    val employee2 = new Employee2("Masodik", 200); //o nem dolgozik
    employee2.displayInfo();

    val manager1 = new Manager("Harmadik", 300, 100);
    manager1.displayInfo();

    println("-------------------")

    val employee3 = new Employee("3", 300);
    val employee4 = new Employee("4", 400);
    val employee5 = new Employee("5", 500);
    val manager2 = new Manager("2", 200, 100);
    val manager3 = new Manager("3", 200, 100);

    Company.addEmployee(employee3)
    Company.addEmployee(employee4)
    Company.addEmployee(employee5)
    Company.addEmployee(manager2)
    Company.addEmployee(manager3)

    println("-------------------")

    Company.getEmployeeInfo(2)
    Company.getEmployeeInfo(3)

    println("-------------------")

    Company.fireEmployee()
    Company.fireEmployee()
}
