package dev.hely.lib.manager.example;

import dev.hely.lib.Messages;
import dev.hely.lib.handler.Handler;
import org.bukkit.event.Listener;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */
public class ExampleHandler extends Handler implements Listener {


    @Override
    public void onDisable() {
        Messages.sendMessage("GOOD BYE...");
    }
}
