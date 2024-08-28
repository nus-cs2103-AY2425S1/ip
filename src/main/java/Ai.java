import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ai {
    ArrayList<Task> tasks = new ArrayList<>();

    static final String greetings = "Hi, I'm your favourite idol, Ai!!!\n"
            + "What shall we do today? Teehee o(◠u◠)o\n";
    static final String closing = "Don't you wanna get my autograph first?\n"
            + "Aww okie :,( See ya!!\n";
    static final String line = "____________________________________________________________\n";
    static final String DIRECTORY_PATH = "./data";
    static final String FILE_PATH = "./data/ai.txt";

    public void readFileContents() {
        try {
            File dir = new File(DIRECTORY_PATH);

            dir.mkdirs();

            if (!dir.exists()) {
                System.out.println("Path created");
            }

            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);


            f.createNewFile();
            // Check if file exists
            if (!f.exists()) {
                // Create file
                System.out.println("File created");
            }

            // File exists, read from file
            while (s.hasNext()) {
                add(readFileLine(s.nextLine()));
            }

            s.close();
        } catch (IOException | AiException e) {
        }
    }

    public Task readFileLine(String input) throws AiException {
        try {
            String[] parsedInput = input.split(" \\| ", 3);
            String taskType = parsedInput[0];
            Boolean isDone = parsedInput[1].equals("1");
            String taskDesc = parsedInput[2];

            if (taskType.equals("T")) {
                return new ToDo(taskDesc, isDone);
            } else if (taskType.equals("D")) {
                String[] parsedDateDesc = taskDesc.split(" \\| ", 2);
                String descDeadline = parsedDateDesc[0];
                String date = parsedDateDesc[1];

                return new Deadline(descDeadline, date, isDone);
            } else {
                String[] parsedFromToDesc = taskDesc.split(" \\| ", 2);
                String descEvent = parsedFromToDesc[0];
                String fromTo = parsedFromToDesc[1];

                String[] parsedDate = fromTo.split(" - ",2);
                String from = parsedDate[0];
                String to = parsedDate[1];

                return new Event(descEvent, from, to, isDone);

            }
        } catch (Exception e) {
            throw new AiException("Ahh dearr, I think your lines might be a teeny tiny buggyyy :p");
        }
    }

    public void writeFile(String path) {
        try {
            FileWriter fw = new FileWriter(path);
            for (Task task : tasks) {
                fw.write(task.stringData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
        }
    }

    public void isDue(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate);

        for(int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if(temp instanceof Deadline) {
                Deadline tempDeadline = (Deadline) temp;

                if(tempDeadline.isEqual(date)) {
                    System.out.println(temp);
                }
            }
        }

        System.out.println(line);
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

        ai.readFileContents();

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

                    String[] parsedInput = arguments.split(" /by ", 2);
                    String desc = parsedInput[0];
                    String date = parsedInput[1];

                    ai.add(new Deadline(desc, date));
                    break;
                case "event":
                    if (arguments.length() <= 0) {
                        throw new AiException("Whoopsies, event cannot be empty >.<\n " +
                                "Try something like \"event birthday w Ai <3333 /from 5am /to 6pm\" instead!\n");
                    }
                    ai.add(new Event(arguments));
                    break;
                case "due":
                    if (arguments.length() <= 0) {
                        throw new AiException("Oh! due cannot be empty!!\n"
                                + "Try something like \"due 2019-12-02\" instead!!!\n");
                    }
                    ai.isDue(arguments);
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

                ai.writeFile(FILE_PATH);
            } catch (AiException e) {
                System.out.println(e.getMessage() + "\n" + line);
            }
        }
    }
}
