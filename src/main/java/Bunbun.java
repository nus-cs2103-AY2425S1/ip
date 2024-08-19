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
                System.out.println("You: " + msg);
                if (msg.equals("list")) {
                    list.displayList();
                } else {
                    list.addTask(msg);
                }
            }
        }
    }
}
