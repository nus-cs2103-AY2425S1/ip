package Boombotroz;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Boombotroz {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList task_list;

    public Boombotroz(String file_path) {
        System.out.println("Hello! I'm Boombotroz" +
                "\nWhat can I do for you?");

        ui = new Ui();
        storage = new Storage(file_path);
        task_list = new TaskList();
        parser = new Parser();

        try {
            storage.printTasks(task_list);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    parser.getList(task_list);
                    input = scanner.nextLine();

                } else if (input.startsWith("mark ")) {
                    parser.markTask(task_list, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("unmark ")) {
                    parser.unmarkTask(task_list, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("delete ")) {
                    parser.deleteTask(task_list, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("todo ")) {
                    parser.toDoTask(task_list, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("deadline ")) {
                    parser.deadlineTask(task_list, input, storage, ui);
                    input = scanner.nextLine();

                } else if (input.startsWith("event ")) {
                    parser.eventTask(task_list, input, storage, ui);
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
        new Boombotroz("../data.txt").run();

    }

}

