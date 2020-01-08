package com.store.datastore;

import java.util.Collection;

import com.store.product.Product;

/**
 * A repository to store product data and provide common search functionality.
 *
 * @author Amandeep Saini
 *
 */
public interface DataStore {


	/**
	 * Save the given product in the data store
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * Get all categories stored in the data store
	 * @return
	 */
	public Collection<String> getCategories();

	/**
	 * Search for all products in the given category
	 * @param category
	 * @return A collection of products in the given category
	 */
	public Collection<Product> getProducts(String category);

	/**
	 * Search for all products in the given category which also match a keyword
	 * @param category
	 * @param keyword
	 * @return
	 */
	public Collection<Product> getProducts(String category, String keyword);

	/**
	 * Search for all products which match a keyword
	 * @param keyword
	 * @return
	 */
	public Collection<Product> getProductsByKeyword(String keyword);

	/**
	 * Get a collection of all keywords in the data store
	 * @return
	 */
	public Collection<String> getKeywords();

	/**
	 * Does the datastore contain any products
	 * @return
	 */
	public boolean isEmpty();
}
