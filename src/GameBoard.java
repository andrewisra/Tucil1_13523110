import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private char[][] board;
    private int N, M;

    // Konstruktor
    public GameBoard(int N, int M) {
        this.N = N;
        this.M = M;
        board = new char[N][M];

        // Inisialisasi dengan '.'
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
    }

    public int getN() { return N; }
    public int getM() { return M; }
    public char[][] getBoard() { return board; }

    private static final String RESET = "\u001B[0m"; // Reset warna ANSI

    // Daftar warna tetap untuk huruf A-Z
    private static final Map<Character, String> COLORS = new HashMap<>();
    static {
        COLORS.put('A', "\u001B[31m"); // Merah
        COLORS.put('B', "\u001B[32m"); // Hijau
        COLORS.put('C', "\u001B[33m"); // Kuning
        COLORS.put('D', "\u001B[34m"); // Biru
        COLORS.put('E', "\u001B[35m"); // Ungu
        COLORS.put('F', "\u001B[36m"); // Cyan
        COLORS.put('G', "\u001B[37m"); // Abu-abu Terang
        COLORS.put('H', "\u001B[91m"); // Merah Terang
        COLORS.put('I', "\u001B[92m"); // Hijau Terang
        COLORS.put('J', "\u001B[93m"); // Kuning Terang
        COLORS.put('K', "\u001B[94m"); // Biru Terang
        COLORS.put('L', "\u001B[95m"); // Ungu Terang
        COLORS.put('M', "\u001B[96m"); // Cyan Terang
        COLORS.put('N', "\u001B[97m"); // Putih
        COLORS.put('O', "\u001B[41m"); // Latar Merah
        COLORS.put('P', "\u001B[42m"); // Latar Hijau
        COLORS.put('Q', "\u001B[43m"); // Latar Kuning
        COLORS.put('R', "\u001B[44m"); // Latar Biru
        COLORS.put('S', "\u001B[45m"); // Latar Ungu
        COLORS.put('T', "\u001B[46m"); // Latar Cyan
        COLORS.put('U', "\u001B[47m"); // Latar Abu-abu
        COLORS.put('V', "\u001B[101m"); // Latar Merah Terang
        COLORS.put('W', "\u001B[102m]"); // Latar Hijau Terang
        COLORS.put('X', "\u001B[103m]"); // Latar Kuning Terang
        COLORS.put('Y', "\u001B[104m]"); // Latar Biru Terang
        COLORS.put('Z', "\u001B[105m]"); // Latar Ungu Terang
    }

    // Menampilkan papan permainan dengan warna
    public void printBoardWithColor() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '.') {
                    System.out.print(". "); // Tidak berwarna untuk kosong
                } else {
                    String color = COLORS.getOrDefault(cell, RESET);
                    System.out.print(color + cell + RESET + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Mengecek apakah suatu blok bisa ditempatkan pada posisi tertentu
    public boolean canPlace(Block block, int x, int y) {
        char[][] shape = block.getShape();
        int rows = shape.length;
        int cols = shape[0].length;

        if (x + rows > N || y + cols > M) {
            return false; // Blok keluar dari batas papan
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (shape[i][j] != ' ' && board[x + i][y + j] != '.') {
                    return false; // Posisi sudah terisi
                }
            }
        }
        return true;
    }

    // Menempatkan blok pada papan
    public void placeBlock(Block block, int x, int y) {
        char[][] shape = block.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != ' ') {
                    board[x + i][y + j] = shape[i][j];
                }
            }
        }
    }

    // Menghapus blok dari papan (untuk backtracking)
    public void removeBlock(Block block, int x, int y) {
        char[][] shape = block.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != ' ') {
                    board[x + i][y + j] = '.';
                }
            }
        }
    }

    // Mengecek apakah papan sudah penuh
    public boolean isFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '.') {
                    return false;
                }
            }
        }
        return true;
    }
}
