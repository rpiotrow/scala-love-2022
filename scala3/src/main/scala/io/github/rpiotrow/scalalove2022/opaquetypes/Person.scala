package io.github.rpiotrow.scalalove2022.opaquetypes

opaque type FirstName = String
object FirstName:
  def apply(value: String): FirstName = value

opaque type LastName = String
object LastName:
  def apply(value: String): LastName = value

case class Person(firstName: FirstName, lastName: LastName)

object Examples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
