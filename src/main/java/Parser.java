import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private Ui ui;
    private TaskList list;
    private Scanner scanner;
    private String[] inputArr;
    private Storage storage;

    public Parser(Ui ui, TaskList list, Scanner scanner, String[] inputArr, Storage storage) {
        this.ui = ui;
        this.list = list;
        this.scanner = scanner;
        this.inputArr = inputArr;
        this.storage = storage;
    }
    public String[] nextInput(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
    public void Parse() throws ElonException{
        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                ui.listTasks(list);
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                ui.markTask(index, list);
                try {
                    storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println(e);
                }
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                ui.unmarkTask(index, list);
                try {
                    storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println(e);
                }
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("delete")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                ui.deleteTask(index, list);
                try {
                    storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println(e);
                }
                inputArr = this.nextInput(scanner);
                continue;
            } else {
                if (inputArr[0].equals("todo")) {
                    try {
                        ui.addToDo(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("deadline")) {
                    try {
                        ui.addDeadline(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("event")) {
                    try {
                        ui.addEvent(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else {
                    throw new ElonException("Error. Invalid command.");
                }
                ui.endAddTask(list.listSize());
                inputArr = this.nextInput(scanner);
            }
        }
        scanner.close();
        ui.exit();
    }

}
