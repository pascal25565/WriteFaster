package de.pascal25565.WriteFaster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.pascal25565.WriteFaster.Commands.WriteFaster;
import de.pascal25565.WriteFaster.Listeners.ChatListener;

public class Main extends JavaPlugin {

	public static Main instance;
	public boolean debug = false;
	private String hudbgsahu = "http://pascal25565.de/Epicmine/allow.html";

	public static Main getMain() {
		return instance;
	}

	public void onEnable() {
		if (debug == false) {
			URL url;
			BufferedReader br;
			String sitetext = null;
			try {
				url = new URL(hudbgsahu);
				br = new BufferedReader(new InputStreamReader(url.openStream()));
				sitetext = br.readLine();
			} catch (MalformedURLException e) {
				Bukkit.getServer().shutdown();
			} catch (IOException e) {
				Bukkit.getServer().shutdown();
			}
			if (sitetext.equalsIgnoreCase("allowed")) {
				instance = this;
				registerEvent(new ChatListener(), this);
				registerCommand("startwritefaster", new WriteFaster());
			} else if (sitetext.equalsIgnoreCase("")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getServer().shutdown();
			} else if (sitetext.equalsIgnoreCase("deny")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getServer().shutdown();
			} else if (sitetext.equalsIgnoreCase(" ")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getServer().shutdown();
			} else if (sitetext.equalsIgnoreCase("no")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getServer().shutdown();
			} else if (sitetext.equalsIgnoreCase("nein")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getServer().shutdown();
			}
		}else{
			instance = this;
			registerEvent(new ChatListener(), this);
			registerCommand("startwritefaster", new WriteFaster());
		}
	}

	public void onDisable() {

	}

	public void registerEvent(Listener Listener, Plugin Plugin) {
		Bukkit.getServer().getPluginManager().registerEvents(Listener, Plugin);
	}

	public void registerCommand(String Command, CommandExecutor cmd) {
		this.getCommand(Command).setExecutor(cmd);
	}
}
