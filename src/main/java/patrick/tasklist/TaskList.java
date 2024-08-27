package patrick.tasklist;

import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) throws Parser.PatrickException {
        if (!taskList.isEmpty()) {
            this.taskList = taskList;
        } else {
            throw new Parser.PatrickException("Something wrong!");
        }
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public static void delete(String input) throws Parser.PatrickException {
        String taskNo = input.replace("delete", "").trim();
        try {
            Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new Parser.PatrickException("Delete Task Details must be an integer");
        }
        int num = Integer.parseInt(taskNo);
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Delete Task Details cannot be empty!!");
        }
        else if (num > Storage.getList().size()){
            throw new Parser.PatrickException("Input task index is invalid. Please try again!!");
        }
        else {
            Ui.showDeleteItemMsg(num);
            Storage.deleteItem(num);
            try {
                Storage.writeToFile();
            } catch (IOException e) {
                System.out.println("There is an error: " + e.getMessage());
            }
        }
    }
    public static void mark(String input) throws Parser.PatrickException {
        String taskNo = input.replace("mark", "").trim();
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Task Number cannot be empty!!");
        } else {
            try {
                Integer.parseInt(taskNo);
            } catch (NumberFormatException e) {
                throw new Parser.PatrickException("Mark Task Details must be an integer");
            }
            int num = Integer.parseInt(taskNo);
            if (num > Storage.getList().size()) {
                throw new Parser.PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) Storage.getList().get(num - 1);
                if (curr.isDone) {
                    throw new Parser.PatrickException("You cannot mark a completed task!!");
                } else {
                    curr.markAsDone();
                    Ui.showMarkUnmarkMsg("Nice! I've marked this task as done:\n  ", curr.toString()+ "\n");
                    try {
                        Storage.writeToFile();
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }

    public static void unmark(String input) throws Parser.PatrickException {
        String taskNo = input.replace("unmark", "").trim();
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Task Number cannot be empty!!");
        } else {
            try {
                Integer.parseInt(taskNo);
            } catch (NumberFormatException e) {
                throw new Parser.PatrickException("Unmark Task Details must be an integer");
            }
            int num = Integer.parseInt(taskNo);

            if (num > Storage.getList().size()) {
                throw new Parser.PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) Storage.getList().get(num - 1);
                if (!curr.isDone) {
                    throw new Parser.PatrickException("You cannot unmark an incomplete task!!");
                } else {
                    curr.markAsUndone();
                    Ui.showMarkUnmarkMsg("Nice! I've marked this task as as not done yet:\n  ", curr.toString() + "\n");
                    try {
                        Storage.writeToFile();
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }
}
