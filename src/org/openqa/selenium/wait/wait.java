package org.openqa.selenium.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class wait{
    
    public static WebElement element(WebDriver driver, By by){
        return element(driver,by,10);
    }
    public static WebElement element(WebDriver driver, By by, long max_wait){
        return element(driver,by,max_wait, 0);
    }
    public static WebElement element(WebDriver driver, By by, long max_wait, boolean refresh){
        return element(driver, by, max_wait, refresh,0);
    }
    private static WebElement element(WebDriver driver, By by, long max_wait, boolean refresh, long c){
        c++;
        if(refresh == true){
            WebElement ele = element(driver, by);
            if(ele == null){
                if(c <= max_wait){
                    driver.navigate().refresh();
                    java(2);
                    return element(driver, by, max_wait, true,c);
                }else{
                    return null;
                }
            }else{
                return ele;
            }
        }else{
            return element(driver, by, max_wait);
        }
    }
    private static WebElement element(WebDriver driver, By by, long max_wait, long c){
        java(1);
        c++;
        try{
            WebElement e = driver.findElement(by);
            if(e != null){
                return e;
            }else{
                if(c <= max_wait){
                    return element(driver,by,max_wait,c);
                }else{
                    return null;
                }
            }
        }catch(Exception e){
            if(c <= max_wait){
                return element(driver,by,max_wait,c);
            }else{
                return null;
            }
        }
    }
    public static void java(long Seconds){
        try {Thread.sleep(1000 * Seconds);}catch(Exception e){}
    }
    
}
