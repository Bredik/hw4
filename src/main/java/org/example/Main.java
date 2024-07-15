package org.example;

import java.util.concurrent.CyclicBarrier;

public class Main {
    private static int count = 2;
    public static void main(String[] args) {
        System.out.println("ПОДГОТОВКА");
        CyclicBarrier startBarrier = new CyclicBarrier(count*2 + 1, () -> {
            System.out.println("Все авто готовы!");
        });
        CyclicBarrier endBarrier = new CyclicBarrier(count*2 + 1, () -> {
            System.out.println("Все авто прибыли!");
        });

        Team teams[] = new Team[count];

        for (int i = 0; i < teams.length; i++) {
            teams[i] = new Team(i + 1, startBarrier, endBarrier);
        }

        Race race = new Race(1500, teams);

        race.start(startBarrier, endBarrier);
        race.printResults();
    }
}
