package io.github.rpiotrow.scalalove2022.primitives

case class Book(
  title: String,
  authors: List[Person],
  publishingHouse: String,
  edition: String,
  publishYear: Int,
  language: String,
  originalLanguage: Option[String],
  translators: Option[List[Person]],
  isbn: String
)

object BookExamples:

  val redBook: Book = Book(
    "Functional Programming in Scala",
    List(
      Person("Michael", "Pilquist"),
      Person("Rúnar", "Bjarnason"),
      Person("Paul", "Chiusano")
    ),
    "Manning Publications",
    "Second Edition",
    2022,
    "English",
    None,
    None,
    "9781617299582"
  )
