def applyFunc(func: Int => Int, list: List[Int]): List[Int] = {
  list match {
    case Nil => List.empty
    case head :: tail => func(head) :: applyFunc(func, tail)
  }
}


object higherOrder extends App {
  val add = (x: Int) => x + 1
  println("x + 1: " + applyFunc(add, List(1, 2, 3)) + "\n")


  val list = List(1, 2, 3, 4, 5)
  println(list.map(elem => elem * elem)) //minden elemre alkalmazza es azt adja vissza
  println(list.filter(elem => elem % 2 == 0)) //csak a feltételt teljesito elemeket adja vissza
  println(list.fold(100)((accumulator, elem) => accumulator + elem)) //100-hoz hozzaadja az elemeket
  println(list.reduce((accumulator, elem) => accumulator + elem) + "\n") //osszeadja az elemeket

  val list2 = List('A', 'B', 'C', 'D')
  println(list2.zip(list)) //az azonos indexueket parba allitja
  println(list2.zipWithIndex) //itt meg az indexet adja parnak
  println(list.exists(elem => elem > 3)) //legalabb egy elem megfelel e
  println(list.forall(elem => elem > 3)) //az osszes elem megfelel e

}