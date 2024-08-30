package tecna;

import java.util.Scanner;

public class CommandScanner {
    private Scanner scanner;
    private String input;

    public CommandScanner() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the latest input scanned by the scanner
     */
    public String getInput() {
        return this.input;
    }

    public CommandType getRequest() {
        this.input = this.scanner.nextLine().trim();
        String[] input_words = this.input.split(" ");
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input_words[0].equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input_words[0].equalsIgnoreCase("mark")) {
            return CommandType.MARK;
        } else if (input_words[0].equalsIgnoreCase("unmark")) {
            return CommandType.UNMARK;
        } else if (input_words[0].equalsIgnoreCase("delete")) {
            return CommandType.DELETE;
        } else if (input_words[0].equalsIgnoreCase("todo")) {
            if (input_words.length <= 1) {
                return CommandType.TODO_WRONG_FORMAT;
            } else {
                return CommandType.TODO;
            }
        } else if (input_words[0].equalsIgnoreCase("deadline")) {
            return CommandType.DEADLINE;
        } else if (input_words[0].equalsIgnoreCase("event")) {
            return CommandType.EVENT;
        } else {
            return CommandType.INVALID;
        }
    }

    public int markIndex() {
        String[] input_words = this.input.split(" ");
        return Integer.parseInt(input_words[1]) - 1;
    }

    public void close() {
        this.scanner.close();
    }

}
