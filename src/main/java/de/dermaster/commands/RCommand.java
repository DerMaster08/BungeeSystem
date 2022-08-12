/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FriendUtils;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class RCommand
/*    */   extends Command {
/*    */   public RCommand() {
/* 15 */     super("R");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (args.length >= 1) {
/* 23 */         if (Proxy.lastMsg.containsKey(p.getName())) {
/* 24 */           ProxiedPlayer other = ProxyServer.getInstance().getPlayer((String)Proxy.lastMsg.get(p.getName()));
/* 25 */           if (other != null) {
/* 26 */             if (FriendUtils.hasFriend(p.getUniqueId().toString(), other.getUniqueId().toString())) {
/* 27 */               String msg = "";
/* 28 */               for (int i = 0; i < args.length; i++) {
/* 29 */                 msg = String.valueOf(msg) + " " + args[i];
/*    */               }
/* 31 */               Proxy.lastMsg.put(other.getName(), p.getName());
/* 32 */               other.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§6" + p.getName() + " §8>> §6Mir§8:§6" + msg));
/* 33 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§6Ich §8>> §6" + other.getName() + "§8:§6" + msg));
/*    */             } else {
/* 35 */               p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("MsgNoFriend").replace("<PlayerName>", args[0])));
/*    */             } 
/*    */           } else {
/* 38 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("PlayerNotOnline")));
/*    */           } 
/*    */         } else {
/* 41 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("NoMessageToAnswer")));
/*    */         } 
/*    */       } else {
/* 44 */         sendHelpMsg((CommandSender)p);
/*    */       } 
/*    */     } else {
/* 47 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 52 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("RHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\RCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */