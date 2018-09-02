package com.arlania.model.input.impl;

import java.sql.PreparedStatement;

import com.arlania.GameSettings;
import com.arlania.model.input.Input;
import com.arlania.util.NameUtils;
import com.arlania.world.entity.impl.player.Player;

import mysql.MySQLController;
import mysql.MySQLDatabase;
import mysql.MySQLController.Database;

public class ChangePassword extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		player.getPacketSender().sendInterfaceRemoval();
		if(!GameSettings.MYSQL_ENABLED) {
			player.getPacketSender().sendMessage("This service is currently unavailable.");
			return;
		}
		if(syntax == null || syntax.length() <= 2 || syntax.length() > 15 || !NameUtils.isValidName(syntax)) {
			player.getPacketSender().sendMessage("That password is invalid. Please try another password.");
			return;
		}
		if(syntax.contains("_")) {
			player.getPacketSender().sendMessage("Your password can not contain underscores.");
			return;
		}
		if(player.getBankPinAttributes().hasBankPin()) {
			player.getPacketSender().sendMessage("Please visit the nearest bank and enter your pin before doing this.");
			return;
		}

		
		
			player.setPassword(syntax);
			player.getPacketSender().sendMessage("Your account's password is now: "+syntax);
		
	}
}
