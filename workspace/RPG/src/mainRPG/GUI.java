package mainRPG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import audio.MusicPlayer;

public class GUI implements Runnable {

	public JLabel locationLabel;
    public JTextArea playerInfo;
    public JTextArea questDisplay;
    public JTextArea equipmentDisplay;
    public JTextArea mainArea;
    public JTextField commandField;

	public final int WIDTH = 800;
	public final int HEIGHT = 550;
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
    
    MainRPG main;
    int area;
    int health;
    int maxHealth;
    int mana;
    int ad;
    int def;
    int lvl;
    int exp;
    String sword;
    String shield;
    String location;

    public JFrame frame = new JFrame();

    /**
     * Starts the game.
     */
    public static void main(String[] args) {
        new GUI().run();
        ThreadPool pool = new ThreadPool(3);
		MusicPlayer player = new MusicPlayer("Opening", "Town", "Sanctuary", "The Empire");
		pool.runTask(player);
    }

    /**
     * Runs the game.
     */
    public void run() {
        main = new MainRPG(this);
        area = MainRPG.getArea();
        health = MainRPG.getHealth();
        maxHealth = MainRPG.getMaxHealth();
        mana = MainRPG.getMana();
        ad = MainRPG.getattackDamage();
        def = MainRPG.getDefense();
        lvl = MainRPG.getLevel();
        exp = MainRPG.getEXP();
        sword = MainRPG.getSword();
        shield = MainRPG.getShield();

        createGui();
        main.setup();
    }

    public void giveResponseToLastSub(ActionEvent e) {

    }

    /**
     * Creates the GUI.
     */
    private void createGui() {

        createComponents();
        arrangeComponents();
        redirectSystemStreams();
        frame.setPreferredSize(gameSize);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void appendMainArea(String appenedText) {
    	mainArea.append(appenedText.concat("\n"));
    }

    private void createComponents() {

        frame.setBackground(Color.WHITE);
        locationLabel = new JLabel(" Your location:");
        playerInfo = new JTextArea();
        equipmentDisplay = new JTextArea();
        questDisplay = new JTextArea();
        mainArea = new JTextArea(24, 30);
        commandField = new JTextField();

        commandField.addActionListener(new AbstractAction()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                GUI.this.commandField.setText("");
                mainArea.setCaretPosition(mainArea.getText().length());
            }
        });
    }
       

    /**
     * Lays out all components.
     */
    private void arrangeComponents() {

    	frame.setLayout(new BorderLayout());

        // Location area at top
    	frame.add(locationLabel, BorderLayout.NORTH);

        // Main area
        mainArea.setLineWrap(true);
        mainArea.setEditable(false);

        frame.add(new JScrollPane(mainArea), BorderLayout.CENTER);
        mainArea.setCaretPosition(mainArea.getText().length());

        // Right side, composed of three panels
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(new JLabel(" Player Info"), BorderLayout.NORTH);
        panel2.add(new JScrollPane(playerInfo), BorderLayout.CENTER);
        playerInfo.setEditable(false);
        playerInfo.setText("HP " + health + "/" + maxHealth + "\nMana " + mana + "\nAD   " + ad + "\nDEF " + def + "\nLvl    " + lvl + "\nExp  " + exp);
        panel2.setBorder(new LineBorder(Color.BLACK));
        panel2.setBackground(new Color(0xFFCCCC));
        panel1.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(new JLabel(" Currently Equipped"), BorderLayout.NORTH);
        panel3.add(new JScrollPane(equipmentDisplay), BorderLayout.CENTER);
        equipmentDisplay.setEditable(false);
        equipmentDisplay.setText(sword + "\n" + shield);
        panel3.setBorder(new LineBorder(Color.BLACK));
        panel3.setBackground(new Color(0xCCCCFF));
        panel1.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.add(new JLabel(" Quests etc?"), BorderLayout.NORTH);
        panel4.add(new JScrollPane(questDisplay), BorderLayout.CENTER);
        questDisplay.setEditable(false);
        questDisplay.setText("Main Quest\nSide Quests");
        panel4.setBorder(new LineBorder(Color.BLACK));
        panel4.setBackground(new Color(0xCCFFCC));

        panel1.add(panel4);
        frame.add(panel1, BorderLayout.EAST);

        // Input panel
        JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panel6.add(new JLabel(" Input: "), BorderLayout.WEST);
        panel6.add(commandField, BorderLayout.CENTER);
        panel6.setFocusable(true);
        frame.add(panel6, BorderLayout.SOUTH);
    }


    private void updateTextArea(final String text) {
    	  SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	      mainArea.append(text);
    	    }
    	  });
    	}

    	private void redirectSystemStreams() {
    	  OutputStream out = new OutputStream() {

    	    @Override
    	    public void write(int b) throws IOException {
    	      updateTextArea(String.valueOf((char) b));
    	    }

    	    @Override
    	    public void write(byte[] b, int off, int len) throws IOException {
    	      updateTextArea(new String(b, off, len));
    	    }

    	    @Override
    	    public void write(byte[] b) throws IOException {
    	      write(b, 0, b.length);
    	    }
    	  };

    	  System.setOut(new PrintStream(out, true));
    	  System.setErr(new PrintStream(out, true));
    	}

}