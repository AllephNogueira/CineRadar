package com.allephnogueira.cineradar.ViewModel

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class FormatarData {

    /**
     * Vamos formatar a data e caso ela não exista vamos retornar uma mensagem.
     */
    companion object {
        fun formatarData(localDate: String): String {
            /** Definindo o formato de entrada */
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")

            /** Definindo o formato de saída */
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            return try {
                val date: Date = inputFormat.parse(localDate) ?: Date() /** Converte a string para Date */
                outputFormat.format(date) /** Formata a data no padrão desejado */
            } catch (e: Exception) {
                e.printStackTrace()
                "Data não encontrada"
            }
        }
    }
}
