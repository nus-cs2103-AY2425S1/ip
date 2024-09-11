package kitty;

import kitty.kittyexceptions.FindException;
import kitty.kittyexceptions.MarksException;
import kitty.tasks.Task;

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
                Parser.parseFirstWord(command, this, tasks, storage);
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

    public String showGoodbyeMessage() {
        String message = "Bye. Hope I can see you again soon!\n"
                + "Next time bring me some cat food please!!!";
        showUpperLine();
        System.out.println(message);
        showLowerLine();
        return message;
    }

    public String showTaskList(TaskList tasks) {
        showUpperLine();
        System.out.println("Meow~ Here you are!");
        String message = tasks.list();
        showLowerLine();
        return "Meow~ Here you are!\n" + message;
    }

    public String showDoneMessage(Task task) {
        showUpperLine();
        String message = "Well done! You have completed this task!\n" + " " + task;
        System.out.println(message);
        showLowerLine();
        return message;
    }

    public String showUndoneMessage(Task task) {
        showUpperLine();
        String msg = "Meow~ Okay we can continue this task!\n" + "  " + task;
        System.out.println(msg);
        showLowerLine();
        return msg;
    }

    public String showAddTaskMessage(Task task, int size) {
        showUpperLine();
        String msg = String.format("Okie, I added it into the list:\n" + "  " + task
                + "\nNow you have %d tasks in the list.\n", size);
        System.out.printf(msg);
        showLowerLine();
        return msg;
    }

    public String showDeleteTaskMessage(String note) {
        showUpperLine();
        System.out.println("I have removed it from the list :)");
        System.out.println(note);
        showLowerLine();
        return "I have removed it from the list :)\n" + note;
    }

    public String showFindTask(String str) {
        showUpperLine();
        String msg = "Meow~ Here you are!\n" + str;
        System.out.printf(msg);
        showLowerLine();
        return msg;
    }

    private void showUpperLine() {
        System.out.println(DIVISION_LINE);
    }

    private void showLowerLine() {
        System.out.println("\n" + DIVISION_LINE);
    }

    public String showErrorMessage(String message) {
        showUpperLine();
        System.out.println(message);
        showLowerLine();
        return message;
    }
}
