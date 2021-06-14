package taglessfinal

import cats.{Id, Monad}
import cats.effect._
import cats.implicits._
import cats.effect.unsafe.implicits.global
import taglessfinal.service.{PartnerClientBridge, PartnerService}
import taglessfinal.service.algebra.PartnerClientAlgebra
import taglessfinal.service.interpreters.ABCOrderInterpreters._
import taglessfinal.model._
import taglessfinal.model.PartnerOrder._
import taglessfinal.service.interpreters.ABCOrderInterpreters._
import taglessfinal.service.interpreters.ABCClientInterpreters.given

import java.util.UUID

@main def TFPartnerApp =
  def sendToABC(order: Order[ABCOrder]) = new PartnerClientBridge[ABCOrder] {
    override def apply[F[_]](using L: PartnerClientAlgebra[F, ABCOrder]): F[Int] =
      L.send(order)
  }

  val customer = Customer(id = UUID.randomUUID().toString, firstName = "john", lastName = "doe")

  val address = Address(
    country = "Germany",
    city = "Berlin",
    street = "Gerstr.",
    houseNumber = "36A",
    postCode = "13187")

  def order(name: String): Order[ABCOrder] = Order(id = UUID.randomUUID(), customer, address, Vector.empty[Item], Some(ABCOrder(UUID.randomUUID(), name)))

  val ABCIOClient = sendToABC(order("io")).apply(using clientABCIO)
  new PartnerService[IO, IO, ABCOrder](clientABCIO, new ABCOrderInterpreter, x => x).ship(order("john")).unsafeRunSync()

  sendToABC(order("id")).apply(using clientABCId)

  def orderGen(warehouse: String) = warehouse match {
    case "ABC" =>
      new PartnerService[IO, IO, ABCOrder](clientABCIO, new ABCOrderInterpreter, x => x)
        .ship(order("john"))
    case _ =>
  }

