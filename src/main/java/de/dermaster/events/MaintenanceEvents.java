/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FileHelper;
/*    */ import java.io.File;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.ChatEvent;
/*    */ import net.md_5.bungee.api.event.ServerConnectEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.api.plugin.Plugin;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class MaintenanceEvents
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onServerConnect(ServerConnectEvent e) {
/* 22 */     File config = new File(Proxy.getInstance().getDataFolder().getPath(), "config.yml");
/* 23 */     final ProxiedPlayer p = e.getPlayer();
/* 24 */     if (Boolean.valueOf(FileHelper.getString(config, "Maintenance")).booleanValue() && 
/* 25 */       !p.hasPermission(Config.getSetting("StaffPerm"))) {
/* 26 */       ProxyServer.getInstance().getScheduler().schedule((Plugin)Proxy.getInstance(), new Runnable()
/*    */           {
/*    */             public void run() {
/* 29 */               p.disconnect(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("MaintenanceKickMessage")));
/*    */             }
/* 31 */           },  20L, TimeUnit.MILLISECONDS);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onChat(ChatEvent e) {
/* 38 */     String[] args = e.getMessage().split(" ");
/* 39 */     ProxiedPlayer p = (ProxiedPlayer)e.getSender();
/* 40 */     if (args.length == 6 && args[0].equalsIgnoreCase("/cloud") && args[1].equalsIgnoreCase("sp") && 
/* 41 */       args[0].equalsIgnoreCase("target") && args[0].equalsIgnoreCase("Proxy") && 
/* 42 */       args[0].equalsIgnoreCase("maintenance") && args[0].equalsIgnoreCase("true") && 
/* 43 */       p.hasPermission(Config.getSetting("MaintenancePerm"))) {
/* 44 */       handleCommand((CommandSender)p, args);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void handleCommand(CommandSender p, String[] args) {
/* 50 */     File config = new File(Proxy.getInstance().getDataFolder().getPath(), "config.yml");
/* 51 */     if (Boolean.valueOf(FileHelper.getString(config, "Maintenance")).booleanValue()) {
/* 52 */       FileHelper.saveString(config, "Maintenance", "false");
/* 53 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("MaintenanceOff")));
/*    */     } else {
/* 55 */       FileHelper.saveString(config, "Maintenance", "true");
/* 56 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("MaintenanceOn")));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\MaintenanceEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */