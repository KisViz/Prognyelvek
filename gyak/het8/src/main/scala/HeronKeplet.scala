def heron(a: Double, b: Double, c: Double): Double = {
    val s = (a + b + c) / 2
    math.sqrt(s * (s - a) * (s - b) * (s - c))
}

object HeronKeplet extends App {
  println(heron(3,4,5))
  println(heron(6,8,10))
  println(heron(9,100,99))
}
