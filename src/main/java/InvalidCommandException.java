public class InvalidCommandException extends Exception {

    String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }
    public String message() {
        if (message.length() >= 4 && this.message.substring(0, 4).equals("todo")) {
            return "Please specify the ToDo Task";
        } else if (message.length() >= 8 && this.message.substring(0, 8).equals("deadline")) {
            return "Please specify the Deadline Task";
        } else if (message.length() >= 5 && this.message.substring(0, 5).equals("event")) {
            return "Please specify the Event Task";
        }
        return "Tako does not understand this command!";
    }
}
