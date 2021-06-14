package taglessfinal.model

import java.util.UUID

enum PartnerOrder:
  case XYZOrder(id: UUID, userName: String)
  case ABCOrder(id: UUID, userName: String)
