package campus.valence.demineur;

import java.util.Objects;

public class Case {

    static class BombCase extends Case {

        public BombCase(int x, int y) {
            super(x, y);
        }
    }
    static class ClearCase extends Case {

        public ClearCase(int x, int y) {
            super(x, y);
        }
    }

    private int x;

    private int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
        @Override
    public String toString() {
        return "Case{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return x == aCase.x && y == aCase.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
