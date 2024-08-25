public class Parser {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isExit;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public void parse(String command) {
        if (command.equals("bye")) {
            storage.save(tasks);
            ui.goodbye();
            isExit = true;
        } else if (command.equals("list")) {
            tasks.listTasks();
        } else if (command.startsWith("mark")) {
            tasks.markTask(Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("unmark")) {
            tasks.unmarkTask(Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("todo")) {
            try {
                tasks.addTask(command, TaskList.TaskType.TODO);
            } catch (PikappiException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.startsWith("deadline")) {
            try {
                tasks.addTask(command, TaskList.TaskType.DEADLINE);
            } catch (PikappiException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.startsWith("event")) {
            try {
                tasks.addTask(command, TaskList.TaskType.EVENT);
            } catch (PikappiException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.startsWith("delete")) {
            try {
                tasks.deleteTask(command);
            } catch (PikappiException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                throw new PikappiException("Pi-ka..?? I don't understand..");
            } catch (PikappiException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
