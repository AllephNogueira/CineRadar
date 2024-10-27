package com.allephnogueira.cineradar.Service

import com.allephnogueira.cineradar.Model.RetrofitClient
import org.junit.Assert.assertNotNull
import org.junit.Test

class RetrofitClientTest {

    @Test
    fun `testa se RetrofitClient retorna uma instância válida de ApiService`() {
        // Obtem a instância de ApiService
        val apiService = RetrofitClient.instance

        // Verifica se a instância não é nula
        assertNotNull("A instância de ApiService não deveria ser nula", apiService)
    }
}
