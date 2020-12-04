package br.com.gerenciadorproposta.resource;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteResourceTest extends BaseIntegrationTest {

    @Test
    public void listagemClientesDeveRetornarNoFormatoEsperado() throws Exception {
        this.mockMvc.perform(get("/api/clientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(10001)))
                .andExpect(jsonPath("$[0].cnpj", is("17778490000154")))
                .andExpect(jsonPath("$[0].razaoSocial", is("Exemplo2")))
                .andExpect(jsonPath("$[0].telefone", is("51977885566")))
                .andExpect(jsonPath("$[0].email", is("teste2@teste2.com.br")));
    }

}
