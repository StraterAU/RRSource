package com.arlania.model.input.impl;

import java.sql.PreparedStatement;

import com.arlania.GameSettings;
import com.arlania.model.input.Input;
import com.arlania.util.NameUtils;
import com.arlania.world.entity.impl.player.Player;

import mysql.MySQLController;
import mysql.MySQLDatabase;
import mysql.MySQLController.Database;

public class PosInput extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		player.getPacketSender().sendClientRightClickRemoval();
		player.getPlayerOwnedShopManager().updateFilter(syntax);
		
	}
}
