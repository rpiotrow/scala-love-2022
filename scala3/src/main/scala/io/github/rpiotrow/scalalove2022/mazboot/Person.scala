package io.github.rpiotrow.scalalove2022.mazboot

import mazboot.validations.strings.MatchesRegex

val NotBlankString = MatchesRegex(".*\\S+.*")

val FirstName = NotBlankString
type FirstName = FirstName.Valid

val LastName = NotBlankString
type LastName = LastName.Valid

case class Person(firstName: FirstName, lastName: LastName)

object PersonExamples:

  val johnDoe: Either[_, Person] =
    for
      firstName <- FirstName.validateEither("John")
      lastName  <- LastName.validateEither("Doe")
    yield Person(firstName, lastName)
