package campus.valence.demineur;

import java.util.HashSet;
import java.util.Set;

public class Demineur {

    public static Demineur create(int bombCount, int boardSize) {
        Case[][] board = new Case[boardSize][boardSize];
        generatedClearCase(boardSize, board);
        generatedBombCase(bombCount, boardSize, board);
        return new Demineur(board);
    }

    private static void generatedClearCase(int boardSize, Case[][] board) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Case.ClearCase(i, j);
            }
        }
    }

    private static void generatedBombCase(int bombCount, int boardSize, Case[][] board) {
        Set<Case.BombCase> bombes = new HashSet<>(); // création d'un ensemble unique de bombes
        while (bombes.size() < bombCount) {            // tant que mon ensemble contient moins que 10 bombes
            int x = (int) (Math.random() * boardSize); // je génère une coordonnée x aléatoire
            int y = (int) (Math.random() * boardSize); // je génère une coordonnée y aléatoire
            Case.BombCase bomb = new Case.BombCase(x, y);
            if (bombes.add(bomb)) {
                board[x][y] = bomb;  // j'ajoute une bombe à mon ensemble, si une même bombes existe déjà elle ne sera pas ajoutée mais remplacée par une nouvelle
            }
        }

    }

    private Case[][] board;

    protected Demineur(Case[][] board) {
        this.board = board;
    }

    String boardAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            sb.append("|");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] instanceof Case.BombCase) {
                    sb.append("X");
                } else {
                    sb.append(countBombsAround(i, j));
                }
            }
            if (i < board.length-1) {
                sb.append("|\n");
            }else{sb.append("|");}
        }
        return sb.toString();
    }

    public Case[][] getBoard() {
        return board;
    }

    public int countBombsAround(int x, int y) {
        int bombCount = 0;
        if (board[x][y] instanceof Case.ClearCase) {
            for (int i = Math.max(0, x - 1); i <= Math.min(board.length - 1, x + 1); i++) {
                for (int j = Math.max(0, y - 1); j <= Math.min(board.length - 1, y + 1); j++) {
                    if (board[i][j] instanceof Case.BombCase) {
                        bombCount++;
                    }
                }
            }
        }
        return bombCount;
    }

    public void setBoard(Case[][] board) {
        this.board = board;
    }
}
