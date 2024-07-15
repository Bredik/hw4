package org.example;

import java.util.concurrent.Semaphore;

public class PitStop extends Thread {
    private Semaphore semaphore;

    PitWorker[] workers = new PitWorker[4];

    public PitStop() {
        this.semaphore = new Semaphore(1);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new PitWorker(i, this);
            workers[i].start();
        }
    }

    public void pitline(F1Cars f1Cars) {
        try {
            semaphore.acquire();
            System.out.println("Авто " + f1Cars.getName() + " заехало на пит стоп");
            System.out.println("Состояние колес: 1: " + f1Cars.getWheel(0).getStatus() + ", " +
                    "2: " + f1Cars.getWheel(1).getStatus() + ", " +
                    "3: " + f1Cars.getWheel(2).getStatus() + ", " +
                    "4: " + f1Cars.getWheel(3).getStatus());

            System.out.println("Пит 2");
        } catch (Exception e) {

        } finally {
            System.out.println("Пит 3");
            semaphore.release();
            System.out.println("Пит 4");
        }

        // TODO условие: на питстоп может заехать только 1 пилот
        // TODO держим поток до момента смены всех шин
        // TODO каждую шину меняет отдельный PitWorker поток
        // TODO дожидаемся когда все PitWorker завершат свою работу над машиной
        //TODO метод запускается из потока болида, нужна синхронизация с потоком питстопа

        // TODO отпускаем машину
    }


    @Override
    public void run() {
        while(!isInterrupted()){
            //синхронизируем поступающие болиды и работников питстопа при необходимости
        }
    }

    public F1Cars getCar() {
        //TODO Блокируем поток до момента поступления машины на питстоп и возвращаем ее

        return null;
    }
}
