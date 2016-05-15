package fantasysimulator.pojo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameTable {
	
	private String id;
	private StringProperty playerOne;
	private StringProperty playerTwo;
	private StringProperty who;

	public GameTable(String id, String playerOne, String playerTwo, String who) {
		this.id= id;
		this.playerOne = new SimpleStringProperty(playerOne);
		this.playerTwo = new SimpleStringProperty(playerTwo);
		this.who = new SimpleStringProperty(who);
	}
	
	public String getPlayerOne() {
		return playerOne.get();
	}

	public void setPlayerOne(String playerOne) {
		this.playerOne.set(playerOne);
	}
	
	public StringProperty getPlayerOneProperty() {
		return playerOne;
	}

	public String getPlayerTwo() {
		return playerTwo.get();
	}

	public void setPlayerTwo(String playerTwo) {
		this.playerTwo.set(playerTwo);
	}
	
	public StringProperty getPlayerTwoProperty() {
		return playerTwo;
	}

	public String getWho() {
		return who.get();
	}

	public void setWho(String who) {
		this.who.set(who);
	}
	
	public StringProperty getWhoProperty() {
		return who;
	}

	public void setPlayerOne(StringProperty playerOne) {
		this.playerOne = playerOne;
	}

	public void setPlayerTwo(StringProperty playerTwo) {
		this.playerTwo = playerTwo;
	}

	public void setWho(StringProperty who) {
		this.who = who;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
