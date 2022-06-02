package gw.benchmarks

import gw.data.ChildName
import gw.data.Gender
import gw.java.utils.JavaCode
import gw.utils.NamesService
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.*

@State(Scope.Benchmark)
class SortedLongNamesByGenderBenchmark {
    private var names: List<ChildName> = emptyList()
    private var namesMutable: MutableList<ChildName> = mutableListOf()

    @Setup
    fun setUp() {
        names = NamesService.readChildNames()
        namesMutable = names.toMutableList()
    }

    @Benchmark
    fun java(): Map<Gender, SortedSet<String>>? {
        return JavaCode.sortedLongNamesByGender(namesMutable)
    }

    @Benchmark
    fun kotlinSequence(): Map<Gender, SortedSet<String>> {
        return names.asSequence() // Sequence<ChildName>
            .filter { it.name.length >= 5 } // Sequence<ChildName>
            .groupBy({ it.gender }, { it.name }) // Map<Gender, List<String>>
            .mapValues { it.value.toSortedSet() } // Map<Gender, SortedSet<String>>
    }

    @Benchmark
    fun kotlinNonSequence(): Map<Gender, SortedSet<String>> {
        return names
            .filter { it.name.length >= 5 }
            .groupBy({ it.gender }, { it.name }) // Map<Gender, List<String>>
            .mapValues { it.value.toSortedSet() } // Map<Gender, SortedSet<String>>
    }
}
