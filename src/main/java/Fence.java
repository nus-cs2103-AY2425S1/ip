import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fence {

    private ArrayList<Task> items = new ArrayList<>();

    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    public void exit() {
        System.out.println("have good day :)");
    }

    public void add(Task task) {
        System.out.println("added: " + task);
        items.add(task);
    }

    public void list() {
        System.out.println("!plans:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }
    }

    public void mark(int i) {
        Task task = items.get(i-1);
        task.complete();
        System.out.println("good job");
        System.out.println(task);
    }

    public void unmark(int i) {
        Task task = items.get(i-1);
        task.undo();
        System.out.println("huh?");
        System.out.println(task);
    }

    public void count() {
        System.out.println("Now you have " + items.size() +
                ((items.size() == 1) ? " item " : " items ") + "in the list.");
    }

    public void delete(int i) {
        System.out.println("removed: " + items.get(i-1));
        System.out.println("(we never make plans)");
        items.remove(i - 1);
    }

    public static void main(String[] args) {
        Fence fence = new Fence();
        fence.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(command);
            String firstWord = st.nextToken();
            if (command.equals("bye")) {
                fence.exit();
                break;
            } else if (command.equals("list")) {
                fence.list();
            } else if (firstWord.equals("todo")) {
                try {
                    String desc = st.nextToken();
                    while (st.hasMoreTokens()) {
                        desc = desc + " " + st.nextToken();
                    }
                    fence.add(new Todo(desc));
                    fence.count();
                } catch (NoSuchElementException e) {
                    System.out.println("doing nothing");
                }

            } else if (firstWord.equals("deadline")) {
                String desc = st.nextToken();
                String by = "";
                boolean descDone = false;
                while (st.hasMoreTokens()) {
                    String nextWord = st.nextToken();
                    if (nextWord.equals("/by")) {
                        descDone = true;
                        by = st.nextToken();
                        continue;
                    }
                    if (descDone) {
                        by = by + " " + nextWord;
                    } else {
                        desc = desc + " " + nextWord;
                    }
                }
                fence.add(new Deadline(desc, by));
                fence.count();
            } else if (firstWord.equals("event")) {
                String desc = st.nextToken();
                String from = "";
                String to = "";
                boolean descDone = false;
                boolean fromDone = false;
                while (st.hasMoreTokens()) {
                    String nextWord = st.nextToken();
                    if (nextWord.equals("/from")) {
                        descDone = true;
                        from = st.nextToken();
                        continue;
                    }
                    if (nextWord.equals("/to")) {
                        fromDone = true;
                        to = st.nextToken();
                        continue;
                    }
                    if (fromDone) {
                        to = to + " " + nextWord;
                    } else if (descDone) {
                        from = from + " " + nextWord;
                    } else {
                        desc = desc + " " + nextWord;
                    }
                }
                fence.add(new Event(desc, from, to));
                fence.count();
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                int i = Integer.parseInt(st.nextToken());
                if (firstWord.equals("mark")) {
                    fence.mark(i);
                } else {
                    fence.unmark(i);
                }
            } else if (firstWord.equals("delete")) {
                int i = Integer.parseInt(st.nextToken());
                fence.delete(i);
                fence.count();
            } else {
                System.out.println("fence is programmed to track your tasks and has long lost all ability " +
                        "to do other things ");
            }
        }
    }
}
