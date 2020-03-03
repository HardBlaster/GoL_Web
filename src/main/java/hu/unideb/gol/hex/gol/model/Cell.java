package hu.unideb.gol.hex.gol.model;

import lombok.Getter;

@Getter
public class Cell {

    public static final int INVALID = -1;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    private int status;

    public Cell() {
        this.status = INVALID;
    }

    public Cell(int status) {
        this.status = status;
    }

    public Cell(boolean valid, boolean alive) {
        if(!valid) {
            this.status = INVALID;
        } else if (alive) {
            this.status = ALIVE;
        } else {
            this.status = DEAD;
        }
    }

    public void kill() {
        this.status = DEAD;
    }

    public void live() {
        this.status = ALIVE;
    }

    public void invalidate() {
        this.status = INVALID;
    }

    public boolean isAlive() {
        return status == ALIVE;
    }

    public boolean isDead() {
        return status == DEAD;
    }

    public boolean isValid() {
        return status != INVALID;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}