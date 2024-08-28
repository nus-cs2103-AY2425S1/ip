import java.io.IOException;
import java.util.ArrayList;

public class Niko {
    private final String name;
    private final Ui ui;
    private final TaskManager taskManager;
    private final DateTimeParser dateTimeParser;
    private final Storage storage;
    public String readyToWrite;
    public Niko(String name) {
        this.name = name;
        this.dateTimeParser = new DateTimeParser();
        this.ui = new Ui();
        this.taskManager = new TaskManager();
        this.storage = new Storage("D://example.txt");
    }

    public void start() {
        ui.showWelcomeMessage(this.name);
        try {
            ArrayList<Task> tasks = storage.load();
            for (Task task : tasks) {
                taskManager.addTask(task);
            }
            ui.showLoadSuccessMessage(tasks.size());
        } catch (IOException e) {
            ui.showErrorMessage("Error loading tasks from file: " + e.getMessage());
        }

        while (true) {
            try {
                String input = ui.getUserInput();

                if (input.equals("bye")) {
                    break;
                }

                handleInput(input);

            } catch (NikoException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }

        ui.showGoodbyeMessage();
    }

    private void handleInput(String input) throws NikoException {
        if (input.startsWith("todo")) {
            if(input.length()>5){
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    throw new NikoException("The description of a todo cannot be empty.");
                }
                taskManager.addTask(new Todo(description));
                ui.showAddTaskMessage(taskManager.getLastTask(), taskManager.getTaskCount());
                readyToWrite = taskManager.getTasks().toString();
                storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));
            }else{
                throw new NikoException("The description of a todo cannot be empty.");
            }


        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by ");
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new NikoException("The description or deadline of a deadline cannot be empty.");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            taskManager.addTask(new Deadline(description, by));
            ui.showAddTaskMessage(taskManager.getLastTask(), taskManager.getTaskCount());
            readyToWrite = taskManager.getTasks().toString();
            storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));

        } else if (input.startsWith("event")) {
            String[] parts = input.substring(6).split("/from |/to ");
            if (parts.length < 3 || parts[0].trim().isEmpty()) {
                throw new NikoException("The description or date/time of an event cannot be empty.");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            taskManager.addTask(new Event(description, from, to));
            ui.showAddTaskMessage(taskManager.getLastTask(), taskManager.getTaskCount());
            readyToWrite = taskManager.getTasks().toString();
            storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));

        } else if (input.startsWith("search")) {
        if (input.length() > 7) {
            String date = input.substring(7).trim();
            if (!date.isEmpty()) {
                dateTimeParser.searchTasks(date,this.taskManager);
            } else {
                try {
                    throw new NikoException("The description of a search cannot be empty.");
                } catch (NikoException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                throw new NikoException("The description of a search cannot be empty.");
            } catch (NikoException e) {
                throw new RuntimeException(e);
            }
        }
        } else if (input.startsWith("list")) {
            ui.showTaskList(taskManager.getTasks());

        } else if (input.startsWith("mark")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            taskManager.markTaskAsDone(taskIndex);
            ui.showMarkTaskMessage(taskManager.getTask(taskIndex));
            readyToWrite = taskManager.getTasks().toString();
            storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));


        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            taskManager.unmarkTaskAsDone(taskIndex);
            ui.showUnmarkTaskMessage(taskManager.getTask(taskIndex));
            readyToWrite = taskManager.getTasks().toString();
            storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));


        } else if (input.startsWith("delete")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = taskManager.deleteTask(taskIndex);
            ui.showDeleteTaskMessage(removedTask, taskManager.getTaskCount());
            readyToWrite = taskManager.getTasks().toString();
            storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));

        } else {
            throw new NikoException("I'm sorry lah, I don't know what that means.");
        }
    }
}
