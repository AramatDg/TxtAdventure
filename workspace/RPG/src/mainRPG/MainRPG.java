package mainRPG;

import java.util.Scanner;
import java.util.Random;

import audio.MusicPlayer;

public class MainRPG implements Runnable{
	
	// Player input and random numbers for fights etc.
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();
	
	// Runs the game
	private static boolean running;
	
	// Sets the player in an area, and records which one they need to be in after leaving the inventory
	public static int area = 1;
	public static int lastArea = 0;
	
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
	static String name;
	static int maxHealth = 20;
	static int health = 20;
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
	static int copper = 50;
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
	static int flintLock = 0; // 100
	
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
	
	public static void main(String args[]) {
		
		ThreadPool pool = new ThreadPool(3);
		MusicPlayer player = new MusicPlayer("Opening", "Town", "Sanctuary", "The Empire");
		GUI gui = new GUI();
		pool.runTask(gui);
		pool.runTask(player);
		
		running = true;
		
		System.out.println("\tEnter your name!"
				+ "\n");
		
		name = in.nextLine();
		
		System.out.println("\tYour name is: " + name
				+ "\n");
		System.out.println("Press any key to continue!"
				+ "\n");
		
		in.nextLine();
		
		System.out.println("You awake in a field, wheat rising all around you."
				+ "\nThe only thing you see is a small town looming ahead."
				+ "\nYou walk to the town, curious as to where you are."
				+ "\n");
		
		while(running) {
			
			while(health > 0) {
				
				if(area == 1) {
					town();
				}
			
				else if(area == 2) {
					forestToCity();
				}
			
				else if(area == 3) {
					cave();
				}
			
				else if(area == 4) {
					forestToTown();
				}
			
				else if(area == 5) {
					city();
				}
			
				else if(area == 6) {
					castle();
				}
			
				else if(area == 7) {
					mountain();
				}
				
				else if(area == 8) {
					inventory();
				}
				
				else if(area == 9) {
					dragonNest();
				}
			
			}
			
			System.out.println("");
			System.out.println(name + " has defeated " + enemiesDefeated + " monsters!");
			System.out.println(name + " was level " + level + " and had " + experience + " XP!");
			System.out.println("");
			System.out.println("##########################");
			System.out.println("# Thank you for playing! #");
			System.out.println("##########################");
			System.out.println("");
			System.out.println("Created by: Dane Stark");
			
			running = false;
		}
		
	}
	

	public static void town() {
		
		area = 1;
		
		String[] shadyMan = {"Shady Man"};
		int shadyManHP = 500;
		int shadyManAD = 150;
		int shadyManEXP = 500;
		int shadyManMoney = 10;
			
		System.out.println("You enter the town, it seems rather familiar."
				+ "\n");
			
		while(area == 1 && health > 0) {
			
			System.out.println("Where would you like to go?"
					+ "\n");
			System.out.println("1: Tavern");
			System.out.println("2: General Store");
			System.out.println("3: Town's Square");
			System.out.println("4: Inventory"
					+ "\n");
			
			String input = in.nextLine();
			
			if(input.equals("1")) {
				
				System.out.println("You enter the Tavern through sturdy oak double doors. "
						+ "\nSmelling a hardy aroma of wheat beer and a small fire. "
						+ "\nThere appears to be only a Shady Man sitting in one corner and the Barkeeper cleaning the counter."
						+ "\n");
				
				Tavern:
				while(health > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Speak to the Barkeeper");
					System.out.println("2: Speak to the Shady Man");
					System.out.println("3: Leave");
				
					input = in.nextLine();
				
					if(input.equals("1")) {
					
						System.out.println("You walk up to the Barkeeper as he eyes you suspiciously. "
							+ "\nWhat can I do fer ya?  He asks cautiously. "
							+ "\n");
						System.out.println("1: Buy a beer");
						System.out.println("2: Rent a room for the night");
						System.out.println("3: Ask about town");
						System.out.println("4: Leave");
					
						input = in.nextLine();
						
						if(input.equals("1")) {
							
							System.out.println("Want a beer do ya?  That'll be five copper Carlons."
									+ "\n");
							System.out.println("1: Buy");
							System.out.println("2: Nevermind");
							
							input = in.nextLine();
							
							if(input.equals("1") && copper >= 5) {
								
								beer ++;
								copper -= 5;
								System.out.println("You purchase a beer. "
										+ "\n");
								System.out.println("The Barkeeper pours out a cold one."
										+ "\nWell then, here you are.  Enjoy!"
										+ "\n");
							}
							
							else if(input.equals("1") && copper < 5) {
								
								System.out.println("You do not have enough to buy a drink. "
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
							}
							
						}
						
						else if(input.equals("2")) {
							
							System.out.println("Want to stay for the night eh?  Very well, twenty five copper Carlons."
									+ "\n");
							System.out.println("1: Rent");
							System.out.println("2: Nevermind");
							
							input = in.nextLine();
							
							if(input.equals("1") && copper >= 25) {
								
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
							}
							
							else if(input.equals("1") && copper < 25) {
								
								System.out.println("You do not have enough for a room. "
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
							}
							
						}
						
						else if(input.equals("3")) {
							
							System.out.println("You lean on the well polished counter of the bar and ask about the town"
									+ "\n"
									+ "\nThe Barkeeper answers, Well we have had a rough patch with monsters in the forest recently,"
									+ "\nnot to mention the increased tax rates and theft is getting out of hand."
									+ "\nThough overall, I love Haraolen, its been my home since birth."
									+ "\n"
									+ "\nTo which he gets lost in his memories and ignores you."
									+ "\n");
						}
						
						else if(input.equals("4")) {
							
							System.out.println("You change your mind and turn away from the man."
									+ "\n");
						}
						
					}
				
					else if(input.equals("2") && beer < 1 && shadyManState == 0) {
					
						shadyManState = 1;
						System.out.println("You approach the Shady Man and he eyes you darkly. "
							+ "\nYou can see him pulling a Flintlock from his holster.  "
							+ "\nWith good judgement, you decide to leave this fellow alone for now."
							+ "\n");
						continue Tavern;
					}
					
					else if(input.equals("2") && beer < 1 && shadyManState == 1) {
						
						shadyManState = 2;
						System.out.println("You approach the Shady Man and he stares you down. "
							+ "\nYou can see him with his Flintlock already pulled out.  "
							+ "\nYou turn around and decide to leave this irritated fellow alone for now."
							+ "\n");
						continue Tavern;
					}
					
					else if(input.equals("2") && beer < 1 && shadyManState == 2) {
						
						shadyManState = 3;
						System.out.println("With bad judgement, you approach the Shady Man again. "
							+ "\nYou can see him pulling back the hammer on his Flintlock.  "
							+ "\nYou turn and leave this angry fellow alone."
							+ "\n");
						continue Tavern;
					}
					
					else if(input.equals("2") && beer < 1 && shadyManState == 3) {
						
						health = 0;
						System.out.println("You stupidly walk back over to the Shady Man. "
							+ "\nYou see him level his Flintlock at your head with finger on the trigger.  "
							+ "\nYou have a good clue on what happens ne---------."
							+ "\n"
							+ "\nThe hammer slams down and the barrel is thrown backwards with recoil"
							+ "\n"
							+ "\nYour body collapses on the floor, head hollowed, and brain matter splattered everywhere."
							+ "\n");
						break;
					}
				
					else if(input.equals("2") && beer >= 1) {
					
						shadyManState = 0;
						System.out.println("You approach the Shady Man and his eyes dart to the beer in your hand."
								+ "\nWell what do we have here, he says still staring at the drink, a friend perhaps?"
								+ "\n"
								+ "\nWhat would you like to do?");
						System.out.println("1: Give the Shady Man a beer");
						System.out.println("2: Awkwardly turn and walk away slowly");
						
						input = in.nextLine();
						
						if(input.equals("1")) {
							
							beer --;
							System.out.println("You hand the Shady Man a beer");
							System.out.println("He gladly accepts it and allows you to sit with him."
									+ "\n");
							
							ShadyFight:
							while(health > 0) {
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Ask about him");
								System.out.println("2: Ask about his weapon");
								System.out.println("3: Fight him");
								System.out.println("4: Leave");
								
								input = in.nextLine();
								
								if(input.equals("1") && beer > 0) {
									
									beer --;
									System.out.println("He raises his mug and beckons for you to do the same"
											+ "\n");
									System.out.println("You take a drink together and he starts talking."
											+ "\nWell I settled here a while ago after journying to the ends of the world."
											+ "\nI aquired fame and fortune, but that was all taken from me."
											+ "\nAll I have left is my Flintlock, and countless stories."
											+ "\n");
								}
								
								else if(input.equals("1") && beer == 0) {
									
									System.out.println("He raises his mug and beckons for you to do the same."
											+ "\n");
									System.out.println("Unfortunately you do not have a drink."
											+ "\nHe drinks his anyway and remains silent."
											+ "\n");
								}
								
								else if(input.equals("2") && beer > 4) {
									
									System.out.println("So you want to know about my Flintlock eh?"
											+ "\nWell then, let's see how much you can drink."
											+ "");
									System.out.println("What would you like to do?"
											+ "\n");
									System.out.println("1: Drink with him");
									System.out.println("2: Do something else");
									
									input = in.nextLine();
									
										if(input.equals("1")) {
										
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
										}
										
										else if(input.equals("2")) {
											
											System.out.println("You don't feel like drinking right now."
													+ "\n");
										}
										
								}
								
								else if(input.equals("2") && beer < 4) {
									
									System.out.println("He eyes you lack of numerous drinks and laughs."
											+ "\nSorry only people that can drink with me will get to hear from me."
											+ "\n");
								}
								
								else if(input.equals("3")) {
									
									System.out.println("You have a temporary lapse of insanity and decide to fight."
											+ "\n");
									
									// Calls enemy interaction
									int mobFightHealth = shadyManHP;
									String enemy = shadyMan[rand.nextInt(shadyMan.length)];
									System.out.println("\t# A " + enemy + " has appeared! #"
											+ "\n");
									
									while(mobFightHealth > 0) {
										
										System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
										System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
										System.out.println("\n\tWhat would you like to do?");
										System.out.println("\t1. Attack");
										System.out.println("\t2. Cast Spell");
										System.out.println("\t3. Use Item");
										System.out.println("\t4. Run");
										
										String action = in.nextLine();
									
										if(action.equals("1") && mobFightHealth >= 2) {
											
											int damageDealt = rand.nextInt(attackDmg);
											int damageTaken = rand.nextInt(shadyManAD) - defense;
											
											mobFightHealth -= damageDealt;
											
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
											
											if(health < 1) {
												
												System.out.println("\t> You collapse on the floor, taking your last breath.");
												break;
											}
											
										}
										
										else if(action.equals("1") && mobFightHealth < 2) {
											
											int execute = attackDmg;
											mobFightHealth -= execute;
											
											System.out.println("\t> You behead the " + enemy + "!");
											System.out.println("");
										}
										
										else if(action.equals("2")) {
											
											System.out.println("\t1. Cast Fireball\t10 Mana");
											System.out.println("\t2. Cast Magic Missile\t15 Mana");
											if(spellState > 0) {
												System.out.println("\t3. Cast Absorb\t\t25 Mana");
											}
											
											action = in.nextLine();
											
											if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
												
												mobFightHealth -= fireBallDamage;
												mana -= fireBallMana;
												
												System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
												System.out.println("");
											}
											
											else if(action.equals("1") && mobFightHealth < 2) {
												
												int execute = attackDmg;
												mobFightHealth -= execute;
												
												System.out.println("\t> You execute the " + enemy + "!");
												System.out.println("");
											}
											
											else if(action.equals("1") && mana < 10) {
												
												System.out.println("\tYou do not have enough mana for this spell!");
												System.out.println("");
											}
											
											if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
												
												mobFightHealth -= magicMissileDamage;
												mana -= magicMissileMana;
											
												System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
												System.out.println("");
											}
										
											else if(action.equals("2") && mobFightHealth < 2) {
											
												int execute = attackDmg;
												mobFightHealth -= execute;
											
												System.out.println("\tYou execute the " + enemy + "!");
												System.out.println("");
											}
										
											else if(action.equals("2") && mana < 15) {
											
												System.out.println("\tYou do not have enough mana for this spell!");
												System.out.println("");
											}
											
											if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
												
												mobFightHealth -= absorbDamage;
												mana -= absorbMana;
												health += 10;
												
												if(health > maxHealth) {
													health = maxHealth;
												}
											
												System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
												System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
												System.out.println("");
											}
										
											else if(action.equals("3") && mobFightHealth < 2) {
											
												int execute = attackDmg;
												mobFightHealth -= execute;
											
												System.out.println("\tYou execute the " + enemy + "!");
												System.out.println("");
											}
										
											else if(action.equals("3") && mana < 25 || spellState == 0) {
											
												System.out.println("\tYou do not have enough mana for this spell!");
												System.out.println("");
											}
											
											else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
												
												System.out.println("");
												System.out.println("Invalid Command!");
												System.out.println("");
											}
										}
										
										else if(action.equals("3")) {
											
											System.out.println("\tSelect an item to use");
											System.out.println("\t1. Health Potion");
											System.out.println("\t2. Super Health Potion");
											System.out.println("\t3. Mana Potion");
											
											action = in.nextLine();
											
											if(action.equals("1")) {
												
												if(healthPots > 0) {
												
													health += healthPotHeal;
													healthPots --;
													
													if(health > maxHealth) {
														health = maxHealth;
													}
													
													System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
																	+ "\n\t> You now have " + health + " HP."
																	+ "\n\t> You have " + healthPots + " health potions left.\n");
													
												}
											
												else {
												
													System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
													System.out.println("");
													
												}
											}
									
											else if(action.equals("2")) {
											
												if(superHealthPots > 0) {
												
													health += superHealthPotHeal;
													superHealthPots --;
													
													if(health > maxHealth) {
														health = maxHealth;
													}
													
													System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
																	+ "\n\t> You now have " + health + " HP."
																	+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
													
												}
											
												else {
												
													System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
													System.out.println("");
													
												}
											}
										
											else if(action.equals("3")) {
											
												if(manaPots > 0) {
												
													mana += manaPotRestore;
													manaPots --;
													System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
															+ "\n\t> You now have " + mana + " Mana."
															+ "\n\t> You have " + manaPots + " mana potions left.\n");
													
												}
											
												else {
												
													System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
													System.out.println("");
													
												}
											}
											
											else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
												
												System.out.println("");
												System.out.println("Invalid Command!");
												System.out.println("");
											}
										}
										
										else if(action.equals("4")) {
											
											System.out.println("\tYou run away from the " + enemy + "!"
													+ "\n");
											continue ShadyFight;
										}
										
										else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
											
											System.out.println("");
											System.out.println("\tInvalid command!");
											System.out.println("");
										}
									}
									
									
									// Defeat
									if(health < 1) {
										
										System.out.println("");
										System.out.println("Your soul leaves your body, bound for the unknown.");
										break;
									}
									
									// End of fight
									experience += shadyManEXP;
									gold += shadyManMoney;
									enemiesDefeated ++;		
										
									System.out.println("------------------------------------------------------");
									System.out.println(" # " + enemy + " was defeated! #");
									System.out.println(" # You have gained " + shadyManEXP + " XP #");
									System.out.println(" # You have gained " + shadyManMoney + " gold pieces #");
									System.out.println(" # You have " + health + " HP left #");
									
									leveling();
									
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
										
										flintLock ++;
										System.out.println(" * The " + enemy + " dropped his FlintLock! * ");
									}
									
								}
								
								else if(input.equals("4")) {
									
									System.out.println("You get up and walk away from the Shady Man."
											+ "\n");
									break;
								}
							
							}
							
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You turn and slowly walk awkwardly away."
									+ "\n");
						}
						
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You walk out of the Tavern."
								+ "\n");
						break;
					}
				
				}
				
			}
			
			else if(input.equals("2")) {
				
				System.out.println("You push open a solid door and enter the General Store."
						+ "\nThere is one lonely women looking for fresh ingredients and the Shopkeeper sorting his shelves."
						+ "\n "
						+ "\nOh, the Shopkeeper exclaims, a customer!  He hurries back behind the counter."
						+ "\nJust take a look around and come up to pay when you are ready, items are marked with prices."
						+ "\n");
				
				Shop:
				while(health > 0) {
					
					System.out.println("What would you like to buy?"
							+ "\n");
					System.out.println("1: Bread\t\t15 copper");
					System.out.println("2: Apple\t\t10 copper");
					System.out.println("3: Bronze Sword\t\t50 copper");
					System.out.println("4: Bronze Shield\t50 copper");
					System.out.println("5: Lantern\t\t30 copper");
					System.out.println("6: Sell");
					System.out.println("7: Leave the store");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						System.out.println("Buy bread for 15 copper Carlons?"
								+ "\n");
						System.out.println("1: Buy");
						System.out.println("2: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1") && copper >= 15) {
							
							bread ++;
							copper -= 15;
							System.out.println("You have bought a loaf of bread."
									+ "\n");
						}
						
						else if(input.equals("1") && copper < 15) {
							
							System.out.println("You do not have enough for this item."
									+ "\n");
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You continue looking."
									+ "\n");
							continue Shop;
						}
						
					}
					
					else if(input.equals("2")) {
						
						System.out.println("Buy apple for 10 copper Carlons?"
								+ "\n");
						System.out.println("1: Buy");
						System.out.println("2: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1") && copper >= 10) {
							
							apple ++;
							copper -= 10;
							System.out.println("You have bought an apple."
									+ "\n");
						}
						
						else if(input.equals("1") && copper < 10) {
							
							System.out.println("You do not have enough for this item."
									+ "\n");
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You continue looking."
									+ "\n");
						}
						
					}
					
					else if(input.equals("3")) {
						
						System.out.println("Buy bronze sword for 50 copper Carlons?"
								+ "\n");
						System.out.println("1: Buy");
						System.out.println("2: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1") && copper >= 50) {
							
							bronzeSword ++;
							copper -= 50;
							System.out.println("You have bought a bronze sword."
									+ "\n");
						}
						
						else if(input.equals("1") && copper < 50) {
							
							System.out.println("You do not have enough for this item."
									+ "\n");
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You continue looking."
									+ "\n");
						}
						
					}
					
					else if(input.equals("4")) {
						
						System.out.println("Buy bronze shield for 50 copper Carlons?"
								+ "\n");
						System.out.println("1: Buy");
						System.out.println("2: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1") && copper >= 50) {
							
							bronzeShield ++;
							copper -= 50;
							System.out.println("You have bought a bronze shield."
									+ "\n");
						}
						
						else if(input.equals("1") && copper < 50) {
							
							System.out.println("You do not have enough for this item."
									+ "\n");
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You continue looking."
									+ "\n");
						}
						
					}
					
					else if(input.equals("5")) {
						
						System.out.println("Buy lantern for 30 copper Carlons?"
								+ "\n");
						System.out.println("1: Buy");
						System.out.println("2: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1") && copper >= 30) {
							
							lantern ++;
							copper -= 30;
							System.out.println("You have bought a lantern."
									+ "\n");
						}
						
						else if(input.equals("1") && copper < 30) {
							
							System.out.println("You do not have enough for this item."
									+ "\n");
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You continue looking."
									+ "\n");
						}
						
					}
					
					else if(input.equals("6")) {
						
						System.out.println("The Shopkeeper looks at you intrigued, What have you go?"
								+ "\nJust keep in mind, I don't buy what I can't sell myself."
								+ "\n");
							
						while(health > 0) {
							
							System.out.println("What would you like to sell?"
									+ "\n");
							System.out.println("1: Bronze Sword\t\t25 copper");
							System.out.println("2: Bronze Shield\t25 copper");
							System.out.println("3: Iron Sword\t\t1 silver");
							System.out.println("4: Iron Shield\t\t1 silver");
							System.out.println("5: Buy");
						
							input = in.nextLine();
							
							if(input.equals("1") && bronzeSword > 0 && currentSwordEquip != "Bronze Sword") {
								
								bronzeSword --;
								copper += 25;
								System.out.println("You sell the bronze sword for 25 copper."
										+ "\n");
							}
							
							else if(input.equals("1") && bronzeSword == 0) {
								
								System.out.println("You do not have a bronze sword."
										+ "\n");
							}
							
							else if(input.equals("1") && currentSwordEquip == "Bronze sword") {
								
								System.out.println("You must first remove your sword to sell."
										+ "\n");
							}
							
							if(input.equals("2") && bronzeShield > 0 && currentShieldEquip != "Bronze Shield") {
								
								bronzeShield --;
								copper += 25;
								System.out.println("You sell the bronze shield for 25 copper."
										+ "\n");
							}
							
							else if(input.equals("2") && bronzeShield == 0) {
								
								System.out.println("You do not have a bronze shield."
										+ "\n");
							}
							
							else if(input.equals("2") && currentShieldEquip == "Bronze Shield") {
								
								System.out.println("You must first remove your shield to sell."
										+ "\n");
							}
							
							if(input.equals("3") && ironSword > 0 && currentSwordEquip != "Iron Sword") {
								
								ironSword --;
								silver ++;
								System.out.println("You sell the iron sword for 1 silver."
										+ "\n");
							}
							
							else if(input.equals("3") && ironSword == 0) {
								
								System.out.println("You do not have an iron sword."
										+ "\n");
							}
							
							else if(input.equals("3") && currentSwordEquip == "Iron sword") {
								
								System.out.println("You must first remove your sword to sell."
										+ "\n");
							}
							
							if(input.equals("4") && ironShield > 0 && currentShieldEquip != "Iron Shield") {
								
								ironShield --;
								silver ++;
								System.out.println("You sell the iron shield for 1 silver."
										+ "\n");
							}
							
							else if(input.equals("4") && ironShield == 0) {
								
								System.out.println("You do not have an iron shield."
										+ "\n");
							}
							
							else if(input.equals("4") && currentShieldEquip == "Iron Shield") {
								
								System.out.println("You must first remove your shield to sell."
										+ "\n");
							}
							
							else if(input.equals("5")) {
								
								break;
							}
							
						}
						
					}
					
					else if(input.equals("7")) {
						
						System.out.println("You are satisfied with your visit and leave the shop."
								+ "\n");
						break;
					}
					
				}
				
			}
			
			else if(input.equals("3")) {
				
				System.out.println("You venture into the near vacant Town Square, decorated by a single large fountain. "
						+ "\nThere are only a few farmers attempting to sell cheap goods, and a few Guards lazily standing watch."
						+ "\n");
				
				while(health > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Toss a coin in the fountain");
					System.out.println("2: Interact with a guard");
					System.out.println("3: Interact with a farmer");
					System.out.println("4: Search for the exit of the town");
					System.out.println("5: Back to shops");
					System.out.println("6: Inventory");
					
					
					input = in.nextLine();
					
					if(input.equals("1") && copper > 0) {
						
						copper --;
						System.out.println("You toss a coin into the fountain, hoping for some luck!"
								+ "\n");
						
						int luck = rand.nextInt(100);
						
						if(luck >= 50) {
							
							health ++;
							if(health > maxHealth) {
								health = maxHealth;
							}
							
							System.out.println("Seeing your copper coin sink to the bottom, you feel slightly rejuvinated."
									+ "\n"
									+ "\nYou now have " + health + " HP remaining."
									+ "\n");
						}
						
						else if(luck < 50) {
							
							health --;
							System.out.println("Seeing your copper coin sink to the bottom, you feel slightly drained."
									+ "\n"
									+ "\nYou now have " + health + " HP remaining."
									+ "\n");
						}
						
					}
					
					else if(input.equals("1") && copper < 1) {
						
						System.out.println("You do not have a coin to toss into the fountain."
								+ "\n");
						
					}
					
					else if(input.equals("2")) {
						
						System.out.println("The guard peers at you approaching through sleepy eyes."
								+ "\nHe then yawns and closes then, ignoring you."
								+ "\n");
						// Perhaps enhance interaction later
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You walk up to a farmer, seeing what he has to offer."
								+ "\nHe has a small variety of wares, most useless to a man with no farm."
								+ "\n");
						
						while(health > 0) {
									
							System.out.println("Which item would you like? He eagerly says"
									+ "\n");
							System.out.println("1: Apple\t9 copper");
							System.out.println("2: Bread\t14 copper");
							System.out.println("3: Pumpkin\t20 copper");
							System.out.println("4: Scythe\t45 copper");
							System.out.println("5: Leave");
								
							input = in.nextLine();
								
							if(input.equals("1")) {
									
								System.out.println("Buy apple for 9 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
									
								input = in.nextLine();
									
								if(input.equals("1") && copper >= 9) {
										
									apple ++;
									copper -= 9;
									System.out.println("You have bought an apple."
											+ "\n");
								}
									
								else if(input.equals("1") && copper < 9) {
										
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
									
								else if(input.equals("2")) {
										
									System.out.println("You continue looking."
											+ "\n");
								}
									
							}
								
							else if(input.equals("2")) {
									
								System.out.println("Buy bread for 14 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
									
								input = in.nextLine();
									
								if(input.equals("1") && copper >= 14) {
										
									bread ++;
									copper -= 14;
									System.out.println("You have bought a loaf of bread."
											+ "\n");
								}
									
								else if(input.equals("1") && copper < 14) {
										
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
									
								else if(input.equals("2")) {
										
									System.out.println("You continue looking."
											+ "\n");
								}
									
							}
								
							else if(input.equals("3")) {
									
								System.out.println("Buy pumpkin for 20 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
									
								input = in.nextLine();
									
								if(input.equals("1") && copper >= 20) {
										
									pumpkin ++;
									copper -= 20;
									System.out.println("You have bought a nice sized pumpkin."
											+ "\n");
								}
									
								else if(input.equals("1") && copper < 20) {
										
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
									
								else if(input.equals("2")) {
										
									System.out.println("You continue looking."
											+ "\n");
								}
									
							}
									
							else if(input.equals("4")) {
										
								System.out.println("Buy scythe for 45 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
										
								input = in.nextLine();
										
								if(input.equals("1") && copper >= 45) {
											
									scythe ++;
									copper -= 45;
									System.out.println("You have bought a scythe."
											+ "\n");
								}
										
								else if(input.equals("1") && copper < 45) {
											
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
										
								else if(input.equals("2")) {
											
									System.out.println("You continue looking."
											+ "\n");
								}
									
							}
							
							else if(input.equals("5")) {
								
								System.out.println("You leave the farmer and his goods."
										+ "\n");
								break;
							}
								
						}
							
					}

					else if(input.equals("4")) {
						
						System.out.println("Ahead you see the city's gate leading south."
								+ "\nBeyond it is a lush, green forest which is rumored to be dangerous.");

						area = 2;
						System.out.println("Looking ahead with hope and determination, you decide to leave town."
								+ "\n");
						break;
						
						
					}

					else if(input.equals("5")) {
						
						System.out.println("You take another look around and decide to return to the shops."
								+ "\n");
						break;
					}
					
					else if(input.equals("6")) {
						
						area = 8;
						lastArea = 1;
						break;
						
					}
					
				}
				
			}
			
			else if(input.equals("4")) {
				
				area = 8;
				lastArea = 1;
				break;
				
			}
			
		}
		
	}
	
	
	
	public static void forestToCity() {
		
		area = 2;
		
		// Mobs
		String[] mobs = {"Slime", "Giant Animated Mushroom", "Feral Rabbit", "Giant Spider", "Bandit"};
		int mobHealth = 10;
		int mobAD = 10;
		int mobEXP = 10;
		int mobMoney = 5;
		
		System.out.println("As you enter the forest, daylight dims through thickening treetops."
				+ "\nThe path through the forest is old, but wide enough to walk through.");
		
		Forest:
		while(area == 2 && health > 0) {
			
			System.out.println("");
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Continue to next town");
			System.out.println("2: Look for a monster to fight");
			System.out.println("3: Return to previous town");
			System.out.println("4: Inventory");
			if(forestState > 0) {
				System.out.println("5: Go through caves");
			}
			
			String input = in.nextLine();
			
			if(input.equals("1") && (forestState == 0 || forestState == 1)) {
				
				forestState = 1;
				System.out.println("As you attempt to continue up the path toward the next town,"
						+ "\nA giant boulder is blocking your path and you are forced to turn around."
						+ "\nThough as you do, you see the mouth of a cave through the trees."
						+ "\nThis could be a way around the impassable boulder."
						+ "\n");
			}
			
			else if(input.equals("1") && forestState == 2) {
				
				cityState = 0;
				area = 5;
				System.out.println("You go up the path toward the city and find the boulder pushed away"
						+ "\nThe trip takes some time, but it is much faster and safer."
						+ "\nYou exit the forest and continue onward."
						+ "\n");
				break;
			}
			
			else if(input.equals("2")) {
				
				// Calls enemy interaction
				int mobFightHealth = mobHealth;
				String enemy = mobs[rand.nextInt(mobs.length)];
				System.out.println("\t# A " + enemy + " has appeared! #"
						+ "\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(mobAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You execute the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage"
									+ "\n");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage"
									+ "\n");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Forest;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
			
				// End of fight
				experience += mobEXP;
				copper += mobMoney;
				enemiesDefeated ++;
				
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + mobEXP + " XP #");
				System.out.println(" # You have gained " + mobMoney + " copper pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < healthPotDrop) {
					
					healthPots++;
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
				
			}
			
			else if(input.equals("3")) {
				
				area = 1;
				System.out.println("You leave the forest, deciding to go back to town"
						+ "\n");
				break;
			}
			
			else if(input.equals("4")) {
				
				area = 8;
				lastArea = 2;
				break;
			}
			
			else if(input.equals("5") && forestState > 0) {
				
				area = 3;
				System.out.println("You venture into the caves, searching for a way around."
						+ "\n");
				break;
			}
			
		}
		
	}
	
	
	public static void cave() {
		
		area = 3;
		
		// Mobs
		String[] mobs = {"Goblin", "Giant Rat", "Cave Slime", "Skeleton", "Zombie"};
		int mobHealth = 25;
		int mobAD = 15;
		int mobEXP = 25;
		int mobMoney = 15;
		
		String[] caveTroll = {"Cave Troll"};
		int caveTrollHealth = 50;
		int caveTrollAD = 20;
		int caveTrollEXP = 50;
		int caveTrollMoney = 50;
		
		System.out.println("Climbing into the cave system, you see that it extends deeper then you first thought."
				+ "\nWithout a lantern, you know that the journey through this piercing darkness would be perilous.");
		
		Cave:
		while(area == 3 && health > 0) {
			
			System.out.println("");
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Continue through cave");
			System.out.println("2: Look for a monster to fight");
			System.out.println("3: Return to the forest");
			System.out.println("4: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1") && lantern < 1) {
				
				System.out.println("You attempt to walk foward into the cave, but it is too dark to see anything."
						+ "\nPerhaps you should go back to town and buy a lantern."
						+ "\n");
			}
			
			else if(input.equals("1") && lantern > 0) {
				
				System.out.println("You proceed into the cave system, holding your lantern in front of you."
						+ "\nEvery step sends shivers up your spine, feeling like something is watching you."
						+ "\n"
						+ "\nAs you step further, you can hear a deep, loud grunting sound ahead."
						+ "\n");
				
				// Calls enemy interaction
				int mobFightHealth = caveTrollHealth;
				String enemy = caveTroll[rand.nextInt(caveTroll.length)];
				System.out.println("\t# A " + enemy + " has appeared! #"
						+ "\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(caveTrollAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You behead the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Cave;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += caveTrollEXP;
				copper += caveTrollMoney;
				enemiesDefeated ++;		
					
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + caveTrollEXP + " XP #");
				System.out.println(" # You have gained " + caveTrollMoney + " copper pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < healthPotDrop) {
					
					healthPots++;
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You now have " + healthPots + " health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
					System.out.println(" # The " + enemy + " dropped a mana potion! # ");
					System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
				}
				
				if(rand.nextInt(100) < superHealthPotDrop) {
					
					superHealthPots++;
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
				
				cityState = 0;
				area = 5;
				System.out.println("");
				System.out.println("You have defeated the Cave Troll and cleared the path in front of you."
						+ "\nAfter a short time walking, you find the exit to the cave and continue on your adventure."
						+ "\n");
				break;
			}
			
			else if(input.equals("2")) {
				
				// Calls enemy interaction
				int mobFightHealth = mobHealth;
				String enemy = mobs[rand.nextInt(mobs.length)];
				System.out.println("\t# A " + enemy + " has appeared! #\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(mobAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You execute the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Cave;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += mobEXP;
				copper += mobMoney;
				enemiesDefeated ++;
				
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + mobEXP + " XP #");
				System.out.println(" # You have gained " + mobMoney + " copper pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < healthPotDrop) {
					
					healthPots++;
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You now have " + healthPots + " health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
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
				
			}
			
			else if(input.equals("3")) {
				
				area = 2;
				System.out.println("You decide going through the cave is not wise at this time."
						+ "\n");
				break;
				
			}
			
			else if(input.equals("4")) {
	
					area = 8;
					lastArea = 3;
					break;
			}
			
		}
		
	}
	
	
	
	public static void forestToTown() {
		
		area = 4;
		forestState = 2;
		
		// Mobs
		String[] mobs = {"Dryad", "Feral Unicorn", "Satyr", "Nymph", "Owlbear", "Troll"};
		int mobHealth = 50;
		int mobAD = 25;
		int mobEXP = 50;
		int mobMoney = 50;
		
		System.out.println("You leave the city and head back to the forest."
				+ "\nYou notice as you head into the treeline, that this side is darker."
				+ "\nThe monsters in this area of the forest are stronger, but more rewarding.");
		
		Forest:
		while(area == 4 && health > 0) {
			
			System.out.println("");
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Continue to town");
			System.out.println("2: Find a monster to fight");
			System.out.println("3: Return to city");
			System.out.println("4: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1")) {
				
				area = 1;
				System.out.println("You continue toward the small town."
						+ "\nYou also find the path has been cleared of the boulder."
						+ "\nThe trip is long, but you eventually arrive."
						+ "\n");
				break;
			}
			
			else if(input.equals("2")) {
				
				// Calls enemy interaction
				int mobFightHealth = mobHealth;
				String enemy = mobs[rand.nextInt(mobs.length)];
				System.out.println("\t# A " + enemy + " has appeared! #\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(mobAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You execute the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Forest;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += mobEXP;
				copper += mobMoney;
				enemiesDefeated ++;
				
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + mobEXP + " XP #");
				System.out.println(" # You have gained " + mobMoney + " copper pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < healthPotDrop) {
					
					healthPots++;
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You now have " + healthPots + " health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
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
				
			}
			
			else if(input.equals("3")) {
				
				area = 5;
				System.out.println("You walk back toward the city, leaving the dark forest behind."
						+ "\n");
				break;
			}
			
			else if(input.equals("4")) {
				
				area = 8;
				lastArea = 4;
				break;
			}
			
		}
		
	}
	
	
	
	public static void city() {
		
		area = 5;
		
		if(cityState == 0) {
		System.out.println("After an arduous journey, you finally arrive at a large city."
				+ "\nAs you enter you find that it is teeming with life."
				+ "\nCrowds of people swarming around you on every side hustling and bustling."
				+ "\nLuckily there are street signs, pointing to many locations around you."
				+ "\n");
		}
		
		else {
			System.out.println("");
		}
		
		while(area == 5 && health > 0) {
			
			cityState = 1;
			System.out.println("Where would you like to go?"
					+ "\n");
			System.out.println("1: Inn");
			System.out.println("2: Shops");
			System.out.println("3: Guilds");
			System.out.println("4: Castle");
			System.out.println("5: Back to forest");
			System.out.println("6: Head toward the mountains");
			System.out.println("7: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1")) {
				
				System.out.println("You walk into one of few inns that dot the main street."
						+ "\n"
						+ "\nThe sweet smell of pie, and good ale is in the air."
						+ "\nThere is a Innkeeper tending to guests coming and going."
						+ "\nThere is also a bar area on the ground floor."
						+ "\n");
				
				Inn:
				while(health > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Speak to Innkeeper");
					System.out.println("2: Speak to Barkeeper");
					System.out.println("3: Speak to locals");
					System.out.println("4: Leave");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						System.out.println("The Innkeeper smiles warmly as you walk toward him."
								+ "\nWhat can I do for you sir?"
								+ "\n");
						System.out.println("1: Rent a room for the night");
						System.out.println("2: Purchase a room");
						System.out.println("3: Nevermind");
						if(roomState == 1) {
							System.out.println("4: Go to your room");
						}
						
						input = in.nextLine() ;
						
						if(input.equals("1")) {
							
							System.out.println("Wish to stay the night?  Very well, fifty copper Carlons please."
									+ "\n");
							System.out.println("1: Rent");
							System.out.println("2: Nevermind");
						
							input = in.nextLine();
						
							if(input.equals("1") && copper >= 50) {
							
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
							}
						
							else if(input.equals("1") && copper < 50) {
							
								System.out.println("You do not have enough for a room. "
										+ "\n");
							}
						
						}
						
						else if(input.equals("2")) {
							
							System.out.println("You want a residence here eh?  That will be 5 gold Carlons."
									+ "\n");
							System.out.println("1: Buy room");
							System.out.println("2: Nevermind");
							
							input = in.nextLine();
						
							if(input.equals("1") && gold >= 5 && roomState == 0) {
							
								roomState = 1;
								gold -= 10;
								System.out.println("You purchase a place to call home"
										+ "\nThe Innkeeper hands you a key and tells you the room is upstairs."
										+ "\n");
							}
						
							else if(input.equals("1") && gold < 5 || roomState == 1) {
							
								System.out.println("You either do not have enough coins, or already purchase the room."
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
							}
							
						}
						
						else if(input.equals("3")) {
							
							System.out.println("You have second thoughts and turn away."
									+ "\n");
						}
						
						else if(input.equals("4") && roomState == 1) {
							
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
						}
						
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You wander over to a busy bar and luckily find a seat."
								+ "\nThe Barkeeper is serving drinks of all kinds and takes notice of you."
								+ "\nHe yells over the commotion, What can I get for you?"
								+ "\n");
						System.out.println("1: Beer");
						System.out.println("2: Apple Pie");
						System.out.println("3: Rumors");
						System.out.println("4: Nevermind");
						
						input = in.nextLine();
						
						if(input.equals("1")) {
							
							System.out.println("Want a beer?  That will be ten copper Carlons."
									+ "\n");
							System.out.println("1: Buy");
							System.out.println("2: Nevermind");
							
							input = in.nextLine();
							
							if(input.equals("1") && copper >= 10) {
								
								beer ++;
								copper -= 10;
								System.out.println("You purchase a beer. "
										+ "\n");
								System.out.println("The Barkeeper pours out a cold one."
										+ "\nWell then, here you are.  Enjoy!"
										+ "\n");
							}
							
							else if(input.equals("1") && copper < 10) {
								
								System.out.println("You do not have enough to buy a drink. "
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
							}
						}
						
						else if(input.equals("2")) {
							
							System.out.println("Want a piece of pie?  One silver Carlon please."
									+ "\n");
							System.out.println("1: Buy");
							System.out.println("2: Nevermind");
							
							input = in.nextLine();
							
							if(input.equals("1") && silver >= 1) {
								
								applePie ++;
								silver -= 1;
								System.out.println("You purchase a piece of pie. "
										+ "\n");
								System.out.println("The Barkeeper slides you over a plate."
										+ "\nHere you are, best pie in the city.  Enjoy!"
										+ "\n");
							}
							
							else if(input.equals("1") && silver < 1) {
								
								System.out.println("You do not have enough for pie. "
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("You have second thoughts and turn away."
										+ "\n");
							}
						}
						
						else if(input.equals("3")) {
							
							System.out.println("From what I have heard, the King is sending brave folk on a quest."
									+ "\nHe said it is extremely dangerous, and not to be taken lightly."
									+ "\nIf you are feeling up to it, go seek an audience with His Majesty."
									+ "\n");
						}
						
						else if(input.equals("4")) {
							
							System.out.println("You have second thought and turn away."
									+ "\n");
						}
						
					}
					
					else if(input.equals("3")) {
						
						System.out.println("The local folk do not take notice of you."
								+ "\nThey are too busy drinking and laughing."
								+ "\n");
						continue Inn;
					}
					
					else if(input.equals("4")) {
						
						System.out.println("You exit the inn, returning to busy streets."
								+ "\n");
						break;
						
					}
					
				}
				
			}
			
			else if(input.equals("2")) {
				
				System.out.println("You follow the signs west to what appears to be a shopping district."
						+ "\nThere is a good variety to choose from."
						+ "\n");
				
				while(health > 0) {
					
					System.out.println("Where would you like to go?"
							+ "\n");
					System.out.println("1: General Store");
					System.out.println("2: Weapons and Armor Shop");
					System.out.println("3: Magic Shop");
					System.out.println("4: Inventory");
					System.out.println("5: Castle");
					System.out.println("6: Return to Main Street");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
					
						System.out.println("You push open a solid stone door and enter the General Store."
								+ "\nThere appears to be several people browsing it's wares."
								+ "\nThe Shopkeeper welcomes you from behind the counter. "
								+ "\n"
								+ "\nHe kindly says, Just take a look around and come up to pay when you are ready."
								+ "\n");
						
						Shop:
						while(health > 0) {
							
							System.out.println("What would you like to buy?"
									+ "\n");
							System.out.println("1: Bread\t\t20 copper");
							System.out.println("2: Apple\t\t10 copper");
							System.out.println("3: Health Potion\t50 copper");
							System.out.println("4: Mana Potion\t\t50 copper");
							System.out.println("5: Apple Pie\t\t1 silver");
							System.out.println("6: Leave the store");
							
							input = in.nextLine();
							
							if(input.equals("1")) {
								
								System.out.println("Buy bread for 20 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && copper >= 20) {
									
									bread ++;
									copper -= 20;
									System.out.println("You have bought a loaf of bread."
											+ "\n");
								}
								
								else if(input.equals("1") && copper < 20) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Shop;
								}
								
							}
							
							else if(input.equals("2")) {
								
								System.out.println("Buy apple for 10 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && copper >= 10) {
									
									apple ++;
									copper -= 10;
									System.out.println("You have bought an apple."
											+ "\n");
								}
								
								else if(input.equals("1") && copper < 10) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
								}
								
							}
							
							else if(input.equals("3")) {
								
								System.out.println("Buy health potion for 50 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && copper >= 50) {
									
									healthPots ++;
									copper -= 50;
									System.out.println("You have bought a health potion."
											+ "\n");
								}
								
								else if(input.equals("1") && copper < 50) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
								}
								
							}
							
							else if(input.equals("4")) {
								
								System.out.println("Buy mana potion for 50 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && copper >= 50) {
									
									manaPots ++;
									copper -= 50;
									System.out.println("You have bought a mana potion."
											+ "\n");
								}
								
								else if(input.equals("1") && copper < 50) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
								}
								
							}
							
							else if(input.equals("5")) {
								
								System.out.println("Buy apple pie for 1 silver Carlon?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 1) {
									
									applePie ++;
									silver -= 1;
									System.out.println("You have bought an apple pie."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 1) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
								}
								
							}
							
							else if(input.equals("6")) {
								
								System.out.println("You are satisfied with your visit and leave the shop."
										+ "\n");
								break;
							}
							
						}
					
					}
				
					else if(input.equals("2")) {
						
						System.out.println("You enter what appears to be an active Smithy."
								+ "\nIt has a section for buying, and another for producing."
								+ "\nThe Smith calls to you as you enter, Hello!"
								+ "\nWe have what you need on display over by the counter."
								+ "\nJust be careful not to cut or burn yourself."
								+ "\n");
						
						Blacksmith:
						while(health > 0) {
							
							System.out.println("What would you like to buy?"
									+ "\n");
							System.out.println("1: Iron Sword\t\t5 silver");
							System.out.println("2: Iron Shield\t\t5 silver");
							System.out.println("3: Steel Sword\t\t15 silver");
							System.out.println("4: Steel Shield\t\t15 silver");
							System.out.println("5: Mithril Sword\t30 silver");
							System.out.println("6: Mithril Shield\t30 silver");
							System.out.println("7: Sell");
							System.out.println("8: Leave the store");
							
							input = in.nextLine();
							
							if(input.equals("1")) {
								
								System.out.println("Buy iron sword for 5 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 5) {
									
									ironSword ++;
									silver -= 5;
									System.out.println("You have bought an iron sword."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 5) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							else if(input.equals("2")) {
								
								System.out.println("Buy iron shield for 5 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 5) {
									
									ironShield ++;
									silver -= 5;
									System.out.println("You have bought an apple."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 5) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							else if(input.equals("3")) {
								
								System.out.println("Buy steel sword for 15 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 15) {
									
									steelSword ++;
									silver -= 15;
									System.out.println("You have bought a steel sword."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 15) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							else if(input.equals("4")) {
								
								System.out.println("Buy steel shield for 15 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 15) {
									
									steelShield ++;
									silver -= 15;
									System.out.println("You have bought a steel shield."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 15) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							else if(input.equals("5")) {
								
								System.out.println("Buy mithril sword for 30 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 30) {
									
									mithrilSword ++;
									silver -= 30;
									System.out.println("You have bought a mithril sword."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 30) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							else if(input.equals("6")) {
								
								System.out.println("Buy mithril shield for 30 silver Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 30) {
									
									mithrilShield ++;
									silver -= 30;
									System.out.println("You have bought a mithril shield."
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 30) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Blacksmith;
								}
								
							}
							
							if(input.equals("7")) {
								
								System.out.println("The Shopkeeper asks, What can you offer?"
										+ "\nHe also says, Just keep in mind, I don't buy junk."
										+ "\n");
								
								while(health > 0) {
									
									System.out.println("What would you like to sell?"
											+ "\n");
									System.out.println("1: Steel Sword\t\t5 silver");
									System.out.println("2: Steel Shield\t\t5 silver");
									System.out.println("3: Mithril Sword\t10 silver");
									System.out.println("4: Mithril Shield\t10 silver");
									System.out.println("5: Adamant Sword\t30 silver");
									System.out.println("6: Adamant Shield\t30 silver");
									System.out.println("7: Buy");
							
									input = in.nextLine();
								
									if(input.equals("1") && steelSword > 0 && currentSwordEquip != "Steel Sword") {
									
										steelSword --;
										silver += 5;
										System.out.println("You sell the steel sword for 5 silver."
												+ "\n");
									}
								
									else if(input.equals("1") && steelSword == 0) {
									
										System.out.println("You do not have a steel sword."
												+ "\n");
									}
								
									else if(input.equals("1") && currentSwordEquip == "Steel sword") {
									
										System.out.println("You must first remove your sword to sell."
												+ "\n");
									}
								
									if(input.equals("2") && steelShield > 0 && currentShieldEquip != "Steel Shield") {
									
										steelShield --;
										silver += 5;
										System.out.println("You sell the steel shield for 5 silver."
												+ "\n");
									}
								
									else if(input.equals("2") && steelShield == 0) {
									
										System.out.println("You do not have a steel shield."
												+ "\n");
									}
								
									else if(input.equals("2") && currentShieldEquip == "Steel Shield") {
									
										System.out.println("You must first remove your shield to sell."
												+ "\n");
									}
									
									if(input.equals("3") && mithrilSword > 0 && currentSwordEquip != "Mithril Sword") {
									
										mithrilSword --;
										silver += 10;
										System.out.println("You sell the mithril sword for 10 silver."
												+ "\n");
									}
								
									else if(input.equals("3") && mithrilSword == 0) {
									
										System.out.println("You do not have a mithril sword."
												+ "\n");
									}
								
									else if(input.equals("3") && currentSwordEquip == "Mithril sword") {
									
										System.out.println("You must first remove your sword to sell."
												+ "\n");
									}
									
									if(input.equals("4") && mithrilShield > 0 && currentShieldEquip != "Mithril Shield") {
									
										mithrilShield --;
										silver += 10;
										System.out.println("You sell the mithril shield for 10 silver."
												+ "\n");
									}
								
									else if(input.equals("4") && mithrilShield == 0) {
									
										System.out.println("You do not have a mithril shield."
												+ "\n");
									}
								
									else if(input.equals("4") && currentShieldEquip == "Mithril Shield") {
									
										System.out.println("You must first remove your shield to sell."
												+ "\n");
									}
									
									if(input.equals("5") && adamantSword > 0 && currentSwordEquip != "Adamant Sword") {
										
										adamantSword --;
										silver += 30;
										System.out.println("You sell the adamant sword for 30 silver."
												+ "\n");
									}
								
									else if(input.equals("5") && adamantSword == 0) {
									
										System.out.println("You do not have an adamant sword."
												+ "\n");
									}
								
									else if(input.equals("5") && currentSwordEquip == "Adamant sword") {
									
										System.out.println("You must first remove your sword to sell."
												+ "\n");
									}
									
									if(input.equals("6") && adamantShield > 0 && currentShieldEquip != "Adamant Shield") {
									
										adamantShield --;
										silver += 30;
										System.out.println("You sell the adamant shield for 30 silver."
												+ "\n");
									}
								
									else if(input.equals("6") && mithrilShield == 0) {
									
										System.out.println("You do not have an adamant shield."
												+ "\n");
									}
								
									else if(input.equals("6") && currentShieldEquip == "Adamant Shield") {
									
										System.out.println("You must first remove your shield to sell."
												+ "\n");
									}
								
									else if(input.equals("7")) {
									
										break;
									}
								
								}
								
							}
							
							else if(input.equals("8")) {
								
								System.out.println("You are satisfied with your visit and leave the shop."
										+ "\n");
								break;
							}
							
						}
					
					}
				
					else if(input.equals("3")) {
					
						System.out.println("You wander into the magic shop, a blast of strange aromas fill your nose"
								+ "\nA few Mage's Guild members are browsing alchemical ingredients."
								+ "\nThe shop owner says without looking at you, What can I do for you young one?."
								+ "\n");
						
						Magic:
						while(health > 0) {
							
							System.out.println("What would you like to do?"
									+ "\n");
							System.out.println("1: Mana Potion\t\t50 copper");
							System.out.println("2: Learn spell: Absorb  2 silver");
							System.out.println("3: Leave");
							
							input = in.nextLine();
							
							if(input.equals("1")) {
								
								System.out.println("Buy mana potion for 50 copper Carlons?"
										+ "\n");
								System.out.println("1: Buy");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && copper >= 50) {
									
									manaPots ++;
									copper -= 50;
									System.out.println("You have bought a mana potion."
											+ "\n");
								}
								
								else if(input.equals("1") && copper < 50) {
									
									System.out.println("You do not have enough for this item."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Magic;
								}
								
							}
							
							else if(input.equals("2")) {
								
								System.out.println("Learn absorb for 2 silver Carlons?"
										+ "\n");
								System.out.println("1: Learn");
								System.out.println("2: Nevermind");
								
								input = in.nextLine();
								
								if(input.equals("1") && silver >= 2) {
									
									spellState ++;
									silver -= 2;
									System.out.println("You have been taught how to use the absorb spell!"
											+ "\n");
								}
								
								else if(input.equals("1") && silver < 2) {
									
									System.out.println("You do not have enough to learn this spell."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You continue looking."
											+ "\n");
									continue Magic;
								}
								
							}
								
							else if(input.equals("3")) {
									
								System.out.println("You say goodbye and leave the shop."
										+ "\n");
								break;
							}
								
						}
					
					}
				
					else if(input.equals("4")) {
					
						area = 8;
						lastArea = 5;
						break;
					}
				
					else if(input.equals("5")) {
					
						area = 6;
						System.out.println("Looking toward the north end of the sprawling city,"
								+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
								+ "\n");
						break;
					}
				
					else if(input.equals("6")) {
					
						System.out.println("You return to the main street."
								+ "\n");
						break;
					}
				
				}
				
			}
			
			else if(input.equals("3")) {
				
				System.out.println("You turn down a constricted side street that winds its way east,"
						+ "\nAfter a few minutes of walking, the street opens up into a large courtyard."
						+ "\nThere are a few large buildings with flags hanging outside and pictures on them."
						+ "\nOne has crossed swords, the second has a wizards hat, a third depicts stacks of coins."
						+ "\n");
				
				while(health > 0) {
					
					System.out.println("Where would you like to go?"
							+ "\n");
					System.out.println("1: Warriors Guild");
					System.out.println("2: Mages Guild");
					System.out.println("3: Merchants Guild");
					System.out.println("4: Inventory");
					System.out.println("5: Castle");
					System.out.println("6: Return to Main Street");
				
					input = in.nextLine();
				
					if(input.equals("1")) {
						
						if(guildState == 0) {
							
						System.out.println("You step foot inside the Warriors guild."
								+ "\nThere are all sorts of weapons in display cases and robust armor on the walls."
								+ "\nA burly man with white armor and a clipboard walks up to you."
								+ "\nHello there, he says, what can I do for you stranger."
								+ "\n");
						}
						
						else if(guildState == 1) {
							
							System.out.println("You walk back into the guild and the man in white notices you."
									+ "\nWelcome back " + name + "!  What is on the agenda today?"
									+ "\n");
							
						}
						
						while(health > 0) {
							
							if(guildState == 0) {
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Ask about Guild");
								System.out.println("2: Join Guild");
								System.out.println("3: Leave");
							
								input = in.nextLine();
							
								if(input.equals("1")) {
									
									System.out.println("The man responds, We are a guild for warriors and heros."
											+ "\nOur numbers are few these days, but we are actively recruiting."
											+ "\nWe take on dangerous quests, and fight fearsome foes!"
											+ "\nYou look the fighting type, perhaps you should consider joining."
											+ "\n");
								}
								
								else if(input.equals("2")) {
									
									System.out.println("So you want to join?  Great!"
											+ "\nJust sign these forms and waivers, simple legal issues."
											+ "\nBasically we are not held liable for your injury or death."
											+ "\n");
									System.out.println("What would you like to do?"
											+ "\n");
									System.out.println("1: Join");
									System.out.println("2: Nevermind");
									
									input = in.nextLine();
									
									if(input.equals("1")) {
										
										guildState = 1;
										System.out.println("Perfect! The man says.  Just finish filling these out."
												+ "\nCome speak to me when you are done, I can get you going."
												+ "\n");
									}
									
									else if(input.equals("2")) {
										
										System.out.println("You decide against it and tell him perhaps another time."
												+ "\n");
									}
									
								}
								
								else if(input.equals("3")) {
									
									System.out.println("You decide to leave instead of joining."
											+ "\n");
									break;
								}
							}
							
							else if(guildState == 1) {
								
								System.out.println("What would you like to do?"
										+ "\n");
								System.out.println("1: Aquire Mission");
								System.out.println("2: Browse Guild Shop");
								System.out.println("3: Leave");
								
								input = in.nextLine();
								
								if(input.equals("1")) {
									
									System.out.println("You walk over to the Mission Board and see what is available."
											+ "\nThe man in white walks over and checks with you."
											+ "\nWell, he says, right now we only have monster clearing missions."
											+ "\nBasically you kill a good number of them, and we can reward you."
											+ "\n");
									System.out.println("What would you like to do?"
											+ "\n");
									System.out.println("1: Accept Mission");
									System.out.println("2: Finish Mission");
									System.out.println("3: Leave board");
									
									input = in.nextLine();
									
									if(input.equals("1") && guildMission == 0) {
										
										enemiesDefeated = 0;
										guildMission ++;
										System.out.println("You accept the kill mission."
												+ "\nYou must kill 100 monsters to complete this mission."
												+ "\n");
									}
									
									else if(input.equals("1") && guildMission == 1) {
										
										System.out.println("You already have a mission to complete."
												+ "\n");
									}
									
									else if(input.equals("2") && guildMission == 0) {
										
										System.out.println("You must aquire a mission to finish it."
												+ "\n");
									}
									
									else if(input.equals("2") && guildMission == 1)  {
										
										if(enemiesDefeated < 100) {
											
											System.out.println("You have not killed enough monsters to finish mission."
													+ "\nYou have killed " + enemiesDefeated + "/100 monsters."
													+ "\n");
										}
										
										else if(enemiesDefeated > 99) {
											
											enemiesDefeated -= 100;
											silver += 5;
											warriorToken ++;
											System.out.println("You turn in the mission and collect the reward."
													+ "\nYou have gained 5 silver coins, and 1 warrior token."
													+ "\n");
										}
										
									}
									
									else if(input.equals("3")) {
										
										System.out.println("You leave the board."
												+ "\n");
									}
									
								}
								
								else if(input.equals("2")) {
									
									System.out.println("You walk over to what appears to be a conventional shop."
											+ "\nThe Guild Shopkeeper sees you coming and kindly says."
											+ "\nThe only way to buy special items here is to spend Guild Tokens."
											+ "\n");
									
									while(health > 0) {
										
										System.out.println("What would you like to buy?"
												+ "\n");
										System.out.println("1: Super Health Potion\t1 silver");
										System.out.println("2: Guild Sword\t\t1 token");
										System.out.println("3: Guild Shield\t\t1 token");
										System.out.println("4: Leave shop");
										
										input = in.nextLine();
										
										if(input.equals("1")) {
												
											System.out.println("Buy super health potion for 1 silver Carlon?"
													+ "\n");
											System.out.println("1: Buy");
											System.out.println("2: Nevermind");
												
											input = in.nextLine();
												
											if(input.equals("1") && silver >= 1) {
													
												superHealthPots ++;
												silver --;
												System.out.println("You have bought a super health potion."
														+ "\n");
											}
												
											else if(input.equals("1") && silver < 1) {
													
												System.out.println("You do not have enough for this item."
														+ "\n");
											}
												
											else if(input.equals("2")) {
													
												System.out.println("You continue looking."
														+ "\n");
											}
											
										}
											
										else if(input.equals("2")) {
											
											System.out.println("Buy guild sword for 1 token?"
													+ "\n");
											System.out.println("1: Buy");
											System.out.println("2: Nevermind");
													
											input = in.nextLine();
													
											if(input.equals("1") && warriorToken >= 1) {
														
												guildSword ++;
												warriorToken --;
												System.out.println("You have bought a guild sword."
														+ "\n");
											}
													
											else if(input.equals("1") && warriorToken < 1) {
														
												System.out.println("You do not have enough for this item."
														+ "\n");
											}
													
											else if(input.equals("2")) {
														
												System.out.println("You continue looking."
														+ "\n");
											}
												
										}
										
										else if(input.equals("3")) {
											
											System.out.println("Buy guild shield for 1 token?"
													+ "\n");
											System.out.println("1: Buy");
											System.out.println("2: Nevermind");
													
											input = in.nextLine();
													
											if(input.equals("1") && warriorToken >= 1) {
														
												guildShield ++;
												warriorToken --;
												System.out.println("You have bought a guild shield."
														+ "\n");
											}
													
											else if(input.equals("1") && warriorToken < 1) {
														
												System.out.println("You do not have enough for this item."
														+ "\n");
											}
													
											else if(input.equals("2")) {
														
												System.out.println("You continue looking."
														+ "\n");
											}
												
										}
										
										else if(input.equals("4")) {
											
											System.out.println("You walk away from the guild shop."
													+ "\n");
											break;
										}
										
									}
									
								}
								
								else if(input.equals("3")) {
									
									System.out.println("You say goodbye and leave the guild hall."
											+ "\n");
									break;
								}
								
							}
							
						}
						
					}
				
					else if(input.equals("2")) {
					
						System.out.println("Guild hall currently closed."
								+ "\n");
					
					}
				
					else if(input.equals("3")) {
					
						System.out.println("You walk through gilded doors and into the Merchants guild."
								+ "\nThere are a great many people rummaging through papers and signing documents."
								+ "\nYou notice booths meant for money exchange and questions."
								+ "\nThe clerks behind them welcome you and beckon you to step forward."
								+ "\n");
						
						while(health > 0) {
							
							System.out.println("What would you like to do?"
									+ "\n");
							System.out.println("1: Inquire about joining");
							System.out.println("2: Exchange currency");
							System.out.println("3: Leave");
							
							input = in.nextLine();
							
							if(input.equals("1")) {
								
								System.out.println("You ask about joining this prestigious organization."
										+ "\nThe clerk sizes you up estimating your worth."
										+ "\nHe tells you that they are currently not accepting new members."
										+ "\n");
							}
							
							else if(input.equals("2")) {
								
								System.out.println("Which coin would you like to exchange?"
										+ "\n");
								System.out.println("1: Gold - Silver");
								System.out.println("2: Silver - Copper");
								
								input = in.nextLine();
								
								if(input.equals("1") && gold > 0) {
									
									gold --;
									silver += 100;
									System.out.println("You exchange 1 gold coin for 100 silver coins."
											+ "\n");
								}
								
								else if(input.equals("1") && gold == 0) {
									
									System.out.println("You do not have any gold coins to exchange."
											+ "\n");
								}
								
								if(input.equals("2") && silver > 0) {
									
									silver --;
									copper += 100;
									System.out.println("You exchange 1 silver coin for 100 copper coins."
											+ "\n");
								}
								
								else if(input.equals("2") && silver == 0) {
									
									System.out.println("You do not have any gold coins to exchange."
											+ "\n");
								}
							}
							
							else if(input.equals("3")) {
								
								System.out.println("You walk out of the guild hall."
										+ "\n");
								break;
							}
						}
					
					}
				
					else if(input.equals("4")) {
					
						area = 8;
						lastArea = 5;
						break;
					}
				
					else if(input.equals("5")) {
					
						area = 6;
						System.out.println("Looking toward the north end of the sprawling city,"
								+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
								+ "\n");
						break;
					}
				
					else if(input.equals("6")) {
					
						System.out.println("You return to the main street."
								+ "\n");
						break;
					}
					
				}
				
			}
			
			else if(input.equals("4")) {
				
				area = 6;
				System.out.println("Looking toward the north end of the sprawling city,"
						+ "\nYou see an impressively ornate caslte, and naturally walk toward it."
						+ "\n");
				break;
			}
			
			else if(input.equals("5")) {
				
				area = 4;
				System.out.println("As you turn to leave, you hear people speaking."
						+ "\nThey are making mention of brute-like monsters in the forest."
						+ "\n"
						+ "\nLeaving the city, you head back toward the small town you came from."
						+ "\nPerhaps the boulder that was once blocking your way is gone."
						+ "\n");
				break;
			}
			
			else if(input.equals("6") && finalQuest == 0) {
				
				System.out.println("A guard stops you from going through this gate."
						+ "\nSorry civilian, only those granted access by the King may pass."
						+ "\n");
			}
			
			else if(input.equals("6") && finalQuest == 1) {
				
				area = 7;
				System.out.println("You leave the city to go toward a large, black mountain range."
						+ "\n");
				break;
			}
			
			else if(input.equals("7")) {
				
				area = 8;
				lastArea = 5;
				break;
			}
			
		}
		
	}
	
	
	public static void castle() {
		
		area = 6;
		
		System.out.println("You walk up to the castle's imposing gates."
				+ "\nIt is heavily guarded by crossbowmen."
				+ "\nWithout difficulty, you enter the gate and head inside."
				+ "\n");
		
		while(area == 6 && health > 0) {
			
			System.out.println("Where would you like to go?"
					+ "\n");
			System.out.println("1: Throne room");
			System.out.println("2: Dungeon");
			System.out.println("3: Barracks");
			System.out.println("4: Return to city");
			System.out.println("5: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1")) {
				
				System.out.println("You walk through the courtyard of the castle and enter large double doors."
						+ "\nInside expands into a Grand Hall, filled with Honor Guards and Noblepeople"
						+ "\nYou see, sitting on a throne made of pure sapphire, is Carlon, King of the Realm."
						+ "\n");
				
				while(health > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Speak to the King");
					System.out.println("2: Speak to a Nobleperson");
					System.out.println("3: Leave");
				
					input = in.nextLine();
				
					if(input.equals("1") && kingState == 1 && gameBeat == 0) {
					
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
						System.out.println("2: Leave");
					
						input = in.nextLine();
					
						if(input.equals("1") && finalQuest == 0) {
						
							System.out.println("You inquire about this quest."
									+ "\nThe King states that there is a Large Dragon in the Black Mountains."
									+ "\nIt is wreaking havoc on his kingdom and must be killed."
									+ "\nThe task is perilous, but the reward is great."
									+ "\n");
							System.out.println("What would you like to do?"
								+ "\n");
							System.out.println("1: Accept");
							System.out.println("2: Deny");
								
							input = in.nextLine();
							
							if(input.equals("1")) {
							
								finalQuest = 1;
								System.out.println("You accept the task of killing this dragon."
										+ "\nThe King is surprised, but pleased."
										+ "\nHe says, I hearby grant you passage to the mountains!"
										+ "\nKnow that your life is in your hands, but be careful nonetheless."
										+ "\nHe then waves you off in order to tend to other matters."
										+ "\n");
							}
						
							else if(input.equals("2")) {
							
								System.out.println("You think about it and decide not to accept thie quest."
										+ "\n");
							}
								
						}
						
						else if(input.equals("1") && finalQuest == 1) {
							
							System.out.println("You have already accepted this quest."
									+ "\n");
						}
							
						else if(input.equals("2")) {
						
							System.out.println("You bow politely and walk away from the King."
									+ "\n");
							break;
						}
					
					}
				
					else if(input.equals("1") && kingState == 0) {
					
						System.out.println("You attempt to approach the King; however, many people are before you."
								+ "\n");
					}
					
					else if(input.equals("1") && gameBeat == 1) {
						
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
								+ "\nWith that, he says, you may now enter the Dungeon as you please."
								+ "\nAs well as sleep in the barracks."
								+ "\nCongratulations Hero!"
								+ "\n");
					}
				
					else if(input.equals("2") && kingState == 1) {
					
						System.out.println("The Nobleman you spoke to just smiles and bows."
								+ "\nHe does not wish to speak further and turns back to his friends."
								+ "\n");
					}
				
					else if(input.equals("2") && kingState == 0) {
					
						System.out.println("You walk up to a Nobleman and get his attention."
								+ "\nHe looks at you judgementally and is about to get back to his friends."
								+ "\n");
						System.out.println("What do you do?"
								+ "\n");
						System.out.println("1: Speak to him");
						System.out.println("2: Bow");
						System.out.println("3: Leave him alone");
					
						input = in.nextLine();
					
						if(input.equals("1") && nobleState == 1) {
						
							System.out.println("He says, I am assuming you wish to speak to King Carlon?"
									+ "\nThat privilege does not come cheap young one, ten silver."
									+ "\n");
							System.out.println("What do you do?"
									+ "\n");
							System.out.println("1: Hand over 10 silver Carlons");
							System.out.println("2: Leave him alone");
						
							input = in.nextLine();
						
							if(input.equals("1") && silver >= 10) {
							
								kingState = 1;
								System.out.println("You hand over ten silver coins, and he greedily takes them."
										+ "\nHe says, Just walk back up to the King, and I will vouch for you."
										+ "\n");
							}
						
							else if(input.equals("1") && silver < 10) {
							
								System.out.println("You do not have enough for this."
										+ "\n");
							}
						
							else if(input.equals("2")) {
							
								System.out.println("You turn and leave this man alone."
										+ "\n");
							}
						}
					
						else if(input.equals("1") && nobleState == 0) {
							
							System.out.println("He ignores you and turns back around."
									+ "\n");
						}
					
						else if(input.equals("2")) {
						
							nobleState = 1;
							System.out.println("You bow courteously to this man, and he returns in kind."
									+ "\n");
						}
					
						else if(input.equals("3")) {
						
							System.out.println("You turn and leave this man alone."
									+ "\n");
						}
					}
				
					else if(input.equals("3")) {
					
						System.out.println("You take another look around and leave the Throne Room."
								+ "\n");
						break;
					}
				}
					
			}
			
			else if(input.equals("2") && gameBeat == 1) {
				
				System.out.println("Two guards unlock and open the door for you."
						+ "\nYou enter into the castle dungeon"
						+ "\n"
						+ "\n");
				System.out.println("Beep Boop! Area not implemented!"
						+ "\n"
						+ "\n");
			}
			
			else if(input.equals("2") && gameBeat == 0) {
				
				System.out.println("Two guards stop you from approaching the door."
						+ "\nThey tell you to turn around or be locked inside."
						+ "\nWithout needing to be told twice, you turn around."
						+ "\n");
			}
			
			else if(input.equals("3")) {
				
				
				while(health > 0) {
					
					System.out.println("You enter into the Castle Barracks, to find guards and knights sleeping."
							+ "\nA few acknowledge your entry."
							+ "\n");
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Sleep in a vacant bed");
					System.out.println("2: Leave");
				
					input = in.nextLine();
				
					if(input.equals("1") && gameBeat == 0) {
					
						System.out.println("You walk over and attempt to lay down, but a knight stops you."
								+ "\nThis is not for public use!  Only those in the King's service sleep here."
								+ "\n");
					}
					
					if(input.equals("1") && gameBeat == 1) {
						
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
								+ "\nYou awake feeling refreshed and ready for an adventure!"
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You leave the barracks and return to the Castle's Courtyard."
								+ "\n");
						break;
					}
				}
				
			}
			
			else if(input.equals("4")) {
				
				area = 5;
				System.out.println("You leave castle behind and head back into the city."
						+ "\n");
				break;
			}
			
			else if(input.equals("5")) {
				
				area = 8;
				lastArea = 6;
				break;
			}
			
		}
		
	}
	
	
	public static void mountain() {
		
		area = 7;
		
		// Mobs
		String[] mobs = {"Ogre", "Mountain Giant", "Giant Boar", "Centaur", "Dire Wolf"};
		int mobHealth = 100;
		int mobAD = 30;
		int mobEXP = 100;
		int mobMoney = 1;
		
		String[] dragonGuard = {"Ancient Dragon Guardian"};
		int dragonGuardHP = 150;
		int dragonGuardAD = 70;
		int dragonGuardEXP = 150;
		int dragonGuardMoney = 5;
		
		System.out.println("After a tiring trip, you are in the foothills of the Black Mountains"
				+ "\nThere are powerful monsters all around."
				+ "\nYou can see smoke rising from the highest peak.");
		
		Mountain:
		while(area == 7 && health > 0) {
			
			System.out.println("");
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Continue toward Dragon's Nest");
			System.out.println("2: Search for a monster to fight");
			System.out.println("3: Return to city");
			System.out.println("4: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1") && nestPath == 1) {
				
				area = 9;
				System.out.println("You continue climbing towards the Dragon's Nest."
						+ "\n");
				break;
			}
			
			else if(input.equals("1") && nestPath == 0) {
				
				// Calls enemy interaction
				int mobFightHealth = dragonGuardHP;
				String enemy = dragonGuard[rand.nextInt(dragonGuard.length)];
				System.out.println("\t# An " + enemy + " has appeared! #"
						+ "\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(dragonGuardAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You behead the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t 15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Mountain;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += dragonGuardEXP;
				silver += dragonGuardMoney;
				enemiesDefeated ++;		
					
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + dragonGuardEXP + " XP #");
				System.out.println(" # You have gained " + dragonGuardMoney + " silver pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < healthPotDrop) {
					
					healthPots++;
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You now have " + healthPots + " health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
					System.out.println(" # The " + enemy + " dropped a mana potion! # ");
					System.out.println(" # You now have " + manaPots + " mana potion(s). # ");
				}
				
				if(rand.nextInt(100) < superHealthPotDrop) {
					
					superHealthPots++;
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
				
				area = 9;
				System.out.println("");
				System.out.println("You have defeated the Ancient Dragon Guardian and cleared the path in front of you."
						+ "\nYou continue climbing toward the Dragon's Nest."
						+ "\n");
				break;
			}
			
			else if(input.equals("2")) {
				
				// Calls enemy interaction
				int mobFightHealth = mobHealth;
				String enemy = mobs[rand.nextInt(mobs.length)];
				System.out.println("\t# A " + enemy + " has appeared! #\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(mobAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You execute the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue Mountain;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += mobEXP;
				silver += mobMoney;
				enemiesDefeated ++;
				
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + mobEXP + " XP #");
				System.out.println(" # You have gained " + mobMoney + " silver pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < superHealthPotDrop) {
					
					superHealthPots++;
					System.out.println(" # The " + enemy + " dropped a super health potion! # ");
					System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
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
				
			}
			
			else if(input.equals("3")) {
				
				area = 5;
				System.out.println("You turn away from the mountain range, and go back to the city."
						+ "\n");
				break;
			}
			
			else if(input.equals("4")) {
				
				area = 8;
				lastArea = 7;
				break;
			}
			
		}
		
	}
	
	
	public static void inventory() {
		
		area = 8;
		
		System.out.println("You open up your Bag of Holding to see what it currently contains."
				+ "\n");
		
		while(area == 8 && health > 0) {
			
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
				System.out.println(bread + " Loaves of Bread");
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
			if(flintLock > 0) {
				System.out.println(flintLock + " FlintLock(s)");
			}
			
			System.out.println("\n"
					+ "What would you like to do?"
					+ "\n");
			System.out.println("1: Use or equip item");
			System.out.println("2: Check status");
			System.out.println("3: Continue adventure"
					+ "\n");
			
			String input = in.nextLine();
			
			if(input.equals("1")) {
				
				System.out.println("Type the name of the item you wish to use or equip."
						+ "\n");
				
				input = in.nextLine();
				
				if((input.equals("Apple") || input.equals("apple")) && apple > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Eat");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						apple --;
						health += 5;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You eat an apple, restoring 5 HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						apple --;
						System.out.println("You drop an apple on the ground."
								+ "\n");
					}
					
				}
				
				if((input.equals("Apple Pie") || input.equals("apple pie")) && applePie > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Eat");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						applePie --;
						health += 30;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You eat an apple pie, restoring 20 HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						apple --;
						System.out.println("You drop an apple pie on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Beer") || input.equals("beer")) && beer > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Drink");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
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
						
						else if(drunk >= 6){
							
							System.out.println("Drinking another, you realize you may black out."
									+ "\n");
						}
					}
					
					else if(input.equals("2")) {
						
						beer --;
						System.out.println("You drop the mug of beer on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Bread") || input.equals("bread")) && bread > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Eat");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						bread --;
						health += 10;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You eat some bread, restoring 10 HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						bread --;
						System.out.println("You drop some bread on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Pumpkin") || input.equals("pumpkin")) && pumpkin > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Eat");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						pumpkin --;
						health += 20;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You carve out and eat the pumpkin, restoring 20 HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						pumpkin --;
						System.out.println("You drop a pumpkin on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Scythe") || input.equals("scythe")) && scythe > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 5;
						currentSwordEquip = "Scythe";
						System.out.println("You fasten the scythe to your back, hoping to get it bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a scythe."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Scythe") {
						
						attackDmg -= 5;
						currentSwordEquip = "nothing";
						System.out.println("You remove the scythe from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Scythe") {
						
						scythe --;
						System.out.println("You toss a scythe on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Bronze Sword") || input.equals("bronze sword")) && bronzeSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 5;
						currentSwordEquip = "Bronze Sword";
						System.out.println("You fasten the bronze sword to your belt, hoping to get it bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Bronze Sword") {
						
						attackDmg -= 5;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Bronze Sword") {
						
						bronzeSword --;
						System.out.println("You toss a bronze sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Bronze Shield") || input.equals("bronze shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 5;
						defense += 5;
						currentShieldEquip = "Bronze Shield";
						System.out.println("You fasten the bronze shield to your back, hoping to not get killed."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Bronze Shield") {
						
						maxHealth -= 5;
						defense -= 5;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Bronze Shield") {
						
						bronzeShield --;
						System.out.println("You toss a bronze shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}

				}
				
				else if((input.equals("Iron Sword") || input.equals("iron sword")) && ironSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 10;
						currentSwordEquip = "Iron Sword";
						System.out.println("You fasten the iron sword to your belt, hoping to get it bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Iron Sword") {
						
						attackDmg -= 10;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Iron Sword") {
						
						ironSword --;
						System.out.println("You toss an iron sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Iron Shield") || input.equals("iron shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 10;
						defense += 10;
						currentShieldEquip = "Iron Shield";
						System.out.println("You fasten the iron shield to your back, hoping to not get killed."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Iron Shield") {
						
						maxHealth -= 10;
						defense -= 10;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Iron Shield") {
						
						ironShield --;
						System.out.println("You toss an iron shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Steel Sword") || input.equals("steel sword")) && steelSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 20;
						currentSwordEquip = "Steel Sword";
						System.out.println("You fasten the steel sword to your belt, hoping to get it bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Steel Sword") {
						
						attackDmg -= 20;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Steel Sword") {
						
						steelSword --;
						System.out.println("You toss a steel sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Steel Shield") || input.equals("steel shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 20;
						defense += 15;
						currentShieldEquip = "Steel Shield";
						System.out.println("You fasten the steel shield to your back, hoping to not get killed."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Steel Shield") {
						
						maxHealth -= 20;
						defense -= 15;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Steel Shield") {
						
						steelShield --;
						System.out.println("You toss a steel shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
				}
				
				else if((input.equals("Mithril Sword") || input.equals("mithril sword")) && mithrilSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 25;
						currentSwordEquip = "Mithril Sword";
						System.out.println("You fasten the mithril sword to your belt, knowing it will get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Mithril Sword") {
						
						attackDmg -= 25;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Mithril Sword") {
						
						mithrilSword --;
						System.out.println("You toss a mithril sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Mithril Shield") || input.equals("mithril shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 25;
						defense += 20;
						currentShieldEquip = "Mithril Shield";
						System.out.println("You fasten the mithril shield to your back, hoping to not get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Mithril Shield") {
						
						maxHealth -= 25;
						defense -= 20;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Mithril Shield") {
						
						mithrilShield --;
						System.out.println("You toss a mithril shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Guild Sword") || input.equals("guild sword")) && guildSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 30;
						currentSwordEquip = "Guild Sword";
						System.out.println("You fasten the guild sword to your belt, knowing it will get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Guild Sword") {
						
						attackDmg -= 30;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Guild Sword") {
						
						guildSword --;
						System.out.println("You toss a guild sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Guild Shield") || input.equals("guild shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 30;
						defense += 25;
						currentShieldEquip = "Guild Shield";
						System.out.println("You fasten the guild shield to your back, hoping to not get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Guild Shield") {
						
						maxHealth -= 30;
						defense -= 25;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Guild Shield") {
						
						guildShield --;
						System.out.println("You toss a guild shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Adamant Sword") || input.equals("adamant sword")) && adamantSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 35;
						currentSwordEquip = "Adamant Sword";
						System.out.println("You fasten the adamant sword to your belt, knowing it will get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Adamant Sword") {
						
						attackDmg -= 35;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your belt."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Adamant Sword") {
						
						adamantSword --;
						System.out.println("You toss an adamant sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Adamant Shield") || input.equals("adamant shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 35;
						defense += 30;
						currentShieldEquip = "Adamant Shield";
						System.out.println("You fasten the adamant shield to your back, hoping to not get bloody."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Adamant Shield") {
						
						maxHealth -= 35;
						defense -= 30;
						currentShieldEquip = "nothing";
						System.out.println("You remove the shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Adamant Shield") {
						
						adamantShield --;
						System.out.println("You toss an adamant shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Dragon Sword") || input.equals("dragon sword")) && dragonSword > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 50;
						currentSwordEquip = "Dragon Sword";
						System.out.println("You fasten the dragon sword to your back, you can feel heat radiating."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding a sword."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "Dragon Sword") {
						
						attackDmg -= 50;
						currentSwordEquip = "nothing";
						System.out.println("You remove the sword from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "Dragon Sword") {
						
						dragonSword --;
						System.out.println("You toss a dragon sword on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else if(input.equals("Dragon Shield") || input.equals("dragon shield")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentShieldEquip == "nothing") {
						
						maxHealth += 50;
						defense += 40;
						currentShieldEquip = "Dragon Shield";
						System.out.println("You fasten the dragon shield to your back, knowing it will protect you."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wearing a shield."
								+ "\n");
					}
					
					else if(input.equals("2") && currentShieldEquip == "Dragon Shield") {
						
						maxHealth -= 50;
						defense -= 40;
						currentShieldEquip = "nothing";
						System.out.println("You remove the large shield from your back."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentShieldEquip != "Dragon Shield") {
						
						dragonShield --;
						System.out.println("You toss an dragon shield on the ground."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentShieldEquip + "."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Health Potion") || input.equals("health potion")) && healthPots > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Drink");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						healthPots --;
						health += healthPotHeal;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You drink a health potion, restoring " + healthPotHeal + " HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						healthPots --;
						System.out.println("You throw the health potion on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Super Health Potion") || input.equals("super health potion")) && superHealthPots > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Drink");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						superHealthPots --;
						health += superHealthPotHeal;
						
						if(health > maxHealth) {
							health = maxHealth;
						}
						
						System.out.println("You drink a super health potion, restoring " + superHealthPotHeal + " HP."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						superHealthPots --;
						System.out.println("You throw the super health potion on the ground."
								+ "\n");
					}
					
				}
				
				else if((input.equals("Mana Potion") || input.equals("mana potion")) && manaPots > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Drink");
					System.out.println("2: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1")) {
						
						manaPots --;
						mana += manaPotRestore;
						System.out.println("You drink a mana potion, restoring " + manaPotRestore + " Mana."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						manaPots --;
						System.out.println("You throw the mana potion on the ground."
								+ "\n");
					}
					
				}
				
				else if(input.equals("FlintLock") || input.equals("flintlock")) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Equip");
					System.out.println("2: Disequip");
					System.out.println("3: Discard");
					
					input = in.nextLine();
					
					if(input.equals("1") && currentSwordEquip == "nothing") {
						
						attackDmg += 100;
						currentSwordEquip = "FlintLock";
						System.out.println("You strap the FlintLock to your thigh, ready to fire."
								+ "\n");
					}
					
					else if(input.equals("1")) {
						
						System.out.println("You are already wielding an item."
								+ "\n");
					}
					
					else if(input.equals("2") && currentSwordEquip == "FlintLock") {
						
						attackDmg -= 100;
						currentSwordEquip = "nothing";
						System.out.println("You remove the FlintLock from your leg."
								+ "\n");
					}
					
					else if(input.equals("2")) {
						
						System.out.println("You cannot disequip something you do not have equipped."
								+ "\n");
					}
					
					else if(input.equals("3") && currentSwordEquip != "FlintLock") {
						
						flintLock --;
						System.out.println("You discard the powerful weapon."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						System.out.println("You cannot drop the item you have equipped."
								+ "\n");
					}
					
					else {
						
						System.out.println("You are currently wielding " + currentSwordEquip + "."
								+ "\n");
					}
					
				}
				
				else {
					
					System.out.println("You do not have this item or it was spelt incorrectly."
							+ "\n");
				}
				
			}
			
			else if(input.equals("2")) {
				
				System.out.println(" + You are level " + level + " and you have " + experience + " XP +");
				System.out.println(" + You have " + health + "/" + maxHealth + " health and " + mana + " mana +");
				System.out.println(" + You have " + attackDmg + " attack damage and " + defense + " defense +");
				System.out.println(" $ You have " + gold + " gold pieces, " + silver + " silver pieces, and " + copper + " copper pieces $"
						+ "\n");
				System.out.println("Currently wielding " + currentSwordEquip + "."
						+ "\n\t\t   " + currentShieldEquip + "."
						+ "\n");
						
			}
			
			else if(input.equals("3")) {
				
				System.out.println("You close the bag and continue your adventure."
						+ "\n");
				
				switch(lastArea) {
				
				case 1:
					area = 1;
					break;
				case 2:
					area = 2;
					break;
				case 3:
					area = 3;
					break;
				case 4:
					area = 4;
					break;
				case 5:
					area = 5;
					break;
				case 6:
					area = 6;
					break;
				case 7:
					area = 7;
					break;
				case 8:
					area = 8;
					break;
				case 9:
					area = 9;
					break;
				}
				
			}
			
		}
		
	}
	
	public static void dragonNest() {
		
		area = 9;
		
		String[] mobs = {"Hatchling Dragon", "Young Drake", "Giant Eagle", "Dragon Worshipper", "Strong Fire Elemental"};
		int mobHealth = 150;
		int mobAD = 60;
		int mobEXP = 150;
		int mobMoney = 5;
		
		String[] dragon = {"Ancient Dragon"};
		int dragonHP = 400;
		int dragonAD = 90;
		int dragonEXP = 400;
		int dragonMoney = 1;
		
		String[] nestMobs = {"Fire Drake", "Young Dragon", "Dragonfire Elemental", "Master Dragon Worshipper"};
		int nestMobHP = 250;
		int nestMobAD = 70;
		int nestMobEXP = 250;
		int nestMobMoney = 25;
		
		System.out.println("Climbing to a crest on the mountain, you peek over and see what lies ahead."
				+ "\nThere appears to be a stairway leading up to the peak of the now Scorched Mountain."
				+ "\nThough it is thouroughly blocked by many dangerous creatures.");
		
		DragonAerie:
		while(area == 9 && health > 0) {
			
			System.out.println("");
			System.out.println("What would you like to do?"
					+ "\n");
			System.out.println("1: Go up stairway");
			System.out.println("2: Fight a monster");
			System.out.println("3: Retreat down mountain");
			System.out.println("4: Inventory");
			
			String input = in.nextLine();
			
			if(input.equals("1") && nestKills >= 20) {
				
				System.out.println("You finally thin out the monsters enough to climb the stairway."
						+ "\n");
				System.out.println("As you approach the top, you feel the mountain radiating with heat."
						+ "\nIt is mountainside is as black as the void, with no living creature in sight."
						+ "\nThe stairs suddenly stop at the peak of the mountain."
						+ "\nOn a spacious plateau is the largest beast you have ever seen."
						+ "\n");
				
				Nest:
				while(health > 0) {
					
					System.out.println("What would you like to do?"
							+ "\n");
					System.out.println("1: Fight the Dragon");
					System.out.println("2: Search the Dragon's Nest");
					System.out.println("3: Fight a monster");
					System.out.println("4: Retreat down mountain");
					System.out.println("5: Inventory");
					
					input = in.nextLine();
					
					if(input.equals("1") && gameBeat == 1) {
						
						System.out.println("You look at the body of the dead Ancient Dragon."
								+ "\nSmiling, you know you are a hero."
								+ "\n");
					}
					
					else if (input.equals("1") && gameBeat == 0) {
						
						// Calls enemy interaction
						int mobFightHealth = dragonHP;
						String enemy = dragon[rand.nextInt(dragon.length)];
						System.out.println("\t# A " + enemy + " has appeared! #\n");
						
						while(mobFightHealth > 0) {
							
							System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
							System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
							System.out.println("\n\tWhat would you like to do?");
							System.out.println("\t1. Attack");
							System.out.println("\t2. Cast Spell");
							System.out.println("\t3. Use Item");
							System.out.println("\t4. Run");
							
							String action = in.nextLine();
						
							if(action.equals("1") && mobFightHealth >= 2) {
								
								if(rand.nextInt(100) > 20) {
									
									int damageDealt = rand.nextInt(attackDmg);
									int damageTaken = rand.nextInt(dragonAD) - defense;
									
									mobFightHealth -= damageDealt;
									
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
									
									mobFightHealth -= damageDealt;
									health -= damageTaken;
									
									System.out.println("The Ancient Dragon rears back and opens it's gaping maw."
											+ "\nThe air heats up instantly as it breathes fire at you."
											+ "\n");
									System.out.println("\t> You recieve " + damageTaken + " points of damage!");
									System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
									System.out.println("");
								}
								
								if(health < 1) {
									
									System.out.println("\t> You collapse on the floor, taking your last breath.");
									break;
								}
								
							}
							
							else if(action.equals("1") && mobFightHealth < 2) {
								
								int execute = attackDmg;
								mobFightHealth -= execute;
								
								System.out.println("\t> You cut off the " + enemy + "'s head!");
								System.out.println("");
							}
							
							else if(action.equals("2")) {
								
								System.out.println("\t1. Cast Fireball\t10 Mana");
								System.out.println("\t2. Cast Magic Missile\t15 Mana");
								if(spellState > 0) {
									System.out.println("\t3. Cast Absorb\t\t25 Mana");
								}
								
								action = in.nextLine();
								
								if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
									
									mobFightHealth -= fireBallDamage;
									mana -= fireBallMana;
									
									System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
									System.out.println("");
								}
								
								else if(action.equals("1") && mobFightHealth < 2) {
									
									int execute = attackDmg;
									mobFightHealth -= execute;
									
									System.out.println("\t> You execute the " + enemy + "!");
									System.out.println("");
								}
								
								else if(action.equals("1") && mana < 10) {
									
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
									
									mobFightHealth -= magicMissileDamage;
									mana -= magicMissileMana;
								
									System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
									System.out.println("");
								}
							
								else if(action.equals("2") && mobFightHealth < 2) {
								
									int execute = attackDmg;
									mobFightHealth -= execute;
								
									System.out.println("\tYou execute the " + enemy + "!");
									System.out.println("");
								}
							
								else if(action.equals("2") && mana < 15) {
								
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
									
									mobFightHealth -= absorbDamage;
									mana -= absorbMana;
									health += 10;
									
									if(health > maxHealth) {
										health = maxHealth;
									}
								
									System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
									System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
									System.out.println("");
								}
							
								else if(action.equals("3") && mobFightHealth < 2) {
								
									int execute = attackDmg;
									mobFightHealth -= execute;
								
									System.out.println("\tYou execute the " + enemy + "!");
									System.out.println("");
								}
							
								else if(action.equals("3") && mana < 25 || spellState == 0) {
								
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
									
									System.out.println("");
									System.out.println("Invalid Command!");
									System.out.println("");
								}
							}
							
							else if(action.equals("3")) {
								
								System.out.println("\tSelect an item to use");
								System.out.println("\t1. Health Potion");
								System.out.println("\t2. Super Health Potion");
								System.out.println("\t3. Mana Potion");
								
								action = in.nextLine();
								
								if(action.equals("1")) {
									
									if(healthPots > 0) {
									
										health += healthPotHeal;
										healthPots --;
										
										if(health > maxHealth) {
											health = maxHealth;
										}
										
										System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
														+ "\n\t> You now have " + health + " HP."
														+ "\n\t> You have " + healthPots + " health potions left.\n");
										
									}
								
									else {
									
										System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
										System.out.println("");
										
									}
								}
						
								else if(action.equals("2")) {
								
									if(superHealthPots > 0) {
									
										health += superHealthPotHeal;
										superHealthPots --;
										
										if(health > maxHealth) {
											health = maxHealth;
										}
										
										System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
														+ "\n\t> You now have " + health + " HP."
														+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
										
									}
								
									else {
									
										System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
										System.out.println("");
										
									}
								}
							
								else if(action.equals("3")) {
								
									if(manaPots > 0) {
									
										mana += manaPotRestore;
										manaPots --;
										System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
												+ "\n\t> You now have " + mana + " Mana."
												+ "\n\t> You have " + manaPots + " mana potions left.\n");
									}
								
									else {
									
										System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
										System.out.println("");
										
									}
								}
								
								else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
									
									System.out.println("");
									System.out.println("Invalid Command!");
									System.out.println("");
								}
							}
							
							else if(action.equals("4")) {
								
								System.out.println("\tYou run away from the " + enemy + "!"
										+ "\n");
								continue Nest;
							}
							
							else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
								
								System.out.println("");
								System.out.println("\tInvalid command!");
								System.out.println("");
							}
						}
						
						
						// Defeat
						if(health < 1) {
							
							System.out.println("");
							System.out.println("Your soul leaves your body, bound for the unknown.");
							break;
						}
						
						// End of fight
						experience += dragonEXP;
						gold += dragonMoney;
						enemiesDefeated ++;
						
						System.out.println("------------------------------------------------------");
						System.out.println(" # " + enemy + " was defeated! #");
						System.out.println(" # You have gained " + dragonEXP + " XP #");
						System.out.println(" # You have gained " + dragonMoney + " gold piece #");
						System.out.println(" # You have " + health + " HP left #");
						
						leveling();
						
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
						
						gameBeat = 1;
					}
					
					else if(input.equals("2") && gameBeat == 0) {
						
						System.out.println("You cannot approach the Dragon's Nest with it still alive."
								+ "\n");
					}
					
					else if(input.equals("2") && gameBeat == 1) {
						
						gold += 5;
						silver += 50;
						copper += 50;
						superHealthPots += 5;
						manaPots += 10;
						
						System.out.println("You look into the Nest of the Dragon and see many fallen beings."
								+ "\nAmong the fallen, you find many useful items and rewards."
								+ "\nThere is a skeleton here, not burnt like the others."
								+ "\nIt appeared to have some sort of unique weapon, though it is long gone."
								+ "\n"
								+ "\nYou finish searching and go on your way."
								+ "\n");
						
						System.out.println("You have recieved: 5 gold, 50 silver, 50 copper."
								+ "\n\t5 super health potions, and 10 mana potions."
								+ "\n");
					}
					
					else if(input.equals("3")) {
						
						// Calls enemy interaction
						int mobFightHealth = nestMobHP;
						String enemy = nestMobs[rand.nextInt(nestMobs.length)];
						System.out.println("\t# A " + enemy + " has appeared! #\n");
						
						while(mobFightHealth > 0) {
							
							System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
							System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
							System.out.println("\n\tWhat would you like to do?");
							System.out.println("\t1. Attack");
							System.out.println("\t2. Cast Spell");
							System.out.println("\t3. Use Item");
							System.out.println("\t4. Run");
							
							String action = in.nextLine();
						
							if(action.equals("1") && mobFightHealth >= 2) {
								
								int damageDealt = rand.nextInt(attackDmg);
								int damageTaken = rand.nextInt(nestMobAD) - defense;
								
								mobFightHealth -= damageDealt;
								
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
								
								if(health < 1) {
									
									System.out.println("\t> You collapse on the floor, taking your last breath.");
									break;
								}
								
							}
							
							else if(action.equals("1") && mobFightHealth < 2) {
								
								int execute = attackDmg;
								mobFightHealth -= execute;
								
								System.out.println("\t> You execute the " + enemy + "!");
								System.out.println("");
							}
							
							else if(action.equals("2")) {
								
								System.out.println("\t1. Cast Fireball\t10 Mana");
								System.out.println("\t2. Cast Magic Missile\t15 Mana");
								if(spellState > 0) {
									System.out.println("\t3. Cast Absorb\t\t25 Mana");
								}
								
								action = in.nextLine();
								
								if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
									
									mobFightHealth -= fireBallDamage;
									mana -= fireBallMana;
									
									System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
									System.out.println("");
								}
								
								else if(action.equals("1") && mobFightHealth < 2) {
									
									int execute = attackDmg;
									mobFightHealth -= execute;
									
									System.out.println("\t> You execute the " + enemy + "!");
									System.out.println("");
								}
								
								else if(action.equals("1") && mana < 10) {
									
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
									
									mobFightHealth -= magicMissileDamage;
									mana -= magicMissileMana;
								
									System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
									System.out.println("");
								}
							
								else if(action.equals("2") && mobFightHealth < 2) {
								
									int execute = attackDmg;
									mobFightHealth -= execute;
								
									System.out.println("\tYou execute the " + enemy + "!");
									System.out.println("");
								}
							
								else if(action.equals("2") && mana < 15) {
								
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
									
									mobFightHealth -= absorbDamage;
									mana -= absorbMana;
									health += 10;
									
									if(health > maxHealth) {
										health = maxHealth;
									}
								
									System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
									System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
									System.out.println("");
								}
							
								else if(action.equals("3") && mobFightHealth < 2) {
								
									int execute = attackDmg;
									mobFightHealth -= execute;
								
									System.out.println("\tYou execute the " + enemy + "!");
									System.out.println("");
								}
							
								else if(action.equals("3") && mana < 25 || spellState == 0) {
								
									System.out.println("\tYou do not have enough mana for this spell!");
									System.out.println("");
								}
								
								else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
									
									System.out.println("");
									System.out.println("Invalid Command!");
									System.out.println("");
								}
							}
							
							else if(action.equals("3")) {
								
								System.out.println("\tSelect an item to use");
								System.out.println("\t1. Health Potion");
								System.out.println("\t2. Super Health Potion");
								System.out.println("\t3. Mana Potion");
								
								action = in.nextLine();
								
								if(action.equals("1")) {
									
									if(healthPots > 0) {
									
										health += healthPotHeal;
										healthPots --;
										
										if(health > maxHealth) {
											health = maxHealth;
										}
										
										System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
														+ "\n\t> You now have " + health + " HP."
														+ "\n\t> You have " + healthPots + " health potions left.\n");
										
									}
								
									else {
									
										System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
										System.out.println("");
										
									}
								}
						
								else if(action.equals("2")) {
								
									if(superHealthPots > 0) {
									
										health += superHealthPotHeal;
										superHealthPots --;
										
										if(health > maxHealth) {
											health = maxHealth;
										}
										
										System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
														+ "\n\t> You now have " + health + " HP."
														+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
										
									}
								
									else {
									
										System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
										System.out.println("");
										
									}
								}
							
								else if(action.equals("3")) {
								
									if(manaPots > 0) {
									
										mana += manaPotRestore;
										manaPots --;
										System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
												+ "\n\t> You now have " + mana + " Mana."
												+ "\n\t> You have " + manaPots + " mana potions left.\n");
									}
								
									else {
									
										System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
										System.out.println("");
										
									}
								}
								
								else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
									
									System.out.println("");
									System.out.println("Invalid Command!");
									System.out.println("");
								}
							}
							
							else if(action.equals("4")) {
								
								System.out.println("\tYou run away from the " + enemy + "!"
										+ "\n");
								continue Nest;
							}
							
							else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
								
								System.out.println("");
								System.out.println("\tInvalid command!");
								System.out.println("");
							}
						}
						
						
						// Defeat
						if(health < 1) {
							
							System.out.println("");
							System.out.println("Your soul leaves your body, bound for the unknown.");
							break;
						}
						
						// End of fight
						experience += nestMobEXP;
						silver += nestMobMoney;
						enemiesDefeated ++;
						nestKills ++;
						
						System.out.println("------------------------------------------------------");
						System.out.println(" # " + enemy + " was defeated! #");
						System.out.println(" # You have gained " + nestMobEXP + " XP #");
						System.out.println(" # You have gained " + nestMobMoney + " silver pieces #");
						System.out.println(" # You have " + health + " HP left #");
						
						leveling();
						
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
						if(rand.nextInt(100) < superHealthPotDrop) {
							
							superHealthPots++;
							System.out.println(" # The " + enemy + " dropped a super health potion! # ");
							System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
						}
						
						if(rand.nextInt(100) < manaPotDrop) {
							
							manaPots++;
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
						
					}
					
					else if(input.equals("4")) {
						
						System.out.println("You head back to the Aerie."
								+ "\n");
						break;
					}
					
					else if(input.equals("5")) {
						
						area = 8;
						lastArea = 9;
						break;
					}
					
				}
				
			}
			
			else if(input.equals("1") && nestKills < 19 && nestKills > 10) {
				
				System.out.println("You attempt to approach the stairway,"
						+ "\nYou make some progress, but are forced back again.");
			}
			
			else if(input.equals("1") && nestKills < 10) {
				
				System.out.println("You attempt to approach the stairway,"
						+ "\nBut you are forced back because there are too many monsters.");
			}
			
			else if(input.equals("2")) {
				
				// Calls enemy interaction
				int mobFightHealth = mobHealth;
				String enemy = mobs[rand.nextInt(mobs.length)];
				System.out.println("\t# A " + enemy + " has appeared! #\n");
				
				while(mobFightHealth > 0) {
					
					System.out.println("\tYour HP: " + health + "/" + maxHealth + "\tMana: " + mana);
					System.out.println("\t"  + enemy + "'s HP: " + mobFightHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Cast Spell");
					System.out.println("\t3. Use Item");
					System.out.println("\t4. Run");
					
					String action = in.nextLine();
				
					if(action.equals("1") && mobFightHealth >= 2) {
						
						int damageDealt = rand.nextInt(attackDmg);
						int damageTaken = rand.nextInt(mobAD) - defense;
						
						mobFightHealth -= damageDealt;
						
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
						
						if(health < 1) {
							
							System.out.println("\t> You collapse on the floor, taking your last breath.");
							break;
						}
						
					}
					
					else if(action.equals("1") && mobFightHealth < 2) {
						
						int execute = attackDmg;
						mobFightHealth -= execute;
						
						System.out.println("\t> You execute the " + enemy + "!");
						System.out.println("");
					}
					
					else if(action.equals("2")) {
						
						System.out.println("\t1. Cast Fireball\t10 Mana");
						System.out.println("\t2. Cast Magic Missile\t15 Mana");
						if(spellState > 0) {
							System.out.println("\t3. Cast Absorb\t\t25 Mana");
						}
						
						action = in.nextLine();
						
						if(action.equals("1") && mobFightHealth >= 2 && mana >= 10) {
							
							mobFightHealth -= fireBallDamage;
							mana -= fireBallMana;
							
							System.out.println("\tYou shoot a fireball at the " + enemy + " dealing " + fireBallDamage + " damage");
							System.out.println("");
						}
						
						else if(action.equals("1") && mobFightHealth < 2) {
							
							int execute = attackDmg;
							mobFightHealth -= execute;
							
							System.out.println("\t> You execute the " + enemy + "!");
							System.out.println("");
						}
						
						else if(action.equals("1") && mana < 10) {
							
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("2") && mobFightHealth >= 2 && mana >= 15) {
							
							mobFightHealth -= magicMissileDamage;
							mana -= magicMissileMana;
						
							System.out.println("\tYou shoot a magic missile at the " + enemy + " dealing " + magicMissileDamage + " damage");
							System.out.println("");
						}
					
						else if(action.equals("2") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("2") && mana < 15) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						if(action.equals("3") && mobFightHealth >= 2 && mana >= 25 && spellState > 0) {
							
							mobFightHealth -= absorbDamage;
							mana -= absorbMana;
							health += 10;
							
							if(health > maxHealth) {
								health = maxHealth;
							}
						
							System.out.println("\tYou cast absorb at the " + enemy + " dealing " + absorbDamage + " damage");
							System.out.println("\tYou absorb " + absorbDamage + " health from the " + enemy);
							System.out.println("");
						}
					
						else if(action.equals("3") && mobFightHealth < 2) {
						
							int execute = attackDmg;
							mobFightHealth -= execute;
						
							System.out.println("\tYou execute the " + enemy + "!");
							System.out.println("");
						}
					
						else if(action.equals("3") && mana < 25 || spellState == 0) {
						
							System.out.println("\tYou do not have enough mana for this spell!");
							System.out.println("");
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("3")) {
						
						System.out.println("\tSelect an item to use");
						System.out.println("\t1. Health Potion");
						System.out.println("\t2. Super Health Potion");
						System.out.println("\t3. Mana Potion");
						
						action = in.nextLine();
						
						if(action.equals("1")) {
							
							if(healthPots > 0) {
							
								health += healthPotHeal;
								healthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a health potion, healing for " + healthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + healthPots + " health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
								System.out.println("");
								
							}
						}
				
						else if(action.equals("2")) {
						
							if(superHealthPots > 0) {
							
								health += superHealthPotHeal;
								superHealthPots --;
								
								if(health > maxHealth) {
									health = maxHealth;
								}
								
								System.out.println("\t> You drink a super health potion, healing for " + superHealthPotHeal + " HP."
												+ "\n\t> You now have " + health + " HP."
												+ "\n\t> You have " + superHealthPots + " super health potions left.\n");
								
							}
						
							else {
							
								System.out.println("\t> You have no super health potions left! Defeat a boss for the chance to get one!");
								System.out.println("");
								
							}
						}
					
						else if(action.equals("3")) {
						
							if(manaPots > 0) {
							
								mana += manaPotRestore;
								manaPots --;
								System.out.println("\t> You drink a mana potion, restoring " + manaPotRestore + " Mana."
										+ "\n\t> You now have " + mana + " Mana."
										+ "\n\t> You have " + manaPots + " mana potions left.\n");
							}
						
							else {
							
								System.out.println("\t> You have no mana potions left! Defeat any monster for the chance to get one!");
								System.out.println("");
								
							}
						}
						
						else if(!action.equals("1") && !action.equals("2") && !action.equals("3")) {
							
							System.out.println("");
							System.out.println("Invalid Command!");
							System.out.println("");
						}
					}
					
					else if(action.equals("4")) {
						
						System.out.println("\tYou run away from the " + enemy + "!"
								+ "\n");
						continue DragonAerie;
					}
					
					else if(!action.equals("1") && !action.equals("2") && !action.equals("3") && !action.equals("4")) {
						
						System.out.println("");
						System.out.println("\tInvalid command!");
						System.out.println("");
					}
				}
				
				
				// Defeat
				if(health < 1) {
					
					System.out.println("");
					System.out.println("Your soul leaves your body, bound for the unknown.");
					break;
				}
				
				// End of fight
				experience += mobEXP;
				silver += mobMoney;
				enemiesDefeated ++;
				nestKills ++;
				
				System.out.println("------------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have gained " + mobEXP + " XP #");
				System.out.println(" # You have gained " + mobMoney + " silver pieces #");
				System.out.println(" # You have " + health + " HP left #");
				
				leveling();
				
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
				if(rand.nextInt(100) < superHealthPotDrop) {
					
					superHealthPots++;
					System.out.println(" # The " + enemy + " dropped a super health potion! # ");
					System.out.println(" # You now have " + superHealthPots + " super health potion(s). # ");
				}
				
				if(rand.nextInt(100) < manaPotDrop) {
					
					manaPots++;
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
				
			}
			
			else if(input.equals("3")) {
				
				area = 7;
				System.out.println("You retreat back down the mountain."
						+ "\n");
				break;
			}
			
			else if(input.equals("4")) {
				
				area = 8;
				lastArea = 9;
				break;
			}
			
		}
		
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