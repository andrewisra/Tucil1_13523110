import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("      SELAMAT DATANG DI IQ PUZZLE PRO  ");
        System.out.println("=======================================");
        
        System.out.print("Masukkan nama file input(test/nama_file.txt): ");

        String filename = scanner.nextLine();

        PuzzleReader reader = new PuzzleReader(filename);
        List<Block> blocks = reader.getBlocks();
        GameBoard board = new GameBoard(reader.getN(), reader.getM());
    
        Solver solver = new Solver(board, blocks);
        solver.solvePuzzle();
        System.out.print("Apakah anda ingin menyimpan solusi? (ya/tidak) ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("ya")) {
            System.out.print("Masukkan nama file output (contoh: solusi.txt): ");
            String outputFilename = scanner.nextLine().trim();
            solver.saveSolution(outputFilename);
        } else {
            System.out.println("Solusi tidak disimpan.");
        }

        System.out.println();
        System.out.println("SELAMAT, IQ KAMU SEMAKIN NAIK!");
        scanner.close();
    }
}
