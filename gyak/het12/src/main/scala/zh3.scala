import scala.annotation.tailrec

// 1. feladat:
case class Komplex(re: Double, im: Double) {
  def szoroz(komplex: Komplex): Komplex = {
    //    (r_1, i_1) * (r_2, i_2) = (r_1 * r_2 - i_1 * i_2, r_1 * i_2 + r_2 * i_1)
    Komplex(
      (this.re * komplex.re) - (this.im * komplex.im),
      (this.re * komplex.im) + (komplex.re * this.im)
    ) //ez igy nem tudom mennyire helyes
  }
}

// 2. feladat:
object G9V7JU {

  def insertion(list: List[Int], ertek: Int): List[Int] = {

    @tailrec
    def helper(list: List[Int], szaml: Int, res: List[Int]): List[Int] = {
      list match
        case Nil => res
        case head :: tail =>
          if (szaml % 2 == 0) {
            helper(tail, 1, res ::: List(head) ::: List(ertek))
          } else {
            helper(tail, szaml + 1, res ::: List(head))
          }
    }

    helper(list, 1, List())
  }

  // A megoldasok tesztelese
  def main(args: Array[String]): Unit = {
    val k1 = Komplex(2.0, 3.0)
    val k2 = Komplex(4.0, 5.0)

    println(k1.szoroz(k2))

    val l1 = List.range(1,5)
    println(insertion(l1, 5))
  }
}
