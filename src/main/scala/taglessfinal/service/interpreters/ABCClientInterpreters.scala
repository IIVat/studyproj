package taglessfinal.service.interpreters

import cats.{Id, Monad}
import cats.effect._
import taglessfinal.service.algebra.PartnerClientAlgebra
import taglessfinal.model._
import taglessfinal.model.PartnerOrder._

object ABCClientInterpreters {
  class ABCClientInterpreter extends PartnerClientAlgebra[IO, ABCOrder] {
    override def send(order: Order[ABCOrder]): IO[Int] = {
      for {
        _ <- IO { println("io") }
        b <- IO.pure(1)
      } yield b
    }
  }

  object ABCClientInterpreter {
    def apply[F[_]]: ABCClientInterpreter =
      new ABCClientInterpreter()
  }

  given clientABCIO: PartnerClientAlgebra[IO, ABCOrder] with
    def send(order: Order[ABCOrder]): IO[Int] =
      for {
        _ <- IO{ println("io") }
        b <- IO.pure(1)
      } yield b
    
  given clientABCId: PartnerClientAlgebra[Id, ABCOrder] with
    override def send(order: Order[ABCOrder]): Int = {
      println("id")
      1
    }
    
}
