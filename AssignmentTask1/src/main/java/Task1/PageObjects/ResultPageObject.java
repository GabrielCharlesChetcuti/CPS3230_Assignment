package Task1.PageObjects;

import Task1.Interfaces.*;
import Task1.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ResultPageObject {

    WebDriver Driver;
    amntTimesOpened amntTimesOpened;
    int counter = 0;
    CheckPageIsLoaded loaded;
    CheckForPostRequest request;
    CheckForDeleteRequest dRequest;
    amntTimesPostRequestMade amntTimesPostRequestMade;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //opens webpage
    public void OpenWebpage() {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        Driver = new ChromeDriver();

        Driver.get("https://www.scanmalta.com/shop/");
        Driver.manage().window().maximize();
    }

    //opens webpage and runs search
    public void WebDriver(String item) {

        OpenWebpage();

        WebElement searchField = Driver.findElement(By.name("q"));
        searchField.sendKeys(item);
        Driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        String title = Driver.getTitle();
        System.out.println(title);
    }

    //closes webpage afterwords
    public void teardown() {
        Driver.quit();
    }


    //returns list of all products available on the page
    public List<WebElement> returnAllProducts() {

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[5]/div[2]/ol/li"));

        return searchResults;
    }

    //gets all product info and stores it in instances of Product
    public List<Product> getAllProductInfo(List<WebElement> searchResults, int size, int alertType) {
        String heading;
        String description;
        String url;
        String imageUrl;
        String price;

        List<Product> products = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            heading = "Iphone/IPhone accessory";
            description = searchResults.get(i).findElement(By.className("product-item-link")).getText();
            url = searchResults.get(i).findElement(By.className("product-item-link")).getAttribute("href");
            imageUrl = searchResults.get(i).findElement(By.className("product-image-photo")).getAttribute("src");
            price = searchResults.get(i).findElement(By.className("price")).getText();


            products.add(new Product(alertType, heading, description, url, imageUrl, "b8f8d062-5baa-4d7e-8958-ec836a8e26cb", getPriceInCents(price)));
        }

        return products;
    }

    //turns price into cents
    public int getPriceInCents(String price)
    {
        double DPriceInCents;
        int priceInCents;

        price = price.replaceAll("€", "");

        DPriceInCents = Double.valueOf(price);
        DPriceInCents = DPriceInCents * 100;
        priceInCents = (int) DPriceInCents;

        return priceInCents;
    }

    //set injector for amount of times a page is opened
    public void setAmntTimedOpened(amntTimesOpened amntTimesOpened) {
        this.amntTimesOpened = amntTimesOpened;
    }

    //set injector to check if page is loaded
    public void setCheckPageIsLoaded(CheckPageIsLoaded loaded) {
        this.loaded = loaded;
    }

    //set injector to check for post request
    public void setCheckForPostRequest(CheckForPostRequest request) {
        this.request = request;
    }

    //set injector to check for delete requests
    public void setCheckForDeleteRequest(CheckForDeleteRequest request) {
        this.dRequest = request;
    }

    //set injector to check how many times a post request is made
    public void setAmntTimesPostRequestMade(amntTimesPostRequestMade amntTimesPostRequestMade) {
        this.amntTimesPostRequestMade = amntTimesPostRequestMade;
    }


    //test stub to check if the page was loaded
    public boolean stubToCheckIfPageIsFound()
    {
        if(!loaded.isPageLoaded())
        {
            return false;
        }

        if(loaded.isPageLoaded())
        {
            return true;
        }
        return false;
    }

    //test stub to check if post request was made or not
    public boolean stubToCheckIfPostRequestIsMade(int postRequest)
    {
        if(postRequest != 201 && !request.CheckForPostRequest())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //test stub to check if delete request was made
    public boolean stubToCheckIfDeleteRequestIsMade(int deleteRequest)
    {
        if(deleteRequest != 200 && !dRequest.CheckForDeleteRequest())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //method for checking how many pages were opened
    public int getAmntOfPagesOpened() {
        if (amntTimesOpened != null) {
            counter = amntTimesOpened.timeOpened();
        }

        return counter;
    }

    //method for checking how many pages were opened
    public int getAmntOfPostReqeustsMade() {
        if (amntTimesPostRequestMade != null) {
            counter = amntTimesPostRequestMade.madePostRequests();
        }

        return counter;
    }

    // call for post request
    public int postRequest(String productJsonString) throws IOException
    {
        URL url = new URL("https://api.marketalertum.com/Alert");
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        byte [] out = productJsonString.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int responseCode = http.getResponseCode();

        System.out.print(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        return responseCode;
    }

    // call for delete request
    public int deleteRequest() throws IOException {
        URL url = new URL("https://api.marketalertum.com/Alert?userId=b8f8d062-5baa-4d7e-8958-ec836a8e26cb");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDoOutput(true);
        http.setRequestProperty(
                "Content-Type", "application/json");
        http.setRequestMethod("DELETE");
        int responseCode = http.getResponseCode();
        http.connect();
        return responseCode;
    }

    // convert a string to json
    public String convertToJson(Product products) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String JsonString = mapper.writeValueAsString(products);

        System.out.println(JsonString);


        mapper.writeValue(new File("C:\\Users\\gabri\\Desktop\\BSC Software Development\\Year 3\\Software Testing\\Assingment\\Task 1\\data\\data.json"), products);

        return JsonString;
    }
}

