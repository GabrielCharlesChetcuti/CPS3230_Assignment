package Task1;

import Task1.PageObjects.ResultPageObject;
import Task1.Spies.amntTimesOpenedSpy;
import Task1.Spies.amntTimesPostRequestMadeSpy;
import Task1.Stubs.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestSuite {


    @Test
    public void stubCheckPageIsFound()
    {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setCheckPageIsLoaded(new PageIsLoaded());

        //excercise
        boolean result = pageObject.stubToCheckIfPageIsFound();

        //verfiy
        Assertions.assertTrue(result);

        //teardown
    }

    @Test
    public void stubCheckPageIsNotFound()
    {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setCheckPageIsLoaded(new PageIsNotLoaded());

        //excercise
        boolean result = pageObject.stubToCheckIfPageIsFound();

        //verfiy
        Assertions.assertFalse(result);

        //teardown
    }

    //stub to check if post request is made
    @Test
    public void stubCheckPostRequestIsFound() throws IOException {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setCheckForPostRequest(new postRequestFound());

        //excercise
        int responseCode = pageObject.postRequest("{\"alertType\":6,\"heading\":\"Iphone/IPhone accessory\",\"description\":\"BeHello iPhone 14 Plus High Impact Glass Screen\",\"url\":\"https://www.scanmalta.com/shop/behello-iphone-14-plus-high-impact-glass-screen-ap.html\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/1/e/1e19ac16-1ec9-4804-a3b1-4db14a750e88_ip14_6-7-282-29.jpg\",\"postedBy\":\"b8f8d062-5baa-4d7e-8958-ec836a8e26cb\",\"priceInCents\":1995}");

        boolean result = pageObject.stubToCheckIfPostRequestIsMade(responseCode);

        //verfiy
//        Assertions.assertTrue(result);
        Assertions.assertEquals(201, responseCode);

        //teardown
    }

    @Test
    public void stubCheckPostRequestIsNotFound() throws IOException {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setCheckForPostRequest(new postRequestNotFound());

        //excercise
        int responseCode = pageObject.postRequest("");
        boolean result = pageObject.stubToCheckIfPostRequestIsMade(responseCode);

        //verfiy
        Assertions.assertFalse(result);

        //teardown
    }

    @Test
    public void stubCheckDeleteRequestIsFound() throws IOException {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setCheckForDeleteRequest(new DeleteRequestFound());

        //excercise
        int responseCode = pageObject.deleteRequest();
        boolean result = pageObject.stubToCheckIfDeleteRequestIsMade(responseCode);

        //verfiy
        Assertions.assertTrue(result);

        //teardown
    }


    @Test
    public void testIfOnePageOpened()
    {
        //setup
        amntTimesOpenedSpy spy = new amntTimesOpenedSpy();
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setAmntTimedOpened(spy);

        //excercise
        int amount = pageObject.getAmntOfPagesOpened();

        //verify
        Assertions.assertEquals(1, amount);

        //teardown
    }

    @Test
    public void testAmntOfPostRequestMade()
    {
        //setup
        amntTimesPostRequestMadeSpy spy = new amntTimesPostRequestMadeSpy();
        ResultPageObject pageObject = new ResultPageObject();
        pageObject.setAmntTimesPostRequestMade(spy);

        //excercise
        int amount = pageObject.getAmntOfPostReqeustsMade();

        //verify
        Assertions.assertEquals(1, amount);

        //teardown
    }

    @Test
    public void testConvertToJson() throws IOException {
        //setup
        Product p = new Product(6, "Iphone/IPhone accessory", "iPhone 14 Pro / iPhone 14 Pro Max", "https://www.scanmalta.com/shop/", "https://www.scanmalta.com/shop/pub/media/catalog/product/cache", "b8f8d062-5baa", 1995);
        ResultPageObject pageObject = new ResultPageObject();


        //excercise
        String JsonString = pageObject.convertToJson(p);

        //assertions
        Assertions.assertEquals("{\"alertType\":6,\"heading\":\"Iphone/IPhone accessory\",\"description\":\"iPhone 14 Pro / iPhone 14 Pro Max\",\"url\":\"https://www.scanmalta.com/shop/\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache\",\"postedBy\":\"b8f8d062-5baa\",\"priceInCents\":1995}", JsonString);

    }

    @Test
    public void testIfConvertedToCents()
    {
        //setup
        ResultPageObject pageObject = new ResultPageObject();
        String price = "€56.44";


        //excercise
        int priceInCents = pageObject.getPriceInCents(price);

        //assertions
        Assertions.assertEquals(5644, priceInCents);
    }
}
