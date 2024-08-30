package lexi.command;
public enum Commands {
        TODO("Adds a todo task."),
        DEADLINE("Adds a task with a deadline."),
        EVENT("Adds an event with a start and end time."),
        MARK("Marks a task as done."),
        UNMARK("Unmarks a task."),
        DELETE("Deletes a task."),
        LIST("Lists all tasks."),
        FIND("Finds all related tasks"),
        BYE("Exits the application.");

        private final String description;

        Commands(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return this.name() + ": " + this.description;
        }

        public static String printCommands() {
            StringBuilder commands = new StringBuilder();
            for (Commands command : Commands.values()) {
                commands.append(command + System.lineSeparator());
            }
            return commands.toString();
        }
}
