package io.github.rpiotrow.scalalove2022.monixnewtypes

import io.github.rpiotrow.scalalove2022.IsbnValidator
import monix.newtypes.*

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
object Title extends NewtypeValidated[String]:
  def apply(value: String): Either[BuildFailure[Title], Title] =
    if !value.isBlank then
      Right(unsafeCoerce(value))
    else
      Left(BuildFailure("empty title"))

type ISBN = ISBN.Type
object ISBN extends NewtypeValidated[String]:
  def apply(value: String): Either[BuildFailure[ISBN], ISBN] =
    if IsbnValidator.validate(value) then
      Right(unsafeCoerce(value))
    else
      Left(BuildFailure("invalid ISBN"))

trait NotBlankString(failureMessage: String) extends NewtypeValidated[String]:
  def apply(value: String): Either[BuildFailure[Type], Type] =
    if !value.isBlank then
      Right(unsafeCoerce(value))
    else
      Left(BuildFailure(failureMessage))

//type Title = Title.Type
//object Title extends NonEmptyString("empty title")

type PublishingHouse = PublishingHouse.Type
object PublishingHouse extends NotBlankString("empty publishing house")

type Edition = Edition.Type
object Edition extends NotBlankString("empty edition")

type Language = Language.Type
object Language extends NotBlankString("empty language")

type PublishYear = PublishYear.Type
object PublishYear extends NewtypeValidated[Int]:
  def apply(value: Int): Either[BuildFailure[Type], Type] =
    if value >= 1970 && value <= 2050 then
      Right(unsafeCoerce(value))
    else
      Left(BuildFailure("invalid publish year"))

object BookExamples:

  val book: Either[BuildFailure[_], Book] =
    for
      isbn <- ISBN("9781617299582")
      title <- Title("Functional Programming in Scala")
      authors = List(
        Person(FirstName("Michael"), LastName("Pilquist")),
        Person(FirstName("Rúnar"), LastName("Bjarnason")),
        Person(FirstName("Paul"), LastName("Chiusano"))
      )
      publishingHouse <- PublishingHouse("Manning Publications")
      edition <- Edition("Second Edition")
      publishYear <- PublishYear(2022)
      language <- Language("English")
    yield Book(title, authors, publishingHouse, edition, publishYear, language, None, None, isbn)
