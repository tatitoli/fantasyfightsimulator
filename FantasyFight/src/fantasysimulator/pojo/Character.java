package fantasysimulator.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Character {
	String race;
	int healPoints;
	int str;
	double dex;
	int attackPoints;
	double defensePoints;
	double movingPoints;

	public Character() {
	}
	
	

	public Character(String race, int healPoints, int str, double dex, int attackPoints, double defensePoints,
			double movingPoints) {
		this.race = race;
		this.healPoints = healPoints;
		this.str = str;
		this.dex = dex;
		this.attackPoints = attackPoints;
		this.defensePoints = defensePoints;
		this.movingPoints = movingPoints;
	}

	public String getRace() {
		return race;
	}
	
	@XmlElement
	public void setRace(String race) {
		this.race = race;
	}
	
	public int getHealPoints() {
		return healPoints;
	}

	@XmlElement
	public void setHealPoints(int healPoints) {
		this.healPoints = healPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	@XmlElement
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	
	public double getDefensePoints() {
		return defensePoints;
	}

	@XmlElement
	public void setDefensePoints(double defensePoints) {
		this.defensePoints = defensePoints;
	}

	public double getMovingPoints() {
		return movingPoints;
	}

	@XmlElement
	public void setMovingPoints(double movingPoints) {
		this.movingPoints = movingPoints;
	}

	public int getStr() {
		return str;
	}
	
	@XmlElement
	public void setStr(int str) {
		this.str = str;
	}
	
	public double getDex() {
		return dex;
	}
	
	@XmlElement
	public void setDex(double dex) {
		this.dex = dex;
	}

	@Override
	public String toString() {
		return "Character [race=" + race + ", healPoints=" + healPoints + ", str=" + str + ", dex=" + dex
				+ ", attackPoints=" + attackPoints + ", defensePoints=" + defensePoints + ", movingPoints="
				+ movingPoints + "]";
	}
}
