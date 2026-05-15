import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class VendureService {

    private final String url;

    public VendureService(String url) {
        this.url = url;
    }



    public List<Product> getProducts() {

        try {

            String graphqlQuery = """
                {
                  "query": "query { products { items { name } } }"
                }
                """;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(graphqlQuery))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());

            JSONArray items = json
                    .getJSONObject("data")
                    .getJSONObject("products")
                    .getJSONArray("items");

            List<Product> products = new ArrayList<>();

            for (int i = 0; i < items.length(); i++) {

                JSONObject item = items.getJSONObject(i);

                String name = item.getString("name");

                products.add(new Product(name, 0.0));
            }

            return products;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Product getProductBySlug(String slug) {

        try {

            String graphqlQuery = """
            {
              "query": "query { product(slug:\\"%s\\") { name } }"
            }
            """.formatted(slug);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(graphqlQuery))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());

            JSONObject product = json
                    .getJSONObject("data")
                    .getJSONObject("product");

            String name = product.getString("name");

            return new Product(name, 0.0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String createProductsQuery() {
        return "query { products { items { name } } }";
    }

    public Product parseProduct(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);

        JSONObject product =
                json.getJSONObject("data")
                        .getJSONObject("product");

        return new Product(product.getString("name"), 0.0);
    }
}