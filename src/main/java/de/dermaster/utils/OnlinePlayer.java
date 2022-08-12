/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
/*    */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Plugin;
/*    */ 
/*    */ public class OnlinePlayer
/*    */ {
/*    */   private int scheduler;
/*    */   private int onlineTime;
/*    */   private String uuid;
/*    */   
/*    */   public OnlinePlayer(ProxiedPlayer p) {
/* 18 */     this.uuid = p.getUniqueId().toString();
/* 19 */     loadOnlineTime(p);
/* 20 */     startScheduler();
/*    */   }
/*    */   
/*    */   private void loadOnlineTime(ProxiedPlayer p) {
    try {
        if(!MySQL.hasAccount(this.uuid)){
            try {
                MySQL.registerOnTime(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                this.onlineTime = MySQL.getOnTime(this.uuid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    /*    */   }
/*    */
public void saveOnlineTime() {
    try {
        MySQL.saveOnTime(this.uuid, this.onlineTime);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    /* 37 */     stopScheduler();
/*    */   }
/*    */   
/*    */   public String getOnlineTime() {
/* 41 */     int playTimeInSeconds = this.onlineTime;
/* 42 */     int seconds = playTimeInSeconds % 60;
/* 43 */     int tooManyMinutes = (playTimeInSeconds - seconds) / 60;
/* 44 */     int rest = tooManyMinutes % 1440;
/* 45 */     int days = (tooManyMinutes - rest) / 1440;
/* 46 */     int minutes = rest % 60;
/* 47 */     int hours = (rest - minutes) / 60;
/*    */     
/* 49 */     return String.valueOf(days) + "d " + hours + "h " + minutes + "m " + seconds + "s";
/*    */   }
/*    */   
/*    */   private void startScheduler() {
/* 53 */     this.scheduler = ProxyServer.getInstance().getScheduler().schedule((Plugin)Proxy.getInstance(), new Runnable()
/*    */         {
/*    */           public void run() {
/* 56 */             OnlinePlayer.this.onlineTime = OnlinePlayer.this.onlineTime + 1;
/*    */           }
/* 58 */         },  1L, 1L, TimeUnit.SECONDS).getId();
/*    */   }
/*    */   
/*    */   private void stopScheduler() {
/* 62 */     ProxyServer.getInstance().getScheduler().cancel(this.scheduler);
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\OnlinePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */