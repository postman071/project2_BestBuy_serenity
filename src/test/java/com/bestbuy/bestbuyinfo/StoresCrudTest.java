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
public class StoresCrudTest extends TestBase {

    static int storeid;
    static String name = "Prime" + TestUtils.getRandomValue();
    static String updatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "Xyz";

    static String address2 = "Abc";
    static String city = "Mehsana";

    static String state = "Gujarat";
    static String zip = "382865";
    @Steps
    StoresSteps storesSteps;

    @Title("This will create a new store ")
    @Test
    public void test001() {
        Response response = storesSteps.createStore(name, type, address, address2, city, state, zip);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeid = jsonPath.getInt("id");
        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(address, jsonPath.getString("address"));
        Assert.assertEquals(address2, jsonPath.getString("address2"));
        Assert.assertEquals(city, jsonPath.getString("city"));
        Assert.assertEquals(state, jsonPath.getString("state"));
        Assert.assertEquals(zip, jsonPath.getString("zip"));
    }

    @Title("Get Store by Store Id ")
    @Test
    public void test002() {
        Response response = storesSteps.getStoreById(storeid);
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(address, jsonPath.getString("address"));
        Assert.assertEquals(address2, jsonPath.getString("address2"));
        Assert.assertEquals(city, jsonPath.getString("city"));
        Assert.assertEquals(state, jsonPath.getString("state"));
        Assert.assertEquals(zip, jsonPath.getString("zip"));
    }

    @Title("Update Store by Store Id ")
    @Test
    public void test003() {
        Response response = storesSteps.updateStoreById(updatedName, type, address, address2, city, state, zip, storeid);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        Assert.assertEquals(updatedName, jsonPath.getString("name"));

    }

    @Title("Delete Store by Store Id ")
    @Test
    public void test004() {
        Response response = storesSteps.deleteStoreById(storeid);
        response.then().log().all().statusCode(200);

        response = storesSteps.getStoreById(storeid);
        response.then().log().all().statusCode(404);

    }
}
