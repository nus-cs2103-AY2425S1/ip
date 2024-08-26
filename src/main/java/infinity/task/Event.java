package infinity.task;

import infinity.infinityexception.InfinityException;

/**
 * This class is the event task that the bot will recognise and manage.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for the Event class.
     * @param description The description of the event
     * @throws InfinityException If the format of the event is wrong
     */
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

    /**
     * 2nd Constructor for the Event class.
     * @param isDone The status of the event
     * @param description The description of the event
     * @param from The starting date of the event
     * @param to The ending date of the event
     * @throws InfinityException If the format of the event is wrong
     */
    public Event(boolean isDone, String description, String from, String to) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.from = from;
        this.to = to;
        this.setTypeOfTask("E");
    }

    /**
     * Parses the save-file format of the event
     * @param delimiter The delimiter to separate the fields
     * @return The save-file format of the event
     */
    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s%s%s", 
                Character.toString(this.typeOfTask), delimiter, 
                this.isDone ? "1" : "0", delimiter, 
                this.from, delimiter, 
                this.to, delimiter, 
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s, to: %s)", 
                Character.toString(this.typeOfTask), this.isDone ? "X" : " ", this.description, this.from, this.to);
    }
}