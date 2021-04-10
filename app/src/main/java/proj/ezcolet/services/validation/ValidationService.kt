package proj.ezcolet.services.validation

class ValidationService {
    companion object {
        fun getStringState(string: String, regexPattern: String = PATTERN_ANYTHING): String {
            if (string.isEmpty()) {
                return EMPTY
            }
            if (!string.matches(Regex(regexPattern))) {
                return INVALID
            }
            return VALID
        }

        fun hasCourierUsername(username: String): Boolean {
            return username.startsWith("curier_")
        }
    }
}