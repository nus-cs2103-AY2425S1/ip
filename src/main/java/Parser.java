import java.io.IOException;

public class Parser {

    public void parse(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] input = userInput.split(" /", 2);
        String type = input[0].split(" ")[0].toLowerCase();
        String description = input[0].substring(type.length());
        try {
            switch (type) {
                case("bye"):
                    ui.printByeMessage();
                    System.exit(0);
                    break;
                case("list"):
                    ui.printTaskList(taskList);
                    break;
                case("mark"):
                    int position = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.get(position - 1).markDone();
                    ui.markedDone(taskList.get(position - 1));
                    storage.save(taskList);
                    break;
                case("unmark"):
                    int pos = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.get(pos - 1).undo();
                    ui.markedUndone(taskList.get(pos - 1));
                    storage.save(taskList);
                    break;
                case("todo"):
                    Task todo = new Todo(description);
                    taskList.addTask(todo);
                    ui.taskAddedMessage(todo, taskList.size());
                    storage.save(taskList);
                    break;
                case("deadline"):
                    String dueDate = input[1].split(" ", 2)[1].trim();
                    Task deadline = new deadline(description, dueDate);
                    taskList.addTask(deadline);
                    ui.taskAddedMessage(deadline, taskList.size());
                    storage.save(taskList);
                    break;
                case("event"):
                    String startTime = input[1].split(" /", 2)[0].split(" ", 2)[1].trim();
                    String endTime = input[1].split(" /", 2)[1].split(" ", 2)[1].trim();
                    Task event = new Event(description, startTime, endTime);
                    taskList.addTask(event);
                    ui.taskAddedMessage(event,taskList.size());
                    storage.save(taskList);
                    break;
                case("delete"):
                    int target = Integer.parseInt(userInput.split(" ")[1]);
                    Task temp = taskList.get(target - 1);
                    taskList.removeTask(target - 1);
                    ui.taskDeleted(temp, taskList.size());
                    storage.save(taskList);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
