import java.util.Date

class Hello(var name: String) {

  def greetings(name: String): Unit = { //a Unit az olyan mint a void, csak egy tenyleges tipus
    this.name = name
    println("Hello " + this.name + ", Hello " + Hello.staticField + ", Time: " + new Date())
  }
}

object Hello {

  var staticField : String = "World"

  def main(args: Array[String]): Unit = {
    var msg = new Hello("World")
    msg.greetings("Scala")
  }
}
