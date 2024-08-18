import java.util.ArrayList;
public class KorolevList {
    private ArrayList<KorolevTask> events;

    public KorolevList() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String event) {
        System.out.println("Got it. I've added this task:");
        KorolevTask e;
        if (event.contains("event")) {
            e = new KorolevEvent(event);
        } else if (event.contains("todo")) {
            e = new KorolevTodo(event);
        } else if (event.contains("deadline")) {
            e = new KorolevDeadline(event);
        } else {
            e = new KorolevTask(event);
        }

        System.out.println(e);
        this.events.add(e);
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
