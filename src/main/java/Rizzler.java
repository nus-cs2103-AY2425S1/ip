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
            try {
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
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("aight, i'll note that you've completed this.");
                        rizzlerSpeech.say(doneTask.toString());
                        rizzlerSpeech.lineBreak();
                        continue;
                    case "unmark":
                        int taskToUnMark = Integer.parseInt(userInputArr[1]);
                        Task undoneTask = taskLog.undoTask(taskToUnMark);
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("no worries, we'll circle back around to this.");
                        rizzlerSpeech.say(undoneTask.toString());
                        rizzlerSpeech.lineBreak();
                        continue;
                    case "todo":
                        String[] todoDescArr = Arrays.copyOfRange(userInputArr, 1, userInputLen);
                        String todoDesc = String.join(" ", todoDescArr);
                        ToDo newTodo = new ToDo(todoDesc);
                        taskLog.addTask(newTodo);
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("added: " + newTodo);
                        rizzlerSpeech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
                        rizzlerSpeech.lineBreak();
                        continue;
                    default:
                        Task newTask = new Task(userInput);
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("added: " + newTask);
                        rizzlerSpeech.lineBreak();
                        taskLog.addTask(newTask);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();

        // bid farewell to user
        rizzlerSpeech.bidFarewell();
    }
}
