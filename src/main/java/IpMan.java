import java.util.Scanner;

public class IpMan {
    public static final String SEPARATOR = "____________________________________________________________";
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int nextIdx = 0;
        System.out.println(SEPARATOR);
        System.out.println("Hello from Ip Man!\nWhat can I do for you?");
        System.out.println(SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(SEPARATOR);
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
                break;
            } else if (line.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < nextIdx; i++){
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else if (line.toLowerCase().contains("unmark")) {
                int idx = Integer.parseInt(line.split(" ")[1]) - 1;
                if (idx < 0 || idx > nextIdx) { continue; }
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[idx].unmarkStatus());
            } else if (line.toLowerCase().contains("mark")) {
                int idx = Integer.parseInt(line.split(" ")[1]) - 1;
                if (idx < 0 || idx > nextIdx) { continue; }
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[idx].markStatus());
            } else {
                System.out.println("added: " + line);
                list[nextIdx++] = new Task(line);
            }
            System.out.println(SEPARATOR);
        }
    }
}
