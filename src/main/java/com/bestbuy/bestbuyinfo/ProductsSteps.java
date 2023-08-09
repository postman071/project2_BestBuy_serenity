package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Path;
import com.bestbuy.model.ProjectPojo;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;

public class ProductsSteps {

    @Step("Create new product - name : {0},type : {1},upc : {2},price : {3},description : {4},model : {5}")
    public Response createProduct(String name, String type, String upc, double price, String description, String model) {

        ProjectPojo projectPojo = ProjectPojo.getProductPojo(name, type, upc, price, description, model);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .post(Path.PRODUCTS);
    }

    @Step("Get Product by product id : {0}")
    public Response getProductById(int productId) {
        return SerenityRest.given().log().all()
                .when()
                .get(Path.PRODUCTS + "/" + productId);
    }

    @Step("Update new product by productId - name : {0},type : {1},upc : {2},price : {3},description : {4},model : {5},productId : {6}")
    public Response updateProductByProductId(String name, String type, String upc, double price, String description, String model, int productId) {

        ProjectPojo projectPojo = ProjectPojo.getProductPojo(name, type, upc, price, description, model);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .put(Path.PRODUCTS + "/" + productId);
    }

    @Step("Delete Product By Product id : {0}")
    public Response deleteProductById(int productId) {

        return given().log().all()
                .when()
                .delete(Path.PRODUCTS + "/" + productId);

    }
}
