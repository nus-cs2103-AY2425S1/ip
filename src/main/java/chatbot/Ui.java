package chatbot;

import storage.Storage;
import tasks.TaskList;

import java.util.Scanner;

import storage.Storage;
import tasks.TaskList;

public class Ui {

    private TaskList taskList;
    private Storage storage;

    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Takes in user inputs and prints out chatbot's responses.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        botSay("Hello I'm chatbot.Peridot!!");
        botSay("What's up?");
        String userResponse = scanner.nextLine();
        while (!userResponse.equals("bye")) {
            try {
                Parser.answer(userResponse, taskList);
                this.storage.write(taskList.getTaskList());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                userResponse = scanner.nextLine();
            }
        }
        botSay("Bye!");
    }

    /**
     * Prints out the message with a "Peridot: " prefix.
     */
    public static void botSay(String string) {
        System.out.println("chatbot.Peridot: " + string);
    }
}
