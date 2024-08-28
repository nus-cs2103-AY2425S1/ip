package oyster.utils;

import oyster.commands.Command;
import oyster.commands.ByeCommand;
import oyster.commands.ListCommand;
import oyster.commands.ToDoCommand;
import oyster.commands.ErrorCommand;
import oyster.commands.DeadlineCommand;
import oyster.commands.EventCommand;
import oyster.exceptions.DateFormatException;
import oyster.exceptions.TaskFieldException;
import oyster.tasks.DeadlineTask;

import java.util.Scanner;
import java.util.zip.DataFormatException;

public class CommandParser {
    public static Command parse(String line) {
        Scanner scanner = new Scanner(line);
        String input = scanner.next().toLowerCase();

        Command command;

        switch (input) {
        case "bye":
            command = new ByeCommand();
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
        default:
            command = new ErrorCommand("Oh no! I'm afraid I don't understand...");
        }

        scanner.close();

        return command;
    }
}
