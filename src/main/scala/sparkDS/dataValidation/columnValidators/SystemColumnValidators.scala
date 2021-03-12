package sparkDS.dataValidation.columnValidators

import sparkDS.dataValidation.ColumnValidator

object SystemColumnValidators {
  //@formatter:off
  val boolean_validator   = ColumnValidator("boolean_validator",       "isnotnull(cast(%this_col% as boolean)",   "Value is not boolean")
  val byle_validator      = ColumnValidator("byte_validator",          "isnotnull(cast(%this_col% as byte)",      "Value is not byte")
  val date_validator      = ColumnValidator("date_validator",          "isnotnull(cast(%this_col% as date)",      "Value is not date")
  val double_validator    = ColumnValidator("double_validator",        "isnotnull(cast(%this_col% as double)",    "Value is not double")
  val int_validator       = ColumnValidator("int_validator",           "isnotnull(cast(%this_col% as int)",       "Value is not int")
  val long_validator      = ColumnValidator("long_validator",          "isnotnull(cast(%this_col% as long)",      "Value is not long")
  val short_validator     = ColumnValidator("short_validator",         "isnotnull(cast(%this_col% as short)",     "Value is not short")
  val timestamp_validator = ColumnValidator("timestamp_validator",     "isnotnull(cast(%this_col% as timestamp)", "Value is not timestamp")

  // Based on https://pe.usps.com/text/pub28/28apb.htm
  val us_state_validation = ColumnValidator("us_state_validation",     "isnotnull(cast(%this_col% as timestamp)", "Value is not timestamp")
  //@formatter:on
}
