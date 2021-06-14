package taglessfinal.model

type ProductType = String
type ProductName = String
type Quantity = Int

final case class Item(id: String, _type: ProductType, name: ProductName, quantity: Quantity)

