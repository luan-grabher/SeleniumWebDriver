/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openqa.selenium.chromeclass;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import main.Utilitarios;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumChrome {

    private String localDriver;
    private String htmlPage;
    private WebDriver chrome;
    private WebElement el;

    /**
     * Inicializa o Chromedriver.
     *
     * @param local
     */
    public SeleniumChrome(String local) {
        localDriver = local;
        constructor();
    }

    public SeleniumChrome() {
        try {
            WebDriverManager.chromedriver().setup();
            localDriver = WebDriverManager.chromedriver().getBinaryPath();
            constructor();
        } catch (Exception e) {
        }

    }

    public void constructor() {
        try {
            System.setProperty("webdriver.chrome.driver", localDriver);
        } catch (Exception e) {
            System.out.println("Oocrreu um erro: " + e);
        }
    }

    /**
     * Abre o Google Chrome na página passada por parâmetro.
     *
     * @param url Página a ser aberta.
     * @return Retorna se foi possível abrir o driver.
     */
    public boolean abrirChrome(String url) { // abre o chrome em uma página desejada
        try {
            chrome = new ChromeDriver();
            chrome.get(url);
            return true;
        } catch (Exception er) {
            System.out.println("Ocorreu um erro: " + er);
            return false;
        }
    } // fim de abrirChrome

    /**
     * Verifica se o chromedriver.exe está aberto e então o fecha.
     *
     * @return Retorna se foi possível fecha-lo.
     */
    @SuppressWarnings("UseSpecificCatch")
    public boolean fecharChrome() { // verifica se chrome está realmente aberto e fecha-o
        try {
            if (Utilitarios.processoRodando("chromedriver.exe")) {
                chrome.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e);
        }
        return false;
    } // fim do fechar chrome

    /**
     * Retorna o driver para utilização.
     *
     * @return Retorna o driver.
     */
    public WebDriver getChrome() {
        return this.chrome;
    } // fim do driver

    /**
     * Define um novo local para o driver.
     *
     * @param novoLocal
     */
    public void setLocalDriver(String novoLocal) {
        try {
            localDriver = novoLocal;
            System.setProperty("webdriver.chrome.driver", localDriver);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e);
        }
    } // fim do setLocalDriver

    /**
     * Faz a impressão da página aberta pelo selenium.
     *
     * @return Retorna verdadeiro se tiver conseguido imprimir.
     */
    public boolean imprimiPagina() {
        boolean b = false;
        try {
            Robot r = new Robot();

            try {
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_P);
                r.keyRelease(KeyEvent.VK_P);
                r.keyRelease(KeyEvent.VK_CONTROL);

                r.delay(1000);

                r.keyPress(KeyEvent.VK_ENTER);
                r.keyRelease(KeyEvent.VK_ENTER);

                b = true;
            } catch (Exception e) {
                System.out.println("Erro ao pressionar teclas para impressão: " + e);
            }
        } catch (Exception e) {
            System.out.println("Erro ao printar página!");
        }
        return b;
    } // fim do imprimiPagina

    /**
     * Salva a página aberta pelo selenium como html.
     *
     * @return Retorna verdadeiro se tiver conseguido salvar.
     */
    public boolean salvaPagina() {
        boolean b = false;
        try {
            Robot r = new Robot();

            try {
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_S);
                r.keyRelease(KeyEvent.VK_S);
                r.keyRelease(KeyEvent.VK_CONTROL);

                r.delay(1000);

                r.keyPress(KeyEvent.VK_ENTER);
                r.keyRelease(KeyEvent.VK_ENTER);

                b = true;
            } catch (Exception e) {
                System.out.println("Erro ao pressionar teclas para salvar: " + e);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar página!");
        }
        return b;
    } // fim do salvaPagina

    /**
     * Pega o conteúdo HTML da página aberta pelo selenium.
     *
     * @return Retorna uma String contendo o conteúdo do HTML da página Caso
     * ocorra algum erro ela retorna um erro.
     */
    public String getHtmlPagina() {
        try {
            el = chrome.findElement(By.cssSelector("HTML"));
            // pega atributo do HTML
            String textoHtml = el.getAttribute("innerHTML");
            // retorna string contendo o conteúdo do HTML
            htmlPage = "<html>" + textoHtml + "</html>";
            System.out.println("HTML pego com sucesso!");
        } catch (Exception e) {
            htmlPage = "Erro ao pegar HTML da página!";
            System.out.println("Erro ao pegar HTML da página!");
        }
        return htmlPage;
    } // fim do getHtmlPagina
} // fim da classe SeleniumChrome
