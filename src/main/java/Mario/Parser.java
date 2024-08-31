package Mario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final Mario mario;
    private final Ui ui;

    public Parser(Mario mario) {
        this.mario = mario;
        this.ui = new Ui();
    }

    /**
     * Handles user inputs.
     * @param input User input.
     * @throws IOException If input is invalid.
     */
    public void parseCommand(String input) throws IOException {
        String commandString = input.split(" ")[0];
        CommandType command;
        try {
            command = CommandType.fromString(commandString);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    ui.betweenLines("Oh no! Idk what that means."));
            return;
        }

<<<<<<< HEAD
        switch(command) {
            case LIST:
                handleListTasks();
                break;
            case EVENT:
                handleEventTask(input);
                break;
            case TODO:
                handleAddTodoTask(input);
                break;
            case DEADLINE:
                handleAddDeadlineTask(input);
                break;
            case MARK:
                handleMarkTask(input);
                break;
            case UNMARK:
                handleUnmarkTask(input);
                break;
            case REMOVE:
                handleRemoveTask(input);
                break;
            case BYE:
                handleBye();
                break;
            case FIND:
                handleFind(input);
                break;
            default:
                handleInvalidInput();
=======
        switch (command) {
        case LIST:
            handleListTasks();
            break;
        case EVENT:
            handleEventTask(input);
            break;
        case TODO:
            handleAddTodoTask(input);
            break;
        case DEADLINE:
            handleAddDeadlineTask(input);
            break;
        case MARK:
            handleMarkTask(input);
            break;
        case UNMARK:
            handleUnmarkTask(input);
            break;
        case REMOVE:
            handleRemoveTask(input);
            break;
        case BYE:
            handleBye();
            break;
        default:
            handleInvalidInput();
>>>>>>> branch-A-CodingStandard
        }
    }

    /**
     * Calls {@link Mario#listTasks()}.
     */
    public void handleListTasks() {
        Mario.listTasks();
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addTodoTask(String)}.
     * @param input
     * @throws IOException If input is invalid
     */
    public void handleAddTodoTask(String input) throws IOException {
        String[] parts = input.split(" ", 2);
        if (parts.length == 1) {
            System.out.println(
                    ui.betweenLines(
                            "Mamma mia! Please include a valid todo name."));
        } else {
            String taskName = parts[1];
            Mario.addTodoTask(taskName);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addDeadlineTask(String, String)}.
     * @param input
     */
    public void handleAddDeadlineTask(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println(
                    ui.betweenLines(
                            "Mamma mia! "
                                    + "Please include task name and deadline."));
            return;
        }
        String content = parts[1];
        try {
            String[] deadlineParts = content.split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new ArrayIndexOutOfBoundsException();
            }

            String name = deadlineParts[0].trim();
            String deadline = deadlineParts[1].trim();
            LocalDateTime formattedDeadline = parseDateTime(deadline);

            if (formattedDeadline != null) {
                Mario.addDeadlineTask(name, formattedDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")));
            } else {
                Mario.addDeadlineTask(name, deadline);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    ui.betweenLines("Mamma mia! There's no deadline."
                    + " \nAdd one with the /by keyword!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addEventTask(String, String, String)}.
     * @param input
     */
    public void handleEventTask(String input) {
        if (input.split("\\s+").length == 1) {
            System.out.println(ui.betweenLines(
                    "Mamma mia! The event name cannot be empty!\n"));

        } else {
            String info;
            String name;
            String duration;
            String start;
            String end;
            try {
                info = input.substring(6);
                name = info.split("/from")[0];
                duration = info.split("/from")[1];
                start = duration.split("/to")[0].trim();

                end = duration.split("/to")[1].trim();

                LocalDateTime formattedStart = parseDateTime(start);
                LocalDateTime formattedEnd = parseDateTime(end);

                if (formattedEnd != null && formattedStart != null) {
                    Mario.addEventTask(name, formattedStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")),
                            formattedEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")));
                } else {
                    Mario.addEventTask(name, start, end);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ui.betweenLines(
                        "Mamma mia! There's no start and/or end time.\n"
                        + "     Add them with the /from and /to keywords!\n"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#markTask(int)}.
     * @param input
     */
    public void handleMarkTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            System.out.println(ui.betweenLines(
                    "Please enter a valid number!"));
            return;
        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                System.out.println(ui.betweenLines(
                        "Please enter an integer between 0 and "
                            + (mario.getNumTasks() + 1)));
                return;
            }
            mario.markTask(number);
        } catch (NumberFormatException e) {
            System.out.println(ui.betweenLines(
                    "Mamma mia! Please enter a valid integer."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#unmarkTask(int)}.
     * @param input
     */
    public void handleUnmarkTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            System.out.println(ui.betweenLines(
                    "Please enter a valid number!"));
            return;
        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                System.out.println(ui.betweenLines("Please enter an integer between 0 and "
                        + (mario.getNumTasks() + 1)));
                return;
            }
            mario.unmarkTask(number);
        } catch (NumberFormatException e) {
            System.out.println(
                    ui.betweenLines(
                            "Mamma mia! Please enter a valid integer."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#removeTask(int)}
     * @param input
     */
    public void handleRemoveTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            System.out.println(ui.betweenLines("Please enter a valid number!"));
            return;
        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                System.out.println(ui.betweenLines("Please enter an integer between 0 and "
                        + (mario.getNumTasks() + 1)));
                return;
            }
            mario.removeTask(number);
        } catch (NumberFormatException e) {
            System.out.println(ui.betweenLines("Mamma mia! Please enter a valid integer."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls {@link Mario#bye()}
     */
    public void handleBye() {
        mario.bye();
    }

    /**
     * Handles invalid input and prompts new user input.
     */
    public void handleInvalidInput() {
        System.out.println(ui.betweenLines("I don't understand what that means :("));
    }

    /**
     * Converts valid date time string to type LocalDateTime.
     * @param dateTimeString In the format yyyy-MM-dd HHmm.
     * @return
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Calls {@link Mario#findTask(String)}.
     * @param input
     */
    public void handleFind(String input) {
        String keyword = input.split(" ", 2)[1];
        mario.findTask(keyword);

    }

}
