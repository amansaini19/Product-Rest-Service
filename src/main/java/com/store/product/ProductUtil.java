package com.store.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

/**
 * Provides helper functions related to products
 *
 * @author Amandeep Saini
 *
 */
public class ProductUtil {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
     * Get a collection of JSON formatted Strings for the provided products.
     *
     * @param products
     * @return
     */
    public static Collection<String> getJsonObjects(Collection<Product> products){
        List<String> jsonObjects = new ArrayList<>();
        products.forEach(product -> {
            jsonObjects.add(generateJsonObject(product));
        });

        return jsonObjects;
    }

    /**
     * Get a JSON formatted String representing the provided product
     * @param product
     * @return
     */
    public static String generateJsonObject(Product product){
        JSONObject obj = new JSONObject();
        obj.put("createdDate", product.getCreatedDate());
        obj.put("imageUrl", product.getImageUrl());
        obj.put("title", product.getTitle());
        obj.put("category", product.getCategory());
        obj.put("isActive", product.getIsActive());
        obj.put("itemId", product.getItemId());
        obj.put("parentCategory", product.getParentCategory());
        obj.put("department", product.getDepartment());
        obj.put("upc", product.getUpc());
        obj.put("brand", product.getBrand());
        obj.put("modifiedDate", product.getModifiedDate());
        obj.put("itemHashint64", product.getItemHashint64());

        return obj.toJSONString();
    }

    /**
     * Creates a new Product object for the provided JSON object.
     *
     * @param product The JSON object which contains the necessary information about a product
     * @return A Product representing the provided input
     * @throws ParseException
     */
    public static Product parseProduct(JSONObject product) throws ParseException{
        String imageUrl = product.get("imageUrl").toString();
        Date createdDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(product.get("createdDate").toString());
        Date modifiedDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(product.get("modifiedDate").toString());
        long itemHashint64 = Long.parseLong(product.get("itemHashint64").toString());
        String title = product.get("title").toString();
        String category = product.get("category").toString();
        boolean isActive = product.get("isActive").toString().equals('0');
        long itemId = Long.parseLong(product.get("itemId").toString());

        return new Product(createdDate,
                imageUrl,
                title,
                category,
                isActive,
                itemId,
                product.get("parentCategory").toString(),
                product.get("department").toString(),
                product.get("upc").toString(),
                product.get("brand").toString(),
                modifiedDate,
                itemHashint64,
                ProductUtil.getKeywords(title));
    }

    /**
     * Splits the input by spaces. Words less than 3 characters are ignored.
     * @return A Set of keywords.
     */
    public static Set<String> getKeywords(String input){
        Set<String> keywords = new HashSet<String>();

        for(String keyword: input.split("\\s+")){
            if(keyword.length() > 2) {
                keywords.add(keyword);
            }
        }

        return keywords;
    }
}
