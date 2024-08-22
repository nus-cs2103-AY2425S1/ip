package duke.ui;

public class Ui {

    private final static String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String greeting = "Hello from\n" + logo + "\nWhat can I do for you?";
    private final static String goodbye = "Bye! Hope to see you again soon!";

    public Ui() {}

    public void startup() {
        System.out.println(greeting);
    }

    public void shutdown() {
        System.out.println(goodbye);
    }

}
