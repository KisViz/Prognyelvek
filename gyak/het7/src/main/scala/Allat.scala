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

class Dog extends Animal with Walks with EatsAndSpeak {
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
    val dog = new Dog
    dog.sound()
    dog.walk()
    dog.eat()
    dog.speak()
  }
}
