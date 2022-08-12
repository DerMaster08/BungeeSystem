/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class FailCommand
/*    */   extends Command {
/*    */   public FailCommand() {
/* 13 */     super("Fail");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 18 */     if (sender instanceof ProxiedPlayer) {
/* 19 */       ProxiedPlayer p = (ProxiedPlayer)sender;
/* 20 */       p.sendMessage(TextComponent.fromLegacyText(Proxy.PREFIX + Config.getSetting("FailMessage")));
/*    */     } else {
/* 22 */       sender.sendMessage(TextComponent.fromLegacyText(Proxy.PREFIX + Config.getSetting("CommandOnlyIngame")));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\FailCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */