import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Ai {
    ArrayList<Task> tasks = new ArrayList<>();

    static final String greetings = "Hi, I'm your favourite idol, Ai!!!\n"
            + "What shall we do today? Teehee o(◠u◠)o\n";
    static final String closing = "Don't you wanna get my autograph first?\n"
            + "Aww okie :,( See ya!!\n";
    static final String line = "____________________________________________________________\n";

    public void readFileContents(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            add(readFileLine(s.nextLine()));
        }
    }

    public Task readFileLine(String s) {
        return null;
    }

    public void list() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
        }
        System.out.println(line);
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("Task added!!");
        System.out.println(task);
        System.out.println(String.format("You better finish your %d tasks!! ehe :3\n", tasks.size()) + line);
    }

    public void mark(String index) throws AiException {
        int i = Integer.parseInt(index) - 1;

        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist...\n" +
                                    "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        temp.mark();
        System.out.println("Marked as done... since you have time, how about a drink ;)");
        System.out.println(temp + "\n" + line);
    }

    public void unmark(String index) throws AiException {
        int i = Integer.parseInt(index) - 1;

        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist...\n" +
                    "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        temp.unmark();
        System.out.println("Donzo, task unmarked! Let me know if you need anything else :3");
        System.out.println(temp + "\n" + line);
    }

    public void delete(String index) throws AiException {
        int i = Integer.parseInt(index) - 1;

        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist... can't be removed >....<\n" +
                    "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        tasks.remove(i);
        System.out.println("Gotchyaa, task removed!!\n");
        System.out.println(temp + "\n");
        System.out.println("You have "+ tasks.size() + " tasks in your list :p\n" + line);
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

                String[] parsedReply = reply.split(" ", 2);
                String command = parsedReply[0];
                String arguments = (parsedReply.length > 1) ? parsedReply[1] : "";

                switch (command) {
                case "list":
                    ai.list();
                    break;
                case "unmark":
                    ai.unmark(arguments);
                    break;
                case "mark":
                    ai.mark(arguments);
                    break;
                case "todo":
                    if (arguments.length() <= 0) {
                        throw new AiException("Whoopsies, todo cannot be empty >.<\n " +
                                "Try something like \"todo hangout with Ai\" instead!\n");
                    }
                    ai.add(new ToDo(arguments));
                    break;
                case "deadline":
                    if (arguments.length() <= 0) {
                        throw new AiException("Whoopsies, deadline cannot be empty >.<\n " +
                                "Try something like \"deadline date w Ai <3 /by Wed\" instead!\n");
                    }
                    ai.add(new Deadline(arguments));
                    break;
                case "event":
                    if (arguments.length() <= 0) {
                        throw new AiException("Whoopsies, event cannot be empty >.<\n " +
                                "Try something like \"event birthday w Ai <3333 /from 5am /to 6pm\" instead!\n");
                    }
                    ai.add(new Event(arguments));
                    break;
                case "delete":
                    ai.delete(arguments);
                    break;
                case "bye":
                    System.out.println(closing + line);
                    return;
                default:
                    System.out.println("Sorry, I don't quite understand what you mean..."
                            + "wanna try typing smth else??\n" + line);
                    break;
                }
            } catch (AiException e) {
                System.out.println(e.getMessage() + "\n" + line);
            }
        }
    }
}
