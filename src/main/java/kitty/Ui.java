package kitty;

import kitty.kittyexceptions.FindException;
import kitty.kittyexceptions.MarksException;
import kitty.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private static final String DIVISION_LINE = "--------------------------";
    private boolean isWorking;
    private TaskList tasks;
    private Storage storage;

    public Ui(TaskList tasks, Storage storage) {
        isWorking = true;
        this.tasks = tasks;
        this.storage = storage;
    }

    public void run() {
        showGreetingMessage();
        try {
            storage.initializeTaskList(tasks);
        } catch (FileNotFoundException e) {
            showErrorMessage("Cannot find ip/data/Kitty.txt file.");
        }catch (IOException e) {
            showErrorMessage("Create file ip/data/Kitty.txt failed.");
        }

        String command = "";
        Scanner sc = new Scanner(System.in);
        while (isWorking) {
            command = sc.nextLine();
            if (command.contains("bye")) {
                isWorking = false;
                showGoodbyeMessage();
                continue;
            }
            try {
                if (Parser.parseFirstWord(command, this, tasks, storage)) {
                    showErrorMessage("Burrrrr~ What is this??? I have no idea about it...\n");
                }
            } catch (FindException e) {
                showErrorMessage(e.toString());
            } catch (MarksException e) {
                showErrorMessage(e.toString());
            } catch (NumberFormatException e) {
                showErrorMessage("Please Input valid number.\n");
            } catch (IndexOutOfBoundsException e) {
                showErrorMessage("Index out of bound!!! Please input number within the range of list size.\n");
            }
        }
    }

    public void showGreetingMessage() {
        System.out.println("Hello! I'm Kitty!");
        System.out.println("What can I do for you?");
        showLowerLine();
    }

    public void showGoodbyeMessage() {
        showUpperLine();
        System.out.println("Bye. Hope I can see you again soon!\n"
                + "Next time bring me some cat food please!!!");
        showLowerLine();
    }

    public void showTaskList(TaskList tasks) {
        showUpperLine();
        System.out.println("Meow~ Here you are!");
        tasks.list();
        showLowerLine();
    }

    public void showDoneMessage(Task task) {
        showUpperLine();
        System.out.println("Well done! You have completed this task!");
        System.out.println(" " + task);
        showLowerLine();
    }

    public void showUndoneMessage(Task task) {
        showUpperLine();
        System.out.println("Meow~ Okay we can continue this task!");
        System.out.println("  " + task);
        showLowerLine();
    }

    public void showAddTaskMessage(Task task, int size) {
        showUpperLine();
        System.out.println("Okie, I added it into the list:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
        showLowerLine();
    }

    public void showDeleteTaskMessage(String note) {
        showUpperLine();
        System.out.println("I have removed it from the list :)");
        System.out.println(note);
        showLowerLine();
    }

    public void showFindTask(String str) {
        showUpperLine();
        System.out.println("Meow~ Here you are!");
        System.out.printf(str);
        showLowerLine();
    }

    private void showUpperLine() {
        System.out.println(DIVISION_LINE);
    }

    private void showLowerLine() {
        System.out.println("\n" + DIVISION_LINE);
    }

    public void showErrorMessage(String message) {
        showUpperLine();
        System.out.println(message);
        showLowerLine();
    }
}
