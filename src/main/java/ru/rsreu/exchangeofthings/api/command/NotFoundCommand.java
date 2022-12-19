package ru.rsreu.exchangeofthings.api.command;

import ru.rsreu.exchangeofthings.persistent.enums.Jsp;

import javax.servlet.ServletException;
import java.io.IOException;

public class NotFoundCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.NOT_FOUND);
    }
}
