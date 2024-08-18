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
}
