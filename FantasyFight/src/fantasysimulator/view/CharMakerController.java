package fantasysimulator.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;

import fantasysimulator.daoimp.GameDaoImp;
import fantasysimulator.daoimp.PlayerDaoImp;
import fantasysimulator.pojo.Player;
import fantasysimulator.pojo.Character;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CharMakerController {
	
	private LocalDateTime localDateTime;
	NumberFormat formatter = new DecimalFormat("#0.00"); 
	private PlayerDaoImp playerDao =  new PlayerDaoImp();
	private GameDaoImp gameDao = new GameDaoImp();
	private List<Player> turnByPlayers;
	private Player playerOne;
	private Player playerTwo;
	
	@FXML
	private Button nextButton;
	
	@FXML
	private TextField nameOne;

	@FXML
	private TextField nameTwo;

	@FXML
	private ComboBox<String> raceOne;

	@FXML
	private ComboBox<String> raceTwo;

	@FXML
	private Label hasPointsOne;

	@FXML
	private Label hasPointsTwo;

	@FXML
	private Button strOnePlus;

	@FXML
	private Button strOneMinus;

	@FXML
	private Button strOne;

	@FXML
	private Button strTwoPlus;

	@FXML
	private Button strTwoMinus;

	@FXML
	private Button strTwo;

	@FXML
	private Button dexOnePlus;

	@FXML
	private Button dexOneMinus;

	@FXML
	private Button dexOne;

	@FXML
	private Button dexTwoPlus;

	@FXML
	private Button dexTwoMinus;

	@FXML
	private Button dexTwo;

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
	private void initialize() {
		strOne.setText("1");
		strTwo.setText("1");
		dexOne.setText("1");
		dexTwo.setText("1");
		attackOne.setText("1");
		attackTwo.setText("1");
		defenseOne.setText("1");
		defenseTwo.setText("1");
		hasPointsOne.setText("10");
		hasPointsTwo.setText("10");
		hpOne.setText("0");
		hpTwo.setText("0");
		mpOne.setText("0");
		mpTwo.setText("0");
		ObservableList<String> races = FXCollections.observableArrayList("Ork", "Elf", "Ember", "Törpe", "Félszerzet",
				"Gnóm");
		raceOne.setItems(races);
		raceTwo.setItems(races);
	}
	
	private String makeid(){
		String id = ""+localDateTime.now();
		id=id.replace("-", "").replace(":", "").replace(".", "");
		return id;
	}

	@FXML
	private void raceOneAction() {
		int number = Integer.parseInt(dexOne.getText());
		mpOne.setText(formatter.format(playerDao.getCalculateMovingPoints(raceOne.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
		number = Integer.parseInt(strOne.getText());
		hpOne.setText(String.valueOf(playerDao.getHealthPoints(raceOne.getSelectionModel().getSelectedItem(), number)));
	}

	@FXML
	private void strOnePlus() {
		int number = Integer.parseInt(strOne.getText());
		int hasPoints = Integer.parseInt(hasPointsOne.getText());
		if (number >= 9) {
			strOne.setText("9");
		} else if(hasPoints!=0){
			hasPoints--;
			number++;
			strOne.setText(String.valueOf(number));
			attackOne.setText(String.valueOf(number));
			hasPointsOne.setText(String.valueOf(hasPoints));
			if(raceOne.getSelectionModel().getSelectedItem()!=null){
				hpOne.setText(String.valueOf(playerDao.getHealthPoints(raceOne.getSelectionModel().getSelectedItem(), number)));
			}
		}
	}

	@FXML
	private void strOneMinus() {
		int number = Integer.parseInt(strOne.getText());
		int hasPoints = Integer.parseInt(hasPointsOne.getText());
		if (number <= 1) {
			strOne.setText("1");
		} else if(hasPoints!=10) {
			hasPoints++;
			number--;
			strOne.setText(String.valueOf(number));
			attackOne.setText(String.valueOf(number));
			hasPointsOne.setText(String.valueOf(hasPoints));
			if(raceOne.getSelectionModel().getSelectedItem()!=null){
				hpOne.setText(String.valueOf(playerDao.getHealthPoints(raceOne.getSelectionModel().getSelectedItem(), number)));
			}
		}
	}

	@FXML
	private void strTwoPlus() {
		int number = Integer.parseInt(strTwo.getText());
		int hasPoints = Integer.parseInt(hasPointsTwo.getText());
		if (number >= 9) {
			strTwo.setText("9");
		} else if(hasPoints!=0){
			hasPoints--;
			number++;
			strTwo.setText(String.valueOf(number));
			attackTwo.setText(String.valueOf(number));
			hasPointsTwo.setText(String.valueOf(hasPoints));
			if(raceTwo.getSelectionModel().getSelectedItem()!=null){
				hpTwo.setText(String.valueOf(playerDao.getHealthPoints(raceTwo.getSelectionModel().getSelectedItem(), number)));
			}
		}
	}

	@FXML
	private void strTwoMinus() {
		int number = Integer.parseInt(strTwo.getText());
		int hasPoints = Integer.parseInt(hasPointsTwo.getText());
		if (number <= 1) {
			strTwo.setText("1");
		} else if(hasPoints!=10) {
			hasPoints++;
			number--;
			strTwo.setText(String.valueOf(number));
			attackTwo.setText(String.valueOf(number));
			hasPointsTwo.setText(String.valueOf(hasPoints));
			if(raceTwo.getSelectionModel().getSelectedItem()!=null){
				hpTwo.setText(String.valueOf(playerDao.getHealthPoints(raceTwo.getSelectionModel().getSelectedItem(), number)));
			}
		}
	}

	@FXML
	private void dexOnePlus() {
		int number = Integer.parseInt(dexOne.getText());
		int hasPoints = Integer.parseInt(hasPointsOne.getText());
		if (number >= 9) {
			dexOne.setText("9");
		} else if(hasPoints!=0){
			hasPoints--;
			number++;
			dexOne.setText(String.valueOf(number));
			defenseOne.setText(String.valueOf(number));
			hasPointsOne.setText(String.valueOf(hasPoints));
			if(raceOne.getSelectionModel().getSelectedItem()!=null){
				mpOne.setText(formatter.format(playerDao.getCalculateMovingPoints(raceOne.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
			}
		}
	}

	@FXML
	private void dexTwoPlus() {
		int number = Integer.parseInt(dexTwo.getText());
		int hasPoints = Integer.parseInt(hasPointsTwo.getText());
		if (number >= 9) {
			dexTwo.setText("9");
		} else if(hasPoints!=0){
			hasPoints--;
			number++;
			dexTwo.setText(String.valueOf(number));
			defenseTwo.setText(String.valueOf(number));
			hasPointsTwo.setText(String.valueOf(hasPoints));
			if(raceTwo.getSelectionModel().getSelectedItem()!=null){
				mpTwo.setText(formatter.format(playerDao.getCalculateMovingPoints(raceTwo.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
			}
		}
	}

	@FXML
	private void dexTwoMinus() {
		int number = Integer.parseInt(dexTwo.getText());
		int hasPoints = Integer.parseInt(hasPointsTwo.getText());
		if (number <= 1) {
			dexTwo.setText("1");
		} else if(hasPoints!=10){
			hasPoints++;
			number--;
			dexTwo.setText(String.valueOf(number));
			defenseTwo.setText(String.valueOf(number));
			hasPointsTwo.setText(String.valueOf(hasPoints));
			if(raceTwo.getSelectionModel().getSelectedItem()!=null){
				mpTwo.setText(formatter.format(playerDao.getCalculateMovingPoints(raceTwo.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
			}
		}
	}

	@FXML
	private void dexOneMinus() {
		int number = Integer.parseInt(dexOne.getText());
		int hasPoints = Integer.parseInt(hasPointsOne.getText());
		if (number <= 1) {
			dexOne.setText("1");
		} else if(hasPoints!=10){
			hasPoints++;
			number--;
			dexOne.setText(String.valueOf(number));
			defenseOne.setText(String.valueOf(number));
			hasPointsOne.setText(String.valueOf(hasPoints));
			if(raceOne.getSelectionModel().getSelectedItem()!=null){
				mpOne.setText(formatter.format(playerDao.getCalculateMovingPoints(raceOne.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
			}
		}
	}

	@FXML
	private void raceTwoAction() {
		int number = Integer.parseInt(dexTwo.getText());
		mpTwo.setText(formatter.format(playerDao.getCalculateMovingPoints(raceTwo.getSelectionModel().getSelectedItem(), Double.parseDouble(number + ""))));
		number = Integer.parseInt(strTwo.getText());
		hpTwo.setText(String.valueOf(playerDao.getHealthPoints(raceTwo.getSelectionModel().getSelectedItem(), number)));
	}
	
	@FXML
	private void nextButtonAction() {
		int hasPointOne = Integer.parseInt(hasPointsOne.getText());
		int hasPointTwo = Integer.parseInt(hasPointsTwo.getText());
		if(hasPointOne!=0 || hasPointTwo!=0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hiba");
			alert.setHeaderText("Figyelmeztetõ üzenet:");
			alert.setContentText("Nincs minden 'Kiosztható pontok' kiosztva!");
			alert.showAndWait();
		}else if(nameOne.getText().equals("") || nameTwo.getText().equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hiba");
			alert.setHeaderText("Figyelmeztetõ üzenet:");
			alert.setContentText("Nincs név megadva!");
			alert.showAndWait();
		}else if(raceTwo.getSelectionModel().getSelectedItem()==null || raceOne.getSelectionModel().getSelectedItem()==null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hiba");
			alert.setHeaderText("Figyelmeztetõ üzenet:");
			alert.setContentText("Faj nincs kiválasztva!");
			alert.showAndWait();
		}else{
			String mp = mpOne.getText();
			mp=mp.replace(",", ".");
			playerOne = new Player(makeid(),nameOne.getText(),new Character(raceOne.getSelectionModel().getSelectedItem(),
					Integer.parseInt(hpOne.getText()),Integer.parseInt(strOne.getText()),Double.parseDouble(dexOne.getText()),
					Integer.parseInt(attackOne.getText()),Double.parseDouble(defenseOne.getText()),Double.parseDouble(mp)));
			mp = mpTwo.getText();
			mp=mp.replace(",", ".");
			playerTwo = new Player(makeid(),nameTwo.getText(),new Character(raceTwo.getSelectionModel().getSelectedItem(),
					Integer.parseInt(hpTwo.getText()),Integer.parseInt(strTwo.getText()),Double.parseDouble(dexTwo.getText()),
					Integer.parseInt(attackTwo.getText()),Double.parseDouble(defenseTwo.getText()),Double.parseDouble(mp)));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CharMakerController.class.getResource("/FightForm.fxml"));
			try {
				AnchorPane fightView = (AnchorPane)loader.load();
				Stage stage = new Stage();
				stage.setTitle("Harc");
				stage.initModality(Modality.WINDOW_MODAL);
				
				Scene scene = new Scene(fightView);
				stage.setScene(scene);
				
				FightController controller = loader.getController();
				controller.setInitPlayerOne(playerOne,playerTwo.getCharacter().getDex());
				controller.setInitPlayerTwo(playerTwo, playerOne.getCharacter().getStr());
				controller.setStage(stage);
				turnByPlayers = gameDao.makeTurnPlayers(playerOne, playerTwo);
				controller.setTurnsList(turnByPlayers);
				stage.setResizable(false);
				stage.show();
				Stage actualStage = (Stage) nextButton.getScene().getWindow();
				actualStage.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
