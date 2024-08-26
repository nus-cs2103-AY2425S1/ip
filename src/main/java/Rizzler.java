import java.util.Arrays;
import java.util.Scanner;

public class Rizzler {
    public static void main(String[] args) {
        RizzlerSpeech rizzlerSpeech = new RizzlerSpeech();
        TaskLog taskLog = new TaskLog();
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
                case "mark":
                    int taskToMark = Integer.parseInt(userInputArr[1]);
                    Task doneTask = taskLog.doTask(taskToMark);
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("aight, i'll note that you've completed this.");
                    rizzlerSpeech.say("\t" + doneTask.toString());
                    rizzlerSpeech.say();
                    continue;
                case "unmark":
                    int taskToUnMark = Integer.parseInt(userInputArr[1]);
                    Task undoneTask = taskLog.undoTask(taskToUnMark);
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("no worries, we'll circle back around to this.");
                    rizzlerSpeech.say("\t" + undoneTask.toString());
                    rizzlerSpeech.say();
                    continue;
                case "todo":
                    String[] todoDescArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                    String todoDesc = String.join(" ", todoDescArr);
                    ToDo newTodo = new ToDo(todoDesc);
                    taskLog.addTask(newTodo);
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("certainly, i'll keep track of this todo for you ;)");
                    rizzlerSpeech.say("\t" + newTodo);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                    rizzlerSpeech.say();
                    continue;
                case "deadline":
                    String[] deadlineInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                    String deadlineInfo = String.join(" ", deadlineInfoArr);
                    String deadlineDesc = deadlineInfo.split("/by")[0];
                    String deadlineTime = deadlineInfo.split("/by")[1];
                    Deadline newDeadline = new Deadline(deadlineDesc, deadlineTime);
                    taskLog.addTask(newDeadline);
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("certainly, i'll keep track of this deadline for you ;)");
                    rizzlerSpeech.say("\t" + newDeadline);
                    rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                    rizzlerSpeech.say();
                    continue;
                default:
                    Task newTask = new Task(userInput);
                    rizzlerSpeech.say();
                    rizzlerSpeech.say("added: " + newTask);
                    rizzlerSpeech.say();
                    taskLog.addTask(newTask);
            }
        }
        scanner.close();

        // bid farewell to user
        rizzlerSpeech.bidFarewell();
    }
}
