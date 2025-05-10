/**
 * Hozz létre egy függvényt, amely egy végtelen LazyList-et generál adott szabály
 * szerint: minden harmadik számot szorozzon meg kettővel, minden ötödik számhoz
 * adjon hozzá hármat. Kérjük le a lista első 20 elemét.
 * */

def elso(elozo: Int): LazyList[Int] = {
  (elozo + 1) match
    case x if (elozo + 1) % 3 == 0 && (elozo + 1) % 5 == 0 =>
      ((x * 2) + 3) #:: elso(elozo + 1) //15 miatt
    case a if (elozo + 1) % 3 == 0 => (a * 2) #:: elso(elozo + 1)
    case b if (elozo + 1) % 5 == 0 => (b + 3) #:: elso(elozo + 1)
    case _ => (elozo + 1) #:: elso(elozo + 1)
}

/**Készíts egy függvényt, amely egy egész számokat tartalmazó végtelen listát,
 * egy szűrési feltételt, valamint két alternatív végrehajtási stratégiát kap
 * paraméterként. A függvény feladata, hogy megtalálja a lista első olyan elemét,
 * amelyre a megadott feltétel (Int => Boolean) teljesül. Amint ez az első
 * találat megvan, a függvény ellenőrizze, hogy a szám páros vagy páratlan:
    -Ha a szám páros, akkor hajtsa végre az első stratégiát
    -Ha páratlan, akkor hajtsa végre a második stratégiát
 A két stratégia típusa => Unit legyen, azaz a kifejezések csak akkor
 értékelődjenek ki, amikor valóban futtatni kell őket.
 */

def masodik(list: LazyList[Int], feltetel: Int => Boolean, parosStrat: => Unit, paratlanStrat: => Unit): Unit = {
  list.find(feltetel) match
    case Some(ertek) =>
      if (ertek % 2 == 0) {
//        println("paros " + ertek)
        parosStrat
      } else {
//        println("paratlan " + ertek)
        paratlanStrat
      }
    case None => ()
}



object feladatok11 extends App {
  println(elso(0).take(20).toList)

  println("---------")

  val szamok = LazyList.from(1)
  val feltetel: Int => Boolean = x => x > 100 && x % 17 == 0
  lazy val parosStrategia = println("Páros számot találtunk!")
  lazy val paratlanStrategia = println("Páratlan számot találtunk!")

  masodik(szamok, feltetel, parosStrategia, paratlanStrategia)
}