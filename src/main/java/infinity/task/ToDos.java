package infinity.task;

import infinity.infinityexception.InfinityException;

public class ToDos extends Task {
    public ToDos(String description) {
        this.setDescription(description);
        this.setTypeOfTask("T");
    }

    public ToDos(boolean isDone, String description) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.setTypeOfTask("T");
    }
}