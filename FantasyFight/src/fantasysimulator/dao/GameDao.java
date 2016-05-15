package fantasysimulator.dao;

import java.util.List;

import javax.xml.bind.JAXBException;

import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.Games;
import fantasysimulator.pojo.Player;

public interface GameDao {
	public Games getAllGame() throws JAXBException;
	
	public Game getGameById(String id, Games games);

//	public Games saveGame(Games games, Game game);

	public void saveGames(Games games) throws JAXBException;

	public void saveMatch(Games games,String fileName) throws JAXBException;
	
	public Games loadMatch() throws JAXBException;

	public List<Player> makeTurnPlayers(Player playerOne, Player playerTwo);

	public int getTargetHit(int attack, double defense);

	public int getCriticTargetHit(int hitTarget);

	Games loadMatch(String fileName) throws JAXBException;

	void saveMatch(Games games) throws JAXBException;

	void saveGames(Games games, String fileName) throws JAXBException;

	Games getAllGame(String fileName) throws JAXBException;
}
