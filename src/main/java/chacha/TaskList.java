package chacha;

import chacha.task.DeadlineTask;
import chacha.task.EventTask;
import chacha.task.Task;
import chacha.task.ToDoTask;

import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTotalNumber() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public Task addToDo(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 5) {
            String[] arrOfString = {
                    "What chacha.task are you intending to add as a \'todo\'?",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        } else {
            String description = cmd.substring(5);
            Task task = new ToDoTask(description, false);
            this.tasks.add(task);

            storage.writeFile(ui.printToDo(description));

            return task;
        }
    }

    public Task addEvent(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 6) {
            String[] arrOfString = {
                    "You are missing some info needed (chacha.task description, date, start time, end time).",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        String temp = cmd.substring(6);

        String[] arr = temp.split(" /");
        if (arr.length < 4) {
            // potential exception when arr does not have all elements needed
            String[] arrOfString = {
                    "You are missing some info needed (chacha.task description, date, start time, end time).",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        String description = arr[0];

        if (!arr[2].startsWith("from")) {
            // potential exception when deadline is not inputted well
            String[] arrOfString = {
                    "Please type start time in the form of \'from ...\'.",
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        if (!arr[3].startsWith("to")) {
            // potential exception when end time is not inputted well
            String[] arrOfString = {
                    "Please type end time in the form of \'to ...\'.",
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        LocalDate date = LocalDate.parse(arr[1]);
        String startTime = arr[2].substring(5);
        String endTime = arr[3].substring(3);
        Task task = new EventTask(description, false, date, startTime, endTime);
        this.tasks.add(task);

        storage.writeFile(ui.printEvent(description, date, startTime, endTime));

        return task;
    }

    public Task addDeadline(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 9) {
            String[] arrOfString = {
                    "You are missing some info needed (chacha.task description, deadline).",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        String temp = cmd.substring(9);
        String[] arr = temp.split(" /");
        if (arr.length < 2) {
            // potential exception when arr does not have all elements needed
            String[] arrOfString = {
                    "You are missing some info needed (chacha.task description, deadline).",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        String description = arr[0];

        if (!arr[1].startsWith("by")) {
            // potential exception when deadline is not inputted well
            String[] arrOfString = {
                    "Please type deadline in the form of \'by ...\'.",
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        LocalDate date = LocalDate.parse(arr[1].substring(3));
        Task task = new DeadlineTask(description, false, date);
        this.tasks.add(task);

        storage.writeFile(ui.printDeadline(description, date));

        return task;
    }

    public String printList(Ui ui) {
        String[] arrOfTasks = new String[this.getTotalNumber()];

        for (int i = 0; i < this.getTotalNumber(); i++) {
            String index = String.valueOf(i + 1);
            arrOfTasks[i] = index + ". " + this.getTask(i).printTask();
        }

        return ui.printList(arrOfTasks);
    }

    public Task deleteTask(String cmd, Ui ui, Storage storage) throws ChaChaException {

        if (cmd.length() <= 7) {
            String[] arrOfString = {
                    "You are missing the index of chacha.task you want to mark. ",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        int index = Integer.parseInt(cmd.substring(7));

        Task deletedTask = this.tasks.remove(index - 1);
        storage.overwriteFile(this);
        return deletedTask;
    }

    public Task markDone(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 5) {
            String[] arrOfString = {
                    "You are missing the index of chacha.task you want to mark. ",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        int index = Integer.parseInt(cmd.substring(5));

        Task markedTask = this.tasks.get(index - 1).markDone();
        storage.overwriteFile(this);
        return markedTask;
    }

    public Task markUndone(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 7) {
            String[] arrOfString = {
                    "You are missing the index of chacha.task you want to mark. ",
                    "Please type again!"
            };

            throw new ChaChaException(ui.printStrings(arrOfString));
        }

        int index = Integer.parseInt(cmd.substring(7));

        Task unmarkedTask = this.tasks.get(index - 1).markUndone();
        storage.overwriteFile(this);
        return unmarkedTask;
    }
}
