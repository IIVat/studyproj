package taglessfinal.model

type Country = String
type City = String
type Street = String
type HouseNumber = String
type PostCode = String

final case class Address(country: Country,
                         city: City,
                         street: Street,
                         houseNumber: HouseNumber,
                         postCode: PostCode)
