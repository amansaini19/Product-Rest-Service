# Product REST Service

This is a simple REST based service application that I developed when I was learning about web services. The application loads a file containing production data (products.json) to in an in-memory map at startup and provides the following end-points for querying for product related data:


```
/search/category - returns a list of all known product categories 
/search/category/{category} - returns a list of all products for the provided category (example: /search/category/upright%20brooms)
/search/category/{category}/keyword/{word} - returns a list of products for the provided category and keyword (example: /search/category/upright%20brooms/keyword/clean)
/search/keyword - returns a list of all known product keywords (keywords are based on the title)
/search/keyword/{word} - returns a list of products for the provided keyword
```

## Usage

This application is written in Java (version 8+) and uses maven for dependency management. It was developed in Eclipse. To run, ensure the project is maven enabled in Eclipse and set the main class to AppServer.java. The service will start on port 8183.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
