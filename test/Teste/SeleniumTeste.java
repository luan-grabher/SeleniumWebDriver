
package Teste;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumTeste
{
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        System.out.println(WebDriverManager.chromedriver().getBinaryPath());
    }
    
}
