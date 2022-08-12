/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import de.dermaster.utils.FriendUtils;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class FLCommand
/*    */   extends Command {
/*    */   public FLCommand() {
/* 15 */     super("FriendList", Config.getSetting("PlayerPerm"), new String[] { "FL", "FreundesListe" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 20 */     if (sender instanceof ProxiedPlayer) {
/* 21 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 22 */       if (args.length == 0) {
/* 23 */         FriendUtils.sendFriendList(p, 1);
/* 24 */       } else if (args.length == 1) {
/* 25 */         int side = 1;
/*    */         try {
/* 27 */           side = Integer.parseInt(args[0]);
/* 28 */         } catch (Exception exc) {
/* 29 */           sendHelpMsg(p);
/*    */           return;
/*    */         } 
/* 32 */         FriendUtils.sendFriendList(p, side);
/*    */       } else {
/* 34 */         sendHelpMsg(p);
/*    */       } 
/*    */     } else {
/* 37 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("CommandOnlyIngame"))));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendHelpMsg(ProxiedPlayer p) {
/* 42 */     p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("FL-Help"))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\FLCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */