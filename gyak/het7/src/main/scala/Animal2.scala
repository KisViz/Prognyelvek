class Animal2 {
  val species: String = "Unknown Animal"
  def sound(): String = "Unknown"
}

class Dog2 extends Animal2 {
  override val species: String = "Dog"
  override def sound(): String = "Ruff"
}

object Animal2 {
  def main(args: Array[String]): Unit = {
    val dog = new Dog2
    println(dog.species)
    println(dog.sound())
  }
}
