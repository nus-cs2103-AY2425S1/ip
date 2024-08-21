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
        boolean done = false;
        while (!done) {
            String command = echo();
            if (command.equals("bye")) {
                String byeMessage = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
                System.out.println(byeMessage);
                done = true;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                int len = l1.getSize();
                for (int i = 0; i < len; i++) {
                    System.out.println(i + ". " + l1.getItem(i));
                }
                System.out.println("______________________________________________________________");
            } else {
                String echoMessage = "____________________________________________________________\n"
                        + "added: "
                        + command
                        +"\n____________________________________________________________";
                System.out.println(echoMessage);
                l1.addItem(command);
            }
        }
    }

    public static String echo() {
        Scanner scan  = new Scanner(System.in);
        return scan.nextLine();
    }
}