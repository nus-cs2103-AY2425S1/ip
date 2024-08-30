package luke;

import luke.task.TaskList;

public class Luke {
    public static void main(String[] args) {
        String logo = " .____           __\n"
                + "|    |    __ __|  | __ ____\n"
                + "|    |   |  |  \\  |/ // __ \\\n"
                + "|    |___|  |  /    <\\  ___/\n"
                + "|_______ \\____/|__|_ \\\\___  >\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("""
                ____________________________________________________________
                i'm luke, your glorified task manager.
                i can keep a list of your todos, events, and deadlines.
                tell me what you need to keep track of. i'll help you out :)
                ____________________________________________________________
                """);
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Ui ui = new Ui(storage, parser, taskList);
        ui.start();
    }
}
