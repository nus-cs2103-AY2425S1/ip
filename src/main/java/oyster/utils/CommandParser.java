package oyster.utils;

import oyster.commands.*;
import oyster.exceptions.DateFormatException;
import oyster.exceptions.TaskFieldException;

import java.util.Scanner;

public class CommandParser {
    public static Command parse(String line) {
        Scanner scanner = new Scanner(line);
        String input = scanner.next().toLowerCase();

        Command command;

        switch (input) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            try {
                if (!scanner.hasNext()) {
                    throw new TaskFieldException("Description");
                }

                if (input.equals("todo")) {
                    command = new ToDoCommand(scanner.nextLine());
                } else if (input.equals("deadline")) {
                    command = new DeadlineCommand(scanner.nextLine());
                } else {
                    command = new EventCommand(scanner.nextLine());
                }
            } catch (TaskFieldException e) {
                command = new ErrorCommand("Please provide the " + e.field + " field!");
            } catch (DateFormatException e) {
                command = new ErrorCommand("Please input in (dd/mm/yyyy) format!");
            } catch (Exception e) {
                command = new ErrorCommand(e.getMessage());
            }
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            if (!scanner.hasNextInt()) {
                command = new ErrorCommand("Please input a valid task number!");
                break;
            }

            int index = scanner.nextInt() - 1;
            if (input.equals("mark")) {
                command = new MarkCommand(index);
            } else if (input.equals("unmark")) {
                command = new MarkCommand(index);
            } else {
                command = new DeleteCommand(index);
            }

            break;
        default:
            command = new ErrorCommand("Oh no! I'm afraid I don't understand...");
        }

        scanner.close();

        return command;
    }
}
