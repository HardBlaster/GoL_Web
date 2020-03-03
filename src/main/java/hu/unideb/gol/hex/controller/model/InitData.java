package hu.unideb.gol.hex.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
public class InitData {
    private int width;
    private int height;
    private int aliveRatio;
    private int[] born;
    private int[] stable;
    private int[] dead;

    @Override
    public String toString() {
        return "InitData{" +
                "width=" + width +
                ", height=" + height +
                ", aliveRatio=" + aliveRatio +
                ", born=" + Arrays.toString(born) +
                ", stable=" + Arrays.toString(stable) +
                ", dead=" + Arrays.toString(dead) +
                '}';
    }
}
