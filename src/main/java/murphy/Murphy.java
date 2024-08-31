package murphy;

import murphy.storage.Storage;
import murphy.task.Deadline;
import murphy.task.Event;
import murphy.task.Task;
import murphy.task.TaskList;
import murphy.task.Todo;
import murphy.ui.Ui;

public class Murphy {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Murphy(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (MurphyException e) {
            ui.showError(String.format("Error loading save file: %s", e.getMessage()));
            tasks = new TaskList();
        }
    }
    public void run() {
        /*
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        */
        boolean isClosed = false;
        while(!isClosed) {
            String input = ui.getInput();
            if (input.equals("bye")) {
                isClosed = true;
                ui.showText("Bye. Hope to see you again soon!");
                ui.closeScanner();
                try {
                    storage.write(tasks);
                } catch (MurphyException e) {
                    ui.showError(String.format("Error saving tasks to storage: %s", e.getMessage()));
                }
                continue;
            }

            if (input.equals("list")) {
                ui.showText(tasks.toString());
            }

            else if (input.startsWith("mark ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    ui.showError("mark usage: \"mark [task number]\"");
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    ui.showError("mark usage: \"mark [task number]\"");
                    continue;
                }
                try {
                    ui.showText(tasks.markItem(index));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error marking task: %s", e.getMessage()));
                }
            }

            else if (input.startsWith("unmark ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    ui.showError("unmark usage: \"unmark [task number]\"");
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    ui.showError("unmark usage: \"unmark [task number]\"");
                    continue;
                }
                try {
                    ui.showText(tasks.unmarkItem(index));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error unmarking task: %s", e.getMessage()));
                }
            }

            else if (input.startsWith("delete ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    ui.showError("delete usage: \"delete [task number]\"");
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    ui.showError("delete usage: \"delete [task number]\"");
                    continue;
                }
                try {
                    ui.showText(tasks.deleteItem(index));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error deleting task: %s", e.getMessage()));
                }
            }

            else if(input.startsWith("todo ")) {
                try {
                    Task todo = new Todo(input.substring(5));
                    ui.showText(tasks.addItem(todo));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error adding todo: %s", e.getMessage()));
                }
            }

            else if(input.startsWith("deadline ")) {
                if (!input.contains("/by ")) {
                    ui.showError("deadline usage: \"deadline [description] /by [date]\"");
                    continue;
                }
                String[] split = input.split("/by ");
                try {
                    Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
                    ui.showText(tasks.addItem(deadline));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error adding deadline: %s", e.getMessage()));
                }
            }

            else if (input.startsWith("event ")) {
                if (!input.contains("/from ") || !input.contains("/to ")) {
                    ui.showError("event usage: \"event [description] /from [date] /to [date]\"");
                    continue;
                }
                String[] split = input.split("/from ");
                String[] split2 = split[1].split("/to ");
                try {
                    Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
                    ui.showText(tasks.addItem(event));
                } catch (MurphyException e) {
                    ui.showError(String.format("Error adding event: %s", e.getMessage()));
                }
            } else if (input.startsWith("find ")) {
                try {
                    ui.showText(tasks.find(input.substring(5)));
                } catch (MurphyException e) {
                    ui.showError(e.getMessage());
                }
            }

            else {
                ui.showError("Command not found");
            }
        }
    }

    public static void main(String[] args){
        new Murphy("./data/murphy.txt").run();
    }
}
