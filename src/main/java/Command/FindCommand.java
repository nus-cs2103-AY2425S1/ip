package Command;

import TaskList.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static Ui.Ui.*;

public class FindCommand extends Command{
    private TaskList list;
    private String date;

    public FindCommand (TaskList list, String date) {
        this.list = list;
        this.date = date;
    }

    @Override
    public void execute() {
        try {
            TaskList output = new TaskList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isRelatedToDate(LocalDate.parse(date))) {
                    output.add(list.get(i));
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
            printMessages("Sorry~ I can not recognize the time", "Please enter the time in the " +
                    "YYYY-MM-DD format");
        }


    }
}
