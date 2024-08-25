import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class encapsulates a task helper chatbot.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    /** List of tasks entered by the user */
    private TaskList tasks;
    /** TODO doc this */
    private Storage storage;

    public Qwerty() {
        this.isChatting = true;
        this.tasks = new TaskList();
        this.storage = new Storage("savefile.txt");
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println("""

                Hello! I'm Qwerty.
                What can I do for you?""");
    }

    /**
     * Prints a goodbye message.
     */
    public void sayGoodbye() {
        System.out.println("""
                
                Bye. Hope to see you again soon!""");
    }

    /**
     * Starts the chatbot and run the main chat loop.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        storage.loadTasks(tasks);
        greet();

        while (isChatting) {
            System.out.println(); // blank line before user input
            String rawInput = scanner.nextLine();
            try {
                Command command = Parser.parse(rawInput);
                command.execute(tasks, storage);
                storage.saveTasks(tasks);
                isChatting = !command.isExitCommand();
            } catch (QwertyException e) {
                System.out.println(e.getMessage()); //TODO TEMP
            }
        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
