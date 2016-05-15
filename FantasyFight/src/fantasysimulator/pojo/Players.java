package fantasysimulator.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "players")
public class Players {
	List<Player> players;	

	@XmlElement(name="player")
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void add(Player player) {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(player);
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		return "Players [players=" + players + "]";
	}
	
	
}
