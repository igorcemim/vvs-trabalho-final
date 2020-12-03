package br.com.gerenciadorproposta.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PropostaTest extends BaseFunctionalTest {

    @Test
    public void cadastroProposta() {
        driver.get(baseUrl());

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Adicionar')]")));

        driver.findElement(By.linkText("Propostas")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Adicionar')]")));

        driver.findElement(By.xpath("//button[contains(.,'Adicionar')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Adicionar - Proposta')]")));

        ((JavascriptExecutor) driver).executeScript("window.alert = function() {}");

        driver.findElement(By.id("descricao")).sendKeys("Exemplo de proposta");
        driver.findElement(By.id("data")).sendKeys("01012020");
        driver.findElement(By.id("valor")).sendKeys("10000");

        Select statusSelect = new Select(driver.findElement(By.id("status")));
        statusSelect.selectByVisibleText("Aprovada");

        Select clienteSelect = new Select(driver.findElement(By.id("cliente")));
        clienteSelect.selectByVisibleText("Exemplo2");

        driver.findElement(By.xpath("//button[contains(.,'Salvar')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'ID')]")));

        List<WebElement> mostradorId = driver.findElements(By.xpath("//h4[contains(.,'ID')]"));

        Assertions.assertTrue(mostradorId.size() == 1);
    }

}
