package org.example.repository

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.createFile
import kotlin.io.path.notExists

class CotizacionRepository {

    fun ShowAll(paht:Path): MutableMap<Int,List<String>> {
        val pahtFinal=paht.resolve("cotizacion.csv")
        val br:BufferedReader = Files.newBufferedReader(pahtFinal)
        br.readLine()

        val informacionUnitaria = mutableMapOf<Int,List<String>>()
        var numeros = 0

        if (pahtFinal.notExists()){
            pahtFinal.createFile()
        }

        br.use {
            it.forEachLine { line ->
                val allSpliteado = line.split(";")
                informacionUnitaria[numeros]= allSpliteado
                numeros++
            }
        }
        return informacionUnitaria

    }

    fun MakeSummary(paht: Path,map: MutableMap< Int,List<String>>) {

        val pahtFinal = paht.resolve("cotizacionFinal.csv")
        if (pahtFinal.notExists()) {
            pahtFinal.createFile()
        }

        var numero = 0
        val bw = Files.newBufferedWriter(pahtFinal)

        bw.use {
                for (i in map) {
                    val max = map.get(numero)?.get(2)?.replace(".", "")?.replace(",", ".")?.toDouble() ?: 0.0
                    val min = map.get(numero)?.get(3)?.replace(".", "")?.replace(",", ".")?.toDouble() ?: 0.0
                    val media = ((min + max) / 2).toString().format("%.2f").replace(",",".")
                    it.write(
                        "Name : ${
                            map.get(numero)?.get(0)
                        }  |  maximus : ${max}  |  minimum : ${min} |  average : ${media}\n"
                    )

                    numero++
                }

        }
    }
}