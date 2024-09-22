package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;

/**
 * Represents an abstract command in the task management application.
 * This class serves as a base for all concrete command implementations.
 */
public abstract class Command {
    protected String[] arguments;
    private boolean isExit;

    /**
     * Constructs a new Command with the specified exit status.
     *
     * @param isExit true if this command should cause the program to exit, false otherwise
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks if this command should cause the program to exit.
     *
     * @return true if this command should cause the program to exit, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    public String fetchDescription() throws TrackieException {

        StringBuilder descFetcher = new StringBuilder();
        int ptr = 1;
        while (ptr < arguments.length) {
            if (arguments[ptr].charAt(0) == '/') {
                break;
            }
            descFetcher.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (descFetcher.isEmpty()) {
            throw new TrackieException("Description cannot by empty brudda.");
        }
        return descFetcher.substring(0, descFetcher.length() - 1);
    }

    public String fetchDeadline() throws TrackieException {
        int ptr = 1;
        int marker = arguments.length;
        StringBuilder deadlineFetcher = new StringBuilder();
        while (ptr < arguments.length) {
            if (arguments[ptr].equals("/by")) {
                marker = ptr;
            }
            if (ptr > marker) {
                deadlineFetcher.append(arguments[ptr]).append(' ');
            }
            ptr++;
        }
        if (deadlineFetcher.isEmpty()) {
            throw new TrackieException("Bro gimme a deadline man");
        }
        return deadlineFetcher.substring(0, deadlineFetcher.length() - 1);
    }

    public String fetchStartTime() throws TrackieException {
        int ptr = 1;
        int marker = arguments.length;
        StringBuilder startTimeFetcher = new StringBuilder();
        while (ptr < arguments.length) {
            if (arguments[ptr].equals("/to")) {
                break;
            }
            if (arguments[ptr].equals("/from")) {
                marker = ptr;
            }
            if (ptr > marker) {
                startTimeFetcher.append(arguments[ptr]).append(' ');
            }
            ptr++;
        }
        if (startTimeFetcher.isEmpty()) {
            throw new TrackieException("Deadline cannot be empty");
        }
        return startTimeFetcher.substring(0, startTimeFetcher.length() - 1);
    }

    public String fetchEndTime() throws TrackieException {
        int ptr = 1;
        int marker = arguments.length;
        StringBuilder endTimeFetcher = new StringBuilder();
        while (ptr < arguments.length) {
            if (arguments[ptr].equals("/to")) {
                marker = ptr;
            }
            if (ptr > marker) {
                endTimeFetcher.append(arguments[ptr]).append(' ');
            }
            ptr++;
        }
        if (endTimeFetcher.isEmpty()) {
            throw new TrackieException("Deadline cannot be empty");
        }
        return endTimeFetcher.substring(0, endTimeFetcher.length() - 1);
    }
    /**
     * Executes the command. This method must be implemented by concrete subclasses.
     *
     * @param tasklist the TaskList to operate on
     * @param storage the storage system to interact with
     */
    public abstract String execute(TaskList tasklist, Storage storage) throws TrackieException;
}
