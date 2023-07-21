public class OffByN implements CharacterComparator {
    private int offNum;

    public OffByN(int N) {
        this.offNum = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs(x - y) == offNum);
    }
}
