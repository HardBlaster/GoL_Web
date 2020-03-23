package hu.unideb.gol.hex.gol.model;

public class Utils {

    public static int[][] simplifyEnvironment(Environment environment) {
        int[][] simpleEnvironment = new int[environment.getMatrix().length][environment.getMatrix()[0].length/2];

        int r = 0;
        for(Cell[] row : environment.getMatrix()) {

            int c = 0;
            for(Cell cell : row) {
                if(cell.isValid()) {

                    simpleEnvironment[r][c] = cell.getStatus();
                    c++;

                }
            }
            r++;

        }

        return simpleEnvironment;
    }
}
