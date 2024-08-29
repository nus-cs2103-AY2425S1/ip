import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Elon {
    Action action = new Action();

    private Storage storage;
    private ArrayList<Task> list;

    public Elon(Storage storage) {
        this.storage = storage;
    }
    public String[] nextInput(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
    private void Run() throws ElonException {
        this.list = storage.loadFile();
        action.greet();
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                action.listTasks(list);
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                action.markTask(index, list);
                try {
                    storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println(e);
                }
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                action.unmarkTask(index, list);
                try {
                    storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println(e);
                }
                inputArr = this.nextInput(scanner);
                continue;
            } else if (inputArr[0].equals("delete")) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                action.deleteTask(index, list);
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
                        action.addToDo(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("deadline")) {
                    try {
                        action.addDeadline(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else if (inputArr[0].equals("event")) {
                    try {
                        action.addEvent(inputArr, list);
                        storage.saveFile(list);
                    } catch (ElonException | IOException e) {
                        System.out.println(e);
                    }
                } else {
                    throw new ElonException("Error. Invalid command.");
                }
                action.endAddTask(list.size());
                inputArr = this.nextInput(scanner);
            }
        }
        scanner.close();
        action.exit();
    }
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./Data.txt");
            Elon elon = new Elon(storage);
            elon.Run();
        } catch (ElonException | IOException e) {
            System.out.println(e);
        }
    }
}
