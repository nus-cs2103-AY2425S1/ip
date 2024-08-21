import java.util.ArrayList;

public class Utility {

    public static final String LINE = "____________________________________________________________\n";
    private static ArrayList<String> toDo = new ArrayList<String>();

    public static void greeting() {
        System.out.print(LINE + " Hello! I'm LuToDo \n" +
                "What can I do for you?\n" +
                 LINE);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addToDo(String s) {
        toDo.add(s);
        System.out.println("added: " + s);
    }

    public static String[] divideMessage(String message) {
        return message.trim().split("\\s+");
    }

    public static void handleMessage(String message) {

        if (message.equals("list")) {

            for (int i = 1; i <= toDo.size(); i++) {
                System.out.println(i + ". " + toDo.get(i - 1));
            }
            return ;
        }
        addToDo(message);

    }
}
