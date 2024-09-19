import java.util.ArrayList;
public class Lewis {
    private static boolean isExit = false;
    /**
     * Initialises the data for Lewis bot to function. This includes loading the tasklist from
     * the hard drive and the sorted command list that Lewis can parse.
     */
    private static void init() {
        ArrayList<String> savedTasks = Storage.load();
        TaskList taskList = TaskList.of(savedTasks);
    }

    public void run() {
        Parser parser = Parser.of();
        init();
        Ui.printLine();
        System.out.println("Hello! My name is Lewis, a chatbot.\nHow can I help you?");

        while (!isExit) {
            String userInput = Ui.readLine();
            try {
                Command command = parser.parseCommand(userInput);
                Lewis.isExit = command.isExit();
                command.execute();
            } catch (LewisException e) {
                Ui.printString(e.getMessage());
            } finally {
                Ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        Lewis lewis = new Lewis();
        lewis.run();
        System.exit(0);
    }
}
