package io.github.rpiotrow.scalalove2022.primitives

case class Person(firstName: String, lastName: String)

object PersonExamples:

  val johnDoe: Person = Person("John", "Doe")

  val doeJohn: Person = Person("Doe", "John")
