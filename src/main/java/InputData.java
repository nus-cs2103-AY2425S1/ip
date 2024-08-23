public class InputData {

    private String[] arr;
    private int index;

    public InputData() {
        this.arr = new String[100];
        this.index = 0;
    }

    public String add(String input) {
        this.arr[this.index] = input;
        this.index++;
        return String.format("added: %s", input);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(String.format("%d. %s\n", i + 1, this.arr[i]));
        }
        return result.toString();
    }
}
