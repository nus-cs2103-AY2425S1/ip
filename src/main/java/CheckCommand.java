import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CheckCommand extends Command {
    private String userInput;
    private ArrayList<Task> tasks;

    public CheckCommand(String userInput, TaskList taskList) {
            super(userInput, taskList);
            this.userInput = userInput;
            this.tasks = taskList.getTaskList();
    }

    @Override
    public void execute() throws WrongDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String date = userInput.substring(5).trim();
        try {
            LocalDate selectedDate = LocalDate.parse(date, formatter);
            String output = "";
            int counter = 1;

            for (Task task : this.tasks) {
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    LocalDate taskDate = deadlineTask.getDeadline().getDateTime().toLocalDate();
                    if (selectedDate.equals(taskDate)) {
                        output += counter + ". " + deadlineTask + "\n";
                        counter++;
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    LocalDate taskDate = eventTask.getEventDate().getDateTime().toLocalDate();
                    if (selectedDate.equals(taskDate)) {
                        output += counter + ". " + eventTask + "\n";
                        counter++;
                    }
                }
            }
            System.out.println(output + "These tasks are due on "
                    + selectedDate.toString() + Ui.horizontalLine);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }
}
