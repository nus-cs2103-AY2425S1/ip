package task;

import exception.DukeException;
import exception.ParseException;
import task.KorolevDeadline;
import task.KorolevEvent;
import task.KorolevTask;
import task.KorolevTodo;
import storage.KorolevStorage;
import parser.EventParser;

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
        String name;
        String date;
        String target = event.split("\\s")[0];
        switch (target) {
            case "event" -> {
                try {
                    name = EventParser.parseName("event", "/from", event);
                    date = EventParser.parseDate(event);
                    e = new KorolevEvent(name, date);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (ParseException exp) {
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
                    date = EventParser.parseDate(event);
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
        StringBuilder msg = new StringBuilder(listNotice);
        msg.append(this.toString());
        return msg.toString();
    }

    public String markEvent(int index) throws DukeException {
        if (index >= this.events.size()) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.get(index);
        t.markTask();
        System.out.println(markNotice);
        System.out.println(t);
        return t.toString();
    }

    public String removeEvent(int index) throws DukeException {
        if (index >= this.events.size()) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.remove(index);
        System.out.println(t);
        System.out.println(deleteNotice);
        System.out.println("Now you have " + this.events.size() + " tasks in the list.");
        return t.toString();
    }

    public String unmarkEvent(int index) throws DukeException {
        if (index >= this.events.size()) {
            throw new DukeException(outOfIndexError);
        }
        KorolevTask t = this.events.get(index);
        t.unmarkTask();
        System.out.println(unmarkNotice);
        System.out.println(t);
        return t.toString();
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
