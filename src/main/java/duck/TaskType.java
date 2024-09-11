package duck;

public enum TaskType {
    TODO(Command.TODO.toString()),
    DEADLINE(Command.DEADLINE.toString()),
    EVENT(Command.EVENT.toString());

    private final String name;

    private TaskType(String name) {
        this.name = name;
    }

    public boolean equalsName(String name) {
        return this.name.equals(name);
    }

    public String toString() {
        return this.name;
    }
}
