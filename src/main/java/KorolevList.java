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
        String target = event.split("\\s")[0];
        switch (target) {
            case "event" -> {
                try {

                    name = DukeException.EventParser.parseName("event", "/from", event);
                    date = DukeException.EventParser.parseDate(event);
                    e = new KorolevEvent(name, date);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (DukeKorolev.ParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            case "todo" -> {
                try {
                    name = DukeException.EventParser.parseName("todo", "", event);
                    e = new KorolevTodo(name);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (DukeKorolev.ParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            case "deadline" -> {
                try {
                    name = DukeException.EventParser.parseName("deadline", "/by", event);
                    date = DukeException.EventParser.parseDate(event);
                    e = new KorolevDeadline(name, date);
                    this.events.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                } catch (DukeKorolev.ParseException exp) {
                    System.out.println(exp.getMessage());
                }
            }
            default -> throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    public void removeEvent(int index) {
        KorolevTask t = this.events.remove(index);
        System.out.println(t);
        System.out.println("Now you have " + this.events.size() + " tasks in the list.");
    }

    public void unmarkEvent(int index) {
        KorolevTask t = this.events.get(index);
        t.unmarkTask();
        System.out.println(t);
    }
}
