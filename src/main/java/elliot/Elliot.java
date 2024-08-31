package elliot;

import utility.Storage;
import utility.TaskList;
import utility.Ui;
import task.Task;
import task.TodoTask;
import task.DeadlineTask;
import task.EventTask;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.time.format.DateTimeParseException;
import exception.ElliotException;
import command.Command;
import command.CommandType;

public class Elliot {
    private Storage storage;
    private TaskList taskList;

    private Elliot(String filePathString) {
        storage = new Storage(filePathString);
        taskList = storage.loadTaskList();
    }

    public static void main(String[] args) {
        new Elliot("./data/ElliotTaskList.ser").run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Ui.introSay();
        while (isRunning) {
            System.out.print("> ");
            String userInput = scanner.nextLine().strip();
            String[] commandString = stripStrArray(userInput.toLowerCase().split(" ", 2));
            Ui.say("");
            try {
                CommandType commandType = CommandType.parseStringToCommand(commandString[0]);
                Command command = commandType.getCommand();
                command = command.parseArguments(commandString.length < 2 
                        ? "" 
                        : commandString[1]);
                taskList = command.runCommand(taskList, storage);
                isRunning = !(commandType == CommandType.BYE);
            } catch (ElliotException e) {
                continue;
            }
        }
    }

    private String[] stripStrArray(String[] strArray) {
        return Arrays.stream(strArray)
                .map(String::strip)
                .toArray(String[]::new);
    }

}
