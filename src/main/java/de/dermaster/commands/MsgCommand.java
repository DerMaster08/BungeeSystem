/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FriendUtils;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class MsgCommand extends Command {
/*    */   public MsgCommand() {
/* 15 */     super("Msg");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (args.length >= 2) {
/* 23 */         ProxiedPlayer other = ProxyServer.getInstance().getPlayer(args[0]);
/* 24 */         if (other != null) {
/* 25 */           if (FriendUtils.hasFriend(p.getUniqueId().toString(), other.getUniqueId().toString())) {
/* 26 */             String msg = "";
/* 27 */             for (int i = 1; i < args.length; i++) {
/* 28 */               msg = String.valueOf(msg) + " " + args[i];
/*    */             }
/* 30 */             Proxy.lastMsg.put(args[0], p.getName());
/* 31 */             ProxyServer.getInstance().getPlayer(args[0]).sendMessage((BaseComponent)new TextComponent(String.valueOf(Proxy.FRIEND_PREFIX) + "§6" + p.getName() + " §8>> §6Mir§8:§6" + msg));
/* 32 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§6Ich §8>> §6" + args[0] + "§8:§6" + msg));
/*    */           } else {
/* 34 */             p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("MsgNoFriend").replace("<PlayerName>", args[0])));
/*    */           } 
/*    */         } else {
/* 37 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("PlayerNotOnline")));
/*    */         } 
/*    */       } else {
/* 40 */         sendHelpMsg((CommandSender)p);
/*    */       } 
/*    */     } else {
/* 43 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(CommandSender p) {
/* 48 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + Config.getSetting("MsgHelp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\MsgCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */