package mysql.impl.Highscores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.arlania.model.PlayerRights;
import com.arlania.model.Skill;
import com.arlania.world.entity.impl.player.Player;
import com.mysql.jdbc.Statement;

/*public class HighscoresHandler implements Runnable{




	private Player player;

	public HighscoresHandler(Player player) {
		this.player = player;
	}


	@Override
	
	
  /* public void run() {
		
		if(player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER)
			return;

		final String username = player.getUsername();

		final int gameMode = 0;

		final long overallXp = player.getSkillManager().getTotalExp();
	
		final long attackXp = player.getSkillManager().getExperience(Skill.ATTACK);
	
		final long defenceXp = player.getSkillManager().getExperience(Skill.DEFENCE);
		
		final long strengthXp = player.getSkillManager().getExperience(Skill.STRENGTH);
		
		final long constitutionXp = player.getSkillManager().getExperience(Skill.CONSTITUTION);
		
		final long rangedXp = player.getSkillManager().getExperience(Skill.RANGED);
		
		final long prayerXp = player.getSkillManager().getExperience(Skill.PRAYER);
		
		final long magicXp = player.getSkillManager().getExperience(Skill.MAGIC);
		
		final long cookingXp = player.getSkillManager().getExperience(Skill.COOKING);
		
		final long woodcuttingXp = player.getSkillManager().getExperience(Skill.WOODCUTTING);
		
		final long fletchingXp = player.getSkillManager().getExperience(Skill.FLETCHING);
		
		final long fishingXp = player.getSkillManager().getExperience(Skill.FISHING);
		
		final long firemakingXp = player.getSkillManager().getExperience(Skill.FIREMAKING);
		
		final long craftingXp = player.getSkillManager().getExperience(Skill.CRAFTING);
		
		final long smithingXp = player.getSkillManager().getExperience(Skill.SMITHING);
		
		final long miningXp = player.getSkillManager().getExperience(Skill.MINING);
		
		final long herbloreXp = player.getSkillManager().getExperience(Skill.HERBLORE);
		
		final long agilityXp = player.getSkillManager().getExperience(Skill.AGILITY);
		
		final long thievingXp = player.getSkillManager().getExperience(Skill.THIEVING);
		
		final long slayerXp = player.getSkillManager().getExperience(Skill.SLAYER);
		
		final long farmingXp = player.getSkillManager().getExperience(Skill.FARMING);
		
		final long runecraftingXp = player.getSkillManager().getExperience(Skill.RUNECRAFTING);
		
		final long hunterXp = player.getSkillManager().getExperience(Skill.HUNTER);
		
		final long constructionXp = player.getSkillManager().getExperience(Skill.CONSTRUCTION);
		final long summoningXp = player.getSkillManager().getExperience(Skill.SUMMONING);
		final long dungXp = player.getSkillManager().getExperience(Skill.DUNGEONEERING);
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Connection connection = null;
		
		Statement stmt = null;

		
		// try {
		// 	connection = DriverManager.getConnection("jdbc:mysql://162.212.253.147:3306/simplic4_hiscores", "simplic4_aj", "ajw77wright");
		// } catch (SQLException e) {
		// 	e.printStackTrace();
		// 	return;
		// }
		
		if (connection != null) {
		    try {
		    	stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM `hs_users` WHERE username='" +username+ "'");
				if(rs.next()) {
					if(rs.getInt("count") > 0)  {
						stmt.executeUpdate("UPDATE `hs_users` SET overall_xp = '"+overallXp+"', attack_xp = '"+attackXp+"', defence_xp = '"+defenceXp+"', strength_xp = '"+strengthXp+"', constitution_xp = '"+constitutionXp+"', ranged_xp = '"+rangedXp+"', prayer_xp = '"+prayerXp+"', magic_xp = '"+magicXp+"', cooking_xp = '"+cookingXp+"', woodcutting_xp = '"+woodcuttingXp+"', fletching_xp = '"+fletchingXp+"', fishing_xp = '"+fishingXp+"', firemaking_xp = '"+firemakingXp+"', crafting_xp = '"+craftingXp+"', smithing_xp = '"+smithingXp+"', mining_xp = '"+miningXp+"', herblore_xp = '"+herbloreXp+"', agility_xp = '"+agilityXp+"', thieving_xp = '"+thievingXp+"', slayer_xp = '"+slayerXp+"', farming_xp = '"+farmingXp+"', runecrafting_xp = '"+runecraftingXp+"', hunter_xp = '"+hunterXp+"', construction_xp = '"+constructionXp+"', summoning_xp = '"+summoningXp+"', dungeoneering_xp = '"+dungXp+"' WHERE username = '"+username+"'");
					} else {
						stmt.executeUpdate("INSERT INTO `hs_users` (username, rights, overall_xp, attack_xp, defence_xp, strength_xp, constitution_xp, ranged_xp, prayer_xp, magic_xp, cooking_xp, woodcutting_xp, fletching_xp, fishing_xp, firemaking_xp, crafting_xp, smithing_xp, mining_xp, herblore_xp, agility_xp, thieving_xp, slayer_xp, farming_xp, runecrafting_xp, hunter_xp, construction_xp, summoning_xp, dungeoneering_xp) VALUES ('"+username+"', '"+gameMode+"', '"+overallXp+"', '"+attackXp+"', '"+defenceXp+"', '"+strengthXp+"', '"+constitutionXp+"', '"+rangedXp+"', '"+prayerXp+"', '"+magicXp+"', '"+cookingXp+"', '"+woodcuttingXp+"', '"+fletchingXp+"', '"+fishingXp+"', '"+firemakingXp+"', '"+craftingXp+"', '"+smithingXp+"', '"+miningXp+"', '"+herbloreXp+"', '"+agilityXp+"', '"+thievingXp+"', '"+slayerXp+"', '"+farmingXp+"', '"+runecraftingXp+"', '"+hunterXp+"', '"+constructionXp+"','"+summoningXp+"', '"+dungXp+"')");
					}
				}
				stmt.close();
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Failed to make connection!");
		}

		return;
	}
	
}*/
