import java.util.Scanner;

public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = " Hello! I'm Simon \n" +
            " What can I do for you?\n";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    Task[] taskList = new Task[100];
    int taskCount = 0;
    public void run() {
        System.out.print(WLC_MSG);
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")){
            if (input.isEmpty()) {
                continue;
            }
            else if (input.equals("list")){
                printAllTasks();
                continue;
            }
            else if(input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList[index].markAsDone();
                String prMsg = printMessage("\tNice! I've marked this task as done:\n" + "\t" + taskList[index].toString());
                System.out.print(prMsg);
                continue;
            }
            else if(input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList[index].markAsNotDone();
                String prMsg = printMessage("\tOK, I've marked this task as not done yet:\n" + "\t" + taskList[index].toString());
                System.out.print(prMsg);
                continue;
            }
            else if (input.startsWith("deadline")) {
                String[] info = input.substring(9).split("/by");
                Deadline deadline = new Deadline(info[0], taskCount,info[1]);
                taskList[taskCount] = deadline;
                taskCount++;
                String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + deadline.toString()) + "\tNow you have " + taskCount +" tasks in the list.";
                System.out.println(prMsg);
                continue;
            }

            else if (input.startsWith("event")) {
                String[] info = input.substring(5).split("/from");
                String[] deadlines = info[1].split("/to");
                String from = deadlines[0];
                String to = deadlines[1];
                String name = info[0];
                Events event = new Events(name, taskCount, from, to);
                taskList[taskCount] = event;
                taskCount++;
                String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + event.toString()) + "\tNow you have " + taskCount +" tasks in the list.";
                System.out.println(prMsg);
                continue;
            }
            else if (input.startsWith("todo")) {
                String info = input.substring(4).trim();
                ToDo todo = new ToDo(info, taskCount);
                taskList[taskCount] = todo;
                taskCount++;
                String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + todo.toString()) + "\tNow you have " + taskCount +" tasks in the list.";
                System.out.println(prMsg);
                continue;
            }
            taskList[taskCount] = new Task(input, taskCount);
            taskCount++;
            String prMsg = printMessage("added: " + input);
            System.out.print(prMsg);
        }
        System.out.print(printMessage(EXT_MSG));
    }
    public static void main(String[] args) {
        Simon simon = new Simon();
        simon.run();
    }

    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    private void printAllTasks() {
        System.out.print(HOR_LINE);
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList[i];
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
        System.out.print(HOR_LINE);
    }
}

