package fantasysimulator.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "games")
public class Games {
	List<Game> games;	

	@XmlElement(name="game")
	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void add(Game game) {
		if (this.games == null) {
			this.games = new ArrayList<Game>();
		}
		this.games.add(game);
	}

	public List<Game> getGames() {
		return games;
	}

	@Override
	public String toString() {
		return "Games [games=" + games + "]";
	}
	
}
