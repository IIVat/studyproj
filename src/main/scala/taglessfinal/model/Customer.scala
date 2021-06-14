package taglessfinal.model

type FirstName = String
type LastName = String

final case class Customer(id: String, firstName: FirstName, lastName: LastName)

