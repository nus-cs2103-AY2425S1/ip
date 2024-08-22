import java.util.ArrayList;
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

    public void add(String command) {
        System.out.println("added: " + command);
        Task task = new Task(command);
        items.add(task);
    }

    public void list() {
        System.out.println("!plans: ");
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
            }
            if (command.equals("list")) {
                fence.list();
                continue;
            }
            if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                try {
                    int i = Integer.parseInt(st.nextToken());
                    if (!st.hasMoreTokens()) {
                        if (firstWord.equals("mark")) {
                            fence.mark(i);
                        } else {
                            fence.unmark(i);
                        }
                    } else {
                        fence.add(command);
                    }
                }
                catch(NumberFormatException | IndexOutOfBoundsException e) {
                    fence.add(command);
                }
                continue;
            }
            fence.add(command);
        }
    }
}
