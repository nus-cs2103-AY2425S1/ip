import java.util.ArrayList;
public class KorolevList {
    private ArrayList<String> events;

    public KorolevList() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String event) {
        this.events.add(event);
        System.out.println("added: " + event);
    }

    public void displayList() {
        for (int i = 0; i < this.events.size(); i++) {
            System.out.println((i + 1) + ". " + this.events.get(i));
        }
    }
}
