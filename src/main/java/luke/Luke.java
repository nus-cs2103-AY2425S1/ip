package luke;

import luke.task.TaskList;

public class Luke {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
    public String getOutput(String input) {
        // Storage storage = new Storage();
        TaskList taskList = new TaskList();
        // Parser parser = new Parser();
        // Ui ui = new Ui(storage, parser, taskList);
        return Ui.handleUserInput(input);
    }
}
