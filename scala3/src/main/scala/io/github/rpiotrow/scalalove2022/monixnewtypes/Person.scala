package io.github.rpiotrow.scalalove2022.monixnewtypes

import monix.newtypes._

type FirstName = FirstName.Type
object FirstName extends NewtypeWrapped[String]

type LastName = LastName.Type
object LastName extends NewtypeWrapped[String]

case class Person(firstName: FirstName, lastName: LastName)

object PersonExamples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
