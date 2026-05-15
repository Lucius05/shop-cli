import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VendureServiceTest {

    @Test
    void shouldCreateProductsQuery() {
        VendureService service = new VendureService("http://localhost:3000/shop-api");

        String query = service.createProductsQuery();

        assertEquals("query { products { items { name } } }", query);
    }

    @Test
    void shouldParseProductResponse() {
        VendureService service = new VendureService("http://localhost:3000/shop-api");

        String json = """
            {
              "data": {
                "product": {
                  "name": "Wooden Side Desk"
                }
              }
            }
            """;

        Product product = service.parseProduct(json);

        assertEquals("Wooden Side Desk", product.name);
        assertEquals(0.0, product.price);
    }
}