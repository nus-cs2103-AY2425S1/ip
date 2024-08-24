import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gallium {
    public static void main(String[] args) {
        String helloMessage = "Hello! I am Gallium. \n    What can I do for you?";
        String listMessage = "Here are the tasks in your list:";
        String byeMessage = "Bye. Hope to see you again soon!";
        String lines = "____________________________________________________________";
        LinkedHashMap<String, Boolean> taskList = new LinkedHashMap<String, Boolean>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("    " + lines + "\n    " + helloMessage + "\n    " + lines);
        String Message = userInput.nextLine();
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";

        while (!Message.equals(bye)) {
            Set<String> keySet = taskList.keySet();
            String[] keyArray = keySet.toArray(new String[keySet.size()]);
            if (Message.equals(list)) {
                System.out.println("    " + lines);
                System.out.println("    " + listMessage);
                for (int i = 1; i <= taskList.size(); i++) {
                    String key = keyArray[i - 1];
                    String box = taskList.get(key) ? "[X] " : "[ ] ";
                    System.out.println("\n    " + i + ". " + box + key);
                }
                System.out.println("    " + lines + "\n    ");
                Message = userInput.nextLine();
            } else if (Message.matches(mark + " \\d+") || Message.matches(unmark + " \\d+")) {
                boolean isMark = Message.startsWith(mark);
                Pattern pattern = Pattern.compile((isMark ? mark : unmark) + " (\\d+)");
                Matcher matcher = pattern.matcher(Message);
                if (matcher.matches()) {
                    int index = Integer.parseInt(matcher.group(1));
                    String key = keyArray[index - 1];
                    taskList.put(key, isMark);
                    String box = taskList.get(key) ? "[X] " : "[ ] ";
                    System.out.println("    " + lines);
                    System.out.println("    " + (isMark ? "Nice! I've marked this task as done: "
                            : "OK, I've marked this task as not done yet: ") + "\n" + "    " + box + key);
                    System.out.println("    " + lines + "\n    ");
                    Message = userInput.nextLine();
                }
            } else {
                System.out.println("    " + lines + "\n    " + "added: " + Message + "\n    " + lines + "\n");
                taskList.put(Message, false);
                Message = userInput.nextLine();
            }
        }
        System.out.println("    " + lines + "\n    " + byeMessage + "\n    " + lines + "\n");
        userInput.close();
    }
}
