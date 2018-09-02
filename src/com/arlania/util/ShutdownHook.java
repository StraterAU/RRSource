package com.arlania.util;

import java.util.logging.Logger;

import com.arlania.GameServer;
import com.arlania.world.World;
import com.arlania.world.content.Scoreboards;
import com.arlania.world.content.WellOfGoodwill;
import com.arlania.world.content.WellOfWealth;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.grandexchange.GrandExchangeOffers;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerHandler;

public class ShutdownHook extends Thread {

	/**
	 * The ShutdownHook logger to print out information.
	 */
	private static final Logger logger = Logger.getLogger(ShutdownHook.class.getName());

	@Override
	public void run() {
		logger.info("The shutdown hook is processing all required actions...");
		//World.savePlayers();
		GameServer.setUpdating(true);
		for (Player player : World.getPlayers()) {
			if (player != null) {
			//	World.deregister(player);
				PlayerHandler.handleLogout(player);
			}
		}
		WellOfGoodwill.save();
		WellOfWealth.save();
		GrandExchangeOffers.save();
		ClanChatManager.save();
		logger.info("The shudown hook actions have been completed, shutting the server down...");
	}
}
