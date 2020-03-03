package hu.unideb.gol.hex.gol.simulator;

import hu.unideb.gol.hex.gol.model.Environment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Simulator {
    int generation = 0;
    Environment environment;

    public Simulator(Environment environment) {
        this.environment = environment;
    }

    public void run(int maxGen) {
        for(int i = this.generation; i < maxGen; i++) {

            this.environment.nextGen();
            this.generation++;

        }
    }
}
