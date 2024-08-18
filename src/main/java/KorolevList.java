import java.util.ArrayList;
public class KorolevList {
    private ArrayList<KorolevTask> events;

    public KorolevList() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String event) {
        this.events.add(new KorolevTask(event));
        System.out.println("added: " + event);
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
