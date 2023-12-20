import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.security.rsa.RSAUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AutomationPracticeRegistrationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void dragAndDropExample() {
        driver.get("https://demo.guru99.com/test/drag_drop.html");
        Actions actions = new Actions(driver);
        actions
                .dragAndDrop(driver.findElement(By.xpath("//a[contains(text(),'BANK')]")), driver.findElement(By.id("bank")));
        actions.build()
                .perform();

    }

    @Test
    public void slideExample() {
        driver.get("https://jqueryui.com/slider/#rangemin");

        driver.switchTo().frame(driver.findElement(By.cssSelector("[class=\"demo-frame\"]")));

        Actions actions = new Actions(driver);
        actions
                .dragAndDropBy(driver.findElement(By.cssSelector(".ui-slider-handle.ui-corner-all.ui-state-default")), 50, 0)
                .build()
                .perform();
        driver.switchTo().defaultContent();

    }

    @Test
    public void hoverExample() {
        driver.get("https://www.tehnomanija.rs/");

        Actions actions = new Actions(driver);
        actions
                // .moveToElement(driver.findElement(By.cssSelector("#ammenu-header-container > div.headerWrapper > div > div > div > div > ul.header_custom_links > li:nth-child(2) > a")))
                // .moveToElement(driver.findElement(By.xpath("//*[@id=\"ammenu-header-container\"]/div[1]/div/div/div/div/ul[1]/li[2]/ul/div[1]/ul[1]/li[4]/a")))
                .moveToElement(driver.findElement(By.xpath("//a[text()='Bela tehnika']")))
                .moveToElement(driver.findElement(By.xpath("//a[text()='Kombinovani frižideri']")))
                .click()
                .build()
                .perform();
        ;

    }


    @Test
    public void buyProduct() {

        //Dodati description assert (uz ime i cenu da pokupimo description)

        driver.get("https://www.saucedemo.com/v1/");

        String item1 = "Sauce Labs Bolt T-Shirt";
        String item2 = "Sauce Labs Bike Light";

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        //  driver.findElements(By.cssSelector(".btn_primary.btn_inventory")).get(2).click();
        driver.findElement(By.xpath("//div[text()='" + item1 + "']/../../../div[3]/button")).click();
        driver.findElement(By.xpath("//div[text()='" + item2 + "']/../../../div[3]/button")).click();

        String item1Price = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../../../div[3]/div")).getText();
        String item2Price = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../../../div[3]/div")).getText();
        String item1Name = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
        String item2Name = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();

        String item1Des = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_desc']")).getText();
        String item2Des = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_desc']")).getText();

        driver.findElement(By.cssSelector(".shopping_cart_link.fa-layers.fa-fw")).click();

        String item1CartPrice = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_price']")).getText();
        String item2CartPrice = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_price']")).getText();
        String item1CartName = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
        String item2CartName = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();

        String item1CartDesc = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_desc']")).getText();
        String item2CartDesc = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_desc']")).getText();

        Assert.assertEquals(item1Price.replace("$", ""), item1CartPrice);
        Assert.assertEquals(item2Price.replace("$", ""), item2CartPrice);
        Assert.assertEquals(item1Name.replace("$", ""), item1CartName);
        Assert.assertEquals(item2Name.replace("$", ""), item2CartName);

        Assert.assertEquals(item1Des, item1CartDesc);
        Assert.assertEquals(item2Des, item2CartDesc);

//        Assert.assertEquals(item1Price,item1CartPrice);
//        Assert.assertEquals(item2Price,item2CartPrice);
//        Assert.assertEquals(item1Name,item1CartName);
//        Assert.assertEquals(item2Name,item2CartName);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cart_list']/div[3]//div[@class='inventory_item_name']")).getText(), item1);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cart_list']/div[4]//div[@class='inventory_item_name']")).getText(), item2);

        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        driver.findElement(By.id("first-name")).sendKeys("Name");
        driver.findElement(By.id("last-name")).sendKeys("Last");
        driver.findElement(By.id("postal-code")).sendKeys("12547");

        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();

        String item1CartPriceCheckout = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_price']")).getText();
        String item2CartPriceCheckout = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_price']")).getText();
        String item1CartNameCheckout = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
        String item2CartNameCheckout = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();

        Assert.assertEquals(item1Price, item1CartPriceCheckout);
        Assert.assertEquals(item2Price, item2CartPriceCheckout);
        Assert.assertEquals(item1Name, item1CartNameCheckout);
        Assert.assertEquals(item2Name, item2CartNameCheckout);

        String itemTotalPrice = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText().replace("Item total: $", "");
        double itemTotalPriceCalc = Double.parseDouble(item1Price.replace("$", "")) + Double.parseDouble(item2Price.replace("$", ""));


        Assert.assertEquals(Double.parseDouble(itemTotalPrice), itemTotalPriceCalc);

        driver.findElement(By.cssSelector(".btn_action.cart_button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-complete.html");

    }


    @Test
    public void sauceDemLogin() {
        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".product_label")).getText(), "Products");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        //Dodati testove za druge usere (za zakljucan, za pogresan username i password)
    }


    @Test
    public void sauceDemWrongPasswordLogin() {
        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.cssSelector("#login-button")).click();

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");


        //Dodati test za pogresan password)
    }



    @Test
    public void sauceDemLockedLogin() {
        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

    //    String errorMessage = driver.findElement(By.cssSelector("#login_button_container > div > form > h3")).getText();

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
//        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out");

        //Dodati test za zakljucanog usera
    }




   @Test
    public void registrationTest() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php");
        driver.findElement(By.cssSelector("[title='Log in to your customer account']")).click();
        WebElement email = driver.findElement(By.xpath("//*[@id='email_create']"));
        email.sendKeys("testing@fjls.com");
        driver.findElement(By.cssSelector("#SubmitCreate")).click();
        driver.findElement(By.cssSelector("#id_gender1")).click();
        driver.findElement(By.xpath("//*[@id='customer_firstname']")).sendKeys("Joseph");
        driver.findElement(By.xpath("//*[@id='customer_lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//*[@id='email']")).clear();
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com");
        driver.findElement(By.cssSelector("#passwd")).sendKeys("pass124");
        Select select = new Select(driver.findElement(By.id("days")));
        select.selectByIndex(16);
        Select selectMonth = new Select(driver.findElement(By.id("months")));
        selectMonth.selectByIndex(12);
        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectYear.selectByValue("1956");
        //selectYear.selectByIndex(2000);
        driver.findElement(By.cssSelector("#newsletter")).click();
        driver.findElement(By.cssSelector("#submitAccount")).click();


       Assert.assertEquals(driver.getCurrentUrl(),"http://www.automationpractice.pl/index.php?controller=my-account");
       Assert.assertEquals(driver.getTitle(),"My account - My Shop");


        driver.findElement(By.cssSelector("[title='Add my first address']")).click();
        driver.findElement(By.cssSelector("#company")).sendKeys("QAcompany");
        driver.findElement(By.cssSelector("#address1")).sendKeys("943 Forest Dr, Arnold, MD 21012");
        driver.findElement(By.cssSelector("#city")).sendKeys("Arnold");
        driver.findElement(By.cssSelector("#id_state")).sendKeys("Maryland");
        //Select state = new Select(driver.findElement(By.id("id_state")));
        //state.selectByValue("Maryland");
        driver.findElement(By.cssSelector("#postcode")).sendKeys("21012");
        driver.findElement(By.cssSelector("#phone")).click();
        driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("124578888777");
        driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("441111147455");
        driver.findElement(By.xpath("//*[@id='other']")).sendKeys("Thank you!");
        driver.findElement(By.xpath("//*[@id='alias']")).sendKeys(" Test");
        driver.findElement(By.cssSelector("#submitAddress")).click();
        driver.getCurrentUrl();
        driver.getTitle();

       Assert.assertEquals(driver.getTitle(),"Addresses - My Shop");
        Thread.sleep(5000);

        //Asertacije (proveriti title, URL)
    }
}

