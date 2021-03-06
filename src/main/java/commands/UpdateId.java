package commands;

import collection.City;
import IOutils.InputAndOutput;
import collectionUtils.PriorityQueueStorage;

import java.util.PriorityQueue;

/**
 * Класс для команды update, которая обновляет значение элемента коллекции по его id.
 */

public class UpdateId extends Command {
    /**
     * Поле, использующееся для временного хранения коллекции.
     */
    private final PriorityQueue<City> dop = new PriorityQueue<>(10, (c1, c2) -> (c2.getArea() - c1.getArea()));

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public UpdateId() {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param inputAndOutput  объект, через который производится ввод/вывод.
     * @param commandsControl объект, содержащий объекты доступных команд.
     * @param priorityQueue   хранимая коллекция.
     */
    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, PriorityQueueStorage priorityQueue) {
        try {
            int id = Integer.parseInt(inputAndOutput.getArgument());
            boolean flag = false;
            while (!priorityQueue.getCollection().isEmpty()) {
                City city = priorityQueue.pollFromQueue();
                if (city.getId() == id) {
                    city = inputAndOutput.readCity();
                    flag = true;
                }
                dop.add(city);
            }
            if (flag) inputAndOutput.output("обновление элемента успешно завершено");
            else inputAndOutput.output("Элемент с id " + id + " не существует");
            while (!dop.isEmpty()) {
                City city = dop.poll();
                priorityQueue.addToCollection(city);
            }
        } catch (NumberFormatException e) {
            inputAndOutput.output("неправильный формат id");
        }
    }
}
