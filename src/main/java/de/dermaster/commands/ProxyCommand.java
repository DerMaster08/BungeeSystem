/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import java.io.IOException;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class ProxyCommand
/*    */   extends Command
/*    */ {
/*    */   public ProxyCommand() {
/* 16 */     super("Proxy");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 21 */     if (sender instanceof ProxiedPlayer) {
/* 22 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 23 */       if (p.hasPermission(Config.getSetting("AdminPerm"))) {
/* 24 */         handleCommand((CommandSender)p, args);
/*    */       } else {
/* 26 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 29 */       handleCommand(sender, args);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void handleCommand(CommandSender p, String[] args) {
/* 34 */     if (args.length == 1) {
/* 35 */       if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
/*    */         
/* 37 */         try { Config.loadConfigs(); }
/* 38 */         catch (IOException e) { e.printStackTrace(); }
/* 39 */          p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("ProxyConfigReloadSuccess")));
/*    */       } else {
/* 41 */         sendHelpMsg(p);
/*    */       } 
/*    */     } else {
/* 44 */       sendHelpMsg(p);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 49 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("ProxyHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\ProxyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */