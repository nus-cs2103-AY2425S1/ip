public class KitaOutofBounds extends RuntimeException {
    @Override
    public String toString() {
        return "The task ID you selected is out of bounds/does not exist :c";
    }
}
