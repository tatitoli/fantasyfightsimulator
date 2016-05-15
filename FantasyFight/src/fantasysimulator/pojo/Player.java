package fantasysimulator.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="palyer")
public class Player {
	String id;
	String name;
	Character character;
	
	public Player(){
		super();
	}
	
	public Player(String id, String name, Character character) {
		this.id = id;
		this.name = name;
		this.character = character;
	}
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	@XmlElement
	public void setCharacter(Character character) {
		this.character = character;
	}

	@Override
	public String toString() {
		return "Player [Id=" + id + ", name=" + name + ", character=" + character + "]";
	}
	
	
}
