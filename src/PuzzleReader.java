import java.io.*;
import java.util.*;

public class PuzzleReader {
    private int N, M, P;
    private String caseType;
    private List<Block> blocks;

    public PuzzleReader(String filename) {
        blocks = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File" + filename + "tidak ditemukan.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] firstLine = br.readLine().split(" ");
            N = Integer.parseInt(firstLine[0]);
            M = Integer.parseInt(firstLine[1]);
            P = Integer.parseInt(firstLine[2]);

            caseType = br.readLine().trim();

            for (int i = 0; i < P; i++) {
                List<String> blockShape = new ArrayList<>();
                String line;

                while ((line = br.readLine()) != null) {
                    // line = line.trim();
                    if (line.isEmpty()) break; // Pastikan berhenti di baris kosong
                    blockShape.add(line);
                }

                if (!blockShape.isEmpty()) {
                    blocks.add(new Block((char) ('A' + i), blockShape.toArray(new String[0])));
                } else {
                    System.err.println("Warning: Blok " + (char) ('A' + i) + " kosong!");
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        }
    }

    public void printInfo() {
        System.out.println("Dimensi papan: " + N + " x " + M);
        System.out.println("Jumlah blok: " + P);
        System.out.println("Tipe kasus: " + caseType);
        System.out.println("Blok-blok puzzle:");
        for (Block b : blocks) {
            System.out.println("Blok " + b.getId() + ":");
            b.printBlock();
        }
    }

    public List<Block> getBlocks() { return blocks; }
    public int getN() { return N; }
    public int getM() { return M; }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file input: ");
        String filename = scanner.nextLine();

        PuzzleReader reader = new PuzzleReader(filename);
        reader.printInfo();
    }
}
