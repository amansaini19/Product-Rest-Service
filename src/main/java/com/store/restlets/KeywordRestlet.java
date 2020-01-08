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
 * - List all keywords in the system (/search/keyword)
 * - List all records matching the single keyword in any category (/search/keyword/{word})
 *
 * A status value is returned to indicate if any results were found or not.
 *
 * @author Amandeep Saini
 *
 */
public class KeywordRestlet extends CustomRestlet {

    private static Log log = LogFactory.getLog(KeywordRestlet.class);
	private DataStore ds;


	public KeywordRestlet(DataStore ds) {
		super();
		this.ds = ds;
	}

	@Override
    public void handle(Request request, Response response) {

		if (request.getMethod().equals(Method.GET)) {
			String keyword = null;

			// Get request attributes and decode
			try{
				keyword = request.getAttributes().get("word").toString();
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			}catch(Exception e){
			    log.error("An error occurred while getting request attributes", e);
			    response.setStatus(org.restlet.data.Status.CLIENT_ERROR_BAD_REQUEST, e, "Unable to parse request attributes");
			}

			// All products search by keyword
			if (keyword != null && !keyword.isEmpty()) {
        		JSONObject json = new JSONObject();

				Collection<Product> products = ds.getProductsByKeyword(keyword);
				if(products != null && !products.isEmpty()){
					JSONArray array = new JSONArray();
					array.addAll(ProductUtil.getJsonObjects(products));
        			json.put(RESPONSE_ATTRIBUTE_RESULTS, array);
        			json.put(RESPONSE_ATTRIBUTE_STATUS, Status.SUCCESS);
				}else{
					json.put(RESPONSE_ATTRIBUTE_STATUS, Status.NO_RESULTS_FOUND);
				}

				response.setEntity(json.toJSONString(), MediaType.APPLICATION_JSON);
			} else { // Return all keywords
        		JSONObject json = new JSONObject();

				Collection<String> keywords = ds.getKeywords();
				if(keywords != null && !keywords.isEmpty()){
					JSONArray array = new JSONArray();
        			array.addAll(keywords);
        			json.put(RESPONSE_ATTRIBUTE_RESULTS, array);
        			json.put(RESPONSE_ATTRIBUTE_STATUS, Status.SUCCESS);
				}else{
					json.put(RESPONSE_ATTRIBUTE_STATUS, Status.NO_RESULTS_FOUND);
				}

				response.setEntity(json.toJSONString(), MediaType.APPLICATION_JSON);
			}
		} else {
			response.setStatus(org.restlet.data.Status.CLIENT_ERROR_BAD_REQUEST);
		}
	}
}
