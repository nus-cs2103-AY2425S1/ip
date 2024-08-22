public class List {
    private static final String[] LIST = new String[100];
    private static int numItems = 0;

    public void addItem(String item) {
        LIST[numItems] = item;
        numItems++;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numItems; i++) {
            output.append(String.valueOf(i + 1)).append(". ").append(LIST[i]).append("\n");
        }
        return output.toString();
    }
}
