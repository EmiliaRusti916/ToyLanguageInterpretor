package view.textInterface.commands;

import controller.Controller;
import exceptions.MyException;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private final Controller controller;
    private String description;
    private String id;

    public RunExampleCommand(String id, String description, Controller controller) {
        super(id, description);
        this.controller = controller;
        this.id = id;
        this.description = description;
    }

    @Override
    public void execute() {
        try {
            this.controller.allSteps();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Controller getController() {
        return controller;
    }
    @Override
    public String toString() {
        return "{Command " + id + '\'' +
                ", " + description + '\'' +
                '}';
    }
}
