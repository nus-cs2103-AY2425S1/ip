package lumina.task;
import lumina.ui.Ui;
import lumina.parser.Parser;
import lumina.exception.LuminaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    // tasks
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;
    
    public TaskList(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
        this.tasks = new ArrayList<>();
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
        StringBuilder addTaskMessage = new StringBuilder();
        addTaskMessage.append("Got it. I've added this task:\n");
        addTaskMessage.append(ui.indentMessage(task.toString())).append("\n");
        addTaskMessage.append(String.format("Now you have %d tasks in the list.", tasks.size()));
        ui.printMessage(addTaskMessage.toString());
    }

    public void listTasks() {
        StringBuilder listedTaskMessage = new StringBuilder();
        listedTaskMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            listedTaskMessage.append(Integer.toString(i + 1)).append(".");
            listedTaskMessage.append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                listedTaskMessage.append("\n");
            }
        }
        ui.printMessage(listedTaskMessage.toString());
    }

    public ArrayList<String> toLines() {
        return tasks.stream()
                .map(Task::saveString)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public void markTaskDone(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected index out of bounds! Please try again");
        }
        StringBuilder taskDoneMessage = new StringBuilder();
        taskDoneMessage.append("Nice! I've marked this task as done:\n");
        this.tasks.get(index).markAsDone();
        taskDoneMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        ui.printMessage(taskDoneMessage.toString());
    }

    public void markTaskNotDone(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected index out of bounds! Please try again");
        }
        StringBuilder taskNotDoneMessage = new StringBuilder();
        taskNotDoneMessage.append("OK, I've marked this task as not done yet:\n");
        this.tasks.get(index).markAsNotDone();
        taskNotDoneMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        ui.printMessage(taskNotDoneMessage.toString());
    }

    public void handleTodoTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if(msgSplit.length < 2) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your ToDo Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < msgSplit.length; i++) {
            builder.append(msgSplit[i]);
            if(i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }
        Task task = new TodoTask(builder.toString());
        this.addTask(task);

    }

    public void handleDeadlineTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if(msgSplit.length < 4) { // need desc and bydatetime
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Deadline Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }

        StringBuilder builder = new StringBuilder();
        String desc = "", byDateTime = "";
        boolean by = false;

        for(int i = 1; i < msgSplit.length; i++) {
            if(msgSplit[i].equals("/by")) {
                if(by) {
                    throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Deadline Lumina.Main.Lumina.Task.Task!" +
                            " Please try again");
                }
                by = true;
                desc = builder.toString().trim();
                builder = new StringBuilder();
                continue;
            }
            builder.append(msgSplit[i]);
            if (i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }

        if (!by) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Deadline Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }
        byDateTime = builder.toString().trim();
        if (desc.isEmpty() || byDateTime.isEmpty()) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Deadline Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }
        LocalDate byDateObject = parser.parseDateString(byDateTime);
        Task task = new DeadlineTask(desc, byDateObject);
        this.addTask(task);
    }

    public void deleteTask(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected index out of bounds! Please try again");
        }
        StringBuilder deleteTaskMessage = new StringBuilder();
        deleteTaskMessage.append("Noted. I've removed this task:\n");
        deleteTaskMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        deleteTaskMessage.append("\n");
        this.tasks.remove(index);
        deleteTaskMessage.append(String.format("Now you have %d tasks in the list.", tasks.size()));
        ui.printMessage(deleteTaskMessage.toString());
    }

    public void findTasks(String searchString) {
        StringBuilder findTasksMessage = new StringBuilder();
        findTasksMessage.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if(this.tasks.get(i).findInDescription(searchString)) {
                if (count > 1) {
                    findTasksMessage.append("\n");
                }
                findTasksMessage.append(Integer.toString(count)).append(".");
                findTasksMessage.append(this.tasks.get(i));
                count++;
            }
        }
        ui.printMessage(findTasksMessage.toString());
    }

    public void handleEventTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if(msgSplit.length < 6) { // need desc, startDateTime, endDateTime
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Event Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }

        enum Type {
            DESC, FROM, TO
        }

        Type currentType = Type.DESC;

        StringBuilder builder = new StringBuilder();
        String desc = "", startDateTime = "", endDateTime = "";
        boolean from = false;
        boolean to = false;
        for(int i = 1; i < msgSplit.length; i++) {
            if(msgSplit[i].equals("/from")) {
                if (from) {
                    throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Event Lumina.Main.Lumina.Task.Task!" +
                            " Please try again");
                }
                switch (currentType) {
                    case DESC:
                        desc = builder.toString().trim();
                        break;
                    case FROM:
                        startDateTime = builder.toString().trim();
                        break;
                    case TO:
                        endDateTime = builder.toString().trim();
                        break;
                }
                builder = new StringBuilder();
                from = true;
                currentType = Type.FROM;
                continue;
            }
            if (msgSplit[i].equals("/to")) {
                if (to) {
                    throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Event Lumina.Main.Lumina.Task.Task!" +
                            " Please try again");
                }
                switch (currentType) {
                    case DESC:
                        desc = builder.toString().trim();
                        break;
                    case FROM:
                        startDateTime = builder.toString().trim();
                        break;
                    case TO:
                        endDateTime = builder.toString().trim();
                        break;
                }
                builder = new StringBuilder();
                to = true;
                currentType = Type.TO;
                continue;
            }
            builder.append(msgSplit[i]);
            if (i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }

        if (!from || !to) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Event Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }
        switch (currentType) {
            case DESC:
                desc = builder.toString().trim();
                break;
            case FROM:
                startDateTime = builder.toString().trim();
                break;
            case TO:
                endDateTime = builder.toString().trim();
                break;
        }
        if (desc.trim().isEmpty() || startDateTime.trim().isEmpty() || endDateTime.trim().isEmpty()) {
            throw new LuminaException("Oh no! Lumina.Main.Lumina detected invalid format for your Event Lumina.Main.Lumina.Task.Task!" +
                    " Please try again");
        }
        LocalDate startDateObject = parser.parseDateString(startDateTime);
        LocalDate endDateObject = parser.parseDateString(endDateTime);
        Task task = new EventTask(desc, startDateObject, endDateObject);
        this.addTask(task);
    }

    public void setData(ArrayList<Task> data) {
        tasks = data;
    }
}
