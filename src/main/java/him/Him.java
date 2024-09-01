package him;

import command.Command;
import exceptions.AlreadyCompletedException;
import exceptions.HimException;
import exceptions.StartAfterEndException;
import exceptions.TaskDoesNotExistException;
import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Him {

    private static TaskList list = new TaskList();
    private static final Storage storage = new Storage();

    public static void main(String[] args) {
        try {
            list = storage.loadTaskList();
        } catch (FileNotFoundException e) {
            storage.initStorage();
        } catch (AlreadyCompletedException | StartAfterEndException e) {
            him.Ui.showLoadingFailure();
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        him.Ui.greet();
        him.Ui.printUser();
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            try {
                Command c = Parser.parseUserInput(userInput);
                c.execute(list);
            } catch (HimException e) {
                him.Ui.say(e.getMessage().split("\n"));
            }

            try {
                storage.saveTaskList(list);
            } catch (IOException e) {
                him.Ui.showSaveFailure();
            }
            him.Ui.printUser();
            userInput = sc.nextLine();
        }
        sc.close();
        him.Ui.exit();
    }
}
