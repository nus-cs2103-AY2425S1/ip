public class Ui {
    private static final String LINE_BREAK = "\n_______________________________________________________________________\n";
    private static final String INDENTATION = "    ";

    public void showGreeting() {
        String greeting = "Hello! I am EDITH, your personal chatbot companion:)" + "\nHow may I assist you?";
        System.out.println(LINE_BREAK + greeting + LINE_BREAK);
    }

    public void showExitMessage() {
        String exitMessage = "It has been my pleasure helping you. Hope to see you again soon!";
        System.out.println(INDENTATION + exitMessage + LINE_BREAK);
    }

    public void showPrompt() {
        System.out.println("Your next instruction: ");
    }

    public void showLineBreak() {
        System.out.println(LINE_BREAK);
    }

    public void showIndentedMessage(String message) {
        System.out.println(INDENTATION + message);
    }

    public void showErrorMessage(String message) {
        System.err.println(INDENTATION + message + LINE_BREAK);
    }

    public void showInvalidIndexError(int index) {
        System.err.println(INDENTATION + "Task " + index + " does not exist. Please enter a valid task number." + LINE_BREAK);
    }

    public void showInvalidTaskError() {
        String string1 = "Invalid task due to missing details which were not provided. ";
        String string2 = "Please provide a valid instruction with the correct relevant details.";
        System.err.println(INDENTATION + string1 + string2 + LINE_BREAK);
    }

    public void showInvalidDateTimeError() {
        String string = "Invalid date/time format. Please use 'day/month/year HHmm' (e.g '13/9/2024 1800').";
        System.err.println(INDENTATION + string + LINE_BREAK);
    }

    public void showInvalidInstructionError() {
        EdithException e = new EdithException("Sorry but that is not an instruction I can execute.");
        System.err.println(INDENTATION + e + LINE_BREAK);
    }
}
