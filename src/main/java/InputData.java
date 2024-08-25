public class InputData {

    private Task[] arr;
    private int index;

    public InputData() {
        this.arr = new Task[100];
        this.index = 0;
    }

    public String add(Task input) {
        this.arr[this.index] = input;
        this.index++;
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                input, this.index);
    }

    public String mark(int index) throws PotongException {
        if (index - 1 >= this.index) {
            throw new PotongException("We cannot mark a task thats not there!");
        }
        return this.arr[index - 1].mark();
    }

    public String unmark(int index) throws PotongException {
        if (index - 1 >= this.index) {
            throw new PotongException("We cannot mark a task thats not there!");
        }
        return this.arr[index - 1].unmark();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            result.append(String.format("%d. %s\n", i + 1, this.arr[i]));
        }
        return result.toString();
    }
}
