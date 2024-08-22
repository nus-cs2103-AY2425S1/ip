import java.util.ArrayList;
import java.util.Scanner;

public class Fence {

    private ArrayList<String> items = new ArrayList<>();

    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    public void exit() {
        System.out.println("have good day :)");
    }

    public void add(String command) {
        System.out.println("added: " + command);
        items.add(command);
    }

    public void list() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }
    }

    public static void main(String[] args) {
        Fence fence = new Fence();
        fence.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                fence.exit();
                break;
            }
            if (command.equals("list")) {
                fence.list();
                continue;
            }
            fence.add(command);
        }
    }
}
