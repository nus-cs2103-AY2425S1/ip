import java.util.Scanner;

public class Kitty {
    private static final String name = "Kitty";
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
        while (!command.contains("bye")) {
            command = sc.nextLine();
            System.out.println(divisionLine);
            System.out.println(command + "\n");
            System.out.println(divisionLine);
        }
        Exit();
    }

    public static void Exit() {
        System.out.println("Bye. Hope I can see you again soon!\n");
        System.out.println(divisionLine);
    }
}
