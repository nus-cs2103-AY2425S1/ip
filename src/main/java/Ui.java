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

    public String invalidDateTimeError() {
        return "Invalid date/time format. Please use 'day/month/year HHmm' (e.g '13/9/2024 1800').";
    }
}
