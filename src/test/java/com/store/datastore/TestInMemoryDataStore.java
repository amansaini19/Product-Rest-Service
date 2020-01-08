package com.store.datastore;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.store.product.Product;
import com.store.product.ProductUtil;

/**
 * JUnit test for InMemoryDataStore
 *
 */
public class TestInMemoryDataStore {

    private DataStore dataStore = new InMemoryDataStore();

    @Before
    public void init(){
        String title = "Test prod 1";
        Product prod1 = new Product(new Date(), "http://localhost/img.jpg",
            title, "cat 1", true, 12345l, "parent cat 1", "dept 1",
            "0123456", "brand 1", new Date(), 123456l, ProductUtil.getKeywords(title));

        title = "Test prod 2";
        Product prod2 = new Product(new Date(), "http://localhost/img.jpg",
            title, "cat 2", true, 12345l, "parent cat 1", "dept 1",
            "01234567", "brand 1", new Date(), 1234567l, ProductUtil.getKeywords(title));

        title = "Test prod 3";
        Product prod3 = new Product(new Date(), "http://localhost/img.jpg",
            title, "cat 3", true, 12345l, "parent cat 1", "dept 1",
            "012345678", "brand 1", new Date(), 12345678l, ProductUtil.getKeywords(title));

        dataStore.addProduct(prod1);
        dataStore.addProduct(prod2);
        dataStore.addProduct(prod3);
    }

    @Test
    public void testDataStore(){
        assertEquals(dataStore.getCategories().size(), 3);
        assertEquals(dataStore.getProducts("cat 3").size(), 1);
        assertEquals(dataStore.getProductsByKeyword("test").size(), 3);
        assertEquals(dataStore.getProducts("cat 3", "test").size(), 1);
        assertEquals(dataStore.getKeywords().size(), 2);

    }
}
