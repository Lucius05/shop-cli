import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "cli",
        description = "Application CLI pour afficher les produits",
        subcommands = {ListCommand.class}
)
public class CLI implements Runnable {

    @Option(names = "--url", description = "URL du serveur Vendure")
    String url = System.getenv("URL");

    public static void main(String[] args) {
        new CommandLine(new CLI()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Utilisez une sous-commande, par exemple: list");
    }
}