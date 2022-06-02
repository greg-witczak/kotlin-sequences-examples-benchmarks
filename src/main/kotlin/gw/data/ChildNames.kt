package gw.data

data class ChildName(
    val name: String,
    val gender: Gender
)

enum class Gender {
    BOY,
    GIRL
}
