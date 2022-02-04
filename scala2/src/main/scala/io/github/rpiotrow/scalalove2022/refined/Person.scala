package io.github.rpiotrow.scalalove2022.refined

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.string._
import io.estatico.newtype.macros.newtype

object PersonDomain {

  type FirstNameType = String Refined MatchesRegex[".*\\S+.*"]
  @newtype case class FirstName(value: FirstNameType)

  type LastNameType = String Refined MatchesRegex[".*\\S+.*"]
  @newtype case class LastName(value: LastNameType)

  case class Person(firstName: FirstName, lastName: LastName)
}

object PersonExamples {
  import PersonDomain._

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
}
