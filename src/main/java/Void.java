import java.io.IOException;
import java.lang.SecurityException;
public class Void {
    private static final String FILE_PATH = "./data/void.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String[] greetings = {
//                "Hello! I'm your friendly void cat, \n",
//                "Purr... Hello, wanderer. I am \n",
//                "Mew! Welcome human! I'm \n",
            "Greetings from the abyss, friend, for I am\n"
//                ,
//                "Meow! Happy to help, they call me \n"
    };

    private String[] assistGreetings = {
//                "How can this void assist you today?",
            "At your service, human."
//                ,
//                "What help does human need today?",
//                "Need any help?",
//                "What can I do for you?"
    };

    // Example of exits
    private String[] exits = {
//                "Purr... Until next time, friend.",
            "Meow! I shall vanish into the shadows now."
//                ,
//                "Farewell! May your path be clear.",
//                "Mew! See you in the void again soon.",
//                "The void calls, but I'll return. Goodbye!"
    };


    public Void(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | VoidException e) {
            Ui.showMessageAndLines("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        } catch (SecurityException s) {
            Ui.showMessageAndLines("Security error in creating file or directory: " + s.getMessage());
        }
    }

    public void run() {
        ui.welcome(greetings, assistGreetings);
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                new Parser().parseAndExecute(fullCommand, tasks, ui, storage);
                if (fullCommand.equalsIgnoreCase("bye")) {
                    ui.goodbye(exits);
                    break;
                }
            } catch (VoidException | IllegalArgumentException e) {
                Ui.showMessageAndLines(e.getMessage());
            } catch (IOException i) {
                Ui.showMessageAndLines("Error saving tasks to file.");
            } catch (SecurityException s) {
                Ui.showMessageAndLines("Security error in creating file or directory: " + s.getMessage());
            }
        }
        Ui.showLine();
        ui.close();
    }

    public static void main(String[] args) {
        new Void(FILE_PATH).run();
    }
}
