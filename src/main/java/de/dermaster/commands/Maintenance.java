/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FileHelper;
/*    */ import java.io.File;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class Maintenance
/*    */   extends Command
/*    */ {
/*    */   public Maintenance() {
/* 17 */     super("Maintenance");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 22 */     if (sender instanceof ProxiedPlayer) {
/* 23 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 24 */       if (p.hasPermission(Config.getSetting("MaintenancePerm"))) {
/* 25 */         handleCommand((CommandSender)p, args);
/*    */       } else {
/* 27 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 30 */       handleCommand(sender, args);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void handleCommand(CommandSender p, String[] args) {
/* 35 */     File config = new File(Proxy.getInstance().getDataFolder().getPath(), "config.yml");
/* 36 */     if (Boolean.valueOf(FileHelper.getString(config, "Maintenance")).booleanValue()) {
/* 37 */       FileHelper.saveString(config, "Maintenance", "false");
/* 38 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("MaintenanceOff")));
/*    */     } else {
/* 40 */       FileHelper.saveString(config, "Maintenance", "true");
/* 41 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("MaintenanceOn")));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\Maintenance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */