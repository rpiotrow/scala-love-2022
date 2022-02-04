package io.github.rpiotrow.scalalove2022.kebs

import io.github.rpiotrow.scalalove2022.IsbnValidator
import pl.iterators.kebs.tagged._
import pl.iterators.kebs.tag.meta.tagged

@tagged object BookDomain {

  import PersonDomain._

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

  trait TitleTag
  type Title = String @@ TitleTag
  object Title {
    def validate(value: String): Either[String, String] =
      if (!value.isBlank)
        Right(value)
      else
        Left("empty title")
// macro adds:
//    def apply(arg: String): Title = from(arg).getOrElse(throw new IllegalArgumentException(arg))
//    def from(arg: String): Either[String, Title] = validate(arg).map(arg1 => arg1.taggedWith[TitleTag])
  }

  trait ISBNTag
  type ISBN = String @@ ISBNTag
  object ISBN {
    def validate(value: String): Either[String, String] =
      if (IsbnValidator.validate(value))
        Right(value)
      else
        Left("invalid ISBN")
  }

  private def notBlankString(value: String): Either[String, String] = {
    if (!value.isBlank)
      Right(value)
    else
      Left("empty string")
  }

  trait PublishingHouseTag
  type PublishingHouse = String @@ PublishingHouseTag
  object PublishingHouse {
    def validate(value: String): Either[String, String] =
      notBlankString(value)
  }

  trait EditionTag
  type Edition = String @@ EditionTag
  object Edition {
    def validate(value: String): Either[String, String] =
      notBlankString(value)
  }

  trait LanguageTag
  type Language = String @@ LanguageTag
  object Language {
    def validate(value: String): Either[String, String] =
      notBlankString(value)
  }

  trait PublishYearTag
  type PublishYear = Int @@ PublishYearTag
  object PublishYear {
    def validate(value: Int): Either[String, Int] =
      if (value >= 1970 && value <= 2050)
        Right(value)
      else
        Left("invalid publish year")
  }
}

object BookExamples {
  import BookDomain._
  import PersonDomain._

  val book: Either[String, Book] =
    for {
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
    } yield Book(title, authors, publishingHouse, edition, publishYear, language, None, None, isbn)
}
