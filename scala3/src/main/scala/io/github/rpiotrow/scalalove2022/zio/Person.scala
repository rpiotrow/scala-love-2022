package io.github.rpiotrow.scalalove2022.zio

import zio.prelude.Newtype

type FirstName = FirstName.Type
object FirstName extends Newtype[String]

type LastName = LastName.Type
object LastName extends Newtype[String]

case class Person(firstName: FirstName, lastName: LastName)

object PersonExamples:

  val johnDoe: Person = Person(FirstName("John"), LastName("Doe"))
