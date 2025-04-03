import scala.annotation.tailrec

def fibo_normal_if(N: Int): Int = {
  if (N <= 0) {
    0
  } else if (N == 1) {
    1
  } else {
    fibo_normal_if(N - 1) + fibo_normal_if(N - 2)
  }
}

def fibo_normal_pattern(N: Int): Int = {
  N match
    case N if N <= 0 => 0
    case 1 => 1
    case n => fibo_normal_pattern(N - 1) + fibo_normal_pattern(N - 2)
//    case _ => fibo_normal_pattern(N - 1) + fibo_normal_pattern(N - 2)
}

def fibo_tail_if(N: Int): Int = {

  @tailrec
  def fibo_tail_if(N: Int, a: Int, b: Int): Int = {
    if (N <= 0) {
      a
    } else {
      fibo_tail_if(N - 1, b, a + b)
    }
  }

  fibo_tail_if(N, 0, 1)
}

def fibo_tail_pattern(N: Int): Int = {

    @tailrec
    def fibo_tail_pattern(N: Int, a: Int, b: Int): Int = {
      N match
        case N if N <= 0 => a
        case n => fibo_tail_pattern(N - 1, b, a + b)
    }

    fibo_tail_pattern(N, 0 , 1)
}




object Fibonacci extends App {
    println("fibo_normal_if: " + fibo_normal_if(-10) + ", " + fibo_normal_if(10))
    println("fibo_normal_pattern: " + fibo_normal_pattern(-10) + ", " + fibo_normal_pattern(10))
    println("fibo_tail_if: " + fibo_tail_if(-10)+ ", " + fibo_tail_if(10))
    println("fibo_tail_pattern: " + fibo_tail_pattern(-10)+ ", " + fibo_tail_pattern(10))
}
