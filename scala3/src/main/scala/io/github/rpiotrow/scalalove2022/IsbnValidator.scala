package io.github.rpiotrow.scalalove2022

object IsbnValidator:
  def validate(value: String): Boolean =
    if value.length == 10 then validateIsbn10(value)
    else if value.length == 13 then validateIsbn13(value)
    else false

  private def validateIsbn10(value: String): Boolean =
    val significantPart = value.take(9)
    val controlChar = value.last
    if significantPart.forall(_.isDigit) then
      val sum = significantPart
        .map(_.asDigit)
        .zipWithIndex
        .map { case (number, index) =>
          number*(index+1)
        }
        .sum
      sum % 11 match
        case 10 => controlChar == 'X'
        case s  => controlChar == s + 48
    else false

  private def validateIsbn13(value: String): Boolean =
    val significantPart = value.take(12)
    val controlChar = value.last
    if significantPart.forall(_.isDigit) then
      val sum = significantPart
        .map(_.asDigit)
        .zipWithIndex
        .map { case (number, index) =>
          val weight = if index % 2 == 0 then 1 else 3
          number * weight
        }
        .sum
      (10 - sum % 10) % 10 == controlChar.asDigit
    else false
