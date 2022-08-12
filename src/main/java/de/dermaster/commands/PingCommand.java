/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class PingCommand
/*    */   extends Command {
/*    */   public PingCommand() {
/* 14 */     super("Ping");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 19 */     if (sender instanceof ProxiedPlayer) {
/* 20 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 21 */       if (args.length == 0) {
/* 22 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("PingSelf").replace("<Ping>", String.valueOf(p.getPing()))));
/* 23 */       } else if (args.length == 1) {
/* 24 */         ProxiedPlayer other = ProxyServer.getInstance().getPlayer(args[0]);
/* 25 */         if (other != null) {
/* 26 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("PingOther").replace("<Ping>", String.valueOf(other.getPing()))
/* 27 */                 .replace("<PlayerName>", other.getName())));
/*    */         } else {
/* 29 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("PlayerNotOnline")));
/*    */         } 
/*    */       } else {
/* 32 */         sendHelpMsg((CommandSender)p);
/*    */       } 
/*    */     } else {
/* 35 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 40 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("PingHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\PingCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */