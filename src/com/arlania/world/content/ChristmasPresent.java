package com.arlania.world.content;

import com.arlania.util.RandomUtility;
import com.arlania.world.entity.impl.player.Player;

public class ChristmasPresent {


	public static void openBox (Player player) {
		player.getInventory().delete(6542, 1);
		
		if (RandomUtility.getRandom(10) == 5) {
			/*
			 * Landing on 5 and recieve reward
			 */
			player.getInventory().add(1050, 1);
			player.getPacketSender().sendMessage("Congratulations you recieved the santa hat");
			
		} else {
			/*
			 * Not landing on 5
			 */
			player.getInventory().add(995, 1000000 + RandomUtility.getRandom(50000000));
			player.getPacketSender().sendMessage("Sorry, you didn't get the santa hat. Try again");
		}
	}



}
