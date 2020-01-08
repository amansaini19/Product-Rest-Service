package com.store.restlets;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;

import com.store.datastore.DataStore;
import com.store.product.Product;
import com.store.product.ProductUtil;

/**
 * Provides a REST service for the following functions:
 * - List all categories (/search/category/)
 * - List all product records in the given category (/search/category/{category})
 * - List all product records in the given category which also match a keyword (/search/category/{category}/keyword/{word})
 *
 * A status value is returned to indicate if any results were found or not.
 *
 * @author Amandeep Saini
 *
 */
public class CategoryRestlet extends CustomRestlet {

    private static Log log = LogFactory.getLog(CategoryRestlet.class);

	private DataStore ds;


	public CategoryRestlet(DataStore ds) {
		super();
		this.ds = ds;
	}

	@Override
    public void handle(Request request, Response response) {

		if(request.getMethod().equals(Method.GET)) {
			String category = null;
			String keyword = null;

			// Get request attributes and decode
			try{
				category = request.getAttributes().get("category").toString();
				category = java.net.URLDecoder.decode(category, "UTF-8");
				keyword = request.getAttributes().get("word").toString();
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			}catch(Exception e){
			    log.error("An error occurred while getting request attributes", e);
	            response.setStatus(org.restlet.data.Status.CLIENT_ERROR_BAD_REQUEST, e, "Unable to parse request attributes");
			}

			// Category and keyword search
			if (category != null && !category.isEmpty() && keyword != null && !keyword.isEmpty()) {

				JSONObject json = new JSONObject();

				Collection<Product> products = ds.getProducts(category, keyword);
				if(products != null && !products.isEmpty()){
					JSONArray array = new JSONArray();
        			array.addAll(ProductUtil.getJsonObjects(products));
        			json.put(RESPONSE_ATTRIBUTE_RESULTS, array);
        			json.put(RESPONSE_ATTRIBUTE_STATUS, Status.SUCCESS);
				}else{
					json.put(RESPONSE_ATTRIBUTE_STATUS, Status.NO_RESULTS_FOUND);
				}

				response.setEntity(json.toJSONString(), MediaType.APPLICATION_JSON);

			}else if(category != null && !category.isEmpty()){ // Category only search
        		JSONObject json = new JSONObject();

				Collection<Product> products = ds.getProducts(category);
				if(products != null && !products.isEmpty()){
					JSONArray array = new JSONArray();
					array.addAll(ProductUtil.getJsonObjects(products));
        			json.put(RESPONSE_ATTRIBUTE_RESULTS, array);
        			json.put(RESPONSE_ATTRIBUTE_STATUS, Status.SUCCESS);
				}else{
					json.put(RESPONSE_ATTRIBUTE_STATUS, Status.NO_RESULTS_FOUND);
				}

				response.setEntity(json.toJSONString(), MediaType.APPLICATION_JSON);

        	}else{ // Return all categories
        		JSONObject json = new JSONObject();

				Collection<String> categories = ds.getCategories();
				if(categories != null && !categories.isEmpty()){
					JSONArray array = new JSONArray();
					array.addAll(categories);
        			json.put(RESPONSE_ATTRIBUTE_RESULTS, array);
        			json.put(RESPONSE_ATTRIBUTE_STATUS, Status.SUCCESS);
				}else{
					json.put(RESPONSE_ATTRIBUTE_STATUS, Status.NO_RESULTS_FOUND);
				}

        		response.setEntity(json.toJSONString(), MediaType.APPLICATION_JSON);
        	}


        }else {
            response.setStatus(org.restlet.data.Status.CLIENT_ERROR_BAD_REQUEST);
        }
	}
}
