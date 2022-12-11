package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.enums.Jsp;

import javax.servlet.ServletException;
import java.io.IOException;

public class UserPanelThingCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.USER_PANEL_THING);
    }
}
