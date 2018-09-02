package com.arlania;

import java.math.BigInteger;

import com.arlania.model.Position;
import com.arlania.net.security.ConnectionHandler;

public class GameSettings {
	
	
	/**
	 * Dzone activation
	 */
	public static boolean DZONEON = false;
	
	/**
	 * The game port
	 */
	public static final int GAME_PORT = 43594;

	/**
	 * The game version
	 */
	public static final int GAME_VERSION = 13;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGIN_THRESHOLD = 25;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGOUT_THRESHOLD = 50;
	
	/**
	 * The maximum amount of players who can receive rewards on a single game
	 * sequence.
	 */
	public static final int VOTE_REWARDING_THRESHOLD = 15;

	/**
	 * The maximum amount of connections that can be active at a time, or in
	 * other words how many clients can be logged in at once per connection.
	 * (0 is counted too)
	 */
	public static final int CONNECTION_AMOUNT = 2;

	/**
	 * The throttle interval for incoming connections accepted by the
	 * {@link ConnectionHandler}.
	 */
	public static final long CONNECTION_INTERVAL = 1000;

	/**
	 * The number of seconds before a connection becomes idle.
	 */
	public static final int IDLE_TIME = 15;
	
	/**
	 * The keys used for encryption on login
	 */
	public static final BigInteger RSA_MODULUS = new BigInteger("141038977654242498796653256463581947707085475448374831324884224283104317501838296020488428503639086635001378639378416098546218003298341019473053164624088381038791532123008519201622098961063764779454144079550558844578144888226959180389428577531353862575582264379889305154355721898818709924743716570464556076517");
	public static final BigInteger RSA_EXPONENT = new BigInteger("73062137286746919055592688968652930781933135350600813639315492232042839604916461691801305334369089083392538639347196645339946918717345585106278208324882123479616835538558685007295922636282107847991405620139317939255760783182439157718323265977678194963487269741116519721120044892805050386167677836394617891073");

	/**
	 * The maximum amount of messages that can be decoded in one sequence.
	 */
	public static final int DECODE_LIMIT = 30;
	
	/** GAME **/

	/**
	 * Processing the engine
	 */
	public static final int ENGINE_PROCESSING_CYCLE_RATE = 600;
	public static final int GAME_PROCESSING_CYCLE_RATE = 600;

	/**
	 * Are the MYSQL services enabled?
	 */
	public static boolean MYSQL_ENABLED = false;
	/**
	 * Is it currently bonus xp?
	 */
	public static boolean BONUS_EXP = false;//Misc.isWeekend();
	/**
	 * 
	 * The default position
	 */
	public static final Position DEFAULT_POSITION = new Position(3088, 3504);

	
	public static final int MAX_STARTERS_PER_IP = 2;
	
	/**
	 * Untradeable items
	 * Items which cannot be traded or staked
	 */
	public static final int[] UNTRADEABLE_ITEMS = 
		{
		11995, 11996, 11997, 11978, 12001, 12002, 12003, 12004, 12005, 12006, 11990, 11991, 11992, 11993, 11994, 11989, 11988, 11987, 11986, 11985, 11984, 11983, 11982, 11981, 11979, 20079, 20103, 20080, 20081, 20082, 20083, 14914, 20086, 14916, 20087, 20088, 20089, 20090, 20085,
		18349, 18351, 18353, 13262, 19634, 19635, 19642, 19643, 19644, 19711, 19712, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 
		14022, 14020, 14021, 6570, 14019, 20747, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 8839, 8840, 8841, 8842, 19711, 19712,
		};

	/**
	 * Unsellable items
	 * Items which cannot be sold to shops
	 */
	public static int UNSELLABLE_ITEMS[] = new int[] {
		18349, 18351, 18353, 995, 18349, 18351, 18353, 13262, 19634, 19635, 19642, 19643, 19644, 19711, 19712, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 6570, 14019, 20747, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 8839, 8840, 8841, 8842, 19711, 19712
	};

	public static final String[] INVALID_NAMES = { "mod", "moderator", "admin", "administrator", "owner", "developer",
			"supporter", "dev", "developer", "nigga", "0wn3r", "4dm1n", "m0d", "adm1n", "a d m i n", "m o d",
			"o w n e r" };

	public static final int 
	ATTACK_TAB = 0, 
	SKILLS_TAB = 1, 
	QUESTS_TAB = 2, 
	ACHIEVEMENT_TAB = 14,
	INVENTORY_TAB = 3, 
	EQUIPMENT_TAB = 4, 
	PRAYER_TAB = 5, 
	MAGIC_TAB = 6,

	SUMMONING_TAB = 13, 
	FRIEND_TAB = 8, 
	IGNORE_TAB = 9, 
	CLAN_CHAT_TAB = 7,
	LOGOUT = 10,
	OPTIONS_TAB = 11,
	EMOTES_TAB = 12;
}
