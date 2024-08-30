import commands.TaskList;
import storage.Storage;
import system.Ui;
import system.InputProcessor;
import system.Parser;
import java.io.IOException;

public class Tanjiro {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        InputProcessor inputProcessor = new InputProcessor();
        Storage storage = new Storage();
        storage.createFile();

        Parser parser = new Parser();
        TaskList.init_list();
        ui.greet();

        String userInput = inputProcessor.read().toLowerCase();
        while (!parser.containBye(userInput)) {
            if (parser.containList(userInput)) {
                TaskList.list_task();
            } else if (parser.containMark(userInput)) {
                parser.performMark(userInput);
            } else if (parser.containToDo(userInput)) {
                parser.performToDo(userInput);
            } else if (parser.containDeadline(userInput)) {
                parser.performDeadline(userInput);
            } else if (parser.containEvent(userInput)) {
                parser.performEvent(userInput);
            } else if (parser.containDelete(userInput)) {
                parser.performDelete(userInput);
            } else {
                ui.invalid_input();
            }
            userInput = inputProcessor.read().toLowerCase();
        }

        ui.goodbye();

    }

}
