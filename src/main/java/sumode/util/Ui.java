package sumode.util;
import sumode.task.*;

import java.util.List;

public class Ui {
    private static final String LOGO = """
                           ___
                          |* *|
                          |\\_/|
                  ---------------------
                  |                   |
                -----     SUMO      -----
                | | |               | | |
                -----      DE       -----
                  |                   |
                  ---------------------
                  |                   |
                  |      /-----\\      |
                  |_____/       \\_____|
                """;

    private static final String GOODBYE = """
                ------------------------------------
                Goodbye! Sumo hope to see you again!
                ------------------------------------
                """;



    /**
     * Prints a greeting message to user.
     */
    public void greet() {
        System.out.println("------------------------------------\n"
                + "    Hello, I am Sumo-DE\n"
                + LOGO
                + '\n'
                + " How can Sumo help you?\n"
                + "------------------------------------"
        );
    }

    /**
     * Prints a notice that command is wrong.
     */
    public void unknownCommand (String commandString) {
        System.out.println("Sumo dunno your command \"" + commandString +"\" ! Check spelling of your first word.");
    }

    /**
     * Prints error message.
     */
    public void handleError (Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a query asking for next command.
     */
    public void next() {
        System.out.println("""
                            ------------------------------------
                            Do you need anything else from SUMO?
                            ------------------------------------""");
    }

    /**
     * Prints goodbye message.
     */
    public void bye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints a notice that user won't be able to save any data in this session.
     */
    public void unknownSaveError() {
        System.out.println("Help! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save");
    }

    /**
     * Prints a notice that user's latest change is unable to be saved.
     */
    public void latestSaveError() {
        System.out.println("Sumo cannot save latest change.");
    }

    /**
     * Prints a notice that user's saved file is corrupted and inform user which line it is at.
     * @param line line where the file is corrupted.
     */
    public void corruptedSaveFile(int line) {
        System.out.println("Your saved file at line " + (line) + " is corrupted. "
                        + "Sumo cannot read so Sumo will skip that and continue with the rest!");
    }

    /**
     * Prints all the tasks given in the task list.
     * @param tasks list of task to be printed.
     */
    public void printTask(List<Task> tasks) {
        System.out.println("Below is the list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task == null) {
                break;
            }
            System.out.println((i+1) + ". "+ task);
        }
    }

    /**
     * Prints a notice that user's task is added to the list.
     * @param task task added.
     * @param count total task after adding.
     */
    public void addTask(Task task, int count) {
        System.out.println("Sumo has added this task for you.\n"
                + task
                + "\n"
                + "There are now "
                + count
                + " task(s) in total!");
    }

    /**
     * Prints a notice that user's task is removed from the list.
     * @param task task removed.
     * @param count total task after removing.
     */
    public void removeTask(Task task, int count) {
        System.out.println("Sumo removed this task for you.\n"
                        + task
                        + "\n"
                        + "There are now "
                        + count
                        + " task(s) in total!");
    }

    /**
     * Prints a notice that user's task is marked.
     * @param task task marked.
     */
    public void unmark(Task task) {
        System.out.println("Sumo has marked this task as NOT done.");
        System.out.println(task);
    }

    /**
     * Prints a notice that user's task is unmarked.
     * @param task task unmarked.
     */
    public void mark(Task task) {
        System.out.println("Sumo has marked this task as done.");
        System.out.println(task);
    }
}
