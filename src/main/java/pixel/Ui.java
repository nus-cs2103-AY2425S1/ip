package pixel;

import java.util.Scanner;
import pixel.task.TaskList;

public class Ui {
    private String name = "Pixel";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showWelcome() {
        PixelSays("Hello! I'm " + name, "What can I do for you?\n");
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void PixelSays(String... args) {
        for (String arg : args) {
            System.out.println("    " + arg);
        }
    }

    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            PixelSays("No matching tasks found!");
        } else {
            PixelSays("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                PixelSays((i + 1) + ". " + matchingTasks.getTaskAtIndex(i));
            }
        }
    }

    public void closeUi() {
        this.scanner.close();
    }
}
