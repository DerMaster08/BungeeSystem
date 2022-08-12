/*     */ package de.dermaster;
/*     */ 
/*     */ import de.dermaster.commands.BauserverCommand;
/*     */ import de.dermaster.commands.BroadcastCommand;
/*     */ import de.dermaster.commands.ClearChat;
/*     */ import de.dermaster.commands.DCCommand;
/*     */ import de.dermaster.commands.FLCommand;
/*     */ import de.dermaster.commands.FailCommand;
/*     */ import de.dermaster.commands.FriendCommand;
/*     */ import de.dermaster.commands.JoinMeCommand;
/*     */ import de.dermaster.commands.Maintenance;
/*     */ import de.dermaster.commands.MsgCommand;
/*     */ import de.dermaster.commands.OnlineTimeCommand;
/*     */ import de.dermaster.commands.PartyCommand;
/*     */ import de.dermaster.commands.PingCommand;
/*     */ import de.dermaster.commands.ProxyCommand;
/*     */ import de.dermaster.commands.RCommand;
/*     */ import de.dermaster.commands.SpyCommand;
/*     */ import de.dermaster.commands.TeamChatCommand;
/*     */ import de.dermaster.commands.TestserverCommand;
/*     */ import de.dermaster.commands.VoteCommand;
/*     */ import de.dermaster.commands.YTCommand;
/*     */ import de.dermaster.events.AntiCommandEvents;
/*     */ import de.dermaster.events.AntiSpamEvents;
/*     */ import de.dermaster.events.AutoMute;
/*     */ import de.dermaster.events.FriendEvents;
/*     */ import de.dermaster.events.LobbyJoinEvent;
/*     */ import de.dermaster.events.MaintenanceEvents;
/*     */ import de.dermaster.events.OnlineTimeManager;
/*     */ import de.dermaster.events.PartyEvents;
/*     */
/*     */ import de.dermaster.events.SpyEvents;
/*     */ import de.dermaster.utils.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import net.md_5.bungee.api.ProxyServer;
/*     */ import net.md_5.bungee.api.plugin.Command;
/*     */ import net.md_5.bungee.api.plugin.Listener;
/*     */ import net.md_5.bungee.api.plugin.Plugin;
/*     */ 
/*     */ public class Proxy
/*     */   extends Plugin {
/*     */   private static Proxy plugin;
/*  54 */   public static String PREFIX = "";
/*  55 */   public static String REPORT_PREFIX = "";
/*  56 */   public static String JOINME_PREFIX = "";
/*  57 */   public static String PARTY_PREFIX = "";
/*  58 */   public static String FRIEND_PREFIX = "";
/*  59 */   public static String TEAMCHAT_PREFIX = "";
/*     */ 
/*     */   
/*  62 */   public static HashMap<UUID, ArrayList<ReportHelper>> reports = new HashMap<>();
/*  63 */   public static HashMap<UUID, OnlinePlayer> onlinePlayers = new HashMap<>();
/*  64 */   public static HashMap<UUID, JoinMeInfos> joinmes = new HashMap<>();
/*  65 */   public static HashMap<String, String> lastMsg = new HashMap<>();
/*  66 */   public static HashMap<UUID, ArrayList<UUID>> spyPlayers = new HashMap<>();
/*  67 */   public static HashMap<UUID, String> lastMessagesSpam = new HashMap<>();
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  71 */     super.onEnable();
/*  72 */     plugin = this;
/*  77 */     System.out.println("BungeeCordSystem was initialized!");
                if(MySQL.connect()){
                    System.out.println("MySQL Verbunden");
                }
/*  80 */     try { Config.loadConfigs(); }
/*  81 */     catch (IOException e) { e.printStackTrace(); }
/*  82 */      ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new ProxyCommand());
/*  83 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new YTCommand());
/*  84 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new DCCommand());
/*  85 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new BauserverCommand());
/*  86 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new TestserverCommand());
/*  87 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new TeamChatCommand());
/*  88 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new ClearChat());
/*  89 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new BroadcastCommand());
/*  90 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new Maintenance());
/*  91 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new OnlineTimeCommand());
/*  94 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new JoinMeCommand());
/*  95 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new PartyCommand());
/*  96 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new SpyCommand());
/*  97 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new PingCommand());
/*  98 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new MsgCommand());
/*  99 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new RCommand());
/* 100 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new FriendCommand());
/* 101 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new FLCommand());
/* 102 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new VoteCommand());
/* 103 */     ProxyServer.getInstance().getPluginManager().registerCommand(this, (Command)new FailCommand());
/* 104 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new MaintenanceEvents());
/* 105 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new AutoMute());
/* 106 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new OnlineTimeManager());
/* 107 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new PartyEvents());
/* 108 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new SpyEvents());
/* 109 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new FriendEvents());
/* 111 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new AntiSpamEvents());
/* 112 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new AntiCommandEvents());
/* 113 */     ProxyServer.getInstance().getPluginManager().registerListener(this, (Listener)new LobbyJoinEvent());
/* 114 */     AutomatedMessages.start();
/*     */   }
/*     */   
/*     */   public static Proxy getInstance() {
/* 118 */     return plugin;
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\tellem\Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */