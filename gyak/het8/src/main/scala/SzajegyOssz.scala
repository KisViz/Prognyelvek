import scala.annotation.tailrec

def digSum_normal_if(szam: Int): Int = {
  if (szam == 0) {
    0
  } else {
    Math.abs(szam) % 10 + digSum_normal_if(szam / 10)
  }
}

def digSum_normal_pattern(szam: Int): Int = {
  szam match
    case 0 => 0
    case _ => Math.abs(szam) % 10 + digSum_normal_pattern(szam / 10)
}

def digSum_tail_if(szam: Int): Int = {

  @tailrec
  def digSum_tail_if(szam: Int, szum: Int): Int = {
    if (szam == 0) {
      szum
    } else
      digSum_tail_if(szam / 10, szum + Math.abs(szam) % 10)
  }

  digSum_tail_if(szam, 0)
}

def digSum_tail_pattern(szam: Int): Int = {

  @tailrec
  def digSum_tail_pattern(szam: Int, szum: Int): Int = {
    szam match
      case 0 => szum
      case _ => digSum_tail_pattern(szam / 10, szum + Math.abs(szam) % 10)
  }

  digSum_tail_pattern(szam, 0)
}


object SzajegyOssz extends App {
  println("digSum_normal_if: " + digSum_normal_if(123) + ", " + digSum_normal_if(-123))
  println("digSum_normal_pattern: " + digSum_normal_pattern(123) + ", " + digSum_normal_pattern(-123))
  println("digSum_tail_if: " + digSum_tail_if(123) + ", " + digSum_tail_if(-123))
  println("digSum_tail_pattern: " + digSum_tail_pattern(123) + ", " + digSum_tail_pattern(-123))
}