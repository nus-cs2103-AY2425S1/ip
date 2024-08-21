public class Item {

    private String name;

    public Item(String newname) {
        this.name = newname;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
