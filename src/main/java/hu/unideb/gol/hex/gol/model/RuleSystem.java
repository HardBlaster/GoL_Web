package hu.unideb.gol.hex.gol.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleSystem {

    private int[] born;
    private int[] stable;
    private int[] dead;

    public RuleSystem() {
        this.born = new int[]{3};
        this.stable = new int[]{2};
        this.dead = new int[]{0, 1, 4, 5, 6, 7, 8};
    }

    public RuleSystem(int[] born, int[] stable, int[] dead) {
        this.born = born;
        this.stable = stable;
        this.dead = dead;
    }

}