/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class VoteCommand
/*    */   extends Command {
/*    */   public VoteCommand() {
/* 14 */     super("vote");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 19 */     if (sender instanceof ProxiedPlayer) {
/* 20 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 21 */       p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("VoteMessage"))));
/*    */     } else {
/* 23 */       sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\VoteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */