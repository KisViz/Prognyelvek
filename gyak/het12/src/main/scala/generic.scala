import scala.annotation.tailrec

def genericListSize[T](list : List[T]): Int = {
  list match {
    case Nil => 0
    case h :: t => 1 + genericListSize(t)
  }
}

def genericListSum[A: Numeric](list: List[A]): A = {
  val numeric = summon[Numeric[A]]
  list match {
    case Nil => numeric.zero
    case h :: t => numeric.plus(h, genericListSum(t))
  }
}

def maxElement[A: Ordering](list: List[A]): Option[A] = {
  val ordering = summon[Ordering[A]]

  @tailrec
  def helper(currentList: List[A], currentMax: A): A = {
    currentList match {
      case Nil => currentMax
      case currentHead :: currentTail =>
        val nextMax =
          if (ordering.gt(currentHead, currentMax))
            currentHead
          else
            currentMax
        helper(currentTail, nextMax)
    }
  }

  list match {
    case Nil => None
    case head :: tail => Some(helper(tail, head))
  }
}

def createPairs[T1, T2](list1: List[T1], list2: List[T2]): Option[List[(T2, T1)]] = {

  def loop(l1: List[T1], l2: List[T2]): List[(T2, T1)] = {
    (l1, l2) match {
      case (Nil, Nil) => Nil
      case (h1 :: t1, h2 :: t2) => (h2, h1) :: loop(t1, t2)
      case _ => Nil
    }
  }

  val res = loop(list1, list2)
  res match {
    case Nil => None
    case _ => Some(res)
  }
}


@main
def main(): Unit =
  val l1 = List.range(1,11)
  val l2 = List("almafa", "katica", "kiskacsa", "póniló", "porszívó",
    "pillangó", "repülő", "serpenyő", "esernyő", "újjam van")

  println("genericListSize: " + genericListSize(l1))
  println("genericListSize: " + genericListSize(l2))
  println("genericListSum: " + genericListSum(l1))
//  println("genericListSum: " + genericListSum(l2)) //stringre nincs ordering
  println("maxElement: " + maxElement(l1))
  println("maxElement: " + maxElement(l2))
  println("createPairs: " + createPairs(l1, l2))
  println("createPairs: " + createPairs(l1, List("egy")))
