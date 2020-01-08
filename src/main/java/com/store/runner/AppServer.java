package com.store.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import com.store.datastore.DataStore;
import com.store.datastore.InMemoryDataStore;
import com.store.product.ProductUtil;
import com.store.restlets.CategoryRestlet;
import com.store.restlets.KeywordRestlet;

/**
 * The main class. This class expects to be given as a argument the file path to an appropriate products file.
 *
 * @author Amandeep Saini
 *
 */
public class AppServer extends Application {

    private static Log log = LogFactory.getLog(AppServer.class);

	private DataStore dataStore = new InMemoryDataStore();

	public static void main(String[] args) throws Exception {

		if(args.length > 0) {
		    log.info("Starting server");
            new AppServer(args[0]);
        } else {
            log.error("Please provide the products file path");
        }
	}

	private AppServer(String filePath){
		JSONParser parser = new JSONParser();
		InputStreamReader reader = null;

		// read products file
		try {
			File file = new File(filePath);
			reader = new InputStreamReader(new FileInputStream(file));

		} catch (FileNotFoundException e) {
			log.error(filePath + " does not exist. Please provide a valid file path.", e);
			System.exit(1);
		}

		// parse products JSON file
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(reader);
		} catch (Exception e) {
			log.error("An error occurred while parsing products JSON file", e);
			System.exit(1);
		}

		// load all products
		JSONArray products = (JSONArray) jsonObject.get("products");
		Iterator<JSONObject> productsItr = products.iterator();

		// add each product to the data store
		while (productsItr.hasNext()) {
		    JSONObject product = productsItr.next();
		    try{
		        dataStore.addProduct(ProductUtil.parseProduct(product));
		    }catch(Exception e){
		        log.error("An error occurred while loading product with data: " + product, e);
		    }
		}

		if(dataStore.isEmpty()){
			log.error("There were no products loaded from the file. Exiting.");
			System.exit(1);
		}

		// Setup and start Restlet
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8183);

		component.getDefaultHost().attach("", this);
		try {
			component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		CategoryRestlet cr = new CategoryRestlet(dataStore);
		KeywordRestlet kr = new KeywordRestlet(dataStore);

		router.attach("/search/category", cr);
		router.attach("/search/category/{category}", cr);
		router.attach("/search/category/{category}/keyword/{word}", cr);
		router.attach("/search/keyword", kr);
		router.attach("/search/keyword/{word}", kr);
		return router;
	}
}
