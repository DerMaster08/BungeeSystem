/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   public static String getUUIDfromName(String name) {
/* 10 */     File playerData = new File(Proxy.getInstance().getDataFolder().getPath(), "PlayerData.yml");
/* 11 */     int allPlayerCount = FileHelper.getInteger(playerData, "Count");
/* 12 */     for (int i = 1; i <= allPlayerCount; i++) {
/* 13 */       if (FileHelper.getString(playerData, "Player" + i + ".Name").equals(name)) {
/* 14 */         return FileHelper.getString(playerData, "Player" + i + ".UUID");
/*    */       }
/*    */     } 
/* 17 */     return null;
/*    */   }
/*    */   
/*    */   public static String getNameFromUUID(String uuid) {
/* 21 */     File playerData = new File(Proxy.getInstance().getDataFolder().getPath(), "PlayerData.yml");
/* 22 */     int allPlayerCount = FileHelper.getInteger(playerData, "Count");
/* 23 */     for (int i = 1; i <= allPlayerCount; i++) {
/* 24 */       if (FileHelper.getString(playerData, "Player" + i + ".UUID").equals(uuid)) {
/* 25 */         return FileHelper.getString(playerData, "Player" + i + ".Name");
/*    */       }
/*    */     } 
/* 28 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */