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

    public static void main(String[] args) throws AiException {
        Ai ai = new Ai();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object


        System.out.println(line + greetings + line);

        String reply;

        while (true) {
            try {
                reply = scanner.nextLine();  // Read user input
                System.out.println(line);

                if(reply.equals("list")) {
                    ai.list();
                } else if (reply.startsWith("unmark")) {
                    ai.unmark(Character.getNumericValue(reply.charAt(reply.length() - 1)));
                } else if (reply.startsWith("mark")) {
                    ai.mark(Character.getNumericValue(reply.charAt(reply.length() - 1)));
                } else if (reply.startsWith("todo")) {
                    if (reply.length() < "todo ".length() + 1) {
                        throw new AiException("Whoopsies, todo cannot be empty >.< \n " +
                                "Try something like \"todo hangout with Ai\" instead! \n");
                    }
                    ai.add(new ToDo(reply.substring(5)));
                } else if (reply.startsWith("deadline")){
                    if (reply.length() < "deadline ".length() + 1) {
                        throw new AiException("Whoopsies, deadline cannot be empty >.< \n " +
                                "Try something like \"deadline date w Ai <3 /by Wed\" instead! \n");
                    }
                    ai.add(new Deadline(reply.substring(9)));
                } else if (reply.startsWith("event")){
                    if (reply.length() < "event ".length() + 1) {
                        throw new AiException("Whoopsies, event cannot be empty >.< \n " +
                                "Try something like \"event birthday w Ai <3333 /from 5am /to 6pm\" instead! \n");
                    }
                    ai.add(new Event(reply.substring(6)));
                } else if (reply.equals("bye")){
                    break;
                } else {
                    throw new AiException("Sorry, I don't quite understand what you mean... wanna try typing smth else??");
                }
            } catch (AiException e) {
                System.out.println(e.getMessage() + "\n" + line);
            }
        }
        System.out.println(closing + line);
    }
}
