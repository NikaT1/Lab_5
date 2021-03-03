package commands;

import collection.CreationPriorityQueue;
import IOutils.InputAndOutput;

public class Help extends Commands {
    public Help () {
        super ("help", "вывести справку по доступным командам");
    }
    public void doCommand(InputAndOutput inputAndOutput, CommandsControl commandsControl, CreationPriorityQueue priorityQueue) {
        inputAndOutput.output("Доступные команды:");
        for (Commands command : commandsControl.getCommands().values()) {
            inputAndOutput.output(command.toString());
        }
    }
}