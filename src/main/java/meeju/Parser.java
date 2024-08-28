package meeju;

public class Parser {

    public int parse(TaskList taskList, String instruction) {
        if (instruction.equals("bye")) {
            return -1;
        } else if (instruction.equals("list")) {
            taskList.printList();
        } else if (instruction.startsWith("mark ")) {
            try {
                taskList.markTask(instruction.substring(5));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("unmark ")) {
            try {
                taskList.unmarkTask(instruction.substring(7));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("delete ")) {
            try {
                taskList.deleteTask(instruction.substring(7));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else if (instruction.startsWith("todo ")) {
            try {
                taskList.addTodoTask(instruction.substring(5));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        }  else if (instruction.startsWith("deadline ")) {
            try {
                taskList.addDeadlineTask(instruction.substring(9));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        }  else if (instruction.startsWith("event ")) {
            try {
                taskList.addEventTask(instruction.substring(6));
            } catch (MeejuException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("I'm sorry, I did not understand that =^..^=");
            return 5;
        }
        return 0;
    }
}
