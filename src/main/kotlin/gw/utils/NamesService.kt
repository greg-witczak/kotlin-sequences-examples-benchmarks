package gw.utils

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.KotlinModule
import gw.data.ChildName

object NamesService {
    private val csvMapper = CsvMapper().apply {
        registerModule(KotlinModule())
        enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
    }

    private inline fun <reified T> readCsvFile(fileName: String): List<T> {
        this::class.java.classLoader.getResourceAsStream(fileName).use { resourceStream ->
            return csvMapper
                .readerFor(T::class.java)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(resourceStream)
                .readAll()
                .toList()
        }
    }

    fun readChildNames() =
        readCsvFile<ChildName>("child-names.csv")
}

