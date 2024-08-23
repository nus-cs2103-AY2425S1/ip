/**
 * This class represents a Task, super class of Todo, Event and Deadline
 * @author Gan Ren Yick (A0276246X)
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @param text input by user
     * @return an instant of task which can be either Deadline, Todo or Event type depend on text
     * @throws InvalidInputException is thrown when user input is not recognised
     * @throws ArgumentMissingException is thrown when argument(s) of task(Event and Deadline) is missing
     * @throws EmptyDescriptionException is thrown when description of tasks is missing
     */
    public static Task of(String text) throws InvalidInputException, ArgumentMissingException, EmptyDescriptionException {
        String action = text.split(" ")[0];
        return switch (action) {
            case "todo" -> new Todo(Task.getDescription(text));
            case "deadline" -> new Deadline(Task.getDescription(text), Task.findArgumentOf("by", text));
            case "event" -> new Event(Task.getDescription(text),
                    Task.findArgumentOf("from", text),
                    Task.findArgumentOf("to", text));
            default -> throw new InvalidInputException("Im not smart enough to understand that :(");
        };
    }

    private static String findArgumentOf(String parameter, String text) throws InvalidInputException{
        String[] fragments = text.split("/");
        for (int i = 1; i < fragments.length; i++) {
            String[] args = fragments[i].split(" ");
            if (args[0].equals(parameter)) {
                args[0] = "";
                String out = String.join(" ", args);
                if (!out.isEmpty()) {
                    return out.substring(1);
                }
            }
        }
        throw new InvalidInputException("OOPSIEE where is your /" + parameter + " argument");
    }

    private static String getDescription(String text) throws EmptyDescriptionException{
        String[] arr = text.split("/")[0].split(" ");
        arr[0] = "";
        String out = String.join(" ", arr);
        if (out.isEmpty()) {
            throw new EmptyDescriptionException(text.split(" ")[0] + " what?? Wheres your description?");
        }
        return out.substring(1);
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * mark the task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}