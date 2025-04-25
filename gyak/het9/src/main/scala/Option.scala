object Option {

  def quadratic(abc: (Double, Double, Double) ): Option[(Double, Double)] = {
    val (a, b, c) = abc
    val d = (b * b) - (4 * a * c)

    if (d < 0)
      None
      //(0,0) ha nincs option
    else
      val x1 = (-b + Math.sqrt(d)) / (2 * a)
      val x2 = (-b - Math.sqrt(d)) / (2 * a)
      Some((x1, x2))
  }

  def main(args: Array[String]): Unit = {
    quadratic(1 , -5, 6) match {
      case Some((x1, x2)) => println("Roots: " + x1 + " " + x2)
      case None           => println("No real roots")
    }

    quadratic(1, -5, 26) match {
      case Some((x1, x2)) => println("Roots: " + x1 + " " + x2)
      case None => println("No real roots")
    }
  }
}
