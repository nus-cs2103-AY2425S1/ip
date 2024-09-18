package duke.exceptions;

/**
 * This exception is thrown when the user inputs a command that is not recognized by the system.
 */
public class InvalidInputException extends Exception {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("I'm sorry, but I don't know what that means\n")
                .append("Refer to the list of available commands: \n")
                .append("   1. Create a new task without deadline: todo [name] \n")
                .append("   2. Create a new task with deadline: deadline [name] /by [time] \n")
                .append("   3. Create a new task with duration: event [name] /from [time] /to [time] \n")
                .append("       NOTE: time in dd/MM/yyyy HH:mm or dd/MM/yyyy \n")
                .append("   4. Mark a task as complete: mark [index of task] \n")
                .append("   5. Mark a task as incomplete: unmark [index of task] \n")
                .append("   6. Delete a task from the list: delete [index of task] \n")
                .append("   7. Search for a task: find [name of task] \n")
                .append("   8. Display the current tasks in list: list");
        return stringBuilder.toString();
    }
}
