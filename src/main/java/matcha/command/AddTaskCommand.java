package matcha.command;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import matcha.exception.MatchaException;
import matcha.task.Task;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Todo;
import matcha.TaskList;
import matcha.Storage;
import matcha.Ui;

public class AddTaskCommand extends Command{
    private String commandType;
    private String[] inputWords;
    public AddTaskCommand(String[] inputWords, String commandType) {
        this.commandType = commandType;
        this.inputWords = inputWords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException {
        if (inputWords.length < 2) {
            throw new MatchaException("Please include the " + commandType + " details!");
        }
        
        switch(commandType) {

        case "todo":
            Todo todo = new Todo(inputWords[1]);
            System.out.println("Alright, I have added this Todo:");
            //add todos to task list
            tasks.addTask(todo);
            tasks.printTask(todo);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            break;

        case "deadline":
            Deadline deadline = createDeadline();
            System.out.println("Alright, I have added this Deadline:");
            //add deadline to task list
            tasks.addTask(deadline);
            tasks.printTask(deadline);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            break;

        case "event":
            Event event = createEvent();
            System.out.println("Alright, I have added this Event:");
            //add event to task list
            tasks.addTask(event);
            tasks.printTask(event);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            break;

        default:
            throw new MatchaException("Invalid command to add tasks!");
        }
    }

    private Deadline createDeadline() throws MatchaException {
        if (!inputWords[1].contains(" /by ")) {
            throw new MatchaException("Invalid format to add Deadline.\nPlease use '/by' to specify the " +
                    "time of the Deadline.");
        }
        //extract deadline description and 'by'
        String[] deadlineInfo = inputWords[1].split(" /by ", 2);
        String deadlineDesc = deadlineInfo[0].strip();
        String by = deadlineInfo[1].strip();

        //check if 'by' is in the correct format
        LocalDateTime formattedBy;
        try {
            formattedBy = LocalDateTime.parse(by, Task.getInputFormat());
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date/time format! Please use 'yyyy-MM-dd HH:mm' format.");
        }

        return new Deadline(deadlineDesc, formattedBy);
    }

    private Event createEvent() throws MatchaException {
        if (inputWords.length < 2) {
            throw new MatchaException("Please include the Event details!");
        }
        if (!inputWords[1].contains(" /from ") || !inputWords[1].contains(" /to ")) {
            throw new MatchaException("Invalid format to add Event.\nPlease use '/from' and '/to' to specify the " +
                    "Event duration.");
        }
        //extract event description, 'from' and 'to'
        String eventDesc = inputWords[1].split(" /from")[0];
        String from = inputWords[1].split(" /from ")[1].split(" /to ")[0];
        String to = inputWords[1].split(" /to ")[1];

        //check if 'from' and 'to' are in the correct format
        LocalDateTime formattedFrom;
        LocalDateTime formattedTo;
        try {
            formattedFrom = LocalDateTime.parse(from, Task.getInputFormat());
            formattedTo = LocalDateTime.parse(to, Task.getInputFormat());
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date/time format! Please use 'yyyy-MM-dd HH:mm' format.");
        }

        return new Event(eventDesc, formattedFrom, formattedTo);
    }
}
