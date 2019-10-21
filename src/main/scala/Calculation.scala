import Calculation.FromInts.FromInt
import Calculation.Num.Num

object Calculation extends App {
  def average(list: List[Int]): Int = list.foldLeft(0)(_ + _) / list.size

  object Num {

    trait Num[A] {
      def plus(a: A, b: A): A

      def minus(a: A, b: A): A

      def multiply(a: A, b: A): A

      def divide(a: A, b: A): A

      def zero: A
    }


    // Numの性質をもつInt型のインスタンス
    implicit object IntNum extends Num[Int] {
      def plus(a: Int, b: Int): Int = a + b

      def minus(a: Int, b: Int): Int = a - b

      def multiply(a: Int, b: Int): Int = a * b

      def divide(a: Int, b: Int): Int = a / b

      def zero = 0
    }

    implicit object DoubleNum extends Num[Double] {
      def plus(a: Double, b: Double): Double = a + b

      def minus(a: Double, b: Double): Double = a - b

      def multiply(a: Double, b: Double): Double = a * b

      def divide(a: Double, b: Double): Double = a / b

      def zero = 0.0
    }

  }

  object FromInts {

    trait FromInt[A] {
      def to(from: Int): A
    }

    object FromInt {

      implicit object FromIntToInt extends FromInt[Int] {
        def to(from: Int): Int = from
      }

      implicit object FromIntToDouble extends FromInt[Double] {
        def to(from: Int): Double = from
      }

    }

  }

  //  def average[A](list: List[A])(implicit a: Num[A], b: FromInt[A]): A = {
  //    val length = list.length
  //    val sum = list.foldLeft(a.zero)((x, y) => a.plus(x, y))
  //    a.divide(sum, b.to(length))
  //  }

  def average[A: Num : FromInt](list: List[A]): A = {
    val a = implicitly[Num[A]]
    val b = implicitly[FromInt[A]]
    val length = list.length
    val sum = list.foldLeft(a.zero)((x, y) => a.plus(x, y))
    a.divide(sum, b.to(length))
  }

  def median[A: Num : FromInt : Ordering](list: List[A]): A = {
    val num = implicitly[Num[A]]
    val ordering = implicitly[Ordering[A]]
    val int = implicitly[FromInt[A]]

    val sorted = list.sorted
    val size = list.length

    if (size % 2 == 1) {
      sorted(size / 2)
    } else {
      val fst = sorted((size / 2) - 1)
      val snd = sorted(size / 2)
      num.divide(num.plus(fst, snd), int.to(2))
    }
  }

  val list = List(3, 4, 5)
  println(average(list))

  assert(2 == median(List(1, 3, 2)))
  assert(2.5 == median(List(1.5, 2.5, 3.5)))
  assert(3 == median(List(1, 3, 4, 5)))
}
