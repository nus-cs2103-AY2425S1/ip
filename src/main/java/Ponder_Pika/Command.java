package Ponder_Pika;

public class Command {
    public enum Action {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE}

    private final Action act;
    private final Object data;

    public Command(Action act, Object data) {
        this.act = act;
        this.data = data;
    }

    public Action getAction() {
        return this.act;
    }

    public Object getData() {
        return this.data;
    }
}
