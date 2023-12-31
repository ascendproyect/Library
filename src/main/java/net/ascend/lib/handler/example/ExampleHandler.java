package net.ascend.lib.handler.example;

import net.ascend.lib.Messages;
import net.ascend.lib.handler.Handler;
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
