public class KitaMissingTo extends RuntimeException {
    @Override
    public String toString() {
        return "Your task is missing the '/to <what date>' field :c";
    }
}
