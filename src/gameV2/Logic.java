package gameV2;

import java.util.ArrayList;

import powerUps.*;

public class Logic implements Runnable{
	// Initialize stats
	Stats stats = new Stats();
	ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	
	// Methods
	protected void addScore(int amount) {
		stats.setScore(stats.getScore() + amount);
	}
	
	protected void removeScore(int amount) {
		stats.setScore(stats.getScore() - amount);
	}
	
	protected void addPowerclick() {
		stats.setPowerclickLevel(stats.getPowerclickLevel() + 1);
	}
	
	protected void addAutoclick() {
		stats.setAutoclickLevel(stats.getAutoclickLevel() + 1);
		refreshAutoClick();
	}
	
	protected void addCritclick() {
		stats.setCritclickLevel(stats.getAutoclickLevel() + 1);
	}
	
	protected void refreshPowerUps() {
		powerups.clear();
		PowerClick powerclick = new PowerClick(stats.getPowerclickLevel());
		CritClick critclick = new CritClick(stats.getCritclickLevel());
		powerups.add(powerclick);
		powerups.add(critclick);
	}
	
	protected void refreshAutoClick() {
		Thread t1 = new Thread(new Logic());
		t1.start();
	}
	
	// Click calls 
	protected void Click() {
		int power = 1;
		refreshPowerUps();
		// Goes in all the PowerUps and calculates the new Power
		 for (int i = 0; i < powerups.size(); i++) {
			 	PowerUp p = powerups.get(i);
			 	power = p.getPower(power);;
	        }
		
		addScore(power);
	}

	@Override
	public void run() {
		AutoClick autoclick = new AutoClick(stats.getAutoclickLevel());
		autoclick.generateScore();
		Click();
	}
}