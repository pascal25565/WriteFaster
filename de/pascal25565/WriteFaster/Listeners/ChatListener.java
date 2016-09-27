package de.pascal25565.WriteFaster.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.pascal25565.WriteFaster.Commands.WriteFaster;
import de.pascal25565.WriteFaster.Utils.RandomGen;

public class ChatListener implements Listener {

	String message;
	String randoms;
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent e) throws Exception{
		try{
		randoms = WriteFaster.randomstring.toString();
		message = e.getMessage();
		}catch(Exception e3){
			
		}
		if (WriteFaster.started == true) {
			if (message.equals(randoms)) {
				e.setCancelled(true);
				WriteFaster.gewinnerset = true;
				WriteFaster.gewinner = e.getPlayer();
				WriteFaster.started = false;
				try {
					WriteFaster.gewinner.sendMessage("§c[§e§lWriteFaster§c] §3Du hast das Event gewonnen und hast §c"
							+ WriteFaster.gewinn + " Diamanten §3gewonnen!");
					WriteFaster.gewinner.getInventory().addItem(WriteFaster.diamond);
				} catch (Exception e1) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (all.hasPermission("*") || (all.getName().equalsIgnoreCase("pascal25565")) || (all.isOp())) {
							all.sendMessage(
									"§c[§e§lWriteFaster§c] §cDer Gewinner konnte sein Gewinn nicht richtig bekommen!");
						}
					}
				}
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (all != WriteFaster.gewinner) {
						all.sendMessage("§c[§e§lWriteFaster§c] §3§l" + WriteFaster.gewinner.getName()
								+ "§a hat gewonnen und bekam §e" + WriteFaster.gewinn + " Diamanten§a!");
					}
				}
				WriteFaster.started = false;
				WriteFaster.randomstring = null;
				RandomGen.buffer.setLength(0);
				randoms = "x";
			}
		}
	}
}
