package main.java;
import main.java.Task;
import main.java.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class MrTracker {

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
            Task currTask = taskList.get(i);
            String prefix = currTask.isDone ? "[X] " : "[ ] ";
            System.out.println(i+1 + "." + prefix + taskList.get(i).name);
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
                System.out.println("task " + ++index + " is marked!");
            } else {
                curr.unMark();
                System.out.println("task " + ++index + " is unmarked!");
            }
        }
    }

    public static void addTodo (ArrayList<Task> taskList, String arg) {
        ToDo newToDo = new ToDo(arg);
        taskList.add(newToDo);
        System.out.println("I have added the task " + newToDo.toString());
        System.out.println("You now have " + taskList.size() + " tasks");
        return;
    }

    public static void addDeadLine (ArrayList<Task> taskList, String arg) {
        if (arg.contains("/by ")) {
            int index = arg.indexOf("/by ");
            String taskName = arg.substring(0, index);
            String endDate = arg.substring(index+4);
            Task newDeadLine = new DeadLine(taskName, endDate);
            taskList.add(newDeadLine);
            System.out.println("I have added the task " + newDeadLine.toString());
            System.out.println("You now have " + taskList.size() + " tasks");
            return;
        } else {
            //throw error?
        }
    }

    public static void addEvent (ArrayList<Task> taskList, String arg) {
        if (arg.contains("/from ") && arg.contains("/to ")) {
            int fromIndex = arg.indexOf("/from ");
            int toIndex = arg.indexOf("/to ");
            String taskName = arg.substring(0, fromIndex);
            String fromDate = arg.substring(fromIndex + 6, toIndex);
            String toDate = arg.substring(toIndex + 4);
            Task newEvent = new Event(taskName, fromDate, toDate);
            taskList.add(newEvent);
            System.out.println("I have added the task " + newEvent.toString());
            System.out.println("You now have " + taskList.size() + " tasks");
            return;
        } else {
            //throw error?
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
        while (true) {
            String input = sc.nextLine();
            MrTracker.printLine();
            String lowerInput = input.toLowerCase();
            if (lowerInput.equals("bye")) {
                sc.close();
                break;

            } else if (lowerInput.equals("list")) {
                MrTracker.printTaskList(taskList);

            } else if (input.startsWith("mark ")) {
                // if an int is passed
                if (MrTracker.checkValidIndex(input, 5)) {
                    int index = MrTracker.checkIndex(input, 5);
                    // if index is outside of acceptable range,
                    markAndUnmark(taskList, input, index, true);
                } else {
                    // MrTracker.addTask(taskList, input);
                    System.out.println(input.substring(5) + " is not a valid index");
                }

            } else if (input.startsWith("unmark ")) {

                if (MrTracker.checkValidIndex(input, 7)) {
                    int index = MrTracker.checkIndex(input, 7);
                    // if index is outside of acceptable range,
                    markAndUnmark(taskList, input, index, false);
                } else {
                    // MrTracker.addTask(taskList, input);
                    System.out.println(input.substring(7) + " is not a valid index");
                }

            } else if (input.startsWith("todo ")) {
                MrTracker.addTodo(taskList, input.substring(5));
            } else if (input.startsWith("deadline ")) {
                MrTracker.addDeadLine(taskList, input.substring(9));
            } else if (input.startsWith("event ")) {
                MrTracker.addEvent(taskList, input.substring(6));

            } else {
                // MrTracker.addTask(taskList, input);
                System.out.println(input + " is not a valid command!");
            }


            MrTracker.printLine();
        }
        System.out.println("Bye. Hope to see you again soon! \n");
        MrTracker.printLine();
    }
}
