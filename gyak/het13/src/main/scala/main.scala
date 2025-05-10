def lazyFunc(x: Int, mode: String): Int = {
  println("Counting " + mode) // MELLÉKHATÁS!
  x * 2
}

def tempFunc(): Int = {
  println("Hosszú számítás elindult...")
  Thread.sleep(1000) // Java osztályokhoz továbbra is hozzáférünk
  println("Számítás kész.")
  99
}

def callFunc(x: Int, y: => Int): Int = {
  if x > 10
    then println("Greater..")
    else y
  y
}

def fibonacci(a: Int, b: Int): LazyList[Int] = {
  a #:: fibonacci(b, a + b)
}

@main
def main(): Unit = {
  def res_def = lazyFunc(10, "def") /*ez egy újrafelhasználható függvénydefiníció lesz és csak akkor fut le, amikor használjuk - minden egyes használatnál újból kiszámításra kerül az eredmény*/
  lazy val res_lazy = lazyFunc(10, "lazy") /* a változó értéke csak akkor kerül kiszámításra, amikor először használjuk, utána a cache-ből kerül újra és újra betöltésre*/
  val res_val = lazyFunc(20, "strict") /*azonnal kiértékelésre kerül, akár használjuk, akár nem*/

  println(res_def + res_lazy + res_val)
  println(res_def + res_lazy + res_val)


  println("------------")
  println("tempFunc: " + tempFunc())
  println("------------")
  print("callFunc 1: ")
  callFunc(1, tempFunc())
  println("------------")
  print("callFunc 11: ")
  callFunc(11, tempFunc())


  println("------------")
  val lazyNums: LazyList[Int] = LazyList.from(1)
  println(lazyNums.take(10))
  println(lazyNums.take(10).toList)
  println(fibonacci(0, 1).take(10).toList)


}

