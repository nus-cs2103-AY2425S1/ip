import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Zaibot {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage(taskList);

    public void run() {
        Ui.printGreeting();
        boolean continueLoop = true;
        Ui.printSeparator();

        while (continueLoop) {
            try {
                String commandInput = Ui.readCommand();
                Command command = Parser.parse(commandInput);
                command.execute(taskList, storage);
                continueLoop = command.toContinue();
                Ui.printSeparator();
            }
            catch (ZaibotException e) {
                Ui.displayError(e);
            }
        }
    }

    /**
     * Takes in the list of tasks, and converts it into a string.
     * @return A string of all the tasks, enumerated.
     */
    public static void main(String[] args) {
        new Zaibot().run();
    }
}
