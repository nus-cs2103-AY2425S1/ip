package noisy;

import java.time.LocalDate;

public class Parser {

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public String parseInput(String input, TaskList taskList, Storage storage, Ui ui) {
        Task task = null;
        ui.printWelcome();
        if (input.equals("bye")) {
            return ui.printGoodbye();
        }
        if (input.equals("list")) {
            return ui.printList(taskList);
        }
        if (input.startsWith("mark ")) {
            String[] string = input.split(" ");
            Integer index = Integer.parseInt(string[1]);
            taskList.markDoneFromList(index - 1);
            return ui.printMark(index, taskList);
        }

        if (input.startsWith("find ")) {
            String keyword = input.split(" ", 2)[1];
            return ui.printFind(taskList, keyword);
        }

        try {
            switch (input.split(" ")[0]) {
                case "todo":
                    if (input.split(" ", 2).length < 2) {
                        throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    task = new Todo(input.split(" ", 2)[1], false);
                    break;
                case "Deadline":
                    input = input.trim();
                    String[] deadlineParts = input.split(" ", 4);
                    task = new Deadline(deadlineParts[1], Boolean.parseBoolean(deadlineParts[2]), this.parseDate(deadlineParts[3]));
                    break;
                case "Event":
                    String[] eventParts = input.split(" ", 5);
                    task = new Event(eventParts[1], false, this.parseDate(eventParts[3]), this.parseDate(eventParts[4]));
                    break;
                case "delete":
                    String[] deleteParts = input.split(" ");
                    Integer index = Integer.parseInt(deleteParts[1]);
                    Task deletedTask = taskList.getTask(index - 1);
                    taskList.deleteFromList(index - 1);
                    return ui.printDelete(index - 1, taskList);
                default:
                    throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NoisyException e) {
            System.out.println(e);
        }

        taskList.addToList(task);
        storage.saveTasks(taskList.getTasks());
        return ui.printAdd(task, taskList);
    }
}

