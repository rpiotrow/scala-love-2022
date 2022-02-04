package io.github.rpiotrow.scalalove2022.valueclasses

case class FirstName(value: String) extends AnyVal

case class LastName(value: String) extends AnyVal

case class Person(firstName: FirstName, lastName: LastName)

object Examples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
