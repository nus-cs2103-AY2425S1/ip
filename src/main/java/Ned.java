import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
public class Ned {
    public static final String INDENTATIONS = "    ";
    private Storage storage;
    private ArrayList<Task> listOfText;
    private static final String logo = Ned.INDENTATIONS + " ____  _____              __  \n"
            + Ned.INDENTATIONS + "|_   \\|_   _|            |  ] \n"
            + Ned.INDENTATIONS + "  |   \\ | |  .---.   .--.| |  \n"
            + Ned.INDENTATIONS + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
            + Ned.INDENTATIONS + " _| |_\\   |_| \\__.,| \\__/  |  \n"
            + Ned.INDENTATIONS + "|_____|\\____|'.__.' '.__.;__]";
    public static final String cachedTasksPath = Paths.get("src", "data", "cachedTasks.txt").toString();
    public Ned(String filePath) {
        try {
            this.storage = new Storage(filePath);
        } catch (NedException e){
            print("Loading error, m'lord"); //update this
        }
    }

    public void run() {
        showWelcomeMessage();
        this.listOfText = this.storage.load();
        this.checkCommands();
        this.showByeMessage();
    };
    public static void main(String[] args) {
//        Ned.showWelcomeMessage();
//        Ned.loadInSavedTasks(Ned.cachedTasksPath);
//        Ned.checkCommands();
//        Ned.showByeMessage();
        new Ned(Ned.cachedTasksPath).run();
    }

    public void showWelcomeMessage() {
        print("Hello! I'm\n" + logo + "\n");
        print("Lord of Winterfell and Warden Of The North");
        print("What can I do for you?");
    }


    public void showByeMessage() {
        print("I wish you good fortune in the wars to come, m' lord");
    }

    public static void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ned.INDENTATIONS + line);
    }

    private void checkCommands() {
        System.out.println("\n");
        CommandManager commandParser = new CommandManager(this.listOfText);
        Scanner inputDetector = new Scanner(System.in);
        FlagWrapper flag = new FlagWrapper(true);
        while (flag.getStatus()) {
            String nextInput = inputDetector.nextLine();
            commandParser.processCommand(nextInput, flag);
        }
    }
}
