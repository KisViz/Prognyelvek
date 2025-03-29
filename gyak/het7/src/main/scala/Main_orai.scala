/*
class Dog(var name: String) {
  def method(name: String):Unit={
//    name="sanyi" - nono
    println("Hello"+name)
  }
}

import java.util.Date

class Hello(var name: String) {

  def greetings(name: String): Unit = {
//    this.name = name
    println("Hello " + this.name + ", Hello " + Hello.staticField + ", Time: " + new Date())
  }
}

object Hello {

  private var staticField : String = "World"

  def main(args: Array[String]): Unit = {
    var msg = new Hello("World")
    msg.greetings("Scala")
  }
}

abstract class Animal {
  var name: String = "Kirkman"
  def sound(): Unit
}

trait Walks {
  def walk(): Unit = {
    println("I can walk")
  }
}

trait EatsAndSpeak {
  def eat(): Unit = {
    println("I can eat")
  }

  def speak(): Unit
}

final class Dog2 extends Animal with Walks with EatsAndSpeak {
//  override val name:String="Sanyi"
  this.name = "SuperDog"
  def sound(): Unit = {
    println("Ruff")
  }

  def speak(): Unit = {
    println("I am " + this.name)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val dog = new Dog2
    dog.sound()
    dog.walk()
    dog.eat()
    dog.speak()
    var i = 0
    while (i < 5) {
      println("i = " + i)
      i += 1
    }

  }
}

object Main2 extends App {

    var i = 0
    while (i < 5) {
      println("i = " + i)
      i += 1
    }

}
*/
