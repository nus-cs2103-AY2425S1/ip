import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KillJoy {
    private String welcomeString = "    Hello! I'm KillJoy\n    I'm GENIUS!!!\n    What can I do for you?\n" +
                                   "    ------------------------------------";
    private String exitString = "    ------------------------------------\n" +
                                "    Bubyyeee & Don't Stwesszz. Time to hide now!!\n" +
                                "    ------------------------------------";
    private String logoString = "    ------------------------------------\n" +
                                "     _  ___ _ _      _\n" +
                                "    | |/ (_) | |    | |\n" +
                                "    | ' / _| | |    | | ___  _   _\n" +
                                "    |  < | | | |_   | |/ _ \\| | | |\n" +
                                "    | . \\| | | | |__| | (_) | |_| |\n" +
                                "    |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
                                "                              __/ |\n" +
                                "                             |___/\n" +
                                "    ------------------------------------";
    private String markString = "Ayee Yooo! I've marked this task okaayyyyy:";
    private String unmarkString = "Yo big guy! I've unmarked this for you:";
    private String deleteString = "Okay! I'll remove this task:";
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    public void start() {
        System.out.println(this.logoString);
        System.out.println(this.welcomeString);
        Scanner user = new Scanner(System.in);

        while(true) {
            String input = user.nextLine();
            if (input.equals("")) {
                this.printLine();
                System.out.println("    WHHHAHAHAHHA!! You didn't enter anything siaaa!!!");
                this.printLine();
                continue;
            }

            String[] inputAsList = input.split(" ");

            if (inputAsList[0].equals("bye")) {
                System.out.println(this.exitString);
                break;
            } else if (inputAsList[0].equals("list")){
                this.printTaskList();
            } else if (inputAsList[0].equals("mark") || inputAsList[0].equals("unmark")) {
                this.markOrUnmarkTask(inputAsList);
            } else if (inputAsList[0].equals("delete")) {
                this.deleteTask(inputAsList);
            } else {
                this.processUserInput(input);
            }
        }
    }

    private void processUserInput(String input) {
        String[] inputSplit = input.split("/");
        String[] inputSplitBySpace = inputSplit[0].split(" ");
        String typeOfTask = inputSplitBySpace[0];
        if (!Arrays.asList("todo", "deadline", "event").contains(typeOfTask) ) {
            this.printLine();
            System.out.println("    AGWHAHH!!! What'ya sayin' dawgg??");
            this.printLine();
            return;
        }
        if (inputSplitBySpace.length == 1) {
            this.printLine();
            System.out.println("    What siaaa!! Description cannot be empty");
            this.printLine();
            return;
        }


        this.printLine();
        if (typeOfTask.equals("todo")) {
            String description = inputSplit[0].replaceFirst("todo ", "");
            taskList.add(new Todo(description));
        } else if (typeOfTask.equals("deadline")) {
            String description = inputSplit[0].replaceFirst("deadline ", "");
            String by = inputSplit[1].replaceFirst("by ", "");
            taskList.add(new Deadline(description, by));
        } else if (typeOfTask.equals("event")) {
            String description = inputSplit[0].replaceFirst("event ", "");
            String from = inputSplit[1].replaceFirst("from ", "");
            String to = inputSplit[2].replaceFirst("to ", "");
            taskList.add(new Event(description, from, to));
        }
        taskCount++;
        System.out.println("    Yo Dawgg!! Added this task:");
        System.out.println("    " + this.taskList.get(taskCount - 1));
        if (taskCount == 1) {
            System.out.println("    Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("    Now you have " + taskCount + " tasks in the list.");
        }
        this.printLine();
    }

    private void markOrUnmarkTask(String[] inputAsList) {
        int taskNum = Integer.MAX_VALUE;

        if (inputAsList.length == 1) {
            this.printLine();
            System.out.println("    MAOHWAHAWK !! Enter the task number too!!!");
            this.printLine();
            return;
        }

        taskNum = Integer.parseInt(inputAsList[1]) - 1;
        if (taskNum + 1 > taskCount || taskNum < 0) {
            this.printLine();
            System.out.println("    DANGER !! Task does not exist");
            this.printLine();
            return;
        }

        if (inputAsList[0].equals("mark")) {
            this.taskList.get(taskNum).changeStatus();
            this.printLine();
            System.out.println("    " + markString + "\n        " + this.taskList.get(taskNum));
            this.printLine();
        } else if (inputAsList[0].equals("unmark")) {
            taskNum = Integer.parseInt(inputAsList[1]) - 1;
            this.taskList.get(taskNum).changeStatus();
            this.printLine();
            System.out.println("    " + unmarkString + "\n        " + this.taskList.get(taskNum));
            this.printLine();
        }
    }

    private void deleteTask(String[] inputAsList) {
        int taskNum = Integer.MAX_VALUE;

        if (inputAsList.length == 1) {
            this.printLine();
            System.out.println("    MAOHWAHAWK !! Enter the task number too!!!");
            this.printLine();
            return;
        }

        taskNum = Integer.parseInt(inputAsList[1]) - 1;
        if (taskNum + 1 > taskCount || taskNum < 0) {
            this.printLine();
            System.out.println("    DANGER !! Task does not exist");
            this.printLine();
            return;
        }

        Task t = this.taskList.get(taskNum);
        this.taskList.remove(taskNum);
        this.taskCount--;
        this.printLine();
        System.out.println("    " + deleteString + "\n        " + t );
        if (taskCount == 1) {
            System.out.println("    Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("    Now you have " + taskCount + " tasks in the list.");
        }
        this.printLine();
    }

    private void printLine() {
        System.out.println("    ------------------------------------");
    }

    private void printTaskList() {
        this.printLine();
        System.out.println("    Here are your tasks lah!! Don't die:");
        for (int i = 0; i < this.taskCount; i++) {
            System.out.println("    " + (i + 1) + ". " + this.taskList.get(i));
        }
        this.printLine();
    }

    public static void main(String[] args) {
        KillJoy kj = new KillJoy();
        kj.start();
    }
}
