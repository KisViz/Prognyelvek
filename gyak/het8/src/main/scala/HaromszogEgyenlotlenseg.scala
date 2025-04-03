def hszEgy(a: Double, b: Double, c: Double): Boolean = {
  if ((a + b > c) && (a + c > b) && (b + c > a)) {
    true
  } else {
    false
  }
}

def hszEgy_okos(a: Double, b: Double, c: Double): Boolean = {
  (a + b > c) && (a + c > b) && (b + c > a)
}

object HaromszogEgyenlotlenseg extends App {
  println(hszEgy(1,2,3))
  println(hszEgy(1,1,10))
  println(hszEgy(3,4,5)) //pitagoraszi ezert tuti jo
  println(hszEgy(6,8,10)) //ez is

  println("----------")

  println(hszEgy_okos(1, 2, 3))
  println(hszEgy_okos(1, 1, 10))
  println(hszEgy_okos(3, 4, 5))
  println(hszEgy_okos(6, 8, 10))
}