public class Agave {

    private UserInterface ui;
    private boolean isRunning;
    private Task taskManager;

    public Agave() {
        this.ui = new UserInterface();
        this.isRunning = true;
        this.taskManager = new Task();
    }

    public void run() {
        ui.showWelcome();

        while (isRunning) {
            try {
                String userInput = ui.getUserInput("Enter a command: ");
                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                    ui.showBye();
                } else if (userInput.equalsIgnoreCase("list")) {
                    ui.showTasks(taskManager.getTasks(), taskManager.getTaskCount());
                } else if (userInput.startsWith("mark")) {
                    handleMark(userInput);
                } else if (userInput.startsWith("unmark")) {
                    handleUnmark(userInput);
                } else if (userInput.startsWith("todo")) {
                    handleTodoInput(userInput);
                } else if (userInput.startsWith("deadline")) {
                    handleDeadlineInput(userInput);
                } else if (userInput.startsWith("event")) {
                    handleEventInput(userInput);
                } else {
                    throw new AgaveException("I'm sorry, but I don't understand the command: " + userInput);
                }
            } catch (AgaveException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void handleMark(String userInput) throws AgaveException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                taskManager.getTasks()[taskNumber - 1].markAsDone();
                ui.showMarkedTask(taskManager.getTasks()[taskNumber - 1]);
            } else {
                throw new AgaveException("Task number is out of range. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new AgaveException("Please enter a valid task number.");
        }
    }

    private void handleUnmark(String userInput) throws AgaveException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                taskManager.getTasks()[taskNumber - 1].unmarkAsDone();
                ui.showUnmarkedTask(taskManager.getTasks()[taskNumber - 1]);
            } else {
                throw new AgaveException("Task number is out of range. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new AgaveException("Please enter a valid task number.");
        }
    }

    private void handleTodoInput(String userInput) throws AgaveException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new AgaveException("The description of a todo cannot be empty.");
        }
        taskManager.addTask(new ToDo(description));
        System.out.println("Added todo:");
        System.out.println(" [T][ ] " + description);
        taskManager.showNumberOfTasks();
    }

    private void handleDeadlineInput(String userInput) throws AgaveException {
        try {
            String[] split = userInput.split(" /by ");
            String description = split[0].substring(8).trim();
            String by = split[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new AgaveException("The description and deadline of a task cannot be empty");
            }
            taskManager.addTask(new Deadline(description, by));
            System.out.println("Added deadline:");
            System.out.println("  [D][ ] " + description + " (by: " + by + ")");
            taskManager.showNumberOfTasks();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the deadline in the correct format: 'deadline <description> /by <date/time>'.");
        }
    }

    private void handleEventInput(String userInput) throws AgaveException {
        try {
            String[] split = userInput.split(" /from | /to ");
            String description = split[0].substring(5).trim();
            String from = split[1].trim();
            String to = split[2].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new AgaveException("The description, start time, and end time of an event cannot be empty.");
            }
            taskManager.addTask(new Event(description, from, to));
            System.out.println("Added event:");
            System.out.println("  [E][ ] " + description + " (from: " + from + " to: " + to + ")");
            taskManager.showNumberOfTasks();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the event in the correct format: 'event <description> /from <start time> /to <end time>'.");
        }
    }

    public static void main(String[] args) {
        Agave agave = new Agave();
        agave.run();
    }
}