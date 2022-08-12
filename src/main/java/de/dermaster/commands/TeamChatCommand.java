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
/*    */ public class TeamChatCommand
/*    */   extends Command {
/*    */   public TeamChatCommand() {
/* 15 */     super("TeamChat", Config.getSetting("PlayerPerm"), new String[] { "TC" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (p.hasPermission(Config.getSetting("StaffPerm"))) {
/* 23 */         if (args.length >= 1) {
/* 24 */           String msg = String.valueOf(Proxy.TEAMCHAT_PREFIX) + p.getName() + ":"; byte b; int i; String[] arrayOfString;
/* 25 */           for (i = (arrayOfString = args).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 26 */             msg = String.valueOf(msg) + " " + s; b++; }
/*    */           
/* 28 */           for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
/* 29 */             if (all.hasPermission(Config.getSetting("StaffPerm"))) {
/* 30 */               all.sendMessage(TextComponent.fromLegacyText(msg));
/*    */             }
/*    */           } 
/*    */         } else {
/* 34 */           sendHelpMsg((CommandSender)p);
/*    */         } 
/*    */       } else {
/* 37 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.TEAMCHAT_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("NoPermMessage"))));
/*    */       } 
/*    */     } else {
/* 40 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.TEAMCHAT_PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 45 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.TEAMCHAT_PREFIX) + Config.getSetting("TeamChatHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\TeamChatCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */