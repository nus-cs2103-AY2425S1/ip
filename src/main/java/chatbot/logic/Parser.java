package chatbot.logic;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.DeadlineArgsException;
import chatbot.exception.EmptyArgsException;
import chatbot.exception.EventArgsException;
import chatbot.exception.InputException;
import chatbot.exception.InvalidCommandException;
import chatbot.exception.MissingDeadlineByException;
import chatbot.exception.MissingEventFromException;
import chatbot.exception.MissingEventToException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

/**
 * Represents the parser, converting strings into chatbot instructions
 * Either processes the user's inputs, or processes file inputs into instructions for the chatbot
 */
public class Parser {
    /**
     * Reads the encoded strings present in the data file, and processes it into a suitable Task
     *
     * @param taskString The encode string read from the file
     * @return A Task object that represents the encoded string
     * @throws IOException Exception thrown if string format is unknown
     */
    public static Task parseFileLine(String taskString) throws IOException {
        // TODO error handling for if data file is corrupted
        // splits the taskString into an array of token string based on the | character
        String[] tokens = taskString.split("\\|");
        boolean isDone = Integer.parseInt(tokens[1]) == 1;
        String title = tokens[2];
        return switch (tokens[0]) {
        case "T" -> new Todo(title, isDone);
        case "D" -> new Deadline(title, LocalDateTime.parse(tokens[3]), isDone);
        case "E" -> new Event(title, LocalDateTime.parse(tokens[3]), LocalDateTime.parse(tokens[4]), isDone);
        default -> throw new IOException();
        };
    }

    /**
     * Takes in user instructions, parses it into suitable instructions, and executes them
     *
     * @param s User's instruction string
     * @param taskList TaskList object as encapsulated by the Bobby class
     * @param storage Storage object as encapsulated by the Bobby class
     * @return A string representation of the chatbot's reply to the command
     * @throws InputException Exception thrown if instruction is not of the correct format
     */
    public static String processInput(String s, TaskList taskList, Storage storage) throws InputException {
        // splits the command into 2 strings - The command string, and the arguments string
        String[] inputArr = s.split(" ", 2);
        String command = inputArr[0];
        switch (command) {
        case "bye" -> {
            return "Bye. Hope to see you again soon!";
        }
        case "list" -> {
            return taskList.listTasks();
        }
        case "mark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            String str = taskList.mark(idx, true);
            storage.writeToFile(taskList);
            return str;
        }
        case "unmark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            String str = taskList.mark(idx, false);
            storage.writeToFile(taskList);
            return str;
        }
        case "find" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            String query = inputArr[1];
            return taskList.find(query);
        }
        case "delete" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            String str = taskList.remove(idx);
            storage.writeToFile(taskList);
            return str;
        }
        case "todo" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            Task newTask = new Todo(inputArr[1]);
            String str = taskList.add(newTask);
            storage.writeToFile(taskList);
            return str;
        }
        case "deadline" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/by ")) {
                throw new MissingDeadlineByException();
            }
            String[] args = inputArr[1].split(" /by ", 2);
            if (args.length <= 1) {
                throw new DeadlineArgsException();
            }
            String name = args[0];
            String deadline = args[1];
            if (name.trim().isEmpty() || deadline.trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Task newTask = new Deadline(name, LocalDateTime.parse(deadline, formatter));
                String str = taskList.add(newTask);
                storage.writeToFile(taskList);
                return str;
            } catch (DateTimeParseException e) {
                return "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format";
            }
        }
        case "event" -> {
            // Error handling
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/from ")) {
                throw new MissingEventFromException();
            }
            if (!inputArr[1].contains(" /to")) {
                throw new MissingEventToException();
            }
            String[] args = inputArr[1].split(" /from ", 2);
            if (args.length <= 1) {
                throw new EventArgsException();
            }

            String name = args[0];
            String[] fromTo = args[1].split(" /to ", 2);
            if (fromTo.length <= 1) {
                throw new EventArgsException();
            }

            String from = fromTo[0];
            String to = fromTo[1];

            if (name.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                throw new EmptyArgsException();
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Task newTask = new Event(name, LocalDateTime.parse(from, formatter),
                        LocalDateTime.parse(to, formatter));
                String str = taskList.add(newTask);

                storage.writeToFile(taskList);
                return str;
            } catch (DateTimeParseException e) {
                return "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format";
            }
        }
        default -> throw new InvalidCommandException();
        }
    }
}
