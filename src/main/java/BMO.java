import java.util.Scanner;

public class BMO {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n" + line);

        Storage storage = new Storage();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    System.out.println(storage.getTasks() + line);
                    break;
                default:
                    storage.addTask(command);
                    System.out.println("added: " + command + "\n" + line);
                    break;
            }
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
