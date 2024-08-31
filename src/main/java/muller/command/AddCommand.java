package muller.command;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

import java.time.LocalDate;

public class AddCommand extends Command {
    private String[] inputs;
    private String type;

    public AddCommand(String[] inputs, String type) {
        this.inputs = inputs;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException(type.equals("T") ? "Todo what?" : type.equals("D") ? "Deadline for what?" : "Event for what?");
        }
        String details = inputs[1].trim();
        Task task;

        switch (type) {
            case "T":
                task = new Task(details);
                task.setType("T");
                break;
            case "D":
                task = parseDeadline(details);
                break;
            case "E":
                task = parseEvent(details);
                break;
            default:
                throw new MullerException("Unknown task type!");
        }

        tasks.addTask(task);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();

        // Save the updated task list to the storage
        storage.save(tasks);
    }

    private Task parseDeadline(String details) throws MullerException {
        String[] detailParts = details.split("/by", 2);
        if (detailParts.length < 2) {
            throw new MullerException("Oops, you didn't specify the deadline!");
        }
        Task task = new Task(detailParts[0].trim());
        task.setType("D");
        LocalDate date = parseDate(detailParts[1].trim());
        task.setDate(date);
        return task;
    }

    private Task parseEvent(String details) throws MullerException {
        String[] detailParts = details.split("/from", 2);
        if (detailParts.length < 2) {
            throw new MullerException("Oops, you didn't specify the start date!");
        }
        String[] dateParts = detailParts[1].split("/to", 2);
        if (dateParts.length < 2) {
            throw new MullerException("You missed out either the start or end date!");
        }
        Task task = new Task(detailParts[0].trim());
        task.setType("E");
        LocalDate startDate = parseDate(dateParts[0].trim());
        LocalDate endDate = parseDate(dateParts[1].trim());
        task.setDateRange(startDate, endDate);
        return task;
    }

    private LocalDate parseDate(String dateStr) throws MullerException {
        try {
            return LocalDate.parse(dateStr, Task.INPUT_DATE_FORMATTER);
        } catch (Exception e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }
}

