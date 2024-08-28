package utility;

public class Ui {
    
    public void welcomeMessage() {
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initialResponse);
    }
    
    public void listTask(TaskList taskList) {
        String echoResponse = "____________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + taskList.listWord() + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    public void showLoadingError() {
        System.out.println("No Tasks Loaded from Memory");
    }
    
    public void byeMessage() {
        String echoResponse = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    public void addTaskMessage(TaskList taskList) {
        String echoResponse = "____________________________________________________________ \n"
                + "Got it. I've added this task: \n"
                + taskList.lastTask().toString()
                + taskList.getLength() + "\n"
                + "____________________________________________________________ \n";
        System.out.println(echoResponse);
    }
    
    public void undoneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n "
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    public void doneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
}
