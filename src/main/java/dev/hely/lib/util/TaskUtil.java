package dev.hely.lib.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ThreadFactory;

/**
 * @Author Joako
 * @Date 5/1/2023 | 11:34
 **/

public class TaskUtil {

    private final JavaPlugin plugin;

    public TaskUtil(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }
    public void scheduleSyncDelayed(Callable callback) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                this.plugin,
                callback::call
        );
    }

    @Deprecated
    public void scheduleAsyncDelayed(Callable callback) {
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(
                this.plugin,
                callback::call
        );
    }

    public void scheduleSyncDelayed(Callable callback, long delay) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                this.plugin,
                callback::call,
                delay
        );
    }

    @Deprecated
    public void scheduleAsyncDelayed(Callable callback, long delay) {
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(
                this.plugin,
                callback::call,
                delay
        );
    }

    public void scheduleSyncRepeating(Callable callback, long start, long delay) {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(
                this.plugin,
                callback::call,
                start,
                delay
        );
    }

    @Deprecated
    public void scheduleAsyncRepeatingTask(Callable callback, long start, long delay) {
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(
                this.plugin,
                callback::call,
                start,
                delay
        );
    }
    public void run(Callable callable) {
        this.plugin.getServer().getScheduler().runTask(this.plugin, callable::call);
    }

    public void runAsync(Callable callable) {
        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, callable::call);
    }

    public void runLater(Callable callable, long delay) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, callable::call, delay);
    }

    public void runAsyncLater(Callable callable, long delay) {
        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(this.plugin, callable::call, delay);
    }

    public void runTimer(Callable callable, long delay, long interval) {
        this.plugin.getServer().getScheduler().runTaskTimer(this.plugin, callable::call, delay, interval);
    }

    public void runAsyncTimer(Callable callable, long delay, long interval) {
        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, callable::call, delay, interval);
    }
    public BukkitTask asyncTimer(Callable callable, long delay, long value) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, callable::call, delay, value);
    }

    public void sync(Callable callable) {
        Bukkit.getScheduler().runTask(this.plugin, callable::call);
    }

    public BukkitTask syncLater(Callable callable, long delay) {
        return Bukkit.getScheduler().runTaskLater(this.plugin, callable::call, delay);
    }

    public BukkitTask syncTimer(Callable callable, long delay, long value) {
        return Bukkit.getScheduler().runTaskTimer(this.plugin, callable::call, delay, value);
    }

    public void async(Callable callable) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, callable::call);
    }

    public BukkitTask asyncLater(Callable callable, long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(this.plugin, callable::call, delay);
    }


    public interface Callable {
        void call();
    }
}
