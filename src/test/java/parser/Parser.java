package parser;

import commands.*;
import exceptions.HandsomeException;
import task.DeadlineTest;
import task.EventTest;
import task.ToDoTest;

public class Parser {
    public static Command parse(String input) throws HandsomeException {
        String[] specifiedInput = input.split(" ", 2); // for mark 2 etc.
        String command = specifiedInput[0];

        switch (command) {
            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "unmark":
                return new UnmarkCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "todo":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                return new AddCommand(new ToDoTest(specifiedInput[1]));

            case "deadline":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                String[] taskAndTime = specifiedInput[1].split(" /by ", 2);
                return new AddCommand(new DeadlineTest(taskAndTime[0], taskAndTime[1]));

            case "event":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                String[] fromAndTo = specifiedInput[1].split(" /from | /to ");
                return new AddCommand(new EventTest(fromAndTo[0], fromAndTo[1], fromAndTo[2]));

            case "delete":
                return new DeleteCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "bye":
                return new ExitCommand();

            default:
                System.out.println("Hey handsome, unfortunately I don't know what you are saying :( ");
                throw new HandsomeException("Try better next time with right commands.");
        }
    }
}
