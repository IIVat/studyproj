package taglessfinal.model

import java.util.UUID
import taglessfinal.model.PartnerOrder

final case class Order[P <: PartnerOrder](id: UUID,
                                          customer: Customer,
                                          address: Address,
                                          items: Vector[Item],
                                          extraData: Option[P])
