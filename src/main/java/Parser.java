public class Parser {
    TaskList taskList;
    Ui ui;

    Storage storage;
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    };

    public boolean parse(String response) {
        String[] splited = response.split(" ",2);
        if (splited[0].equals("todo") || splited[0].equals("event") || splited[0].equals("deadline")) {
            handleAdd(splited);
        } else if (splited[0].equals("list")) {
            taskList.list();
        } else if (splited[0].equals("mark")) {
            handleMark(splited[1]);
        } else if (splited[0].equals("unmark")) {
            handleUnmark(splited[1]);
        } else if (splited[0].equals("delete")) {
            handleDelete(splited[1]);
        } else if (splited[0].equals("bye")) {
            return handleBye();
        } else {
            try {
                throw new DukeException("I dont understand what you are trying to say :(");
            } catch (DukeException e) {
                System.out.println("________________________________");
                System.out.println(e.getMessage() + "\n________________________________");
            }
        }
        return true;
    }
    public void handleAdd(String[] splits) {
        Task current = null;
        try {
            switch (splits[0]) {
                case "todo":
                    current = taskList.addTodo(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
                case "event":
                    current = taskList.addEvent(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
                case "deadline":
                    current = taskList.addDeadline(splits[1]);
                    ui.addMessage(current,taskList.size());
                    break;
            }
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "________________________________");
        }
    }

    public void handleMark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task marked = taskList.mark(index);
        if (marked != null) {
            ui.markedMessage(marked);
        }
    }

    public void handleUnmark(String description) {
        int index = Integer.parseInt(description) - 1;
        Task unmarked = taskList.unmark(index);
        if (unmarked != null) {
            ui.markedMessage(unmarked);
        }
    }

    public void handleDelete(String description) {
        int index = Integer.parseInt(description) - 1;
        Task deleted = taskList.delete(index);
        if (deleted != null) {
            ui.deletedMessage(deleted, taskList.size());
        }
    }

    public boolean handleBye() {
        this.storage.writeStorage(taskList.getTaskList());
        ui.byeMessage();
        return false;
    }
}
