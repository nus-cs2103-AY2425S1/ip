package System;

import Commands.Deadlines;
import Commands.Events;
import Commands.TaskList;
import Commands.ToDos;

public class Ui {
    public void greet() {
        line();
        System.out.println("Hello! I'm Tanjiro!");
        System.out.println("What can I do for you?");
        line();
        System.out.println("Instructions:");
        System.out.println("todo ___");
        System.out.println("deadline ___ /by ____");
        System.out.println("event ___ /from ___ /to ___");
        System.out.println("Please input the date format in: yyyy-mm-dd hhmm.");
        line();
    }

    public void goodbye() {
        System.out.println("Bye! Hope to see you again!");
        line();
    }

    public void emptyList() {
        System.out.println("There are currently zero tasks in the list!");
    }

    public void doesNotExist() {
        System.out.println("Task does not exist!");
    }
    public void alreadyMarked(){
        System.out.println("Task has already been marked!");
    }

    public void alreadyUnmarked(){
        System.out.println("Task has already been unmarked!");
    }

    public void findTaskMessage(StringBuilder sb) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(sb.toString());
    }
    public void delete_message(TaskList t) {
        System.out.println("Noted. I've removed this task:");
        if (t instanceof ToDos) {
            if (t.getCurrent_status()== TaskList.status.MARKED) {
                System.out.println("[T][X] " + t.getName());
            } else {
                System.out.println("[T][ ] " + t.getName());
            }
        } else if (t instanceof Deadlines) {
            if (t.getCurrent_status()== TaskList.status.MARKED) {
                System.out.println("[D][X] " + t.getName() + "(by: " + t.getDate() + ")");
            } else {
                System.out.println("[D][ ] " + t.getName() + "(by: " + t.getDate() + ")");
            }
        } else if (t instanceof Events) {
            if (t.getCurrent_status()== TaskList.status.MARKED) {
                System.out.println("[E][X] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            } else {
                System.out.println("[E][ ] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            }
        }
        System.out.println("Now you have " + t.get_list_size() + " tasks in the list.");
    }

    public void date() {
        System.out.println("The date provided is invalid!");
    }
    public void list_task_message(String information) {
//        int counter = 1;
//        for (Task t : task_list) {
//            if (t.getCurrent_status()== Task.status.MARKED) {
//                if (t.getTag().equals("T")) {
//                    if (t instanceof Commands.ToDos) {
//                        System.out.println(counter + ".[T][X] " + t.getName());
//                    }
//                } else if (t.getTag().equals("D")) {
//                    if (t instanceof Commands.Deadlines) {
//                        System.out.println(counter + ".[D][X] " + t.getName() + "(by: " + t.getDay() + ")");
//                    }
//                } else if (t.getTag().equals("E")) {
//                    if (t instanceof Commands.Events) {
//                        System.out.println(counter + ".[E][X] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
//                    }
//                } else {
//                    System.out.println(counter + ".[-][X] " + t.getName());
//                }
//            } else {
//                if (t.getTag().equals("T")) {
//                    if (t instanceof Commands.ToDos) {
//                        System.out.println(counter + ".[T][ ] " + t.getName());
//                    }
//                } else if (t.getTag().equals("D")) {
//                    if (t instanceof Commands.Deadlines) {
//                        System.out.println(counter + ".[D][ ] " + t.getName() + "(by: " + t.getDay() + ")");
//                    }
//                } else if (t.getTag().equals("E")) {
//                    if (t instanceof Commands.Events) {
//                        System.out.println(counter + ".[E][ ] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
//                    }
//                } else {
//                    System.out.println(counter + ".[-][ ] " + t.getName());
//                }
//            }
//            counter++;
//        }
        System.out.println(information);
    }

    public void mark_message(String s) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + s);
    }

    public void unmark_message(String s) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[_] " + s);
    }

    private void line() {
        System.out.println("========================================");
    }

    public void empty_todo() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void empty_deadline() {
        System.out.println("OOPS!!! The format of a deadline is wrong.");
    }

    public void empty_event() {
        System.out.println("OOPS!!! The format of a event is wrong.");
    }

    public void invalid_input() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
