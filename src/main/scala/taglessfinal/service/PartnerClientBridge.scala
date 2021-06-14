package taglessfinal.service

import taglessfinal.model.PartnerOrder
import taglessfinal.service.algebra.PartnerClientAlgebra

trait PartnerClientBridge[T <: PartnerOrder]:
  def apply[F[_]](using L: PartnerClientAlgebra[F, T]): F[Int]
