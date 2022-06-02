package gw.benchmarks

import gw.data.ChildName
import gw.data.Gender
import gw.java.utils.JavaCode
import gw.utils.NamesService
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class CountLongNamesByGenderBenchmark {
    private var names: List<ChildName> = emptyList()

    @Setup
    fun setUp() {
        names = NamesService.readChildNames()
    }

    @Benchmark
    fun java(): Map<Gender, Long> {
        return JavaCode.countLongNamesByGender(names)
    }

    @Benchmark
    fun kotlinSequence(): Map<Gender, Int> {
        return names.asSequence()
            .filter { it.name.length >= 5 }
            .groupingBy { it.gender }
            .eachCount()
    }

    @Benchmark
    fun kotlinNonSequence(): Map<Gender, Int> {
        return names
            .filter { it.name.length >= 5 }
            .groupingBy { it.gender }
            .eachCount()
    }
}
