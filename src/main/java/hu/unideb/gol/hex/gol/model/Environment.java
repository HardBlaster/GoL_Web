package hu.unideb.gol.hex.gol.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.ArrayUtils;

import java.util.Random;

@NoArgsConstructor
@Getter
@Setter
public class Environment {

    private int width;
    private int height;
    private Cell[][] matrix;
    private RuleSystem ruleSystem;

    public Environment(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Cell[height][width * 2];
    }

    public Environment(int width, int height, int AR) {
        this(width, height);
        this.populate(AR);
    }

    public void populate(int AR) {
        Random rand = new Random();

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width * 2; j++) {
                matrix[i][j] = new Cell();

                if (!((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))) {
                    if (rand.nextInt(100) <= AR) {
                        matrix[i][j].live();
                    } else {
                        matrix[i][j].kill();
                    }
                }

            }
        }
    }

    public int[][] nextGen() {
        int[][] neighbors = getNeighbours();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j].isValid()) {

                    if (ArrayUtils.contains(this.ruleSystem.getBorn(), neighbors[i][j])) {
                        matrix[i][j].live();
                    } else if (ArrayUtils.contains(this.ruleSystem.getDead(), neighbors[i][j])) {
                        matrix[i][j].kill();
                    }

                }
            }
        }

        return neighbors;
    }

    private int[][] getNeighbours() {
        int[][] neighbors = new int[this.height][this.width*2];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {

                if(matrix[i][j].isValid()) {
                    neighbors[i][j] = livingNeighbors(i, j);
                } else {
                    neighbors[i][j] = Cell.INVALID;
                }

            }
        }

        return neighbors;
    }

    private int livingNeighbors(int row, int col) {
        int neighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -3; j <= 3; j++) {

                if (row + i >= 0 && row + i < this.height
                        && col + j >= 0 && col + j < this.width*2
                        && !(i == 0 && j == 0)
                        && this.matrix[row + i][col + j].isAlive()) {
                    neighbors++;
                }

            }
        }

        neighbors += row + 2 < this.height ? this.matrix[row + 2][col].getStatus() : 0;
        neighbors += row - 2 >= 0 ? this.matrix[row - 2][col].getStatus() : 0;

        return neighbors;
    }
}