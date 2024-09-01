package nen.utils;

import nen.exceptions.ArgumentMissingException;
import nen.exceptions.DateTimeFormatIncorrectException;
import nen.exceptions.EmptyDescriptionException;
import nen.exceptions.InvalidInputException;
import nen.tasks.Task;

/**
 * This class represents a parser deals with making sense of the user command
 * @author Gan Ren Yick (A0276246X)
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a parser which determined action of user input and make changes
     * to taskList and give reaction to ui to print out
     * @param taskList of tasks
     * @param ui user interface to print out answer to user input
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses given input and make changes to taskList and give reaction to ui to print out
     * @param input by user
     * @return a boolean which determined if nen2 should continue read user input
     */
    public boolean continueParsing(String input) {
        int arg;
        String action = input.split(" ")[0];
        String react = "";
        try {
            switch(action) {
            case "bye":
                return false;
            case "mark":
                arg = getIndex(input);
                taskList.get(arg - 1).markAsDone();
                react += "Nice! I've marked this task as done:"
                        + "\n"
                        + taskList.get(arg - 1).toString() + "\n";
                break;
            case "unmark":
                arg = getIndex(input);
                taskList.get(arg - 1).markAsNotDone();
                react += "OK, I've marked this task as not done yet:\n"
                        + taskList.get(arg - 1).toString() + "\n";
                break;
            case "delete":
                arg = getIndex(input);
                react += "Noted. I've removed this task:\n"
                        + taskList.get(arg - 1).toString() + "\n";
                taskList.remove(arg - 1);
                react += "Now you have " + taskList.size() + " tasks in the list." + "\n";
                break;
            case "list":
                react += "Here are the tasks in your list:\n"
                        + taskList.toString();
                break;
            case "find":
                try {
                    int count = 1;
                    react += "Here are the matching tasks in your list:\n";
                    for (Task t : taskList.findTaskWithKeyword(input.split(" ")[1])) {
                        react += count + "." + t.toString() + "\n";
                        count++;
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArgumentMissingException("find what?");
                }
            default:
                Task t = Task.of(input);
                taskList.add(t);
                react += "Got it. I've added this task: \n" + t + "\n"
                        + "Now you have " + taskList.size()
                        + " tasks in the list." + "\n";
            }
            ui.print(react);
        } catch (InvalidInputException
                 | ArgumentMissingException
                 | EmptyDescriptionException
                 | DateTimeFormatIncorrectException e) {
            ui.print(e.getMessage() + "\n");
        }

        return true;
    }

    /**
     * Gets the index of task for the command to execute on
     * @param text command input by user
     * @return index of task
     * @throws ArgumentMissingException when command doesn't contain an index
     * @throws InvalidInputException when the index in command is invalid
     */
    private int getIndex(String text) throws ArgumentMissingException, InvalidInputException {
        String[] arr = text.split(" ");
        if (arr.length < 2) {
            throw new ArgumentMissingException(arr[0] + " which one??");
        }
        try {
            int out = Integer.parseInt(arr[1]);
            if (out > taskList.size()) {
                throw new ArgumentMissingException("Helloo there's only " + taskList.size() + " tasks\n"
                        + "How to " + arr[0] + " " + out + "th task");
            }
            return out;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Huh?? \"" + arr[1] + "\" is not a number lah!");
        }
    }
}
