import scala.annotation.tailrec

def interval_normal_if(N: Double): Double = {
  if (math.abs(N) == 1) { //mert egyebkent tobbszor is hozzaadnank
    1
  } else if (N > 1) {
    Math.pow(N, 2) + interval_normal_if(N - 1)
  } else {
    Math.pow(N, 2) + interval_normal_if(N + 1)
  }
}

def interval_normal_pattern(N: Double): Double = {//nem kene double, ha csak siman n*n-eznek nem meth.pow-oznek:(
  N match
    case N if Math.abs(N) == 1 => 1
    case N if N > 1 => Math.pow(N, 2) + interval_normal_if(N - 1)
    case _ => Math.pow(N, 2) + interval_normal_if(N + 1)
}

def interval_tail_if(N: Double): Double = {

  @tailrec
  def interval_tail_if(N: Double, sum: Double): Double = {
    if (math.abs(N) == 1) {
      sum + 1
    } else if (N > 1) {
      interval_tail_if(N - 1, sum + Math.pow(N, 2) )
    } else {
      interval_tail_if(N + 1, sum + Math.pow(N, 2) )
    }
  }

  interval_tail_if(N,0)
}

def interval_tail_pattern(N: Double): Double = {

  @tailrec
  def interval_tail_pattern(N: Double, sum: Double ): Double = {
    N match
      case N if Math.abs(N) == 1 => sum + 1
      case N if N > 1 => interval_tail_pattern(N - 1, sum + Math.pow(N, 2))
      case _ => interval_tail_pattern(N + 1, sum + Math.pow(N, 2))
  }

  interval_tail_pattern(N, 0)
}


object Intervallum extends App {
  println("interval_normal_if: " + interval_normal_if(-10) + ", " + interval_normal_if(10))
  println("interval_normal_pattern: " + interval_normal_pattern(-10) + ", " + interval_normal_pattern(10))
  println("interval_tail_if: " + interval_tail_if(-10) + ", " + interval_tail_if(10))
  println("interval_tail_pattern: " + interval_tail_pattern(-10) + ", " + interval_tail_pattern(10))
}