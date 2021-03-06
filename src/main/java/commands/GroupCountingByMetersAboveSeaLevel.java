package commands;

import collection.City;
import IOutils.InputAndOutput;
import collectionUtils.PriorityQueueStorage;

import java.util.PriorityQueue;

/**
 * Класс для команды group_counting_by_meters_above_sea_level, которая группирует элементы коллекции по значению
 * поля metersAboveSeaLevel и выводит количество элементов в каждой группе.
 */

public class GroupCountingByMetersAboveSeaLevel extends Command {
    /**
     * Поле, использующееся для временного хранения коллекции.
     */
    private final PriorityQueue<City> dop = new PriorityQueue<>(10, (c1, c2) -> {
        if (c2.getMetersAboveSeaLevel() != null && c1.getMetersAboveSeaLevel() != null) {
            return c1.getMetersAboveSeaLevel().compareTo(c2.getMetersAboveSeaLevel());
        } else if (c2.getMetersAboveSeaLevel() == null && c1.getMetersAboveSeaLevel() != null) {
            return -1;
        } else if (c2.getMetersAboveSeaLevel() != null && c1.getMetersAboveSeaLevel() == null) {
            return 1;
        } else return 0;
    });

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public GroupCountingByMetersAboveSeaLevel() {
        super("group_counting_by_meters_above_sea_level", "сгруппировать элементы коллекции по значению поля metersAboveSeaLevel, вывести количество элементов в каждой группе");
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param inputAndOutput  объект, через который производится ввод/вывод.
     * @param commandsControl объект, содержащий объекты доступных команд.
     * @param priorityQueue   хранимая коллекция.
     */
    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, PriorityQueueStorage priorityQueue) {
        if (priorityQueue.getCollection().isEmpty()) inputAndOutput.output("Коллекция пуста");
        else {
            while (!priorityQueue.getCollection().isEmpty()) {
                City city = priorityQueue.pollFromQueue();
                dop.add(city);
            }
            City city = dop.poll();
            Long meters = null;
            if (city != null) {
                meters = city.getMetersAboveSeaLevel();
            }
            inputAndOutput.output("Группа " + meters + " (м):");
            if (city != null) {
                inputAndOutput.output(city.toString());
            } else inputAndOutput.output(null);
            priorityQueue.addToCollection(city);
            while (!dop.isEmpty()) {
                city = dop.poll();
                if (meters != null && !meters.equals(city.getMetersAboveSeaLevel()) || meters == null && city.getMetersAboveSeaLevel() != null) {
                    meters = city.getMetersAboveSeaLevel();
                    inputAndOutput.output("Группа " + meters + " (м):");
                }
                inputAndOutput.output(city.toString());
                priorityQueue.addToCollection(city);
            }
        }
    }
}
