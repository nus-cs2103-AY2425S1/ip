public class NotEnoughArguments extends Exception {
    public TaskType type;
    public NotEnoughArguments(String type, String message) {
        super(message);
        if (type.equalsIgnoreCase("deadline")) {
            this.type = TaskType.DEADLINE;
        } else if (type.equalsIgnoreCase("event")) {
            this.type = TaskType.EVENT;
        } else if (type.equalsIgnoreCase("todo")) {
            this.type = TaskType.TODO;
        }
    }

    public NotEnoughArguments() {
        super("not enough arguments");
    }

    @Override
    public String toString() {
        switch (type) {
            case DEADLINE:
                return "Yo Homieee, there are not enough arguments for deadline!\n deadline should be of this format: deadline <description> /by <date>";
            case EVENT:
                return "Yo Homieee, there are not enough arguments for event!\n event should be of this format: event <description> /from <date> /to <date>";
            case TODO:
                return "Yo Homieee, there are not enough arguments for todo!\n todo should be of this format: todo <description>";
        }
        return "Yo Homieee, there are not enough arguments!";
    }
}
