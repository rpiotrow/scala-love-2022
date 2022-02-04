package io.github.rpiotrow.scalalove2022.refined

import eu.timepit.refined.*
import eu.timepit.refined.api.*
import eu.timepit.refined.string.*

object PersonDomain {

  type NotBlankStringRefine = MatchesRegex[".*\\S+.*"]
  type NotBlankString = String Refined NotBlankStringRefine

  opaque type FirstName = NotBlankString
  object FirstName:
    def apply(value: NotBlankString): FirstName = value

  opaque type LastName = NotBlankString
  object LastName:
    def apply(value: NotBlankString): LastName = value

  case class Person(firstName: FirstName, lastName: LastName)
}

object PersonExamples {
  import PersonDomain._

  for
    firstName <- refineV[NotBlankStringRefine]("John")
    lastName <- refineV[NotBlankStringRefine]("Doe")
  yield Person(FirstName(firstName), LastName(lastName))
}
