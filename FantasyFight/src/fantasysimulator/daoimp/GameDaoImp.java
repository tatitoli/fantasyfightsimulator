package fantasysimulator.daoimp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fantasysimulator.dao.GameDao;
import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.Games;
import fantasysimulator.pojo.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameDaoImp implements GameDao {
	private ObservableList<Game> gameList = FXCollections.observableArrayList();

	public ObservableList<Game> getGameList() {
		return gameList;
	}

	public void setGameList(ObservableList<Game> gameList) {
		this.gameList = gameList;
	}

	private int[][] hitTable = new int[][]{
		  { 45, 25, 5, 5, 5, 1, 1, 1, 1},
		  { 85, 55, 45, 35, 25, 15, 5, 5, 5},
		  { 95, 65, 55, 45, 35, 25, 15, 5, 5 },
		  { 95, 75, 65, 55, 45, 35, 25, 15, 5},
		  { 95, 85, 75, 65, 55, 45, 35, 25, 15},
		  { 99, 95, 85, 75, 65, 55, 45, 35, 25},
		  { 99, 95, 95, 85, 75, 65, 55, 45, 35},
		  { 99, 95, 95, 95, 85, 75, 65, 55, 45},
		  { 99, 95, 95, 95, 95, 85, 75, 65, 55},
		};
		
	
	@Override
	public Games getAllGame() throws JAXBException {
		File file = new File(System.getProperty("user.home"), "game.xml");
		Games readGames;
		if (file.exists()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readGames = (Games) jaxbUnmarshaller.unmarshal(file);
        } else {
            readGames = new Games();
        }
		return readGames;
	}
	
	@Override
	public Games getAllGame(String fileName) throws JAXBException {
		File file = new File(System.getProperty("user.home"), fileName);
		Games readGames;
		if (file.exists()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readGames = (Games) jaxbUnmarshaller.unmarshal(file);
        } else {
            readGames = new Games();
        }
		return readGames;
	}

//	@Override
//	public Games saveGame(Games games, Game game) {
//		games.add(game);
//		return games;
//	}

	@Override
	public List<Player> makeTurnPlayers(Player playerOne, Player playerTwo) {
		List<Player> turnByPlayers =  new ArrayList<>();
		double oneMove=playerOne.getCharacter().getMovingPoints();
		double twoMove=playerTwo.getCharacter().getMovingPoints();
		for(int i=0;i<100;i++){
			if(oneMove<=twoMove){
				turnByPlayers.add(playerTwo);
				oneMove+=playerOne.getCharacter().getMovingPoints();
			}else if(oneMove>=twoMove){
				turnByPlayers.add(playerOne);
				twoMove+=playerTwo.getCharacter().getMovingPoints();
			}
		}
		return turnByPlayers;
	}

	@Override
	public int getTargetHit(int attack, double defense) {
		return hitTable[attack-1][((int) defense)-1];
	}

	@Override
	public int getCriticTargetHit(int hitTarget) {
		return (int) (hitTarget*0.15);
	}

	@Override
	public void saveMatch(Games games) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Games.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

		jaxbMarshaller.marshal( games, new File( System.getProperty("user.home"), "actualGame.xml" ) );
//		jaxbMarshaller.marshal( games, System.out );
	}

	@Override
	public void saveGames(Games games) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Games.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

		jaxbMarshaller.marshal( games, new File( System.getProperty("user.home"), "game.xml" ) );
//		jaxbMarshaller.marshal( games, System.out );
	}
	
	@Override
	public void saveGames(Games games, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Games.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

		jaxbMarshaller.marshal( games, new File( System.getProperty("user.home"), fileName ) );
//		jaxbMarshaller.marshal( games, System.out );
	}

	@Override
	public Game getGameById(String id, Games games) {
		Game game =  new Game();
		for( int i=0; i<games.getGames().size();i++){
			if(id.equals(games.getGames().get(i).getId())){
				game = games.getGames().get(i);
				break;
			}
		}
		return game;
	}

	@Override
	public Games loadMatch(String fileName) throws JAXBException {
		File file = new File(System.getProperty("user.home"), fileName);
		Games readGames;
		if (file.exists()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readGames = (Games) jaxbUnmarshaller.unmarshal(file);
        } else {
            readGames = new Games();
        }
		return readGames;
	}

	@Override
	public Games loadMatch() throws JAXBException {
		File file = new File(System.getProperty("user.home"), "actualGame.xml");
		Games readGames;
		if (file.exists()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readGames = (Games) jaxbUnmarshaller.unmarshal(file);
        } else {
            readGames = new Games();
        }
		return readGames;
	}

	@Override
	public void saveMatch(Games games, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Games.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

		jaxbMarshaller.marshal( games, new File( System.getProperty("user.home"), fileName ) );
//		jaxbMarshaller.marshal( games, System.out );
	}
}
