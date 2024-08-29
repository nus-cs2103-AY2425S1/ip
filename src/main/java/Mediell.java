import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Mediell {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        Storage storage = new Storage();
        TaskList items = storage.loadData();
        Ui ui = new Ui(items);
        ui.printGreeting();
        do {
            message = scanner.nextLine();
        } while (ui.main(message));
        ui.printFarewell();
    }
}
