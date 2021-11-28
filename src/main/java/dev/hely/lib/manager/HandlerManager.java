package dev.hely.lib.manager;

import dev.hely.lib.example.ExampleJavaPlugin;
import dev.hely.lib.handler.Handler;
import dev.hely.lib.manager.example.ExampleHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

public enum HandlerManager implements Manager {

    INSTANCE;

    private List<Handler> handlersStorage;

    @Override
    public void onEnable(JavaPlugin plugin) {
        this.handlersStorage = new ArrayList<>();

        this.handlersStorage.add(new ExampleHandler());

        this.handlersStorage.stream().filter(handler -> handler instanceof Listener).forEach(handler -> Bukkit.getPluginManager().registerEvents((Listener) handler, ExampleJavaPlugin.INSTANCE));
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getDescription().getName() +"] Loading " + this.handlersStorage.size() + " handlers.");
    }

    @Override
    public void onDisable(JavaPlugin plugin) {
        try {
            this.handlersStorage.forEach(Handler::onDisable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.handlersStorage.clear();
    }
}
