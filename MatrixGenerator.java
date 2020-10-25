import java.util.Random;

// генерація матриці шляхів між містами
class MatrixGenerator{
    public static int[][] generate(int len) {
        int[][] matrix = new int[len][len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) { matrix[i][j] = Integer.MAX_VALUE; } else {
                    matrix[i][j] = random.nextInt(150  - 6) + 5; }// за умовою мінімум шлях від 5 до 150 одиниць
            } }
        return matrix;
    }}

