package luke;

import luke.task.TaskList;

public class Luke {
    public static void main(String[] args) {
        System.out.println();
    }
    public String getOutput(String input) {
        TaskList taskList = new TaskList();
        return Ui.handleUserInput(input);
    }
}
