import java.io.File;

public class Yapper {
    public static UI ui;
    public static Parser parser;
    public static Storage storage;
    public static TaskList taskList;

    public Yapper(String fileName) {
        File file = new File(fileName);
        storage = new Storage(file);
        taskList = storage.loadHistory();
        parser = new Parser(taskList);
        ui = new UI(parser);
    }
    public static void main(String[] args) {
        String fileName = "./src/main/java/YapperHistoryFile";
        new Yapper(fileName).run();
    }

    public void run() {
        // greeting message
        System.out.println("Hello! I'm Yapper\n" +
                "What can I do for you?\n");
        taskList.returnList();

        ui.readInput();
    }

    // exits when the user types the command "bye"
    public static void exit()
    {
        System.out.println("Yapper shall yap next time!");
    }
}
