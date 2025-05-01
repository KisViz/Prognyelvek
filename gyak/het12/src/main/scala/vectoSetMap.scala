object vectoSetMap extends App {
  val v1 = Vector(10, 20, 30)
  val v2 = v1.updated(0, 111)
  val v3 = v1 :+ 40
  val v4 = 0 +: v1
  println(v1)
  println(v1(0))
  println(v2)
  println(v3)
  println(v4)
  println(v1)

  println("----------------")

  val s1 = Set(1, 2, 3, 3)
  val s2 = s1 + 4
  val s3 = s1 - 2
  println(s1)
  println(s1.contains(2))
  println(s2)
  println(s3)
  println(s1)

  println("----------------")

  val m1 = Map("One" -> 1, "Two" -> 2)
  val m2 = m1.updated("Two", "One")
  val m3 = m1 + ("Three" -> 3)
  val m4 = m1 - "Two"
  println(m1)
  println(m1.get("One"))
  println(m2)
  println(m3)
  println(m4)
  println(m1)
}