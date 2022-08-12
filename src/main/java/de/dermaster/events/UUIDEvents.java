/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import de.dermaster.utils.FileHelper;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.event.PostLoginEvent;
/*    */ 
/*    */ 
/*    */ public class UUIDEvents
/*    */ {
/*    */   public static void onJoin(PostLoginEvent e) throws IOException {
/* 14 */     ProxiedPlayer p = e.getPlayer();
/* 15 */     File playerData = new File(Proxy.getInstance().getDataFolder().getPath(), "PlayerData.yml");
/* 16 */     int allPlayerCount = FileHelper.getInteger(playerData, "Count");
/* 17 */     boolean alreadyJoinedServer = false;
/* 18 */     for (int i = 1; i <= allPlayerCount; i++) {
/* 19 */       if (FileHelper.getString(playerData, "Player" + i + ".UUID").equals(p.getUniqueId().toString())) {
/* 20 */         FileHelper.saveString(playerData, "Player" + i + ".Name", p.getName());
/* 21 */         alreadyJoinedServer = true;
/*    */       } 
/*    */     } 
/* 24 */     if (!alreadyJoinedServer) {
/* 25 */       FileHelper.saveInteger(playerData, "Count", allPlayerCount + 1);
/* 26 */       FileHelper.saveString(playerData, "Player" + (allPlayerCount + 1) + ".UUID", p.getUniqueId().toString());
/* 27 */       FileHelper.saveString(playerData, "Player" + (allPlayerCount + 1) + ".Name", p.getName());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\UUIDEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */