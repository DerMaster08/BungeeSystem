/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class YTCommand
/*    */   extends Command {
/*    */   public YTCommand() {
/* 13 */     super("YouTube", Config.getSetting("PlayerPerm"), new String[] { "YT" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 18 */     sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', Config.getSetting("YTRequirements"))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\YTCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */