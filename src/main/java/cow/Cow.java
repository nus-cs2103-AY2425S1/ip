package cow;

import java.io.IOException;
import cow.commands.Command;
import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.parser.Parser;
import cow.todolist.TodoList;

/** Creates a cow object to start the chatbot. **/
public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private TodoList todoList;
    private final FileSaver fs;
    private final Ui ui;

    /**
     * Creates an instance of the Cow class.
     *
     * @param filePath of the save file.
     */
    public Cow(String filePath) {
        this.ui = new Ui();
        this.fs = new FileSaver(filePath);
        try {
            todoList = fs.loadData();
        } catch (IOException | CowExceptions e) {
            handleCowException();
        }
    }

    private void handleCowException() {
        ui.printLoadingError();
        todoList = new TodoList();
    }

    /**
     * Runs the Cow program by repeated asking for inputs until
     * users says bye.
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(todoList, ui, fs);
                isExit = c.isExit();
            } catch (CowExceptions e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Gets response from cow from an input.
     *
     * @param input user input from GUI.
     * @return the response from cow.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(todoList, ui, fs);
            return ui.getCurrentText();
        } catch (CowExceptions e) {
            return e.getMessage();
        }
    }

    /**
     * Gets cow greeting message.
     * @return a string.
     */
    public String getGreetings() {
        return ui.printGreetings();
    }

    /**
     * Runs cow.
     * @param args are optional.
     */
    public static void main(String[] args) {
        new Cow("data/cow.txt").run();
    }
}
