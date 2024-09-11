package mediell;

import mediell.exception.IncorrectDateFormatException;
import mediell.exception.IncorrectIndexException;
import mediell.exception.IncorrectInstructionFormatException;
import mediell.exception.NotEnoughParametersException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

/** Represents the Ui of the project. */
public class Ui {

    public Ui() {
    }

    public String printGreeting() {
        return "Hello! I'm Mediell!\nWhat can I do for you? :)";
    }

    public String printFarewell() {
        return "Bye. Hope to see you again soon! :(";
    }

    public String getMessage(Instruction instruction, TaskList taskList)
            throws IncorrectIndexException, IncorrectInstructionFormatException {
        switch (instruction.getInstructionType()) {
            case MARK:
                assert instruction.getIndex() != -1;
                return "Nice! I've marked this task as done:\n" + taskList.getTask(instruction.getIndex());
            case DELETE:
                assert instruction.getTask() != null;
                return "Ok, I will remove the task:\n" + instruction.getTask();
            case INSERT:
                assert instruction.getTask() != null;
                return "Got it! I've added this task: \n" + instruction.getTask();
            case SEARCH:
                return taskList.displayFoundList(instruction.getSearch());
            case UNMARK:
                assert instruction.getIndex() != -1;
                return "Ok, I've marked this task as not done yet:\n" + taskList.getTask(instruction.getIndex());
        }
        throw new IncorrectInstructionFormatException();
    }

    public String handleError(Exception e) {
        if (e instanceof IncorrectIndexException) {
            return "OOPS!! The number provided is incorrect";
        } else if (e instanceof IncorrectDateFormatException) {
            return "OOPS!! Incorrect date time format provided use YYYY-MM-DD";
        } else if (e instanceof IncorrectInstructionFormatException) {
            return "Sorry :( I'm confused at what I have to do";
        } else if (e instanceof NotEnoughParametersException) {
            return "OOPS!! Not enough inputs were provided";
        }
        return "OOPS!! An unknown error has occurred :(";
    }
}
