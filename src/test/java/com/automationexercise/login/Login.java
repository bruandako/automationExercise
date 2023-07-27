package com.automationexercise.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testes automatizados do site Automation Exercise")
public class Login {


    @Test
    @Order(1)
    @DisplayName("Criar uma conta no site automationexercise")
    public void criarUmaContaNoSiteAutomationexercise() {
        //Abrir o navegador e maximizar a tela
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Abrir o site automationexercise
        navegador.get("https://automationexercise.com/");

        //Clicar em [signup/login] e validar que o título 'new user signup' é exibido
        navegador.findElement(By.xpath("//a[@href='/login']")).click();
        String newUser = String.valueOf(navegador.findElement((By.xpath("//h2[contains(.,'New User Signup!')]"))));

        //Preencher os campos nome e e-mail
        navegador.findElement(By.xpath("//input[@name='name']")).sendKeys("Clara Mendes");
        navegador.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("clara.mendes@teste.com.br");

        //Clicar no botão Signup
        navegador.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        //Preencher as informações para a conta
        navegador.findElement(By.xpath("//input[@id='id_gender2']")).click();
        navegador.findElement(By.xpath("//input[@id='password']")).sendKeys("Senha1!");
        navegador.findElement(By.xpath("//select[@id='days'] /option[@value='3']")).click();
        navegador.findElement(By.xpath("//select[@id='months']/option[@value='10']")).click();
        navegador.findElement(By.xpath("//select[@id='years']/option[@value='1990']")).click();

        //Preencher as informações do endereço
        navegador.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Clara");
        navegador.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Mendes");
        navegador.findElement(By.xpath("//input[@id='address1']")).sendKeys("Rua Itapiru, 357");
        navegador.findElement(By.xpath("//select[@id='country']/option[@value='Canada']")).click();
        navegador.findElement(By.xpath("//input[@id='state']")).sendKeys("São Paulo");
        navegador.findElement(By.xpath("//input[@id='city']")).sendKeys("São Paulo");
        navegador.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("04143-000");
        navegador.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("11987458877");

        //Clicar no botão Create Account
        navegador.findElement(By.xpath("//button[@data-qa='create-account']")).click();

        //Validar a mensagem de sucesso e fechar o navegador
        String msgSucesso = navegador.findElement(By.xpath("//div[@class='col-sm-9 col-sm-offset-1']")).getText();
        String removeNomeDoBotao = msgSucesso.replace("Continue", "");
        Assertions.assertEquals("ACCOUNT CREATED!\n" +
                "Congratulations! Your new account has been successfully created!\n" +
                "You can now take advantage of member privileges to enhance your online shopping experience with us.\n", removeNomeDoBotao);
        navegador.quit();
    }

    @Test
    @Order(2)
    @DisplayName("Realizar o login no site automationexercise")
    public void realizarOLoginNoSiteAutomationexercise() {
        //Abrir o navegador e maximizar a tela
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Abrir o site automationexercise
        navegador.get("https://automationexercise.com/");

        //Clicar em [signup/login] e validar que o título 'new user signup' é exibido
        navegador.findElement(By.xpath("//a[@href='/login']")).click();
        String newUser = String.valueOf(navegador.findElement((By.xpath("//h2[contains(.,'Login to your account')]"))));

        //Informe e-mail e senha
        navegador.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("clara.mendes@teste.com.br");
        navegador.findElement(By.xpath("//input[@name='password']")).sendKeys("Senha1!");

        //Clicar no botão Login
        navegador.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        //Verificar se a mensagem 'Logged in as' é exibida
        String msgUsuarioLogado = navegador.findElement(By.xpath("//a[contains(.,'Logged in as Clara Mendes')]")).getText();
        Assertions.assertEquals("Logged in as Clara Mendes", msgUsuarioLogado);
        navegador.quit();
    }

    @Test
    @Order(3)
    @DisplayName("Realizar a exclusão da conta no site automationexercise")
    public void realizarAExclusaoDaContaNoSiteAutomationExercise() {
        //Abrir o navegador e maximizar a tela
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Abrir o site automationexercise
        navegador.get("https://automationexercise.com/");

        //Clicar em [signup/login] e validar que o título 'new user signup' é exibido
        navegador.findElement(By.xpath("//a[@href='/login']")).click();
        String newUser = String.valueOf(navegador.findElement((By.xpath("//h2[contains(.,'Login to your account')]"))));

        //Informe e-mail e senha
        navegador.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("clara.mendes@teste.com.br");
        navegador.findElement(By.xpath("//input[@name='password']")).sendKeys("Senha1!");

        //Clicar no botão Login
        navegador.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        //Clicar no botão Delete Account
        navegador.findElement(By.xpath("//a[@href='/delete_account']")).click();
        navegador.switchTo().activeElement().sendKeys(Keys.ENTER);

        //Validar a mensagem de confirmação
        String msgConfirmaExclusao = navegador.findElement(By.xpath("//div[@class='col-sm-9 col-sm-offset-1']")).getText();
        String removeNomeDoBotao = msgConfirmaExclusao.replace("Continue", "");
        Assertions.assertEquals("ACCOUNT DELETED!\n" +
                "Your account has been permanently deleted!\n" +
                "You can create new account to take advantage of member privileges to enhance your online shopping experience with us.\n", removeNomeDoBotao);
        navegador.quit();
    }

}
