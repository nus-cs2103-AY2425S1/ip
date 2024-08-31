import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Rizzler {
    public static void main(String[] args) {
        RizzlerSpeech rizzlerSpeech = new RizzlerSpeech();
        Storage storage = new Storage();
        TaskLog taskLog;
        try {
            taskLog = storage.getTasks();
        } catch (FileNotFoundException e) {
            taskLog = new TaskLog();
        }
        Scanner scanner = new Scanner(System.in);

        // greet user
        rizzlerSpeech.greet();

        // interact with user
        boolean userIsDone = false;
        while (!userIsDone) {
            String userInput = scanner.nextLine();
            String[] userInputArr = userInput.split(" ");
            int userInputLen = userInputArr.length;
            switch (userInputArr[0]) {
                case "bye":
                    userIsDone = true;
                    continue;
                case "list":
                    rizzlerSpeech.list(taskLog.getLog());
                    continue;
                case "delete":
                    rizzlerSpeech.say();
                    if (userInputArr.length == 1) {
                        rizzlerSpeech.say("what's the task number you want to delete?");
                        rizzlerSpeech.say();
                        continue;
                    }
                    try {
                        Integer.parseInt(userInputArr[1]);
                    } catch (NumberFormatException e) {
                        rizzlerSpeech.say("i need a task number, not a description darlin'");
                        rizzlerSpeech.say();
                        continue;
                    }
                    int taskToDelete = Integer.parseInt(userInputArr[1]);
                    try {
                        taskLog.getTask(taskToDelete - 1);
                    } catch (RizzlerException e) {
                        rizzlerSpeech.say(e.getMessage());
                        rizzlerSpeech.say();
                        continue;
                    }
                    Task deletedTask = taskLog.deleteTask(taskToDelete);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("sure hon, i'll remove this task from the list.");
                    rizzlerSpeech.say("\t" + deletedTask);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks left to work on.");
                    rizzlerSpeech.say();
                    continue;
                case "mark":
                    rizzlerSpeech.say();
                    if (userInputArr.length == 1) {
                        rizzlerSpeech.say("i need a task number to mark, hot stuff.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    try {
                        Integer.parseInt(userInputArr[1]);
                    } catch (NumberFormatException e) {
                        rizzlerSpeech.say("i need a task number, not a description darlin'");
                        rizzlerSpeech.say();
                        continue;
                    }
                    int taskToMark = Integer.parseInt(userInputArr[1]);
                    try {
                        taskLog.doTask(taskToMark);
                    } catch (RizzlerException e) {
                        rizzlerSpeech.say(e.getMessage());
                        rizzlerSpeech.say();
                        continue;
                    }
                    Task doneTask = taskLog.doTask(taskToMark);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("aight, i'll note that you've completed this.");
                    rizzlerSpeech.say("\t" + doneTask.toString());
                    rizzlerSpeech.say();
                    continue;
                case "unmark":
                    rizzlerSpeech.say();
                    if (userInputArr.length == 1) {
                        rizzlerSpeech.say("i need a task number to unmark, hot stuff.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    try {
                        Integer.parseInt(userInputArr[1]);
                    } catch (NumberFormatException e) {
                        rizzlerSpeech.say("i need a task number, not a description darlin'");
                        rizzlerSpeech.say();
                        continue;
                    }
                    int taskToUnmark = Integer.parseInt(userInputArr[1]);
                    try {
                        taskLog.undoTask(taskToUnmark);
                    } catch (RizzlerException e) {
                        rizzlerSpeech.say(e.getMessage());
                        rizzlerSpeech.say();
                        continue;
                    }
                    Task undoneTask = taskLog.undoTask(taskToUnmark);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("no worries, we'll circle back around to this.");
                    rizzlerSpeech.say("\t" + undoneTask.toString());
                    rizzlerSpeech.say();
                    continue;
                case "todo":
                    rizzlerSpeech.say();
                    String[] todoDescArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                    if (todoDescArr.length == 0) {
                        rizzlerSpeech.say("you have to let me know what task you have to do, dearie");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String todoDesc = String.join(" ", todoDescArr);
                    ToDo newTodo = new ToDo(todoDesc);
                    taskLog.addTask(newTodo);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("certainly, i'll keep track of this todo for you ;)");
                    rizzlerSpeech.say("\t" + newTodo);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                    rizzlerSpeech.say();
                    continue;
                case "deadline":
                    rizzlerSpeech.say();
                    String[] deadlineInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                    if (deadlineInfoArr.length == 0) {
                        rizzlerSpeech.say("honey, deadlines require a description and a date/time.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String deadlineInfo = String.join(" ", deadlineInfoArr);
                    if (!deadlineInfo.contains("/by")) {
                        rizzlerSpeech.say("remember to include a \"/by [date/time]\" for the deadline!");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String[] deadlineInfoBySplit = deadlineInfo.split("/by");
                    if (deadlineInfoBySplit.length < 2) {
                        rizzlerSpeech.say("deadlines require both a description and a date/time dear.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String deadlineDesc = deadlineInfoBySplit[0];
                    if (deadlineDesc.isEmpty()) {
                        rizzlerSpeech.say("your deadline is missing a description honey.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String deadlineTime = deadlineInfoBySplit[1];
                    Deadline newDeadline = new Deadline(deadlineDesc, deadlineTime);
                    taskLog.addTask(newDeadline);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("certainly, i'll keep track of this deadline for you ;)");
                    rizzlerSpeech.say("\t" + newDeadline);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                    rizzlerSpeech.say();
                    continue;
                case "event":
                    rizzlerSpeech.say();
                    String[] eventInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                    if (eventInfoArr.length == 0) {
                        rizzlerSpeech.say("honey, events require a description, start, and end.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String eventInfo = String.join(" ", eventInfoArr);
                    if (!eventInfo.contains("/from")) {
                        rizzlerSpeech.say("remember to include a \"/from [start]\" for this event!");
                        rizzlerSpeech.say();
                        continue;
                    } else if (!eventInfo.contains("/to")) {
                        rizzlerSpeech.say("remember to include a \"/to [end]\" for this event!");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String[] eventInfoFromSplit = eventInfo.split("/from");
                    String eventDesc = eventInfoFromSplit[0];
                    if (eventDesc.isEmpty()) {
                        rizzlerSpeech.say("your event is missing a description honey.");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String eventDuration = eventInfoFromSplit[1];
                    if (eventDuration.split("/to").length < 2) {
                        rizzlerSpeech.say("events require a description, a start, and an end dear");
                        rizzlerSpeech.say();
                        continue;
                    }
                    String eventStart = eventDuration.split("/to")[0];
                    String eventEnd = eventDuration.split("/to")[1];
                    Event newEvent = new Event(eventDesc, eventStart, eventEnd);
                    taskLog.addTask(newEvent);
                    storage.storeTasks(taskLog);
                    rizzlerSpeech.say("certainly, i'll keep track of this event for you ;)");
                    rizzlerSpeech.say("\t" + newEvent);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                    rizzlerSpeech.say();
                    continue;
                default:
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("sincerest apologies darlin', i don't recognise that command.");
                    rizzlerSpeech.say();
            }
        }
        scanner.close();
        // bid farewell to user
        rizzlerSpeech.bidFarewell();
    }
}
