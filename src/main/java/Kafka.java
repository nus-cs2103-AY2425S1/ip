import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Kafka {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static boolean isExitChat = false;

    public Kafka(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new TaskList();
        }
    }

    public static void exitChat() {
        if (!isExitChat) {
            isExitChat = true;
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.greet();
        while (!isExitChat) {
            try {
                Parser.parseCommand(scanner.nextLine(), taskList, storage, ui);
            } catch (KafkaException | IOException e) {
                ui.showError(e);
            } catch (DateTimeParseException e) {
                ui.incorrectDateDetails();
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Kafka("C:/Users/Nicholas/Downloads/Kafka.txt").run();
    }
}
