public class InvalidInputException extends DelphiException {
    public InvalidInputException() {
        super("the input you have provided me is not formatted correctly. Please give me an input starting with todo, deadline or event");
    }
}
