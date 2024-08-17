import java.util.Scanner;  // Import the Scanner class

public class Ai {
    String[] tasks = new String[101];
    int counter = 0;
    static final String greetings = "Hi, I'm your favourite idol, Ai!!! \n"
            + "What shall we do today? Teehee o(◠u◠)o \n";
    static final String closing = "Don't you wanna get my autograph first? \n"
            + "Aww okie :,( See ya!! \n";
    static final String line = "____________________________________________________________ \n";

    public void list() {
        for(int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + tasks[i] + "\n");
        }
        System.out.println(line);
    }

    public void add(String task) {
        tasks[counter] = task;
        counter++;
        System.out.println("added: " + task + "\n" + line);
    }

    public static void main(String[] args) {
        Ai ai = new Ai();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object


        System.out.println(line + greetings + line);

        String reply;

        do {
            reply = scanner.nextLine();  // Read user input
            System.out.println(line);

            if(reply.equals("list")) {
                ai.list();
            } else {
                ai.add(reply);
            }
        }  while(!reply.equals("bye"));
        System.out.println(closing + line);
    }
}
