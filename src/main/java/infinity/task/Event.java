package infinity.task;

import infinity.infinityexception.InfinityException;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description) throws InfinityException {
        try {
            this.setDescription(description.split(" /from ")[0]);
            this.from = description.split(" /from ")[1].split(" /to ")[0];
            this.to = description.split(" /to ")[1];
            this.setTypeOfTask("E");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InfinityException("oops, I think your format is a little wrong");
        }
    }

    public Event(boolean isDone, String description, String from, String to) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.from = from;
        this.to = to;
        this.setTypeOfTask("E");
    }

    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s%s%s", 
                this.typeOfTask, delimiter, 
                this.isDone ? "1" : "0", delimiter, 
                this.from, delimiter, 
                this.to, delimiter, 
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s, to: %s)", 
                this.typeOfTask, this.isDone ? "X" : " ", this.description, this.from, this.to);
    }
}