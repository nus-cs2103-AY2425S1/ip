package kotori.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kotori.tasklist.TaskList;
import kotori.ui.Ui;


/**
 * This class represents a command of searching tasks
 * related to a specific date.
 * */

public class SearchCommand extends Command {
    private TaskList list;
    private String date;
    /**
     * make a search command.
     * */
    public SearchCommand(TaskList list, String date) {
        this.list = list;
        this.date = date;
    }

    /**
     * Executes the command and start searching.
     * */

    @Override
    public String execute() {
        try {
            TaskList output = new TaskList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isRelatedToDate(LocalDate.parse(date))) {
                    output.add(list.get(i));
                }
            }
            if (output.isEmpty()) {
                return Ui.printMessages(String.format("Great! You have no task related to this date %s", date));
            } else {
                return Ui.printListWithMessages(output, String.format("These are the tasks related to this date %s",
                        date));
            }
        } catch (DateTimeParseException e) {
            return Ui.printMessages("Sorry~ I can not recognize the time", "Please enter the time in the "
                    + "YYYY-MM-DD format");
        }
    }
}
