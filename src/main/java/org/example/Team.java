package org.example;

import java.util.concurrent.CyclicBarrier;

public class Team {
    private final long id;

    private final F1Cars[] cars = new F1Cars[2];

    private final PitStop pitStop = new PitStop();

    public Team(long id, CyclicBarrier startBarrier, CyclicBarrier endBarrier) {
        this.id = id;
        for (int i = 0; i < this.cars.length; i++) {
            this.cars[i] = new F1Cars(id * 10 + i, pitStop, startBarrier, endBarrier);
        }
        pitStop.start();
    }

    public void prepareRace(Race race) {
        for (int i = 0; i < this.cars.length; i++) {
            this.cars[i].prepareRace(race);
        }
    }
}
