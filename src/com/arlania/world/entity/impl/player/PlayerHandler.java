package com.arlania.world.entity.impl.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.arlania.GameServer;
import com.arlania.GameSettings;
import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.BonusExperienceTask;
import com.arlania.engine.task.impl.CombatSkullEffect;
import com.arlania.engine.task.impl.FireImmunityTask;
import com.arlania.engine.task.impl.OverloadPotionTask;
import com.arlania.engine.task.impl.PlayerSkillsTask;
import com.arlania.engine.task.impl.PlayerSpecialAmountTask;
import com.arlania.engine.task.impl.PrayerRenewalPotionTask;
import com.arlania.engine.task.impl.StaffOfLightSpecialAttackTask;
import com.arlania.model.Flag;
import com.arlania.model.Locations;
import com.arlania.model.PlayerRights;
import com.arlania.model.Skill;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Equipment;
import com.arlania.model.definitions.WeaponAnimations;
import com.arlania.model.definitions.WeaponInterfaces;
import com.arlania.net.PlayerSession;
import com.arlania.net.SessionState;
import com.arlania.net.security.ConnectionHandler;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.BonusManager;
import com.arlania.world.content.Lottery;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.PlayersOnlineInterface;
import com.arlania.world.content.StaffList;
import com.arlania.world.content.StartScreen;
import com.arlania.world.content.WellOfGoodwill;
import com.arlania.world.content.WellOfWealth;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.combat.effect.CombatPoisonEffect;
import com.arlania.world.content.combat.effect.CombatTeleblockEffect;
import com.arlania.world.content.combat.magic.Autocasting;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.pvp.BountyHunter;
import com.arlania.world.content.combat.range.DwarfMultiCannon;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.grandexchange.GrandExchange;
import com.arlania.world.content.minigames.impl.Barrows;
import com.arlania.world.content.skill.impl.hunter.Hunter;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.entity.impl.player.Player;

import mysql.impl.Hiscores;


public class PlayerHandler {

	
	
public static void rspsdata(Player player, String username){
try{
username = username.replaceAll(" ","_");
String secret = "d4ec33c0c23ae3c91764fcc625108a5a"; //YOUR SECRET KEY!
String email = "adilrahman78399@gmail.com"; //This is the one you use to login into RSPS-PAY
URL url = new URL("http://rsps-pay.com/includes/listener.php?username="+username+"&secret="+secret+"&email="+email);
BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
String results = reader.readLine();
if(results.toLowerCase().contains("!error:")){
//Logger.log(this, "[RSPS-PAY]"+results);
}else{
String[] ary = results.split(",");
for(int i = 0; i < ary.length; i++){
switch(ary[i]){
case "0":
player.sendMessage("Your donation was not found please Contact Owner!");	
	break;

case "20072":
	player.getInventory().add(6183, 1);
	break;
case "20007":
	player.getInventory().add(6199, 1);
	break;
	
case "19509":
	player.getInventory().add(6199, 1);
case "19744":
	player.getInventory().add(15356, 1);
break;
case "19749":
	player.getInventory().add(15355, 1);
break;
case "19934":
	player.getInventory().add(15359, 1);
break;
//50$scroll
case "19935":
	player.getInventory().add(15358, 1);
break;
case "19936":
	player.getInventory().add(15358, 2);
break;
case "19975":
	player.getInventory().add(15358, 5);
break;

case "20134":
player.getInventory().add(18933, 1);
break;

case "20135":
player.getInventory().add(18934, 1);
break;

case "SECONDPRODUCTID": //product ids can be found on the webstore page
//add items for the second product here!
break;
}
}
}
}catch(IOException e){}
}			
	
	
	/**
	 * Gets the player according to said name.
	 * @param name	The name of the player to search for.
	 * @return		The player who has the same name as said param.
	 */
	
	
	public static Player getPlayerForName(String name) {
		for (Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if (player.getUsername().equalsIgnoreCase(name))
				return player;
		}
		return null;
	}  

    public static void handleLogin(Player player) {
        System.out.println("[World] Registering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
        player.getPlayerOwnedShopManager().hookShop();
        player.setActive(true);
        ConnectionHandler.add(player.getHostAddress());
        World.getPlayers().add(player);
        World.updatePlayersOnline();
        PlayersOnlineInterface.add(player);
        player.getSession().setState(SessionState.LOGGED_IN);

        player.getPacketSender().sendMapRegion().sendDetails();

        player.getRecordedLogin().reset();

        player.getPacketSender().sendTabs();

        for (int i = 0; i < player.getBanks().length; i++) {
            if (player.getBank(i) == null) {
                player.setBank(i, new Bank(player));
            }
        }
        player.getInventory().refreshItems();
        player.getEquipment().refreshItems();
        
        WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        CombatSpecial.updateBar(player);
        BonusManager.update(player);

        player.getSummoning().login();
        player.getFarming().load();
        Slayer.checkDuoSlayer(player, true);
        for (Skill skill : Skill.values()) {
            player.getSkillManager().updateSkill(skill);
        }

        player.getRelations().setPrivateMessageId(1).onLogin(player).updateLists(true);

        player.getPacketSender().sendConfig(172, player.isAutoRetaliate() ? 1 : 0)
                .sendTotalXp(player.getSkillManager().getTotalGainedExp())
                .sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId())
                .sendRunStatus()
                .sendRunEnergy(player.getRunEnergy())
                .sendString(8135, "" + player.getMoneyInPouch())
                .sendInteractionOption("Follow", 3, false)
                .sendInteractionOption("Trade With", 4, false)
                .sendInterfaceRemoval().sendString(39161, "@or2@Server time: @or2@[ @yel@" + Misc.getCurrentServerTime() + "@or2@ ]");

        Autocasting.onLogin(player);
        PrayerHandler.deactivateAll(player);
        CurseHandler.deactivateAll(player);
        BonusManager.sendCurseBonuses(player);
        Achievements.updateInterface(player);
        Barrows.updateInterface(player);

        TaskManager.submit(new PlayerSkillsTask(player));
        if (player.isPoisoned()) {
            TaskManager.submit(new CombatPoisonEffect(player));
        }
        if (player.getPrayerRenewalPotionTimer() > 0) {
            TaskManager.submit(new PrayerRenewalPotionTask(player));
        }
        if (player.getOverloadPotionTimer() > 0) {
            TaskManager.submit(new OverloadPotionTask(player));
        }
        if (player.getTeleblockTimer() > 0) {
            TaskManager.submit(new CombatTeleblockEffect(player));
        }
        if (player.getSkullTimer() > 0) {
            player.setSkullIcon(1);
            TaskManager.submit(new CombatSkullEffect(player));
        }
        if (player.getFireImmunity() > 0) {
            FireImmunityTask.makeImmune(player, player.getFireImmunity(), player.getFireDamageModifier());
        }
        if (player.getSpecialPercentage() < 100) {
            TaskManager.submit(new PlayerSpecialAmountTask(player));
        }
        if (player.hasStaffOfLightEffect()) {
            TaskManager.submit(new StaffOfLightSpecialAttackTask(player));
        }
        if (player.getMinutesBonusExp() >= 0) {
            TaskManager.submit(new BonusExperienceTask(player));
        }

        player.getUpdateFlag().flag(Flag.APPEARANCE);

        Lottery.onLogin(player);
        Locations.login(player);
        
        if (player.didReceiveStarter() == false) {
			//player.getInventory().add(995, 1000000).add(15501, 1).add(1153, 1).add(1115, 1).add(1067, 1).add(1323, 1).add(1191, 1).add(841, 1).add(882, 50).add(1167, 1).add(1129, 1).add(1095, 1).add(1063, 1).add(579, 1).add(577, 1).add(1011, 1).add(1379, 1).add(556, 50).add(558, 50).add(557, 50).add(555, 50).add(1351, 1).add(1265, 1).add(1712, 1).add(11118, 1).add(1007, 1).add(1061, 1).add(1419, 1);
			
        	//player.setReceivedStarter(true);
        }
		//DialogueManager.start(player, 177);
        player.getPacketSender().sendMessage("@blu@Welcome to Morytania! We hope you enjoy your stay!");

        
        if (player.experienceLocked()) {
            player.getPacketSender().sendMessage("@red@Warning: your experience is currently locked.");
        }
        ClanChatManager.handleLogin(player);
        ClanChatManager.join(player, "help");

        if (GameSettings.BONUS_EXP) {
			player.getPacketSender().sendMessage("<img=10> <col=008FB2>Morytania currently has a bonus experience event going on, make sure to use it!");
        }
        if (WellOfWealth.isActive()) {
            player.getPacketSender().sendMessage("<img=10> @blu@The Well of Wealth is granting x2 Easier Droprates for another " + WellOfWealth.getMinutesRemaining() + " minutes.");
        }
        if (WellOfGoodwill.isActive()) {
            player.getPacketSender().sendMessage("<img=10> @blu@The Well of Goodwill is granting 30% Bonus xp for another " + WellOfGoodwill.getMinutesRemaining() + " minutes.");
        }
        PlayerPanel.refreshPanel(player);

    	//New player
		if(player.newPlayer()) {
			StartScreen.open(player);
			player.setPlayerLocked(true);
		}

        player.getPacketSender().updateSpecialAttackOrb().sendIronmanMode(player.getGameMode().ordinal());

        if (player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR || player.getRights() == PlayerRights.DEVELOPER) {
            World.sendMessage("<img="+player.getRights().ordinal()+"><col=6600CC> " + Misc.formatText(player.getRights().toString().toLowerCase()) + " " + player.getUsername() + " has just logged in, feel free to message them for support.");
        }
        if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR ||  player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER) {
			if (!StaffList.staff.contains(player.getUsername())) {
				StaffList.login(player);
			}
		}
        GrandExchange.onLogin(player);

        if (player.getPointsHandler().getAchievementPoints() == 0) {
            Achievements.setPoints(player);
        }
        
        if(player.getPlayerOwnedShopManager().getEarnings() > 0) {
        	player.sendMessage("<col=FF0000>You have unclaimed earnings in your player owned shop!");
        }

        PlayerLogs.log(player.getUsername(), "Login from host " + player.getHostAddress() + ", serial number: " + player.getSerialNumber());
    }

    public static boolean handleLogout(Player player) {
        try {

            PlayerSession session = player.getSession();

            if (session.getChannel().isOpen()) {
                session.getChannel().close();
            }

            if (!player.isRegistered()) {
                return true;
            }

            boolean exception = GameServer.isUpdating() || World.getLogoutQueue().contains(player) && player.getLogoutTimer().elapsed(600000);
            if (player.logout() || exception) {
            	//new Thread(new HighscoresHandler(player)).start();
                System.out.println("[World] Deregistering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
                player.getSession().setState(SessionState.LOGGING_OUT);
                ConnectionHandler.remove(player.getHostAddress());
                player.setTotalPlayTime(player.getTotalPlayTime() + player.getRecordedLogin().elapsed());
                player.getPacketSender().sendInterfaceRemoval();
                if (player.getCannon() != null) {
                    DwarfMultiCannon.pickupCannon(player, player.getCannon(), true);
                }
                if (exception && player.getResetPosition() != null) {
                    player.moveTo(player.getResetPosition());
                    player.setResetPosition(null);
                }
                if (player.getRegionInstance() != null) {
                    player.getRegionInstance().destruct();
                }
                
                
                
                
                if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR ||  player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER) {
					StaffList.logout(player);
				}
                Hunter.handleLogout(player);
                Locations.logout(player);
                player.getSummoning().unsummon(false, false);
                player.getFarming().save();
                player.getPlayerOwnedShopManager().unhookShop();
                BountyHunter.handleLogout(player);
                ClanChatManager.leave(player, false);
                player.getRelations().updateLists(false);
                PlayersOnlineInterface.remove(player);
                TaskManager.cancelTasks(player.getCombatBuilder());
                TaskManager.cancelTasks(player);
                player.save();
                World.getPlayers().remove(player);
                session.setState(SessionState.LOGGED_OUT);
                World.updatePlayersOnline();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
