package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileWriter; 
import java.io.FileReader; 
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Duke {

    public static int TODO = 0;
    public static int DEADLINE = 1;
    public static int EVENT = 2;

    public static class Ui {
        Ui(String openingText, String closingText) {
            this.openingText = openingText;
            this.closingText = closingText;
            this.horizontalLine = horizontalLine = "----------------------------------------------------------";
        }

        void printHorizontalLine() {
            System.out.println(horizontalLine);
        }

        void printOpening() {
            System.out.println(horizontalLine);
            System.out.println(openingText);
            System.out.println(horizontalLine);
        }

        void printMessage(String message) {
            System.out.println(horizontalLine);
            System.out.println(message);
            System.out.println(horizontalLine);
        }

        void printClosing() {

            System.out.println(horizontalLine);
            System.out.println(closingText);
            System.out.println(horizontalLine);
        }

        void printTaskAdded(Task task, int len) {
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            task.print();
            System.out.println("Now you have " + len + " tasks in the list.");
            System.out.println(horizontalLine);
        }

        void printTaskDeleted(Task task, int len) {
            System.out.println(horizontalLine);
            System.out.println("Noted. I've removed this task:");
            task.print();
            System.out.println("Now you have " + len + " tasks in the list.");
            System.out.println(horizontalLine);
        }

        void printTaskMarked(Task task) {
            System.out.println(horizontalLine);
            System.out.println("Nice! I've marked this task as done:");
            task.print();
            System.out.println(horizontalLine);
        }

        void printTaskUnmarked(Task task) {
            System.out.println(horizontalLine);
            System.out.println("OK, I've marked this task as not done yet:");
            task.print();
            System.out.println(horizontalLine);
        }

        void printError(String str) {
            System.out.println(horizontalLine);
            System.out.println(str);
            System.out.println(horizontalLine);
        }

        String openingText;
        String closingText;
        String horizontalLine;
    }

    public static class Parser {

        public Parser() {
            userInput = "";
            this.ptr = 0;
        }
        
        public Parser(String userInput) {
            this.userInput = userInput;
            this.ptr = 0;
        }

        public void readInput(String newUserInput) {
            this.userInput = newUserInput;
            this.ptr = 0;
        }

        public String getArgument(char escapeCharacter) {
            int len = userInput.length();
            int startIndex = ptr;

            while(ptr < len) {
                if(userInput.charAt(ptr) == escapeCharacter) {
                    break;
                }
                ptr++;
            }

            return userInput.substring(startIndex, ptr++);
        }

        public String getArgument(char escapeCharacter, int advanceAmount) {
            int len = userInput.length();
            int startIndex = ptr;

            while(ptr < len) {
                if(userInput.charAt(ptr) == escapeCharacter) {
                    break;
                }
                ptr++;
            }

            int oldPtr = ptr;
            ptr += advanceAmount;

            return userInput.substring(startIndex, oldPtr);
        }

        String userInput;
        int ptr;
    }

    public static class TaskList {

        TaskList(Ui ui) {
            this.tasks = new ArrayList<Task>();
            this.ui = ui;
        }

        TaskList(ArrayList<Task> tasks, Ui ui) {
            this.tasks = tasks;
            this.ui = ui;
        }

        TaskList(TaskList taskList, Ui ui) {
            this.tasks = taskList.tasks;
            this.ui = ui;
        }

        void addTask(Task task) {
            tasks.add(task);
            ui.printTaskAdded(task, tasks.size());
        }

        void deleteTask(int rank) {

            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }

            Task toRemove = tasks.get(rank-1);
            tasks.remove(rank-1);

            ui.printTaskDeleted(toRemove, tasks.size());
        }

        void listTasks() {
            int numberOfMessages = tasks.size();

            ui.printHorizontalLine();

            for(int i=0; i<numberOfMessages; i++) {
                tasks.get(i).print(i+1);
            }

            ui.printHorizontalLine();
        }

        void markTask(int rank) {
            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }
            Task task = tasks.get(rank - 1);

            task.mark();

            ui.printTaskMarked(task);
        }

        void unmarkTask(int rank) {

            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }

            Task task = tasks.get(rank - 1);

            task.unmark();

            ui.printTaskUnmarked(task);
        }

        private boolean isMatch(String query, Task task) {
            String taskName = task.name;
            int qlen = query.length();
            int tlen = taskName.length();

            if(qlen > tlen) {
                return false;
            }

            int startPtr = 0;
            int endPtr = qlen-1;

            while(endPtr < tlen) {
                if(taskName.substring(startPtr, endPtr + 1).equals(query)) {
                    return true;
                }

                ++startPtr;
                ++endPtr;
            }

            return false;
        }

        void fetchQuery(String query) {
            int len = tasks.size();
            ArrayList<Task> matchingTasks = new ArrayList<Task>();
            ui.printHorizontalLine();
            System.out.println("Here are the matching tasks in your list:");
            for(int i=0; i<len; ++i) {
                Task task = this.tasks.get(i);
                if(isMatch(query, task)) {
                    matchingTasks.add(task);
                }
            }

            TaskList matchingTaskList = new TaskList(matchingTasks, this.ui);
            matchingTaskList.listTasks();
        }

        ArrayList<Task> tasks;
        Ui ui;
    }

    public static class Storage {

        Storage(String filepath, Ui ui) {
            this.filepath = filepath;
            this.ui = ui;
        }

        public void save(TaskList taskList) {

            ArrayList<Task> tasks = taskList.tasks;
            try {
                File myObj = new File(filepath);
                myObj.createNewFile();

                FileWriter fw = new FileWriter(filepath);

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

        public TaskList load() {
            TaskList taskList = new TaskList(ui);

            try {
                File myObj = new File(filepath);
                myObj.createNewFile();

                FileReader fr = new FileReader(filepath);

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

                    taskList.addTask(task);
                }

            } catch (IOException e) {

                System.out.println("An error occurred.");
                e.printStackTrace();

            };

            return taskList;
        }

        String filepath;
        Ui ui;
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

        return message.substring(startIndex, ptr++);
    }

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
            
            try {
                LocalDate date = LocalDate.parse(deadline, DateTimeFormatter.ISO_DATE);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

                this.deadline = date.format(myFormatObj);
            } catch (Exception e) {
                this.deadline = deadline;
            }
        }

        Task(String name, int type, String[] eventTimings) {
            this.name = name;
            this.marked = false;
            this.type = type;

            try {
                LocalDate date = LocalDate.parse(eventTimings[0], DateTimeFormatter.ISO_DATE);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

                eventTimings[0] = date.format(myFormatObj);
            } catch (Exception e) {
                // do nothing
                System.out.println(e);
            }

            try {
                LocalDate date = LocalDate.parse(eventTimings[1], DateTimeFormatter.ISO_DATE);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

                eventTimings[1] = date.format(myFormatObj);
            } catch (Exception e) {
                // do nothing
            }

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


    public static boolean isEmptyString(String str) {
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) != ' ')
                return false;
        }

        return true;
    }

    /*
        file format:
            - TODO/DEADLINE/EVENT|MARKED?|description|Deadline1|Deadline2|
    */

    public static void main(String[] args) {

        String exitCommand = "bye";
        String listCommand = "list";

        String openingText = "Hello! I'm Jeff\n " +
                 "What can I do for you?";
        
        String closingText = "Bye. Hope to see you again soon!";

        Ui ui = new Ui(openingText, closingText);
        Storage storage = new Storage("data.txt", ui);
        TaskList taskList = storage.load();

        Scanner inputReader = new Scanner(System.in);
        Parser parser = new Parser();

        ui.printOpening();

        while(true)
        {
            storage.save(taskList);
            String input = inputReader.nextLine();
            parser.readInput(input);

            String command = parser.getArgument(' ');

            if(command.equals(exitCommand)) {

                ui.printClosing();
                break;

            } else if(command.equals("list")) {

                taskList.listTasks();

            } else if(command.equals("mark")) {

                int rankToMark = Integer.valueOf(parser.getArgument('\n'));

                taskList.markTask(rankToMark);

            } else if(command.equals("unmark")) {

                int rankToUnmark = Integer.valueOf(parser.getArgument('\n'));

                taskList.unmarkTask(rankToUnmark);
            } else if(command.equals("todo")) {

                
                if(input.length() == 4) {
                    ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                    break;
                }

                String taskName = parser.getArgument('\n');

                if(isEmptyString(taskName)) {
                    ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                    break;
                }

                taskName = taskName.trim();
                taskList.addTask(new Task(taskName, TODO));
            } else if(command.equals("deadline")) {

                if(input.length() <= 9) {
                    ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                    break;
                }

                String taskName = parser.getArgument('/', 4);

                if(isEmptyString(taskName)) {
                    ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                    break;
                }

                if(input.length() <= 9 + taskName.length() + 4) {
                    ui.printError("Error: No deadline provided. Terminating program.");
                    break;
                }

                String deadline = parser.getArgument('\n');
                deadline = deadline.trim();

                if(isEmptyString(deadline)) {
                    ui.printError("Error: No deadline provided. Terminating program.");
                    break;
                }

                taskName = taskName.trim();

                taskList.addTask(new Task(taskName, DEADLINE, deadline));
            } else if(command.equals("event")) {

                if(input.length() <= 5) {
                    ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                    break;
                }

                String taskName = parser.getArgument('/', 6);

                if(isEmptyString(taskName)) {
                    ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                    break;
                }

                if(input.length() <= 6 + taskName.length() + 6) {
                    ui.printError("Error: No start time provided for event. Terminating program.");
                    break;
                }

                String startTime = parser.getArgument('/', 4);

                if(isEmptyString(startTime)) {
                    ui.printError("Error: No start time provided for event. Terminating program.");
                    break;
                }

                if(input.length() <= 6 + taskName.length() + 6 + startTime.length() + 4) {
                    ui.printError("Error: No end time provided for event. Terminating program.");
                    break;
                }

                String endTime = parser.getArgument('\n');
                endTime = endTime.trim();

                if(isEmptyString(endTime)) {
                    ui.printError("Error: No end time provided for event. Terminating program.");
                    break;
                }

                taskName = taskName.trim();
                startTime = startTime.trim();
                
                String[] eventTimings = new String[] {startTime, endTime};


                taskList.addTask(new Task(taskName, EVENT, eventTimings));
            } else if(command.equals("delete")) {

                if(input.length() <= 7) {
                    ui.printError("Error: You need to specify which task to delete. Terminating program.");
                    break;
                }

                int rankToDelete = Integer.valueOf(parser.getArgument('\n'));

                taskList.deleteTask(rankToDelete);
            } else if(command.equals("find")) {
                if(command.length() == 4) {
                    ui.printError("Error: You need to give a search query. Terminating program.");
                    break;
                }
                String query = parser.getArgument('\n');
                taskList.fetchQuery(query);
            }  else {

                System.out.println("Error: Invalid input, terminating program.");
                break;

            }
        }
    }
}