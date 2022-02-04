package io.github.rpiotrow.scalalove2022.kebs

import pl.iterators.kebs.opaque._

opaque type FirstName = String
object FirstName extends Opaque[FirstName, String]

opaque type LastName = String
object LastName extends Opaque[LastName, String]

case class Person(firstName: FirstName, lastName: LastName)

object PersonExamples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
