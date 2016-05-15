package fantasysimulator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fantasysimulator.daoimp.PlayerDaoImp;
import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.Games;
import fantasysimulator.pojo.Player;
import fantasysimulator.pojo.Players;
import fantasysimulator.pojo.Character;

public class PlayerDaoTest {

	Games games;
	Game game;
	Players players = new Players();
	Player player;
	Character character;
	PlayerDaoImp playerDaoImp = new PlayerDaoImp();
	
	
	@Before
	public void setUp() throws Exception {
		player = new Player();
		character = new Character();
		character.setRace("Elf");
		character.setStr(8);
		character.setDex(6.0);
		character.setAttackPoints(8);
		character.setDefensePoints(8);
		character.setMovingPoints(playerDaoImp.getCalculateMovingPoints("Elf", 8.0));
		character.setHealPoints(playerDaoImp.getHealthPoints("Elf", 8));
		player.setCharacter(character);
		player.setId("0");
		players.add(player);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(player != null);
		assertTrue(new Player("0", "Teszt", character)!=null);
	}
	
	@Test 
	public void testGetters(){
		player.setName("Noemi");
		assertEquals("Noemi", player.getName());
		player.setId("0123456789");
		assertEquals("0123456789", player.getId());
	}
	
	@Test
	public void testGetCalculateMovingPoints(){
		assertTrue(player.getCharacter().getMovingPoints()>=0);
		assertEquals(player.getCharacter().getMovingPoints()==16, true);
		assertEquals(player.getCharacter().getMovingPoints()==15, false);
	}
	
	@Test
	public void testGetHealthPoints(){
		assertTrue(player.getCharacter().getHealPoints()>=0);
		assertEquals(player.getCharacter().getHealPoints()==57, false);
		assertEquals(player.getCharacter().getHealPoints()==56, true);
		assertEquals(player.getCharacter().getHealPoints()==55, false);
	}
	
	@Test
	public void testGetPlayer(){
		assertTrue(players!=null);
		assertEquals(playerDaoImp.getPlayer(players, "0").equals(player), true);
		assertEquals(player.getCharacter().getHealPoints()==15, false);
	}
}
