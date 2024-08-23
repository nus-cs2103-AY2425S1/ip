import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duck {
    public static void main(String[] args) throws DuckException {
        Scanner scan = new Scanner(System.in);
        Task[] cmds = new Task[100];
        int cmdNum = 0;

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        while (true) {
            String userCmd = scan.nextLine();
            if (userCmd.toLowerCase().equals("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            } else if (userCmd.toLowerCase().equals("list")) {
                if (cmdNum==0) {
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                for (int n = 1; n<=cmdNum; n++) {
                    System.out.println(n + ". " + cmds[n - 1]);
                }
            } else if ((userCmd.length() >= 4) && (userCmd.substring(0,4).toLowerCase().equals("mark"))) {
                int num = Integer.valueOf(userCmd.substring(5));
                if (num < cmdNum + 1) {
                    cmds[num-1].mark();
                } else {
                    throw new DuckException("There is no task with the given task number.");
                }
                System.out.println("Nice! I've marked this task as done:\n "+ cmds[num-1]);
            } else if ((userCmd.length() >= 6) && (userCmd.substring(0,6).toLowerCase().equals("unmark"))) {
                int num = Integer.valueOf(userCmd.substring(7));
                if (num < cmdNum + 1) {
                    cmds[num-1].unmark();
                } else {
                    throw new DuckException("There is no task with the given task number.");
                }
                System.out.println("OK, I've marked this task as not done yet:\n "+ cmds[num-1]);
            } else if ((userCmd.length() >= 6) && (userCmd.substring(0,6).toLowerCase().equals("delete"))) {
                int num = Integer.valueOf(userCmd.substring(7));
                if (num < cmdNum + 1) {
                    System.out.println("Noted. I've removed this task:\n "+ cmds[num-1]);

                    ArrayList<Task> newCmds = new ArrayList<Task>(Arrays.asList(cmds));
                    newCmds.remove(num-1);
                    cmds = newCmds.toArray(new Task[100]);
                    cmdNum--;

                    System.out.println("Now you have " + cmdNum + " tasks in the list.");
                } else {
                    throw new DuckException("There is no task with the given task number.");
                }

            } else if ((userCmd.length() >= 4) && (userCmd.substring(0,4).toLowerCase().equals("todo"))) {
                if (userCmd.length() == 4) {
                    throw new DuckException("The description of a todo cannot be empty.");
                }
                cmds[cmdNum] = new Todo(userCmd.substring(5));
                System.out.println("Got it. I've added this task:\n  " + cmds[cmdNum]);
                cmdNum++;
                System.out.println("Now you have " + cmdNum + " tasks in the list.");
            } else if ((userCmd.length() >= 8) && (userCmd.substring(0,8).toLowerCase().equals("deadline"))) {
                if (userCmd.length() == 8) {
                    throw new DuckException("The description of a deadline cannot be empty.");
                }
                userCmd = userCmd.substring(9);
                int n = userCmd.indexOf("/");
                if (n==-1) {
                    throw new DuckException("A deadline needs an end date.");
                }

                cmds[cmdNum] = new Deadline(userCmd.substring(0, n), userCmd.substring(n+4));
                System.out.println("Got it. I've added this task:\n  " + cmds[cmdNum]);
                cmdNum++;
                System.out.println("Now you have " + cmdNum + " tasks in the list.");
            } else if ((userCmd.length() >= 5) && (userCmd.substring(0,5).toLowerCase().equals("event"))) {
                if (userCmd.length() == 5) {
                    throw new DuckException("The description of a event cannot be empty.");
                }
                userCmd = userCmd.substring(6);
                int start = userCmd.indexOf("/");
                int end = userCmd.substring(start+1).indexOf("/");
                if ((start == -1) || (end == -1)) {
                    throw new DuckException("An event needs both a start and end date or time.");
                }

                cmds[cmdNum] = new Event(userCmd.substring(0, start),
                        userCmd.substring(start+6, start+end),
                        userCmd.substring(start+1+end+4));
                System.out.println("Got it. I've added this task:\n  " + cmds[cmdNum]);
                cmdNum++;
                System.out.println("Now you have " + cmdNum + " tasks in the list.");
            } else {
                throw new DuckException("Given command is not a valid user input.");
            }
        }
        scan.close();
    }

}


