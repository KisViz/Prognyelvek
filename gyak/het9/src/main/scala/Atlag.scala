import scala.annotation.tailrec

//jegyzetbol lopva es kicsit atirva
def listSize(list: List[Double]): Int = {

  @tailrec
  def listSize(list: List[Double], res: Int): Int = {
    list match {
      case Nil => res
      case head :: tail => listSize(tail, res + 1)
    }
  }

  listSize(list, 0)
}

//jegyzetbol lopva es kicsit atirva
def listSum(list: List[Double]): Double = {

  @tailrec
  def listSum(list: List[Double], res: Double): Double = {
    list match {
      case Nil => res
      case head :: tail => listSum(tail, head + res)
    }
  }

  listSum(list, 0.0)
}

def atlag(list: List[Double]): Option[Double] = {
  list match
    case Nil => None
    case _ => Some(listSum(list) / listSize(list))
}

object Atlag extends App {
  val list1 = List(1.0, 2.0, 3.0, 4.0, 5.0)
  val list2 = List(5.5, 1.3, 8.7)

  println(list1)
  println(atlag(list1))
  println(list2)
  println(atlag(list2))
  println(atlag(List()))
}