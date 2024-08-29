import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class LevelHundred {

    private final String pathToTaskFile = "src/main/data/level-hundred.txt";
    private final String name = "LevelHundred";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    
    public LevelHundred() {
        this.ui = new Ui();
        this.storage = new Storage(pathToTaskFile);
        this.taskList = new TaskList();
    }

    private Task createTask(String[] words, String command) throws LevelHundredException {
        if (command.equals("todo")) {
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }

            return new Todo(taskDescription);
        } else if (command.equals("deadline")) {
            List<String> tmp = Arrays.asList(words);
            int byIdx = tmp.indexOf("/by");
            if (byIdx == -1) {
                throw new MissingArgumentException(command, "by");
            }

            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, byIdx));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }

            String by = String.join(" ", Arrays.copyOfRange(words, byIdx + 1, words.length));
            if (by.equals("")) {
                throw new MissingArgumentException(command, "by");
            }

            return new Deadline(taskDescription, by);
        } else {
            List<String> tmp = Arrays.asList(words);
            int fromIdx = tmp.indexOf("/from");
            int toIdx = tmp.indexOf("/to");
            if (fromIdx == -1) {
                throw new MissingArgumentException(command, "from");
            }
            if (toIdx == -1) {
                throw new MissingArgumentException(command, "to");
            }

            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, fromIdx));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }
            String from = String.join(" ", Arrays.copyOfRange(words, fromIdx + 1, toIdx));
            if (from.equals("")) {
                throw new MissingArgumentException(command, "from");
            }

            String to = String.join(" ", Arrays.copyOfRange(words, toIdx + 1, words.length));
            if (to.equals("")) {
                throw new MissingArgumentException(command, "to");
            }

            return new Event(taskDescription, from, to);
        }
    }

    private void handleAddTask(String[] words, String command) {
        try {
            Task newTask = createTask(words, command);
            this.taskList.addTask(newTask);
            this.storage.update(this.taskList.getTaskList());
            this.ui.printAddTask(newTask, this.taskList.size());
        } catch (LevelHundredException e) {
            this.ui.printException(e);
        }
    }

    private void handleUpdateTaskStatus(String[] words, String command) {
        if (words.length == 1) {
            this.ui.printException(new MissingArgumentException(command, "task index"));
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            this.ui.printException(new InvalidArgumentException(command, invalidString));
            return;
        }

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = this.taskList.getTaskList().get(idx);
            if (command.equals("mark")) {
                t.mark();
                this.ui.printSuccessfulMark(t);
            } else {
                t.unmark();
                this.ui.printSuccessfulUnmark(t);
            }
            this.storage.update(this.taskList.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            this.ui.printException(new InvalidArgumentException("task index", words[1]));
        }
    }

    private void handleDeleteTask(String[] words) {
        if (words.length == 1) {
            this.ui.printException(new MissingArgumentException("delete", "task index"));
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            this.ui.printException(new InvalidArgumentException("delete", invalidString));
            return;
        }

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = this.taskList.removeTask(idx);
            this.storage.update(this.taskList.getTaskList());
            this.ui.printDeleteTask(t, this.taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            this.ui.printException(new InvalidArgumentException("task index", words[1]));
        }
    }

    private void initialiseTaskList() {
        try {
            ArrayList<Task> tasks = this.storage.load();
            tasks.forEach(t -> this.taskList.addTask(t));
        } catch (InvalidStorageFileException e) {
            this.ui.printException(e);
        }
    }

    private void run() {
        this.initialiseTaskList();

        this.ui.greet(this.name);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String userInput = "";

        while (isRunning) {
            userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            String command = words[0];
            switch(command) {
                case "bye":
                    isRunning = false;
                    this.ui.exit();
                    break;
                case "list":
                    ArrayList<Task> tasks = this.taskList.getTaskList();
                    this.ui.printTasks(tasks);
                    break;
                case "mark": case "unmark":
                    this.handleUpdateTaskStatus(words, command);
                    break;
                case "todo": case "deadline": case "event":
                    this.handleAddTask(words, command);
                    break;
                case "delete":
                    this.handleDeleteTask(words);
                default:
                    this.ui.printException(new InvalidCommandException());
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
