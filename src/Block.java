import java.util.Arrays;

public class Block {
    private char[][] shape;  // Matriks 2D yang merepresentasikan blok
    private char id;

    // Konstruktor
    public Block(char id, String[] shapeStrings) {
        this.id = id;
        int rows = shapeStrings.length;
        int cols = shapeStrings[0].length();
        this.shape = new char[rows][cols];

        // Inisialisasi matriks shape dari string input
        for (int i = 0; i < rows; i++) {
            this.shape[i] = shapeStrings[i].toCharArray();
        }
    }

    // Metode untuk merotasi blok 90 derajat searah jarum jam
    public void rotate90() {
        int rows = shape.length;
        
        // Menentukan panjang maksimum dari semua baris
        int maxCols = 0;
        for (char[] row : shape) {
            maxCols = Math.max(maxCols, row.length);
        }

        // Membuat array baru dengan ukuran yang sesuai setelah rotasi
        char[][] rotated = new char[maxCols][rows];

        // Mengisi array baru dengan spasi sebagai padding
        for (int i = 0; i < maxCols; i++) {
            Arrays.fill(rotated[i], ' ');
        }

        // Melakukan rotasi dengan memindahkan elemen satu per satu
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }

        shape = rotated; // Perbarui shape dengan hasil rotasi
    }


    // Metode untuk mencgflip blok secara horizontal (atau mencerminkan dengan sumbu vertikal)
    public void flipHorizontal() {
        int rows = shape.length;
        
        // Menentukan panjang maksimum baris
        int maxCols = 0;
        for (char[] row : shape) {
            maxCols = Math.max(maxCols, row.length);
        }
        
        // Membuat array baru dengan padding spasi untuk menyamakan panjang baris
        char[][] flipped = new char[rows][maxCols];
        
        for (int i = 0; i < rows; i++) {
            // (note: Isi baris baru dengan spasi untuk menghindari masalah ukuran yang tidak seragam)
            Arrays.fill(flipped[i], ' ');
            
            // Balik setiap baris secara horizontal
            for (int j = 0; j < shape[i].length; j++) {
                flipped[i][maxCols - 1 - j] = shape[i][j];
            }
        }
    
        shape = flipped; // Perbarui shape dengan hasil yang sudah dicerminkan
    }

    // Menampilkan bentuk blok saat ini
    public void printBlock() {
        for (char[] row : shape) {
            System.out.println(new String(row));
        }
        System.out.println();
    }

    // Getter untuk mendapatkan representasi matriks blok
    public char[][] getShape() {
        return shape;
    }

    // Getter untuk mendapatkan ID blok
    public char getId() {
        return id;
    }


    // DRIVER UJI COBA MODUL
    
    // public static void main(String[] args) {
    //     // Contoh blok berbentuk 'T'
    //     String[] tShape = {
    //         "ABC",
    //         " D ",
    //         "XYXPUUUUUUU",
    //         "        Z"
    //     };
    //     Block blockT = new Block('T', tShape);

    //     System.out.println("Blok Awal:");
    //     blockT.printBlock();

    //     System.out.println("Cerminan horizontal:");
    //     blockT.flipHorizontal();
    //     blockT.printBlock();

    //     System.out.println("Rotasi 90 derajat:");
    //     blockT.rotate90();
    //     blockT.printBlock();

    //     System.out.println("Cerminan Horizontal:");
    //     blockT.flipHorizontal();
    //     blockT.printBlock();
    // }
}
