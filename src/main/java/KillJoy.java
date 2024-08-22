import java.util.ArrayList;
import java.util.Scanner;

public class KillJoy {
    private String welcomeString = "   Hello! I'm KillJoy \n   I'm GENIUS!!!\n   What can I do for you?\n" +
                                   "   ------------------------------------";
    private String exitString = "   ------------------------------------\n" +
                                "    Bubyyeee & Don't Stwesszz. Time to hide now!!\n" +
                                "   ------------------------------------";
    private String logoString = "   ------------------------------------\n" +
                                "    _  ___ _ _      _             \n" +
                                "   | |/ (_) | |    | |            \n" +
                                "   | ' / _| | |    | | ___  _   _ \n" +
                                "   |  < | | | |_   | |/ _ \\| | | |\n" +
                                "   | . \\| | | | |__| | (_) | |_| |\n" +
                                "   |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
                                "                             __/ |\n" +
                                "                            |___/ \n" +
                                "   ------------------------------------";
    private String markString = "Ayee Yooo! I've marked this task okaayyyyy:";
    private String unmarkString = "Yo big guy! I've unmarked this for you:";
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    public void start() {
        System.out.println(this.logoString);
        System.out.println(this.welcomeString);

        Scanner user = new Scanner(System.in);

        while(true) {
            String input = user.nextLine();
            int taskNum;
            String[] inputAsList = input.split(" ");

            if (inputAsList[0].equals("bye")) {
                System.out.println(this.exitString);
                break;
            } else if (inputAsList[0].equals("list")){
                this.printTasks();
            } else if (inputAsList[0].equals("mark")) {
                taskNum = Integer.parseInt(inputAsList[1]) - 1;
                this.taskList.get(taskNum).changeStatus();
                this.printTask(this.taskList.get(taskNum));
            } else if (inputAsList[0].equals("unmark")) {
                taskNum = Integer.parseInt(inputAsList[1]) - 1;
                this.taskList.get(taskNum).changeStatus();
                this.printTask(this.taskList.get(taskNum));
            }
            else {
                this.processUserInput(input);
            }
        }
    }

    private void processUserInput(String input) {
        System.out.println("    ------------------------------------");
        taskList.add(new Task(input));
        taskCount++;
        System.out.println("    added: " + input);
        System.out.println("    ------------------------------------");
    }

    private void printTask(Task task) {
        System.out.println("    ------------------------------------");
        System.out.println("    " + markString + "\n        [" + task.getStatusIcon() + "] " + task);
        System.out.println("    ------------------------------------");
    }

    private void printTasks() {
        System.out.println("    ------------------------------------");
        for (int i = 0; i < this.taskCount; i++) {
            System.out.println("    " + (i + 1) + ". [" + this.taskList.get(i).getStatusIcon() + "] "
                    + this.taskList.get(i));
        }
        System.out.println("    ------------------------------------");
    }


    public static void main(String[] args) {
        KillJoy kj = new KillJoy();
        kj.start();
    }
}
