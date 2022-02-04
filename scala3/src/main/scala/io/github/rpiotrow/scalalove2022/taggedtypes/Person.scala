package io.github.rpiotrow.scalalove2022.taggedtypes

trait FirstNameTag
type FirstName = String with FirstNameTag
object FirstName:
  def apply(value: String): FirstName = value.asInstanceOf[FirstName]

trait LastNameTag
type LastName = String with LastNameTag
object LastName:
  def apply(value: String): LastName = value.asInstanceOf[LastName]

case class Person(firstName: FirstName, lastName: LastName)

object Examples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))

