public class ToDoTask extends Task {
    public ToDoTask(String input) throws EmptyToDoDescriptionException {
        String name = input.substring(4).trim();

        if (name.isEmpty()) {
            throw new EmptyToDoDescriptionException("The description of a todo task cannot be empty.");
        }

        this.name = name;
        this.taskTypeSymbol = "T";

    }

}
