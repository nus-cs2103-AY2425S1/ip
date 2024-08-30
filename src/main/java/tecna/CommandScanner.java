package tecna;

import java.util.Scanner;

public class CommandScanner {
    private Scanner scanner;
    private String input;
    private int inputIndex;

    public CommandScanner() {
        this.scanner = new Scanner(System.in);
    }

    public int getInputIndex() {
        return this.inputIndex;
    }

    /**
     * Returns the latest input scanned by the scanner
     */
    public String getInput() {
        return this.input;
    }

    public CommandType getRequest() throws NumberFormatException {
        this.input = this.scanner.nextLine().trim();
        String[] input_words = this.input.split(" ");
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input_words[0].equalsIgnoreCase("mark")) {
            return this.markIndex() ? CommandType.MARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("unmark")) {
            return this.markIndex() ? CommandType.UNMARK : CommandType.INDEX_WRONG_FORMAT;
        } else if (input_words[0].equalsIgnoreCase("delete")) {
            return this.markIndex() ? CommandType.DELETE : CommandType.INDEX_WRONG_FORMAT;
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

    public boolean markIndex() {
        String[] input_words = this.input.split(" ");
        try {
            this.inputIndex = Integer.parseInt(input_words[1]) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void close() {
        this.scanner.close();
    }

}
