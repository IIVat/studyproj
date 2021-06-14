package taglessfinal.service

import taglessfinal.model.PartnerOrder
import taglessfinal.service.algebra.PartnerOrderAlgebra

trait PartnerOrderBridge[T <: PartnerOrder] {
  def apply[F[_]](using A: PartnerOrderAlgebra[F, T]): F[T]
}
