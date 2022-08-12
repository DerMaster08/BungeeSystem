/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.Config;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
import java.util.List;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.ChatEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class AntiCommandEvents
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(ChatEvent e) throws IOException {
/* 22 */     ProxiedPlayer p = (ProxiedPlayer)e.getSender();
/* 23 */     String[] args = e.getMessage().split(" ");
/* 24 */     if (p.hasPermission(Config.getSetting("AntiCommandBypassPerm")))
/* 25 */       return;  if (e.getMessage().startsWith("/")) {
/* 26 */       File file = new File(Proxy.getInstance().getDataFolder().getPath(), "AntiCommand.yml");
/* 27 */       List<String> contents = Files.readAllLines(Paths.get(file.toURI()));
/* 28 */       for (String s : contents) {
/* 29 */         String[] line = s.split("\\?", 2);
/* 30 */         if (args[0].equalsIgnoreCase("/" + line[0])) {
/* 31 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + ChatColor.translateAlternateColorCodes('&', line[1])));
/* 32 */           e.setCancelled(true);
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\AntiCommandEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */