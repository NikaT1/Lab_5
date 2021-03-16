package commands;

import collection.City;
import collectionUtils.CreationPriorityQueue;
import IOutils.InputAndOutput;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * Класс для команды save, которая сохраняет в файл коллекцию.
 */

public class CommandSave extends Commands {
    private final PriorityQueue<City> dop = new PriorityQueue<>(10, (c1, c2) -> (c2.getArea() - c1.getArea()));

    public CommandSave() {
        super("save", "сохранить коллекцию в файл");
    }

    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, CreationPriorityQueue priorityQueue) {
        try {
            PrintWriter printWriter = new PrintWriter(priorityQueue.getFilePath());
            printWriter.write("id,name,x,y,creationDate,area,population,metersAboveSeaLevel,establishmentDate,agglomeration,climate,age" + "\n");
            while (!priorityQueue.getPriorityQueue().isEmpty()) {
                City city = priorityQueue.pollFromQueue();
                printWriter.write(city.getId() + ",");
                printWriter.write(city.getName() + ",");
                printWriter.write(city.getCoordinates().getX() + ",");
                printWriter.write(city.getCoordinates().getY() + ",");
                printWriter.write(city.getCreationDate() + ",");
                printWriter.write(city.getArea() + ",");
                printWriter.write(city.getPopulation() + ",");
                if (city.getMetersAboveSeaLevel() == null) printWriter.write(",");
                else printWriter.write(city.getMetersAboveSeaLevel() + ",");
                if (city.getEstablishmentDate() == null) printWriter.write(",");
                else printWriter.write(city.getEstablishmentDate() + ",");
                if (city.getAgglomeration() == null) printWriter.write(",");
                else printWriter.write(city.getAgglomeration() + ",");
                printWriter.write(city.getClimate() + ",");
                if (city.getGovernor().getAge() == null) printWriter.write(",");
                else printWriter.write(city.getGovernor().getAge() + "");
                printWriter.write("\n");
                dop.add(city);
            }
            printWriter.flush();
            while (!dop.isEmpty()) {
                priorityQueue.addToQueue(dop.poll());
            }
            inputAndOutput.output("Коллекция успешно сохранена");
        } catch (FileNotFoundException e) {
            inputAndOutput.output("Не хватает прав на запись в файл");
        }
    }
}
