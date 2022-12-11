package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.enums.Jsp;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminPanelCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.ADMIN_PANEL);
    }
}
