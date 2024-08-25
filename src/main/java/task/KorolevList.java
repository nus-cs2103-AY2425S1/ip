package task;

import exception.DukeException;
import exception.ParseException;
import storage.KorolevStorage;
import parser.EventParser;
import parser.DateParser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class KorolevList {
    private static String outOfIndexError = "The index is out of bound!";
    private static String listNotice = "Here are the tasks in your list:\n";
    private static String markNotice = "Nice! I've marked this task as done:";
    private static String unmarkNotice = "OK, I've marked this task as not done yet:";
    private static String deleteNotice = "Noted. I've removed this task:";
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

    public void addEvent(String event) throws DukeException {
        KorolevTask e;
        String name, date, from, to;
        String target = event.split("\\s")[0];
        switch (target) {
            case "event" -> {
                try {
                    name = EventParser.parseName("event", "/from", event);
                    from = DateParser.parseFrom(event);
                    to = DateParser.parseTo(event);
                    e = new KorolevEvent(name, from, to);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (ParseException | DateTimeParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            case "todo" -> {
                try {
                    name = EventParser.parseName("todo", "", event);
                    e = new KorolevTodo(name);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (ParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            case "deadline" -> {
                try {
                    name = EventParser.parseName("deadline", "/by", event);
                    date = DateParser.parseBy(event);
                    e = new KorolevDeadline(name, date);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (ParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            default -> throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        System.out.println("Now you have " + events.size() + " tasks in the list");
    }

    public String displayList() {
        return listNotice + this.toString();
    }

    public void markEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.get(index);
        t.markTask();
        System.out.println(markNotice);
        System.out.println(t);
    }

    public void removeEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.remove(index);
        System.out.println(t);
        System.out.println(deleteNotice);
        System.out.println("Now you have " + this.events.size() + " tasks in the list.");
    }

    public void unmarkEvent(int index) throws DukeException {
        if (index >= this.events.size() || index < 0) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.get(index);
        t.unmarkTask();
        System.out.println(unmarkNotice);
        System.out.println(t);
    }

    private String createSaveInfo() {
        StringBuilder msg = new StringBuilder();
        for (KorolevTask event : this.events) {
            msg.append(event).append("\n");
        }

        return msg.toString();
    }

    public void saveEvent() {
        //Write to the file
        String msg = this.createSaveInfo();
        storage.writeToFile(msg);
    }


    public void loadEvent() {
        storage.readLines(this.events);
    }
}
