package io.github.rpiotrow.scalalove2022.zio

import io.github.rpiotrow.scalalove2022.IsbnValidator
import zio.prelude.{Assertion, Newtype, Subtype, Validation}
import zio.prelude.Assertion.*

case class Book(
  title: Title,
  authors: List[Person],
  publishingHouse: PublishingHouse,
  edition: Edition,
  publishYear: PublishYear,
  language: Language,
  originalLanguage: Option[Language],
  translators: Option[List[Person]],
  isbn: ISBN
)

type Title = Title.Type
object Title extends Newtype[String]:
  override inline def assertion: Assertion[String] =
    matches(".*\\S+.*".r)

type ISBN = ISBN.Type
object ISBN extends Subtype[String]:
  override inline def assertion: Assertion[String] =
    matches("\\d{9}[0-9X]|\\d{13}".r)
  extension (self: ISBN)
    def validated: Validation[String, ISBN] =
      Validation.fromPredicateWith("Invalid ISBN")(self)(
        IsbnValidator.validate
      )

trait NotBlankString extends Newtype[String]:
   override inline def assertion: Assertion[String] = matches(".*\\S+.*".r)

//type Title = Title.Type
//object Title extends NonEmptyString

type PublishingHouse = PublishingHouse.Type
object PublishingHouse extends NotBlankString

type Edition = Edition.Type
object Edition extends NotBlankString

type Language = Language.Type
object Language extends NotBlankString

type PublishYear = PublishYear.Type
object PublishYear extends Newtype[Int]:
   override inline def assertion: Assertion[Int] = between(1970, 2050)

object BookExamples:

  val title: Title = Title("Functional Programming in Scala")
  val authors: List[Person] = List(
    Person(FirstName("Michael"), LastName("Pilquist")),
    Person(FirstName("Rúnar"), LastName("Bjarnason")),
    Person(FirstName("Paul"), LastName("Chiusano"))
  )
  val publishingHouse: PublishingHouse = PublishingHouse("Manning Publications")
  val edition: Edition = Edition("Second Edition")
  val publishYear: PublishYear = PublishYear(2022)
  val language: Language = Language("English")

  val redBookValidated: Validation[String, Book] =
    for
      isbn <- ISBN("9781617299582").validated
    yield Book(title, authors, publishingHouse, edition, publishYear, language, None, None, isbn)
