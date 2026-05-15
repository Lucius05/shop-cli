import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;

@Command(name = "list", description = "Affiche les produits")
public class ListCommand implements Runnable {

    @Option(names = "--format", defaultValue = "table", description = "Format de sortie: table ou json")
    String format;

    @Override
    public void run() {
        VendureService service = new VendureService("http://localhost:3000/shop-api");
        List<Product> products = service.getProducts();

        if (format.equals("json")) {
            System.out.println("""
                    [{"name":"Produit 1","price":10.0},{"name":"Produit 2","price":20.0}]
                    """);
        } else {
            System.out.println("Nom       | Prix");
            System.out.println("----------------");
            for (Product product : products) {
                System.out.println(product.name + " | " + product.price);
            }
        }
    }
}