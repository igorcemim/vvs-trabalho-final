package br.com.gerenciadorproposta.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ClienteTest extends BaseFunctionalTest {

    private final String SELETOR_BOTAO_ADICIONAR = "//button[contains(.,'Adicionar')]";

    @Test
    public void testarManutencaoClientes() {
        String SELETOR_BOTAO_SALVAR = "//button[contains(.,'Salvar')]";
        String SELECTOR_LABEL_ID = "//h4[contains(.,'ID')]";

        driver.get(baseUrl() + "/clientes");

        wait.until(visibilityOfElementLocated(By.xpath(SELETOR_BOTAO_ADICIONAR)));

        driver.findElement(By.xpath(SELETOR_BOTAO_ADICIONAR)).click();

        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(.,'Adicionar - Cliente')]")));

        driver.findElement(By.id("cnpj")).sendKeys("90600089000110");
        driver.findElement(By.id("email")).sendKeys("teste@teste.com.br");
        driver.findElement(By.id("razaoSocial")).sendKeys("Exemplo");
        driver.findElement(By.id("telefone")).sendKeys("51900002222");
        driver.findElement(By.xpath(SELETOR_BOTAO_SALVAR)).click();

        waitAndAcceptAlert();

        wait.until(visibilityOfElementLocated(By.xpath(SELECTOR_LABEL_ID)));

        List<WebElement> labelComId = driver.findElements(By.xpath(SELECTOR_LABEL_ID));

        Assertions.assertEquals(1, labelComId.size());
    }

    @Test
    public void testarRemocaoClientes() {
        String SELETOR_REGISTRO = "//td[contains(text(),'10002')]/ancestor::tr";
        String SELETOR_BOTAO_FILHO_REMOVER = ".//button[contains(text(),'Remover')]";

        driver.get(baseUrl() + "/clientes");

        wait.until(visibilityOfElementLocated(By.xpath(SELETOR_BOTAO_ADICIONAR)));

        WebElement element = driver.findElement(By.xpath(SELETOR_REGISTRO));
        element.findElement(By.xpath(SELETOR_BOTAO_FILHO_REMOVER)).click();

        waitAndAcceptAlert();

        wait.until(not(visibilityOfElementLocated(By.xpath(SELETOR_REGISTRO))));

        List<WebElement> elements = driver.findElements(By.xpath(SELETOR_REGISTRO));
        Assertions.assertEquals(Boolean.TRUE, elements.isEmpty());
    }

}
