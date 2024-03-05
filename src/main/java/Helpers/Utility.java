package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utility {
    static Properties prop;
    static FileInputStream in;
    static FileOutputStream out;
    static FileReader reader;

    public static String getValue(String key) throws IOException {
        prop = new Properties();
        reader = new FileReader("data.properties");
        prop.load(reader);
        String value = prop.getProperty(key);
        reader.close();
        return value;
    }

    public static String getValue(String fileName, String key) throws IOException {
        prop = new Properties();
        reader = new FileReader("src//test//java//Config//" + fileName + ".properties");
        prop.load(reader);
        String value = prop.getProperty(key);
        reader.close();
        return value;
    }

    public static void setValue(String key, String value) throws IOException {
        in = new FileInputStream("data.properties");
        prop = new Properties();
        prop.load(in);
        in.close();

        out = new FileOutputStream("data.properties");
        prop.setProperty(key, value);
        prop.store(out, null);
        out.close();

    }

    public static String getXpath(WebElement self, WebElement ancestor) {
        int a = ancestor.findElements(By.xpath("./ancestor::*")).size();
        int s = self.findElements(By.xpath("./ancestor::*")).size();
        String path = "";
        WebElement current = self;
        for (int i = s - a; i > 0; i--) {
            String tag = current.getTagName();
            int lvl = current.findElements(By.xpath("./preceding-sibling::" + tag)).size() + 1;
            path = String.format("/%s[%d]%s", tag, lvl, path);
            current = current.findElement(By.xpath("./parent::*"));
        }
        return path;
    }
}