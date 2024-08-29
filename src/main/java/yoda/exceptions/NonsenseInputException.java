package yoda.exceptions;

public class NonsenseInputException extends InvalidInputException{
    NonsenseInputException() {
        super("What is the meaning of this...?");
    }
}
