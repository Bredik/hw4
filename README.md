# Практическое задание по разделу "Синхронизаторы из пакета java.util.concurrent"

## Задание
Нужно симулировать гонку формулы 1.


Потребуется корректно синхронизировать следующие типы потоков:

1) Основной поток, который создает и запускает гонку и ожидает завершения для отображения результатов
2) F1Cars (2 у каждой команды) - гоночные болиды, ждут сигнал на старт и двигаются к финишу
3) PitStop (1 у каждой команды) - питстоп, который впускает на питстоп болдиды строго по одному и не отпускает поток болида пока не завершится обслуживание
4) PitWorker (4 у каждого питстопа) - работник питстопа, который меняет колеса только на своей позиции (от 0 до 3). Ждет машину и сигнализирует о завершении


Для реализации можно использовать любые известные вам инструменты из стандартной библиотеки Java. 

Код требуется привести к потокобезопастному виду, менять реализацию можно - но не нарушать логику работы

Параметры длительности, расчета скорости или износа шин можно корректировать если текущие значения неустраивают.


## Как выполнять
Смотри [README по сссылке](https://github.com/multithreading-course-practice/HW-readme/blob/main/README.md)
