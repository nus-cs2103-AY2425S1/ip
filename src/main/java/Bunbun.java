import java.util.ArrayList;

/**
 * This class implements a chatbot by the name of Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Bunbun {
    public static void main(String[] args) {
        UI.startScreen();
        TaskList list = new TaskList();

        while (true) {
            String msg = Parser.getMessage();
            if (msg.equals("bye")) {
                UI.endScreen();
                break;
            } else {
                System.out.println(msg);
                ArrayList<String> tokens = Parser.getTokens();
                String firstWord = tokens.get(0);
                int len = tokens.size();
                if (firstWord.equals("list")) {
                    list.displayList();
                } else if (firstWord.equals("mark")) {
                    if (len == 2) {
                        String val = tokens.get(1);
                        list.markDoneTask(Integer.parseInt(val));
                    } else {
                        UI.response("Specify 1! positive integer to mark task as complete D:");
                    }
                } else if (firstWord.equals("todo")) {
                    list.addToDo(tokens);
                } else if (firstWord.equals("deadline")) {
                    list.addDeadline(tokens);
                } else if (firstWord.equals("event")) {
                    list.addEvent(tokens);
                } else if (firstWord.equals("delete")) {
                    if (len == 2) {
                        list.deleteTask(Integer.parseInt(tokens.get(1)));
                    } else {
                        UI.response("Specify 1! positive integer to delete task D:");
                    }
                } else {
                    UI.response("Sorry, I don't understand ><");
                }
            }
        }
    }
}
