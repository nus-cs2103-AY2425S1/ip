package FlyChat.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import FlyChat.Tasks.Deadline;
import FlyChat.Tasks.Event;
import FlyChat.Tasks.Task;
import FlyChat.Tasks.Todo;

public class TaskList {
    private ArrayList<Task> storageList;
    private Parser parser;

    public TaskList() {
        storageList = new ArrayList<>();
        parser = new Parser();
    }

    public String addTask(String inputString) {
        if (parser.parseCommand(inputString).equals("todo")) {
            try {
                Todo newToDo = Todo.createNewTodo(parser.getTaskDescription(inputString));
                addToList(newToDo);
                return "Task added:\n  " + newToDo + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^";
            } catch(InputMismatchException e) {
                return "Please ensure your todo has a description TT";
            }

        } else if (parser.parseCommand(inputString).equals("event")) {
            try {
                Event newEvent = Event.createNewEvent(parser.getTaskDescription(inputString), parser.getEventStartTime(inputString)
                        , parser.getEventEndTime(inputString), false);
                addToList(newEvent);
                return "Task added:\n  " + newEvent + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^";
            } catch(InputMismatchException e) {
                return "Please ensure your event has a description, a start and end time TT";
            }
            
        } else {    /* remaining case is deadline task cases */
            try {
                Deadline newDeadline = Deadline.createNewDeadline(parser.getTaskDescription(inputString), 
                        parser.getDeadlineEndDate(inputString), false);
                addToList(newDeadline);
                return "Task added:\n  " + newDeadline + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^";
            } catch(InputMismatchException e) {
                return e.getMessage();
            }
        }
    }

    public String announceItems() {
        String finalString = "";
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < storageList.size(); i++) {
            finalString += ((i + 1) + ". " + storageList.get(i) + "\n");
        }
        return finalString;
    }

    public String mark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).completeTask();
        return "I've marked the task as done ^o^ :\n  " + storageList.get(index - 1).toString();
    }

    public String unmark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).uncompleteTask();
        return "I've marked the task as not done T.T :\n  " + storageList.get(index - 1).toString();
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException{
        Task targetTask = storageList.get(index - 1);
        storageList.remove(targetTask);
        return "Task removed:\n  " + targetTask + "\nNow you have " + storageList.size() 
                + " tasks in the list. SOLDIER ON! ^o^";
    }

    public void addTaskFromFile(String str) throws IOException {
        boolean isCompleted = false;
        isCompleted = parser.checkTaskCompletedFromFile(str);
        
        //format the task string properly using regex
        if (parser.getTaskTypeFromFile(str).equals("[T]")){
            //To-do case
            addToList(Todo.createNewTodo(parser.getTaskDescriptionFromFile(str), isCompleted));
        } else if (parser.getTaskTypeFromFile(str).equals("[D]")){
            //Deadline case
            addToList(Deadline.createNewDeadline(parser.getTaskDescriptionFromFile(str)
                    , parser.getDeadlineEndDateFromFile(str), isCompleted));
        } else if (parser.getTaskTypeFromFile(str).equals("[E]")){
            //Event case
            addToList(Event.createNewEvent(parser.getTaskDescriptionFromFile(str)
                    , parser.getEventStartTimeFromFile(str), parser.getEventEndTimeFromFile(str)
                    , isCompleted));
        } else {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
    }
    
    public String formatSaveString() {
        String saveString = "";
        for (Task t : storageList) {
            saveString += (t.formatStringForSaving() + System.lineSeparator());
        }
        return saveString;
    }
    private void addToList(Task task) {
        storageList.add(task);
    }
}
