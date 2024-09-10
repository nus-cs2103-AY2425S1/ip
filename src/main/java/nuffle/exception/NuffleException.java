package nuffle.exception;

public class NuffleException extends Exception {
    public NuffleException(String message) {
        super(message);
    }

    public static NuffleException noDesc(String input) {
        return new NuffleException("Something's not right! The description of a " + input + " command cannot be empty.");
    }

    public static NuffleException unknown() {
        return new NuffleException("Hmmm... Seems like you have entered a command which I don't seem to understand...");
    }

    public static NuffleException checkEventParams() {
        return new NuffleException("There is missing input after /to or /from.");
    }
    public static NuffleException checkDeadlineParams() {
        return new NuffleException("There is missing input after /by.");
    }

    public static NuffleException checkDeadlineFormat() {
        return new NuffleException("There is missing /by in your command.");
    }

    public static NuffleException checkEventFormat() {
        return new NuffleException("There is missing /to or /from in your command... or both.");
    }

    public static NuffleException checkDateTimeFormat() {
        return new NuffleException("Please check the date time format for your input [format: yyyy-mm-dd hhmm.");
    }

    public static NuffleException checkLoanParams() {
        return new NuffleException("There is missing input after /B /L /amt or /due.");
    }

    public static NuffleException checkLoanFormat() {
        return new NuffleException("There is missing /B /L /amt or /due in your command.");
    }
}
