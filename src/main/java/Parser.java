import java.io.IOException;

public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void handleInput(String userInput) throws IOException, WrongDateTimeFormatException,
            MissingStartEndTimeException, MissingDeadlineException, MissingDescriptionException,
            WrongInputException {

        if (userInput.equals("bye")) {
            Storage.updateFile(taskList.getTaskList());
            Ui.goodbyeMessage();
        } else if (userInput.equals("list")) {
            taskList.toString();
        } else if (userInput.startsWith("check ")) {
            taskList.tasksOnDate(userInput.substring(6).trim());
        } else if (userInput.startsWith("mark ")) {
            int x = Integer.parseInt(userInput.substring(5).trim());
            taskList.completeTask(x - 1);
        } else if (userInput.startsWith("unmark ")) {
            int x = Integer.parseInt(userInput.substring(7).trim());
            taskList.incompleteTask(x - 1);
        } else if (userInput.startsWith("delete ")) {
            int x = Integer.parseInt(userInput.substring(7).trim());
            taskList.deleteTask(x - 1);
        } else {
            taskList.addTask(userInput);
        }
    }
}
