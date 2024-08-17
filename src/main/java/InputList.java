public class InputList {
    private final String[] inputArray;
    public InputList() {
        inputArray = new String[100];
    }
    public void setEntry(String entry, int index) {
        inputArray[index] =  entry;
    }
    public String getEntry(int index) {
        return inputArray[index];
    }
}
