object Main {
  def interval(num: Double): (Double, Double) = {
    (math.floor(num), math.ceil(num))
  }

  def pair(pair: (Int, Int), pair2: (Int, Double)) : Int = {
    math.abs(pair._2 - pair._1)  //tuple második és első tagját lekérjük
  }

  def which(tuple1: (String, Double), tuple2: (String, Double)): (String, Double) = {
    val (name1, weight1) = tuple1
    val (name2, weight2) = tuple2

    (weight1, weight2) match {
      case (0, 0) => ("", 0)
      case (a, b) if a > b => (name1, weight1)
      case _ => (name2, weight2)
    }
  }


  def main(args: Array[String]): Unit = {
    println(interval(math.Pi))
    println(pair((7, 5), (1, 2)))
    println(which(("elso", 0), ("masodik", 0)))
    println(which(("elso", 2), ("masodik", 1)))
    println(which(("elso", 1), ("masodik", 2)))
  }
}
