package fantasysimulator.daoimp;

import fantasysimulator.dao.PlayerDao;
import fantasysimulator.pojo.Player;
import fantasysimulator.pojo.Players;

public class PlayerDaoImp implements PlayerDao {

	@Override
	public Player getPlayer(Players readPlayers, String id) {
		for (int i = 0; i < readPlayers.getPlayers().size(); i++) {
			if (readPlayers.getPlayers().get(i).getId().equals(id)) {
				return readPlayers.getPlayers().get(i);
			}
		}
		return null;
	}

	@Override
	public double getCalculateMovingPoints(String race, double dex) {
		if (race.equals("Elf")) {
			return dex * 2;
		} else if (race.equals("Ork")) {
			return dex * 1.2;
		} else if (race.equals("Gnóm")) {
			return dex * 1.1;
		} else if (race.equals("Törpe")) {
			return dex * 1.3;
		} else if (race.equals("Ember")) {
			return dex * 1.5;
		} else if (race.equals("Félszerzet")) {
			return dex * 1.6;
		}
		return 0;
	}

	@Override
	public int getHealthPoints(String race, int str) {
		if (race.equals("Elf")) {
			return str * 7;
		} else if (race.equals("Ork")) {
			return str * 11;
		} else if (race.equals("Gnóm")) {
			return str * 12;
		} else if (race.equals("Törpe")) {
			return str * 10;
		} else if (race.equals("Ember")) {
			return str * 9;
		} else if (race.equals("Félszerzet")) {
			return str * 8;
		}
		return 0;
	}

	@Override
	public void updatePlayer(Player player, String hp) {
		player.getCharacter().setHealPoints(Integer.parseInt(hp));
	}
}
