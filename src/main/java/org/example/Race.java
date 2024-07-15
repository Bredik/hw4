package org.example;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

@Log4j2
public class Race {
    @Getter
    private long distance;

    private List<F1Cars> participantCars = new java.util.ArrayList<>();

    private List<Team> teams = new java.util.ArrayList<>();

    public Race(long distance, Team[] participantCars) {
        this.distance = distance;
        teams.addAll(List.of(participantCars));
    }

    /**
     * Запускаем гонку
     */
    public void start(CyclicBarrier startBarrier, CyclicBarrier endBarrier) {
        try {
            for (Team team : teams) {
                team.prepareRace(this);
            }
            startBarrier.await();
            System.out.println("GO!!!");
            //TODO даем команду на старт гонки
            endBarrier.await();
            System.out.println("FINISH!!!");
            //TODO блокируем поток до завершения гонки
        } catch (Exception e) {

        }
    }


    //Регистрируем участников гонки
    public void register(F1Cars participantCar) {
        participantCars.add(participantCar);
    }


    public void start(F1Cars f1Cars) {
        //System.out.println("фиксация времени старта");
        //фиксация времени старта
    }

    public long finish(F1Cars participant) {
        //System.out.println("фиксация времени финиша");
        //фиксация времени финиша
        return 0; //длительность гонки у данного участника
    }

    public void printResults() {
        participantCars.sort(F1Cars::compareTo);
        log.info("Результат гонки:");
        int position = 0;
        for (F1Cars participant : participantCars) {
            log.info("Позиция: {} время: {}", position++, participant.getName());
        }
    }
}
