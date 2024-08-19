import java.util.ArrayList;
import java.util.Arrays;

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
                if (tokens.get(0).equals("list")) {
                    list.displayList();
                } else if (tokens.get(0).equals("mark") && (tokens.size() == 2)) {
                    String val = tokens.get(1);
                    list.markDoneTask(Integer.parseInt(val));
                } else {
                    list.addTask(msg);
                }
            }
        }
    }
}
