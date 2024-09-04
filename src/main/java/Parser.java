import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws NoosyException {
        String separated[] = fullCommand.split(" ", 2);
        String firstWord = separated[0];
        String input = separated.length > 1 ? separated[1] : null;

        switch (firstWord) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (input == null) {
                    throw new NoosyException("Which task do you want to mark?");
                }

                return new MarkCommand(Integer.parseInt(input.trim()) - 1);

            case "unmark":
                if (input == null) {
                    throw new NoosyException("Which task do you want to unmark?");
                }

                return new UnmarkCommand(Integer.parseInt(input.trim()) - 1);

            case "delete":
                if (input == null) {
                    throw new NoosyException("Which task do you want to delete?");
                }

                return new DeleteCommand(Integer.parseInt(input.trim()) - 1);

            case "on":
                if (input == null) {
                    throw new NoosyException("You forgot the date! \n" +
                            "It should be formatted as: yyyy-MM-dd");
                }

                try {
                    LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return new OnCommand(date);
                } catch (DateTimeParseException e) {
                    throw new NoosyException("Uh oh! Date should be in the format: yyyy-MM-dd");
                }

            case "todo":
                if (input == null) {
                    throw new NoosyException("Okay surezies but what do you want to do?");
                }

                return new AddCommand(new Todo(input));

            case "deadline":
                if (input == null) {
                    throw new NoosyException("Okay surezies but what do you want to do?");
                }

                String[] withDue = input.split(" /by ");
                if (withDue.length < 2) {
                    throw new NoosyException("I think you forgot the deadline. \n" +
                            "Remember to type in the task and /by the deadline!");
                }

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate deadline = LocalDate.parse(withDue[1], formatter);
                    return new AddCommand(new Deadline(withDue[0], deadline));
                } catch (DateTimeException e) {
                    throw new NoosyException("The date should be formatted as: yyyy-MM-dd");
                }

            case "event":
                if (input == null) {
                    throw new NoosyException("Okay surezies but what do you want to do?");
                }

                String[] withDuration = input.split(" /from | /to ");
                if (withDuration.length < 3) {
                    throw new NoosyException("I think you forgot the duration. \n" +
                            "Remember to type in the task and /from the start /to the end!");
                }

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime from = LocalDateTime.parse(withDuration[1], formatter);
                    LocalDateTime to = LocalDateTime.parse(withDuration[2], formatter);
                    return new AddCommand(new Event(withDuration[0], from, to));
                } catch (DateTimeException e) {
                    throw new NoosyException("Date time format should be: yyyy-MM-dd HHmm");
                }

            default:
                throw new NoosyException("Beep Boop. Noosy is confused. \n" +
                        "Noosy only understands: \n" +
                        "list – listing your tasks \n" +
                        "mark – mark a task as complete \n" +
                        "unmark – unmark a task \n" +
                        "delete – delete a task \n" +
                        "todo – add a task with no time associated \n" +
                        "deadline – add a task with a deadline \n" +
                        "event – add a task with duration \n");
        }
    }
}
