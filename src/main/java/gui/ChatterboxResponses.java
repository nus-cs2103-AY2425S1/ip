package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tasks.Task;

/**
 * Handles the string formatting of chatterbox responses for the gui
 */
public class ChatterboxResponses {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String BOTNAME = "Chatterbox";

    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");


    public ChatterboxResponses() {

    }

    /**
     * generates String for standard greeing
     * @return string format of the greeting
     */
    public String greeting() {
        return ChatterboxResponses.LINE_SEPARATOR + "\n" + "Hello! I'm " + ChatterboxResponses.BOTNAME + "\nWhat can I do for you?";

    }

    public String goodBye() {
        return ChatterboxResponses.LINE_SEPARATOR + "\n" + "Bye! Hope to see you again!" + ChatterboxResponses.LINE_SEPARATOR;

    }

    public String displayList(ArrayList<Task> userList) {
        StringBuilder toReturn =  new StringBuilder(ChatterboxResponses.LINE_SEPARATOR + "\nCurrent Tasks in List: ");
        for (int i = 0; i < userList.size(); i ++) {
            toReturn.append(String.format(i + 1 + ". " + "[%s][%s] %s",
                    userList.get(i).getTaskSymbol(),
                    userList.get(i).getStatus() ? "X" : " ",
                    userList.get(i).getDescription()));
        }
        return toReturn.toString();
    }

    public String getTaskDescription(Task task) {
        return task.getDescription();
    }

    public String markMsg(Task task) {
        return ChatterboxResponses.LINE_SEPARATOR + "\nMarked Task as done!" + "\n" + task.getDescription();
    }

    public String unmarkMsg(Task task) {
        return ChatterboxResponses.LINE_SEPARATOR + "\nMarked Task as undone!" + "\n" + task.getDescription();

    }

    public String addTaskMsg(String type, int size) {
        return ChatterboxResponses.LINE_SEPARATOR +  "\n" + String.format("Added %s to Tasks", type) + "\n"
                + String.format("Currently %d tasks in List");

    }

    public String displaySearch(ArrayList<Task> matches) {
        StringBuilder toReturn  = new StringBuilder(ChatterboxResponses.LINE_SEPARATOR + "\n" + "Displaying All Matching Tasks: ");
        for (int i = 0; i < matches.size(); i++) {
            toReturn.append(String.format(i + 1 + ". " + "[%s][%s] %s",
                    matches.get(i).getTaskSymbol(),
                    matches.get(i).getStatus() ? "X" : " ",
                    matches.get(i).getDescription()));
        }
        return toReturn.toString();

    }

}
