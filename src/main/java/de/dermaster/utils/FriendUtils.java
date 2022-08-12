/*     */ package de.dermaster.utils;
/*     */ 
/*     */ import de.dermaster.Proxy;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.connection.ProxiedPlayer;
/*     */ import net.md_5.bungee.config.Configuration;
/*     */ import net.md_5.bungee.config.ConfigurationProvider;
/*     */ import net.md_5.bungee.config.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ public class FriendUtils
/*     */ {
/*  17 */   public static final String PATH = String.valueOf(Proxy.getInstance().getDataFolder().getPath()) + "/friends";
/*     */ 
/*     */   
/*     */   public static void requestFriend(String requestPlayer, String receivePlayer) {
/*  21 */     File fileForReceiver = new File(PATH, String.valueOf(receivePlayer) + ".yml");
/*     */     
/*  23 */     try { Configuration cfgForReceiver = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForReceiver);
/*  24 */       String finalRequesters = "";
/*     */       
/*  26 */       if (cfgForReceiver.contains("Anfragen") && !cfgForReceiver.getString("Anfragen").equals("")) {
/*  27 */         String requesters = cfgForReceiver.getString("Anfragen");
/*  28 */         finalRequesters = String.valueOf(requesters) + " " + requestPlayer;
/*     */       } else {
/*  30 */         finalRequesters = requestPlayer;
/*     */       } 
/*     */       
/*  33 */       cfgForReceiver.set("Anfragen", finalRequesters);
/*     */       
/*  35 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgForReceiver, fileForReceiver); }
/*  36 */     catch (Exception exc) { exc.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void acceptFriend(String requestPlayer, String receivePlayer) {
/*  42 */     File fileForReceiver = new File(PATH, String.valueOf(receivePlayer) + ".yml");
/*  43 */     File fileForRequester = new File(PATH, String.valueOf(requestPlayer) + ".yml");
/*     */     
/*  45 */     try { Configuration cfgReceiver = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForReceiver);
/*  46 */       Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/*     */ 
/*     */       
/*  49 */       String allRequesters = cfgRequester.getString("Anfragen");
/*  50 */       String finalRequesters = "";
/*  51 */       String[] requesters = allRequesters.split(" ");
/*  52 */       for (int i = 0; i < requesters.length; i++) {
/*  53 */         if (!requesters[i].equals(receivePlayer)) {
/*  54 */           if (finalRequesters.equals("")) {
/*  55 */             finalRequesters = requesters[i];
/*     */           } else {
/*  57 */             finalRequesters = String.valueOf(finalRequesters) + " " + requesters[i];
/*     */           } 
/*     */         }
/*     */       } 
/*  61 */       cfgRequester.set("Anfragen", finalRequesters);
/*     */ 
/*     */       
/*  64 */       String receiverFriends = "";
/*  65 */       if (cfgReceiver.contains("Freunde") && !cfgReceiver.getString("Freunde").equals("")) {
/*  66 */         String oldRequesters = cfgReceiver.getString("Freunde");
/*  67 */         receiverFriends = String.valueOf(oldRequesters) + " " + requestPlayer;
/*     */       } else {
/*  69 */         receiverFriends = requestPlayer;
/*     */       } 
/*  71 */       cfgReceiver.set("Freunde", receiverFriends);
/*     */ 
/*     */       
/*  74 */       String requesterFriends = "";
/*  75 */       if (cfgRequester.contains("Freunde") && !cfgRequester.getString("Freunde").equals("")) {
/*  76 */         String oldRequesters = cfgRequester.getString("Freunde");
/*  77 */         requesterFriends = String.valueOf(oldRequesters) + " " + receivePlayer;
/*     */       } else {
/*  79 */         requesterFriends = receivePlayer;
/*     */       } 
/*  81 */       cfgRequester.set("Freunde", requesterFriends);
/*     */       
/*  83 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgReceiver, fileForReceiver);
/*  84 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgRequester, fileForRequester); }
/*  85 */     catch (IOException exc) { exc.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void declineFriend(String requestPlayer, String receivePlayer) {
/*  90 */     File fileForRequester = new File(PATH, String.valueOf(requestPlayer) + ".yml");
/*     */     
/*  92 */     try { Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/*     */ 
/*     */       
/*  95 */       String allRequesters = cfgRequester.getString("Anfragen");
/*  96 */       String finalRequesters = "";
/*  97 */       String[] requesters = allRequesters.split(" ");
/*  98 */       for (int i = 0; i < requesters.length; i++) {
/*  99 */         if (!requesters[i].equals(receivePlayer)) {
/* 100 */           if (finalRequesters.equals("")) {
/* 101 */             finalRequesters = requesters[i];
/*     */           } else {
/* 103 */             finalRequesters = String.valueOf(finalRequesters) + " " + requesters[i];
/*     */           } 
/*     */         }
/*     */       } 
/* 107 */       cfgRequester.set("Anfragen", finalRequesters);
/*     */ 
/*     */       
/* 110 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgRequester, fileForRequester); }
/* 111 */     catch (Exception exc) { exc.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeFriend(String requestPlayer, String receivePlayer) {
/* 117 */     File fileForReceiver = new File(PATH, String.valueOf(receivePlayer) + ".yml");
/* 118 */     File fileForRequester = new File(PATH, String.valueOf(requestPlayer) + ".yml");
/*     */     
/* 120 */     try { Configuration cfgReceiver = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForReceiver);
/* 121 */       Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/*     */ 
/*     */       
/* 124 */       String allRequesterFriends = cfgRequester.getString("Freunde");
/* 125 */       String finalRequesterFriends = "";
/* 126 */       String[] requesterFriends = allRequesterFriends.split(" ");
/* 127 */       for (int i = 0; i < requesterFriends.length; i++) {
/* 128 */         if (!requesterFriends[i].equals(receivePlayer)) {
/* 129 */           if (finalRequesterFriends.equals("")) {
/* 130 */             finalRequesterFriends = requesterFriends[i];
/*     */           } else {
/* 132 */             finalRequesterFriends = String.valueOf(finalRequesterFriends) + " " + requesterFriends[i];
/*     */           } 
/*     */         }
/*     */       } 
/* 136 */       cfgRequester.set("Freunde", finalRequesterFriends);
/*     */ 
/*     */       
/* 139 */       String allReceiverFriends = cfgReceiver.getString("Freunde");
/* 140 */       String finalReceiverFriends = "";
/* 141 */       String[] receiverFriends = allReceiverFriends.split(" ");
/* 142 */       for (int j = 0; j < receiverFriends.length; j++) {
/* 143 */         if (!receiverFriends[j].equals(requestPlayer)) {
/* 144 */           if (finalReceiverFriends.equals("")) {
/* 145 */             finalReceiverFriends = receiverFriends[j];
/*     */           } else {
/* 147 */             finalReceiverFriends = String.valueOf(finalReceiverFriends) + " " + receiverFriends[j];
/*     */           } 
/*     */         }
/*     */       } 
/* 151 */       cfgReceiver.set("Freunde", finalReceiverFriends);
/*     */ 
/*     */       
/* 154 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgReceiver, fileForReceiver);
/* 155 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgRequester, fileForRequester); }
/* 156 */     catch (Exception exc) { exc.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   public static boolean hasFriend(String requestPlayer, String receivePlayer) {
/* 161 */     if (receivePlayer.equals("")) {
/* 162 */       return false;
/*     */     }
/* 164 */     File fileForRequester = new File(PATH, String.valueOf(requestPlayer) + ".yml");
/*     */     try {
/* 166 */       Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/* 167 */       if (cfgRequester.contains("Freunde")) {
/* 168 */         String friends = cfgRequester.getString("Freunde");
/* 169 */         if (friends.contains(receivePlayer))
/* 170 */           return true; 
/*     */       } 
/*     */     } catch (IOException e) {
/* 173 */       e.printStackTrace();
/* 174 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasRequest(String requestPlayer, String receivePlayer) {
/* 179 */     File fileForRequester = new File(PATH, String.valueOf(requestPlayer) + ".yml");
/*     */     try {
/* 181 */       Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/*     */       
/* 183 */       if (cfgRequester.contains("Anfragen")) {
/* 184 */         String friends = cfgRequester.getString("Anfragen");
/* 185 */         if (friends.contains(receivePlayer))
/* 186 */           return true; 
/*     */       } 
/*     */     } catch (IOException e) {
/* 189 */       e.printStackTrace();
/* 190 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<String> getFriendsSorted(String requestOpener) throws IOException {
/* 208 */     if (requestOpener == null) return new ArrayList<>(); 
/* 209 */     File fileForRequester = new File(PATH, String.valueOf(requestOpener) + ".yml");
/* 210 */     Configuration cfgRequester = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileForRequester);
/*     */     
/* 212 */     if (!cfgRequester.contains("Freunde")) {
/* 213 */       return new ArrayList<>();
/*     */     }
/*     */     
/* 216 */     String allFriends = cfgRequester.getString("Freunde");
/* 217 */     String[] friends = allFriends.split(" ");
/* 218 */     ArrayList<String> result = new ArrayList<>();
/*     */     int i;
/* 220 */     for (i = 0; i < friends.length; i++) {
/* 221 */       String uuid = friends[i];
/* 222 */       if (isOnline(uuid)) {
/* 223 */         result.add(uuid);
/*     */       }
/*     */     } 
/*     */     
/* 227 */     for (i = 0; i < friends.length; i++) {
/* 228 */       String uuid = friends[i];
/* 229 */       if (!isOnline(uuid)) {
/* 230 */         result.add(uuid);
/*     */       }
/*     */     } 
/*     */     
/* 234 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean isOnline(String uuid) {
/* 238 */     for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
/* 239 */       if (getUUIDFromName(p.getName()).equals(uuid)) {
/* 240 */         return true;
/*     */       }
/*     */     } 
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getServer(ProxiedPlayer p) throws IOException {
/* 256 */     return p.getServer().getInfo().getName();
/*     */   }
/*     */   
/*     */   public static String getUUIDFromName(String name) {
/* 260 */     String uuid = PlayerData.getUUIDfromName(name);
/* 261 */     if (uuid != null)
/* 262 */       return uuid; 
/* 263 */     return "";
/*     */   }
/*     */   
/*     */   public static String getNameFromUUID(String uuid) throws IOException {
/* 267 */     String name = PlayerData.getNameFromUUID(uuid);
/* 268 */     if (name != null)
/* 269 */       return name; 
/* 270 */     return "";
/*     */   }
/*     */   
/*     */   public static int getFriendListSidesCount(int i) {
/* 274 */     int j = 0;
/* 275 */     while (j * 8 < i) {
/* 276 */       j++;
/*     */     }
/* 278 */     return j;
/*     */   }
/*     */   
/*     */   public static String getStatus(ProxiedPlayer p, String uuid) throws IOException {
/* 282 */     if (!isOnline(uuid)) {
/* 283 */       return "§a ist §coffline!";
/*     */     }
/* 285 */     String server = getServer(p);
/* 286 */     return "§a ist auf " + server + "!";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<String> getOnlineFriends(String openerUUID) throws IOException {
/* 305 */     File file = new File(PATH, String.valueOf(openerUUID) + ".yml");
/* 306 */     Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
/*     */     
/* 308 */     if (!cfg.contains("Freunde") || cfg.getString("Freunde").equals("")) {
/* 309 */       return new ArrayList<>();
/*     */     }
/*     */     
/* 312 */     String allFriends = cfg.getString("Freunde");
/* 313 */     String[] friends = allFriends.split(" ");
/* 314 */     ArrayList<String> result = new ArrayList<>();
/*     */     
/* 316 */     for (int i = 0; i < friends.length; i++) {
/* 317 */       String uuid = friends[i];
/* 318 */       if (isOnline(uuid)) {
/* 319 */         result.add(uuid);
/*     */       }
/*     */     } 
/*     */     
/* 323 */     return result;
/*     */   }
/*     */   
/*     */   public static void sendFriendList(ProxiedPlayer p, int page) {
/*     */     
/* 328 */     try { ArrayList<String> friends = getFriendsSorted(getUUIDFromName(p.getName()));
/* 329 */       if (friends.size() == 0 || ((String)friends.get(0)).equals("")) {
/* 330 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§cDu hast leider keine Freunde!"));
/*     */       } else {
/* 332 */         if (page > getFriendListSidesCount(friends.size()) || page <= 0) {
/* 333 */           p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§cDie Seite §6" + page + " §cgibt es nicht in deiner Freundesliste!"));
/*     */           return;
/*     */         } 
/* 336 */         p.sendMessage(TextComponent.fromLegacyText(String.valueOf(Proxy.FRIEND_PREFIX) + "§6Seite: " + page + "/" + getFriendListSidesCount(friends.size())));
/* 337 */         for (int i = 8 * page - 8; i < 8 * page; i++) {
/* 338 */           if (i < friends.size()) {
/* 339 */             ProxiedPlayer current = ProxyServer.getInstance().getPlayer(getNameFromUUID(friends.get(i)));
/* 340 */             p.sendMessage(TextComponent.fromLegacyText("§aDer Spieler §6" + getNameFromUUID(friends.get(i)) + getStatus(current, friends.get(i))));
/*     */           } 
/*     */         } 
/*     */       }  }
/* 344 */     catch (IOException e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\FriendUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */