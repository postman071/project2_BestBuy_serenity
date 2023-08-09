package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class ProductCrudTest extends TestBase {

    static String name = "New Product" + TestUtils.getRandomValue();
    static String updatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Hard Good" + TestUtils.getRandomValue();
    static String upc = "12" + TestUtils.getRandomValue();
    static double price = 99.99;
    static String description = "This is a placeholder request for creating a new product.";
    static String model = "NP" + TestUtils.getRandomValue();
    static int productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product ")
    @Test
    public void test001() {

        Response response = productsSteps.createProduct(name, type, upc, price, description, model);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        productId = jsonPath.getInt("id");

        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(upc, jsonPath.getString("upc"));
        Assert.assertTrue(price - jsonPath.getDouble("price") == 0);
        Assert.assertEquals(description, jsonPath.getString("description"));
        Assert.assertEquals(model, jsonPath.getString("model"));
    }

    @Title("Get Product by Product Id ")
    @Test
    public void test002() {
        Response response = productsSteps.getProductById(productId);
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(upc, jsonPath.getString("upc"));
        Assert.assertTrue(price - jsonPath.getDouble("price") == 0);
        Assert.assertEquals(description, jsonPath.getString("description"));
        Assert.assertEquals(model, jsonPath.getString("model"));
    }

    @Title("Update Product by product Id ")
    @Test
    public void test003() {

        Response response = productsSteps.updateProductByProductId(updatedName, type, upc, price, description, model, productId);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        Assert.assertEquals(updatedName, jsonPath.getString("name"));

    }
    @Title("Delete Product by product Id ")
    @Test
    public void test004() {
        Response response = productsSteps.deleteProductById(productId);
        response.then().log().all().statusCode(200);

        response = productsSteps.getProductById(productId);
        response.then().log().all().statusCode(404);
    }
}
