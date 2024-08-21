public class InvalidInputException extends Exception {
    public InvalidInputException() {
      super();
    }
    public String getMessage() {
        return "Sorry~ I can not understand what you said just now.";
    }

}
