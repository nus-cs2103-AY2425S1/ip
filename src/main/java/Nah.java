import java.util.Scanner;

public class Nah {
    static String greetLine = "____________________________________________________________\n"
            + " Hello! I'm NAH \n"
            + " What can I do for you? \n"
            + "____________________________________________________________\n";
    static String byeLine = " Bye. Hope to see you again soon!\n";

    String[] task = new String[100];
    int taskCount = 0;
    void greet() {
        System.out.println(greetLine);
    }

    void exit() {
        System.out.println(byeLine);
    }

    void add(String input) {
        task[taskCount] = input;
        taskCount  ++;
        System.out.println(" added " + input + "\n");
    }

    void readTask() {
        for (int i = 1; i <= taskCount; i ++) {
            System.out.println( " " + i + ". " + task[i - 1] + "\n");
        }
    }

    public static void main(String[] args) {
        String logo = "| \\   | |      /\\      | |  | | \n"
                + "| |\\  | |     / /\\     | |==| | \n"
                + "| | \\ | |    / /__\\    | |  | | \n"
                + "| |  \\  |   / /    \\   | |  | | \n";


        System.out.println("Hello from\n" + logo);

        Nah nah = new Nah();

        nah.greet();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________ \n");
            if (input.equals("bye")) {
                nah.exit();
                break;
            }
            else if(input.equals("list")) {
                nah.readTask();
            }
            else {
                nah.add(input);
            }
        }
        System.out.println("____________________________________________________________ \n");
    }
}
