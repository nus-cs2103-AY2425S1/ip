package rainy.database;

/**
 * Processes and makes sense of user inputs. This class takes a user input and stores it as an
 * array of strings for easy retrieval.
 * This class also contains an <code>int</code> object, count to represent the significance of the user input.
 * If count is not a -1, tasks will either be marked, unmarked, or deleted.
 */
public class Parser {
    private String message;
    private int count;
    private String[] input;
    private String[] splitByTask;

    /**
     * Constructs a new <code>Parser</code> object.
     */
    public Parser() {
        this.message = "";
        this.count = 0;
        this.input = new String[100000];
        this.splitByTask = new String[100000];
    }

    public String getMessage() {
        return this.message;
    }

    public int getCount() {
        return this.count;
    }

    public String[] getInput() {
        return this.input;
    }

    public String[] getSplitByTask() {
        return this.splitByTask;
    }

    public void firstInput(String userInput) {
        /**
         * Splits the user input two ways. The first using the space character as delimiter, and the second using the
         * slash character.
         * Retrieves the first word of user input and matches it with the words mark, unmark, or delete.
         * This method then looks for the second word of user input, and tries to parse it as an <code>Integer</code>
         * representing the task number.
         * Failing which, this method throws an NumberFormatException. This method then assigns count the value of -1.
         * If the length of the array split using the space character delimiter is not 2 when mark, unmark, or delete is detected
         * as the first word, count will be assigned -1 too.
         * If the above conditions are not satisfied, then the user input is valid for the mark, unmark, and delete actions.
         *
         * @param userInput               This parameter represents each user input.
         * @throws NumberFormatException  This method catches the exception when a <code>String</code> cannot be converted into an <code>Integer</code>.
         */
        this.input = userInput.split(" ");
        this.splitByTask = userInput.split("/");
        this.message = this.input[0];
        if ((this.message.equals("mark") || this.message.equals("unmark")
                || this.message.equals("delete")) && this.input.length == 2) {
            try {
                this.count = Integer.parseInt(this.input[1]);
            } catch (Exception e) {
                this.count = -1;
            }
        } else {
            this.count = -1;
        }
    }

    public Instructions enumOperator(String newMessage) {
        /**
         * Represents a <code>String</code> object as an <code>Instructions</code> object.
         *
         * @param newMessage                 This method converts the first word of user input into an <code>Instructions</code> object.
         * @throws IllegalArgumentException  When a user input is not found under <code>Instructions</code> enum, this method catches an IllegalArgumentException and assigns it Instructions.INVALID.
         */
        Instructions instruction;
        try {
            instruction = Instructions.valueOf(newMessage.toUpperCase());
        } catch(Exception e) {
            instruction = Instructions.INVALID;
        }
        return instruction;
    }
}