import java.util.ArrayList;

/**
 * This class implements a chatbot by the name of Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Bunbun {

    private Storage storage;
    private TaskList list;
    private UI ui;

    public Bunbun(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.list = new TaskList(this.storage, this.ui);
    }

    public void run() {
        this.ui.startScreen();
        storage.initializeTaskFile();

        while (true) {
            String msg = Parser.getMessage();
            if (msg.equals("bye")) {
                this.storage.writeAllFromList(list);
                this.ui.endScreen();
                break;
            } else {
                System.out.println(msg);
                ArrayList<String> tokens = Parser.getTokens();
                String firstWord = tokens.get(0);
                int len = tokens.size();
                if (firstWord.equals("list")) {
                    this.list.displayList();
                } else if (firstWord.equals("mark")) {
                    if (len == 2) {
                        String val = tokens.get(1);
                        this.list.markDoneTask(Integer.parseInt(val));
                    } else {
                        this.ui.response("Specify 1! positive integer to mark task as complete D:");
                    }
                } else if (firstWord.equals("todo")) {
                    this.list.addToDo(tokens);
                } else if (firstWord.equals("deadline")) {
                    this.list.addDeadline(tokens);
                } else if (firstWord.equals("event")) {
                    this.list.addEvent(tokens);
                } else if (firstWord.equals("delete")) {
                    if (len == 2) {
                        this.list.deleteTask(Integer.parseInt(tokens.get(1)));
                    } else {
                        this.ui.response("Specify 1! positive integer to delete task D:");
                    }
                } else {
                    this.ui.response("Sorry, I don't understand ><");
                }
            }
        }
    }
    public static void main(String[] args) {
        new Bunbun("src/main/data").run();
    }
}
