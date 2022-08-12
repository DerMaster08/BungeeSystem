/*    */ package de.dermaster.events;
/*    */ 
/*    */ import de.dermaster.utils.Config;
/*    */ import net.md_5.bungee.api.event.ChatEvent;
/*    */ import net.md_5.bungee.api.plugin.Listener;
/*    */ import net.md_5.bungee.event.EventHandler;
/*    */ 
/*    */ public class AutoMute
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onCommand(ChatEvent e) {
/* 12 */     String[] msg = e.getMessage().split(" "); byte b; int j; String[] arrayOfString1;
/* 13 */     for (j = (arrayOfString1 = Config.getSetting("Beschimpfungen").split("#")).length, b = 0; b < j; ) { String badWord = arrayOfString1[b];
/* 14 */       for (int k = 0; k < msg.length; k++) {
/* 15 */         String s = msg[k];
/* 16 */         if (s.equalsIgnoreCase(badWord)) {
/* 17 */           String stars = "";
/* 18 */           for (int m = 0; m < s.length(); m++) {
/* 19 */             stars = String.valueOf(stars) + "*";
/*    */           }
/* 21 */           msg[k] = stars;
/*    */         } 
/*    */       }  b++; }
/*    */     
/* 25 */     String newMsg = msg[0];
/* 26 */     for (int i = 1; i < msg.length; i++) {
/* 27 */       newMsg = String.valueOf(newMsg) + " " + msg[i];
/*    */     }
/* 29 */     e.setMessage(newMsg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\events\AutoMute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */