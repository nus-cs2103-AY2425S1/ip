package bob;

import bob.exception.*;
import bob.task.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Bob {
    private static final DateTimeFormatter INPUT_FORMATTER =
            new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("d[d]/M[M][/uuuu][ HHmm]"))
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("'{'dd-MMM-uuuu HHmm'}'");
    private static final Storage STORAGE = new Storage("data/Bob.txt");
    private static final Ui UI = new Ui();
    private static String argument = "";
    private static TaskList tasks;

    public static LocalDateTime parseDateTime(String string) {
        switch (string) {
        case "now":
            return LocalDateTime.now();
        case "tmr":
        case "tomorrow":
            return LocalDateTime.now().plusDays(1);
        default:
            try {
                return LocalDateTime.from(INPUT_FORMATTER.parse(string));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return OUTPUT_FORMATTER.format(dateTime);
    }

    public static void main(String[] args) {
        UI.printGreeting();

        try {
            tasks = STORAGE.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BobException e) {
            UI.printError(e.getMessage());
            tasks = new TaskList();
        }

        while (true) {
            boolean executed = false;
            String[] input = UI.readInput().split(" ", 2);
            String command = input[0];
            argument = input.length == 1 ? "" : input[1];
            try {
                for (Command c : Command.values()) {
                    if (command.equals(c.CMD)) {
                        c.run();
                        executed = true;
                        break;
                    }
                }
                if (!executed) {
                    Command.CATCH_ALL.run();
                }
            } catch (BobException e) {
                UI.printError(e.getMessage());
            }
        }
    }
}
