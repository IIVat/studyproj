package taglessfinal.service

import cats.arrow
import cats.effect
import cats.implicits._
import cats.{Id, Monad}
import taglessfinal.model.PartnerOrder
import taglessfinal.model._
import taglessfinal.service.algebra.{PartnerClientAlgebra, PartnerOrderAlgebra}

import java.util.UUID

//cats-tagless [A] => F[A] => G[A]
//FunctionK[F[_], G[_]] -> gf
//trait FunctionK[G[_], F[_]]:
//  def apply[A](fa: G[A]): F[A]

type FunctionK[G[_], F[_]] = [A] =>> G[A] => F[A]

class PartnerService[F[_]: Monad, G[_], T <: PartnerOrder](
                                                            partnerClient: PartnerClientAlgebra[F, T],
                                                            transformer: PartnerOrderAlgebra[G, T],
                                                            fk: FunctionK[G, F][Order[T]]):
  def ship(order: Order[T]/*, shipment: Shipment*/): F[Int] =
    for {
      order <- fk(transformer.partnerOrder(order))
      r <- partnerClient.send(order)
    } yield r

