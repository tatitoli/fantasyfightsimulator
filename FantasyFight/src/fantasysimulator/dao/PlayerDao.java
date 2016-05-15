package fantasysimulator.dao;

import fantasysimulator.pojo.Player;
import fantasysimulator.pojo.Players;

public interface PlayerDao {
	public Player getPlayer(Players players, String Id);

	public double getCalculateMovingPoints(String race, double dex);

	public int getHealthPoints(String race, int str);

	public void updatePlayer(Player player, String hp);
}
