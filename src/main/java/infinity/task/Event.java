package infinity.task;

import infinity.infinityexception.InfinityException;
import infinity.storage.Storage;

/**
 * This class is the event task that the bot will recognise and manage.
 */
public class Event extends Task {
    /** Bot reply for wrong format. */
    private static final String BOT_WRONG_FORMAT = "oops, I think your format is a little wrong\n";
    private final String from;
    private final String to;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @throws InfinityException If the format of the event is wrong.
     */
    public Event(String description) throws InfinityException {
        try {
            this.setDescription(description.split(" /from ")[0]);
            this.from = description.split(" /from ")[1].split(" /to ")[0];
            this.to = description.split(" /to ")[1];
            this.setTypeOfTask(Task.TaskTypes.E);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InfinityException(BOT_WRONG_FORMAT);
        }
    }

    /**
     * 2nd Constructor for the Event class.
     *
     * @param isDone The status of the event.
     * @param description The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     * @throws InfinityException If the format of the event is wrong.
     */
    public Event(boolean isDone, String description, String from, String to) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.from = from;
        this.to = to;
        this.setTypeOfTask(Task.TaskTypes.E);
    }

    /**
     * Parses the save-file format of the event.
     *
     * @param delimiter The delimiter to separate the fields.
     * @return The save-file format of the event.
     */
    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s%s%s",
                this.typeOfTask.toString(), delimiter,
                this.isDone ? Storage.DONE_MARKER : Storage.UNDONE_MARKER, delimiter,
                this.from, delimiter,
                this.to, delimiter,
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s, to: %s)",
                this.typeOfTask.toString(),
                this.isDone ? MARKED_MARKER : UNMARKED_MARKER,
                this.description,
                this.from,
                this.to);
    }
}
