package fantasysimulator.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="game")
public class Game {
	String id;
	Players players;
	String whoWin;

	public Game() {
	}

	public Game(String id, Players players, String whoWin) {
		super();
		this.id = id;
		this.players = players;
		this.whoWin = whoWin;
	}
	public String getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public Players getPlayers() {
		return players;
	}
	
	@XmlElement
	public void setPlayers(Players players) {
		this.players = players;
	}
	
	public String getWhoWin() {
		return whoWin;
	}

	@XmlElement
	public void setWhoWin(String whoWin) {
		this.whoWin = whoWin;
	}

	@Override
	public String toString() {
		return "Game [Id=" + id + ", players=" + players + ", whoWin=" + whoWin + "]";
	}
}
