public class Parser {
    public static boolean parse(String userInput, Ui ui, TaskList taskList, Storage storage) {
        String[] userInputArr = userInput.split(" ");
        String keyword = userInputArr[0];

        switch (keyword) {
            case "bye":
                return false;

            case "list":
                ui.showTaskList(taskList.getTasks());
                return true;

            case "mark":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to mark.");
                } else if (!taskList.isValidTaskIndex(userInputArr[1])) {
                    ui.showError("Task does not exist, please try again!");
                } else {
                    int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                    taskList.markTaskAsDone(taskIndex, ui);
                }
                return true;

            case "unmark":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to unmark.");
                } else if (!taskList.isValidTaskIndex(userInputArr[1])) {
                    ui.showError("Task does not exist, please try again!");
                } else {
                    int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                    taskList.markTaskAsUndone(taskIndex, ui);
                }
                return true;

            case "delete":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to delete.");
                } else if (!taskList.isValidTaskIndex(userInputArr[1])) {
                    ui.showError("Task does not exist, please try again!");
                } else {
                    int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                    taskList.deleteTask(taskIndex, ui);
                }
                return true;

            case "todo":
                String todoDesc = String.join(" ", userInputArr).substring(5).trim();
                if (todoDesc.isEmpty()) {
                    ui.showError("Todo 'description' is missing...");
                } else {
                    Task todo = new Todo(todoDesc);
                    taskList.addTask(todo, ui);
                }
                return true;

            case "deadline":
                String[] deadlineParts = userInput.split("/by");
                if (deadlineParts.length < 2) {
                    ui.showError("Deadline 'description' or 'by' input(s) is/are missing...");
                } else {
                    String deadlineDesc = deadlineParts[0].substring(9).trim();
                    String by = deadlineParts[1].trim();
                    try {
                        Task deadline = new Deadline(deadlineDesc, by);
                        taskList.addTask(deadline, ui);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                }
                return true;

            case "event":
                String[] eventParts = userInput.split("/from");
                if (eventParts.length < 2) {
                    ui.showError("Event 'description', 'from', or 'to' input(s) is/are missing...");
                } else {
                    String eventDesc = eventParts[0].substring(6).trim();
                    String[] fromTo = eventParts[1].split("/to");
                    if (fromTo.length < 2) {
                        ui.showError("Event 'from' or 'to' input(s) is/are missing...");
                    } else {
                        String from = fromTo[0].trim();
                        String to = fromTo[1].trim();
                        try {
                            Task event = new Event(eventDesc, from, to);
                            taskList.addTask(event, ui);
                        } catch (IllegalArgumentException e) {
                            ui.showError(e.getMessage());
                        }
                    }
                }
                return true;

            default:
                ui.showError("I don't know what that means... :(");
                return true;
        }
    }
}