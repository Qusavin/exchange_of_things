package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.enums.Route;

import javax.servlet.ServletException;
import java.io.IOException;

public class ModeratorPanelCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Route.MODERATOR_PANEL.getRoute());
    }
}
