//package catstudy
//
//import izumi.reflect.macrortti.LightTypeTagRef.Variance.Contravariant
//
//object CatsTest {
//  trait Filter[T] {
//    def filter(value: T): Boolean
//  }
//
//  trait Functor[F[_]] {
//    def map[A, B](fa: F[A])(f: A => B): F[B]
//  }
//
//  object Filter {
//    def apply[A](implicit instance: Filter[A]): Filter[A] = instance
//  }
//
//  trait Contravariant[F[_]] {
//    def contramap[A, B](fa: F[A])(f: B => A): F[B]
//  }
//
//  object Contravariant {
//    def apply[F[_]](implicit instance: Contravariant[F]): Contravariant[F] = instance
//  }
//
//  implicit object StringFilter extends Filter[String] {
//    override def filter(value: String): Boolean = value.length > 5
//  }
//
//  def filter[A](v: A)(implicit flt: Filter[A]) = flt.filter(v)
//
//  val simpleFilterFunctor = new Functor[Filter] {
//    override def map[A, B](fa: Filter[A])(f: A => B): Filter[B] = new Filter[B] {
//      override def filter(value: B): Boolean = ??? //fa.filter(f(value))
//    }
//  }
//
//  implicit val simpleFilterContravariant = new Contravariant[Filter] {
//    override def contramap[A, B](fa: Filter[A])(f: B => A): Filter[B] = new Filter[B] {
//      override def filter(value: B): Boolean = fa.filter(f(value))
//    }
//  }
//
//  val filterString = Filter[String]
//  implicit val filterInt: Filter[Int] = Contravariant[Filter].contramap[String, Int](filterString)(_.toString)
//
//  implicit def filterOption[T](implicit flt: Filter[T]): Filter[Option[T]] =
//    simpleFilterContravariant.contramap(flt)(_.get)
//
//  trait CustomParser[A] {
//    def encode(value: A): String
//    def decode(value: String): A
//    def imap[B](dec: A => B, enc: B => A): CustomParser[B] = { val self = this
//      new CustomParser[B] {
//        def encode(value: B): String =
//          self.encode(enc(value))
//        def decode(value: String): B =
//          dec(self.decode(value))
//      }
//    }
//  }
//
//  def encode[A](value: A)(implicit c: CustomParser[A]): String = c.encode(value)
//  def decode[A](value: String)(implicit c: CustomParser[A]): A = c.decode(value)
//
//
//
//  def main(args: Array[String]): Unit = {
//    println(filter("hello"))
//    println(filter("hello world!"))
//    println(filter(3))
//    println(filter(Option("some string")))
//  }
//}
