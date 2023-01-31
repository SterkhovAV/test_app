package ru.sterkhovav.test_app.utils

const val saveOrderRequestCorrect = "\"ACCEPTED\""
const val saveOrderRequestIncorrectValue =
    "{\"timestamp\":\"2023-01-31T07:28:20.881+0000\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Error in line 5. Not a number: 1ds\",\"path\":\"/purchase-info/new-order\"}"
const val saveOrderRequestIncorrectTag = "{\"timestamp\":\"2023-01-31T07:29:55.781+0000\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Error in line 4. cvc-complex-type.2.4.a: Invalid content was found starting with element 'la1stName'. One of '{lastName}' is expected.\",\"path\":\"/purchase-info/new-order\"}"
const val saveOrderRequestIncorrectValueRange = "{\"timestamp\":\"2023-01-31T07:31:34.887+0000\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Error in line 6. cvc-maxExclusive-valid: Value '21' is not facet-valid with respect to maxExclusive '6' for type 'items'.\",\"path\":\"/purchase-info/new-order\"}"