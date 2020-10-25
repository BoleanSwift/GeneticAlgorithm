import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //генерація матриці шляхів між містами(асиметрична мережа)
        int[][] matrix = MatrixGenerator.generate(300);
        int NumberOfMinimumPath = 100;
        //генерація шляху/осіб(хромосом) для алгоритма
        GeneticWorld GeneticAlgorithm = new GeneticWorld(matrix, new Selection<>(), new OrderCrossing(), new SingleMutation(), new OnePointLocal(1 / 100000), NumberOfMinimumPath);
        for (int i = 0; i < 500000; i++) {
            if (i % 10000 == 0){
                System.out.println("Довжина найкоротшого маршрута: " + GeneticAlgorithm.getLength());
                System.out.println("Пройдений шлях: " + Arrays.toString(GeneticAlgorithm.getPath()));
                System.out.println();}
            //відбір кращих особин для отримання кращого спадкоємця
            GeneticAlgorithm.iterate();
        }
    }
}

