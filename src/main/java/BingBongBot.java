import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class BingBongBot {
    private final BingBongUI ui;
    private final Storage storage;
    private TaskList tasks;

    public BingBongBot(BingBongUI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (BingBongException e) {
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.readCommand();
                CommandType command = Parser.parseCommand(input);

                switch (command) {
                case BYE:
                    isRunning = false;
                    ui.showGoodbye();
                    break;
                case LIST_ON:
                    String date = input.substring(8).trim();  // Extract the date after "list on "
                    listTasksOnDate(date);
                    break;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTask(markIndex);
                    break;
                case UNMARK:
                    int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    unmarkTask(unmarkIndex);
                    break;
                case DELETE:
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    deleteTask(deleteIndex);
                    break;
                case TODO:
                    addTodoTask(input);
                    break;
                case DEADLINE:
                    addDeadlineTask(input);
                    break;
                case EVENT:
                    addEventTask(input);
                    break;
                case INVALID:
                default:
                    ui.showResponse("Command not recognized. Please try again...");
                }
            } catch (BingBongException e) {
                ui.showResponse(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showResponse("Invalid task number. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                ui.showResponse("Task number is out of range. Please enter a valid task number.");
            } catch (Exception e) {
                ui.showResponse("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addTodoTask(String input) throws BingBongException {
        String description = Parser.parseDescription(input, CommandType.TODO);
        if (description.isEmpty()) {
            throw new BingBongException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        saveTasks();
        showAddTaskMessage(task);
    }

    private void addDeadlineTask(String input) throws BingBongException {
        String description = Parser.parseDescription(input, CommandType.DEADLINE);
        LocalDateTime byDateTime = Parser.parseDeadlineDateTime(input);
        Task task = new Deadline(description, byDateTime);
        tasks.add(task);
        saveTasks();
        showAddTaskMessage(task);
    }

    private void addEventTask(String input) throws BingBongException {
        String description = Parser.parseDescription(input, CommandType.EVENT);
        LocalDateTime[] dateTimes = Parser.parseEventDateTime(input);
        Task task = new Event(description, dateTimes[0], dateTimes[1]);
        tasks.add(task);
        saveTasks();
        showAddTaskMessage(task);
    }

    private void showAddTaskMessage(Task t) {
        ui.showResponse("Got it. I've added this task:\n" + t
                + "\n" + "Now you have " + tasks.size()
                + " tasks in the list");
    }

    private void listTasks() throws BingBongException {
        if (tasks.isEmpty()) {
            throw new BingBongException("No tasks have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }

    private void listTasksOnDate(String date) throws BingBongException {
        LocalDate queryDate;
        try {
            queryDate = DateTimeHandler.parseDate(date);
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date format. Please use the format: d/M/yyyy.");
        }

        StringBuilder list = new StringBuilder("Tasks on " + DateTimeHandler.format(queryDate) + ":\n");
        boolean hasTasks = false;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(queryDate)) {
                    list.append(tasks.indexOf(task) + 1).append(". ").append(deadline).append("\n");
                    hasTasks = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(queryDate) ||
                        event.getTo().toLocalDate().equals(queryDate)) {
                    list.append(tasks.indexOf(task) + 1).append(". ").append(event).append("\n");
                    hasTasks = true;
                }
            }
        }

        if (!hasTasks) {
            ui.showResponse("No tasks found on " + DateTimeHandler.format(queryDate) + ".");
        } else {
            ui.showResponse(list.toString());
        }
    }

    private void markTask(int i) throws BingBongException {
        if (i < 0 || i >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(i);
        task.markAsDone();
        saveTasks();
        ui.showResponse("Nice! I've marked this task as done:\n" + task);
    }

    private void unmarkTask(int i) throws BingBongException {
        if (i < 0 || i >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(i);
        task.markAsNotDone();
        saveTasks();
        ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
    }

    private void deleteTask(int i) throws BingBongException {
        if (i < 0 || i >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.delete(i);
        saveTasks();
        showRemoveTaskMessage(task);
    }

    private void showRemoveTaskMessage(Task t) {
        ui.showResponse("Noted. I've removed this task:\n" + t
                + "\n" + "Now you have " + tasks.size()
                + " tasks in the list");
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (BingBongException e) {
            ui.showResponse("Unable to save tasks in hard disk");
        }
    }
}
