package com.store.datastore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.store.product.Product;
import com.store.product.ProductCategory;

/**
 * Uses an in-memory map to store product data and implement
 * methods defined by the {@code DataStore} interface.
 *
 * @author Amandeep Saini
 *
 */
public class InMemoryDataStore implements DataStore{
    private static Log log = LogFactory.getLog(InMemoryDataStore.class);

    private Map<String, ProductCategory> catProducts = new HashMap<>();

    @Override
    public void addProduct(Product product){
        String category = product.getCategory().toLowerCase();
        ProductCategory productCategory = catProducts.get(category);
        if(productCategory == null){
            productCategory = new ProductCategory(category);
            catProducts.put(category, productCategory);
        }

        try {
            productCategory.addProduct(product);
        } catch (Exception e) {
            log.error(String.format("An error occured when adding a new product to a "
                + "product category. Product: %s, Category: %s", product, productCategory.getCategory()), e);
        }
    }

    @Override
    public Collection<String> getCategories(){
        return catProducts.keySet();
    }

    @Override
    public Collection<Product> getProducts(String category){
        ProductCategory pc = catProducts.get(category.toLowerCase());
        if(pc == null) {
            return null;
        } else {
            return pc.getProducts();
        }
    }

    @Override
    public Collection<Product> getProducts(String category, String keyword){
        ProductCategory pc = catProducts.get(category.toLowerCase());
        if(pc == null) {
            return null;
        } else {
            return pc.getProducts(keyword.toLowerCase());
        }
    }


    @Override
    public Collection<Product> getProductsByKeyword(String keyword){
        return catProducts.values().stream().flatMap(pc -> {
            Collection<Product> products = pc.getProducts(keyword);
            if(products == null){
                products = Collections.emptyList();
            }
            return products.stream();
        }).collect(Collectors.toSet());
    }

    @Override
    public Collection<String> getKeywords(){
        return catProducts.values().stream().flatMap(pc -> pc.getKeywords().stream()).collect(Collectors.toSet());
    }

    @Override
    public boolean isEmpty(){
        return catProducts.isEmpty();
    }
}
