import scala.annotation.tailrec

/**
 *Készíts egy pure generikus függvényt, amely egy listát és egy beszúrandó elemet vár.
 *A függvény szúrja be az elemet a lista minden második elemét követően (tehát pl. [1, 2, 3, 4] és 5-ös
 *érték esetén: [1, 2, 5, 3, 4, 5]). A megoldáshoz használhatsz segédfüggvény(eke)t, ha szükséges.
 */

def genericBeszur[A] (list: List[A], elem: A): List[A] = {

  @tailrec
  def helper(list: List[A], szaml: Int, res: List[A]): List[A] = {
    list match
      case Nil => res
      case head :: tail =>
        if (szaml % 2 == 0) {
          helper(tail, 1, res ::: List(head) ::: List(elem))
        } else {
          helper(tail, szaml + 1, res ::: List(head))
        }
  }

  helper(list, 1, List())
}


/**
 * Készíts egy pure függvényt, amely egy egész számról eldönti, hogy prímszám-e.
 * Ennek megállapításához ne használj beépített függvényeket. A függvényt úgy írd meg, hogy
 * átadható legyen a filter magasabb rendű függvénynek. A megoldáshoz használhatsz
 * segédfüggvény(eke)t, ha szükséges.
 */

//ez nagyon tul lett bonyolitva
def prim(szam: Int): Boolean = {

  @tailrec
  def helper(actual: Int): Boolean = {
    if (szam > 0) {

      if (szam == actual) {
        true
      } else {
        szam % actual match
          case 0 => false
          case _ => helper(actual + 1)
      }

    } else {

      if (szam == actual) {
        true
      } else {
        szam % actual match
          case 0 => false
          case _ => helper(actual - 1)
      }

    }
  }

  szam match
    case szam if szam < 0 => helper(-2)
    case _ => helper(2)
}

def primEgyszeru(szam: Int): Boolean = {

  @tailrec
  def helper(actual: Int): Boolean = {
    if (actual == 0) { false } //ha eredetileg 1
    else if (actual == 1) { true } //ha eredetileg 2
    else if (szam % actual == 0) { false }
    else {
      helper(actual - 1)
    }
  }

  szam match
    case szam if szam < 0 => helper((szam * -1) - 1)
    case _ => helper(szam - 1)
}


/**
 * Készíts egy pure függvényt, amely a forall magasabb rendű függvény
 * működését valósítja meg, de specializálva van egész számokra.
 * Azaz a függvény döntse el, hogy az adott feltételnek a paraméterben
 * átadott össszes elem eleget tesz-e. A megoldáshoz ne használj
 * beépített függvényeket, de használhatsz segédfüggvény(eke)t, ha szükséges.
 */

def forAllInt(list: List[Int], func: Int => Boolean): Boolean = {

  @tailrec
  def helper(list: List[Int]): Boolean = {
    list match
      case Nil => true
      case head :: tail =>
        if (func(head)) {
          helper(tail)
        } else {
          false
        }
  }

  helper(list)
}


/**
 * Készíts egy pure függvényt, amely egy szöveget vár bemenetként, és visszadja a
 * benne szereplő angol ABC karaktereinek előfordulási gyakoriságát. Az eredményt
 * egy immutable Map-ban tárold el. A megoldáshoz használhatsz segédfüggvény(eke)t, ha szükséges.
 */

def betuk(szoveg: String): Map[Char, Int] = {

  @tailrec
  def helper(res: Map[Char, Int], index: Int): Map[Char, Int]= {
    index match
      case index if index >= szoveg.length => res
      case _ =>
        res.get(szoveg.charAt(index).toUpper) match
          case None => helper(res.updated(szoveg.charAt(index).toUpper, 1), index + 1)
          case Some(eddigi) => helper(res.updated(szoveg.charAt(index).toUpper, eddigi + 1), index + 1)
  }

  helper(Map(), 0)
}

object gyak10 extends App {
  val l1 = List.range(1,5)
  println("genericBeszur: " + genericBeszur(l1, 5))

  println("-----------")

  println("prim(2): " + prim(2))
  println("prim(97): " + prim(97))
  println("prim(30): " + prim(30))
  println("prim(-2): " + prim(-2))
  println("prim(-97): " + prim(-97))
  println("prim(30): " + prim(30))
  println("---")
  println("primEgyszeru(2)" + primEgyszeru(2))
  println("primEgyszeru(97)" + primEgyszeru(97))
  println("primEgyszeru(30)" + primEgyszeru(30))
  println("primEgyszeru(-2)" + primEgyszeru(-2))
  println("primEgyszeru(-97)" + primEgyszeru(-97))
  println("primEgyszeru(-30)" + primEgyszeru(-30))
  println("---")
  val l2 = l1.filter(elem => prim(elem))
  println(l2)
  val l3 = l1.filter(elem => primEgyszeru(elem))
  println(l3)

  println("-----------")

  println(forAllInt(l1, x => x < 4))
  println(forAllInt(l1, x => x > 0))
  println(forAllInt(l1, x => primEgyszeru(x)))
  println(forAllInt(l3, x => primEgyszeru(x)))

  println("-----------")

  val szoveg = "Erdo szelen erdo szeli haz toveben volt egy haz abban lakott het suntestver Sunpiroska Suntihamer Sunadorjan"
  println(betuk(szoveg))
}
