import scala.annotation.tailrec

def megfordit(list: List[Int]): List[Int] = {
  list match
    case Nil => Nil
    case head :: tail => megfordit(tail) ::: List(head)
}

def megforditTail(list: List[Int]): List[Int] = {

  @tailrec
  def megforditTail(list: List[Int], res: List[Int]): List[Int] = {
    list match
      case Nil => res
      case head :: tail => megforditTail(tail, List(head) ::: res)
  }

  megforditTail(list, List())
}

object Megfordit extends App {
  val list = List.range(1,10)
  println(list)
  println(megfordit(list))
  println(megforditTail(list))
}