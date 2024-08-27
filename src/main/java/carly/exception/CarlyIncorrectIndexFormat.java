package carly.exception;

public class CarlyIncorrectIndexFormat extends CarlyException {
    public CarlyIncorrectIndexFormat() {
        super("Oh no!, index should be a number. Please reenter your message");
    }

}