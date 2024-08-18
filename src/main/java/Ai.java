import java.util.Scanner;  // Import the Scanner class

public class Ai {
    Task[] tasks = new Task[101];
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

    public void add(Task task) {
        tasks[counter] = task;
        counter++;
        System.out.println("Task added!!");
        System.out.println(task);
        System.out.println(String.format("You better finish your %d tasks!! ehe :3 \n", counter) + line);
    }

    public void mark(int i) {
        Task temp = tasks[i-1];
        temp.mark();
        System.out.println("Marked as done... since you have time, how about a drink ;)");
        System.out.println(temp + "\n" + line);
    }

    public void unmark(int i) {
        Task temp = tasks[i-1];
        temp.unmark();
        System.out.println("Donzo, task unmarked! Let me know if you need anything else :3");
        System.out.println(temp + "\n" + line);
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
            } else if (reply.startsWith("unmark")) {
                ai.unmark(Character.getNumericValue(reply.charAt(reply.length() - 1)));
            } else if (reply.startsWith("mark")) {
                ai.mark(Character.getNumericValue(reply.charAt(reply.length() - 1)));
            } else if (reply.startsWith("todo")) {
                ai.add(new ToDo(reply.substring(5)));
            } else if (reply.startsWith("deadline")){
                ai.add(new Deadline(reply.substring(9)));
            } else if (reply.startsWith("event")){
                ai.add(new Event(reply.substring(6)));
            } else {
                break;
            }
        }  while(!reply.equals("bye"));
        System.out.println(closing + line);
    }
}
