package io.github.rpiotrow.scalalove2022

import org.scalatest.funsuite.AnyFunSuite

class IsbnValidatorSpec extends AnyFunSuite:

  // ISBN with 10 digits

  test("ISBN 0306406152 is valid") {
    assert(IsbnValidator.validate("0306406152"))
  }

  test("ISBN 0306406153 is invalid") {
    assert(!IsbnValidator.validate("0306406153"))
  }

  // ISBN with 13 digits

  test("ISBN 9783161484100 is valid") {
    assert(IsbnValidator.validate("9783161484100"))
  }

  test("ISBN 9783161484101 is invalid") {
    assert(!IsbnValidator.validate("9783161484101"))
  }

  test("ISBN 9788377998601 is valid") {
    assert(IsbnValidator.validate("9788377998601"))
  }
