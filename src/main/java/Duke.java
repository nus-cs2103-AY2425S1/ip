import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileWriter; 
import java.io.FileReader; 
import java.io.BufferedReader;
import java.io.IOException; 


public class Duke {

    public static int TODO = 0;
    public static int DEADLINE = 1;
    public static int EVENT = 2;
    public static class Task {

        Task(String name) {
            this.name = name;
            this.marked = false;
        }

        Task(String name, int type) {
            this.name = name;
            this.marked = false;
            this.type = type;
        }

        Task(String name, int type, String deadline) {
            this.name = name;
            this.marked = false;
            this.type = type;
            this.deadline = deadline;
        }

        Task(String name, int type, String[] eventTimings) {
            this.name = name;
            this.marked = false;
            this.type = type;
            this.eventTimings = eventTimings;
        }

        public void mark() {
            this.marked = true;
        }

        public void unmark() {
            this.marked = false;
        }

        public void print() {
            String message = "";

            if(this.type == TODO) {
                message += "[T]";
            } else if(this.type == DEADLINE) {
                message += "[D]";
            } else if (this.type == EVENT) {
                message += "[E]";
            } else {
                message += "[ ]";
            }

            if(this.marked) {
                message += "[X] ";
            } else {
                message += "[ ] ";
            }

            message += name;

            if(this.type == DEADLINE) {
                message += " (by: " + this.deadline + ")";
            } else if (this.type == EVENT) {
                message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";
            }

            System.out.println(message);
        }

        public void print(int rank) {
            String message = rank + ".";

            if(this.type == TODO) {
                message += "[T]";
            } else if(this.type == DEADLINE) {
                message += "[D]";
            } else if (this.type == EVENT) {
                message += "[E]";
            } else {
                message += "[ ]";
            }

            if(this.marked) {
                message += "[X] ";
            } else {
                message += "[ ] ";
            }

            message += name;

            if(this.type == DEADLINE) {
                message += " (by: " + deadline + ")";
            } else if (this.type == EVENT) {
                message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";
            }

            System.out.println(message);
        }
        private String name;
        private boolean marked;
        private int type;

        private String deadline;

        private String[] eventTimings = new String[2];
    }
    public static String horizontalLine = "----------------------------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void printOpening() {
        String openingText = "Hello! I'm Jeff\n " +
                "What can I do for you?";

        System.out.println(horizontalLine);
        System.out.println(openingText);
        System.out.println(horizontalLine);
    }

    public static void printMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printClosing() {
        String closingText = "Bye. Hope to see you again soon!";

        System.out.println(horizontalLine);
        System.out.println(closingText);
        System.out.println(horizontalLine);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        task.print();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void deleteTask(int rank) {
        Task toRemove = tasks.get(rank-1);
        tasks.remove(rank-1);

        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:");
        toRemove.print();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void listTasks()
    {
        int numberOfMessages = tasks.size();

        System.out.println(horizontalLine);

        for(int i=0; i<numberOfMessages; i++) {
            tasks.get(i).print(i+1);
        }

        System.out.println(horizontalLine);
    }

    public static void markTask(int rank) {
        Task task = tasks.get(rank - 1);

        task.mark();

        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        task.print();
        System.out.println(horizontalLine);
    }

    public static void unmarkTask(int rank) {
        Task task = tasks.get(rank - 1);

        task.unmark();

        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        task.print();
        System.out.println(horizontalLine);
    }

    public static String getCommand(String message, int startIndex, char escapeCharacter) {
        int len = message.length();
        int ptr = startIndex;

        while(ptr < len) {
            if(message.charAt(ptr) == escapeCharacter) {
                break;
            }
            ptr++;
        }

        return message.substring(startIndex, ptr);
    }

    public static boolean isEmptyString(String str) {
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) != ' ')
                return false;
        }

        return true;
    }

    public static void printError(String str) {
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    /*
        file format:
            - TODO/DEADLINE/EVENT|MARKED?|description|Deadline1|Deadline2|
    */

    public static String datafile = "data.txt";

    public static void save() {
        try {
            File myObj = new File(datafile);
            myObj.createNewFile();

            FileWriter fw = new FileWriter(datafile);

            int tasksLen = tasks.size();
            
            for(int i=0; i<tasksLen; ++i) {
                Task task = tasks.get(i);
                String toWrite = "";
                if (task.type == TODO) {
                    toWrite += "T|";
                } else if (task.type == DEADLINE) {
                    toWrite += "D|";
                } else if (task.type == EVENT) {
                    toWrite += "E|";
                }

                if(task.marked) {
                    toWrite += "1|";
                } else {
                    toWrite += "0|";
                }

                toWrite += task.name;
                toWrite += "|";

                if(task.type == TODO) {
                    toWrite += "||";
                }

                if(task.type == DEADLINE) {
                    toWrite += task.deadline + "||";
                }

                if(task.type == EVENT) {
                    toWrite += task.eventTimings[0] + "|" + task.eventTimings[1] + "|";
                }

                toWrite += '\n';

                fw.write(toWrite);
            }

            fw.close();

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        };
    }

    public static void load() {
        try {

            File myObj = new File(datafile);
            myObj.createNewFile();

            FileReader fr = new FileReader(datafile);

            BufferedReader br = new BufferedReader(fr);

            String strLine;


            while((strLine = br.readLine()) != null) {
                int ptr = 0;
                int len = strLine.length();
                String taskType = "" + strLine.charAt(ptr);
                ptr += 2;
                String isMarked = "" + strLine.charAt(ptr);
                ptr += 2;
                String taskDescription = getCommand(strLine, ptr, '|');
                ptr += taskDescription.length() + 1;
                String deadline1 = getCommand(strLine, ptr, '|');
                ptr += deadline1.length() + 1;
                String deadline2 = getCommand(strLine, ptr, '|');
                ptr += deadline2.length() + 1;

                Task task;

                if(taskType.equals("T")) {
                    task = new Task(taskDescription, TODO);
                } else if (taskType.equals("D")) {
                    task = new Task(taskDescription, DEADLINE, deadline1);
                } else {
                    String[] eventTimings = new String[] {deadline1, deadline2};
                    task = new Task(taskDescription, EVENT, eventTimings);
                }

                if(isMarked.equals("1")) {
                    task.mark();
                }

                tasks.add(task);
            }

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        };
    }

    public static void main(String[] args) {

        String exitCommand = "bye";
        String listCommand = "list";
        load();
        Scanner inputReader = new Scanner(System.in);

        printOpening();

        while(true)
        {
            String input = inputReader.nextLine();
            String command = getCommand(input, 0, ' ');

            if(command.equals(exitCommand)) {

                printClosing();
                save();
                break;

            } else if(command.equals("list")) {

                listTasks();

            } else if(command.equals("mark")) {

                int rankToMark = Integer.valueOf(input.substring(5));

                if(rankToMark < 1 || rankToMark > tasks.size()) {
                    printError("Error: The task number is out of bounds. Terminating program.");
                    break;
                }

                markTask(rankToMark);
                save();

            } else if(command.equals("unmark")) {

                int rankToUnmark = Integer.valueOf(input.substring(7));

                if(rankToUnmark < 1 || rankToUnmark > tasks.size()) {
                    printError("Error: The task number is out of bounds. Terminating program.");
                    break;
                }

                unmarkTask(rankToUnmark);
                save();
            } else if(command.equals("todo")) {

                if(input.length() == 4) {
                    printError("Error: The description of a todo cannot be empty. Terminating program.");
                    break;
                }

                String taskName = input.substring(5);
                if(isEmptyString(taskName)) {
                    printError("Error: The description of a todo cannot be empty. Terminating program.");
                    break;
                }
                addTask(new Task(taskName, TODO));
                save();
            } else if(command.equals("deadline")) {

                if(input.length() <= 9) {
                    printError("Error: The description of a deadline cannot be empty. Terminating program.");
                    break;
                }

                String taskName = getCommand(input, 9, '/');

                if(isEmptyString(taskName)) {
                    printError("Error: The description of a deadline cannot be empty. Terminating program.");
                    break;
                }

                if(input.length() <= 9 + taskName.length() + 4) {
                    printError("Error: No deadline provided. Terminating program.");
                    break;
                }

                String deadline = getCommand(input, 9 + taskName.length() + 4, ' ');

                if(isEmptyString(deadline)) {
                    printError("Error: No deadline provided. Terminating program.");
                    break;
                }

                taskName = taskName.substring(0, taskName.length() - 1);

                addTask(new Task(taskName, DEADLINE, deadline));
                save();
            } else if(command.equals("event")) {

                if(input.length() <= 5) {
                    printError("Error: The description of an event cannot be empty. Terminating program.");
                    break;
                }

                String taskName = getCommand(input, 6, '/');

                if(isEmptyString(taskName)) {
                    printError("Error: The description of an event cannot be empty. Terminating program.");
                    break;
                }

                if(input.length() <= 6 + taskName.length() + 6) {
                    printError("Error: No start time provided for event. Terminating program.");
                    break;
                }

                String startTime = getCommand(input, 6 + taskName.length() + 6, '/');

                if(isEmptyString(startTime)) {
                    printError("Error: No start time provided for event. Terminating program.");
                    break;
                }

                if(input.length() <= 6 + taskName.length() + 6 + startTime.length() + 4) {
                    printError("Error: No end time provided for event. Terminating program.");
                    break;
                }

                String endTime = getCommand(input, 6 + taskName.length() + 6 + startTime.length() + 4, ' ');

                if(isEmptyString(endTime)) {
                    printError("Error: No end time provided for event. Terminating program.");
                    break;
                }

                taskName = taskName.substring(0, taskName.length() - 1);
                startTime = startTime.substring(0, startTime.length() - 1);

                String[] eventTimings = new String[] {startTime, endTime};


                addTask(new Task(taskName, EVENT, eventTimings));
                save();
            } else if(command.equals("delete")) {
                if(input.length() <= 7) {
                    printError("Error: You need to specify which task to delete. Terminating program.");
                    break;
                }

                int rankToDelete = Integer.valueOf(input.substring(7));

                if(rankToDelete < 1 || rankToDelete > tasks.size()) {
                    printError("Error: The task number is out of bounds. Terminating program.");
                    break;
                }

                deleteTask(rankToDelete);
                save();
            } else {

                System.out.println("Error: Invalid input, terminating program.");
                break;

            }
        }
    }
}
