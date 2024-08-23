import javax.imageio.ImageReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MGTOW {
    private static TaskStorage tasks = new TaskStorage();
    public static void printMsg(String str){
        System.out.println("\t" + str);
    }
    public static void printTask(int ind, Task task) {
        System.out.println("\t" + ind + ". " + task);
    }

    public static void addTodo(String desc, TaskStorage tasks) throws InvalidTaskException {
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        }

        tasks.addTask(new ToDo(desc));
    }

    public static void addEvent(String desc, String start, String end, TaskStorage tasks) throws InvalidTaskException{
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        } else if (start.isBlank()){
            throw new InvalidTaskException("Start when? Try again");
        } else if (end.isBlank()){
            throw new InvalidTaskException("Till when? Try again");
        }

        tasks.addTask(new Event(desc,start,end));
    }

    public static void addDeadline(String desc, String end, TaskStorage tasks) throws InvalidTaskException {
        if (desc.isBlank()) {
            throw new InvalidTaskException("What you want me to add?");
        } else if (end.isBlank()){
            throw new InvalidTaskException("By when? Try again");
        }

        tasks.addTask(new Deadline(desc,end));
    }

    public static void main(String[] args) {
        String logo = "";
        String line = "____________________________________________";
        Scanner sc = new Scanner(System.in);

        printMsg(line);
        printMsg("Hello! I'm MGTOW");
        printMsg("What can I do for you?");
        printMsg(line);

        Boolean finished = false;

        while (!finished){
            String input = sc.nextLine();
            printMsg(line);

            if (input.equals("bye")){
                finished = true;
                sc.close();
            } else {
                String cmd = input;
                String desc = "";
                if (input.contains(" ")){
                    cmd = input.substring(0, input.indexOf(" "));
                    desc = input.substring(input.indexOf(" ") + 1);
                }
                if (cmd.equals("list")) {
                    tasks.listAllTasks();
                } else if (cmd.equals("mark")) {
                    int ind = Integer.parseInt(desc)-1;
                    try{
                        tasks.markTask(ind);
                    } catch (InvalidTaskException e){
                        printMsg(e.getMessage());
                    }
                } else if (cmd.equals("unmark")) {
                    int ind = Integer.parseInt(desc)-1;
                    try{
                        tasks.unmarkTask(ind);
                    } catch (InvalidTaskException e){
                        printMsg(e.getMessage());
                    }
                } else if (cmd.equals("delete")) {
                    int ind = Integer.parseInt(desc)-1;
                    try{
                        tasks.deleteTask(ind);
                    } catch (InvalidTaskException e){
                        printMsg(e.getMessage());
                    }
                } else if (cmd.equals("todo")) {
                    try {
                        addTodo(desc, tasks);
                    } catch(InvalidTaskException e) {
                        printMsg(e.getMessage());
                    }
                } else if (cmd.equals("deadline")) {
                    try {
                        String arg[] = desc.split("/by ");
                        if (arg.length > 1) {
                            addDeadline(arg[0], arg[1], tasks);
                        } else if (arg.length > 0) {
                            addDeadline(arg[0], "", tasks);
                        } else {
                            addDeadline("", "", tasks);
                        }
                    } catch(InvalidTaskException e) {
                        printMsg(e.getMessage());
                    }
                } else if (cmd.equals("event")) {
                    try {
                        String arg[] = desc.split("/from ");
                        if (arg.length > 1) {
                            String time[] = arg[1].split("/to ");
                            if (time.length > 1){
                                addEvent(arg[0], time[0], time[1], tasks);
                            } else {
                                addEvent(arg[0], time[0], "", tasks);
                            }
                        } else if (arg.length > 0) {
                            addEvent(arg[0], "", "",tasks);
                        } else {
                            addEvent("", "","", tasks);
                        }
                    } catch(InvalidTaskException e) {
                        printMsg(e.getMessage());
                    }
                } else {
                    try {
                        throw new InvalidTaskException("What you talking?");
                    } catch (InvalidTaskException e) {
                        printMsg(e.getMessage());
                    }
                }
                printMsg(line);
            }
        }

        printMsg("OK bye time to MGTOW");
        printMsg(line);
    }
}
