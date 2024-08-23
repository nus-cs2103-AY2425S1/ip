package main.java;
import main.java.Task;
import main.java.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class MrTracker {

    public enum PrefixString {
        BYE("bye"),
        LIST("list"),
        MARK("mark "),
        UNMARK("unmark "),
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event "),
        DELETE("delete ");

        private final String prefix;

        PrefixString (String prefix) {
            this.prefix = prefix;
        }

        public static PrefixString checkPrefixString (String input) {
            for (PrefixString item: PrefixString.values()) {

                if (item.equals(BYE)) {
                    if (input.equals("bye")) {
                        return item;
                    }
                } else if (item.equals(LIST)) {
                    if (input.equals("list")) {
                        return item;
                    }
                } else {
                    if (input.startsWith(item.prefix)) {
                        return item;
                    }
                }
            }
            return null;
        }

    }

    public static void printLine () {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }

    public static boolean checkValidIndex (String command, int start) {
        String trimmed = command.substring(start);
        try {
            int res = Integer.parseInt(trimmed);
            return true;
        } catch (Exception ex) {
            // if the string after "mark " are not numbers, return an invalid index
            return false;
        }
    }

    public static int checkIndex (String command, int start) {
        String trimmed = command.substring(start);
        try {
            int res = Integer.parseInt(trimmed);
            return res;
        } catch (Exception ex) {
            // should not happen unless something bad happens
            return Integer.MAX_VALUE;
        }
    }

    public static void markAndUnmark (ArrayList<Task> taskList, String input, int index, boolean isMark) throws RuntimeException {
        // only comes here if something really bad happens
        if (index == Integer.MAX_VALUE) {
            throw new RuntimeException();
        } else if (index < 1 || index > taskList.size()) {
            System.out.println("task " + index + " does not exist");
        } else {
            index--;
            Task curr = taskList.get(index);
            if (isMark) {
                curr.mark();
                System.out.println("task\n  " + curr.toString() + "\nis marked!");
            } else {
                curr.unMark();
                System.out.println("task\n  " + curr.toString() + "\nis unmarked!");
            }
        }
    }

    public static void addTodo (ArrayList<Task> taskList, String arg) {
        try {
            ToDo newToDo = new ToDo(arg);
            taskList.add(newToDo);
            System.out.println("I have added the task " + newToDo.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
            return;
        } catch (InvalidTaskNameException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void addDeadLine (ArrayList<Task> taskList, String arg) {

        try {
            Task newDeadLine = new DeadLine(arg);
            taskList.add(newDeadLine);
            System.out.println("I have added the task " + newDeadLine.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
            return;
        } catch (InvalidTaskNameException ex) {
            System.out.println(ex.getMessage());
        } catch (InvalidDateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addEvent (ArrayList<Task> taskList, String arg) {

        try {
            Task newEvent = new Event(arg);
            taskList.add(newEvent);
            System.out.println("I have added the task " + newEvent.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
            return;
        } catch (InvalidTaskNameException ex) {
            System.out.println(ex.getMessage());
        } catch (InvalidDateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteTask(String strIndex, ArrayList<Task> taskList) {
        try {
            int index = Integer.parseInt(strIndex);
            if (index < 1 || index > taskList.size()) {
                System.out.println("Task " + index + " does not exist!");
                return;
            }
            index--;
            Task currTask = taskList.get(index);
            taskList.remove(index);
            System.out.println("The task\n  " + currTask.toString() + "\nhas been removed! \nYou now have "
             + taskList.size() + " tasks left.");
            return;
        } catch (NumberFormatException ex) {
            System.out.println("Please provide a valid number");
        }
        
        

    }


    public static void main(String[] args) {
        String name = "Mr Tracker";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        MrTracker.printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        MrTracker.printLine();
        mainLoop: while (true) {
            String input = sc.nextLine();
            MrTracker.printLine();

            PrefixString pref = PrefixString.checkPrefixString(input);
            if (pref != null) {
                switch (pref) {
                    case BYE:
                        sc.close();
                        break mainLoop;
    
                    case LIST:
                        MrTracker.printTaskList(taskList);
                        break;
    
                    case MARK:
                        if (MrTracker.checkValidIndex(input, 5)) {
                            int index = MrTracker.checkIndex(input, 5);
                            // if index is outside of acceptable range,
                            markAndUnmark(taskList, input, index, true);
                        } else {
                            // MrTracker.addTask(taskList, input);
                            System.out.println(input.substring(5) + " is not a valid index");
                        }
    
                        break;
    
                    case UNMARK:
                        if (MrTracker.checkValidIndex(input, 7)) {
                            int index = MrTracker.checkIndex(input, 7);
                            // if index is outside of acceptable range,
                            markAndUnmark(taskList, input, index, false);
                        } else {
                            // MrTracker.addTask(taskList, input);
                            System.out.println(input.substring(7) + " is not a valid index");
                        }
    
                        break;
    
                    case TODO:
                        MrTracker.addTodo(taskList, input.substring(5));
                        break;
    
                    case DEADLINE:
                        MrTracker.addDeadLine(taskList, input.substring(9));
                        break;
    
                    case EVENT:
                        MrTracker.addEvent(taskList, input.substring(6));
                        break;
    
                    case DELETE:
                        MrTracker.deleteTask(input.substring(7), taskList);
                        break;
    
                }
            } else {
                System.out.println(input + " is not a valid command!");
            }

            MrTracker.printLine();
            
        }
        System.out.println("Bye. Hope to see you again soon! \n");
        MrTracker.printLine();
    }
}
