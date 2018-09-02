package com.arlania.net.packet.impl;

import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.world.content.MoneyPouch;
import com.arlania.world.entity.impl.player.Player;

public class WithdrawMoneyFromPouchPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int amount = packet.readInt();
		if(player.getTrading().inTrade() || player.getDueling().inDuelScreen) {
			player.getPacketSender().sendMessage("You cannot withdraw money at the moment.");
		} else {
			MoneyPouch.withdrawMoney(player, amount);
		}
	}

}
