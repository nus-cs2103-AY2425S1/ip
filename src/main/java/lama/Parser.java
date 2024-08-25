package lama;

import lama.command.*;
import lama.task.Deadline;
import lama.task.Event;
import lama.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String command) throws LamaException {
        String[] words = command.split(" ", 2);

        switch (words[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (words.length < 2) {
                throw new LamaException("Please specify the number that wanted to be marked as done!");
            }

            return new MarkCommand(Integer.parseInt(words[1]) - 1);

        case "unmark":
            if (words.length < 2) {
                throw new LamaException("Please specify the number that wanted to be unmarked!");
            }

            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);

        case "todo":
            if (words.length < 2) {
                throw new LamaException("Please specify the description of TODO!");
            }

            return new AddCommand(new Todo(words[1]));

        case "deadline":
            if (words.length < 2) {
                throw new LamaException("Please specify the description of deadline!");
            }

            String[] half = words[1].split(" /by ");

            if (half.length < 2) {
                throw new LamaException("Please specify the date of deadline in the format of:\n" +
                        "deadline [description] /by [date]");
            }

            try {
                LocalDate date = LocalDate.parse(half[1]);
                return new AddCommand(new Deadline(half[0], date));
            } catch (DateTimeException e) {
                throw new LamaException("Date format should follow yyyy-MM-dd");
            }

        case "event":
            if (words.length < 2) {
                throw new LamaException("Please specify the description of event in the format of:\n" +
                        "event [description] /from [start time] /to [end time]");
            }

            String[] first = words[1].split(" /from ");

            if (first.length < 2) {
                throw new LamaException("Please specify the start time of event in the format of:\n" +
                        "event [description] /from [start time] /to [end time]");
            }

            String[] time = first[1].split(" /to ");

            if (time.length < 2) {
                throw new LamaException("Please specify the start time of event in the format of:\n" +
                        "event [description] /from [start time] /to [end time]");
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime from = LocalDateTime.parse(time[0], formatter);
                LocalDateTime to = LocalDateTime.parse(time[1], formatter);
                return new AddCommand(new Event(first[0], from, to));
            } catch (DateTimeException e) {
                throw new LamaException("Date time format should follow yyyy-MM-dd HHmm");
            }

        case "delete":
            if (words.length < 2) {
                throw new LamaException("Please specify the number that wanted to delete!");
            }

            return new DeleteCommand(Integer.parseInt(words[1]) - 1);

        default:
            throw new LamaException("Sorry, I don't know what you want to do!\n" +
                    "You can either choose to use:\n" +
                    "1. todo [Your TODO]\n" +
                    "2. deadline [Your TODO] /by [date of deadline]\n" +
                    "3. event [Your event] /from [start time] /to [end time]\n" +
                    "4. list\n" +
                    "5. mark [number of todo in the list]\n" +
                    "6. unmark [number of todo in the list]\n" +
                    "7. bye");
        }


    }
}
