package derek;

import derek.command.*;
import derek.exception.IncorrectCommandException;
import derek.task.Task;

import java.time.format.DateTimeParseException;

public class Parser {

    private String command;
    private Storage storage;
    private Ui ui;

    public Parser(String command, Storage storage, Ui ui) {
        this.command = command;
        this.storage = storage;
        this.ui = ui;
    }

    public void getCommand() {
        try {
            if (this.command.equals("bye")) {
                LeavingCommand leavingCommand = new LeavingCommand(this.command);
                leavingCommand.execute(this.storage, this.ui);
            } else if (this.command.equals("list")) {
                ListCommand listCommand = new ListCommand(this.command);
                listCommand.execute(this.storage, this.ui);
            } else {
                int size = this.storage.getTaskListSize();
                String[] words = command.split("\\s+");
                if (words.length > 1) {
                    if (words[0].equals("delete")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        DeleteCommand deleteCommand = new DeleteCommand(this.command);
                        deleteCommand.execute(taskNumber, this.storage, this.ui);
                        return;
                    } else if (words[0].equals("mark")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        CompleteCommand completeCommand = new CompleteCommand(this.command);
                        completeCommand.execute(this.storage, taskNumber, this.ui);
                        return;
                    } else if (words[0].equals("unmark")) {
                        int taskNumber = Integer.valueOf(words[1]);
                        if (taskNumber < 1 || taskNumber > size) {
                            throw new IncorrectCommandException("do you not know how to count????");
                        }
                        IncompleteCommand incompleteCommand = new IncompleteCommand(this.command);
                        incompleteCommand.execute(this.storage, taskNumber, this.ui);
                        return;
                    } else if (words[0].equals("find")) {
                        FindCommand findCommand = new FindCommand(this.command);
                        findCommand.execute(this.storage, this.ui);
                        return;
                    }
                } else {
                    throw new IncorrectCommandException("Please enter your commands correctly for Derek (e.g. todo (task)), he keeps throwing tantrums");
                }
                this.getTask();
            }
        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTask() {
        try {
            String[] words = command.split("\\s+");
            if (words[0].equals("deadline")) {
                String[] parts = command.split("/by");
                if (parts.length != 2) {
                    throw new IncorrectCommandException("Please enter your commands correctly for Derek (deadline (task) /by (date))");
                }
                DeadlineCommand deadlineCommand = new DeadlineCommand(this.command);
                String name = deadlineCommand.getTask();
                String[] information = name.split("/by");
                Task task = Task.deadlineTask(information[0], information[1]);
                deadlineCommand.execute(task, this.storage, this.ui);

            } else if (words[0].equals("event")) {
                String[] parts = command.split("/from");
                String[] time = command.split("/to");
                if (parts.length != 2 && time.length != 2) {
                    throw new IncorrectCommandException("Please enter your commands correctly for Derek (event (task) /from (time) /to (time)");
                }
                EventCommand eventCommand = new EventCommand(this.command);
                String name = eventCommand.getTask();
                String[] information1 = name.split("/from");
                String[] information2 = information1[1].split("/to");
                Task task = Task.eventTask(information1[0], information2[0], information2[1]);
                eventCommand.execute(task, this.storage, this.ui);
            } else if (words[0].equals("todo")) {
                TodoCommand todoCommand = new TodoCommand(this.command);
                String name = todoCommand.getTask();
                Task task = Task.toDoTask(name);
                todoCommand.execute(task, this.storage, this.ui);
            } else {
                throw new IncorrectCommandException("Is it a todo, event, or deadline?\nPlease enter your commands correctly for Derek (e.g. todo (task)), he keeps throwing tantrums");
            }
        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter your date in the correct format: DD/MM/YYYY HH:MM");
        }
    }
}
