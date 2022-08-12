/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class BroadcastCommand
/*    */   extends Command {
/*    */   public BroadcastCommand() {
/* 15 */     super("Broadcast", Config.getSetting("PlayerPerm"), new String[] { "BC" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (p.hasPermission(Config.getSetting("StaffPerm"))) {
/* 23 */         handleCommand(sender, args);
/*    */       } else {
/* 25 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 28 */       handleCommand(sender, args);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void handleCommand(CommandSender p, String[] args) {
/* 33 */     if (args.length >= 1) {
/* 34 */       String msg = ChatColor.translateAlternateColorCodes('&', Config.getSetting("BroadcastPrefix")); byte b; int i; String[] arrayOfString;
/* 35 */       for (i = (arrayOfString = args).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 36 */         msg = String.valueOf(msg) + " " + s; b++; }
/*    */       
/* 38 */       for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
/* 39 */         all.sendMessage(TextComponent.fromLegacyText(msg));
/*    */       }
/*    */     } else {
/* 42 */       sendHelpMsg(p);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 47 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("BroadcastHelp"))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\BroadcastCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */