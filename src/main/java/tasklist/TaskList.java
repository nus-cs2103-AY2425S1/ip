package tasklist;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import task.*;
import ui.Ui;

public class TaskList {

    private ArrayList<Task> botMemory;

    public TaskList(ArrayList<Task> botMemory) {
        assert botMemory != null : "Bot memory should not be null";
        this.botMemory = botMemory;
    }

    /**
     * Returns all the items in the ArrayList as a task list.
     * @return String
     */
    public String listToString() {
        StringBuilder outputString = new StringBuilder();
        assert outputString != null : "Output string should not be null";
        if (this.botMemory.isEmpty()) {
            outputString.append("No items in your list");
        } else {
            outputString.append("Here are the tasks in your list: \n");
            for (int i = 0; i < this.botMemory.size(); i++) {
                assert this.botMemory.get(i) != null : "Task in bot memory should not be null";
                outputString.append((i + 1)).append(". ").append(this.botMemory.get(i).toString()).append("\n");
            }
        }
        System.out.println(outputString.toString());
        return outputString.toString();
    }

    public ArrayList<Task> getBotMemory() {
        return this.botMemory;
    }

    public String findTerm(String searchTerm) {
        assert searchTerm != null && !searchTerm.isEmpty() : "Search term should not be null or empty";
        StringBuilder findResponse = new StringBuilder();
        ArrayList<Task> resultArray = new ArrayList<>();
        if (this.botMemory.isEmpty()) {
           findResponse.append("There are no items in the list at the moment, enter '/help' to find out how to enter them.\n");
           return findResponse.toString();
        } else {
            for (int i = 0; i < this.botMemory.size(); i++) {
                if (this.botMemory.get(i).description.contains(searchTerm)) {
                    resultArray.add(this.botMemory.get(i));
                }
            }
        }

        if (resultArray.size() > 0) {
            findResponse.append("Here are the matching tasks in your list:\n");
            for (int j = 0; j < resultArray.size(); j++) {
                findResponse.append((j + 1)).append(". ").append(resultArray.get(j).toString()).append("\n");
            }
            return findResponse.toString();
        } else {
            findResponse.append("No matching tasks found.");
            return findResponse.toString();
        }

    }

    /**
     * Returns a list of tasks (Deadlines or Events) that are due within 3 days.
     * @return String
     */
    public String sayReminders() {
        StringBuilder remindersResponse = new StringBuilder();
        ArrayList<Task> reminderTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLater = now.plusDays(3);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

        for (Task task : botMemory) {
            if (task instanceof Deadline && !task.taskIsDone) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.deadlineDateTime == null) {
                    try {
                        LocalDateTime parsedDeadline = LocalDateTime.parse(deadlineTask.deadlineString, inputFormat);
                        if (parsedDeadline.isBefore(threeDaysLater) && !parsedDeadline.isBefore(now)) {
                            reminderTasks.add(deadlineTask);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid deadline format for task: " + deadlineTask + " " + deadlineTask.deadlineString);
                    }
                } else if (deadlineTask.deadlineDateTime.isBefore(threeDaysLater) && !deadlineTask.deadlineDateTime.isBefore(now)) {
                    reminderTasks.add(deadlineTask);
                }
            } else if (task instanceof Event && !task.taskIsDone) {
                Event eventTask = (Event) task;
                if (eventTask.startDateTime == null || eventTask.endDateTime == null) {
                    try {
                        LocalDateTime parsedStartTime = LocalDateTime.parse(eventTask.startTime, inputFormat);

                        if (parsedStartTime.isBefore(threeDaysLater) && !parsedStartTime.isBefore(now)) {
                            reminderTasks.add(eventTask);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid event format for task: " + eventTask + " " + eventTask.startTime);
                    }
                } else if (eventTask.startDateTime.isBefore(threeDaysLater) && !eventTask.startDateTime.isBefore(now)) {
                    reminderTasks.add(eventTask);
                }
            }
        }
        if (reminderTasks.isEmpty()) {
            remindersResponse.append("You have no tasks with deadlines or events within the next 3 days.");
        } else {
            remindersResponse.append("Here are your upcoming tasks for the next 3 days:\n");
            for (int i = 0; i < reminderTasks.size(); i++) {
                remindersResponse.append((i + 1)).append(". ").append(reminderTasks.get(i).toString()).append("\n");
            }
        }

        return remindersResponse.toString();
    }

    /**
     * Toggles the boolean value corresponding to whether the task is finished or not.
     * @param taskNumber
     */

    public String toggleTaskDone(String userInput, int taskNumber) {
        assert taskNumber >= 0 && taskNumber < botMemory.size() : "Invalid task number";
        StringBuilder response = new StringBuilder();
        if (userInput.equals("mark") && !botMemory.get(taskNumber).taskIsDone) {
            response.append(botMemory.get(taskNumber).toggleTaskDone()).append("\n");
        } else if (userInput.equals("unmark") && botMemory.get(taskNumber).taskIsDone) {
            response.append(botMemory.get(taskNumber).toggleTaskDone()).append("\n");
        } else {
            response.append("Task has already been " + userInput + "ed.");
        }
        return response.toString();
    }

    /**
     * Removes tasks from the ArrayList and informs the user.
     * @param taskToRemove
     */
    public String removeTask(int taskToRemove) {
        assert taskToRemove >= 0 && taskToRemove < botMemory.size() : "Invalid task to remove";
        StringBuilder response = new StringBuilder();

        response.append("Noted. I've removed this task:\n")
                .append(" ").append(botMemory.get(taskToRemove).toString()).append("\n");

        botMemory.remove(taskToRemove);

        response.append("Now you have ").append(botMemory.size()).append(" tasks in the list.\n");

        return response.toString();
    }

    /**
     * Adds tasks to the ArrayList and informs the user.
     * @param newTask
     */
    public String addTask(Task newTask) {
        assert newTask != null : "New task should not be null";
        StringBuilder response = new StringBuilder();

        botMemory.add(newTask);

        response.append("Got it. I've added this task:\n")
                .append(newTask.toString()).append("\n")
                .append("Now you have ").append(botMemory.size()).append(" task(s) in the list.")
                .append(this.listToString()).append("\n");

        return response.toString();

    }


}
