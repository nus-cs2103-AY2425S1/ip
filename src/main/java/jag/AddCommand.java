package jag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents and instance of a AddCommand so that upon execution
 * the right type of task is added to the task list and the right
 * response is displayed
 */
public class AddCommand extends Command {
    private char type;

    /**
     * Constructor for the class to store the type of task
     * to be added to be used on a case by case basis in execute()
     * so that the right message can be replied to using the ui instance
     *
     * @param type Represents the type of task to be added
     *             T = ToDos, D = Deadline, E = Event
     */
    public AddCommand(char type) {
        this.type = type;
    }

    /**
     * Execute the adding of the task to TaskList instance
     * and to call upon the ui instance to display the right type of information
     *
     * @param tasks To help add the task into the TaskList instance
     * @param ui To pass the required information needed to display the right message
     *           with the right task
     * @param storage To store the newly added task and updated the output file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (this.type == 'T') {
            // Tasks
            String description = ui.getDescription(this.type);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            // UI response
            ui.addedResponse(this.type, newTodo, tasks);

        } else if (this.type == 'D') {
            // Tasks
            String description = ui.getDescription(this.type);
            String by = ui.getBy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(by, formatter);
            Deadline newDeadline = new Deadline(description, d1);
            tasks.addTask(newDeadline);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            // jag.Ui response
            ui.addedResponse(this.type, newDeadline, tasks);

        } else {
            // Tasks
            String description = ui.getDescription(this.type);
            String from = ui.getFrom();
            String to = ui.getTo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(from, formatter);
            LocalDateTime d2 = LocalDateTime.parse(to, formatter);
            Event newEvent = new Event(description, d1, d2);
            tasks.addTask(newEvent);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            //jag.Ui response
            ui.addedResponse(this.type, newEvent, tasks);

        }
    }

    /**
     * Returns false so that Jag.java does not exit for loop
     *
     * @return a default false so the run() in Jag.java does not exit
     *              the while loop
     */
    @Override
    public Boolean isExit() {
        return false;
    }

}
