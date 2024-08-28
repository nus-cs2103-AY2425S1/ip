import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Justbot {
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    public Justbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }

    }
    public void run() {
        ui.botIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (JustbotException e) {
                ui.getJustBotExceptionMessage(e);
            }
        }
        ui.byeMessage();
    }

    public static void main(String[] args) {
        new Justbot("/Users/justinyeo/Desktop/CS2103T-ip/data/justbottask.txt").run();
    }

}
