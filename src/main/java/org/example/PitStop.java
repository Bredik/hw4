package org.example;

import java.util.concurrent.*;

public class PitStop extends Thread {
    private Semaphore semaphore;
    private CyclicBarrier barrier;

    PitWorker[] workers = new PitWorker[4];
    private final BlockingQueue<F1Cars> badWheels = new LinkedBlockingQueue<>();

    public PitStop() {
        this.semaphore = new Semaphore(1);
        this.barrier = new CyclicBarrier(5);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new PitWorker(i, this, barrier);
            workers[i].start();
        }
    }

    public void pitline(F1Cars f1Cars) {
        try {
            semaphore.acquire();
            System.out.println("Авто " + f1Cars.getName() + " заехало на пит стоп " + this.getName() + ". " +
                    "Состояние колес: 1: " + f1Cars.getWheel(0).getStatus() + ", " +
                    "2: " + f1Cars.getWheel(1).getStatus() + ", " +
                    "3: " + f1Cars.getWheel(2).getStatus() + ", " +
                    "4: " + f1Cars.getWheel(3).getStatus());
            badWheels.add(f1Cars);
            barrier.await();
            badWheels.clear();
            System.out.println("Авто " + f1Cars.getName() + " покинуло пит стоп");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }


    @Override
    public void run() {
        while(!isInterrupted()) {
            //синхронизируем поступающие болиды и работников питстопа при необходимости
        }
    }

    public F1Cars getCar() {
        while (badWheels.isEmpty()) {
           return null;
        }
        return badWheels.peek();
    }
}
