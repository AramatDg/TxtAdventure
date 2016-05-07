package mainRPG;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.AbstractAction;

public class MainRPG implements Runnable{

	// Player input and random numbers for fights etc.
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();

	// Runs the game
	private static boolean running;

	// Sets the player in an area, and records which one they need to be in after leaving the inventory
	public static int area = 1;

	// The states of towns, areas, and quests can change according to variable
	public static int shadyManState = 0;
	public static int forestState = 0;
	public static int cityState = 0;
	public static int roomState = 0;
	public static int nobleState = 0;
	public static int kingState = 0;
	public static int finalQuest = 0;
	public static int nestPath = 0;
	public static int nestKills = 0;
	public static int guildState = 0;
	public static int guildMission = 0;
	public static int gameBeat = 0;

	// Default player information
	static String input;
	static String name;
	static int maxHealth = 25;
	static int health = 25;
	static int mana = 20;
	static int attackDmg = 10;
	static int defense = 0;
	static int healthPotHeal = 15;
	static int superHealthPotHeal = 30;
	static int manaPotRestore = 10;
	static int enemiesDefeated = 0;
	static int bossDefeated = 0;
	static int hardBossDefeated = 0;
	static int experience = 0;
	static int level = 0;
	static int[] levelArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	static int gold = 0;
	static int silver = 0;
	static int copper = 0;
	static int drunk = 0;
	static int warriorToken = 0;
	static String currentSwordEquip = "nothing";
	static String currentShieldEquip = "nothing";

	// Spells
	static int spellState = 0;
	static int fireBallDamage = 5;
	static int fireBallMana = 10;
	static int magicMissileDamage = 10;
	static int magicMissileMana = 15;
	static int absorbDamage = 10; // Also restores 10Hp
	static int absorbMana = 25;

	// Monster setup, changes based on area interaction
	String enemy;
	String[] mobs = {""};
	static int mobHealth = 10;
	static int mobAD = 10;
	static int mobEXP = 10;
	static int mobMoney = 5;	
	
	// Inventory items - Potions, Food, Equipment, Items
	static int healthPots = 0;
	static int superHealthPots = 0;
	static int manaPots = 0;
	static int apple = 0;
	static int applePie = 0;
	static int beer = 0;
	static int bread = 0;
	static int pumpkin = 0;

	static int scythe = 0; // 5 AD
	static int bronzeSword = 0; // 5 AD
	static int bronzeShield = 0; // 5 Max Hp and Defense
	static int ironSword = 0; // 10
	static int ironShield = 0; // 10
	static int steelSword = 0; // 20
	static int steelShield = 0; // 15
	static int mithrilSword = 0; // 25
	static int mithrilShield = 0; // 20
	static int guildSword = 0; // 30
	static int guildShield = 0; // 25
	static int adamantSword = 0; // 35
	static int adamantShield = 0; // 30
	static int dragonSword = 0; // 50
	static int dragonShield = 0; // 50 and 40
	static int flintlock = 0; // 100

	static int lantern = 0;

	// Drops in percents
	static int healthPotDrop = 30;
	static int superHealthPotDrop = 50;
	static int manaPotDrop = 25;
	static int bSwordDrop = 10;
	static int bShieldDrop = 10;
	static int iSwordDrop = 5;
	static int iShieldDrop = 5;
	static int sSwordDrop = 5;
	static int sShieldDrop = 5;
	static int mSwordDrop = 5;
	static int mShieldDrop = 5;
	static int aSwordDrop = 5;
	static int aShieldDrop = 5;
	static int dSwordDrop = 100;
	static int dShieldDrop = 100;
	static int flintLockDrop = 100;
	
	public GUI theGUI;

	public MainRPG(GUI theGUI) {
		this.theGUI = theGUI;
	}
	
	public void setup() {
		
		theGUI.appendMainArea("Enter your name!"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	MainRPG.name = e.getActionCommand();
                theGUI.appendMainArea("Your name is: ".concat(e.getActionCommand()));
                theGUI.appendMainArea("...");
                theGUI.appendMainArea("You awake in a field, wheat rising all around you."
					+ "\nThe only thing you see is a small town looming ahead."
					+ "\nYou walk to the town, curious as to where you are."
					+ "\n");
                theGUI.appendMainArea("You enter the town, and it seems rather familiar."
    					+ "\n");
                
                theGUI.commandField.removeActionListener(this); 
                townMain();
            }
        });
	}
	
	public void townMain() {
		 area = 1;
		 theGUI.locationLabel.setText(" Your location: Town");
				 	        
         System.out.println("Where would you like to go?"
         		+ "\n");
         System.out.println("1: Tavern");
         System.out.println("2: General Store");
         System.out.println("3: Town's Square");
         System.out.println("4: Inventory"
        		+ "\n");
         	
        theGUI.commandField.addActionListener(new AbstractAction() {
         		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         			
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			theGUI.appendMainArea("You enter the Tavern through sturdy oak double doors. "
    						+ "\nSmelling a hardy aroma of wheat beer and a small fire. "
    						+ "\nThere appears to be only a Shady Man sitting in one corner"
    						+ "\nand the Barkeeper cleaning a well polished counter."
    						+ "\n");
         			tavern();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You push open a solid door and enter the General Store."
    						+ "\nThere is one lonely women looking for fresh ingredients and the Shopkeeper sorting his shelves."
    						+ "\n "
    						+ "\nOh, the Shopkeeper exclaims, a customer!  He hurries back behind the counter."
    						+ "\nJust take a look around and come up to pay when you are ready, items are marked with prices."
    						+ "\n");
         			smallShop();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You venture into the near vacant Town Square, decorated by a single large fountain. "
    						+ "\nThere are only a few farmers attempting to sell cheap goods, and a few Guards lazily standing watch."
    						+ "\n");
         			townSquare();
         		}
         		
        		else if(e.getActionCommand().equals("4")) {
        			theGUI.commandField.removeActionListener(this);
        			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
         			inventory();
         		}
        	}    
         	
         });
	}
	
	public void tavern() {
		
		theGUI.appendMainArea("");
	   	System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Speak to the Barkeeper");
		System.out.println("2: Speak to the Shady Man");
		System.out.println("3: Leave"
				+ "\n");
						
		theGUI.commandField.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
								
				if(e.getActionCommand().equals("1")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You walk up to the Barkeeper as he eyes you suspiciously. "
							+ "\nWhat can I do fer ya?  He asks."
							+ "\n");
					barkeeper();
				}
								
				else if(e.getActionCommand().equals("2")) {
					theGUI.commandField.removeActionListener(this);
					shadyMan();
				}
								
				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You walk out of the Tavern."
							+ "\n");
					townMain();
				}				
			}
	    					
	    });					
	}
	
	public void barkeeper() {
		
		System.out.println("1: Buy a beer");
		System.out.println("2: Rent a room for the night");
		System.out.println("3: Ask about town");
		System.out.println("4: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
									
				if(e.getActionCommand().equals("1")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("Want a beer do ya?  That'll be five copper Carlons."
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(e.getActionCommand().equals("1") && copper >= 5) {
								theGUI.commandField.removeActionListener(this);
								copper -= 5;
								beer ++;
								System.out.println("You purchase a beer. "
										+ "\n");
								System.out.println("The Barkeeper pours the drink into a mug."
										+ "\nWell then, here you are.  Enjoy!"
										+ "\n");
								barkeeper();
							}
							
							else if(e.getActionCommand().equals("1") && copper < 5) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough to buy a drink. "
										+ "\n");
								barkeeper();
							}
							
							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
								barkeeper();
							}
							
						}
						
					});
				}
									
				else if(e.getActionCommand().equals("2")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("Want to stay for the night eh?  Very well, twenty five copper Carlons."
							+ "\n");
					System.out.println("1: Rent");
					System.out.println("2: Nevermind"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(e.getActionCommand().equals("1") && copper >= 25) {
								theGUI.commandField.removeActionListener(this);
								
								copper -= 25;
								health += 25;
								
								if(drunk > 0) {

									drunk = 0;
								}

								else if(drunk >= 5) {

									health += 5;
									attackDmg += 5;
									drunk = 0;
								}

								if(health > maxHealth) {
									health = maxHealth;
								}

								System.out.println("You hand over the money and head upstairs for some much needed rest."
										+ "\n"
										+ "\nZZzzzzzZZZzzzzz"
										+ "\n"
										+ "\nYou awake feeling refreshed and ready for an adventure!"
										+ "\n");
								pInfoUpdate();
								barkeeper();
							}
							
							else if(e.getActionCommand().equals("1") && copper < 25) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough to rent a room. "
										+ "\n");
								barkeeper();
							}
							
							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
								barkeeper();
							}							
						}
						
					});
				}
					
				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
										
					System.out.println("You lean on the well polished counter of the bar and ask about the town"
							+ "\n"
							+ "\nThe Barkeeper answers, Well we have had a rough patch with monsters in the forest recently,"
							+ "\nnot to mention the increased tax rates and theft is getting out of hand."
							+ "\nThough overall, I love Haraolen, its been my home since birth."
							+ "\n"
							+ "\nTo which he gets lost in his memories and ignores you."
							+ "\n");
					barkeeper();
				}
									
				else if(e.getActionCommand().equals("4")) {
					theGUI.commandField.removeActionListener(this);
					tavern();
				}				
			}
													
		});	
	}
	
	public void shadyMan() {
		
		if(beer == 0 && shadyManState == 0) {
			
			shadyManState = 1;
			System.out.println("You approach the Shady Man and he eyes you darkly. "
					+ "\nYou can see him pulling a Flintlock from his holster.  "
					+ "\nWith good judgement, you decide to leave this fellow alone for now."
					+ "\n");
			tavern();
		}
		
		else if(beer == 0 && shadyManState == 1) {

			shadyManState = 2;
			System.out.println("You approach the Shady Man and he stares you down. "
				+ "\nYou can see him with his Flintlock already pulled out.  "
				+ "\nYou turn around and decide to leave this irritated fellow alone for now."
				+ "\n");
			tavern();
		}
		
		else if(beer < 1 && shadyManState == 2) {

			shadyManState = 3;
			System.out.println("With bad judgement, you approach the Shady Man again. "
				+ "\nYou can see him pulling back the hammer on his Flintlock.  "
				+ "\nYou turn and leave this angry fellow alone."
				+ "\n");
			tavern();
		}

		else if(beer < 1 && shadyManState == 3) {

			health = 0;
			pInfoUpdate();
			
			System.out.println("You stupidly walk back over to the Shady Man. "
				+ "\nYou see him level his Flintlock at your head with finger on the trigger.  "
				+ "\nYou have a good clue on what happens ne---------."
				+ "\n"
				+ "\nThe hammer slams down and the barrel is thrown backwards with recoil"
				+ "\n"
				+ "\nYour body collapses to the floor, head hollowed, and brain matter splattered everywhere."
				+ "\n");
			endGame();
		}
		
		else if(beer > 0) {
			
			shadyManState = 0;
			System.out.println("You approach the Shady Man and his eyes dart to the beer in your hand."
					+ "\nWell what do we have here, he says still staring at the drink, a friend perhaps?"
					+ "\n"
					+ "\nWhat would you like to do?");
			System.out.println("1: Give the Shady Man a beer");
			System.out.println("2: Awkwardly turn and walk away slowly"
					+ "\n");
			
			theGUI.commandField.addActionListener(new AbstractAction() {

				@Override
				public void actionPerformed(ActionEvent e) {
									
					if(e.getActionCommand().equals("1")) {
						theGUI.commandField.removeActionListener(this);
						
						beer --;
						
						System.out.println("You hand the Shady Man a beer");
						System.out.println("He gladly accepts it and allows you to sit with him."
								+ "\n");
						shadyManDialog();
					}
									
					else if(e.getActionCommand().equals("2")) {
						theGUI.commandField.removeActionListener(this);
						
						System.out.println("You turn and awkwardly walk away."
								+ "\n");
						tavern();
					}				
				}
		    					
		    });
		}
	}
	
	public void shadyManDialog() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Ask about him");
		System.out.println("2: Ask about his weapon");
		System.out.println("3: Fight him");
		System.out.println("4: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && beer > 0) {
         			theGUI.commandField.removeActionListener(this);
         			beer --;
					System.out.println("He raises his mug and beckons for you to do the same"
							+ "\n");
					System.out.println("You take a drink together and he starts talking."
							+ "\n"
							+ "\nWell I settled here a while ago after journying to the ends of the world."
							+ "\nI aquired fame and fortune, but that was all taken from me."
							+ "\nAll I have left is my Flintlock, and countless stories."
							+ "\n");
					shadyManDialog();
         		}
         		
         		else if(e.getActionCommand().equals("1") && beer == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("He raises his mug and beckons for you to do the same."
							+ "\n");
					System.out.println("Unfortunately you do not have a drink."
							+ "\nHe drinks his anyway and remains silent."
							+ "\n");
					shadyManDialog();
         		}
         		
         		else if(e.getActionCommand().equals("2") && beer > 4) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("So you want to know about my Flintlock eh?"
							+ "\nWell then, let's see how much you can drink."
							+ "");
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Drink with him");
					System.out.println("2: Do something else"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
			      		
			         	@Override
			         	public void actionPerformed(ActionEvent e) {
			         		
			         		if(e.getActionCommand().equals("1")) {
			         			theGUI.commandField.removeActionListener(this);
			         			beer -= 5;
								drunk = 5;
								health -=5;
								attackDmg -= 5;
								System.out.println("You raise mug after mug with the Shady Man."
										+ "\nAfter five mugs, you both stop and he starts talking."
										+ "\n"
										+ "\nWell after a few trips out to a certain black mountain range,"
										+ "\nI managed to slip past a great big dragon living up there."
										+ "\nI found this beauty in it's nest"
										+ "\n");
								shadyManDialog();
			         		}
			         		
			         		else if(e.getActionCommand().equals("2")) {
			         			theGUI.commandField.removeActionListener(this);
			         			
			         			System.out.println("You do not feel like drinking right now."
			         					+ "\n");
			         			shadyManDialog();
			         		}
			         	}
					});
         		}
         		
         		else if(e.getActionCommand().equals("2") && beer < 4) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("He eyes your lack of numerous drinks."
							+ "\nSorry only people that can drink with me will get to hear from me."
							+ "\n");
         			shadyMan();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You have a temporary lapse of insanity and decide to fight."
							+ "\n");
         			String[] mobs = {"Shady Man"};
         			mobHealth = 500;
         			mobAD = 150;
         			mobEXP = 500;
         			mobMoney = 10;	 

         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You stand up and walk away from the Shady Man."
							+ "\n");
         			tavern();
         		}
         	}
		});
	}
	
	public void smallShop() {
		
		System.out.println("What would you like to buy?"
				+ "\n");
		System.out.println("1: Apple\t\t10 copper");
		System.out.println("2: Bread\t\t15 copper");
		System.out.println("3: Bronze Sword\t50 copper");
		System.out.println("4: Bronze Shield\t50 copper");
		System.out.println("5: Lantern\t\t30 copper");
		System.out.println("6: Sell");
		System.out.println("7: Leave the store"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
	         	@Override
	         	public void actionPerformed(ActionEvent e) {
	         			
	         		if(e.getActionCommand().equals("1")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("Buy an apple for 10 copper Carlons?"
	        					+ "\n");
	        			System.out.println("1: Buy");
	        			System.out.println("2: Nevermind"
	        					+ "\n");
	        			
	        			theGUI.commandField.addActionListener(new AbstractAction() {
	        		      		
	        		         	@Override
	        		         	public void actionPerformed(ActionEvent e) {    
	        		         		
	        		         		if(e.getActionCommand().equals("1") && copper >= 10) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			apple ++;
	        		         			copper -= 10;
	        		         			System.out.println("You have bought an apple."
	        		         					+ "\n");
	        		         			smallShop();
	        		         		}

	        		         		else if(e.getActionCommand().equals("1") && copper < 10) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			System.out.println("You do not have enough for this item."
	        		         					+ "\n");
	        		         			smallShop();
	        		         		}
	        		         		
	        		         		else if(e.getActionCommand().equals("2")) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			System.out.println("You continue looking."
	        		    						+ "\n");
	        		         			smallShop();
	        		         		}
	        		         		
	        		         	}
	        			});
	         		}
	         		
	         		else if(e.getActionCommand().equals("2")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("Buy bread for 15 copper Carlons?"
	        					+ "\n");
	        			System.out.println("1: Buy");
	        			System.out.println("2: Nevermind"
	        					+ "\n");
	        			
	        			theGUI.commandField.addActionListener(new AbstractAction() {
	        		      		
	        		         	@Override
	        		         	public void actionPerformed(ActionEvent e) {    
	        		         		
	        		         		if(e.getActionCommand().equals("1") && copper >= 15) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			bread ++;
	        		    				copper -= 15;
	        		    				System.out.println("You have bought a loaf of bread."
	        		    						+ "\n");
	        		         			smallShop();
	        		         		}

	        		         		else if(e.getActionCommand().equals("1") && copper < 15) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			System.out.println("You do not have enough for this item."
	        		         					+ "\n");
	        		         			smallShop();
	        		         		}
	        		         		
	        		         		else if(e.getActionCommand().equals("2")) {
	        		         			theGUI.commandField.removeActionListener(this);
	        		         			
	        		         			System.out.println("You continue looking."
	        		    						+ "\n");
	        		         			smallShop();
	        		         		}        		         		
	        		         	}
	        		         	
	        			});

	         		}
	         		
	         		else if(e.getActionCommand().equals("3")) {
	         			theGUI.commandField.removeActionListener(this);
	         				
	         			System.out.println("Buy bronze sword for 50 copper Carlons?"
	        					+ "\n");
	        			System.out.println("1: Buy");
	        			System.out.println("2: Nevermind"
	        					+ "\n");
	         			
	         			theGUI.commandField.addActionListener(new AbstractAction() {
	         		      		
	         		         	@Override
	         		         	public void actionPerformed(ActionEvent e) {    
	         		         		
	         		         		if(e.getActionCommand().equals("1") && copper >= 50) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			bronzeSword ++;
	         		         			copper -= 50;
	         		         			System.out.println("You have bought a bronze sword."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}

	         		         		else if(e.getActionCommand().equals("1") && copper < 50) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You do not have enough for this item."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}
	         		         		
	         		         		else if(e.getActionCommand().equals("2")) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You continue looking."
	         		    						+ "\n");
	         		         			smallShop();
	         		         		}        		         		
	         		         	}
	         		         	
	         			});

	         		}
	         		
	         		else if(e.getActionCommand().equals("4")) {
	         			theGUI.commandField.removeActionListener(this);
	         				
	         			System.out.println("Buy bronze shield for 50 copper Carlons?"
	        					+ "\n");
	        			System.out.println("1: Buy");
	        			System.out.println("2: Nevermind"
	        					+ "\n");
	         			
	         			theGUI.commandField.addActionListener(new AbstractAction() {
	         		      		
	         		         	@Override
	         		         	public void actionPerformed(ActionEvent e) {    
	         		         		
	         		         		if(e.getActionCommand().equals("1") && copper >= 50) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			bronzeShield ++;
	         		         			copper -= 50;
	         		         			System.out.println("You have bought a bronze shield."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}

	         		         		else if(e.getActionCommand().equals("1") && copper < 50) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You do not have enough for this item."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}
	         		         		
	         		         		else if(e.getActionCommand().equals("2")) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You continue looking."
	         		    						+ "\n");
	         		         			smallShop();
	         		         		}        		         		
	         		         	}
	         		         	
	         			});

	         		}
	         	
	         		else if(e.getActionCommand().equals("5")) {
	         			theGUI.commandField.removeActionListener(this);
	         				
	         			System.out.println("Buy lantern for 30 copper Carlons?"
	        					+ "\n");
	        			System.out.println("1: Buy");
	        			System.out.println("2: Nevermind"
	        					+ "\n");
	         			
	         			theGUI.commandField.addActionListener(new AbstractAction() {
	         		      		
	         		         	@Override
	         		         	public void actionPerformed(ActionEvent e) {    
	         		         		
	         		         		if(e.getActionCommand().equals("1") && copper >= 30) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			lantern ++;
	         		         			copper -= 30;
	         		         			System.out.println("You have bought a lantern."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}

	         		         		else if(e.getActionCommand().equals("1") && copper < 30) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You do not have enough for this item."
	         		         					+ "\n");
	         		         			smallShop();
	         		         		}
	         		         		
	         		         		else if(e.getActionCommand().equals("2")) {
	         		         			theGUI.commandField.removeActionListener(this);
	         		         			
	         		         			System.out.println("You continue looking."
	         		    						+ "\n");
	         		         			smallShop();
	         		         		}        		         		
	         		         	}
	         		         	
	         			});

	         		}
	         		
	         		else if(e.getActionCommand().equals("6")) {
	         			theGUI.commandField.removeActionListener(this);
	         				
	         			System.out.println("The Shopkeeper looks at you intrigued, What have you got?"
	        					+ "\nJust keep in mind, I don't buy what I can't sell myself."
	        					+ "\n");
	         			smallShopSell();
	         		}
	         		
	         		else if(e.getActionCommand().equals("7")) {
	         			theGUI.commandField.removeActionListener(this);
	         				
	         			System.out.println("You are satisfied with your visit and leave the shop."
	        					+ "\n");
	         			townMain();         			
	         		}         			         		
	         	}
	         	
		 });	
	}
	
	public void smallShopSell() {
		
		System.out.println("What would you like to sell?"
				+ "\n");
		System.out.println("1: Bronze Sword\t25 copper");
		System.out.println("2: Bronze Shield\t25 copper");
		System.out.println("3: Iron Sword\t\t1 silver");
		System.out.println("4: Iron Shield\t\t1 silver");
		System.out.println("5: Buy"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && bronzeSword > 0 && currentSwordEquip != "Bronze Sword") {
         			theGUI.commandField.removeActionListener(this);
					bronzeSword --;
					copper += 25;
					System.out.println("You sell the bronze sword for 25 copper."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("1") && bronzeSword == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a bronze sword."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("1") && currentSwordEquip == "Bronze sword") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your sword to sell."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("2") && bronzeShield > 0 && currentShieldEquip != "Bronze Shield") {
					theGUI.commandField.removeActionListener(this);
					bronzeShield --;
					copper += 25;
					System.out.println("You sell the bronze shield for 25 copper."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("2") && bronzeShield == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a bronze shield."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("2") && currentShieldEquip == "Bronze Shield") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your shield to sell."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("3") && ironSword > 0 && currentSwordEquip != "Iron Sword") {
					theGUI.commandField.removeActionListener(this);
					ironSword --;
					silver ++;
					System.out.println("You sell the iron sword for 1 silver."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("3") && ironSword == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have an iron sword."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("3") && currentSwordEquip == "Iron sword") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your sword to sell."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("4") && ironShield > 0 && currentShieldEquip != "Iron Shield") {
					theGUI.commandField.removeActionListener(this);
					ironShield --;
					silver ++;
					System.out.println("You sell the iron shield for 1 silver."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("4") && ironShield == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have an iron shield."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("4") && currentShieldEquip == "Iron Shield") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your shield to sell."
							+ "\n");
					smallShopSell();
				}

				else if(e.getActionCommand().equals("5")) {
					theGUI.commandField.removeActionListener(this);
					smallShop();
				}       		
         	}
		});
	}
	
	public void townSquare() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Toss a coin in the fountain");
		System.out.println("2: Interact with a guard");
		System.out.println("3: Interact with a farmer");
		System.out.println("4: Leave the town");
		System.out.println("5: Back to shops");
		System.out.println("6: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && copper > 0) {
         			theGUI.commandField.removeActionListener(this);
         			copper --;
					System.out.println("You toss a coin into the fountain, hoping for some luck!"
							+ "\n");

					int luck = rand.nextInt(100);

					if(luck >= 50) {

						health ++;
						if(health > maxHealth) {
							health = maxHealth;
						}
						theGUI.playerInfo.setText("HP " + health + "/" + maxHealth + "\nMana " + mana + "\nAD   "
								+ attackDmg + "\nDEF " + defense + "\nLvl    " + level + "\nExp  " + experience);

						System.out.println("Seeing your copper coin sink to the bottom, you feel slightly rejuvinated."
								+ "\n"
								+ "\nYou now have " + health + " HP remaining."
								+ "\n");
					}

					else if(luck < 50) {

						health --;
						theGUI.playerInfo.setText("HP " + health + "/" + maxHealth + "\nMana " + mana + "\nAD   "
								+ attackDmg + "\nDEF " + defense + "\nLvl    " + level + "\nExp  " + experience);
						System.out.println("Seeing your copper coin sink to the bottom, you feel slightly drained."
								+ "\n"
								+ "\nYou now have " + health + " HP remaining."
								+ "\n");
					}
					
					if(health > 0) {
					townSquare();
					}
					else if(health == 0) {
						endGame();
					}
         		}
         		
         		else if(e.getActionCommand().equals("1") && copper == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You do not have a coin to toss into the fountain."
							+ "\n");
         			townSquare();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("The guard peers at you approaching through sleepy eyes."
							+ "\nHe then yawns and closes then, ignoring you."
							+ "\n");
					// Perhaps enhance interaction later
         			townSquare();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk up to a farmer, seeing what he has to offer."
							+ "\nHe has a small variety of wares, most useless to a man with no farm."
							+ "\n");
         			farmer();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Ahead you see the city's gate leading south."
							+ "\nBeyond it is a lush, green forest which is rumored to be dangerous."
							+ "\n"
							+ "Looking ahead with hope and determination, you decide to leave town."
							+ "\n");
					area = 2;
					System.out.println("As you enter the forest, daylight dims through thickening treetops."
							+ "\nThe path through the forest is old, but wide enough to walk through."
							+ "\n");
					forest();
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You take another look around and decide to return to the shops."
							+ "\n");
         			townMain();
         		}
         		
         		else if(e.getActionCommand().equals("6")) {
         			theGUI.commandField.removeActionListener(this);
					System.out.println("You open your travel pack to see what is inside."
							+ "\n");
         			inventory();
         		}
         		
         	}
		});
		
	}
	
	public void farmer() {
		
		System.out.println("Which item would you like? He eagerly says"
				+ "\n");
		System.out.println("1: Apple\t9 copper");
		System.out.println("2: Bread\t14 copper");
		System.out.println("3: Pumpkin\t20 copper");
		System.out.println("4: Scythe\t45 copper");
		System.out.println("5: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {         			         	
         			
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy an apple for 9 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && copper >= 9) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			apple ++;
        		         			copper -= 9;
        		         			System.out.println("You have bought an apple."
        		         					+ "\n");
        		         			farmer();
        		         		}

        		         		else if(e.getActionCommand().equals("1") && copper < 9) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You do not have enough for this item."
        		         					+ "\n");
        		         			farmer();
        		         		}
        		         		
        		         		else if(e.getActionCommand().equals("2")) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You continue looking."
        		    						+ "\n");
        		         			farmer();
        		         		}      		         		
        		         	}
        			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy bread for 14 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && copper >= 14) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			bread ++;
        		    				copper -= 14;
        		    				System.out.println("You have bought a loaf of bread."
        		    						+ "\n");
        		         			farmer();
        		         		}

        		         		else if(e.getActionCommand().equals("1") && copper < 14) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You do not have enough for this item."
        		         					+ "\n");
        		         			farmer();
        		         		}
        		         		
        		         		else if(e.getActionCommand().equals("2")) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You continue looking."
        		    						+ "\n");
        		         			farmer();
        		         		}        		         		
        		         	}
        		         	
        			});

         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy pumpkin for 20 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && copper >= 20) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			pumpkin ++;
         		         			copper -= 20;
         		         			System.out.println("You have bought a pumpkin."
         		         					+ "\n");
         		         			farmer();
         		         		}

         		         		else if(e.getActionCommand().equals("1") && copper < 20) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You do not have enough for this item."
         		         					+ "\n");
         		         			farmer();
         		         		}
         		         		
         		         		else if(e.getActionCommand().equals("2")) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You continue looking."
         		    						+ "\n");
         		         			farmer();
         		         		}        		         		
         		         	}
         		         	
         			});

         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy scythe for 45 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && copper >= 45) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			scythe ++;
         		         			copper -= 45;
         		         			System.out.println("You have bought a scythe."
         		         					+ "\n");
         		         			smallShop();
         		         		}

         		         		else if(e.getActionCommand().equals("1") && copper < 45) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You do not have enough for this item."
         		         					+ "\n");
         		         			smallShop();
         		         		}
         		         		
         		         		else if(e.getActionCommand().equals("2")) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You continue looking."
         		    						+ "\n");
         		         			smallShop();
         		         		}        		         		
         		         	}
         		         	
         			});

         		}
         	         		         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("You leave the farmer and his goods."
							+ "\n");
         			townSquare();
         		}         			         		
         	}
         	
		});		
	}
	
	public void forest() {
		area = 2;		
		theGUI.locationLabel.setText(" Your location: Forest");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Continue through forest");
		System.out.println("2: Look for a monster to fight");
		System.out.println("3: Return to previous town");
		System.out.println("4: Inventory");
		if(forestState > 0) {
			System.out.println("5: Go through caves");
		}
		System.out.println("");
		
		 theGUI.commandField.addActionListener(new AbstractAction() {
      		
	         	@Override
	         	public void actionPerformed(ActionEvent e) {
	         		
	         		if(e.getActionCommand().equals("1") && (forestState == 0 || forestState == 1)) {
	         			theGUI.commandField.removeActionListener(this);
	         			forestState = 1;
	    				System.out.println("As you attempt to continue up the path toward the next town,"
	    						+ "\nA giant boulder is blocking your path and you are forced to turn around."
	    						+ "\nThough as you do, you see the mouth of a cave through the trees."
	    						+ "\nThis could be a way around the impassable boulder."
	    						+ "\n");
	    				forest();
	         		}
	         		
	         		else if(e.getActionCommand().equals("1") && forestState == 2) {
	         			theGUI.commandField.removeActionListener(this);
	    				area = 5;
	    				System.out.println("You continue through the forest by the recently cleared path."
	    						+ "\nThe path proves to be much faster then going through the caves."
	    						+ "\n");
	    				darkForest();
	         		}
	         		
	         		else if(e.getActionCommand().equals("2")) {
	         			theGUI.commandField.removeActionListener(this);   
	         			
	         			// Sets area's mob info
	         			String[] mobs = {"Slime", "Giant Animated Mushroom", "Feral Rabbit", "Giant Spider", "Bandit"};
	         			mobHealth = 10;
	         			mobAD = 10;
	         			mobEXP = 10;
	         			mobMoney = 5;	 
	         			
	         			enemy = mobs[rand.nextInt(mobs.length)];
	         			System.out.println("\t# A " + enemy + " has appeared! #"
	         					+ "\n");	
	         			mobFight();
	         		}
	         		
	         		else if(e.getActionCommand().equals("3")) {
	         			theGUI.commandField.removeActionListener(this);
	         			area = 1;
	    				System.out.println("You leave the forest, deciding to go back to town"
	    						+ "\n");
	    				townMain();
	         		}
	         		
	         		else if(e.getActionCommand().equals("4")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("You open your travel pack to see what is inside."
								+ "\n");
	    				inventory();
	         		}
	         		
	         		else if(e.getActionCommand().equals("5") && forestState > 0) {
	         			theGUI.commandField.removeActionListener(this);
	         			area = 3;
	    				System.out.println("You venture into the caves, searching for a way around."
	    						+ "\n");
	    				System.out.println("Climbing into the cave system, you see that it extends deeper then you first thought."
	    						+ "\nWithout a lantern, you know that a journey through this piercing darkness would be perilous."
	    						+ "\n");
	    				caves();
	         		}
	         	}
		 });
		
	}
	
	public void caves() {
		area = 3;
		theGUI.locationLabel.setText(" Your location: Caves");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Continue through cave");
		System.out.println("2: Look for a monster to fight");
		System.out.println("3: Return to the forest");
		System.out.println("4: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("1") && lantern == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You attempt to walk foward into the cave, but it is too dark to see anything."
							+ "\nPerhaps you should go back to town and buy a lantern."
							+ "\n");
					caves();
				}
				
				else if(e.getActionCommand().equals("1") && lantern > 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You proceed into the cave system, holding your lantern in front of you."
							+ "\nEvery step sends shivers up your spine, feeling like something is watching you."
							+ "\n"
							+ "\nAs you step further, you can hear a deep, loud grunting sound ahead."
							+ "\n");
					
					String[] mobs = {"Cave Troll"};
         			mobHealth = 50;
         			mobAD = 20;
         			mobEXP = 50;
         			mobMoney = 50;	 
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");
         			area = 4;
         			mobFight();
				}
				
				else if(e.getActionCommand().equals("2")) {
					theGUI.commandField.removeActionListener(this);
					
					String[] mobs = {"Goblin", "Giant Rat", "Cave Slime", "Skeleton", "Zombie"};
					mobHealth = 25;
					mobAD = 15;
					mobEXP = 25;
					mobMoney = 15;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
				}
				
				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
					area = 2;
					System.out.println("You decide going through the cave is not wise at this time."
							+ "\n");
					forest();
				}
				
				else if(e.getActionCommand().equals("4")) {
					theGUI.commandField.removeActionListener(this);
					System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
				}
			}
			
		});		
	}
	
	public void darkForest() {
		area = 5;		
		theGUI.locationLabel.setText(" Your location: Dark Forest");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Continue through forest");
		System.out.println("2: Find a monster to fight");
		System.out.println("3: Return to city");
		System.out.println("4: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			forestState = 2;
         			area = 1;
    				System.out.println("You continue through the forest."
    						+ "\nYou find the path has been cleared of a large boulder."
    						+ "\n");
    				forest();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			String[] mobs = {"Dryad", "Feral Unicorn", "Satyr", "Nymph", "Owlbear", "Troll"};
         			mobHealth = 50;
         			mobAD = 25;
         			mobEXP = 50;
         			mobMoney = 50;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 5;
    				System.out.println("You walk back toward the city, leaving the dark forest behind."
    						+ "\n");
    				city();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {     			
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});		
	}
	
	public void city() {
		area = 6;
		theGUI.locationLabel.setText(" Your location: City");
		
		System.out.println("Where would you like to go?"
				+ "\n");
		System.out.println("1: Inn");
		System.out.println("2: Shops");
		System.out.println("3: Guilds");
		System.out.println("4: Castle");
		System.out.println("5: Back to forest");
		System.out.println("6: Head toward the mountains");
		System.out.println("7: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk into one of few inns that dot the main street."
    						+ "\n"
    						+ "\nThe sweet smell of pie, and good ale is in the air."
    						+ "\nThere is a Innkeeper tending to guests coming and going."
    						+ "\nThere is also a bar area on the ground floor."
    						+ "\n");
         			inn();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 7;
         			System.out.println("You follow the signs west to what appears to be a shopping district."
    						+ "\nThere are many large buildings holding various merchants and their wares."
    						+ "\n");
         			shops();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 8;
         			System.out.println("You turn down a constricted side street that winds its way east,"
    						+ "\nAfter a few minutes of walking, the street opens up into a large courtyard."
    						+ "\nThere are a few large buildings with flags hanging outside and pictures on them."
    						+ "\nOne has crossed swords, the second has a wizards hat, a third depicts stacks of coins."
    						+ "\n");
         			guilds();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 9;
    				System.out.println("Looking toward the north end of the sprawling city,"
    						+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
    						+ "\n");
    				System.out.println("You walk up to the castle's imposing gates."
    						+ "\nIt is heavily guarded by crossbowmen."
    						+ "\nFortunately without difficulty, you enter the gate and head inside."
    						+ "\n");
    				castle();
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 4;
    				System.out.println("As you turn to leave, you hear people speaking."
    						+ "\nThey are making mention of brute-like monsters in the forest."
    						+ "\n"
    						+ "\nLeaving the city, you head back toward the small town you came from."
    						+ "\nPerhaps the boulder that was once blocking your way is gone."
    						+ "\n");
         			darkForest();
         		}
         		
         		else if(e.getActionCommand().equals("6") && finalQuest == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("A guard stops you from going through this gate."
    						+ "\nSorry civilian, only those granted access by the King may pass."
    						+ "\n");
    				city();
         		}
         		
         		else if(e.getActionCommand().equals("6") && finalQuest == 1) {
         			theGUI.commandField.removeActionListener(this);
         			area = 10;
    				System.out.println("You leave the city to go toward a large, black mountain range."
    						+ "\n");
    				System.out.println("After a tiring trip, you arrive in the foothills of the Black Mountains."
    						+ "\nYou can see powerful monsters all around the hills."
    						+ "\nYou can also see dense, black smoke rising from the highest peaks."
    						+ "\n");
    				mountain();
         		}
         		
         		else if(e.getActionCommand().equals("7")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});
		
	}
	
	public void inn() {
		theGUI.locationLabel.setText(" Your location: Inn");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Speak to Innkeeper");
		System.out.println("2: Speak to Barkeeper");
		System.out.println("3: Speak to locals");
		System.out.println("4: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("The Innkeeper smiles warmly as you walk toward him."
							+ "\nWhat can I do for you sir?"
							+ "\n");
         			innkeeper();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You wander over to a busy bar and luckily find a seat."
							+ "\nThe Barkeeper is serving drinks of all kinds and takes notice of you."
							+ "\nHe yells over the commotion, What can I get for you?"
							+ "\n");
         			cityBarkeeper();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			System.out.println("The local folk do not take notice of you."
							+ "\nThey are too busy drinking and laughing."
							+ "\n");
         			inn();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You exit the inn, returning to busy streets."
							+ "\n");
         			city();
         		}
         	}
		});		
	}
	
	public void innkeeper() {
		
		System.out.println("1: Rent a room for the night");
		System.out.println("2: Purchase a room");
		System.out.println("3: Nevermind");
		if(roomState == 1) {
			System.out.println("4: Go to your room");
		}
		System.out.println("");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("Wish to stay the night?  Very well, fifty copper Carlons please."
							+ "\n");
					System.out.println("1: Rent");
					System.out.println("2: Nevermind"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
			      		
			         	@Override
			         	public void actionPerformed(ActionEvent e) {
			         					         		
			         		if(e.getActionCommand().equals("1") && copper >= 50) {
			         			theGUI.commandField.removeActionListener(this);
								copper -= 50;
								health += 25;

								if(drunk > 0) {

									drunk = 0;
								}

								else if(drunk >= 5) {

									health += 5;
									attackDmg += 5;
									drunk = 0;
								}

								if(health > maxHealth) {
									health = maxHealth;
								}

								System.out.println("You hand over the money and head upstairs for some much needed rest."
										+ "\n"
										+ "\nZZzzzzzZZZzzzzz"
										+ "\n"
										+ "\nYou awake feeling refreshed and ready for an adventure!"
										+ "\n");
								pInfoUpdate();
								inn();
							}

							else if(e.getActionCommand().equals("1") && copper < 50) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for a room. "
										+ "\n");
								innkeeper();
							}
			         	}
					});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You want a residence here eh?  That will be 5 gold Carlons."
							+ "\n");
					System.out.println("1: Buy room");
					System.out.println("2: Nevermind"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
			      		
			         	@Override
			         	public void actionPerformed(ActionEvent e) {
			         		
			         		if(e.getActionCommand().equals("1") && gold >= 5 && roomState == 0) {
			         			theGUI.commandField.removeActionListener(this);			         			
								roomState = 1;
								gold -= 10;
								System.out.println("You purchase a place to call home"
										+ "\nThe Innkeeper hands you a key and tells you the room is upstairs."
										+ "\n");
								innkeeper();
							}

							else if(e.getActionCommand().equals("1") && gold < 5 || roomState == 1) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You either do not have enough coins, or already purchased the room."
										+ "\n");
								innkeeper();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
								innkeeper();
							}		         		
			         	}
					});
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You have second thoughts and turn away."
							+ "\n");
					inn();
				}

				else if(e.getActionCommand().equals("4") && roomState == 1) {
					theGUI.commandField.removeActionListener(this);
					health += 1000;

					if(drunk > 0) {

						drunk = 0;
					}

					else if(drunk >= 5) {

						health += 5;
						attackDmg += 5;
						drunk = 0;
					}

					if(health > maxHealth) {
						health = maxHealth;
					}

					System.out.println("You go up to your room and open the door."
							+ "\nInside are simple commodities, most importantly a comfortable bed."
							+ "\nYou go and lay down for some sleep."
							+ "\n"
							+ "\nZZzzzzzZZZzzzzzzzzZZZZZZzzzzzzzzZZZZzz"
							+ "\n"
							+ "\nYou awake feeling refreshed and ready for an adventure!"
							+ "\n");
					pInfoUpdate();
					inn();
				}
         	}
		});
	}
	
	public void cityBarkeeper() {
		
		System.out.println("1: Beer");
		System.out.println("2: Apple Pie");
		System.out.println("3: Rumors");
		System.out.println("4: Nevermind"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Want a beer?  That will be ten copper Carlons."
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && copper >= 10) {
         	         			theGUI.commandField.removeActionListener(this);
								beer ++;
								copper -= 10;
								System.out.println("You purchase a beer. "
										+ "\n");
								System.out.println("The Barkeeper pours out a cold one."
										+ "\nWell then, here you are.  Enjoy!"
										+ "\n");
								cityBarkeeper();
							}

							else if(e.getActionCommand().equals("1") && copper < 10) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough to buy a drink. "
										+ "\n");
								cityBarkeeper();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
								cityBarkeeper();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Want a piece of pie?  One silver Carlon please."
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 1) {
         	         			theGUI.commandField.removeActionListener(this);
								applePie ++;
								silver -= 1;
								System.out.println("You purchase a piece of fresh pie. "
										+ "\n");
								System.out.println("The Barkeeper slides you over a plate."
										+ "\nHere you are, best pie in the city.  Enjoy!"
										+ "\n");
								cityBarkeeper();
							}

							else if(e.getActionCommand().equals("1") && silver < 1) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for pie. "
										+ "\n");
								cityBarkeeper();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
								cityBarkeeper();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("From what I have heard, the King is sending brave folk on a quest."
							+ "\nHe said it is extremely dangerous, and not to be taken lightly."
							+ "\nIf you are feeling up to it, go seek an audience with His Majesty."
							+ "\n");
         			cityBarkeeper();      			
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You have second thought and turn away."
							+ "\n");
         			inn();
         		}
         	}
		});
	}
	
	public void shops() {
		area = 7;
		theGUI.locationLabel.setText(" Your location: Shopping District");
		
		System.out.println("Where would you like to go?"
				+ "\n");
		System.out.println("1: General Store");
		System.out.println("2: Weapons and Armor Shop");
		System.out.println("3: Magic Shop");
		System.out.println("4: Return to Main Street");
		System.out.println("5: Castle");
		System.out.println("6: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You push open a solid stone door and enter the General Store."
							+ "\nThere appears to be several people browsing it's wares."
							+ "\nThe Shopkeeper welcomes you from behind the counter. "
							+ "\n"
							+ "\nHe kindly says, Just take a look around and come up to pay when you are ready."
							+ "\n");
         			generalStore();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You enter what appears to be an active Smithy."
							+ "\nIt has a section for buying, and another for producing."
							+ "\nThe Smith calls to you as you enter, Hello!"
							+ "\nWe have what you need on display over by the counter."
							+ "\nJust be careful not to cut or burn yourself."
							+ "\n");
         			wepShop();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You wander into the magic shop, a blast of strange aromas fill your nose"
							+ "\nA few Mage's Guild members are browsing alchemical ingredients."
							+ "\nThe shop owner says without looking at you, What can I do for you young one?."
							+ "\n");
         			magicShop();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 6;
         			System.out.println("You return to the main street."
							+ "\n");
         			city();
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 9;
    				System.out.println("Looking toward the north end of the sprawling city,"
    						+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
    						+ "\n");
    				System.out.println("You walk up to the castle's imposing gates."
    						+ "\nIt is heavily guarded by crossbowmen."
    						+ "\nFortunately without difficulty, you enter the gate and head inside."
    						+ "\n");
    				castle();
         		}
         		
         		else if(e.getActionCommand().equals("6")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});		
	}
	
	public void generalStore() {
		
		System.out.println("What would you like to buy?"
				+ "\n");
		System.out.println("1: Apple\t\t15 copper");
		System.out.println("2: Bread\t\t20 copper");
		System.out.println("3: Health Potion\t\t50 copper");
		System.out.println("4: Mana Potion\t\t50 copper");
		System.out.println("5: Apple Pie\t\t1 silver");
		System.out.println("6: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         			
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy an apple for 15 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && copper >= 15) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			apple ++;
        		         			copper -= 15;
        		         			System.out.println("You have bought an apple."
        		         					+ "\n");
        		         			generalStore();
        		         		}

        		         		else if(e.getActionCommand().equals("1") && copper < 15) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You do not have enough for this item."
        		         					+ "\n");
        		         			generalStore();
        		         		}
        		         		
        		         		else if(e.getActionCommand().equals("2")) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You continue looking."
        		    						+ "\n");
        		         			generalStore();
        		         		}
        		         		
        		         	}
        			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy bread for 20 copper Carlons?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && copper >= 20) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			bread ++;
        		    				copper -= 20;
        		    				System.out.println("You have bought a loaf of bread."
        		    						+ "\n");
        		    				generalStore();
        		         		}

        		         		else if(e.getActionCommand().equals("1") && copper < 20) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You do not have enough for this item."
        		         					+ "\n");
        		         			generalStore();
        		         		}
        		         		
        		         		else if(e.getActionCommand().equals("2")) {
        		         			theGUI.commandField.removeActionListener(this);
        		         			
        		         			System.out.println("You continue looking."
        		    						+ "\n");
        		         			generalStore();
        		         		}        		         		
        		         	}
        		         	
        			});

         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy health potion for 50 copper Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && copper >= 50) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			healthPots ++;
         		         			copper -= 50;
         		         			System.out.println("You have bought a health potion."
         		         					+ "\n");
         		         			generalStore();
         		         		}

         		         		else if(e.getActionCommand().equals("1") && copper < 50) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You do not have enough for this item."
         		         					+ "\n");
         		         			generalStore();
         		         		}
         		         		
         		         		else if(e.getActionCommand().equals("2")) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You continue looking."
         		    						+ "\n");
         		         			generalStore();
         		         		}        		         		
         		         	}
         		         	
         			});

         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy mana potion for 50 copper Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && copper >= 50) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			manaPots ++;
         		         			copper -= 50;
         		         			System.out.println("You have bought a mana potion."
         		         					+ "\n");
         		         			generalStore();
         		         		}

         		         		else if(e.getActionCommand().equals("1") && copper < 50) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You do not have enough for this item."
         		         					+ "\n");
         		         			generalStore();
         		         		}
         		         		
         		         		else if(e.getActionCommand().equals("2")) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You continue looking."
         		    						+ "\n");
         		         			generalStore();
         		         		}        		         		
         		         	}
         		         	
         			});

         		}
         	
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy apple pie for 1 silver Carlon?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && silver >= 1) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			applePie ++;
         		         			silver -= 1;
         		         			System.out.println("You have bought an apple pie."
         		         					+ "\n");
         		         			generalStore();
         		         		}

         		         		else if(e.getActionCommand().equals("1") && silver < 1) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You do not have enough for this item."
         		         					+ "\n");
         		         			generalStore();
         		         		}
         		         		
         		         		else if(e.getActionCommand().equals("2")) {
         		         			theGUI.commandField.removeActionListener(this);
         		         			
         		         			System.out.println("You continue looking."
         		    						+ "\n");
         		         			generalStore();
         		         		}        		         		
         		         	}      		         	
         			});
         		}
         		
         		else if(e.getActionCommand().equals("6")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("You are satisfied with your visit and leave the store."
        					+ "\n");
         			shops();         			
         		}         			         		
         	}        	
		});	
	}
	
	public void wepShop() {
		
		System.out.println("What would you like to buy?"
				+ "\n");
		System.out.println("1: Iron Sword\t\t5 silver");
		System.out.println("2: Iron Shield\t\t5 silver");
		System.out.println("3: Steel Sword\t\t15 silver");
		System.out.println("4: Steel Shield\t\t15 silver");
		System.out.println("5: Mithril Sword\t\t30 silver");
		System.out.println("6: Mithril Shield\t\t30 silver");
		System.out.println("7: Sell");
		System.out.println("8: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy iron sword for 5 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 5) {
         	         			theGUI.commandField.removeActionListener(this);
								ironSword ++;
								silver -= 5;
								System.out.println("You have bought an iron sword."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 5) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy iron shield for 5 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 5) {
         	         			theGUI.commandField.removeActionListener(this);
								ironShield ++;
								silver -= 5;
								System.out.println("You have bought an iron shield."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 5) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy steel sword for 15 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 15) {
         	         			theGUI.commandField.removeActionListener(this);
								steelSword ++;
								silver -= 15;
								System.out.println("You have bought a steel sword."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 15) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         		
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy steel shield for 15 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 15) {
         	         			theGUI.commandField.removeActionListener(this);
								steelShield ++;
								silver -= 15;
								System.out.println("You have bought a steel shield."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 15) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         		
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy mithril sword for 30 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 30) {
         	         			theGUI.commandField.removeActionListener(this);
								mithrilSword ++;
								silver -= 30;
								System.out.println("You have bought a mithril sword."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 30) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         		
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("6")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy mithril shield for 30 silver Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 30) {
         	         			theGUI.commandField.removeActionListener(this);
								mithrilShield ++;
								silver -= 30;
								System.out.println("You have bought a mithril shield."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 30) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								wepShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								wepShop();
							}
         	         		
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("7")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("The smith owner asks, What can you offer me?"
							+ "\nHe also says, I only buy quality goods."
							+ "\n");
         			wepShopSell();
         		}
         		
         		else if(e.getActionCommand().equals("8")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You are satisfied with your visit and leave the shop."
							+ "\n");
         			shops();
         		}
         		
         	}
		});
	}
	
	public void wepShopSell() {
		
		System.out.println("What would you like to sell?"
				+ "\n");
		System.out.println("1: Steel Sword\t\t5 silver");
		System.out.println("2: Steel Shield\t\t5 silver");
		System.out.println("3: Mithril Sword\t\t10 silver");
		System.out.println("4: Mithril Shield\t\t10 silver");
		System.out.println("5: Adamant Sword\t30 silver");
		System.out.println("6: Adamant Shield\t30 silver");
		System.out.println("7: Buy"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("1") && steelSword > 0 && currentSwordEquip != "Steel Sword") {
					theGUI.commandField.removeActionListener(this);
					steelSword --;
					silver += 5;
					System.out.println("You sell the steel sword for 5 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("1") && steelSword == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a steel sword."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("1") && currentSwordEquip == "Steel sword") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your sword to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("2") && steelShield > 0 && currentShieldEquip != "Steel Shield") {
					theGUI.commandField.removeActionListener(this);
					steelShield --;
					silver += 5;
					System.out.println("You sell the steel shield for 5 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("2") && steelShield == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a steel shield."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("2") && currentShieldEquip == "Steel Shield") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your shield to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("3") && mithrilSword > 0 && currentSwordEquip != "Mithril Sword") {
					theGUI.commandField.removeActionListener(this);
					mithrilSword --;
					silver += 10;
					System.out.println("You sell the mithril sword for 10 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("3") && mithrilSword == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a mithril sword."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("3") && currentSwordEquip == "Mithril sword") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your sword to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("4") && mithrilShield > 0 && currentShieldEquip != "Mithril Shield") {
					theGUI.commandField.removeActionListener(this);
					mithrilShield --;
					silver += 10;
					System.out.println("You sell the mithril shield for 10 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("4") && mithrilShield == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have a mithril shield."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("4") && currentShieldEquip == "Mithril Shield") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your shield to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("5") && adamantSword > 0 && currentSwordEquip != "Adamant Sword") {
					theGUI.commandField.removeActionListener(this);
					adamantSword --;
					silver += 30;
					System.out.println("You sell the adamant sword for 30 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("5") && adamantSword == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have an adamant sword."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("5") && currentSwordEquip == "Adamant sword") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your sword to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("6") && adamantShield > 0 && currentShieldEquip != "Adamant Shield") {
					theGUI.commandField.removeActionListener(this);
					adamantShield --;
					silver += 30;
					System.out.println("You sell the adamant shield for 30 silver."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("6") && mithrilShield == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You do not have an adamant shield."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("6") && currentShieldEquip == "Adamant Shield") {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must first remove your shield to sell."
							+ "\n");
					wepShopSell();
				}

				else if(e.getActionCommand().equals("7")) {
					theGUI.commandField.removeActionListener(this);
					wepShop();
				}				
			}			
		});	
	}
	
	public void magicShop() {
			
		System.out.println("What would you like to buy?"
				+ "\n");
		System.out.println("1: Mana Potion\t\t50 copper");
		System.out.println("2: Learn spell: Absorb\t2 silver");
		System.out.println("3: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy mana potion for 50 copper Carlons?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
					
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && copper >= 50) {
         	         			theGUI.commandField.removeActionListener(this);
								manaPots ++;
								copper -= 50;
								System.out.println("You have bought a mana potion."
										+ "\n");
								magicShop();
							}

							else if(e.getActionCommand().equals("1") && copper < 50) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this item."
										+ "\n");
								magicShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								magicShop();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Learn absorb for 2 silver Carlons?"
							+ "\n");
					System.out.println("1: Learn");
					System.out.println("2: Nevermind"
							+ "\n");

         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1") && silver >= 2 && spellState == 0) {
         	         			theGUI.commandField.removeActionListener(this);
								spellState = 1;
								silver -= 2;
								System.out.println("You have been taught how to use the absorb spell!"
										+ "\n");
								magicShop();
							}
         	         		
         	         		else if(e.getActionCommand().equals("1") && spellState == 1) {
         	         			theGUI.commandField.removeActionListener(this);
         	         			
								System.out.println("You have already learned this spell."
										+ "\n");
								magicShop();
							}

							else if(e.getActionCommand().equals("1") && silver < 2) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough to learn this spell."
										+ "\n");
								magicShop();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You continue looking."
										+ "\n");
								magicShop();
							}
         	         	}
         			});
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);         			
         			
         			System.out.println("You say goodbye and leave the shop."
							+ "\n");
         			shops();
         		}
         	}
		});
	}
	
	public void guilds() {
		area = 8;
		theGUI.locationLabel.setText(" Your location: Guild District");
		
		System.out.println("Where would you like to go?"
				+ "\n");
		System.out.println("1: Warriors Guild");
		System.out.println("2: Mages Guild");
		System.out.println("3: Merchants Guild");
		System.out.println("4: Return to Main Street");
		System.out.println("5: Castle");
		System.out.println("6: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			if(guildState == 0) {

						System.out.println("You step foot inside the Warriors guild."
								+ "\nThere are all sorts of weapons in display cases and robust armor on the walls."
								+ "\nA burly man with white armor and a clipboard walks up to you."
								+ "\nHello there, he says, what can I do for you stranger."
								+ "\n");
						warriorsGuild();
					}

					else if(guildState == 1) {

						System.out.println("You walk back into the guild and the man in white notices you."
								+ "\nWelcome back " + name + "!  What is on the agenda today?"
								+ "\n");
						warriorsGuild();
					}       			
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Guild Hall currently closed."
         					+ "\n");
         			guilds();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);        			
         			System.out.println("You walk through gilded doors and into the Merchants guild."
							+ "\nThere are a great many people rummaging through papers and signing documents."
							+ "\nYou notice booths meant for money exchange and questions."
							+ "\nThe clerks behind them welcome you and beckon you to step forward."
							+ "\n");
         			merchantsGuild();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);     			
         			area = 6;
         			System.out.println("You return to the main street."
							+ "\n");
         			city();
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 9;
    				System.out.println("Looking toward the north end of the sprawling city,"
    						+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
    						+ "\n");
    				System.out.println("You walk up to the castle's imposing gates."
    						+ "\nIt is heavily guarded by crossbowmen."
    						+ "\nFortunately without difficulty, you enter the gate and head inside."
    						+ "\n");
    				castle();
         		}
         		
         		else if(e.getActionCommand().equals("6")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});		
	}
	
	public void warriorsGuild() {
		
		if(guildState == 0) {

			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Ask about Guild");
			System.out.println("2: Join Guild");
			System.out.println("3: Leave"
					+ "\n");
			
			theGUI.commandField.addActionListener(new AbstractAction() {
	      		
	         	@Override
	         	public void actionPerformed(ActionEvent e) {
	         		
	         		if(e.getActionCommand().equals("1")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
						System.out.println("The man responds, We are a guild for warriors and heros."
								+ "\nOur numbers are few these days, but we are actively recruiting."
								+ "\nWe take on dangerous quests, and fight fearsome foes!"
								+ "\nYou look the fighting type, perhaps you should consider joining."
								+ "\n");
						warriorsGuild();
					}

					else if(e.getActionCommand().equals("2")) {
						theGUI.commandField.removeActionListener(this);
						
						System.out.println("So you want to join?  Great!"
								+ "\nJust sign these forms and waivers, simple legal issues."
								+ "\nBasically we are not held liable for your injury or death."
								+ "\n");
						System.out.println("What would you like to do?"
								+ "\n");
						System.out.println("1: Join");
						System.out.println("2: Nevermind"
								+ "\n");
						
						theGUI.commandField.addActionListener(new AbstractAction() {
				      		
				         	@Override
				         	public void actionPerformed(ActionEvent e) {
				         		
				         		if(e.getActionCommand().equals("1")) {
				         			theGUI.commandField.removeActionListener(this);
									guildState = 1;
									System.out.println("Perfect! The man says.  Just finish filling these out."
											+ "\nCome speak to me when you are done, I can get you going."
											+ "\n");
									warriorsGuild();
								}
				         		
								else if(e.getActionCommand().equals("2")) {
									theGUI.commandField.removeActionListener(this);
									System.out.println("You decide against it and tell him perhaps another time."
											+ "\n");
									warriorsGuild();
								}
				         	}
						});
					}
	         		
					else if(e.getActionCommand().equals("3")) {
						theGUI.commandField.removeActionListener(this);
						System.out.println("You decide to leave the Guild Hall."
								+ "\n");
						guilds();
					}
	         	}
			});			
		}
		
		else if(guildState == 1) {
			
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Aquire Mission");
			System.out.println("2: Browse Guild Shop");
			System.out.println("3: Leave"
					+ "\n");
			
			theGUI.commandField.addActionListener(new AbstractAction() {
	      		
	         	@Override
	         	public void actionPerformed(ActionEvent e) {
	         		
	         		if(e.getActionCommand().equals("1")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("You walk over to the Mission Board and see what is available."
								+ "\nThe man in white walks over and checks with you."
								+ "\nWell, he says, right now we only have monster clearing missions."
								+ "\nBasically you kill a good number of them, and we can reward you."
								+ "\n");
	         			missionBoard();
	         		}
	         		
	         		else if(e.getActionCommand().equals("2")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("You walk over to what appears to be a conventional shop."
								+ "\nThe Guild Shopkeeper sees you coming and kindly says."
								+ "\nThe only way to buy special items here is to spend Guild Tokens."
								+ "\n");
	         			guildShop();
	         		}
	         		
	         		else if(e.getActionCommand().equals("3")) {
	         			theGUI.commandField.removeActionListener(this);
	         			
	         			System.out.println("You say goodbye and leave the guild hall."
								+ "\n");
	         			guilds();
	         		}
	         	}
			});
		}
	}
	
	public void missionBoard() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Accept Mission");
		System.out.println("2: Finish Mission");
		System.out.println("3: Leave board"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && guildMission == 0) {
         			theGUI.commandField.removeActionListener(this);
					enemiesDefeated = 0;
					guildMission ++;
					System.out.println("You accept the kill mission."
							+ "\nYou must kill 100 monsters to complete this mission."
							+ "\n");
					missionBoard();
				}

				else if(e.getActionCommand().equals("1") && guildMission == 1) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You already have a mission to complete."
							+ "\n");
					missionBoard();
				}

				else if(e.getActionCommand().equals("2") && guildMission == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You must aquire a mission to finish it."
							+ "\n");
					missionBoard();
				}

				else if(e.getActionCommand().equals("2") && guildMission == 1)  {
					theGUI.commandField.removeActionListener(this);
					
					if(enemiesDefeated < 100) {

						System.out.println("You have not killed enough monsters to finish your mission."
								+ "\nYou have killed " + enemiesDefeated + "/100 monsters."
								+ "\n");
						missionBoard();
					}

					else if(enemiesDefeated > 99) {

						guildMission = 0;
						silver += 5;
						warriorToken ++;
						System.out.println("You turn in the mission and collect the reward."
								+ "\nYou have gained 5 silver coins, and 1 warrior token."
								+ "\n");
						missionBoard();
					}
				}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk away from the board."
         					+ "\n");
         			warriorsGuild();
         		}
         	}
		});
	}
	
	public void guildShop() {
		
		System.out.println("What would you like to buy?"
				+ "\n");
		System.out.println("1: Super Health Potion\t1 silver");
		System.out.println("2: Guild Sword\t\t1 token");
		System.out.println("3: Guild Shield\t\t1 token");
		System.out.println("4: Leave shop"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy super health potion for 1 silver Carlon?"
							+ "\n");
					System.out.println("1: Buy");
					System.out.println("2: Nevermind"
							+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && silver >= 1) {
        		         			theGUI.commandField.removeActionListener(this);
									superHealthPots ++;
									silver --;
									System.out.println("You have bought a super health potion."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("1") && silver < 1) {
									theGUI.commandField.removeActionListener(this);
									System.out.println("You do not have enough for this item."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("2")) {
									theGUI.commandField.removeActionListener(this);
									System.out.println("You continue looking."
											+ "\n");
									guildShop();
								}  		
        		         	}
        			});
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Buy guild sword for 1 token?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
        			
        			theGUI.commandField.addActionListener(new AbstractAction() {
        		      		
        		         	@Override
        		         	public void actionPerformed(ActionEvent e) {    
        		         		
        		         		if(e.getActionCommand().equals("1") && warriorToken >= 1) {
        		         			theGUI.commandField.removeActionListener(this);
									guildSword ++;
									warriorToken --;
									System.out.println("You have bought a guild sword."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("1") && warriorToken < 1) {
									theGUI.commandField.removeActionListener(this);
									
									System.out.println("You do not have enough for this item."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("2")) {
									theGUI.commandField.removeActionListener(this);
									
									System.out.println("You continue looking."
											+ "\n");
									guildShop();
								}         		
        		         	}
        		         	
        			});

         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         				
         			System.out.println("Buy guild shield for 1 token?"
        					+ "\n");
        			System.out.println("1: Buy");
        			System.out.println("2: Nevermind"
        					+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         		      		
         		         	@Override
         		         	public void actionPerformed(ActionEvent e) {    
         		         		
         		         		if(e.getActionCommand().equals("1") && warriorToken >= 1) {
         		         			theGUI.commandField.removeActionListener(this);
									guildShield ++;
									warriorToken --;
									System.out.println("You have bought a guild shield."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("1") && warriorToken < 1) {
									theGUI.commandField.removeActionListener(this);
									
									System.out.println("You do not have enough for this item."
											+ "\n");
									guildShop();
								}

								else if(e.getActionCommand().equals("2")) {
									theGUI.commandField.removeActionListener(this);
									
									System.out.println("You continue looking."
											+ "\n");
									guildShop();
								}         		
         		         	}
         		         	
         			});

         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk away from the guild shop."
							+ "\n");
         			warriorsGuild();
         		}
         		
         	}
		});	
	}
	
	public void merchantsGuild() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Inquire about joining");
		System.out.println("2: Exchange currency");
		System.out.println("3: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You ask about joining this prestigious organization."
							+ "\nThe clerk sizes you up estimating your worth."
							+ "\nHe then tells you that they are currently not accepting new members."
							+ "\n");
         			merchantsGuild();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("Which coin would you like to exchange?"
							+ "\n");
					System.out.println("1: Gold - Silver");
					System.out.println("2: Silver - Copper");
					System.out.println("3: Nevermind"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
			      		
			         	@Override
			         	public void actionPerformed(ActionEvent e) {
			         		
			         		if(e.getActionCommand().equals("1") && gold > 0) {
			         			theGUI.commandField.removeActionListener(this);
								gold --;
								silver += 100;
								System.out.println("You exchange 1 gold coin for 100 silver coins."
										+ "\n");
								merchantsGuild();
							}

							else if(e.getActionCommand().equals("1") && gold == 0) {
								theGUI.commandField.removeActionListener(this);
								System.out.println("You do not have any gold coins to exchange."
										+ "\n");
								merchantsGuild();
							}

							else if(e.getActionCommand().equals("2") && silver > 0) {
								theGUI.commandField.removeActionListener(this);
								silver --;
								copper += 100;
								System.out.println("You exchange 1 silver coin for 100 copper coins."
										+ "\n");
								merchantsGuild();
							}

							else if(e.getActionCommand().equals("2") && silver == 0) {
								theGUI.commandField.removeActionListener(this);
								System.out.println("You do not have any silver coins to exchange."
										+ "\n");
								merchantsGuild();
							}
			         		
							else if(e.getActionCommand().equals("3")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You change your mind about changing your change."
										+ "\n");
								merchantsGuild();
							}
			         	}
					});
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk out of the Guild Hall."
         					+ "\n");
         			guilds();
         		}
         	}
		});
	}
	
	public void castle() {
		area = 9;
		theGUI.locationLabel.setText(" Your location: Castle");
		
		System.out.println("Where would you like to go?"
				+ "\n");
		System.out.println("1: Throne room");
		System.out.println("2: Barracks");
		System.out.println("3: Return to city");
		System.out.println("4: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You walk through the courtyard of the castle and enter large double doors."
    						+ "\nInside expands into a Grand Hall, filled with Honor Guards and Noblepeople"
    						+ "\nYou see, sitting on a throne made of pure sapphire, is Carlon, King of the Realm."
    						+ "\n");
         			throneRoom();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			if(gameBeat == 0) {
         				System.out.println("You enter into the Castle Barracks, to find guards and knights sleeping."
         						+ "\nA few are awake and acknowledge your entry suspisciously."
         						+ "\n");
         				barracks();
         			}
         			
         			else if(gameBeat == 1) {
         				System.out.println("You enter into the Castle Barracks, to find guards and knights sleeping."
    							+ "\nA few are awake and acknowledge your entry."
    							+ "\n");
             			barracks();
         			}
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);        			
         			area = 6;
         			System.out.println("You return to the city."
							+ "\n");
         			city();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});
		
	}
	
	public void throneRoom() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Speak to the King");
		System.out.println("2: Speak to a Nobleperson");
		System.out.println("3: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && kingState == 1 && gameBeat == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You approach the King and the noble you spoke to yells over,"
         					+ "\nLet this man pass, he needs to see the King!"
         					+ "\nThe group in front of the Throne lets you through."
         					+ "\n"
         					+ "\nThe King looks down on you sternly."
         					+ "\nWell son, speak up.  Are you here for my quest?"
         					+ "\n");

         			System.out.println("What would you like to do?"
         					+ "\n");
         			System.out.println("1: Ask about Quest");
         			System.out.println("2: Leave"
         					+ "\n");
         			
         			theGUI.commandField.addActionListener(new AbstractAction() {
         	      		
         	         	@Override
         	         	public void actionPerformed(ActionEvent e) {
         	         		
         	         		if(e.getActionCommand().equals("1")) {
         	         			theGUI.commandField.removeActionListener(this);
         	         			
         	         			System.out.println("You inquire about this quest."
         	         					+ "\nThe King states that there is a Large Dragon in the Black Mountains."
         	         					+ "\nIt is wreaking havoc on his kingdom and must be killed."
         	         					+ "\nThe task is perilous, but the reward is great."
         	         					+ "\n");
         	         			System.out.println("What would you like to do?"
         	         					+ "\n");
         	         			System.out.println("1: Accept");
         	         			System.out.println("2: Deny"
         	         					+ "\n");
							
         	         			theGUI.commandField.addActionListener(new AbstractAction() {
		         	      		
         	         				@Override
         	         				public void actionPerformed(ActionEvent e) {
		         	         		
         	         					if(e.getActionCommand().equals("1")) {
         	         						theGUI.commandField.removeActionListener(this);
         	         						finalQuest = 1;
         	         						theGUI.questDisplay.setText("Dragon Slayer");
         	         						System.out.println("You accept the task of killing this dragon."
         	         								+ "\nThe King is surprised, but pleased."
         	         								+ "\nHe says, I hearby grant you passage to the mountains!"
         	         								+ "\nKnow that your life is in your hands, but be careful nonetheless."
         	         								+ "\nHe then waves you off in order to tend to other matters."
         	         								+ "\n");
         	         						throneRoom();
         	         					}

         	         					else if(e.getActionCommand().equals("2")) {
         	         						theGUI.commandField.removeActionListener(this);
										
         	         						System.out.println("You think about it and decide not to accept this quest."
         	         								+ "\n");
         	         						throneRoom();
         	         					}		         	         		
         	         				}
         	         			});		         			         	      			
         	         		}
         	         		
         	         		else if(e.getActionCommand().equals("1") && finalQuest == 1) {
         	         			theGUI.commandField.removeActionListener(this);
         	         			
    							System.out.println("You have already accepted this quest."
    									+ "\n");
    							throneRoom();
    						}

    						else if(e.getActionCommand().equals("2")) {
    							theGUI.commandField.removeActionListener(this);
    							
    							System.out.println("You bow politely and walk away from the King."
    									+ "\n");
    							throneRoom();
    						}
         	         	}
         			});
         		}
				
         		else if(e.getActionCommand().equals("1") && kingState == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You attempt to approach the King; however, too many people block the way."
							+ "\n");
					throneRoom();
				}

				else if(e.getActionCommand().equals("1") && gameBeat == 1) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You walk up to the King exhausted and bloody, he looks at you surprised."
							+ "\nThe only reason a man would come to me in this state, is with dire news."
							+ "\nSpeak now what troubles you young adventurer."
							+ "\n"
							+ "\nYou tell the King and his court of your quest.  They are enthralled by your story."
							+ "\nYou finish it by describing the death of the Ancient Dragon."
							+ "\n"
							+ "\nThe King looks awestruck, for proof you show him the weapon and shield."
							+ "\nHe looks at you with warm adoration and beckons you to come forth."
							+ "\nThe King takes out his sword and so dubs you a knight of his court."
							+ "\nWith that, he says, you may now enter the barracks for rest."
							+ "\nCongratulations Hero!"
							+ "\n");
					throneRoom();
				}

				else if(e.getActionCommand().equals("2") && kingState == 1) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("The Nobleman you spoke to just smiles and bows."
							+ "\nHe does not wish to speak further and turns back to his friends."
							+ "\n");
					throneRoom();
				}
         		
				else if(e.getActionCommand().equals("2") && kingState == 0) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You walk up to a Nobleman and get his attention."
							+ "\nHe looks at you judgementally and is about to get back to his friends."
							+ "\n");
					nobleman();
				}
         		
				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You leave the Throne Room and it's grandeur behind."
							+ "\n");
					castle();
				}
         	}
		});
	}
	
	public void nobleman() {
		
		System.out.println("What do you do?"
				+ "\n");
		System.out.println("1: Speak to him");
		System.out.println("2: Bow");
		System.out.println("3: Leave him alone"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && nobleState == 1) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("He says, I am assuming you wish to speak to King Carlon?"
							+ "\nThat privilege does not come cheap young one, ten silver."
							+ "\n");
					System.out.println("What do you do?"
							+ "\n");
					System.out.println("1: Hand over 10 silver Carlons");
					System.out.println("2: Leave him alone"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
			      		
			         	@Override
			         	public void actionPerformed(ActionEvent e) {
			         		
			         		if(e.getActionCommand().equals("1") && silver >= 10) {
			         			theGUI.commandField.removeActionListener(this);
								kingState = 1;
								System.out.println("You hand over ten silver coins, and he greedily takes them."
										+ "\nHe says, Just walk back up to the King, and I will vouch for you."
										+ "\n");
								nobleman();
							}

							else if(e.getActionCommand().equals("1") && silver < 10) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You do not have enough for this."
										+ "\n");
								nobleman();
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("You turn and leave this greedy man alone."
										+ "\n");
								throneRoom();
							}
			         	}
					});
         		}
         		
         		else if(e.getActionCommand().equals("1") && nobleState == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("He ignores you and turns back around."
							+ "\n");
					nobleman();
				}

				else if(e.getActionCommand().equals("2")) {
					theGUI.commandField.removeActionListener(this);
					nobleState = 1;
					System.out.println("You bow courteously to this man, and he returns in kind."
							+ "\n");
					nobleman();
				}

				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You turn and leave this man alone."
							+ "\n");
					throneRoom();
				}
         	}
		});
	}
	
	public void barracks() {
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Sleep in a vacant bed");
		System.out.println("2: Leave"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && gameBeat == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You walk over and attempt to lay down, but a knight stops you."
							+ "\nThis is not for public use!  Only those in the King's service sleep here."
							+ "\n");
					barracks();
				}

         		else if(e.getActionCommand().equals("1") && gameBeat == 1) {
         			theGUI.commandField.removeActionListener(this);
					health += 5;

					if(drunk > 0) {

						drunk = 0;
					}

					else if(drunk >= 5) {

						health += 5;
						attackDmg += 5;
						drunk = 0;
					}

					if(health > maxHealth) {
						health = maxHealth;
					}

					System.out.println("You walk over to a bed and lay down."
							+ "\n"
							+ "\nZZzzzzzZZZzzzzzzzzZZZZZZzzzzzzz"
							+ "\n"
							+ "\nYou awake feeling slightly refreshed and ready for an adventure!"
							+ "\n");
					pInfoUpdate();
					barracks();
				}
				
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You leave the barracks and return to the Castle's Courtyard."
							+ "\n");
					castle();
				}
         	}
		});
	}
	
	public void mountain() {
		area = 10;
		theGUI.locationLabel.setText(" Your location: Black Mountains");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Continue toward Dragon's Nest");
		System.out.println("2: Search for a monster to fight");
		System.out.println("3: Return to city");
		System.out.println("4: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && nestPath == 1) {
         			theGUI.commandField.removeActionListener(this);
    				area = 12;
    				System.out.println("You continue climbing towards the Dragon's Nest."
    						+ "\n");
    				dragonAerie();
    			}
         		
         		else if(e.getActionCommand().equals("1") && nestPath == 0) {
         			theGUI.commandField.removeActionListener(this);

         			String[] mobs = {"Ancient Dragon Guardian"};
         			mobHealth = 150;
         			mobAD = 70;
         			mobEXP = 150;
         			mobMoney = 5;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# An " + enemy + " has appeared! #"
         					+ "\n");
         			area = 11;
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);   
         			
         			String[] mobs = {"Ogre", "Mountain Giant", "Giant Boar", "Centaur", "Dire Wolf"};
         			mobHealth = 100;
         			mobAD = 30;
         			mobEXP = 100;
         			mobMoney = 1;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 6;
         			System.out.println("You turn away from the mountain range, and go back to the city."
    						+ "\n");
    				city();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});
	}
	
	public void dragonAerie() {
		area = 12;
		theGUI.locationLabel.setText(" Your location: Dragon Aerie");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Go up stairway");
		System.out.println("2: Fight a monster");
		System.out.println("3: Retreat down mountain");
		System.out.println("4: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && nestKills >= 20) {
         			theGUI.commandField.removeActionListener(this);
         			area = 13;
         			System.out.println("You finally thin out the monsters enough to climb the stairway."
    						+ "\n");
    				System.out.println("As you approach the top, you feel the mountain radiating with heat."
    						+ "\nIt is mountainside is as black as the void, with no living creature in sight."
    						+ "\nThe stairs suddenly stop at the peak of the mountain."
    						+ "\nOn a spacious plateau is the largest beast you have ever seen."
    						+ "\n");
    				dragonNest();
         		}
         		
         		else if(e.getActionCommand().equals("1") && nestKills < 19 && nestKills > 10) {
         			theGUI.commandField.removeActionListener(this);
         			
    				System.out.println("You attempt to approach the stairway,"
    						+ "\nYou make some progress, but are forced back again.");
    				dragonAerie();
    			}

    			else if(e.getActionCommand().equals("1") && nestKills < 10) {
    				theGUI.commandField.removeActionListener(this);
    				
    				System.out.println("You attempt to approach the stairway,"
    						+ "\nBut you are forced back because there are too many monsters.");
    				dragonAerie();
    			}
         		
    			else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			String[] mobs = {"Hatchling Dragon", "Young Drake", "Giant Eagle", "Dragon Cultist", "Strong Fire Elemental"};
         			mobHealth = 150;
         			mobAD = 60;
         			mobEXP = 150;
         			mobMoney = 5;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
    			}
         		
    			else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 10;
         			System.out.println("You retreat back down the mountain."
    						+ "\n");
         			mountain();
    			}
         		
    			else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
    			}
         	}
		});
	}
	
	public void dragonNest() {
		area = 13;
		theGUI.locationLabel.setText(" Your location: Dragon Nest");
		
		System.out.println("What would you like to do?"
				+ "\n");
		System.out.println("1: Fight the Dragon");
		System.out.println("2: Search the Dragon's Nest");
		System.out.println("3: Fight a monster");
		System.out.println("4: Retreat down mountain");
		System.out.println("5: Inventory"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1") && gameBeat == 1) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You look at the body of the dead Ancient Dragon."
							+ "\nSmiling, you know you are a hero."
							+ "\n");
         			dragonNest();
         		}
         		
         		else if(e.getActionCommand().equals("1") && gameBeat == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
         			String[] mobs = {"Ancient Dragon"};
         			mobHealth = 400;
         			mobAD = 90;
         			mobEXP = 1000;
         			mobMoney = 1;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");
         			area = 14;
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("2") && gameBeat == 0) {
         			theGUI.commandField.removeActionListener(this);
         			
					System.out.println("You cannot approach the Dragon's Nest with it still alive."
							+ "\n");
					dragonNest();
				}

				else if(e.getActionCommand().equals("2") && gameBeat == 1) {
					theGUI.commandField.removeActionListener(this);
					gold += 5;
					silver += 50;
					copper += 50;
					superHealthPots += 5;
					manaPots += 5;

					System.out.println("You look into the Nest of the Dragon, seeing many fallen people and creatures."
							+ "\nAmong the fallen, you find many useful items and rewards."
							+ "\nThere is a skeleton here, not burnt like the others."
							+ "\nIt appeared to have some sort of unique weapon, though it is long gone."
							+ "\n"
							+ "\nYou finish searching and go on your way."
							+ "\n");

					System.out.println("You have recieved: 5 gold, 50 silver, 50 copper."
							+ "\n\t5 super health potions, and 5 mana potions."
							+ "\n");
					dragonNest();
				}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			String[] mobs = {"Fire Drake", "Young Dragon", "Dragonfire Elemental", "Master Dragon Worshipper"};
         			mobHealth = 250;
         			mobAD = 70;
         			mobEXP = 250;
         			mobMoney = 25;
         			
         			enemy = mobs[rand.nextInt(mobs.length)];
         			System.out.println("\t# A " + enemy + " has appeared! #"
         					+ "\n");	
         			mobFight();
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			area = 12;
         			System.out.println("You head back to the aerie."
    						+ "\n");
         			dragonAerie();
         		}
         		
         		else if(e.getActionCommand().equals("5")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("You open your travel pack to see what is inside."
							+ "\n");
    				inventory();
         		}
         	}
		});
	}
	
	public void mobFight() {
				
		System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
		System.out.println("\t"  + enemy + "'s HP: " + mobHealth);
		System.out.println("\n\tWhat would you like to do?");
		System.out.println("\t1. Attack");
		System.out.println("\t2. Cast Spell");
		System.out.println("\t3. Use Item");
		System.out.println("\t4. Run"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			if(area != 14) {
         				int damageDealt = rand.nextInt(attackDmg);
         				int damageTaken = rand.nextInt(mobAD) - defense;

         				mobHealth -= damageDealt;					
					
         				if(damageTaken > 0) {
         					health -= damageTaken;
         				}

         				else if(damageTaken <= 0) {
         					damageTaken = 1;
         					health -= damageTaken;
         				}

         				System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
         				System.out.println("\t> You recieve " + damageTaken + " points of damage!");
         				System.out.println("");
         				pInfoUpdate();
         			}
         			
         			else if(area == 14) {
         				if(rand.nextInt(100) > 15) {

							int damageDealt = rand.nextInt(attackDmg);
							int damageTaken = rand.nextInt(mobAD) - defense;

							mobHealth -= damageDealt;

							if(damageTaken > 0) {

								health -= damageTaken;
							}

							else if(damageTaken <= 0) {

								damageTaken = 1;
								health -= damageTaken;
							}

							System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
							System.out.println("\t> You recieve " + damageTaken + " points of damage!");
							System.out.println("");
						}

						else {

							int damageDealt = rand.nextInt(attackDmg);
							int damageTaken = 80;

							mobHealth -= damageDealt;
							health -= damageTaken;

							System.out.println("The Ancient Dragon rears back and opens it's gaping maw."
									+ "\nThe air heats up instantly as it breathes fire at you."
									+ "\n");
							System.out.println("\t> You recieve " + damageTaken + " points of damage!");
							System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
							System.out.println("");
						}
         			}

					if(health < 1) {
						
						health = 0;
						pInfoUpdate();
						System.out.println("\t> You collapse on the floor, taking your last breath."
								+ "\n");
						endGame();
					}
					
					else if(mobHealth > 1) {
						mobFight();
					}
					
					else {
						mobRewards();
					}

				}

				else if(e.getActionCommand().equals("1") && mobHealth < 2) {
					theGUI.commandField.removeActionListener(this);
					int execute = attackDmg;
					mobHealth -= execute;

					System.out.println("\t> You execute the " + enemy + "!"
							+ "\n");
					mobRewards();
				}         			         		
         		
         		else if(e.getActionCommand().equals("2")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("\t1. Cast Fireball\t\t10 Mana");
					System.out.println("\t2. Cast Magic Missile\t15 Mana");
					if(spellState > 0) {
						System.out.println("\t3. Cast Absorb\t\t25 Mana");
					}
					else if(spellState == 0) {
						System.out.println("\t3.");
					}
					System.out.println("\t4. Back");
					System.out.println("");
					
					theGUI.commandField.addActionListener(new AbstractAction() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(e.getActionCommand().equals("1") && mobHealth >= 2 && mana >= 10) {
								theGUI.commandField.removeActionListener(this);
								mobHealth -= fireBallDamage;
								mana -= fireBallMana;

								System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage"
										+ "\n");
								
								pInfoUpdate();
								
								if(mobHealth > 1) {
									mobFight();
								}
								
								else {
									mobRewards();
								}
							}

							else if(e.getActionCommand().equals("1") && mobHealth < 2) {
								theGUI.commandField.removeActionListener(this);
								int execute = attackDmg;
								mobHealth -= execute;

								System.out.println("\t> You execute the " + enemy + "!"
										+ "\n");
								mobRewards();
							}

							else if(e.getActionCommand().equals("1") && mana < 10) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("\tYou do not have enough mana for this spell!"
										+ "\n");
								mobFight();
							}

							else if(e.getActionCommand().equals("2") && mobHealth >= 2 && mana >= 15) {
								theGUI.commandField.removeActionListener(this);
								mobHealth -= magicMissileDamage;
								mana -= magicMissileMana;

								System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage"
										+ "\n");
								
								pInfoUpdate();
								
								if(mobHealth > 1) {
									mobFight();
								}
								
								else {
									mobRewards();
								}
							}

							else if(e.getActionCommand().equals("2") && mobHealth < 2) {
								theGUI.commandField.removeActionListener(this);
								int execute = attackDmg;
								mobHealth -= execute;

								System.out.println("\tYou execute the " + enemy + "!");
								System.out.println("");
								mobRewards();
							}

							else if(e.getActionCommand().equals("2") && mana < 15) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("\tYou do not have enough mana for this spell!"
										+ "\n");
								mobFight();
							}

							else if(e.getActionCommand().equals("3") && mobHealth >= 2 && mana >= 25 && spellState > 0) {
								theGUI.commandField.removeActionListener(this);
								mobHealth -= absorbDamage;
								mana -= absorbMana;
								health += 10;

								if(health > maxHealth) {
									health = maxHealth;
								}

								System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
								System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
								System.out.println("");
								
								pInfoUpdate();
								
								if(mobHealth > 1) {
									mobFight();
								}
								
								else {
									mobRewards();
								}
							}

							else if(e.getActionCommand().equals("3") && mobHealth < 2) {
								theGUI.commandField.removeActionListener(this);
								int execute = attackDmg;
								mobHealth -= execute;

								System.out.println("\tYou execute the " + enemy + "!"
										+ "\n");
								mobRewards();
							}
							
							else if(e.getActionCommand().equals("3") && spellState == 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("\tYou do not know this spell!"
										+ "\n");
								mobFight();
							}

							else if(e.getActionCommand().equals("3") && mana < 25) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("\tYou do not have enough mana for this spell!"
										+ "\n");
								mobFight();
							}
							
							else if(e.getActionCommand().equals("4")) {
								theGUI.commandField.removeActionListener(this);
								mobFight();
							}
						}
												
					});					
         		}
         		
         		else if(e.getActionCommand().equals("3")) {
         			theGUI.commandField.removeActionListener(this);
         			
         			System.out.println("\tSelect an item to use");
					System.out.println("\t1. Health Potion");
					System.out.println("\t2. Super Health Potion");
					System.out.println("\t3. Mana Potion");
					System.out.println("\t4. Back"
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(e.getActionCommand().equals("1")) {
								theGUI.commandField.removeActionListener(this);
								if(healthPots > 0) {

									health += healthPotHeal;
									healthPots --;

									if(health > maxHealth) {
										health = maxHealth;
									}

									System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
													+ "\n\t> You now have " + health + " HP."
													+ "\n\t> You have " + healthPots + " health potions left.\n");
									pInfoUpdate();
									mobFight();
								}

								else {

									System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!"
											+ "\n");
									mobFight();
								}
							}

							else if(e.getActionCommand().equals("2")) {
								theGUI.commandField.removeActionListener(this);
								if(superHealthPots > 0) {

									health += superHealthPotHeal;
									superHealthPots --;

									if(health > maxHealth) {
										health = maxHealth;
									}

									System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
													+ "\n\t> You now have " + health + " HP."
													+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
									pInfoUpdate();
									mobFight();
								}

								else {

									System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!"
											+ "\n");
									mobFight();
								}
							}

							else if(e.getActionCommand().equals("3")) {
								theGUI.commandField.removeActionListener(this);
								if(manaPots > 0) {

									mana += manaPotRestore;
									manaPots --;
									System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
											+ "\n\t> You now have " + mana + " Mana."
											+ "\n\t> You have " + manaPots + " mana potions left.\n");
									pInfoUpdate();
									mobFight();
								}

								else {

									System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!"
											+ "\n");
									mobFight();
								}
							}
							
							else if(e.getActionCommand().equals("4")) {
								theGUI.commandField.removeActionListener(this);
								mobFight();
							}
						}						
						
					});				
         		}
         		
         		else if(e.getActionCommand().equals("4")) {
         			theGUI.commandField.removeActionListener(this);
         			System.out.println("\tYou run away from the " + enemy + "!"
							+ "\n");
         			switch(area) {
         			case 1:
         				townMain();
         				break;
         			case 2:
         				forest();
         				break;
         			case 3:
         				caves();
         				break;
         			case 4:
         				caves();
         				break;
         			case 5:
         				darkForest();
         				break;
         			case 6:
         				break;
         			case 7:
         				break;
         			case 8:
         				break;
         			case 9:
         				break;
         			case 10:
         				mountain();
         				break;
         			case 11:
         				mountain();
         				break;
         			case 12:
         				dragonAerie();
         				break;
         			case 13:
         				dragonNest();
         				break;
         			case 14:
         				dragonNest();
         				break;
         			}
         		}
         	}
		});
	}
	
	public void mobRewards() {
		
		switch(area) {
		case 1:
			gold += mobMoney;
			break;
		case 2:
			copper += mobMoney;
			break;
		case 3:
			copper += mobMoney;
			break;
		case 4:
			copper += mobMoney;
			break;
		case 5:
			copper += mobMoney;
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			silver += mobMoney;
			break;
		case 11:
			silver += mobMoney;
			nestPath ++;
			break;
		case 12:
			silver += mobMoney;
			nestKills ++;
			break;
		case 13:
			silver += mobMoney;
			break;
		case 14:
			gold += mobMoney;
			gameBeat = 1;
			break;
		}
		experience += mobEXP;
		enemiesDefeated ++;

		System.out.println("------------------------------------------------------");
		System.out.println(" # " + enemy + " was defeated! #");
		System.out.println(" # You have gained " + mobEXP + " XP #");
		
		switch(area) {
		case 1:
			System.out.println(" # You have gained " + mobMoney + " gold pieces #");
			break;
		case 2:
			System.out.println(" # You have gained " + mobMoney + " copper pieces #");
			break;
		case 3:
			System.out.println(" # You have gained " + mobMoney + " copper pieces #");
			break;
		case 4:
			System.out.println(" # You have gained " + mobMoney + " copper pieces #");
			break;
		case 5:
			System.out.println(" # You have gained " + mobMoney + " copper pieces #");
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			System.out.println(" # You have gained " + mobMoney + " silver pieces #");
			break;
		case 11:
			System.out.println(" # You have gained " + mobMoney + " silver pieces #");
			break;
		case 12:
			System.out.println(" # You have gained " + mobMoney + " silver pieces #");
			break;
		case 13:
			System.out.println(" # You have gained " + mobMoney + " silver pieces #");
			break;
		case 14:
			System.out.println(" # You have gained " + mobMoney + " gold piece #");
			break;
		}
		
		System.out.println(" # You have " + health + " HP left #"
				+ "\n");

		leveling();
		pInfoUpdate();

		// Coin conversions
		if(copper >= 100) {

			silver ++;
			copper -= 100;
		}

		if(silver >= 100) {

			gold ++;
			silver -= 100;
		}

		// Item drops
		switch(area) {
		case 1:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots += 2;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots += 2;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots += 2;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < aSwordDrop) {

				adamantSword ++;
				System.out.println(" * The " + enemy + " dropped an adamant sword! * ");
			}

			if(rand.nextInt(100) < aShieldDrop) {

				adamantShield ++;
				System.out.println(" * The " + enemy + " dropped an adamant shield! * ");
			}

			if(rand.nextInt(100) < flintLockDrop) {

				flintlock ++;
				System.out.println(" * The " + enemy + " dropped his FlintLock! * ");
			}
			break;
		case 2:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots ++;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}

			if(rand.nextInt(100) < bSwordDrop) {

				bronzeSword ++;
				System.out.println(" * The " + enemy + " dropped a bronze sword! * ");
			}

			if(rand.nextInt(100) < bShieldDrop) {

				bronzeShield ++;
				System.out.println(" * The " + enemy + " dropped a bronze shield! * ");
			}
			break;
		case 3:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots ++;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}
			
			if(rand.nextInt(100) < manaPotDrop) {
				
				manaPots ++;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < bSwordDrop) {

				bronzeSword ++;
				System.out.println(" * The " + enemy + " dropped a bronze sword! * ");
			}

			if(rand.nextInt(100) < bShieldDrop) {

				bronzeShield ++;
				System.out.println(" * The " + enemy + " dropped a bronze shield! * ");
			}
			break;
		case 4:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots ++;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots ++;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots ++;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < iSwordDrop) {

				ironSword ++;
				System.out.println(" * The " + enemy + " dropped an iron sword! * ");
			}

			if(rand.nextInt(100) < iShieldDrop) {

				ironShield ++;
				System.out.println(" * The " + enemy + " dropped an iron shield! * ");
			}
			break;
		case 5:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots ++;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots ++;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < iSwordDrop) {

				ironSword ++;
				System.out.println(" * The " + enemy + " dropped an iron sword! * ");
			}

			if(rand.nextInt(100) < iShieldDrop) {

				ironShield ++;
				System.out.println(" * The " + enemy + " dropped an iron shield! * ");
			}
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots ++;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots ++;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < sSwordDrop) {

				steelSword ++;
				System.out.println(" * The " + enemy + " dropped a steel sword! * ");
			}

			if(rand.nextInt(100) < sShieldDrop) {

				steelShield ++;
				System.out.println(" * The " + enemy + " dropped a steel shield! * ");
			}
			break;
		case 11:
			if(rand.nextInt(100) < healthPotDrop) {

				healthPots += 2;
				System.out.println(" # The " + enemy + " dropped a health potion! # ");
				System.out.println(" # You now have " + healthPots + " health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots ++;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots ++;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < mSwordDrop) {

				steelSword ++;
				System.out.println(" * The " + enemy + " dropped a mithril sword! * ");
			}

			if(rand.nextInt(100) < mShieldDrop) {

				steelShield ++;
				System.out.println(" * The " + enemy + " dropped a mithril shield! * ");
			}
			break;
		case 12:
			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots ++;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots += 2;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < mSwordDrop) {

				mithrilSword ++;
				System.out.println(" * The " + enemy + " dropped a mithril sword! * ");
			}

			if(rand.nextInt(100) < mShieldDrop) {

				mithrilShield ++;
				System.out.println(" * The " + enemy + " dropped a mithril shield! * ");
			}
			break;
		case 13:
			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots ++;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots += 2;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < aSwordDrop) {

				adamantSword ++;
				System.out.println(" * The " + enemy + " dropped a adamant sword! * ");
			}

			if(rand.nextInt(100) < aShieldDrop) {

				adamantShield ++;
				System.out.println(" * The " + enemy + " dropped an adamant shield! * ");
			}
			break;
		case 14:
			if(rand.nextInt(100) < superHealthPotDrop) {

				superHealthPots += 4;
				System.out.println(" # The " + enemy + " dropped a super health potion! # ");
				System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
			}

			if(rand.nextInt(100) < manaPotDrop) {

				manaPots += 3;
				System.out.println(" # The " + enemy + " dropped a mana potion! # ");
				System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
			}

			if(rand.nextInt(100) < dSwordDrop) {

				dragonSword ++;
				System.out.println(" * The " + enemy + " dropped a dragon sword! * ");
			}

			if(rand.nextInt(100) < dShieldDrop) {

				dragonShield ++;
				System.out.println(" * The " + enemy + " dropped a dragon shield! * ");
			}
			break;
		}

		switch(area) {
		case 1:
			townMain();
			break;
		case 2:
			forest();
			break;
		case 3:
			caves();
			break;
		case 4:
			System.out.println("You have defeated the Cave Troll and cleared the path in front of you."
					+ "\n"
					+ "\nAfter a short time walking, you find the exit to the cave and continue on your adventure."
					+ "\n");
			System.out.println("After an arduous journey, you finally arrive at a large city."
					+ "\nAs you enter you find that it is teeming with life."
					+ "\nCrowds of people swarming around you on every side hustling and bustling."
					+ "\nLuckily there are street signs, pointing to many locations around you."
					+ "\n");
			city();
			break;
		case 5:
			darkForest();
			break;
		case 6:
			city();
			break;
		case 7:
			shops();
			break;
		case 8:
			guilds();
			break;
		case 9:
			castle();
			break;
		case 10:
			mountain();
			break;
		case 11:
			System.out.println("You have defeated the Ancient Dragon Guardian and cleared the path in front of you."
					+ "\nYou continue climbing toward the Dragon's Nest."
					+ "\n");
			System.out.println("Climbing to a crest on the mountain, you peek over and see what lies ahead."
					+ "\nThere appears to be a stairway leading up to the peak of the now scorched mountain."
					+ "\nThough it is thouroughly blocked by many dangerous creatures."
					+ "\n");
			dragonAerie();
			break;
		case 12:
			dragonAerie();
			break;
		case 13:
			dragonNest();
			break;
		case 14:
			dragonNest();
		}
	}
	
	public void endGame() {
		
		System.out.println("Continue?"
				+ "\n"
				+ "\n1: Yes"
				+ "\n2: No"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {
      		
         	@Override
         	public void actionPerformed(ActionEvent e) {
         		
         		if(e.getActionCommand().equals("1")) {
         			theGUI.commandField.removeActionListener(this);
         			health = maxHealth;
         			mana = 20;
         			copper = 0;
         			silver = 0;
         			gold = 0;
         			experience = 0;
         			pInfoUpdate();
         			townMain();
         		}
         		
         		else if(e.getActionCommand().equals("2"))  {
         			theGUI.commandField.removeActionListener(this);
         			System.out.println("");
         			System.out.println("##########################");
         			System.out.println("      # Thank you for playing! #");
         			System.out.println("##########################");
         			System.out.println("");
         			System.out.println("Created by: Dane Stark");
         		}
         	}
		});
	}
	public void inventory() {
			
		System.out.println("\t ***Inventory***"); // Potions, Food, Equipment, Items

		if(healthPots > 0) {
			System.out.println(healthPots + " Health Potion(s)");
		}
		if(superHealthPots > 0) {
			System.out.println(superHealthPots + " Super Health Potion(s)");
		}
		if(manaPots > 0) {
			System.out.println(manaPots + " Mana Potion(s)");
		}
		if(apple > 0) {
			System.out.println(apple + " Apple(s)");
		}
		if(applePie > 0) {
			System.out.println(applePie + " Apple Pie(s)");
		}
		if(bread > 0) {
			if(bread == 1) {
			System.out.println(bread + " Loaf of Bread");
			}
			else if(bread > 1) {
				System.out.println(bread + " Loaves of Bread");
			}
		}
		if(beer > 0) {
			System.out.println(beer + " Mug(s) of Beer");
		}
		if(pumpkin > 0) {
			System.out.println(pumpkin + " Pumpkin(s)");
		}
		if(scythe > 0) {
			System.out.println(scythe + " Scythe(s)");
		}
		if(bronzeSword > 0) {
			System.out.println(bronzeSword + " Bronze Sword(s)");
		}
		if(bronzeShield > 0) {
			System.out.println(bronzeShield + " Bronze Shield(s)");
		}
		if(ironSword > 0) {
			System.out.println(ironSword + " Iron Sword(s)");
		}
		if(ironShield > 0) {
			System.out.println(ironShield + " Iron Shield(s)");
		}
		if(steelSword > 0) {
			System.out.println(steelSword + " Steel Sword(s)");
		}
		if(steelShield > 0) {
			System.out.println(steelShield + " Steel Shield(s)");
		}
		if(mithrilSword > 0) {
			System.out.println(mithrilSword + " Mithril Sword(s)");
		}
		if(mithrilShield > 0) {
			System.out.println(mithrilShield + " Mithril Shield(s)");
		}
		if(guildSword > 0) {
			System.out.println(guildSword + " Guild Sword(s)");
		}
		if(guildShield > 0) {
			System.out.println(guildShield + " Guild Shield(s)");
		}
		if(adamantSword > 0) {
			System.out.println(adamantSword + " Adamant Sword(s)");
		}
		if(adamantShield > 0) {
			System.out.println(adamantShield + " Adamant Shield(s)");
		}
		if(dragonSword > 0) {
			System.out.println(dragonSword + " Dragon Sword(s)");
		}
		if(dragonShield > 0) {
			System.out.println(dragonShield + " Dragon Shield(s)");
		}
		if(lantern > 0) {
			System.out.println(lantern + " Lantern(s)");
		}
		if(flintlock > 0) {
			System.out.println(flintlock + " Flintlock(s)");
		}

		System.out.println("\n"
				+ "What would you like to do?"
				+ "\n");
		System.out.println("1: Use or equip item");
		System.out.println("2: Check coin pouch");
		System.out.println("3: Continue adventure"
				+ "\n");
		
		theGUI.commandField.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("1")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("Type the name of the item you wish to use or equip."
							+ "\n");
					
					theGUI.commandField.addActionListener(new AbstractAction() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(e.getActionCommand().equals("Health Potion") || e.getActionCommand().equals("health potion") && healthPots > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Drink");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											healthPots --;
											health += healthPotHeal;

											if(health > maxHealth) {
												health = maxHealth;
											}
											System.out.println("You drink a health potion, restoring " + healthPotHeal + " HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											healthPots --;
											System.out.println("You dump the health potion on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Super Health Potion")
									|| e.getActionCommand().equals("super health potion") && superHealthPots > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Drink");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											superHealthPots --;
											health += superHealthPotHeal;

											if(health > maxHealth) {
												health = maxHealth;
											}

											System.out.println("You drink a super health potion, restoring " + superHealthPotHeal + " HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											superHealthPots --;
											System.out.println("You dump the super health potion on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Mana Potion") || e.getActionCommand().equals("mana potion") && manaPots > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Drink");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											manaPots --;
											mana += manaPotRestore;
											System.out.println("You drink a mana potion, restoring " + manaPotRestore + " Mana."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											manaPots --;
											System.out.println("You dump the mana potion on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Apple") || e.getActionCommand().equals("apple") && apple > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Eat");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											apple --;
											health += 5;

											if(health > maxHealth) {
												health = maxHealth;
											}

											System.out.println("You eat an apple, restoring 5 HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											apple --;
											System.out.println("You drop an apple on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Apple Pie") || e.getActionCommand().equals("apple pie") && applePie > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Eat");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											applePie --;
											health += 30;

											if(health > maxHealth) {
												health = maxHealth;
											}

											System.out.println("You eat an apple pie, restoring 20 HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											applePie --;
											System.out.println("You drop an apple pie on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Beer") || e.getActionCommand().equals("beer") && beer > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Drink");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											beer --;
											health ++;
											drunk ++;
											System.out.println("You drink the beer, gaining 1 HP."
													+ "\n");

											if(drunk == 2) {

												System.out.println("You start to feel just a little light headed."
														+ "\n");
											}

											else if(drunk == 3) {

												System.out.println("You start feeling dizzy"
														+ "\n.");
											}

											else if(drunk == 4) {

												System.out.println("The world is starting to spin around you, maybe you should rest."
														+ "\n");
											}

											else if(drunk == 5) {

												health -= 5;
												attackDmg -= 5;
												System.out.println("Your vision tightens as if your eyes are starting to close,"
														+ "\nThe world is spinning around, you get very hungry, and your hands don't feel normal."
														+ "\n");
											}

											else if(drunk >= 6) {

												System.out.println("Drinking another, you realize you may black out."
														+ "\n");
											}
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											beer --;
											System.out.println("You drop the mug of beer on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Bread") || e.getActionCommand().equals("bread") && bread > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Eat");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											bread --;
											health += 10;

											if(health > maxHealth) {
												health = maxHealth;
											}

											System.out.println("You eat some bread, restoring 10 HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											bread --;
											System.out.println("You drop some bread on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Pumpkin") || e.getActionCommand().equals("pumpkin") && pumpkin > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Eat");
								System.out.println("2: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1")) {
											theGUI.commandField.removeActionListener(this);
											pumpkin --;
											health += 20;

											if(health > maxHealth) {
												health = maxHealth;
											}

											System.out.println("You carve out and eat the pumpkin, restoring 20 HP."
													+ "\n");
											pInfoUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("2")) {
											theGUI.commandField.removeActionListener(this);
											pumpkin --;
											System.out.println("You drop a pumpkin on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Scythe") || e.getActionCommand().equals("scythe") && scythe > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 5;
											currentSwordEquip = "Scythe";
											System.out.println("You fasten the scythe to your back, hoping to get it bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Scythe") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Scythe") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 5;
											currentSwordEquip = "nothing";
											System.out.println("You remove the scythe from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Scythe") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Scythe") {
											theGUI.commandField.removeActionListener(this);
											scythe --;
											System.out.println("You toss a scythe on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Bronze Sword") || e.getActionCommand().equals("bronze sword") && bronzeSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 5;
											currentSwordEquip = "Bronze Sword";
											System.out.println("You fasten the bronze sword to your belt, hoping to get it bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Bronze Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Bronze Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 5;
											currentSwordEquip = "nothing";
											System.out.println("You remove the bronze sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Bronze Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Bronze Sword") {
											theGUI.commandField.removeActionListener(this);
											bronzeSword --;
											System.out.println("You toss a bronze sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Bronze Shield") || e.getActionCommand().equals("bronze shield") && bronzeShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 5;
											defense += 5;
											currentShieldEquip = "Bronze Shield";
											System.out.println("You fasten the bronze shield to your back, hoping to not get killed."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Bronze Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Bronze Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 5;
											defense -= 5;
											currentShieldEquip = "nothing";
											System.out.println("You remove the bronze shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Bronze Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Bronze Shield") {
											theGUI.commandField.removeActionListener(this);
											bronzeShield --;
											System.out.println("You toss a bronze shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Iron Sword") || e.getActionCommand().equals("iron sword") && ironSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 10;
											currentSwordEquip = "Iron Sword";
											System.out.println("You fasten the iron sword to your belt, hoping to get it bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Iron Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Iron Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 10;
											currentSwordEquip = "nothing";
											System.out.println("You remove the iron sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Iron Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Iron Sword") {
											theGUI.commandField.removeActionListener(this);
											ironSword --;
											System.out.println("You toss an iron sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Iron Shield") || e.getActionCommand().equals("iron shield") && ironShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 10;
											defense += 10;
											currentShieldEquip = "Iron Shield";
											System.out.println("You fasten the iron shield to your back, hoping to not get killed."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Iron Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Iron Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 10;
											defense -= 10;
											currentShieldEquip = "nothing";
											System.out.println("You remove the iron shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Iron Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Iron Shield") {
											theGUI.commandField.removeActionListener(this);
											ironShield --;
											System.out.println("You toss an iron shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Steel Sword") || e.getActionCommand().equals("steel sword") && steelSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 20;
											currentSwordEquip = "Steel Sword";
											System.out.println("You fasten the steel sword to your belt, hoping to get it bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Steel Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Steel Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 20;
											currentSwordEquip = "nothing";
											System.out.println("You remove the iron sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Steel Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Steel Sword") {
											theGUI.commandField.removeActionListener(this);
											steelSword --;
											System.out.println("You toss a steel sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Steel Shield") || e.getActionCommand().equals("steel shield") && steelShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 20;
											defense += 15;
											currentShieldEquip = "Steel Shield";
											System.out.println("You fasten the steel shield to your back, hoping to not get killed."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Steel Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Steel Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 20;
											defense -= 15;
											currentShieldEquip = "nothing";
											System.out.println("You remove the steel shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Steel Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Steel Shield") {
											theGUI.commandField.removeActionListener(this);
											steelShield --;
											System.out.println("You toss a steel shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Mithril Sword") || e.getActionCommand().equals("mithril sword") && mithrilSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 25;
											currentSwordEquip = "Mithril Sword";
											System.out.println("You fasten the mithril sword to your belt, knowing it will get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Mithril Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Mithril Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 25;
											currentSwordEquip = "nothing";
											System.out.println("You remove the mithril sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Mithril Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Mithril Sword") {
											theGUI.commandField.removeActionListener(this);
											mithrilSword --;
											System.out.println("You toss a mithril sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Mithril Shield") || e.getActionCommand().equals("mithril shield") && mithrilShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 25;
											defense += 20;
											currentShieldEquip = "Mithril Shield";
											System.out.println("You fasten the mithril shield to your back, hoping to not get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Mithril Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Mithril Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 25;
											defense -= 20;
											currentShieldEquip = "nothing";
											System.out.println("You remove the mithril shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Mithril Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Mithril Shield") {
											theGUI.commandField.removeActionListener(this);
											mithrilShield --;
											System.out.println("You toss a mithril shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Guild Sword") || e.getActionCommand().equals("guild sword") && guildSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 30;
											currentSwordEquip = "Guild Sword";
											System.out.println("You fasten the guild sword to your belt, knowing it will get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Guild Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Guild Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 30;
											currentSwordEquip = "nothing";
											System.out.println("You remove the guild sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Guild Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Guild Sword") {
											theGUI.commandField.removeActionListener(this);
											guildSword --;
											System.out.println("You toss a guild sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Guild Shield") || e.getActionCommand().equals("guild shield") && guildShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 30;
											defense += 25;
											currentShieldEquip = "Guild Shield";
											System.out.println("You fasten the guild shield to your back, hoping to not get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Guild Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Guild Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 30;
											defense -= 25;
											currentShieldEquip = "nothing";
											System.out.println("You remove the guild shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Guild Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Guild Shield") {
											theGUI.commandField.removeActionListener(this);
											guildShield --;
											System.out.println("You toss a guild shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Adamant Sword") || e.getActionCommand().equals("adamant sword") && adamantSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 35;
											currentSwordEquip = "Adamant Sword";
											System.out.println("You fasten the adamant sword to your belt, knowing it will get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Adamant Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Adamant Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 35;
											currentSwordEquip = "nothing";
											System.out.println("You remove the adamant sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Adamant Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Adamant Sword") {
											theGUI.commandField.removeActionListener(this);
											adamantSword --;
											System.out.println("You toss an adamant sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Adamant Shield") || e.getActionCommand().equals("adamant shield") && adamantShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 35;
											defense += 30;
											currentShieldEquip = "Adamant Shield";
											System.out.println("You fasten the adamant shield to your back, hoping to not get bloody."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Adamant Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Adamant Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 35;
											defense -= 30;
											currentShieldEquip = "nothing";
											System.out.println("You remove the adamant shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Adamant Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Adamant Shield") {
											theGUI.commandField.removeActionListener(this);
											adamantShield --;
											System.out.println("You toss an adamant shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Dragon Sword") || e.getActionCommand().equals("dragon sword") && dragonSword > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 50;
											currentSwordEquip = "Dragon Sword";
											System.out.println("You fasten the dragon sword to your belt,"
													+ "you can feel intense heat radiating from it."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Dragon Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Dragon Sword") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 50;
											currentSwordEquip = "nothing";
											System.out.println("You remove the dragon sword from your belt."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Dragon Sword") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Dragon Sword") {
											theGUI.commandField.removeActionListener(this);
											dragonSword --;
											System.out.println("You toss a dragon sword on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Dragon Shield") || e.getActionCommand().equals("dragon shield") && dragonShield > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentShieldEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											maxHealth += 50;
											defense += 40;
											currentShieldEquip = "Dragon Shield";
											System.out.println("You fasten the dragon shield to your back, knowing it will protect you."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentShieldEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding " + currentShieldEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip != "Dragon Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip armor you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentShieldEquip == "Dragon Shield") {
											theGUI.commandField.removeActionListener(this);
											maxHealth -= 50;
											defense -= 40;
											currentShieldEquip = "nothing";
											System.out.println("You remove the dragon shield from your back."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip == "Dragon Shield") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentShieldEquip != "Dragon Shield") {
											theGUI.commandField.removeActionListener(this);
											dragonShield --;
											System.out.println("You toss a dragon shield on the ground."
													+ "\n");
											inventory();
										}
									}
								});
							}
							
							else if(e.getActionCommand().equals("Flintlock") || e.getActionCommand().equals("flintlock") && flintlock > 0) {
								theGUI.commandField.removeActionListener(this);
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Equip");
								System.out.println("2: Disequip");
								System.out.println("3: Discard"
										+ "\n");
								
								theGUI.commandField.addActionListener(new AbstractAction() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										if(e.getActionCommand().equals("1") && currentSwordEquip == "nothing") {
											theGUI.commandField.removeActionListener(this);
											attackDmg += 100;
											currentSwordEquip = "FlintLock";
											System.out.println("You strap the Flintlock to your thigh, ready to fire."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("1") && currentSwordEquip != "nothing") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You are currently wielding a " + currentSwordEquip + "."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip != "Flintlock") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot disequip a weapon you do not have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("2") && currentSwordEquip == "Flintlock") {
											theGUI.commandField.removeActionListener(this);
											attackDmg -= 100;
											currentSwordEquip = "nothing";
											System.out.println("You remove the FlintLock from your leg."
													+ "\n");
											pInfoUpdate();
											pEquipUpdate();
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip == "Flintlock") {
											theGUI.commandField.removeActionListener(this);
											
											System.out.println("You cannot drop the item you have equipped."
													+ "\n");
											inventory();
										}
										
										else if(e.getActionCommand().equals("3") && currentSwordEquip != "Flintlock") {
											theGUI.commandField.removeActionListener(this);
											flintlock --;
											System.out.println("You discard the powerful weapon."
													+ "\n");
											inventory();
										}
									}
								});
							}							
							
							else {
								theGUI.commandField.removeActionListener(this);
								System.out.println("You do not have this item or it was spelt incorrectly."
										+ "\n");
								inventory();
							}
							
						}
					});
				}
				
				else if(e.getActionCommand().equals("2")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println(" $ You have " + gold + " gold pieces, " + silver + " silver pieces, and " + copper + " copper pieces $"
							+ "\n");
					inventory();
				}
				
				else if(e.getActionCommand().equals("3")) {
					theGUI.commandField.removeActionListener(this);
					
					System.out.println("You close the pack and continue your adventure."
							+ "\n");
					switch(area) {
					case 1:
						townMain();
						break;
					case 2:
						forest();
						break;
					case 3:
						caves();
						break;
					case 4:
						caves();
						break;
					case 5:
						darkForest();
						break;
					case 6:
						city();
						break;
					case 7:
						shops();
						break;
					case 8:
						guilds();
						break;
					case 9:
						castle();
						break;
					case 10:
						mountain();
						break;
					case 11:
						mountain();
						break;
					case 12:
						dragonAerie();
						break;
					case 13:
						dragonNest();
						break;
					case 14:
						dragonNest();
						break;
					}
				}				
			}
			
		});		
	}

	public static void leveling() {

		// Experience and level system
		if(experience > 99 && level < 1) {

			level = levelArray[0];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 199 && level < 2) {

			level = levelArray[1];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 299 && level < 3) {

			level = levelArray[2];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 399 && level < 4) {

			level = levelArray[3];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 499 && level < 5) {

			level = levelArray[4];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 699 && level < 6) {

			level = levelArray[5];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 999 && level < 7) {

			level = levelArray[6];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 1499 && level < 8) {

			level = levelArray[7];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 1999 && level < 9) {

			level = levelArray[8];
			attackDmg += 1;
			maxHealth += 5;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 2999 && level < 10) {

			level = levelArray[9];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 3999 && level < 11) {

			level = levelArray[10];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 4999 && level < 12) {

			level = levelArray[11];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 5999 && level < 13) {

			level = levelArray[12];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 7999 && level < 14) {

			level = levelArray[13];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 9999 && level < 15) {

			level = levelArray[14];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 13999 && level < 16) {

			level = levelArray[15];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 16999 && level < 17) {

			level = levelArray[16];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 19999 && level < 18) {

			level = levelArray[17];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 24999 && level < 19) {

			level = levelArray[18];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

		else if(experience > 29999 && level < 20) {

			level = levelArray[19];
			attackDmg += 1;
			maxHealth += 10;
			System.out.println(" # You are now level " + level + "! #");
			System.out.println(" # You have gained 1 attack damage! #");
			System.out.println(" # You now have " + maxHealth +" max health! #");
		}

	}

	public void pInfoUpdate() {
		theGUI.playerInfo.setText("HP " + health + "/" + maxHealth + "\nMana " + mana + "\nAD   "
				+ attackDmg + "\nDEF " + defense + "\nLvl    " + level + "\nExp  " + experience);
	}
	
	public void pEquipUpdate() {
		 theGUI.equipmentDisplay.setText(currentSwordEquip + "\n" + currentShieldEquip);
	}

	@Override
	public void run() {

		running = true;
	}

	public static int getHealth() {
		return MainRPG.health;
	}
	public static int getMaxHealth() {
		return MainRPG.maxHealth;
	}
	public static int getMana() {
		return MainRPG.mana;
	}
	public static int getattackDamage() {
		return MainRPG.attackDmg;
	}
	public static int getDefense() {
		return MainRPG.defense;
	}
	public static int getLevel() {
		return MainRPG.level;
	}
	public static int getEXP() {
		return MainRPG.experience;
	}
	public static String getSword() {
		return MainRPG.currentSwordEquip;
	}
	public static String getShield() {
		return MainRPG.currentShieldEquip;
	}
	public static int getArea() {
		return MainRPG.area;
	}

}