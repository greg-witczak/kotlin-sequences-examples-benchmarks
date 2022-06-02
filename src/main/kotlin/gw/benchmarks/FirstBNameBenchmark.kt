package gw.benchmarks

import gw.utils.NamesService
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class FirstBNameBenchmark {
    private var names: List<String> = emptyList()

    @Setup
    fun setUp() {
        names = NamesService.readChildNames().map { it.name }
    }

    @Benchmark
    fun nonSequence(): String {
        return names
            .filter { it.startsWith("B") }
            .first()
    }

    @Benchmark
    fun sequence(): String {
        return names.asSequence()
            .filter { it.startsWith("B") }
            .first()
    }

    @Benchmark
    fun streams(): String {
        return names.stream()
            .filter { it.startsWith("B") }
            .findFirst().get()
    }

}
