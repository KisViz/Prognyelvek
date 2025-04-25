import scala.annotation.tailrec

def pozitiv(list: List[Int]): List[Int] = {
  list match
    case Nil => Nil
    case head :: tail =>  if (head > 0) {
      List(head) ::: pozitiv(tail)
    } else {
      pozitiv(tail)
    }
}

def pozitivTail(list: List[Int]): List[Int] = {

  @tailrec
  def pozitivTail(list: List[Int], res: List[Int]): List[Int] = {
    list match
      case Nil => res
      case head :: tail => if (head > 0) {
        pozitivTail(tail,  res ::: List(head))
      } else {
        pozitivTail(tail, res)
      }
  }

  pozitivTail(list, List())
}


object Pozitiv extends App {
  val list = List(-1, 1, 2, -9, 0, 8, 5, -4)
  println(list)
  println(pozitiv(list))
  println(pozitivTail(list))
}