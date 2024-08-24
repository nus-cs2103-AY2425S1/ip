import java.util.List;

public class Ui {
    private static String LOGO = """
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

    private static String GOODBYE = """
                ------------------------------------
                Goodbye! Sumo hope to see you again!
                ------------------------------------
                """;



    public void greet() {
        System.out.println("------------------------------------\n"
                + "    Hello, I am Sumo-DE\n"
                + LOGO
                + '\n'
                + " How can Sumo help you?\n"
                + "------------------------------------"
        );
    }

    public void unknownCommand (String commandString) {
        System.out.println("Sumo dunno your command \"" + commandString +"\" ! Check spelling of your first word.");
    }

    public void handleError (Exception e) {
        System.out.println(e.getMessage());
    }

    public void next() {
        System.out.println("""
                            ------------------------------------
                            Do you need anything else from SUMO?
                            ------------------------------------""");
    }

    public void bye() {
        System.out.println(GOODBYE);
    }

    public void unknownSaveError() {
        System.out.println("Help! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save");
    }

    public void latestSaveError() {
        System.out.println("Sumo cannot save latest change.");
    }

    public void corruptedSaveFile(int line) {
        System.out.println("Your saved file at line " + (line) + " is corrupted. "
                        + "Sumo cannot read so Sumo will skip that and continue with the rest!");
    }


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

    public void addTask(Task task, int count) {
        System.out.println("Sumo has added this task for you.\n"
                + task
                + "\n"
                + "There are now "
                + count
                + " task(s) in total!");
    }

    public void removeTask(Task task, int count) {
        System.out.println("Sumo removed this task for you.\n"
                        + task
                        + "\n"
                        + "There are now "
                        + count
                        + " task(s) in total!");
    }

    public void unmark(Task task) {
        System.out.println("Sumo has marked this task as NOT done.");
        System.out.println(task);
    }

    public void mark(Task task) {
        System.out.println("Sumo has marked this task as done.");
        System.out.println(task);
    }
}
