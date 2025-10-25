package aoc;


public class App {
    static String RESOURCES_PATH = "resources/";
    public static void main(String[] args) throws Exception {
        

        CSVReader reader = new CSVReader();

        TwoLists<Integer> data = reader.readCSV(RESOURCES_PATH + "puzzle1_input.csv", "   ");

        //Historian_Hysteria.solveA(data);
        //Historian_Hysteria.solveB(data);

        TwoDimensionalList<Integer> matrix = reader.readCSVAsMatix(RESOURCES_PATH + "puzzle2_input.csv", " ");

        //RedNosedReports.solveA(matrix);
        RedNosedReports.solveB(matrix);
        


    }
}
