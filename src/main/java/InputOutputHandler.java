public class InputOutputHandler {
    TaskList taskList;

    public InputOutputHandler(String input) {
        taskList = new TaskList(100);
    }

    public boolean parseInput(String input) throws ElysiaException, StringIndexOutOfBoundsException {
        String output = "";
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            output = "Here's your list! \n";
            output += taskList.toString();
        } else if (input.startsWith("mark")) {
            int taskNumber = Integer.parseInt(input.substring(5));
            try {
                taskList.markTask(taskNumber);
                output = "Amazing! You've completed this task! \n";
                output += taskList.printTask(taskNumber);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                output = "Uh oh, this task number does not exist...";
            }
        } else if (input.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(input.substring(7));
            try {
                taskList.unmarkTask(taskNumber);
                output = "Making a pretty girl undo her work is not good for her health! \n";
                output += taskList.printTask(taskNumber);
            } catch (IndexOutOfBoundsException | NullPointerException e){
                output = "Uh oh, this task number does not exist...";
            }
        } else if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                output = "Hmph! You don't expect me to read your mind for this todo, do you?";
            } else {
                Todo newTodo = new Todo(input.substring(5));
                taskList.addTask(newTodo);
                output = "Added the task below to your list~\n" + newTodo.toString();
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
            }
        } else if (input.startsWith("deadline")) {
            if (input.equals("deadline")) {
                output = "Hmph! You don't expect me to read your mind for this deadline, do you?";
            } else {
                int index = input.indexOf("/");
                Deadline newDeadline = new Deadline(input.substring(9,index),
                        input.substring(index + 4));
                taskList.addTask(newDeadline);
                output = "Added the task below to your list~\n" + newDeadline.toString();
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
            }
        } else if (input.startsWith("event")) {
            if (input.equals("event")) {
                output = "Hmph! You don't expect me to read your mind for this event, do you?";
            } else {
                int index0 = input.indexOf("/");
                int index1 = input.lastIndexOf("/");
                Event newEvent = new Event(input.substring(6,index0),
                        input.substring(index0 + 6, index1),
                        input.substring(index1+4));
                taskList.addTask(newEvent);
                output = "Added the task below to your list~\n" + newEvent.toString();
                output += "Wow! You now have " + taskList.size() + " tasks in your list!";
            }
        }   else {
            throw new ElysiaException("unknown command");
        }
        Message.print(output);
        return true;
    }
}
