import java.util.Scanner;

public class Dawn {
    public static void main(String[] args) {
        String logo = "Dawn 🌙";
        String divider = "--".repeat(30);

        System.out.println(divider);
        System.out.printf("%s speaking, what can I do for you? \n", logo);
        respond();
        System.out.println(divider);
    }
    private static Scanner scanner = new Scanner(System.in);
    private static String[] tasks = new String[100];
    private static Boolean[] status = new Boolean[100];
    private static int counter = 0;

    private static void respond() {
        String input = scanner.next();

        if (input.equals("bye")) {
            System.out.println("Byeeee~ nice chatting with you! See you next time, tschüss :)");
        } else if (input.equals("list")) {
            System.out.println("listing all tasks...");
            for (int i = 0; i < counter; i++) {
                System.out.printf("%d. %s  \n", i + 1, tasks[i]);
            }
            respond();
        } else if (input.contains("mark")) {
            int ind = scanner.nextInt();
            mark(input, ind);
            respond();
        } else {
            tasks[counter] = input;
            status[counter] = false;
            counter++;
            System.out.printf("added TASK <%s> to the list \n", input);
            respond();
        }
    }

    private static void mark(String input, int ind) {
        if (input.contains("unmark")) {
            status[ind] = false;
            System.out.println("ok, I've unmarked this task :( ");
            System.out.printf("[ ] %s \n", tasks[ind]);
        } else {
            status[ind] = true;
            System.out.println("i've marked this task as done!: ");
            System.out.printf("[X] %s \n", tasks[ind]);
        }
    }
}
