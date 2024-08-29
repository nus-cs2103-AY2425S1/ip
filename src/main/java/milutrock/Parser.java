package milutrock;

import milutrock.exceptions.InvalidTaskFormatException;
import milutrock.exceptions.UnknownCommandException;
import milutrock.tasks.Deadline;
import milutrock.tasks.Event;
import milutrock.tasks.Task;
import milutrock.tasks.ToDo;

public class Parser {
    private String stdin;
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.stdin = "";
        this.taskList = taskList;
        this.ui = ui;
    }

    public String getStdin() {
        return this.stdin;
    }

    public boolean parseCommand(String input) throws UnknownCommandException {
        this.stdin += input + "\n";

        if (input.equals("bye")) {
            this.handleBye();
            return false;
        }

        String[] words = input.split("\\s+");

        if (input.equals("list")) {
            this.handleList();
        } else if (words.length == 2 && words[0].equals("mark")) {
            this.handleMark(words);
        } else if (words.length == 2 && words[0].equals("unmark")) {
            this.handleUnmark(words);
        } else if (words.length == 2 && words[0].equals("delete")) {
            this.handleDelete(words);
        } else if (
            words[0].equals("todo") || 
            words[0].equals("deadline") || 
            words[0].equals("event")
        ){
            this.handleAdd(words, input);
        } else {
            throw new UnknownCommandException(input);
        }

        return true;
    }

    private void handleBye() {
        ui.printByeMessage();
    }

    private void handleList() {
        ui.printTaskList();
    }

    private void handleMark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        taskList.markTaskAsDone(i);
        ui.printMarkMessage(i);
    }

    private void handleUnmark(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        taskList.unmarkTaskAsDone(i);
        ui.printUnmarkMessage(i);
    }

    private void handleDelete(String[] words) {
        int i = Integer.parseInt(words[1]) - 1;
        Task task = taskList.removeTask(i);
        ui.printDeleteMessage(task);
    }

    private void handleAdd(String[] words, String input) {
        try {
            Task task;
            if (words[0].equals("todo")) {
                task = ToDo.getToDoFromInput(input);
            } else if (words[0].equals("deadline")) {
                task = Deadline.getDeadlineFromInput(input);
            } else {
                // words[0] is guaranteed to be "event" here
                task = Event.getEventFromInput(input);
            }
            taskList.addTask(task);
            ui.printAddMessage();
        } catch (InvalidTaskFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
