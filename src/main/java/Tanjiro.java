import Commands.TaskList;
import Storage.Storage;
import System.Ui;
import System.InputProcessor;
import System.Parser;
import java.io.IOException;

public class Tanjiro {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        InputProcessor inputProcessor = new InputProcessor();
        Storage storage = new Storage();
        Parser parser = new Parser();

        storage.createFile();
        TaskList.init_list();

        ui.greet();

        String user_input = inputProcessor.read().toLowerCase();
        while (!parser.containBye(user_input)) {
            if (parser.containList(user_input)) {
                TaskList.list_task();
            } else if (parser.containMark(user_input)) {
                parser.performMark(user_input);
            } else if (parser.containToDo(user_input)) {
                parser.performToDo(user_input);
            } else if (parser.containDeadline(user_input)) {
                parser.performDeadline(user_input);
            } else if (parser.containEvent(user_input)) {
                parser.performEvent(user_input);
            } else if (parser.containDelete(user_input)) {
                parser.performDelete(user_input);
            } else {
                ui.invalid_input();
            }
            user_input = inputProcessor.read().toLowerCase();
        }

        ui.goodbye();

    }

}
