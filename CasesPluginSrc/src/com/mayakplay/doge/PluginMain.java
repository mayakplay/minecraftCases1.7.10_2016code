package com.mayakplay.doge;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_7_R4.Item;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Константин on 10.01.2016.
 */
public class PluginMain extends JavaPlugin implements Listener {

    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private FileConfiguration config;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getLogger().info("Im enabled");
        setupPermissions();
        setupChat();
        setupEconomy();
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "CasesListChanel");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "CasesShopChanel");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "CasesCurChanel");

        config = getConfig();

        getLogger().info("Case item drop 1 chance set to - "+config.getInt("Chance1"));
        getLogger().info("Case item drop 2 chance set to - "+config.getInt("Chance2"));
        getLogger().info("Case item drop 3 chance set to - "+config.getInt("Chance3"));
        getLogger().info("Case item drop 4 chance set to - "+config.getInt("Chance4"));
        getLogger().info("Case item drop 5 chance set to - "+config.getInt("Chance5"));

        //Bukkit.getPluginManager().registerEvents(new SimpleEvents(), this);

        //ByteArrayDataOutput o = ByteStreams.newDataOutput();
        //o.writeUTF(pFrom.getPlayer().getName());
        //to.sendPluginMessage(getPlugin(PartyMain.class), "PartyYesNo", o.toByteArray());
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        getLogger().info("Im disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("mpcaseshop") && sender instanceof Player) {
            openCasesShop(((Player) sender).getPlayer());
        }

        if (command.getName().equalsIgnoreCase("mpcaseview") && sender instanceof Player) {
            if (args.length == 1) {
                if (getCasesList().get(Integer.parseInt(args[0])) != null) {
                    openCaseView(((Player) sender).getPlayer(), Integer.parseInt(args[0]));
                }
            }
        }

        if (command.getName().equalsIgnoreCase("rollcase") && sender instanceof Player) {
            if (args.length == 1) {
                if (getCasesList().get(Integer.parseInt(args[0])) != null) {
                    rollCase(((Player) sender).getPlayer(), Integer.parseInt(args[0]));
                }
            }
        }

        if (command.getName().equalsIgnoreCase("mpmotd") && sender instanceof Player) {
            ByteArrayDataOutput setWon = ByteStreams.newDataOutput();
            setWon.writeUTF("SetMotd,"+config.getString("MpMotd"));

            ((Player) sender).getPlayer().sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", setWon.toByteArray());
        }

        if (command.getName().equalsIgnoreCase("casesreload")) {
            if (sender instanceof Player) {
                if (sender.isOp()) {
                    reloadConfig();
                    config = getConfig();

                    if (sender.getName().equalsIgnoreCase("Noire"))
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны. Глебка, шо ты там придумал? Крусаучег!");
                    else if (sender.getName().equalsIgnoreCase("Admin"))
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны. Саня, красава! Пили еще. Ты великолепен!)))");
                    else
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны.");
                }
            } else {
                reloadConfig();
                config = getConfig();
                sender.sendMessage("Привет, владелец консоли. Кейсы я, конечно, перезагружу, но я бы ");
                sender.sendMessage("не отказался стать владельцем пасса от фтп(((9 ");
            }
        }

        return false;
    }

    private Case getCaseById(int caseId) {
        for (int i = 0; i < getCasesList().size(); i++) {
            if (i == caseId)
            return getCasesList().get(i);
        }
        return null;
    }

    private void rollCase(Player target, int caseId) {
        int playerBalace = (int) economy.getBalance(target);

        if (!(playerBalace < getCaseById(caseId).getPrice())) {
            economy.withdrawPlayer(target, getCaseById(caseId).getPrice());
            CaseItem ca = getRandomItemFromCase(caseId);

            int rand = randInt(ca.getMinStackSize(), ca.getMaxStackSize());

            ItemStack is = new ItemStack(Material.getMaterial(ca.getId()), rand, (byte) ca.getMeta());
            new AddItemDelay(target, is).start();

            ByteArrayDataOutput setWon = ByteStreams.newDataOutput();
            setWon.writeUTF("SetWon," + ca.getId() + "," + ca.getMeta() + "," + rand + "," + ca.getRarity());
            getLogger().info(ca.getRarity() + "");
            target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", setWon.toByteArray());

            ByteArrayDataOutput roll = ByteStreams.newDataOutput();
            roll.writeUTF("RollCase");
            target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", roll.toByteArray());
        } else {
            int raznisa = getCaseById(caseId).getPrice() - playerBalace;
            target.sendMessage(ChatColor.DARK_RED + "=============================================================");
            target.sendMessage(ChatColor.RED + "У вас недостаточно денег на счету. Нужно еще " + raznisa);
            target.sendMessage(ChatColor.DARK_RED + "=============================================================");
        }
    }

    private CaseItem getRandomItemFromCase(int caseId) {
        getCaseItems(caseId);

        List<CaseItem> itemsFin = new ArrayList<CaseItem>();
        List<CaseItem> items1 = new ArrayList<CaseItem>();
        List<CaseItem> items2 = new ArrayList<CaseItem>();
        List<CaseItem> items3 = new ArrayList<CaseItem>();
        List<CaseItem> items4 = new ArrayList<CaseItem>();
        List<CaseItem> items5 = new ArrayList<CaseItem>();

        for (int i = 0; i < getCaseItems(caseId).size(); i++) {
            switch (getCaseItems(caseId).get(i).getRarity()) {
                case 1:
                    items1.add(getCaseItems(caseId).get(i));
                    break;
                case 2:
                    items2.add(getCaseItems(caseId).get(i));
                    break;
                case 3:
                    items3.add(getCaseItems(caseId).get(i));
                    break;
                case 4:
                    items4.add(getCaseItems(caseId).get(i));
                    break;
                case 5:
                    items5.add(getCaseItems(caseId).get(i));
                    break;
            }
        }

        for (int i = 0; i < items1.size(); i++) {
            for (int col = 0; col < config.getInt("Chance1"); col++) {
                itemsFin.add(items1.get(i));
            }
        }

        for (int i = 0; i < items2.size(); i++) {
            for (int col = 0; col < config.getInt("Chance2"); col++) {
                itemsFin.add(items2.get(i));
            }
        }

        for (int i = 0; i < items3.size(); i++) {
            for (int col = 0; col < config.getInt("Chance3"); col++) {
                itemsFin.add(items3.get(i));
            }
        }

        for (int i = 0; i < items4.size(); i++) {
            for (int col = 0; col < config.getInt("Chance4"); col++) {
                itemsFin.add(items4.get(i));
            }
        }

        for (int i = 0; i < items5.size(); i++) {
            for (int col = 0; col < config.getInt("Chance5"); col++) {
                itemsFin.add(items5.get(i));
            }
        }

        Collections.shuffle(itemsFin);

        getLogger().info(itemsFin.size()+"s");
        return itemsFin.get(randInt(0,itemsFin.size()-1));
    }

    private void openCaseView(Player target, int caseId) {
        ByteArrayDataOutput clear = ByteStreams.newDataOutput();
        clear.writeUTF("ClearLast");
        target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", clear.toByteArray());

        for (int i = 0; i < getCaseItems(caseId).size(); i++) {
            List<CaseItem> items = getCaseItems(caseId);
            getLogger().info(i + "Point");
            ByteArrayDataOutput list = ByteStreams.newDataOutput();
            getLogger().info(items.get(i).getId() + "," + items.get(i).getMeta() + "," + items.get(i).getRarity());
            list.writeUTF(items.get(i).getId() + "," + items.get(i).getMeta() + "," + items.get(i).getRarity());
            target.sendPluginMessage(getPlugin(PluginMain.class), "CasesCurChanel", list.toByteArray());
        }

        ByteArrayDataOutput open = ByteStreams.newDataOutput();
        open.writeUTF("Viev,"+caseId);
        target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", open.toByteArray());
    }

    private void openCasesShop(Player target) {
        ByteArrayDataOutput clear = ByteStreams.newDataOutput();
        clear.writeUTF("Clear");
        target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", clear.toByteArray());

        for (int i = 0; i < getCasesList().size(); i++) {
            ByteArrayDataOutput list = ByteStreams.newDataOutput();
            list.writeUTF(getCasesList().get(i).getName() + "," + getCasesList().get(i).getPrice() + "," + getCasesList().get(i).getTexture());
            target.sendPluginMessage(getPlugin(PluginMain.class), "CasesListChanel", list.toByteArray());
        }

        ByteArrayDataOutput open = ByteStreams.newDataOutput();
        open.writeUTF("Open");
        target.sendPluginMessage(getPlugin(PluginMain.class), "CasesShopChanel", open.toByteArray());
    }

    private void sendCasesListToPlayer(Player player) {
        player.sendMessage("privet");
    }

    private List<Case> getCasesList() {
        List<Case> cases = new ArrayList<Case>();
        List<String> list = config.getStringList("Cases");

        if (list.size() != 0)
        for (int i = 0; i < list.size(); i++) {
            String[] sumon = list.get(i).split(",");

            String name = sumon[0];
            int price = Integer.parseInt(sumon[1]);
            String texture = sumon[2];

            cases.add(new Case(name, price, texture));
        }


        return cases;
    }

    private List<CaseItem> getCaseItems(int caseIdToget) {
        List<CaseItem> items = new ArrayList<CaseItem>();

        List<String> list = config.getStringList("CaseItems");

        if (list.size() != 0)
        for (int i = 0; i < list.size(); i++) {
            String[] sumon = list.get(i).split(",");

            int id = Integer.parseInt(sumon[0]);
            int meta = Integer.parseInt(sumon[1]);
            int rarity = Integer.parseInt(sumon[2]);
            int minStackSize = Integer.parseInt(sumon[3]);
            int maxStackSize = Integer.parseInt(sumon[4]);
            int caseId = Integer.parseInt(sumon[5]);
            //getLogger().info("a");
            if (caseId == caseIdToget) {
                //getLogger().info("i");
                items.add(new CaseItem(id, meta, rarity, minStackSize, maxStackSize, caseId));
            }
        }

        return items;
    }

    public static int randInt(int min, int max) {
        int randomNum = new Random().nextInt((max - min) + 1) + min;
        return randomNum;
    }

    class AddItemDelay extends Thread {

        Player player;
        ItemStack is;

        public AddItemDelay(Player player, ItemStack is) {
            this.player = player;
            this.is = is;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player.getInventory().addItem(is);
        }
    }
}
