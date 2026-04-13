package com.br.desafio_votacao.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("deve testar a criação do end point de pauta")
    void deve_criar_pauta() throws Exception {
        mockMvc.perform(post("/v1/pautas/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"teste\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("deve testar a busca da pauta por id")
    void deve_buscar_pauta_por_id() throws Exception {
        mockMvc.perform(get("/v1/pautas/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}