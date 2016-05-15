package fantasysimulator.view;

import javax.xml.bind.JAXBException;

import fantasysimulator.daoimp.GameDaoImp;
import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.GameTable;
import fantasysimulator.pojo.Games;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ScoresController {
	private ObservableList<GameTable> gameDataList = FXCollections.observableArrayList();
	private Games gamesList = new Games();
	private Game game;
	private GameDaoImp gameDaoImp = new GameDaoImp();

	private Stage stage;

	@FXML
	private TableView<GameTable> gameTable;

	@FXML
	private Label hitTargetOne;

	@FXML
	private Label hitTargetTwo;

	@FXML
	private Label hitCriticTargetOne;

	@FXML
	private Label hitCriticTargetTwo;

	@FXML
	private Label nameOne;

	@FXML
	private Label nameTwo;

	@FXML
	private Label hpOne;

	@FXML
	private Label hpTwo;

	@FXML
	private Label mpOne;

	@FXML
	private Label mpTwo;

	@FXML
	private Label attackOne;

	@FXML
	private Label attackTwo;

	@FXML
	private Label defenseOne;

	@FXML
	private Label defenseTwo;

	@FXML
	private Label strOne;

	@FXML
	private Label strTwo;

	@FXML
	private Label dexOne;

	@FXML
	private Label dexTwo;

	@FXML
	private TableColumn<GameTable, String> playerOneOszlop;

	@FXML
	private TableColumn<GameTable, String> playerTwoOszlop;

	@FXML
	private TableColumn<GameTable, String> whoWhin;

	public void loadTable() {
		GameDaoImp gameDao = new GameDaoImp();
		try {
			gamesList = gameDao.getAllGame("game.xml");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (gamesList.getGames() != null) {
			for (int i = 0; i < gamesList.getGames().size(); i++) {
				game = gamesList.getGames().get(i);
				gameDataList.add(new GameTable(game.getId(), game.getPlayers().getPlayers().get(0).getName(),
						game.getPlayers().getPlayers().get(1).getName(), game.getWhoWin()));
			}
		}
		gameTable.setItems(getGameDataList());
	}

	public ScoresController() {
	}

	public ObservableList<GameTable> getGameDataList() {
		return gameDataList;
	}

	@FXML
	private void initialize() {
		loadTable();
		playerOneOszlop.setCellValueFactory(cd -> cd.getValue().getPlayerOneProperty());
		playerTwoOszlop.setCellValueFactory(cd -> cd.getValue().getPlayerTwoProperty());
		whoWhin.setCellValueFactory(cd -> cd.getValue().getWhoProperty());

		// showGameData(null);

		gameTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showGameData(newValue));
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void showGameData(GameTable game) {
		if (game != null) {
			Game actualGame = gameDaoImp.getGameById(game.getId(), gamesList);
			nameOne.setText(actualGame.getPlayers().getPlayers().get(0).getName());
			nameTwo.setText(actualGame.getPlayers().getPlayers().get(1).getName());
			attackOne.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(0).getCharacter().getStr()));
			attackTwo.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(1).getCharacter().getStr()));
			defenseOne.setText(String.valueOf((int)actualGame.getPlayers().getPlayers().get(0).getCharacter().getDex()));
			defenseTwo.setText(String.valueOf((int)actualGame.getPlayers().getPlayers().get(1).getCharacter().getDex()));
			hpOne.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(0).getCharacter().getHealPoints()));
			hpTwo.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(1).getCharacter().getHealPoints()));
			mpOne.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(0).getCharacter().getMovingPoints()));
			mpTwo.setText(String.valueOf(actualGame.getPlayers().getPlayers().get(1).getCharacter().getMovingPoints()));
			hitTargetOne.setText(String.valueOf(gameDaoImp.getTargetHit(actualGame.getPlayers().getPlayers().get(0).getCharacter().getStr(), actualGame.getPlayers().getPlayers().get(1).getCharacter().getDex())));
			hitTargetTwo.setText(String.valueOf(gameDaoImp.getTargetHit(actualGame.getPlayers().getPlayers().get(1).getCharacter().getStr(), actualGame.getPlayers().getPlayers().get(0).getCharacter().getDex())));
			hitCriticTargetOne.setText(String.valueOf(gameDaoImp.getCriticTargetHit(Integer.parseInt(hitTargetOne.getText()))));
			hitCriticTargetTwo.setText(String.valueOf(gameDaoImp.getCriticTargetHit(Integer.parseInt(hitTargetTwo.getText()))));
		}
	}
}
