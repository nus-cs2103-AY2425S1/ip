package carly.exception;

public class CarlyNoTaskDescription extends CarlyException {
    public CarlyNoTaskDescription() {
        super("Oops. You forgot to enter a task description. Please reenter your message.");
    }

}
