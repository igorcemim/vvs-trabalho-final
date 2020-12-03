package br.com.gerenciadorproposta.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ClienteTest extends BaseFunctionalTest {

    @Test
    public void cadastroCliente() {
        driver.get(baseUrl());

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Adicionar')]")));

        driver.findElement(By.xpath("//button[contains(.,'Adicionar')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Adicionar - Cliente')]")));
        List<WebElement> elements = driver.findElements(By.xpath("//h1[contains(.,'Adicionar - Cliente')]"));

        ((JavascriptExecutor) driver).executeScript("window.alert = function() {}");

        driver.findElement(By.id("cnpj")).sendKeys("90600089000110");
        driver.findElement(By.id("email")).sendKeys("teste@teste.com.br");
        driver.findElement(By.id("razaoSocial")).sendKeys("Exemplo");
        driver.findElement(By.id("telefone")).sendKeys("51900002222");
        driver.findElement(By.xpath("//button[contains(.,'Salvar')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'ID')]")));

        List<WebElement> mostradorId = driver.findElements(By.xpath("//h4[contains(.,'ID')]"));

        Assertions.assertTrue(mostradorId.size() == 1);
    }

}
