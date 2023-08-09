package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Path;
import com.bestbuy.model.StorePojo;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;

public class StoresSteps {

    @Step("Create store with name : {0},type : {1},address : {2},address2 : {3},city : {4},state : {5},zip : {6}")
    public Response createStore(String name, String type, String address, String address2, String city, String state, String zip) {

        StorePojo storePojo = StorePojo.getStoresPojo(name, type, address, address2, city, state, zip);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(Path.STORES);
    }

    @Step("Get Store by store id : {0}")
    public Response getStoreById(int storeId) {
        return SerenityRest.given().log().all()
                .when()
                .get(Path.STORES + "/" + storeId);
    }


    @Step("Update store By Store id name: {0},type : {1},address : {2},address2 : {3},city : {4},state : {5},zip : {6},storeId : {7}")
    public Response updateStoreById(String name, String type, String address, String address2, String city, String state, String zip, int storeId) {

        StorePojo storePojo = StorePojo.getStoresPojo(name, type, address, address2, city, state, zip);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .put(Path.STORES + "/" + storeId);
    }

    @Step("Delete store By Store id : {0}")
    public Response deleteStoreById(int storeId) {

        return given().log().all()
                .when()
                .delete(Path.STORES + "/" + storeId);

    }

}
