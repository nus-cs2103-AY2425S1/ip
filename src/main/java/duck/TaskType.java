package duck;

import duck.commands.CommandType;

public enum TaskType {
    TODO(CommandType.TODO.toString()),
    DEADLINE(CommandType.DEADLINE.toString()),
    EVENT(CommandType.EVENT.toString());

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
