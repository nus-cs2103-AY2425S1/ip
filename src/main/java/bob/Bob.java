package bob;

import bob.command.Command;
import bob.exception.*;

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
    private static final Parser PARSER = new Parser();
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
        } catch (IOException | BobException e) {
            UI.printError(e.getMessage());
            tasks = new TaskList();
        }

        boolean isExit = false;
        while (!isExit) {
            String input = UI.readInput();
            try {
                Command command = PARSER.parse(input);
                command.execute(tasks, UI, STORAGE);
                isExit = command.isExit();
            } catch (BobException e) {
                UI.printError(e.getMessage());
            }
        }
    }
}
