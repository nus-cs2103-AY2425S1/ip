import utils.EventParser;
import utils.ParseException;

import java.util.ArrayList;
public class KorolevList {
    private ArrayList<KorolevTask> events;

    public KorolevList() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String event) throws DukeException {
        KorolevTask e;
        String name;
        String date;
        if (event.contains("event")) {

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
        } else if (event.contains("todo")) {
            try {
                name = EventParser.parseName("todo", "", event);
                e = new KorolevTodo(name);
                this.events.add(e);
                System.out.println("Got it. I've added this task:");
                System.out.println(e);
            } catch (ParseException exp) {
                System.out.println(exp.getMessage());
            }

        } else if (event.contains("deadline")) {
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
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }



        System.out.println("Now you have " + events.size() + " tasks in the list");
    }

    public void displayList() {
        for (int i = 0; i < this.events.size(); i++) {
            System.out.println((i + 1) + ". " + this.events.get(i));
        }
    }

    public void markEvent(int index) {
        KorolevTask t = this.events.get(index);
        t.markTask();
        System.out.println(t);
    }

    public void unmarkEvent(int index) {
        KorolevTask t = this.events.get(index);
        t.unmarkTask();
        System.out.println(t);
    }
}
