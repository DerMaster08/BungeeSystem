/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import de.dermaster.Proxy;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.ProxyServer;
    import net.md_5.bungee.api.chat.TextComponent;
/*    */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*    */ import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

/*    */
/*    */ public class AutomatedMessages
/*    */ {
/* 13 */   private static int count = 0;
/*    */   
/*    */   public static void start() {
/* 16 */
    ScheduledTask schedule = ProxyServer.getInstance().getScheduler().schedule((Plugin) Proxy.getInstance(), new Runnable() {
        /*    */
        public void run() {
            /* 19 */
            int maxCount = Integer.parseInt(Config.getSetting("MessagesCount"));
            /* 20 */
            if (AutomatedMessages.count < maxCount) {
                /* 21 */
                AutomatedMessages.count = AutomatedMessages.count + 1;
                /*    */
            } else {
                /* 23 */
                AutomatedMessages.count = 1;
                /*    */
            }
            /* 25 */
            String message = ChatColor.translateAlternateColorCodes('&', Config.getSetting("Messages" + AutomatedMessages.count));
            /* 26 */
            for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                /* 27 */
                if (all.getServer().getInfo().getName().equals(Config.getSetting("LobbyServer"))) {
                    /* 28 */
                    all.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + "---------------------------------"));
                    /* 29 */
                    all.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + message));
                    /* 30 */
                    all.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.PREFIX) + "---------------------------------"));
                    /*    */
                }
                /*    */
            }
            /*    */
        }
        /* 34 */
    }, 0, 5, TimeUnit.MINUTES);


/*    */   }
/*    */ }