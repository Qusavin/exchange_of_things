package ru.rsreu.exchangeofthings.command;

import ru.rsreu.exchangeofthings.handler.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public abstract class FrontCommand extends Router {
    public void process() throws ServletException, IOException {
    }

    public void send() throws ServletException, IOException {
    }
}
