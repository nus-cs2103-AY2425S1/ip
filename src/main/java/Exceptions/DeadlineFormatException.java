package Exceptions;

public class DeadlineFormatException extends BonnieException {

    public DeadlineFormatException() {
        super("Hey there, Bonnie could not process your deadline task! Have you ensured that you keyed in the command correctly?\n" +
              "A valid command is \"deadline buy cat food /by 18th September\", and the format is {taskName} /by {deadline}");
    }
}
