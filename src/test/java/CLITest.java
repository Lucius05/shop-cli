import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CLITest {

    @Test
    void shouldCreateCLI() {
        CLI cli = new CLI();
        assertNotNull(cli);
    }

    @Test
    void listProductsShouldThrowUntilImplemented() {
        CLI cli = new CLI();

        assertThrows(
                UnsupportedOperationException.class,
                () -> cli.listProducts()
        );
    }

    @Test
    void shouldSupportJsonFormat() {
        CLI cli = new CLI();

        assertThrows(
                UnsupportedOperationException.class,
                () -> cli.listProducts("json")
        );
    }
}