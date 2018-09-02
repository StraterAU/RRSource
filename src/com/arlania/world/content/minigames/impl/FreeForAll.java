package com.arlania.world.content.minigames.impl;

import com.arlania.GameSettings;
import com.arlania.model.Flag;
import com.arlania.model.Item;
import com.arlania.model.MagicSpellbook;
import com.arlania.model.Position;
import com.arlania.model.Prayerbook;
import com.arlania.model.Skill;
import com.arlania.model.container.impl.Equipment;
import com.arlania.net.packet.impl.EquipPacketListener;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.BonusManager;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.skill.SkillManager;
import com.arlania.world.content.skill.SkillManager.Skills;
import com.arlania.world.entity.impl.player.Player;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author _Lev518
 *
 */

public class FreeForAll {
	public static int TOTAL_PLAYERS = 0;
	private static int PLAYERS_IN_LOBBY = 0;

	/**
	 * @note Stores player and State
	 */
	public static Map<Player, String> playerMap = new HashMap<Player, String>();
	public static Map<Player, String> playersInGame = new HashMap<Player, String>();
	public static final String PLAYING = "PLAYING";
	public static final String WAITING = "WAITING";
	public String type = "";
	public static boolean pure = false;
	public int[][] pureInv = new int[][] { { Equipment.HEAD_SLOT, 1153 }, { Equipment.CAPE_SLOT, 10499 },
			{ Equipment.AMULET_SLOT, 1725 }, { Equipment.WEAPON_SLOT, 4587 }, { Equipment.BODY_SLOT, 1129 },
			{ Equipment.SHIELD_SLOT, 1540 }, { Equipment.LEG_SLOT, 2497 }, { Equipment.HANDS_SLOT, 7459 },
			{ Equipment.FEET_SLOT, 3105 }, { Equipment.RING_SLOT, 2550 }, { Equipment.AMMUNITION_SLOT, 9244 } };
	public static boolean brid = false;
	public static boolean dharok = false;
	private static boolean gameRunning = false;
	private static boolean eventRunning = false;
	private static int waitTimer = 150;
	public static int[][] coordinates = { { 2265, 4684, 4 }, { 2261, 4699, 4 }, { 2282, 4706, 4 }, { 2282, 4689, 4 } };

	public static String getState(Player player) {
		return playerMap.get(player);
	}

	public static void saveOldStats(Player player) {
		Skills currentSkills = player.getSkillManager().getSkills();
		player.oldSkillLevels = currentSkills.level;
		player.oldSkillXP = currentSkills.experience;
		player.oldSkillMaxLevels = currentSkills.maxLevel;
	}

	public static void startGame() {
		for (Player player : playerMap.keySet()) {
			eventRunning = false;
			gameRunning = true;
			player.getPA().closeAllWindows();
			saveOldStats(player);
			player.getSkillManager().newSkillManager();
			updateSkills(player);
			if (pure) {
				player.getSkillManager().setMaxLevel(Skill.ATTACK, 40).setMaxLevel(Skill.STRENGTH, 99)
						.setMaxLevel(Skill.RANGED, 99).setMaxLevel(Skill.MAGIC, 99)
						.setMaxLevel(Skill.CONSTITUTION, 990);
				for (Skill skill : Skill.values()) {
					player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
							.setExperience(skill,
									SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
				}
				player.getInventory().add(1319, 1);
				player.getInventory().add(2440, 1);
				player.getInventory().add(2444, 1);
				player.getInventory().add(379, 25);
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(1169, 1));
				player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(890, 1000));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(15345, 1));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(1725, 1));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(853, 1));
				player.getEquipment().set(Equipment.BODY_SLOT, new Item(1129, 1));
				player.getEquipment().set(Equipment.LEG_SLOT, new Item(1099, 1));
				player.getEquipment().set(Equipment.HANDS_SLOT, new Item(1065, 1));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(1061, 1));
				player.setPrayerbook(Prayerbook.NORMAL);
				player.getEquipment().refreshItems();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				EquipPacketListener.resetWeapon(player);
				BonusManager.update(player);
			} else if (brid) {
				player.getSkillManager().setMaxLevel(Skill.ATTACK, 99).setMaxLevel(Skill.STRENGTH, 99)
						.setMaxLevel(Skill.RANGED, 99).setMaxLevel(Skill.MAGIC, 99).setMaxLevel(Skill.DEFENCE, 99)
						.setMaxLevel(Skill.PRAYER, 990).setMaxLevel(Skill.CONSTITUTION, 990);
				for (Skill skill : Skill.values()) {
					player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
							.setExperience(skill,
									SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
				}
				player.getInventory().add(4151, 1);
				player.getInventory().add(4722, 1);
				player.getInventory().add(19111, 1);
				player.getInventory().add(14484, 1);
				player.getInventory().add(10551, 1);
				player.getInventory().add(11732, 1);
				player.getInventory().add(13262, 1);
				player.getInventory().add(4736, 1);
				player.getInventory().add(2436, 1);
				player.getInventory().add(2440, 1);
				player.getInventory().add(3024, 2);
				player.getInventory().add(385, 13);
				player.getInventory().add(560, 20000);
				player.getInventory().add(565, 20000);
				player.getInventory().add(555, 20000);
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(10828, 1));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(10636, 1));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585, 1));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(15486, 1));
				player.getEquipment().set(Equipment.BODY_SLOT, new Item(4712, 1));
				player.getEquipment().set(Equipment.LEG_SLOT, new Item(4714, 1));
				player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(6889, 1));
				player.getEquipment().set(Equipment.HANDS_SLOT, new Item(7462, 1));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(6920, 1));
				player.getEquipment().set(Equipment.RING_SLOT, new Item(2550, 1));
				player.setSpellbook(MagicSpellbook.ANCIENT);
				player.setPrayerbook(Prayerbook.NORMAL);
				player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB,
						player.getSpellbook().getInterfaceId());
				player.getEquipment().refreshItems();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				EquipPacketListener.resetWeapon(player);
				BonusManager.update(player);
				
			} else if (dharok) {
				player.getSkillManager().setMaxLevel(Skill.ATTACK, 99).setMaxLevel(Skill.STRENGTH, 99)
						.setMaxLevel(Skill.RANGED, 99).setMaxLevel(Skill.MAGIC, 99).setMaxLevel(Skill.DEFENCE, 99)
						.setMaxLevel(Skill.PRAYER, 990).setMaxLevel(Skill.CONSTITUTION, 990);
				for (Skill skill : Skill.values()) {
					player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
							.setExperience(skill,
									SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
				}
				player.getInventory().add(14484, 1);
				player.getInventory().add(2436, 1);
				player.getInventory().add(2440, 1);
				player.getInventory().add(6685, 1);
				player.getInventory().add(4718, 1);
				player.getInventory().add(3024, 2);
				player.getInventory().add(6685, 1);
				player.getInventory().add(385, 17);
				player.getInventory().add(560, 20000);
				player.getInventory().add(9075, 20000);
				player.getInventory().add(557, 20000);
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(4716, 1));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(19111, 1));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585, 1));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(4151, 1));
				player.getEquipment().set(Equipment.BODY_SLOT, new Item(4720, 1));
				player.getEquipment().set(Equipment.LEG_SLOT, new Item(4722, 1));
				player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13262, 1));
				player.getEquipment().set(Equipment.HANDS_SLOT, new Item(7462, 1));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(11732, 1));
				player.getEquipment().set(Equipment.RING_SLOT, new Item(6737, 1));
				player.setSpellbook(MagicSpellbook.LUNAR);
				player.setPrayerbook(Prayerbook.NORMAL);
				player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB,
						player.getPrayerbook().getInterfaceId());
				player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB,
						player.getSpellbook().getInterfaceId());
				player.getEquipment().refreshItems();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				EquipPacketListener.resetWeapon(player);
				BonusManager.update(player);
			}
			movePlayerToArena(player);
			player.inFFALobby = false;
			player.inFFA = true;
			player.getPacketSender().sendInteractionOption("Attack", 2, true);
			waitTimer = 100;
		}
	}

	public static void removePlayer(Player c) {
		playerMap.remove(c);
	}

	public static boolean checkEndGame() {
		if (gameRunning) {

			if (playerMap.size() <= 1) {
				return true;
			}
		}
		return false;
	}

	public static void sequence() {
		if (gameRunning) {

			if (checkEndGame()) {
				endGame();
				return;
			}
			return;
		}

		if (!eventRunning)
			return;

		if (waitTimer > 0) {
			waitTimer--;
			if (waitTimer % 100 == 0 && waitTimer > 0)
				World.sendMessage("@or2@[FFA] " + waitTimer + " seconds until FFA starts!" + " Join now @ ::ffa");
		}
		updateGameInterface();
		if (waitTimer <= 0) {
			if (!gameRunning)
				startGame();
		}
	}

	private static void updateGameInterface() {
		for (Player p : playerMap.keySet()) {
			if (p == null)
				continue;

			String state = getState(p);
			if (state != null && state.equals(WAITING)) {
				p.getPacketSender().sendString(21006, "Time till start: " + waitTimer + "");
				p.getPacketSender().sendString(21007, "Players Ready: " + PLAYERS_IN_LOBBY + "");
				p.getPacketSender().sendString(21009, "");
			}
		}
	}

	public static boolean checkItems(Player c) {
		if (c.getInventory().getFreeSlots() != 28) {
			return false;
		}
		for (int i = 0; i < 14; i++) {
			if (c.getEquipment().get(i).getId() > 0)
				return false;
		}
		return true;
	}

	public static void startEvent(String type) {
		pure = false;
		brid = false;
		dharok = false;
		if (!eventRunning) {
			World.sendMessage("@or2@[FFA] A " + type + " FFA event has been started! Type ::ffa to join!");
			if (type == "pure") {
				pure = true;
			} else if (type == "brid") {
				brid = true;

			} else {
				dharok = true;
			}
			eventRunning = true;
		}
	}

	private static void movePlayerToArena(Player p) {
		int random = Misc.random(3);
		p.moveTo(new Position(coordinates[random][0], coordinates[random][1], coordinates[random][2]));
		PLAYERS_IN_LOBBY--;
	}

	public static void enterLobby(Player c) {
		if (!eventRunning) {
			c.sendMessage("There is no game available right now!");
			return;
		}
		if (c.getSummoning().getSpawnTask() != null) {
			c.sendMessage("You can not join ffa with a pet!");
			return;
		}
		if (getState(c) == null) {
			if (checkItems(c)) {
				c.getPA().closeAllWindows();
				playerMap.put(c, WAITING);
				TOTAL_PLAYERS++;
				PLAYERS_IN_LOBBY++;
				c.inFFALobby = true;
				c.moveTo(new Position(2223, 3799, 0));
				c.sendMessage("Welcome to FFA!");
			} else {
				c.sendMessage("Bank all your items to play FFA!");
			}
		}
	}

	public static void updateSkills(Player player) {
		for (Skill skill : Skill.values())
			player.getSkillManager().updateSkill(skill);
	}

	public static void endGame() {
		eventRunning = false;
		gameRunning = false;
		pure = false;
		brid = false;
		dharok = false;
		for (Player p : playerMap.keySet()) {
			p.sendMessage("You have won the game! PM a admin or owner for your reward!");
			World.sendMessage(
					"@or2@[FFA] @blu@" + Misc.formatPlayerName(p.getUsername()) + " @or2@has just won the FFA game!");
			leaveGame(p);
		}
		playerMap.clear();
	}

	public static void leaveGame(Player c) {
		c.getInventory().deleteAll();
		c.getEquipment().deleteAll();
		playerMap.remove(c);
		c.getSkillManager().getSkills().level = c.oldSkillLevels;
		c.getSkillManager().getSkills().experience = c.oldSkillXP;
		c.getSkillManager().getSkills().maxLevel = c.oldSkillMaxLevels;
		c.getUpdateFlag().flag(Flag.ANIMATION);
		c.getEquipment().refreshItems();
		updateSkills(c);
		c.moveTo(GameSettings.DEFAULT_POSITION.copy());
		c.inFFA = false;
		c.getPacketSender().sendInteractionOption("null", 2, true);
		c.sendMessage("Thank you for participating in FFA!");
	}
}