package Task1;

import Task1.PageObjects.ResultPageObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class main {

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public static void main(String args[]) throws IOException {
        ResultPageObject n = new ResultPageObject();
        List<Product> products = new LinkedList<>();

        n.WebDriver("Iphone");

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = n.returnAllProducts();

        products = n.getAllProductInfo(searchResults, 5, 6);

//        System.out.println(products.getName());
//        System.out.println(Arrays.toString(products));
//        System.out.println(products);

        //json string
        for (Product product: products) {

            String JsonString = n.convertToJson(product);
            n.postRequest(JsonString);
        }






    }
}
