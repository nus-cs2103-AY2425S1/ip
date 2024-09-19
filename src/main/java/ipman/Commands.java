package ipman;

import java.util.ArrayList;

public class Commands {
    TextUi textUi;
    ArrayList<Task> taskList;
    String line;

    /* Constructs a Commands instance with the specified user interface and task list. */
    public Commands(TextUi textUi, ArrayList<Task> taskList, String line) {
        this.textUi = textUi;
        this.taskList = taskList;
        this.line = line;
    }

    /* Parses the input command and executes the corresponding action. */
    public void list() {
        textUi.addToBuffer("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            textUi.addToBuffer((i + 1) + ". " + taskList.get(i));
        }
        // textUi.printBuffer();
    }

    public void find() throws CommandException {
        if (!line.contains(" ")) {
            throw new CommandException("The search string must not be empty.");
        }
        String query = line.split(" ", 2)[1];
        textUi.addToBuffer("Here are the matching tasks in your list:");
        int displayIndex = 1;
        for (Task task : taskList) {
            if (String.valueOf(task).contains(query)) {
                textUi.addToBuffer((displayIndex++) + ". " + task);
            }
        }
    }

    public void delete() throws CommandException {
        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
        if (idx < 0 || idx >= taskList.size()) {
            throw new CommandException("The item to delete does not exist.");
        }
        textUi.addToBuffer("Noted. I've removed this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(idx)));
        taskList.remove(idx);
        textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void unmark() throws CommandException {
        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
        if (idx < 0 || idx >= taskList.size()) {
            throw new CommandException("The item to unmark does not exist.");
        }
        textUi.addToBuffer("OK, I've marked this task as not done yet:");
        textUi.addToBuffer(taskList.get(idx).unmarkStatus());
    }

    public void mark() throws CommandException {
        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
        if (idx < 0 || idx >= taskList.size()) {
            throw new CommandException("The item to mark does not exist.");
        }
        textUi.addToBuffer("Nice! I've marked this task as done:");
        textUi.addToBuffer(taskList.get(idx).markStatus());
    }

    public void todo() throws CommandException {
        if (!line.contains(" ")) {
            throw new CommandException("The description of a todo cannot be empty.");
        }
        String desc = line.split(" ", 2)[1];
        taskList.add(new Todo(desc));
        textUi.addToBuffer("Got it. I've added this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(taskList.size() - 1)));
        textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void deadline() throws CommandException {
        if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
            throw new CommandException("The description of the deadline must not be empty.");
        }
        String[] tail = line.split(" ", 2)[1].split(" /by ");
        taskList.add(new Deadline(tail[0], tail[1]));
        textUi.addToBuffer("Got it. I've added this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(taskList.size() - 1)));
        textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void event() throws CommandException {
        if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
            throw new CommandException("The description of the event must not be empty.");
        }
        String desc = line.split(" ", 2)[1].split(" /from ")[0];
        String from = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[0];
        String to = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[1];
        taskList.add(new Event(desc, from, to));
        textUi.addToBuffer("Got it. I've added this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(taskList.size() - 1)));
        textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void tag() throws CommandException {
        if (line.split(" ").length < 3) {
            throw new CommandException("The tag command must have a task number and a tag.");
        }
        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
        if (idx < 0 || idx >= taskList.size()) {
            throw new CommandException("The item to tag does not exist.");
        }
        String tag = line.split(" ", 3)[2];
        taskList.get(idx).addTag(tag);
        textUi.addToBuffer("Got it. I've added the tag to this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(idx)));
    }

    public void untag() throws CommandException {
        if (line.split(" ").length < 3) {
            throw new CommandException("The untag command must have a task number and a tag.");
        }
        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
        if (idx < 0 || idx >= taskList.size()) {
            throw new CommandException("The item to untag does not exist.");
        }
        String tag = line.split(" ", 3)[2];
        taskList.get(idx).removeTag(tag);
        textUi.addToBuffer("Got it. I've removed the tag from this task:");
        textUi.addToBuffer(String.valueOf(taskList.get(idx)));
    }
}
