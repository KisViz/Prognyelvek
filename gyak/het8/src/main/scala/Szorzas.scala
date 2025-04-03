import scala.annotation.tailrec

def multi_normal_if(a: Int, b: Int): Int = {
  if (b == 0) {
    0
  } else if (Math.abs(b) == 1) { //ugyan ugy mint az intervallumnal
    a
  } else if (b > 0) {
    a + multi_normal_if(a, b - 1)
  } else {
    -multi_normal_if(a, -b) //ha negativ a b legegyszerubb elojelet valtani
    //es a vegen tessuk negativva az egyebeknt poztiv osszeget
  }
}

def multi_normal_if_mashogy(a: Int, b: Int): Int = {
  if (b == 0) {
    0
  } else if (b > 0) {
    a + multi_normal_if(a, b - 1)
  } else {
    //ez jobb lesz a tail recursivehoz
    -a + multi_normal_if(a, b + 1)
  }
}

def multi_normal_pattern(a: Int, b: Int): Int = {
  b match
    case 0 => 0
    case b if Math.abs(b) == 1 => a
    case b if b > 0 => a + multi_normal_if(a, b - 1)
    case _ =>  -multi_normal_if(a, -b)
}

def multi_tail_if(a: Int, b: Int): Int = {

  @tailrec
  def multi_tail_if(a: Int, b: Int, sum: Int): Int = {
    if (b == 0) {
      sum
    } else if (b > 0) {
      multi_tail_if(a, b - 1, sum + a)
    } else {
      multi_tail_if(a, b + 1, sum - a)
      //a masodik mo alapjan konnyebb
    }
  }
  multi_tail_if(a, b, 0)
}

def multi_tail_pattern(a: Int, b: Int): Int = {

  @tailrec
  def multi_tail_pattern(a: Int, b: Int, sum: Int): Int = {
    b match
      case 0 => sum
      case b if b > 0 => multi_tail_pattern(a, b - 1, sum + a)
      case _ => multi_tail_pattern(a, b + 1, sum - a)
  }
  multi_tail_pattern(a, b, 0)
}


object Szorzas extends App {
  println("multi_normal_if: " + multi_normal_if(3, 5) + ", " + multi_normal_if(-3, -5) + ", " + multi_normal_if(3, -5) + ", " + multi_normal_if(-3, 5))
  println("multi_normal_if_mashogy: " + multi_normal_if_mashogy(3, 5) + ", " + multi_normal_if_mashogy(-3, -5) + ", " + multi_normal_if_mashogy(3, -5) + ", " + multi_normal_if_mashogy(-3, 5))
  println("multi_normal_pattern: " + multi_normal_pattern(3, 5) + ", " + multi_normal_pattern(-3, -5) + ", " + multi_normal_pattern(3, -5) + ", " + multi_normal_pattern(-3, 5))
  println("multi_tail_if: " + multi_tail_if(3, 5) + ", " + multi_tail_if(-3, -5) + ", " + multi_tail_if(3, -5) + ", " + multi_tail_if(-3, 5))
  println("multi_tail_pattern: " + multi_tail_pattern(3, 5) + ", " + multi_tail_pattern(-3, -5) + ", " + multi_tail_pattern(3, -5) + ", " + multi_tail_pattern(-3, 5))
}