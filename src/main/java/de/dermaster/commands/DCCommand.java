/*    */ package de.dermaster.commands;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.CommandSender;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.plugin.Command;
/*    */ 
/*    */ public class DCCommand
/*    */   extends Command {
/*    */   public DCCommand() {
/* 12 */     super("Discord", Config.getSetting("PlayerPerm"), new String[] { "DC" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 17 */     sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + "§a" + Config.getSetting("DiscordLink")));
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\commands\DCCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */