package org.example;

/**
 * Работник питстопа, меняет шину на прибывшей машине на своем месте
 */
public class PitWorker extends Thread {

    //Место работника, он же номер колеса от 0 до 3
    private final int position;

    //Ссылка на сущность питстопа для связи
    private final PitStop pitStop;

    public PitWorker(int position, PitStop pitStop) {
        this.position = position;
        this.pitStop = pitStop;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            F1Cars car = pitStop.getCar();
            //TODO работник ждет машину на питстопе и меняет шину на своей позиции
            car.getWheel(position).replaceWheel();
            //TODO работник сообщает о готовности
        }
    }
}
