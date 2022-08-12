/*    */ package de.dermaster.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Paths;
/*    */ import net.md_5.bungee.config.Configuration;
/*    */ import net.md_5.bungee.config.ConfigurationProvider;
/*    */ import net.md_5.bungee.config.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ public class FileHelper
/*    */ {
/*    */   public static void saveString(File file, String key, String value) {
/*    */     
/* 16 */     try { Configuration cfg = getConfiguration(file);
/* 17 */       cfg.set(key, value);
/*    */       
/* 19 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file); }
/* 20 */     catch (IOException e) { e.printStackTrace(); }
/*    */   
/*    */   }
/*    */   public static String getString(File file, String key) {
/*    */     
/* 25 */     try { Configuration cfg = getConfiguration(file);
/* 26 */       return cfg.getString(key); }
/* 27 */     catch (IOException e) { e.printStackTrace();
/* 28 */       return null; }
/*    */   
/*    */   }
/*    */   public static void saveInteger(File file, String key, int value) {
/*    */     
/* 33 */     try { Configuration cfg = getConfiguration(file);
/* 34 */       cfg.set(key, Integer.valueOf(value));
/*    */       
/* 36 */       ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file); }
/* 37 */     catch (IOException e) { e.printStackTrace(); }
/*    */   
/*    */   }
/*    */   public static int getInteger(File file, String key) {
/*    */     
/* 42 */     try { Configuration cfg = getConfiguration(file);
/* 43 */       return cfg.getInt(key); }
/* 44 */     catch (IOException e) { e.printStackTrace();
/* 45 */       return -1; }
/*    */   
/*    */   }
/*    */   public static Configuration getConfiguration(File file) throws IOException {
/* 49 */     return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
/*    */   }
/*    */   
/*    */   public static boolean contains(File file, String key) {
/*    */     
/* 54 */     try { Configuration cfg = getConfiguration(file);
/* 55 */       return cfg.contains(key); }
/* 56 */     catch (IOException e) { e.printStackTrace();
/* 57 */       return false; }
/*    */   
/*    */   }
/*    */   @Deprecated
/*    */   public static String getContentInLine(File file, int line) {
/* 62 */     String result = "";
/*    */     
/* 64 */     try { result = Files.readAllLines(Paths.get(file.getName(), new String[0])).get(line); }
/* 65 */     catch (IOException e) { e.printStackTrace(); }
/* 66 */      return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Leon\Downloads\LbGameBungeeCordSystem(1).jar!\de\telle\\utils\FileHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */