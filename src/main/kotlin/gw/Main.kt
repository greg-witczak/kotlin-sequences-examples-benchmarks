import gw.java.utils.JavaCode
import gw.utils.NamesService

fun main() {
    println(JavaCode.countLongNamesByGender(NamesService.readChildNames()))
}
