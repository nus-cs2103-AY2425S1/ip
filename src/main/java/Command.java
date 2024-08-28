enum Command {
    TODO("Adds a todo task."),
    DEADLINE("Adds a task with a deadline."),
    EVENT("Adds an event with a start and end time."),
    MARK("Marks a task as done."),
    UNMARK("Unmarks a task."),
    DELETE("Deletes a task."),
    LIST("Lists all tasks."),
    BYE("Exits the application.");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name() + ": " + this.description;
    }

    public static void printCommands() {
        for (Command command : Command.values()) {
            System.out.println(command);
        }
    }
}
