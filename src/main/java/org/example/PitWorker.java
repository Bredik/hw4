package org.example;

import java.util.concurrent.CyclicBarrier;

/**
 * Работник питстопа, меняет шину на прибывшей машине на своем месте
 */
public class PitWorker extends Thread {
    //Место работника, он же номер колеса от 0 до 3
    private final int position;

    //Ссылка на сущность питстопа для связи
    private final PitStop pitStop;
    private final CyclicBarrier barrier;

    public PitWorker(int position, PitStop pitStop, CyclicBarrier barrier) {
        this.position = position;
        this.pitStop = pitStop;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (!isInterrupted()) {
                try {
                    F1Cars car = pitStop.getCar();
                    if (car != null && checkWheels(car, position)) {
                        System.out.println("Рабочий " + this.getName() +
                                " принял авто " + car.getName() + " и меняет колесо " + position);
                        car.getWheel(position).replaceWheel(position, car.getName());

                        barrier.await();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    boolean checkWheels(F1Cars car, int position) {
        if (car.getWheel(position).getStatus() < 100) {
            return true; // true если надо менять
        }
        return false;
    }
}
