package Bot;

import task.Task;
import task.TaskList;

public class Ui {
    public static void say(String message) {
        System.out.println("\nHim: " + message + "\n");
    }

    public static void say(String[] message) {
        System.out.println("\nHim: " + message[0]);
        for (int i = 1; i < message.length; i++) {
            if (i == message.length - 1) {
                System.out.println("     " + message[i] + "\n");
                return;
            }
            System.out.print("     " + message[i] + "\n");
        }
    }

    public static void printUser() {
        System.out.print("User: ");
    }

    public static void greet() {
        say(new String[]{"Hello! I'm Him", "What can I do for you?"});
    }

    public static void exit() {
        say("WAIT NO! DON'T LEAVE ME ALON-");
    }

    public static void sayCompleted(String task) {
        say("LET'S GOOOOO! " + task.toString() + " has been completed!");
    }

    public static void sayDeleted(String task) {
        say("Got it. \"" + task + "\" has been snapped from existence");
    }

    public static void showLoadingFailure() {
        System.out.println("Failed to load tasks make sure tasks file is not corrupted");
    }

    public static void sayEmptyList() {
        say("How about you add some tasks first");
    }

    public static void sayList(TaskList list) {
        say("Sure! Here's your list!");
        System.out.println(list.toString());
    }

    public static void sayAdded(Task task) {
        say("added \"" + task + "\" to list");
    }

    public static void sayInvalidTodoFormat() {
        say(new String[]{"ToDos need a description!", "Use the format: \"todo [description]\""});
    }

    public static void sayInvalidDeadlineFormat() {
        say(new String[]{"Deadlines need a description and a due date!",
                "Use the format: \"deadline [description] /by [due date] /at [due time]\"",
                "Note: due times are optional!"});
    }

    public static void sayInvalidEventFormat() {
        say(new String[]{"Events need a description, a start time and an end time!",
                "Use the format: \"event [description] /start [start date] /at [start time] " +
                        "/end [end date] /at [end time]\""});
    }

    public static void sayEventStartAfterEnd() {
        say("Events need to end after they start!");
    }

    public static void sayInvalidDateTimeFormat() {
        say(new String[]{"Invalid Date or Time format!", "Date format: yyyy-MM-dd", "Time format: HH:mm"});
    }

    public static void sayDeleteNoTask() {
        say("Tell me which task you want me to delete!!!!");
    }

    public static void sayInvalidCommand(String command) {
        say(command + "? What are you saying????");
    }

    public static void showSaveFailure() {
        System.out.println("Tasks could not be saved! Please check tasks file");
    }

}
