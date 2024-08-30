package BobChatBot;

import BobChatBot.Command.*;
import BobChatBot.Exceptions.EmptyArgumentException;
import BobChatBot.Exceptions.InvalidInputException;
import BobChatBot.Exceptions.InvalidTaskNumberException;
import BobChatBot.Exceptions.MissingArgumentException;
import BobChatBot.Tasks.Deadlines;
import BobChatBot.Tasks.EventTask;
import BobChatBot.Tasks.ToDos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public static Command parseCommand(String UserCommand, int maxSize) throws EmptyArgumentException, MissingArgumentException, InvalidInputException, InvalidTaskNumberException {
        Scanner scanner = new Scanner(UserCommand);
        String input = scanner.next();
        Command command;
        switch (input) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            int index = scanner.nextInt() - 1;
            if (!(index < maxSize) || index < 0) {
                throw new InvalidTaskNumberException();
            }
            command = new MarkCommand(index);
            break;
        case "unmark":
            int ind = scanner.nextInt() - 1;
            if (!(ind < maxSize) || ind < 0) {
                throw new InvalidTaskNumberException();
            }
            command = new UnmarkCommand(ind);
            break;
        case "todo":
            command = new AddTaskCommand(Parser.newToDo(scanner.nextLine().trim()));
            break;
        case "deadline":
            command = new AddTaskCommand(Parser.newDeadline(scanner.nextLine().trim()));
            break;
        case "event":
            command = new AddTaskCommand(Parser.newEvent(scanner.nextLine().trim()));
            break;
        case "delete":
            int inde = scanner.nextInt() - 1;
            if (!(inde < maxSize) || inde < 0) {
                throw new InvalidTaskNumberException();
            }
            command = new DeleteCommand(inde);
            break;
        default:
            throw new InvalidInputException();
        }
        return command;
    }

    public static ToDos newToDo(String input) throws EmptyArgumentException {
        if (input.isEmpty()) {
            throw new EmptyArgumentException("description", "todo");
        }
        return new ToDos(input);
    }

    public static Deadlines newDeadline(String input) throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^.*/by.*$")) {
            throw new MissingArgumentException("by", "deadline");
        } else if (!input.matches("^\\S{1}.+/by.*$")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^\\S{1}.+ /by \\S{1}.+$")) {
            throw new EmptyArgumentException("by", "deadline");
        }

        String[] inputs = input.split("/by", 2);
        String[] dateTime = inputs[1].trim().split(" ");
        if (dateTime.length == 2) {
            return new Deadlines(inputs[0].trim(),
                    LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        }
        return new Deadlines(inputs[0].trim(), LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
    }

    public static EventTask newEvent(String input) throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*$")) {
            throw new MissingArgumentException("from", "event");
        } else if (!input.matches("^\\S{1}.+/from.*$")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*/to.*$")) {
            throw new MissingArgumentException("to", "event");
        } else if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to.*$")) {
            throw new EmptyArgumentException("from", "event");
        } else if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to \\S{1}.+$")) {
            throw new EmptyArgumentException("to", "event");
        }

        String[] inputs = input.split("/from", 2);
        String[] dates = inputs[1].split("/to", 2);
        String[] startDateTime = dates[0].trim().split(" ");
        String[] endDateTime = dates[1].trim().split(" ");
        if (startDateTime.length == 1 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        } else if (startDateTime.length == 1 && endDateTime.length == 2) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        } else if (startDateTime.length == 2 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        }
        return new EventTask(inputs[0].trim(),
                LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }
}
