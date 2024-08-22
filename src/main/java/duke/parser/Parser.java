package duke.parser;

import duke.exceptions.DukeException;
import java.util.Scanner;
import duke.tasks.TaskList;
public class Parser {

    private final Scanner scan;

    public Parser(Scanner scan) {
        this.scan = scan;
    }

    public boolean handleUserInput() {
        TaskList taskList = TaskList.getInstance();
        while (scan.hasNext()) {
            String[] input = preprocess(scan.nextLine());
            String cmd = input[0], args = input[1];
            try {
                switch (cmd) {
                    case "bye":
                        scan.close();
                        return false;
                    case "list":
                        taskList.printTaskList();
                        break;
                    case "mark":
                        taskList.mark(args);
                        break;
                    case "unmark":
                        taskList.unmark(args);
                        break;
                    case "delete":
                        taskList.deleteTask(args);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        taskList.createTask(cmd, args);
                        break;
                    default:
                        throw new DukeException("Invalid command provided.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    private String[] preprocess(String input) {
        String[] raw = input.split(" ", 2);
        String[] split = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            split[i] = raw[i].trim();
        }
        split[0] = split[0].toLowerCase();
        return split;
    }
}
