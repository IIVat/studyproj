package taglessfinal.service.algebra

import taglessfinal.model._

trait PartnerOrderAlgebra[G[_], P <: PartnerOrder]:
  def partnerOrder(order: Order[P]): G[Order[P]]
