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

    private enum Command {
        BYE("bye") {
            @Override
            public void run() {
                try {
                    STORAGE.save(tasks);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                UI.printExit();
                System.exit(0);
            }
        },
        LIST("list") {
            @Override
            public void run() {
                if (tasks.isEmpty()) {
                    UI.printWithFormat("You have not added any tasks yet.");
                } else {
                    UI.printWithFormat(tasks.toString());
                }
            }
        },
        TODO("todo") {
            @Override
            public void run() {
                if (argument.isBlank()) {
                    throw new MissingArgumentException("description of the todo");
                }
                Task task = new Todo(argument.strip());
                tasks.add(task);
                UI.printWithFormat("added: " + task);
            }
        },
        DEADLINE("deadline") {
            @Override
            public void run() {
                int byIndex = argument.lastIndexOf("/by ");
                if (byIndex == -1) {
                    throw new MissingArgumentException("'by' argument to add a deadline");
                }
                String desc = argument.substring(0, byIndex).strip();
                if (desc.isBlank()) {
                    throw new MissingArgumentException("description of the deadline");
                }

                String by = argument.substring(byIndex + 4).strip();
                Task task = new Deadline(
                        desc,
                        parseDateTime(by)
                );
                tasks.add(task);
                UI.printWithFormat("added: " + task);
            }
        },
        EVENT("event") {
            @Override
            public void run() {
                int fromIndex = argument.lastIndexOf("/from ");
                int toIndex = argument.lastIndexOf("/to ");
                if (fromIndex == -1 || toIndex == -1) {
                    throw new MissingArgumentException("'from' and 'to' arguments to add an event");
                } else {
                    Task task = getTask(fromIndex, toIndex);
                    tasks.add(task);
                    UI.printWithFormat("added: " + task);
                }
            }

            private static Task getTask(int fromIndex, int toIndex) {
                String desc = fromIndex < toIndex
                                ? argument.substring(0, fromIndex)
                                : argument.substring(0, toIndex);
                desc = desc.strip();
                String from = fromIndex < toIndex
                                ? argument.substring(fromIndex + 6, toIndex)
                                : argument.substring(fromIndex + 6);
                from = from.strip();
                String to = fromIndex < toIndex
                                ? argument.substring(toIndex + 4)
                                : argument.substring(toIndex + 4, fromIndex);
                to = to.strip();
                if (desc.isBlank()) {
                    throw new MissingArgumentException("description of the event");
                }
                return new Event(desc, parseDateTime(from), parseDateTime(to));
            }
        },
        MARK("mark") {
            @Override
            public void run() {
                if (argument.isBlank()) {
                    throw new MissingArgumentException("index of the task that you want to mark");
                }

                int i;
                try {
                    i = Integer.parseInt(argument) - 1;
                } catch (NumberFormatException e) {
                    throw new IncorrectArgumentException("an integer");
                }
                if (i < 0 || i >= tasks.size()) {
                    throw new IncorrectArgumentException("a valid index");
                }

                tasks.get(i).mark();
                UI.printWithFormat("Nice! I've marked this task as done:\n  " +
                        tasks.get(i));
            }
        },
        UNMARK("unmark") {
            @Override
            public void run() {
                if (argument.isBlank()) {
                    throw new MissingArgumentException("index of the task that you want to unmark");
                }
                int i;
                try {
                    i = Integer.parseInt(argument) - 1;
                } catch (NumberFormatException e) {
                    throw new IncorrectArgumentException("an integer");
                }
                if (i < 0 || i >= tasks.size()) {
                    throw new IncorrectArgumentException("a valid index");
                }
                tasks.get(i).unmark();
                UI.printWithFormat("OK, I've marked this task as not done yet:\n" +
                        tasks.get(i));
            }
        },
        DELETE("delete") {
            @Override
            public void run() {
                if (argument.isBlank()) {
                    throw new MissingArgumentException("index of the task that you want to delete");
                }
                int i;
                try {
                    i = Integer.parseInt(argument) - 1;
                } catch (NumberFormatException e) {
                    throw new IncorrectArgumentException("an integer");
                }
                if (i < 0 || i >= tasks.size()) {
                    throw new IncorrectArgumentException("a valid index");
                }
                Task task = tasks.remove(i);
                UI.printWithFormat("OK, I've removed this task:\n" +
                        task + "\n" +
                        "Now you have " + tasks.size() + " tasks in the list.");
            }
        },
        RESET("reset") {
            @Override
            public void run() {
                tasks = new TaskList();
                UI.printWithFormat("OK, I've removed all your tasks.");
            }
        },
        CATCH_ALL("") {
            @Override
            public void run() {
                throw new UnknownCommandException();
            }
        };

        public final String CMD;
        public abstract void run();

        Command(String cmd) {
            CMD = cmd;
        }
    }

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
