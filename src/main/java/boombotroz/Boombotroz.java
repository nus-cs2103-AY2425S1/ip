package boombotroz;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class where execution occurs.
 */
public class Boombotroz {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private List<String> commands = new ArrayList<>();

    /**
     * Creates necessary objects and existence of text file to be written into.
     *
     * @param filePath file path to text file.
     */
    public Boombotroz(String filePath) {
        System.out.println("Hello! I'm Boombotroz"
                + "\nWhat can I do for you?");
        commands.add("list");
        commands.add("mark");
        commands.add("unmark");
        commands.add("delete");
        commands.add("find");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
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
     * Generates a response for the user's chat message.
     *
     * @param input command entered by user.
     * @return Response by Boombotroz.
     */
    public String run(String input) {
        while (!input.equals("bye")) {
            String[] result = input.split(" ");
            String command = result[0];
            try {
                if (!commands.contains(command)) {
                    ui.isInvalid();
                }
                if (command.equals("list")) {
                    return parser.printList(ui, taskList);
                } else if (command.equals("mark")) {
                    return parser.markTask(taskList, input, storage, ui);
                } else if (command.equals("unmark")) {
                    return parser.unmarkTask(taskList, input, storage, ui);
                } else if (command.equals("delete")) {
                    return parser.deleteTask(taskList, input, storage, ui);
                } else if (command.equals("find")) {
                    return parser.findTask(taskList, input, ui);
                } else if (command.equals("todo")) {
                    return parser.createToDo(taskList, input, storage, ui);
                } else if (command.equals("deadline")) {
                    return parser.createDeadline(taskList, input, storage, ui);
                } else if (command.equals("event")) {
                    return parser.createEvent(taskList, input, storage, ui);
                }
            } catch (BoomException e) {
                return e.getMessage();
            } catch (FileNotFoundException e) {
                return "File not found";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "Bye. Hope to see you again soon!";
    }
}

