import java.util.ArrayList;
import java.util.Scanner;

public class Kitty {
    private static final String name = "Kitty";
    private static final ArrayList<String> list = new ArrayList<String>(100);
    private static final String divisionLine = "--------------------------";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // TODO: Level 0.Rename, Greet, Exit
        Greet();
    }

    public static void Greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        System.out.println(divisionLine);
        Echo();
    }

    public static void Echo() {
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (true) {
            command = sc.nextLine();
            if (command.contains("bye")) {
                Exit();
                return;
            } else if (command.contains("list")) {
                List();
            } else {
                add(command);
            }
        }
    }

    public static void add(String item) {
        list.add(item);
        System.out.println(divisionLine);
        System.out.println("added: " + item + "\n");
        System.out.println(divisionLine);
    }

    public static void List() {
        int count = 1;
        String[] tmp = new String[0];
        System.out.println(divisionLine);
        for (String item: list.toArray(tmp)) {
            System.out.println(count++ + ". " + item);
        }
        System.out.println("\n" + divisionLine);
    }

    public static void Exit() {
        System.out.println(divisionLine);
        System.out.println("Bye. Hope I can see you again soon!\n");
        System.out.println(divisionLine);
    }
}
