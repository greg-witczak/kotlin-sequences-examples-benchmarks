# Presentation

This is a repository with examples and benchmarks covered in [Kotlin Sequences presentation](https://slides.com/grzesiekwitczak/kotlin-sequences/) for Tricity Kotlin User Group [meeting](https://www.meetup.com/tricity-kotlin-user-group/events/285689933/).

## Building and running benchmark

```
./gradlew benchmark
```

Results will be visible in gradle build log and in `build/reports/benchmarks/main` directory.

## Adjustments

You can adjust number of iterations (and therefore time of benchmark execution) by editing `build.gradle.kts`:

```
warmups = 5 // number of warmup iterations
iterations = 10 // number of iterations
iterationTime = 3 // time in seconds per iteration
```
