package blue;

import blue.task.TaskList;

public class Blue {
    private TaskList tasklist;
    private Parser parser;

    public Blue() {
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        UI.greet();
        parser.parse(tasklist);
        UI.farewell();
    }
    public static void main(String[] args) {
        new Blue().run();
    }
}