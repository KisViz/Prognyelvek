case class ComplexCC(re: Double, im: Double) {

  def osszead(elso: ComplexCC, masodik: ComplexCC): ComplexCC = {
    ComplexCC(elso.re + masodik.re, elso.im + masodik.im)
  }

  def szoroz(elso: ComplexCC, masodik: ComplexCC): ComplexCC = {
    ComplexCC(
      (elso.re * masodik.re) - (elso.im * masodik.im),
      (elso.re * masodik.im) + (elso.im * masodik.re)
    )
  }
}

object ComplexO {
  def osszead(elso: (Double, Double), masodik: (Double, Double)): (Double, Double) = {
    (elso._1 + masodik._1, elso._2 + masodik._2)
  }

  def szoroz(elso: (Double, Double), masodik: (Double, Double)): (Double, Double) = {
    (
      elso._1 * masodik._1 - elso._2 * masodik._2,
      elso._1 * masodik._2 + elso._2 * masodik._1
    )
  }
}

object Komplex extends App {
  val c1 = ComplexCC(2.0, 3.0)
  val c2 = ComplexCC(1.0, 4.0)

  println("case class")
  println("osszead: " + c1.osszead(c1, c2))
  println("szoroz: " + c1.szoroz(c1, c2))

  println()
  println("object")
  val o1 = (2.0, 3.0)
  val o2 = (1.0, 4.0)
  println("osszead: " + ComplexO.osszead(o1,o2))
  println("szoroz: " + ComplexO.szoroz(o1,o2))

}