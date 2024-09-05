package boombotroz;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class where execution occurs.
 */

public class Boombotroz {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Creates necessary objects and existence of text file to be written into.
     *
     * @param filePath file path to text file.
     */
    public Boombotroz(String filePath) {
        System.out.println("Hello! I'm Boombotroz"
                + "\nWhat can I do for you?");

        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();

        try {
            storage.printTasks(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }

    }

    /**
     * Loads input to return corresponding output
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    parser.getList(taskList);
                    input = scanner.nextLine();

                } else if (input.startsWith("mark ")) {
                    parser.markTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("unmark ")) {
                    parser.unmarkTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("delete ")) {
                    parser.deleteTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("find ")) {
                    parser.findTask(taskList, input, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("todo ")) {
                    parser.toDoTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("deadline ")) {
                    parser.deadlineTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("event ")) {
                    parser.eventTask(taskList, input, storage, ui);
                    input = scanner.nextLine();

                } else {
                    ui.invalidInput();

                }
            } catch (BoomException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();

            } catch (FileNotFoundException e) {
                System.out.println("File not found");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip", "src", "main");
        new Boombotroz(path.toString() + "/data.txt").run();

    }

}

