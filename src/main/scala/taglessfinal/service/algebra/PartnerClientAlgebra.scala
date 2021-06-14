package taglessfinal.service.algebra

import taglessfinal.model._

trait PartnerClientAlgebra[F[_], T <: PartnerOrder]:
  def send(order: Order[T]): F[Int]
