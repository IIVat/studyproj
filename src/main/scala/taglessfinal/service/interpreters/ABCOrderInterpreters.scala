package taglessfinal.service.interpreters

import cats.effect._
import taglessfinal.service.algebra.PartnerOrderAlgebra
import taglessfinal.model._
import taglessfinal.model.PartnerOrder._

object ABCOrderInterpreters {
  class ABCOrderInterpreter extends PartnerOrderAlgebra[IO, ABCOrder]:
    def partnerOrder(order: Order[ABCOrder]): IO[Order[ABCOrder]] =
      IO { order.copy(extraData = Some(ABCOrder(order.id, order.customer.firstName))) }
}
