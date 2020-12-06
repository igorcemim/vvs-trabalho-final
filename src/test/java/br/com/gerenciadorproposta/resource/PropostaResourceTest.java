package br.com.gerenciadorproposta.resource;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PropostaResourceTest extends BaseIntegrationTest {

    @Test
    public void listagemPropostasDeveRetornarNoFormatoEsperado() throws Exception {
        this.mockMvc.perform(get("/api/propostas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(20001)))
                .andExpect(jsonPath("$[0].descricao", is("Proposta qualquer")))
                .andExpect(jsonPath("$[0].valor", is(25000.0)))
                .andExpect(jsonPath("$[0].data", is("01/03/2020")))
                .andExpect(jsonPath("$[0].status", is("APROVADA")))
                .andExpect(jsonPath("$[0].cliente.id", is(10001)))
                .andExpect(jsonPath("$[0].cliente.cnpj", is("17778490000154")))
                .andExpect(jsonPath("$[0].cliente.razaoSocial", is("Exemplo2")))
                .andExpect(jsonPath("$[0].cliente.telefone", is("51977885566")))
                .andExpect(jsonPath("$[0].cliente.email", is("teste2@teste2.com.br")));
    }

}
