package io.github.rpiotrow.scalalove2022.kebs

import io.github.rpiotrow.scalalove2022.IsbnValidator
import pl.iterators.kebs.opaque.*

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

opaque type Title = String
object Title extends Opaque[Title, String]:
  override protected def validate(value: String):
      Either[String, Title] =
    if !value.isBlank then
      Right(value)
    else
      Left("empty title")

opaque type ISBN = String
object ISBN extends Opaque[ISBN, String]:
  override protected def validate(value: String):
      Either[String, ISBN] =
    if IsbnValidator.validate(value) then
      Right(value)
    else
      Left("invalid ISBN")

trait NotBlankString[OpaqueType](failureMessage: String)(using ev: OpaqueType =:= String) extends Opaque[OpaqueType, String]:
  override protected def validate(value: String): Either[String, OpaqueType] =
    if !value.isBlank then
      Right(ev.flip.apply(value))
    else
      Left("empty title")

//type Title = Title.Type
//object Title extends NonEmptyString("empty title")

opaque type PublishingHouse = String
object PublishingHouse extends NotBlankString[PublishingHouse]("empty publishing house")

opaque type Edition = String
object Edition extends NotBlankString[Edition]("empty edition")

opaque type Language = String
object Language extends NotBlankString[Language]("empty language")

opaque type PublishYear = Int
object PublishYear extends Opaque[PublishYear, Int]:
  override protected def validate(value: Int): Either[String, PublishYear] =
    if value >= 1970 && value <= 2050 then
      Right(value)
    else
      Left("invalid publish year")

object BookExamples:

  val book: Either[String, Book] =
    for
      isbn <- ISBN.from("9781617299582")
      title <- Title.from("Functional Programming in Scala")
      authors = List(
        Person(FirstName("Michael"), LastName("Pilquist")),
        Person(FirstName("Rúnar"), LastName("Bjarnason")),
        Person(FirstName("Paul"), LastName("Chiusano"))
      )
      publishingHouse <- PublishingHouse.from("Manning Publications")
      edition <- Edition.from("Second Edition")
      publishYear <- PublishYear.from(2022)
      language <- Language.from("English")
    yield Book(title, authors, publishingHouse, edition, publishYear, language, None, None, isbn)
