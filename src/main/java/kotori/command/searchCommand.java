package kotori.command;

import kotori.taskList.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import static kotori.Ui.Ui.printLine;
import static kotori.Ui.Ui.printMessage;
import static kotori.Ui.Ui.printMessages;

/**
 * This class represents a command of searching
 * all the task that is related to a specific date.
 * */

public class searchCommand extends Command{
    private TaskList taskList;
    private String date;

    public searchCommand (TaskList list, String date) {
        this.taskList = list;
        this.date = date;
    }

    /**
     * Activates the command and start searching.
     * */

    @Override
    public void execute() {
        try {
            TaskList output = new TaskList();
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).isRelatedToDate(LocalDate.parse(date))) {
                    output.add(taskList.get(i));
                }
            }
            if (output.isEmpty()) {
                printMessage(String.format("Great! You have no task related to this date %s", date));
            } else {
                printLine();
                System.out.println(String.format("    These are the tasks related to this date %s", date));
                for (int i = 0; i < output.size(); i++) {
                    System.out.println("    " + output.get(i).toString());
                }
                printLine();
            }
        } catch (DateTimeParseException e) {
            printMessages("Sorry~ I can not recognize the time", "Please enter the time in the "
                    + "YYYY-MM-DD format");
        }


    }
}
