package him;

import command.Command;
import exceptions.AlreadyCompletedException;
import exceptions.HimException;
import exceptions.StartAfterEndException;
import exceptions.TaskDoesNotExistException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Him {

    private static TaskList list = new TaskList();
    private static final Storage storage = new Storage();

    private static void complete(int index) {
        try {
            list.complete(index);
            him.Ui.sayCompleted(list.taskAt(index));
        } catch (AlreadyCompletedException | TaskDoesNotExistException e) {
            him.Ui.say(e.getMessage());
        }
    }

    private static void delete(int index) {
        try {
            String task = list.delete(index);
            him.Ui.sayDeleted(task);
        } catch (TaskDoesNotExistException e) {
            him.Ui.say(e.getMessage());
        }
    }

    static TaskList getList() {
        return list;
    }


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
