public class NotEnoughArgumentsException extends Exception {
    public TaskType type;
    public NotEnoughArgumentsException(String type, String message) {
        super(message);
        if (type.equalsIgnoreCase("deadline")) {
            this.type = TaskType.DEADLINE;
        } else if (type.equalsIgnoreCase("event")) {
            this.type = TaskType.EVENT;
        } else if (type.equalsIgnoreCase("todo")) {
            this.type = TaskType.TODO;
        }
    }

    public NotEnoughArgumentsException(String type) {
        new NotEnoughArgumentsException(type, "not enough arguments");
    }

    @Override
    public String toString() {
        return switch (type) {
            case DEADLINE ->
                    "Yo Homieee, there are not enough arguments for deadline!\ndeadline should be of this format: deadline <description> /by <date>";
            case EVENT ->
                    "Yo Homieee, there are not enough arguments for event!\nevent should be of this format: event <description> /from <date> /to <date>";
            case TODO ->
                    "Yo Homieee, there are not enough arguments for todo!\ntodo should be of this format: todo <description>";
        };
    }
}
