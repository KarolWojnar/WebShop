package com.webshop.Service;

import com.google.gson.JsonArray;
import com.webshop.Model.Category;
import com.webshop.Model.Product;
import com.webshop.Repository.CategoryRepository;
import com.webshop.Repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public void addAllItems(Model model) throws JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products"))
                .build();

        CompletableFuture<String> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        String responseBody = responseFuture.join();
        model.addAttribute("resposne", responseBody);
        saveDataToDB(responseBody);
    }

    private void saveDataToDB(String responseBody) throws JSONException {
        JSONArray jArray = new JSONArray(responseBody);
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObject = jArray.getJSONObject(i);
            Optional<Category> optionalCategory = categoryRepository.findCategoryByName(jObject.getString("category"));
            if (optionalCategory.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setName(jObject.getString("category"));
                categoryRepository.save(newCategory);
            }
            Category category = categoryRepository.getCategoryByName(jObject.getString("category"));
            Product newProduct = new Product();
            newProduct.setCategoryId(category);
            newProduct.setDescription(jObject.getString("description"));
            newProduct.setTitle(jObject.getString("title"));
            newProduct.setPrice(jObject.getDouble("price"));
            newProduct.setStockQuantity(100);
            newProduct.setImage(jObject.getString("image"));
            newProduct.setRate(jObject.getJSONObject("rating").getDouble("rate"));
            newProduct.setCountRate(jObject.getJSONObject("rating").getInt("count"));
            productRepository.save(newProduct);
        }
    }
}
