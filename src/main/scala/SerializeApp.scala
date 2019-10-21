object SerializeApp extends App {
  object Serializers {
    trait Serializer[A] {
      def serialize(obj: A): String
    }

    def string[A:Serializer](obj: A): String = {
      implicitly[Serializer[A]].serialize(obj)
    }

    implicit object IntSerializer extends Serializer[Int] {
      def serialize(obj: Int): String = obj.toString
    }

    implicit object DoubleSerializer extends Serializer[Double]  {
      def serialize(obj: Double): String = obj.toString
    }

    implicit def ListSerializer[A](implicit serializer: Serializer[A]): Serializer[List[A]] = new Serializer[List[A]]{
      def serialize(obj: List[A]): String = obj.map(serializer.serialize).mkString("[",",","]")
    }
  }

  import Serializers._

  string(List(1, 2, 3))
}
