package tasklist;

import parser.Parser;
import java.util.ArrayList;
import task.Task;
import todo.ToDo;
import deadline.Deadline;
import event.Event;

public class TaskList {
    public static void createToDo(String name, ArrayList<Task> taskList) {
        ToDo newToDo = new ToDo(name);
        taskList.add(newToDo);
        speakTaskAdded(newToDo, taskList.size());
    }

    public static void createDeadline(String name, String date, ArrayList<Task> taskList) {
        Deadline newDeadline = new Deadline(name, date);
        taskList.add(newDeadline);
        speakTaskAdded(newDeadline, taskList.size());
    }

    public static void createEvent(String eventName, String startTime, String endTime, ArrayList<Task> taskList) {
        Event newEvent = new Event(eventName, startTime, endTime);
        taskList.add(newEvent);
        speakTaskAdded(newEvent, taskList.size());
    }

    public static void deleteEvent(int deletionNumber, ArrayList<Task> taskList) {
        String deletedDescription = taskList.get(deletionNumber).toString();
        taskList.remove(deletionNumber);
        speakTaskRemoved(deletedDescription, taskList.size());
    }

    public static void speakTaskAdded(Task task, int size) {
        String msg = "Got it. I've added this task:\n" + task.toString();
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        Parser.speak(msg);
    }

    public static void speakTaskRemoved(String description, int size) {
        String msg = "Noted. I've removed this task:\n" + description;
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        Parser.speak(msg);
    }
}