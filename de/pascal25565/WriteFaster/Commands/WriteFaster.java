package de.pascal25565.WriteFaster.Commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.pascal25565.WriteFaster.Main;
import de.pascal25565.WriteFaster.Listeners.ChatListener;
import de.pascal25565.WriteFaster.Utils.RandomGen;

public class WriteFaster implements CommandExecutor {

	public static boolean started = false;
	public static int keinerda = 0;
	public static boolean gewinnerset = false;
	public static Player gewinner;
	private String PERMISSION = "writefaster.admin";
	private static Random r = new Random();
	public static String randomstring;
	public static int gewinn;
	public static ItemStack diamond;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			if (p.hasPermission(PERMISSION) || (p.getName().equalsIgnoreCase("pascal25565"))) {
				if (started == false) {
					gewinnerset = false;
					started = true;
					try {
						randomstring = RandomGen.getRandomText(12, RandomGen.Change.mitzahlen);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Fail");
					}
					gewinn = r.nextInt(64) + 3;
					diamond = new ItemStack(Material.DIAMOND, gewinn);
					ItemMeta diameta = diamond.getItemMeta();
					diameta.setDisplayName("§bDiamant");
					diamond.setItemMeta(diameta);
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.sendMessage("§c[§e§lWriteFaster§c] §3Das WriteFaster Event startet in kürze!");
						Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.getMain(), new Runnable() {

							@Override
							public void run() {
								all.sendMessage("§7§l--------------------");
								all.sendMessage("§aMan kann §e" + gewinn + " Diamanten §agewinnen.");
								all.sendMessage("§aGebe schnell diese Kombination ein:");
								all.sendMessage("     §e->§4 " + randomstring.toString() + " §e<-");
								all.sendMessage(" ");
								all.sendMessage("§7§l--------------------");
							}
						}, 20*15);
					}
					Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getMain(), new Runnable() {

						@Override
						public void run() {
							if (started == true) {
								if (keinerda != 5) {
									keinerda++;
									for (Player all : Bukkit.getOnlinePlayers()) {
										if (started != false) {
											if (keinerda != 1) {
												all.sendMessage(
														"§c[§e§lWriteFaster§c] §3Es hat noch niemand gewonnen! Kombination:");
												all.sendMessage(
														"§c[§e§lWriteFaster§c] §3Write: §e" + randomstring.toString());
											}
										}
									}
								} else {
									for (Player all : Bukkit.getOnlinePlayers()) {
										all.sendMessage(
												"§c[§e§lWriteFaster§c] §cDas Event wurde abgebrochen weil es niemand geschafft hat die Kombination in den Chat zu schreiben.");
									}
									WriteFaster.started = false;
									WriteFaster.randomstring = null;
									RandomGen.buffer.setLength(0);
								}
							}
						}
					}, 20 * 15, 20 * 15);
				} else {
					p.sendMessage("§c[§e§lWriteFaster§c] §cDas Spiel hat bereits gestartet.");
				}
			} else {
				p.sendMessage("§c[§e§lWriteFaster§c] §cDu hast daf§r keine Rechte!");
			}
		}
		return true;
	}

}