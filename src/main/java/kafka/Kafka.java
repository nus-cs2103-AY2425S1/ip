package kafka;

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
    public String getResponse(String input) {
        String output;
        try {
            output = Parser.parseCommand(input, taskList, storage, ui);
        } catch (KafkaException | IOException e) {
            output = ui.showError(e);
        } catch (DateTimeParseException e) {
            output = ui.incorrectDateDetails();
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(Ui.greet());
    }
}
