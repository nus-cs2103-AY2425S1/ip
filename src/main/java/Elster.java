import com.sun.source.tree.CaseTree;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Elster {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Elster(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);

        try {
            taskList = new TaskList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean byeSentinel = true;
        String input;
        Scanner myScanner = new Scanner(System.in);

        String logo = "___________.__            __\n" +
                "\\_   _____/|  |   _______/  |_  ___________\n" +
                " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n" +
                " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n" +
                "/_______  /|____/____  > |__|  \\___  >__|\n" +
                "        \\/           \\/            \\/";
        System.out.println(logo);
        ui.printLine();

        System.out.println("    Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("    How can I help you today :|");
        ui.printLine();

        while (byeSentinel) {
            input = myScanner.nextLine().strip();
            if (input.equals("bye")) {
                byeSentinel = false;

            } else if (input.equals("list")) {
                taskList.printList();

            } else if (input.startsWith("deadline")) {
                DeadlineTask task = DeadlineTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }
                taskList.writeToFile();

            } else if (input.startsWith("event")) {
                EventTask task = EventTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }
                taskList.writeToFile();

            } else if (input.startsWith("delete")) {
                taskList.deleteTask(Integer.parseInt(input.substring(7).strip()));

            } else if (input.startsWith("todo")) {
                ToDoTask task = ToDoTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                }
                taskList.writeToFile();

            } else if (input.startsWith("mark")) {
                taskList.markTaskAsDone(Integer.parseInt(input.substring(5,6)));
                taskList.writeToFile();

            } else if (input.startsWith("unmark")) {
                taskList.unmarkTaskAsUndone(Integer.parseInt(input.substring(7,8)));
                storage.writeToFile(taskList);

            } else {
                ui.nonsenseErrorMessage();
            }
        }

        ui.goodbyeMessage();
    }

    public static void main(String[] args) {
        Path dataDir = Paths.get( "data");
        new Elster(dataDir).run();
    }


}
