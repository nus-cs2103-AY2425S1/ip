import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handles user inputs and generate appropriate replies
 */
public class Replies {

    // Stock messages
    public static final String logo = "       _____   _    _  _   _  _   _  __     __  \n" +
            "      / ____| | |  | || \\ | || \\ | | \\ \\   / /  \n" +
            "     | (___   | |  | ||  \\| ||  \\| |  \\ \\_/ /   \n" +
            "      \\___ \\  | |  | || . ` || . ` |   \\   /    \n" +
            "      ____) | | |__| || |\\  || |\\  |    | |     \n" +
            "     |_____/   \\____/ |_| \\_||_| \\_|    |_|     \n";
    public static final String line = "     ────────────────────";
    public static final String welcome = "     HELLO! I am Sunny:)\n     How can I help you?";
    public static final String goodbye = "     You are leaving? Ok bye:( come back soon";

    // List for tasks
    List<Tasks> ls = new ArrayList<>();

    // File path for external storage
    String filePath = "/Users/jerryyou/ip/taskslist.txt";

    /**
     * Generates welcome message, load in previous files
     * @return
     */
    public String welcome() {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Tasks t = TaskCreator.create(line);
                ls.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello from\n" + logo + "\n" + welcome + "\n" + line + "\n";
    }

    /**
     * Takes in user replies, parse them, and generate appropriate replies
     * @param message passed from user through Sunny class
     * @return String to be printed out in Sunny class
     */
    public String reply(String message) {
        String m1 = message.split(" ", 2)[0];

        if (Objects.equals(message,"bye")) {
            String str = "";
            for (Tasks t: ls) {
                if (t instanceof TodoTasks) {
                    str += "todo " + t.getName() + "\n";
                } else if (t instanceof DeadlineTasks) {
                    str += "deadline " + t.getName() + "\n";
                } else {
                    str += "event " + t.getName() + '\n';
                }
            }
            try {
                Files.write(Paths.get(filePath), str.getBytes());
            } catch (FileNotFoundException e) {
                System.out.println("File not found, please create new file with file path: " +
                        "/Users/jerryyou/ip/taskslist.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line + "\n" + goodbye;
        } else if (Objects.equals(message,"list")){
            String s = "";
            for (int i = 1; i <= ls.size(); i++) {
                s = s + String.format("     %h. %s \n", i, ls.get(i - 1));
            }
            return line + "\n" + s + "\n" + line;
        } else if (Objects.equals(message,"")) {
            return line;
        } else if (Objects.equals(m1, "mark")) {
             String m2 = message.split(" ", 2)[1];
             int i2 = Integer.parseInt(m2);
             ls.get(i2 - 1).setter(true);
             return "     Marked the task as done! \n     " + ls.get(i2 - 1);
        } else if (Objects.equals(m1, "unmark")) {
            String m2 = message.split(" ", 2)[1];
            int i2 = Integer.parseInt(m2);
            ls.get(i2 - 1).setter(false);
            return "     Task undone \n     " + ls.get(i2 - 1);
        } else if (Objects.equals(m1, "delete")) {
            String m2 = message.split(" ", 2)[1];
            int i2 = Integer.parseInt(m2);
            String s = ls.get(i2 - 1).toString();
            ls.remove(i2 -1);
            return line + "\n     "
                    + "Got it! removed the task: \n     "
                    + s + "\n     "
                    + String.format("Now you have %h tasks in the list \n", ls.size()) + line;
        } else {
            try {
                Tasks t = TaskCreator.create(message);
                ls.add(t);
                return line + "\n     "
                        + "Got it! added the task: \n     "
                        + t + "\n     "
                        + String.format("Now you have %h tasks in the list \n", ls.size()) + line;
            } catch (Exception e){
                return line + "\n      " + e + line;
            }
        }
    }

}
