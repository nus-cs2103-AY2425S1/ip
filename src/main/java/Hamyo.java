import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Hamyo {

    private final UI ui;
    private boolean active;
    private final TaskList tasks;
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Hamyo() {
        this.ui = new UI();
        this.active = true;
        this.tasks = new TaskList();

        this.ui.greet();
    }

    public static void main(String[] args) {
        try {
            Hamyo hamyo = new Hamyo();
            Storage storage = new Storage("./savedTasks.txt");
            Scanner scanner = new Scanner(System.in);

            storage.loadData(hamyo, hamyo.tasks);
            while (hamyo.active) {
                try {
                    String command = scanner.nextLine();
                    String commandType = command.split(" ")[0];
                    String commandFields = command.substring(commandType.length());
                    switch (commandType) {
                    case "todo":
                        hamyo.tasks.addTask(hamyo, TaskType.TODO, commandFields);
                        break;
                    case "deadline":
                        hamyo.tasks.addTask(hamyo, TaskType.DEADLINE, commandFields);
                        break;
                    case "event":
                        hamyo.tasks.addTask(hamyo, TaskType.EVENT, commandFields);
                        break;
                    case "list":
                        hamyo.tasks.listTasks(hamyo);
                        break;
                    case "listDate":
                        hamyo.tasks.listTasksByDate(hamyo, commandFields);
                        break;
                    case "mark":
                        hamyo.tasks.markTask(hamyo, commandFields);
                        break;
                    case "unmark":
                        hamyo.tasks.unmarkTask(hamyo, commandFields);
                        break;
                    case "delete":
                        hamyo.tasks.deleteTask(hamyo, commandFields);
                        break;
                    case "bye":
                        hamyo.active = false;
                        hamyo.ui.terminate();
                        break;
                    default:
                        throw new HamyoException("Invalid Command!");
                    }
                    storage.saveData(hamyo, hamyo.tasks);
                } catch (HamyoException e) {
                    System.out.println(e.toString());
                    UI.printLine();
                }
            }
            scanner.close();
        } catch (HamyoException e) {
            System.out.println(e.toString());
            UI.printLine();
        }
    }
}
