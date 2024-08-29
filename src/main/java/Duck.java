import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//merging

public class Duck {
    public static void main(String[] args) throws DuckException {
        Scanner scan = new Scanner(System.in);
        Parser parser = new Parser();
        TaskList cmds = new TaskList(Storage.load(), Storage.loadNum());
        int cmdNum = Storage.loadNum();

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        boolean cont = true;
        while (cont) {
            System.out.println(cmds.cmdNum);
            String userCmd = scan.nextLine();
            cont = parser.parseCmd(cmds, userCmd);
            /*if (Parser.parseCmd(userCmd).equals("bye")) {
                Ui.printText("Bye. Hope to see you again soon!");
                break;
            } else if (Parser.parseCmd(userCmd).equals("list")) {
                if (cmdNum==0) {
                    continue;
                }
                cmds.printTasks();

            } else if (Parser.parseCmd(userCmd).equals("mark")) {
                int num = Integer.valueOf(userCmd.substring(5));
                if (num < cmdNum + 1) {
                    cmds.get(num-1).mark();
                } else {
                    throw new DuckException("There is no task with the given task number.");
                }
                Ui.printText("Nice! I've marked this task as done:\n "+ cmds.get(num-1));
            } else if (Parser.parseCmd(userCmd).equals("unmark")) {

            } else if (Parser.parseCmd(userCmd).equals("delete")) {
                int num = Integer.valueOf(userCmd.substring(7));
                if (num < cmdNum + 1) {
                    cmds.delete(num);
                    //cmdNum--;
                } else {
                    throw new DuckException("There is no task with the given task number.");
                }

            } else if (Parser.parseCmd(userCmd).equals("todo")) {
                /*if (userCmd.length() == 4) {
                    throw new DuckException("The description of a todo cannot be empty.");
                }
                cmds.add(new Todo(userCmd.substring(5)));
                //cmdNum++;
            } else if (Parser.parseCmd(userCmd).equals("deadline")) {
                if (userCmd.length() == 8) {
                    throw new DuckException("The description of a deadline cannot be empty.");
                }
                userCmd = userCmd.substring(9);
                int n = userCmd.indexOf("/");
                if ((n==-1) || (userCmd.substring(n + 4).isEmpty())) {
                    throw new DuckException("A deadline needs an end date.");
                }
                cmds.add(new Deadline(userCmd.substring(0, n), userCmd.substring(n+4)));
                //cmdNum++;
            } else if (Parser.parseCmd(userCmd).equals("event")) {
                if (userCmd.length() == 5) {
                    throw new DuckException("The description of a event cannot be empty.");
                }
                userCmd = userCmd.substring(6);
                int start = userCmd.indexOf("/");
                int end = userCmd.substring(start+1).indexOf("/");
                if ((start == -1) || (end == -1)) {
                    throw new DuckException("An event needs both a start and end date or time.");
                }
                cmds.add(new Event(userCmd.substring(0, start),
                        userCmd.substring(start+6, start+end),
                        userCmd.substring(start+1+end+4)));
                //cmdNum++;
            } else {
                throw new DuckException("Given command is not a valid user input.");
            }*/
        }
        scan.close();
    }

}

