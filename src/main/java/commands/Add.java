package commands;

import collection.City;
import collectionUtils.CreationPriorityQueue;
import IOutils.InputAndOutput;
import exceptions.TooMuchElementsException;

import java.util.NoSuchElementException;

/**
 * Класс для команды add, которая добавляет новый элемент в коллекцию.
 */

public class Add extends Commands {
    public Add() {
        super("add", "добавить новый элемент в коллекцию");
    }

    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, CreationPriorityQueue priorityQueue) {
        try {
            City city = inputAndOutput.readCity();
            city.setId(priorityQueue.generateId());
            priorityQueue.addToQueue(city);
            inputAndOutput.output("В коллекцию добавлен новый элемент: " + city.toString());
        } catch (TooMuchElementsException e) {
            inputAndOutput.output("Ошибка: в коллекции слишком много элементов; объект коллекции не создан");
        } catch (NoSuchElementException e) {
            inputAndOutput.output("В скрипте не указаны значения для создания элемента коллекции. Команда add не выполнена");
        }
    }
}
