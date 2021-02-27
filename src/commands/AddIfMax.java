package commands;

import collection.City;
import collection.CreationPriorityQueue;
import collection.InputAndOutput;
import collection.UserInput;

public class AddIfMax extends Commands {
    public AddIfMax() {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
    }
    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, CreationPriorityQueue priorityQueue) {
        City city = inputAndOutput.readCity();
        if (city.getArea()>priorityQueue.getPriorityQueue().peek().getArea()) {
            priorityQueue.addToQueue(city);
            inputAndOutput.output("В коллекцию добавлен новый элемент: " + city.toString());
        } else inputAndOutput.output("В коллекцию не добавлен элемент: " + city.toString());
    }
}