/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ 
/*    */ public class ReportHelper
/*    */ {
/*    */   private ProxiedPlayer reporter;
/*    */   private String reason;
/*    */   
/*    */   public ReportHelper(ProxiedPlayer reporter, String reason) {
/* 11 */     this.reporter = reporter;
/* 12 */     this.reason = reason;
/*    */   }
/*    */   
/*    */   public ProxiedPlayer getReporter() {
/* 16 */     return this.reporter;
/*    */   }
/*    */   public String getReason() {
/* 19 */     return this.reason;
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\ReportHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */