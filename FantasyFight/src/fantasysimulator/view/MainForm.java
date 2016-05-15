package fantasysimulator.view;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import fantasysimulator.daoimp.GameDaoImp;
import fantasysimulator.pojo.Game;
import fantasysimulator.pojo.GameTable;
import fantasysimulator.pojo.Games;
import fantasysimulator.pojo.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainForm extends Application {
	private ObservableList<GameTable> gameDataList = FXCollections.observableArrayList();
	private Games gamesList = new Games();
	private Game game = new Game();
	private Player playerOne = new Player();
	private Player playerTwo = new Player();
	private GameDaoImp gameDaoImp = new GameDaoImp();
	private List<Player> turnByPlayers;
	private boolean hasActualGame = false;

	public ObservableList<GameTable> getGameDataList() {
		return gameDataList;
	}

	@FXML
	Button loadButton;

	@FXML
	Button scoresButton;

	@FXML
	Button closeButton;

	@FXML
	Button newGameButton;

	public void refreshActualGame() {
		try {
			loadGame();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (gamesList.getGames() != null) {
			hasActualGame = true;
		}
	}

	@Override
	public void start(Stage primaryStage) throws JAXBException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainForm.class.getResource("MainForm.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fõoldal");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void closeActionButton(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void scoresButton(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainForm.class.getResource("ScoresForm.fxml"));
			AnchorPane score = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Korábbi meccsek");
			stage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(score);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void newGameActionButton(ActionEvent event) {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("CharMakerForm.fxml"));
			Scene scene = new Scene(root, 800, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Karakterek generálása");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void loadButtonAction(ActionEvent event) {
		refreshActualGame();
		if (hasActualGame) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CharMakerController.class.getResource("FightForm.fxml"));
			try {
				AnchorPane fightView = (AnchorPane) loader.load();
				Stage stage = new Stage();
				stage.setTitle("Harc");
				stage.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(fightView);
				stage.setScene(scene);
				FightController controller = loader.getController();
				controller.setInitPlayerOne(playerOne, playerTwo.getCharacter().getDex());
				controller.setInitPlayerTwo(playerTwo, playerOne.getCharacter().getStr());
				controller.setStage(stage);
				turnByPlayers = gameDaoImp.makeTurnPlayers(playerOne, playerTwo);
				controller.setTurnsList(turnByPlayers);
				stage.setResizable(false);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Figyelem");
			alert.setHeaderText(null);
			alert.setContentText("Nincs utolsó mentett állás!");
			alert.showAndWait();
		}
	}

	private void loadGame() throws JAXBException {
		gamesList = gameDaoImp.loadMatch("actualGame.xml");
		if (gamesList.getGames() != null) {
			playerOne = gamesList.getGames().get(0).getPlayers().getPlayers().get(0);
			playerTwo = gamesList.getGames().get(0).getPlayers().getPlayers().get(1);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
