package oyster.utils;

import oyster.commands.*;
import oyster.exceptions.ParseException;
import oyster.tasks.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static TaskList parseTaskList(String contents) throws ParseException {
        ArrayList<Command> commands = new ArrayList<Command>();

        TaskList taskList = new TaskList();

        try {
            Scanner scanner = new Scanner(contents);

            while (scanner.hasNextLine()) {
                Task task = TaskParser.parse(scanner.nextLine());

                taskList.insert(task);
            }

            scanner.close();
        } catch (Exception e) {
            throw new ParseException();
        }

        return taskList;
    }

    public static String composeTaskList(TaskList taskList) {
        StringBuilder builder = new StringBuilder();

        for (Task task : taskList.getItems()) {
            builder.append(TaskParser.compose(task)).append("\n");
        }

        return builder.toString();
    }

    public static Command parseCommand(String line) {
        return CommandParser.parse(line);

//        switch (command) {
//        case "bye":
//            display.output("See you again! " + Oyster.CHATBOT_EMOJI);
//            isRunning = false;
//            break;
//        case "list":
//            if (taskList.isEmpty()) {
//                display.output("Oops, nothing to see here!");
//            } else {
//                display.output(new String[]{
//                        "Here is your current list!",
//                        taskList.toString()
//                });
//            }
//            break;
//        case "mark":
//            if (!scanner.hasNextInt()) {
//                display.output("Please input a valid task number!");
//                break;
//            }
//
//            try {
//                int markIndex = scanner.nextInt() - 1;
//                taskList.mark(markIndex);
//                display.output(new String[]{
//                        "Well done on completing the task!",
//                        "\t" + taskList.getTask(markIndex).toString()
//                });
//            } catch (RuntimeException e) {
//                display.output("oyster.tasks.Task number does not exist!");
//            }
//            break;
//        case "unmark":
//            if (!scanner.hasNextInt()) {
//                display.output("Please input a valid task number!");
//                break;
//            }
//
//            try {
//                int unmarkIndex = scanner.nextInt() - 1;
//                taskList.unmark(unmarkIndex);
//                display.output(new String[]{
//                        "I have unmarked the task!",
//                        "\t" + taskList.getTask(unmarkIndex).toString()
//                });
//            } catch (RuntimeException e) {
//                display.output("oyster.tasks.Task number does not exist!");
//            }
//            break;
//        case "todo":
//        case "deadline":
//        case "event":
//            Task.TASK_TYPES type = Task.TASK_TYPES.TODO;
//            if (command.equals("deadline")) {
//                type = Task.TASK_TYPES.DEADLINE;
//            } else if (command.equals("event")) {
//                type = Task.TASK_TYPES.EVENT;
//            }
//
//            try {
//                if (!scanner.hasNext()) {
//                    throw new TaskFieldException("Description");
//                }
//
//                display.output(new String[]{
//                        "Alright, the task has been added!",
//                        "\t" + taskList.insert(type, scanner.nextLine()).toString(),
//                        String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
//                });
//            } catch (TaskFieldException e) {
//                display.output("Please provide the " + e.field + " field!");
//            }
//            break;
//        case "delete":
//            if (!scanner.hasNextInt()) {
//                display.output("Please input a valid task number!");
//                break;
//            }
//
//            try {
//                int deleteIndex = scanner.nextInt() - 1;
//                String deletedTask = taskList.pop(deleteIndex).toString();
//                display.output(new String[]{
//                        "I have deleted the task!",
//                        "\t" + deletedTask,
//                        String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
//                });
//                break;
//            } catch (RuntimeException e) {
//                display.output("oyster.tasks.Task number does not exist!");
//            }
//            break;
//        default:
//            display.output("Oh no! I'm afraid I don't understand...");
//        }
    }
}
