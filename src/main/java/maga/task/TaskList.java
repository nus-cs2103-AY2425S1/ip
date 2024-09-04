package maga.task;

public class TaskList {
    private final Task[] taskList;
    private int taskCount = 0;

    public TaskList(int size) {
        taskList = new Task[size];
    }

    public void printTasks() {
        System.out.println("Take a look, all the tasks you have here, so many, yuuuuuuge\n");
        for (int i = 0; i < taskCount; i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + taskList[i].printTask());
        }
    }

    public Task getTask(int id) {
        return taskList[id];
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void deleteTask(int taskNumber) {
        Task tempTask = taskList[taskNumber];
        try {
            taskCount--;
            System.out.print("I've deleted this maga.task:\n" + tempTask.getTaskType() + tempTask.getStatusIcon() +
                    tempTask.getDescription() + "\nYou have " + taskCount + " maga.task(s) now!\n");
            for (int i = taskCount; i < taskList.length - 2; i++) {
                taskList[i] = taskList[i + 1];
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid maga.task specified!");
            taskCount++;
        }
    }

    public void markTask(int taskNumber) {
        Task temp = taskList[taskNumber];
        if (temp == null) {
            System.out.println("You're trying to mark a maga.task that DOESN'T EXIST, like bad people on JAN 6. " +
                    "Some of the kindest and most lovely souls I've met");
        } else {
            temp.markAsDone();
            System.out.println(temp.getTaskType() + temp.getStatusIcon() + temp.getDescription());
            System.out.println("Ya boi Donald took the LIBERTY to mark this done:\n");
        }
    }

    public void unmarkTask(int taskNumber) {
        Task temp = taskList[taskNumber];
        if (temp == null) {
            System.out.println("Stop trying to unmark tasks like ILLEGAL ALIENS after" +
                    " I'm president: NOT HERE!");
        } else {
            temp.markAsUndone();
            System.out.println("Here's the maga.task promised but not completed, just like the DEMS\n");
            System.out.println(temp.getStatusIcon() + temp.getDescription());
        }
    }

    public void addTask(String input) {
        Task task = new TodoTask("");
        if(input.startsWith("todo ")) {
            String descrip = input.substring(5).trim();
            task = new TodoTask(descrip);
        } else if(input.startsWith("event ")) {
            String descrip = input.substring(6).trim();
            String[] descripArray = descrip.split("/");
            if (descripArray.length != 2) {
                System.out.println("An event needs a date!! Don't be Crooked Kamala!!");
                return;
            }
            task = new EventTask(descripArray[0], descripArray[1]);
        } else if(input.startsWith("deadline ")) {
            String descrip = input.substring(9).trim();
            String[] descripArray = descrip.split("/");
            if (descripArray.length != 3) {
                System.out.println("A deadline needs a start and end!! Filibusters are a threat to the " +
                        "American people!!");
                return;
            }
            task = new DeadlineTask(descripArray[0], descripArray[1], descripArray[2]);
        }

        try {
            taskList[taskCount] = task;
            taskCount++;
            System.out.println("Another maga.task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskCount + " maga.task(s) now!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Tasklist is full!");
        }
    }

    public void addTask(Task task) {
        try {
            taskList[taskCount] = task;
            taskCount++;
            System.out.println("Another maga.task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskCount + " maga.task(s) now!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Tasklist is full!");
        }
    }
}
