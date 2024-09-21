package task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import exception.DukeException;
import exception.ParseException;
import parser.DateParser;
import parser.EventParser;
import storage.KorolevStorage;

/**
 * Represents a list of KorolevTask
 */
public class KorolevList {
    private static final String OUT_Of_INDEX_ERROR = "The index is out of bound!";
    private static final String LIST_NOTICE = "Here are the tasks in your list:\n";
    private static final String MARK_NOTICE = "Nice! I've marked this task as done:";
    private static final String UNMARK_NOTICE = "OK, I've marked this task as not done yet:";
    private static final String DELETE_NOTICE = "Noted. I've removed this task:";
    private static final String FILTERED_NOTICE = "Here are the matching tasks in your list:";
    private static final String UNTAG_NOTICE = "Noted, here is the untagged task:";
    private static final String STATS_NOTICE = "Here is the summary of tasks: ";
    private static final String FINISHED_MSG = "Number of finished tasks: ";
    private static final String UNFINISHED_MSG = "Number of unfinished tasks: ";

    private ArrayList<KorolevTask> events;

    private static final KorolevStorage storage = new KorolevStorage();

    public KorolevList() {
        this.events = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.events.size(); i++) {
            msg.append((i + 1)).append(". ").append(this.events.get(i)).append("\n");
        }
        return msg.toString();
    }

    private String handleEventAddition(String event) {
        KorolevTask e;
        String name, from, to;
        try {
            name = EventParser.parseName("event", "/from", event);
            from = DateParser.parseFrom(event);
            to = DateParser.parseTo(event);
            e = new KorolevEvent(name, from, to);
            this.events.add(e);
            System.out.println("Got it. I've added this task:");
            System.out.println(e);

            return "Got it. I've added this task:\n" + e
                    + "\nNow you have " + events.size() + " tasks in the list\n";
        } catch (ParseException | DateTimeParseException exp) {
            System.out.println(exp.getMessage());
            return exp.getMessage();
        }
    }

    private String handleTodoAddition(String event) {
        KorolevTask e;
        String name;
        try {
            name = EventParser.parseName("todo", "", event);
            e = new KorolevTodo(name);
            this.events.add(e);
            System.out.println("Got it. I've added this task:");
            System.out.println(e);

            return "Got it. I've added this task:\n" + e
                    + "\nNow you have " + events.size() + " tasks in the list\n";
        } catch (ParseException exp) {
            System.out.println(exp.getMessage());
            return exp.getMessage();
        }
    }

    private String handleDeadlineAddition(String event) {
        KorolevTask e;
        String name, date;
        try {
            name = EventParser.parseName("deadline", "/by", event);
            date = DateParser.parseBy(event);
            e = new KorolevDeadline(name, date);
            this.events.add(e);
            System.out.println("Got it. I've added this task:");
            System.out.println(e);

            return "Got it. I've added this task:\n" + e
                    + "\nNow you have " + events.size() + " tasks in the list\n";
        } catch (ParseException exp) {
            System.out.println(exp.getMessage());

            return exp.getMessage();
        }
    }

    /**
     * Adds new task to the array list of tasks.
     *
     * @param event description of the event.
     * @throws DukeException when failing to add event.
     */
    public String addEvent(String event) throws DukeException {
        String target = event.split("\\s")[0];
        switch (target) {
        case "event" -> {
            return this.handleEventAddition(event);
        }
        case "todo" -> {
            return this.handleTodoAddition(event);
        }
        case "deadline" -> {
            return this.handleDeadlineAddition(event);
        }
        default -> throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Displays the whole list of tasks
     *
     * @return the string representation of task lists
     */
    public String displayList() {
        return LIST_NOTICE + this.toString();
    }

    /**
     * Marks the specific task to be done
     *
     * @param index index of the event.
     * @throws DukeException the index is out of bound.
     */
    public String markEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(OUT_Of_INDEX_ERROR);
        }
        KorolevTask t = this.events.get(index);
        t.markTask();
        System.out.println(MARK_NOTICE);
        System.out.println(t);

        return MARK_NOTICE + "\n" + t;
    }

    /**
     * Tags an task with a tag.
     *
     * @param index index of the task.
     * @param tag name of the tag to be added.
     * @return successful tagging message.
     * @throws DukeException fail to find the task with index.
     */
    public String tagEvent(int index, String tag) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(OUT_Of_INDEX_ERROR);
        }

        KorolevTask t = this.events.get(index);
        t.tag(tag);
        String TAG_NOTICE = "Noted, here is the tagged task:";
        System.out.println(TAG_NOTICE);
        System.out.println(t);

        return TAG_NOTICE + "\n" + t;
    }

    /**
     * Untags a specific event.
     *
     * @param index index of the task in the event list.
     * @return successful untag information.
     * @throws DukeException fail to untag the task.
     */
    public String untagEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(OUT_Of_INDEX_ERROR);
        }

        KorolevTask t = this.events.get(index);
        t.untag();
        System.out.println(UNTAG_NOTICE);
        System.out.println(t);

        return UNTAG_NOTICE + "\n" + t;
    }

    /**
     * Displays the statistical summary of a specific KorolevList.
     *
     * @return stats of the Korolev task repository.
     */
    public String showStats() {
        return STATS_NOTICE + "\n"
                + FINISHED_MSG + this.countMark() + "\n"
                + UNFINISHED_MSG + (this.events.size() - this.countMark());
    }

    private int countMark() {
        int out = 0;
        for (KorolevTask t : events) {
            if (t.isComplete()) {
                out += 1;
            }
        }

        return out;
    }

    /**
     * Removes a specific task.
     *
     * @param index index of the task.
     * @throws DukeException when the index is out of the range of the array list.
     */
    public String removeEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(OUT_Of_INDEX_ERROR);
        }
        KorolevTask t = this.events.remove(index);
        System.out.println(t);
        System.out.println(DELETE_NOTICE);
        System.out.println("Now you have " + this.events.size() + " tasks in the list.");

        return t + "\n" + DELETE_NOTICE + "\n"
                + "Now you have " + this.events.size() + " tasks in the list.";
    }

    /**
     * Unmarks a specific task to a state of incomplete.
     *
     * @param index index of the task.
     * @throws DukeException when the index is out of the range of the array list.
     */
    public String unmarkEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(OUT_Of_INDEX_ERROR);
        }
        KorolevTask t = this.events.get(index);
        t.unmarkTask();
        System.out.println(UNMARK_NOTICE);
        System.out.println(t);

        return UNMARK_NOTICE + "\n" + t;
    }

    /**
     * Generates the message that will be written into local file.
     *
     * @return message to be recorded.
     */
    private String createSaveInfo() {
        StringBuilder msg = new StringBuilder();
        for (KorolevTask event : this.events) {
            msg.append(event).append("\n");
        }
        return msg.toString();
    }

    /**
     * Saves the information of task list to the.
     */
    public void saveEvent() {
        String msg = this.createSaveInfo();
        storage.writeToFile(msg);
    }


    /**
     * Loads records about events from hard disk.
     */
    public void loadEvent() {
        storage.readLines(this.events);
    }

    public ArrayList<KorolevTask> findItem(String keyword) {
        ArrayList<KorolevTask> out = new ArrayList<>();

        for (KorolevTask e : this.events) {
            if (e.match(keyword)) {
                out.add(e);
            }
        }

        return out;
    }

    public static String displayFilteredList(ArrayList<KorolevTask> tasks) {
        System.out.println(FILTERED_NOTICE);
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            msg.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(msg.toString());

        return FILTERED_NOTICE + "\n" + msg.toString();
    }
}
