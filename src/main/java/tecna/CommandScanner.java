package tecna;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Scans and processes the user's input.
 * An <code>inputIndex</code> is the index of the corresponding task if the command is <i>mark, unmark, or delete</i>.
 * An <code>inputTask</code> is the task required to be added via <i>todo, deadline, or event</i> commands.
 *
 * @author DennieDan.
 */
public class CommandScanner {
    private final Scanner SCANNER;
    private String input;
    private int inputIndex;
    private Task inputTask;
    private String keyword;

    public CommandScanner() {
        this.SCANNER = new Scanner(System.in);
    }

    public int getInputIndex() {
        return this.inputIndex;
    }

    public Task getInputTask() {
        return this.inputTask;
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Returns the latest input scanned by the scanner
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Gets the user's input and parses into the chatbot's valid type command.
     * @return the corresponding command type in <code>CommandType enum</code>.
     */
    public CommandType getRequest() {
        this.input = this.SCANNER.nextLine().trim();
        String[] input_words = this.input.split(" ");
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input_words[0].equalsIgnoreCase("mark")) {
            return this.generateIndex() ? CommandType.MARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("unmark")) {
            return this.generateIndex() ? CommandType.UNMARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("delete")) {
            return this.generateIndex() ? CommandType.DELETE : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("find")) {
            this.keyword = input_words[1];
            return CommandType.FIND;
        } else if (input_words[0].equalsIgnoreCase("todo")) {
            if (input_words.length <= 1) {
                return CommandType.TODO_WRONG_FORMAT;
            } else {
                String[] description = input.split("todo");
                this.inputTask = new ToDo(description[1].trim());
                return CommandType.TODO;
            }
        } else if (input_words[0].equalsIgnoreCase("deadline")) {
            try {
                String[] description = input.split("deadline | /by");
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.inputTask = new Deadline(description[1].trim(), LocalDateTime.parse(description[2].trim(), pattern));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                return CommandType.DEADLINE_WRONG_FORMAT;
            }
            return CommandType.DEADLINE;
        } else if (input_words[0].equalsIgnoreCase("event")) {
            try {
                String[] description = input.split("event | /from | /to ");
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.inputTask =  new Event(description[1].trim(), LocalDateTime.parse(description[2].trim(), pattern), LocalDateTime.parse(description[3].trim(), pattern));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                return CommandType.EVENT_WRONG_FORMAT;
            }
            return CommandType.EVENT;
        } else {
            return CommandType.INVALID;
        }
    }

    /**
     * Reads requests from GUI and return a command type.
     * This is different from getRequest() as there is no CommandScanner used.
     *
     * @param input A request typed by the user.
     * @return a CommandType value.
     */
    public CommandType readRequest(String input) {
        this.input = input;
        String[] input_words = this.input.split(" ");
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input_words[0].equalsIgnoreCase("mark")) {
            return this.generateIndex() ? CommandType.MARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("unmark")) {
            return this.generateIndex() ? CommandType.UNMARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("delete")) {
            return this.generateIndex() ? CommandType.DELETE : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("find")) {
            this.keyword = input_words[1];
            return CommandType.FIND;
        } else if (input_words[0].equalsIgnoreCase("todo")) {
            if (input_words.length <= 1) {
                return CommandType.TODO_WRONG_FORMAT;
            } else {
                String[] description = input.split("todo");
                this.inputTask = new ToDo(description[1].trim());
                return CommandType.TODO;
            }
        } else if (input_words[0].equalsIgnoreCase("deadline")) {
            try {
                String[] description = input.split("deadline | /by");
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.inputTask = new Deadline(description[1].trim(), LocalDateTime.parse(description[2].trim(), pattern));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                return CommandType.DEADLINE_WRONG_FORMAT;
            }
            return CommandType.DEADLINE;
        } else if (input_words[0].equalsIgnoreCase("event")) {
            try {
                String[] description = input.split("event | /from | /to ");
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.inputTask =  new Event(description[1].trim(), LocalDateTime.parse(description[2].trim(), pattern), LocalDateTime.parse(description[3].trim(), pattern));
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                return CommandType.EVENT_WRONG_FORMAT;
            }
            return CommandType.EVENT;
        } else {
            return CommandType.INVALID;
        }
    }

    /**
     * Generates the required index and stores in the <code>inputIndex</code> attribute.
     * @return <code>true</code> if the parsing process is success and <code>false</code> otherwise.
     */
    public boolean generateIndex() {
        String[] input_words = this.input.split(" ");
        try {
            this.inputIndex = Integer.parseInt(input_words[1]) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void close() {
        this.SCANNER.close();
    }

}
