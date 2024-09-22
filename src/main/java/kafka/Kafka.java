package kafka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;


public class Kafka {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Kafka(String filePath) throws FileNotFoundException{
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError(e);
            taskList = new TaskList();
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
