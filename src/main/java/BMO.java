import java.util.Scanner;

public class BMO {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n" + line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(command + "\n");
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
