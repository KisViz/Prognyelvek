import scala.annotation.tailrec

object orai {
  def noparam(): Boolean = {
    true
  }

  def doubled(num: Double): Double = {
    num + num
  }

  def combined(num: Double): Double = {
    if (noparam() || 9>10)
      1.5 + doubled(num)
    else //mindenképp ki kell írni az else ágat is
      2.5 + doubled(num)
  }

//  def masik(num: Double): Unit = {
//    if (noparam())
//      1.5 + doubled(num)
//  }

  def builtint():Unit={
    val pi: Double = math.Pi
    val neg = -5.7

    println(math.min(neg, pi))
    println(math.max(neg, pi))
    println(math.abs(neg))
    println(math.sqrt(pi))
    println(math.pow(2, 3))
    println(math.round(pi)) // legközelebbi egész számra kerekít
    println(math.floor(neg)) // legközelebbi egész számra kerekít felfelé
    println(math.ceil(neg)) // legközelebbi egész számra kerekít lefelé
    println(math.random()) // 0 és 1 közötti lebegőpontos szám
    println(math.signum(-5)) // 1 ha pozítv, -1 ha negatív, 0 ha nulla a szám

    println(-7 % 3) // -1, megtartja az előjelet
    println(math.floorMod(-7, 3)) // mindig nemnegatív és kerekít a floor szerint

  }


//val
  def factorial(num: Int): Long = {
    if (num <= 1)
      1
    else
      num * factorial(num - 1)
  }

//  def doubled(num: Double): Double ={
//    num = 1
//    num + num
//  }

  def factorial2(num:Int):Long={
    num match{
      case k if k<0=>0
      case 0|1=>1
//      case 2=> 2*1
      case n=>n*factorial2(num-1)
//      case _=>n*factorial2(num-1)

    }
  }

//  @tailrec
  def sum(num: Int): Long = {
    if (num <= 0)
      0
    else
      num + sum(num - 1)
  }

  def sumTailRec(num:Int):Long={

    @tailrec
    def sumTailRec(num: Int, res: Long):Long={
      if (num<=0)
        res
      else
        sumTailRec(num-1, res+num)
    }

    sumTailRec(num, 0)
  }

  def recMul(a:Int, b:Int):Int={
    @tailrec
    def recMul(a: Int, b: Int, valami:Int): Int = {
      if(b==0)
        valami
      else
        recMul(a, b-1, valami + a)
    }
    recMul(a,b,0)
  }


  def main(args: Array[String]): Unit = {
    println(combined(34))
    //println(factorial2(-45)) bajsz mert nincs ott ez: case k if k<0=>0
//    println(sum(15000)) bajsz
    println(sumTailRec(15000))
    println(recMul(2,-21))
  }
}
