package io.github.rpiotrow.scalalove2022.kebs

import pl.iterators.kebs.tagged._
import pl.iterators.kebs.tag.meta.tagged

@tagged object PersonDomain {

  trait FirstNameTag
  type FirstName = String @@ FirstNameTag
// macro adds:
//  object FirstName {
//    def apply(arg: String): FirstName = from(arg)
//    def from(arg: String): FirstName = arg.taggedWith[FirstNameTag]
//  }

  trait LastNameTag
  type LastName = String @@ LastNameTag

  case class Person(firstName: FirstName, lastName: LastName)
}

object PersonExamples {
  import PersonDomain._

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
}
