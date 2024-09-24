package org.example

import org.example.repository.CotizacionRepository
import java.nio.file.Path
import kotlin.io.path.Path

/*
Nombre (nombre de la empresa), Final (precio de la acción al cierre de bolsa), Máximo (precio máximo de la acción durante la jornada), Mínimo (precio mínimo de la acción durante la jornada), Volumen (Volumen al cierre de bolsa), Efectivo (capitalización al cierre en miles de euros).
 */

fun main() {
    val cotiza = CotizacionRepository()
    val path = Path.of("src").resolve("main").resolve("ficheros")
    cotiza.ShowAll(path).forEach { println(it)  }
    cotiza.MakeSummary(path,cotiza.ShowAll(path))

}