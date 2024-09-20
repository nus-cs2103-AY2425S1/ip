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
        /**
         * Returns a Ui object which is used to output
         * to the terminal to communicate with user
         *
         * @param openingText the opening message sent to users.
         * @param closingText the final message sent to users.
         * @return Ui object
         */
        Ui(String openingText, String closingText) {
            this.openingText = openingText;
            this.closingText = closingText;
            this.horizontalLine = horizontalLine = "----------------------------------------------------------";
        }

        /**
         * Returns void, just prints a horizontal line
         */
        void printHorizontalLine() {
            System.out.println(horizontalLine);
        }

        /**
         * Returns void, just prints the opening text
         */
        void printOpening() {
            System.out.println(horizontalLine);
            System.out.println(openingText);
            System.out.println(horizontalLine);
        }
         /**
         * Returns void, just prints a message
         * 
         * @param message message to be printed.
         */
        void printMessage(String message) {
            System.out.println(horizontalLine);
            System.out.println(message);
            System.out.println(horizontalLine);
        }

         /**
         * Returns void, just prints the closing tex
         */
        void printClosing() {

            System.out.println(horizontalLine);
            System.out.println(closingText);
            System.out.println(horizontalLine);
        }

         /**
         * Returns void, just prints the latest task added
         * 
         * @param task the task that was added
         * @param len the length of the task list after adding the task
         */
        String printTaskAdded(Task task, int len) {
            String response = "";
            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            System.out.println("Got it. I've added this task:");
            response += "Got it. I've added this task:" + '\n';

            task.print();
            response += task.print() + '\n';

            System.out.println("Now you have " + len + " tasks in the list.");
            response += "Now you have " + len + " tasks in the list." + '\n';

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            return response;
        }

         /**
         * Returns void, just prints the latest task deleted
         * 
         * @param task the task that was added
         * @param len the length of the task list after deleting the task
         */
        String printTaskDeleted(Task task, int len) {
            String response = "";

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            System.out.println("Noted. I've removed this task:");
            response += "Noted. I've removed this task:" + '\n';

            response += task.print() + '\n';

            System.out.println("Now you have " + len + " tasks in the list.");
            response += "Now you have " + len + " tasks in the list." + '\n';

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            return response;
        }

         /**
         * Returns void, just prints the latest task marked
         * 
         * @param task the task that was marked
         */
        String printTaskMarked(Task task) {
            String response = "";

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            System.out.println("Nice! I've marked this task as done:");
            response += "Nice! I've marked this task as done:" + '\n';

            response += task.print() + '\n';

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            return response;
        }

         /**
         * Returns void, just prints the latest task unmarked
         * 
         * @param task the task that was unmarked
         */
        String printTaskUnmarked(Task task) {
            String response = "";

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            System.out.println("OK, I've marked this task as not done yet:");
            response += "\"OK, I've marked this task as not done yet:" + '\n';

            response += task.print() + '\n';

            System.out.println(horizontalLine);
            response += horizontalLine + '\n';

            return response;
        }

         /**
         * Returns void, just prints error messages
         * 
         * @param str the error message to print
         */
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

        /**
         * Returns a Parser object for parsing user commands
         */
        public Parser() {
            userInput = "";
            this.ptr = 0;
        }

        /**
         * Returns a Parser object for parsing user commands
         * 
         * @param userInput the entire command the user has sent
         * @return Parser object
         */
        public Parser(String userInput) {
            this.userInput = userInput;
            this.ptr = 0;
        }


        /**
         * Returns void, just resets the parser state to match new
         * user command that was inputted
         * 
         * @param newUserInput the new command the user has inputted
         */
        public void readInput(String newUserInput) {
            this.userInput = newUserInput;
            this.ptr = 0;
        }

        /**
         * Returns a substring of this.userInput starting from ptr
         * until the first instance of the escapeCharacter that
         * appears past ptr.
         * 
         * @param escapeCharacter the escape character that determines
         * the end of the substring we should return
         * @return the substring from ptr to escapeCharacter (exclusive)
         */
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

        /**
         * Returns a substring of this.userInput starting from ptr
         * until the first instance of the escapeCharacter that
         * appears past ptr, and then advances the ptr by advanceAmount
         * 
         * @param escapeCharacter the escape character that determines
         * the end of the substring we should return
         * @return the substring from ptr to escapeCharacter (exclusive)
         */
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
        /**
         * Returns a TaskList object
         * 
         * @param ui a ui object for printing to the terminal when
         * the TaskList object is modified
         */
        TaskList(Ui ui) {
            this.tasks = new ArrayList<Task>();
            this.ui = ui;
        }

        /**
         * Returns a TaskList object
         * 
         * @param tasks an array list of Task objects which we
         * can directly copy into our tasks field
         * @param ui a ui object for printing to the terminal when
         * the TaskList object is modified
         */
        TaskList(ArrayList<Task> tasks, Ui ui) {
            this.tasks = tasks;
            this.ui = ui;
        }

        /**
         * Returns a TaskList object
         * 
         * @param taskList an existing taskList object that we can
         * copy into this object
         * @param ui a ui object for printing to the terminal when
         * the TaskList object is modified
         */
        TaskList(TaskList taskList, Ui ui) {
            this.tasks = taskList.tasks;
            this.ui = ui;
        }

        /**
         * Returns void, just adds task to our array of tasks
         * 
         * @param task the task to add
         */
        String addTask(Task task) {
            tasks.add(task);
            return ui.printTaskAdded(task, tasks.size());
        }

        void addTaskWithoutPrinting(Task task) {
            tasks.add(task);
        }

        /**
         * Returns void, just deletes a task from our array of tasks
         * 
         * @param rank the 1-indexed index of the task we wish to remove
         */
        String deleteTask(int rank) {
            assert rank >= 1;
            assert rank <= tasks.size();

            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }

            Task toRemove = tasks.get(rank-1);
            tasks.remove(rank-1);

            return ui.printTaskDeleted(toRemove, tasks.size());
        }

        /**
         * Returns void, prints all the tasks out to the terminal
         */
        String listTasks() {
            int numberOfMessages = tasks.size();
            String response = "";
            response += ui.horizontalLine;
            ui.printHorizontalLine();

            for(int i=0; i<numberOfMessages; i++) {
                response += tasks.get(i).print(i+1);
                response += '\n';
            }

            ui.printHorizontalLine();
            response += ui.horizontalLine;
            return response;
        }

        /**
         * Returns void, just marks a task in our array of tasks
         * 
         * @param rank the 1-indexed index of the task we wish to mark
         * in our array of tasks
         */
        String markTask(int rank) {
            assert rank >= 1;
            assert rank <= tasks.size();

            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }
            Task task = tasks.get(rank - 1);

            task.mark();

            return ui.printTaskMarked(task);
        }

        /**
         * Returns void, just unmarks a task in our array of tasks
         * 
         * @param rank the 1-indexed index of the task we wish to unmark
         * in our array of tasks
         */
        String unmarkTask(int rank) {
            assert rank >= 1;
            assert rank <= tasks.size();

            if(rank < 1 || rank > tasks.size()) {
                System.out.println("Error: The task number is out of bounds. Terminating program.");
                System.exit(0);
            }

            Task task = tasks.get(rank - 1);

            task.unmark();

            return ui.printTaskUnmarked(task);
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

        /**
         * Returns void, prints out all tasks that match the search query
         * 
         * @param query the search query used to filter out tasks
         */
        String fetchQuery(String query) {
            String response = "";

            int len = tasks.size();
            ArrayList<Task> matchingTasks = new ArrayList<Task>();

            ui.printHorizontalLine();
            response += ui.horizontalLine + '\n';

            System.out.println("Here are the matching tasks in your list:");
            response += "Here are the matching tasks in your list:\n";

            for(int i=0; i<len; ++i) {
                Task task = this.tasks.get(i);
                if(isMatch(query, task)) {
                    matchingTasks.add(task);
                }
            }

            TaskList matchingTaskList = new TaskList(matchingTasks, this.ui);
            response += matchingTaskList.listTasks() + '\n';

            return response;
        }

        ArrayList<Task> tasks;
        Ui ui;
    }

    public static class Storage {

        /**
         * Returns a storage object for saving and loading Task Lists
         * 
         * @param filepath the path to the storage file where we store
         * the task list data
         * @param ui a ui object for printing to terminal
         */
        Storage(String filepath, Ui ui) {
            this.filepath = filepath;
            this.ui = ui;
        }

        /**
         * Returns void, saves a taskList to a storage file.
         * The data format is TASK_TYPE|MARKED|TASK_NAME|DEADLINE1|DEADLINE2|
         * 
         * @param taskList the task list that we wish to save
         * to a storage file
         */
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

                    if(task.isMarked) {
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

        /**
         * Returns a TaskList, loads a taskList from a storage file into an arraylist.
         * The data format is TASK_TYPE|MARKED|TASK_NAME|DEADLINE1|DEADLINE2|
         * 
         * @return a TaskList that stores tasks matching the data stored in
         * the storage file
         */
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

                    taskList.addTaskWithoutPrinting(task);
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

    /**
     * Returns a substring of message from [startIndex, z) where z is the index
     * of the first escapeCharacter that occurs in message from startIndex onwards.
     * 
     * @param message the entire message we want to analyze
     * @param startIndex the starting index for our substring
     * @param escapeCharacter the character that we wish to use as a terminating character
     * @return the desired substring from startIndex up until the first escapeCharacter
     */
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

        /**
         * Returns a Task object
         * 
         * @param name the name of the task
         */
        Task(String name) {
            this.name = name;
            this.isMarked = false;
        }

        /**
         * Returns a Task object
         * 
         * @param name the name of the task
         * @param type the type of the task (todo/deadline/event)
         */
        Task(String name, int type) {
            this.name = name;
            this.isMarked = false;
            this.type = type;
        }

        /**
         * Returns a Task object
         * 
         * @param name the name of the task
         * @param type the type of the task (todo/deadline/event)
         * @param deadline the deadline for the task in case the type is deadline
         */
        Task(String name, int type, String deadline) {
            this.name = name;
            this.isMarked = false;
            this.type = type;
            
            try {
                LocalDate date = LocalDate.parse(deadline, DateTimeFormatter.ISO_DATE);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

                this.deadline = date.format(myFormatObj);
            } catch (Exception e) {
                this.deadline = deadline;
            }
        }

        /**
         * Returns a Task object
         * 
         * @param name the name of the task
         * @param type the type of the task (todo/deadline/event)
         * @param eventTimings the start and end timings for the task in
         * case its type is event
         */
        Task(String name, int type, String[] eventTimings) {
            this.name = name;
            this.isMarked = false;
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

        private void mark() {
            this.isMarked = true;
        }

        private void unmark() {
            this.isMarked = false;
        }

        private String print() {
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

            if(this.isMarked) {
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
            return message;
        }

        private String print(int rank) {
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

            if(this.isMarked) {
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
            return message;
        }
        private String name;
        private boolean isMarked;
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

    public String exitCommand = "bye";
    public String listCommand = "list";

    public String openingText = "Hello! I'm Jeff\n " +
            "What can I do for you?";

    public String closingText = "Bye. Hope to see you again soon!";

    public Ui ui = new Ui(openingText, closingText);
    public Storage storage = new Storage("data.txt", ui);
    public TaskList taskList = storage.load();

    public Scanner inputReader = new Scanner(System.in);
    public Parser parser = new Parser();

    public String getResponse(String input) {
        parser.readInput(input);
        String response = "";

        String command = parser.getArgument(' ');
        taskList = storage.load();
        if(command.equals(exitCommand)) {

            ui.printClosing();
            response = "bye!";

        } else if(command.equals("list")) {

            response = taskList.listTasks();

        } else if(command.equals("mark")) {

            int rankToMark = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.markTask(rankToMark);
        } else if(command.equals("unmark")) {

            int rankToUnmark = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.unmarkTask(rankToUnmark);
        } else if(command.equals("todo")) {
            assert input.length() > 5;

            if(input.length() == 4) {
                ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                response = "Error: The description of a todo cannot be empty.";
            }

            String taskName = parser.getArgument('\n');

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                response = "Error: The description of a todo cannot be empty.";
            }

            taskName = taskName.trim();
            response = taskList.addTask(new Task(taskName, TODO));
        } else if(command.equals("deadline")) {
            assert input.length() > 9;


            if(input.length() <= 9) {
                ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                response = "Error: The description of a deadline cannot be empty.";
            }

            String taskName = parser.getArgument('/', 4);

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                response = "Error: The description of a deadline cannot be empty.";
            }

            if(input.length() <= 9 + taskName.length() + 4) {
                ui.printError("Error: No deadline provided. Terminating program.");
                response = "Error: No deadline provided.";
            }

            String deadline = parser.getArgument('\n');
            deadline = deadline.trim();

            if(isEmptyString(deadline)) {
                ui.printError("Error: No deadline provided. Terminating program.");
                response = "Error: No deadline provided.";
            }

            taskName = taskName.trim();

            response = taskList.addTask(new Task(taskName, DEADLINE, deadline));

        } else if(command.equals("event")) {

            assert input.length() > 6;

            if(input.length() <= 5) {
                ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                response = "Error: The description of an event cannot be empty.";
            }

            String taskName = parser.getArgument('/', 6);

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                response = "Error: The description of an event cannot be empty.";
            }

            if(input.length() <= 6 + taskName.length() + 6) {
                ui.printError("Error: No start time provided for event. Terminating program.");
                response = "Error: No start time provided for event.";
            }

            String startTime = parser.getArgument('/', 4);

            if(isEmptyString(startTime)) {
                ui.printError("Error: No start time provided for event. Terminating program.");
                response = "Error: No start time provided for event.";
            }

            if(input.length() <= 6 + taskName.length() + 6 + startTime.length() + 4) {
                ui.printError("Error: No end time provided for event. Terminating program.");
                response = "Error: No end time provided for event.";
            }

            String endTime = parser.getArgument('\n');
            endTime = endTime.trim();

            if(isEmptyString(endTime)) {
                ui.printError("Error: No end time provided for event. Terminating program.");
                response = "Error: No end time provided for event.";
            }

            taskName = taskName.trim();
            startTime = startTime.trim();

            String[] eventTimings = new String[] {startTime, endTime};


            response = taskList.addTask(new Task(taskName, EVENT, eventTimings));
        } else if(command.equals("delete")) {
            assert input.length() > 7;

            if(input.length() <= 7) {
                ui.printError("Error: You need to specify which task to delete. Terminating program.");
                response = "Error: You need to specify which task to delete.";
            }

            int rankToDelete = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.deleteTask(rankToDelete);
        }  else if(command.equals("find")) {
            assert input.length() > 5;

            if(input.length() <= 5) {
                ui.printError("Error: You need to give a search query. Terminating program.");
                response = "Error: You need to give a search query.";
            }
            String query = parser.getArgument('\n');
            response = taskList.fetchQuery(query);
        }  else {

            System.out.println("Error: Invalid input, terminating program.");
            return "Error: Invalid input";
        }

        storage.save(taskList);
        return response;
    }

}