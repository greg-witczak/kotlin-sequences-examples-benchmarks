package gw.benchmarks

import gw.java.utils.JavaCode
import gw.utils.NamesService
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class HelloWorldBenchmark {
    private var names: List<String> = emptyList()

    @Setup
    fun setUp() {
        names = NamesService.readChildNames().map { it.name }
    }

    @Benchmark
    fun nonSequence(): List<String> {
        return names
            .filter { it.startsWith("B") }
            .map { it.uppercase() }
            .toList()
    }

    @Benchmark
    fun sequence(): List<String> {
        return names
            .filter { it.startsWith("B") }
            .map { it.uppercase() }
            .toList()
    }

    @Benchmark
    fun nonFunctionalJavaCode(): List<String> {
        return JavaCode.helloWorldNonFunctionalJavaCode(names)
    }

    @Benchmark
    fun javaStreams(): List<String> {
        return JavaCode.helloWorldJavaStreams(names)
    }

}
