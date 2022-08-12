/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*    */ import net.md_5.bungee.api.event.ServerConnectEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class LobbyJoinEvent
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onServerJoin(ServerConnectEvent e) {
/* 13 */     if (e.getTarget().getName().equals(Config.getSetting("LobbyServer")))
/* 14 */       for (int i = 0; i < 200; i++)
/* 15 */         e.getPlayer().sendMessage((new ComponentBuilder(" ")).create());  
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\LobbyJoinEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */