import java.util.List;
import java.io.*;

public class Solver {
    private GameBoard board;
    private List<Block> blocks;
    private long startTime;
    private int iterationCount = 0;

    public Solver(GameBoard board, List<Block> blocks) {
        this.board = board;
        this.blocks = blocks;
    }

    public boolean solve(int index) {
        if (index == blocks.size()) {
            if (board.isFull()) {
                System.out.println("Solusi ditemukan:");
                board.printBoardWithColor();
                return true;
            }
            return false;
        }

        Block block = blocks.get(index);

        for (int x = 0; x < board.getN(); x++) {
            for (int y = 0; y < board.getM(); y++) {
                for (int r = 0; r < 4; r++) { // Coba 4 rotasi
                    if (board.canPlace(block, x, y)) {
                        board.placeBlock(block, x, y);
                        iterationCount++;

                        if (solve(index + 1)) {
                            return true;
                        }
                        board.removeBlock(block, x, y); // Backtracking
                        block.rotate90();
                    }
                }
                block.flipHorizontal(); // Coba dengan cerminan

                for (int r = 0; r < 4; r++) { // Coba 4 rotasi
                    if (board.canPlace(block, x, y)) {
                        board.placeBlock(block, x, y);
                        iterationCount++;

                        if (solve(index + 1)) {
                            return true;
                        }
                        board.removeBlock(block, x, y); // Backtracking
                        block.rotate90();
                    }
                }
                
            }
        }
        return false;
    }

    public void solvePuzzle() {
        startTime = System.currentTimeMillis();
        if (!solve(0)) {
            System.out.println("Tidak ada solusi ditemukan.");
        }
        long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("Waktu pencarian: " + executionTime + " ms");
        System.out.println();
        System.out.println("Banyak kasus yang ditinjau: " + iterationCount);
        System.out.println();
    }

    public void saveSolution(String filename) {
        try {
            // Buat folder test jika belum ada
            File directory = new File("test");
            if (!directory.exists()) {
                directory.mkdir(); // Buat folder test
            }

                // Simpan file di dalam folder test/
            File outputFile = new File("test/" + filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

                // Simpan konfigurasi solusi (papan permainan)
            for (char[] row : board.getBoard()) {
                writer.write(new String(row));
                writer.newLine();
            }

            // Simpan metadata waktu eksekusi dan iterasi
            writer.write("Waktu pencarian: " + executionTime + " ms");
            writer.newLine();
            writer.write("Banyak kasus yang ditinjau: " + iterationCount);
            writer.newLine();

            writer.close();
            System.out.println("Solusi berhasil disimpan di: test/" + filename);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan solusi: " + e.getMessage());
        }
    }   


    private long executionTime;

}
