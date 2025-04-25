import scala.annotation.tailrec

case class Point(val zh1: Int, val zh2: Int, zh3: Int) { //val ha nincs case

  def score(): Int = {
    if (zh1 == 0 || zh2 == 0 || zh3 == 0)
      0
    else
      zh1 + zh2 + zh3
  }
}

case class Pair(first: Int, second: Int) {

  def listPair(list1: List[Int], list2: List[Int]): List[Pair] = {
    (list1, list2) match {
      case (h1 :: t1, h2 :: t2) => Pair(h1, h2) :: listPair(t1, t2)
      case (Nil, _) => List.empty
      case (_, Nil) => List.empty
    }
  }

  @tailrec
  final def listPairRec(list1: List[Int], list2: List[Int], res: List[Pair]): List[Pair] = {
    (list1, list2) match {
      case (h1 :: t1, h2 :: t2) => listPairRec(t1, t2, Pair(h1, h2) :: res)
      case (Nil, _) => res
      case (_, Nil) => res
    }
  }
}

object Main2 {

  def score(point: Point): Int = {
    point match {
      case Point(0, _, _) => 0
      case Point(_, 0, _) => 0
      case Point(_, _, 0) => 0
      case Point(a, b, c) => a + b + c
    }
  }

  def listSize(list: List[Int]): Int = {
    list match {
      case Nil => 0
      case head :: tail => 1 + listSize(tail)
    }
  }


  @tailrec
  def listSum(list: List[Int], res: Int): Int = {
    list match {
      case Nil => res
      case head :: tail => listSum(tail, head + res)
    }
  }


  def main(args: Array[String]): Unit = {
    val point = Point(3, 2, 1)
    println(point.score())
    println(score(point))

    println("----------")

    val list1 = List("alma", "banan", "citrom")
    val list2 = List(1, 2, 3)
    val list3 = 4 :: 5 :: Nil
    val lista4 = 0 :: list3 ::: list2

    println(list1)
    println(list2)
    println(list3)
    println(lista4)

    println("---------")


    val list5 = List.range(-5, 10, 2);
    println(list5)

    val list6= List.fill(2)(5); //számosság, ismétlődő elem
    println(list6)
    println(list5.reverse)
    println(list5.tail)
    println(list5.head)


  }
}
