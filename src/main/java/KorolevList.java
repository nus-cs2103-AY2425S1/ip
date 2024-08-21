import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KorolevList {
    private static String outOfIndexError = "Error! The index is out of bound!";
    private static String listNotice = "Here are the tasks in your list:\n";
    private static String markNotice = "Nice! I've marked this task as done:";
    private static String unmarkNotice = "OK, I've marked this task as not done yet:";
    private static String deleteNotice = "Noted. I've removed this task:";
    private ArrayList<KorolevTask> events;

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
                } catch (DukeKorolev.ParseException exp) {
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
                } catch (DukeKorolev.ParseException exp) {
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
                } catch (DukeKorolev.ParseException exp) {
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

    public void saveEvent(String msg) {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "java", "data", "korolev.txt");

        //Create new file
        if (!java.nio.file.Files.exists(path)) {
            try {
                File record = new File(String.valueOf(path));
                record.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Write to the file (appending lines to the documents)
        try {
            FileWriter writer = new FileWriter(String.valueOf(path));
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEvent() {

    }
}
