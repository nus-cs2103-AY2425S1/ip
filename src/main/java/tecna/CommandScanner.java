package tecna;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandScanner {
    private final Scanner SCANNER;
    private String input;
    private int inputIndex;
    private Task inputTask;

    public CommandScanner() {
        this.SCANNER = new Scanner(System.in);
    }

    public int getInputIndex() {
        return this.inputIndex;
    }

    public Task getInputTask() {
        return this.inputTask;
    }

    /**
     * Returns the latest input scanned by the scanner
     */
    public String getInput() {
        return this.input;
    }

    public CommandType getRequest() throws NumberFormatException {
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
