package br.com.gerenciadorproposta.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PropostaTest extends BaseFunctionalTest {

    private String SELETOR_BOTAO_ADICIONAR = "//button[contains(.,'Adicionar')]";

    @Test
    public void testarManutencaoPropostas() {
        String SELETOR_BOTAO_SALVAR = "//button[contains(.,'Salvar')]";
        String SELECTOR_LABEL_ID = "//h4[contains(.,'ID')]";

        driver.get(baseUrl() + "/propostas");

        wait.until(visibilityOfElementLocated(By.xpath(SELETOR_BOTAO_ADICIONAR)));

        driver.findElement(By.xpath(SELETOR_BOTAO_ADICIONAR)).click();

        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(.,'Adicionar - Proposta')]")));

        driver.findElement(By.id("descricao")).sendKeys("Uma proposta qualquer.");
        driver.findElement(By.id("data")).sendKeys("22/01/2020");
        driver.findElement(By.id("valor")).sendKeys("10000");

        Select statusSelect = new Select(driver.findElement(By.id("status")));
        statusSelect.selectByVisibleText("Aprovada");

        Select clienteSelect = new Select(driver.findElement(By.id("cliente")));
        clienteSelect.selectByVisibleText("Microsoft");

        driver.findElement(By.xpath(SELETOR_BOTAO_SALVAR)).click();

        waitAndAcceptAlert();

        wait.until(visibilityOfElementLocated(By.xpath(SELECTOR_LABEL_ID)));

        List<WebElement> mostradorId = driver.findElements(By.xpath(SELECTOR_LABEL_ID));

        Assertions.assertEquals(1, mostradorId.size());
    }

    @Test
    public void testarRemocaoPropostas() {
        String SELETOR_REGISTRO_LINHA = "//td[contains(text(),'20001')]/ancestor::tr";
        String SELETOR_BOTAO_FILHO_REMOVER = ".//button[contains(text(),'Remover')]";

        driver.get(baseUrl() + "/propostas");

        wait.until(visibilityOfElementLocated(By.xpath(SELETOR_BOTAO_ADICIONAR)));

        WebElement registroLinha = driver.findElement(By.xpath(SELETOR_REGISTRO_LINHA));
        registroLinha.findElement(By.xpath(SELETOR_BOTAO_FILHO_REMOVER)).click();

        waitAndAcceptAlert();

        wait.until(ExpectedConditions.stalenessOf(registroLinha));

        List<WebElement> elements = driver.findElements(By.xpath(SELETOR_REGISTRO_LINHA));
        Assertions.assertEquals(Boolean.TRUE, elements.isEmpty());
    }
}
