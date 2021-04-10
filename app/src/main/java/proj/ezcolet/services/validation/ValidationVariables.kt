package proj.ezcolet.services.validation

const val PATTERN_ANYTHING = ".*"
const val PATTERN_START_UPPERCASE_MIN_3 = "^[A-Z][-a-zA-Z]{2,}$"
const val PATTERN_LENGTH_6 = "^[0-9]{6}$"
const val PATTERN_START_0_LENGTH_10 = "^0[0-9]{9}$"

// username is 8-20 characters long;
// no _ or . at the beginning;
// no __ or _. or ._ or .. inside;
// no _ or . at the end
const val PATTERN_USERNAME = "^(?=[a-zA-Z0-9._]{8,20}\$)(?!.*[_.]{2})[^_.].*[^_.]$"

// password is min 8 characters;
// at least 1 uppercase;
// at least 1 lowercase;
// at least 1 number
const val PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"

const val EMPTY = "empty"
const val INVALID = "invalid"
const val VALID = "valid"

const val EMPTY_ERROR_MESSAGE = "Camp obligatoriu"
const val INVALID_ERROR_MESSAGE = "Format invalid"
