package carly.exception;

public class CarlyIndexOutOfBoundsException extends CarlyException {
    public CarlyIndexOutOfBoundsException(Integer taskNum, Integer taskListSize) {
        super("Oh no! we don't have the item number " + taskNum + " in your list! \n" +
                "Please key in your command with list number up to " + taskListSize + " only.");
    }

}
