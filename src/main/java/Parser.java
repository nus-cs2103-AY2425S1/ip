public class Parser {
    public static Task createTask(String taskDescription) throws IllegalInputPotongException {
        String[] arr = taskDescription.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            String curr = arr[i];
            arr[i] = curr.strip();
        }
        boolean isDone = arr[1].equals("1");
        Task result = null;
        String description = arr[2];
        String time = "";
        switch (arr[0]) {
            case "T":
                result = new ToDoTask(description, isDone);
                break;
            case "D":
                time = arr[3];
                result = new DeadlineTask(description, time, isDone);
                break;
            case "E":
                time = arr[3];
                String[] startAndEnd = time.split("-");
                result = new EventTask(description, startAndEnd[0], startAndEnd[1], isDone);
                break;
            default:
                result = new Task(description, isDone);
        }
        return result;
    }
}
