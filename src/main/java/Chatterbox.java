import java.util.Scanner;

public class Chatterbox {
    public static void main(String[] args) {
        String welcomeMessage = """
                ____________________________________________________________
                 Hello! I'm Chatterbox
                 What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(welcomeMessage);
        StoredList l1 = new StoredList(100);
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            String[] command = sc.nextLine().split(" ", 2);
            if (command[0].equals("bye")) {
                String byeMessage = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
                System.out.println(byeMessage);
                done = true;
            } else if (command[0].equals("list")) {
                System.out.println("____________________________________________________________");
                int len = l1.getSize();
                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + l1.getItem(i-1));
                }
                System.out.println("____________________________________________________________");
            } else if (command[0].contains("mark")) {
                int taskNum = Integer.parseInt(command[1]) - 1;
                l1.getItem(taskNum).setCompleted(command[0].equals("mark"));
            } else if (command[0].equals("todo")) {
                l1.addItem(new ToDos(command[1]));
            } else if (command[0].equals("deadline")) {
                String[] details = command[1].split("/");
                l1.addItem(new Deadline(details[0], details[1].substring(3)));
            } else if (command[0].equals("event")) {
                String[] details = command[1].split("/");
                l1.addItem(new Event(details[0], details[1].substring(5), details[2].substring(3)));
            } else {
                String echoMessage = "____________________________________________________________\n"
                        + "added: "
                        + command
                        +"\n____________________________________________________________";
                System.out.println(echoMessage);
                l1.addItem(new Task(command[1]));
            }
        }
    }
}