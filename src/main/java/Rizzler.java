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
                String[] userInput = scanner.nextLine().split(" ");
                switch (userInput[0]) {
                    case "bye":
                        userIsDone = true;
                        continue;
                    case "list":
                        rizzlerSpeech.list(taskLog.getLog());
                        continue;
                    case "mark":
                        int taskToMark = Integer.parseInt(userInput[1]);
                        Task doneTask = taskLog.doTask(taskToMark);
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("aight, i'll note that you've completed this.");
                        rizzlerSpeech.say(doneTask.toString());
                        rizzlerSpeech.lineBreak();
                        continue;
                    case "unmark":
                        int taskToUnMark = Integer.parseInt(userInput[1]);
                        Task undoneTask = taskLog.undoTask(taskToUnMark);
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("no worries, we'll circle back around to this.");
                        rizzlerSpeech.say(undoneTask.toString());
                        rizzlerSpeech.lineBreak();
                        continue;
                    default:
                        String userCommand = String.join(" ", userInput);
                        Task newTask = new Task(userCommand);
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
