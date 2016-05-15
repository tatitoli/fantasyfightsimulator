package fantasysimulator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fantasysimulator.daoimp.GameDaoImp;
import fantasysimulator.daoimp.PlayerDaoImp;
import fantasysimulator.pojo.Character;
import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.Games;
import fantasysimulator.pojo.Player;
import fantasysimulator.pojo.Players;

public class GameDaoTest {

	List<Player> turnPlayers;
	Games games = new Games();
	Game game = new Game();
	Players players = new Players();
	Player player;
	Player playerTwo;
	Character character;
	Character characterTwo;
	PlayerDaoImp playerDaoImp = new PlayerDaoImp();
	GameDaoImp gameDaoImp = new GameDaoImp();
	
	@Before
	public void setUp() throws Exception {
		player = new Player();
		playerTwo = new Player();
		characterTwo = new Character();
		characterTwo.setRace("Ember");
		character = new Character();
		character.setRace("Elf");
		character.setStr(8);
		character.setDex(6.0);
		character.setAttackPoints(8);
		character.setDefensePoints(6);
		character.setMovingPoints(playerDaoImp.getCalculateMovingPoints("Elf", 6.0));
		character.setHealPoints(playerDaoImp.getHealthPoints("Elf", 8));
		characterTwo.setStr(7);
		characterTwo.setDex(7.0);
		characterTwo.setAttackPoints(7);
		characterTwo.setDefensePoints(7);
		characterTwo.setMovingPoints(playerDaoImp.getCalculateMovingPoints("Ember", 7.0));
		characterTwo.setHealPoints(playerDaoImp.getHealthPoints("Ember", 7));
		player.setCharacter(character);
		player.setId("0");
		playerTwo.setCharacter(characterTwo);
		player.setId("1");
		players.add(player);
		players.add(playerTwo);
		game.setPlayers(players);
		game.setId("0");
		game.setWhoWin("NoOne");
		games.add(game);
	}
	
	@Test
	public void testGetTargetHit() {
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0)==100, false);
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0)==99, true);
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0)==98, false);
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0)==84, false);
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0)==85, true);
		assertEquals(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0)==86, false);
	}
	
	@Test
	public void testGetCriticTargetHit() {
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0))==13, false);
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0))==14, true);
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 1.0))==15, false);
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0))==11, false);
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0))==12, true);
		assertEquals(gameDaoImp.getCriticTargetHit(gameDaoImp.getTargetHit(player.getCharacter().getStr(), 5.0))==13, false);
	}
	
	@Test
	public void testSaveGame(){
		try {
			gameDaoImp.saveGames(games,"testGame.xml");
		} catch (JAXBException e) {
			fail("Hiba a mentés során!");
		}
	}
	
	@After
	public void testGetAllGame(){
		try {
			games = gameDaoImp.getAllGame("testGame.xml");
		} catch (JAXBException e) {
			fail("Hiba a beolvasás során!");
		}
	}
	
	
	@Test
	public void testSaveMatch(){
		try {
			gameDaoImp.saveMatch(games,"testActualGame.xml");
		} catch (JAXBException e) {
			fail("Hiba a mentés során!");
		}
	}
	
	@After
	public void testLoadMatch(){
		try {
			games = gameDaoImp.loadMatch("testActualGame.xml");
		} catch (JAXBException e) {
			fail("Hiba a beolvasás során!");
		}
	}
	
	@Test
	public void testGameById(){
		assertEquals(gameDaoImp.getGameById("0", games).equals(game), true);
		assertEquals(gameDaoImp.getGameById("1", games).equals(game), false);
	}
	
	@Test
	public void testTurnPlayers(){
		turnPlayers = gameDaoImp.makeTurnPlayers(player, playerTwo);
		assertEquals(turnPlayers.get(0).equals(player), true);
		assertEquals(turnPlayers.get(0).equals(playerTwo), false);
	}
}
