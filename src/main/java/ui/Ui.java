package ui;

public class Ui {

    private final String line = "_______________________________________________________\n";

    public Ui() {}
    /**
     * Prints the greeting to the user.
     * @return the greeting to the user.
     */
    public String greet() {
        return line() + "Hello! I'm Meeks! Your friendly chatbot!\n" + "What can I do for you?\n";
    }
    /**
     * Prints the goodbye message to the user.
     * @return the goodbye message to the user.
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!\n" + line();
    }
    /**
     * Prints the unknown command message to the user.
     * @return the unknown command message to the user.
     */
    public String invalidCommand() {
        return "Oh no! You have input an unknown command!\n" + line();
    }

    /**
     * Prints the error message when there is an incorrect format of the
     * command for deadline tasks.
     * @return the error message when there is an incorrect format of the
     * command for deadline tasks.
     */
    public String DeadlineOutOfBoundsExceptionMessage() {
        return "Incorrect format of adding deadline tasks. " +
                "Use '/by to specify the deadline after the task description";
    }

    /**
     * Prints the error message when there is a parsing error with the Deadline Task command
     * relating to the Format of the Date and Time.
     * @return the error message when there is a parsing error with the Deadline Task command
     * relating to the Format of the Date and Time.
     */
    public String DeadlineDateTimeParseExceptionMessage() {
        return "Please input the correct deadline format /by yyyy-MM-dd XXXX <- Time";
    }
    /**
     * Prints the error message when there is an incorrect format of the
     * command for event tasks.
     * @return the error message when there is an incorrect format of the
     * command for event tasks.
     */
    public String EventOutOfBoundsExceptionMessage() {
        return "Incorrect format of adding event tasks. " +
                "Use '/from to specify the start and /to to specify the end " +
                "after the task description\n";
    }
    /**
     * Prints the error message when there is a parsing error with the Event Task command
     * relating to the Format of the Date and Time.
     * @return the error message when there is a parsing error with the Event Task command
     * relating to the Format of the Date and Time.
     */
    public String EventDateTimeParseExceptionMessage() {
        return "Incorrect format of adding event tasks timings. /from yyyy-MM-dd XXXX <- Time" +
                "and /to yyyy-MM-dd XXXX <- Time\n";
    }

    /**
     * Prints the error message when there is an invalid task number being input into the command.
     * @return a string error message when there is an invalid task number being input into the command.
     */

    public String NumberFormatExceptionMessage() {
        return "Please enter a valid task number\n";
    }
    /**
     * A line to separate messages.
     * @return a string message of the segmentation between messages.
     */
    public String line() {
        return this.line;
    }
    /**
     * A message to denote successful addition of the task to the TaskList.
     * @return a string to affirm the user.
     */
    public String affirm() {
        return "Got it. I've added this task: \n";
    }

    public String updateSuccess(int index) {
        return String.format("Successfully updated task %d", index);
    }
}
