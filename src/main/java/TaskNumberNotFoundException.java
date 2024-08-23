public class TaskNumberNotFoundException extends BottyException {
    public TaskNumberNotFoundException(int inputNumber, int maxNumber) {
        super("I don't see a task with that number! Try a number from 1 to " + maxNumber);
    }
}
