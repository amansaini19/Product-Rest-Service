package com.store.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class is used for saving a reference to all products within the same category. Additionally, this class
 * also creates a keyword to product mapping within the same category.
 *
 * @author Amandeep Saini
 *
 */
public class ProductCategory {

	private String category;
	private Set<Product> products = new HashSet<>();
	private Map<String, Set<Product>> keywordProductsMap = new HashMap<>();

	public ProductCategory(String category) {
		this.category = category;
	}

	public void addProduct(Product product){
		if(!product.getCategory().equalsIgnoreCase(category)){
			throw new RuntimeException("Product's category does not match the category set in this class");
		}

		products.add(product);

		// create keyword to product mapping
		for(String keyword: product.getKeywords()){
			keyword = keyword.toLowerCase();
			Set<Product> keywordSet = keywordProductsMap.get(keyword);
			if(keywordSet == null){
				keywordSet = new HashSet<Product>();
				keywordProductsMap.put(keyword, keywordSet);
			}

			keywordSet.add(product);
		}
	}

	public Collection<String> getKeywords(){
		return keywordProductsMap.keySet();
	}

	public Set<Product> getProducts(String keyword){
		return keywordProductsMap.get(keyword);
	}

	public String getCategory() {
		return category;
	}

	public Set<Product> getProducts() {
		return products;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((keywordProductsMap == null) ? 0 : keywordProductsMap.hashCode());
        result = prime * result + ((products == null) ? 0 : products.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProductCategory other = (ProductCategory) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (keywordProductsMap == null) {
            if (other.keywordProductsMap != null) {
                return false;
            }
        } else if (!keywordProductsMap.equals(other.keywordProductsMap)) {
            return false;
        }
        if (products == null) {
            if (other.products != null) {
                return false;
            }
        } else if (!products.equals(other.products)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductCategory [category=" + category + "]";
    }
}
