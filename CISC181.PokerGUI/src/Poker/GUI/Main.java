package Poker.GUI;

// Drew Madden, John Oribioye, Arnold Rivers, Aylin Dindi, Michael Matimu

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import pokerAction.Action;
import pokerBase.Card;
import pokerBase.Player;
import pokerBase.Rule;
import pokerBase.Table;
import pokerEnums.eGame;
import pokerPlay.Client;

public class Main extends JFrame implements Client {

	/** The table. */
	private Table tbl;

	private Map<String, Player> players;
private  List<Player> rankedplayers;
	
	/** The GridBagConstraints. */
	private GridBagConstraints gc;

	/** The board panel. */
	private BoardPanel boardPanel;
	
	/** The control panel. */
	private ControlPanel controlPanel;

	/** The player panels. */
	private Map<String, PlayerPanel> playerPanels;
	
	private Rule rle;
	private eGame RuleType;
	private PlayGame pGame;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Main window = new Main();
	}


	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		tbl = new Table();
		pGame= new PlayGame(eGame.DeucesWild);
		RuleType=eGame.FiveStud;
		rle = new Rule(eGame.FiveStud);		
		
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu which_game = new JMenu("Poker Games");
		menu.add(which_game);
		JRadioButtonMenuItem cards_5_stud = new JRadioButtonMenuItem("5 Card Stud");
		cards_5_stud.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				
				RuleType = eGame.FiveStud;
				rle = new Rule(eGame.FiveStud);
				
				pGame = new PlayGame(eGame.FiveStud);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();
				}
		});
		
		which_game.add(cards_5_stud);
		
		
		
		JRadioButtonMenuItem cards_5_2_jokers = new JRadioButtonMenuItem("5 Card with 2 Jokers");
		cards_5_2_jokers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.FiveStudTwoJoker;
				rle = new Rule(eGame.FiveStudTwoJoker);
				
				pGame = new PlayGame(eGame.FiveStudTwoJoker);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();
			}
		});
		
		which_game.add(cards_5_2_jokers);

		
		JRadioButtonMenuItem DeucesWild = new JRadioButtonMenuItem("5 card wild poker (Deuces Wild)");
		DeucesWild.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.DeucesWild;
				rle = new Rule(eGame.DeucesWild);
				
				pGame = new PlayGame(eGame.DeucesWild);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();	
			}}
		);
		which_game.add(DeucesWild);
		
		
		
		JRadioButtonMenuItem five_card_draw = new JRadioButtonMenuItem("5 Card Draw");
		five_card_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.FiveDraw;
				rle = new Rule(eGame.FiveDraw);
				pGame = new PlayGame(eGame.FiveDraw);
			
				pGame.run();
			}
		});
		
		which_game.add(five_card_draw);
		
		
		
		JRadioButtonMenuItem draw_7_cards = new JRadioButtonMenuItem("7 Cards Draw");
		draw_7_cards.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.SevenDraw;
				rle = new Rule(eGame.SevenDraw);
				pGame = new PlayGame(eGame.SevenDraw);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();
			}
		});
		
		which_game.add(draw_7_cards);
		
		JRadioButtonMenuItem texas_hold_em = new JRadioButtonMenuItem("Texas Hold ‘em");
		texas_hold_em.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.TexasHoldEm;
				rle = new Rule(eGame.TexasHoldEm);
				pGame = new PlayGame(eGame.TexasHoldEm);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();
			}
		});
		
		which_game.add(texas_hold_em);
		
	
		JRadioButtonMenuItem omaha_game = new JRadioButtonMenuItem("Omaha");
		omaha_game.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent x){
				RuleType = eGame.Omaha;
				rle = new Rule(eGame.Omaha);
				pGame = new PlayGame(eGame.Omaha);
				for (Player ply: players.values()){
					pGame.AddPlayer(ply);
				}
				pGame.run();
			}
		});
		
		which_game.add(omaha_game);
		
		ButtonGroup group_type = new ButtonGroup();
		group_type.add(cards_5_stud);
		group_type.add(DeucesWild);
		group_type.add(cards_5_2_jokers);
		group_type.add(five_card_draw);
		group_type.add(draw_7_cards);
		group_type.add(texas_hold_em);
		group_type.add(omaha_game);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());
		
		gc = new GridBagConstraints();
		controlPanel = new ControlPanel();
		controlPanel.getBtnStart().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						rle = new Rule(RuleType);
						pGame = new PlayGame(RuleType);
						for (Player p: players.values()){
							pGame.AddPlayer(p);
						}
						pGame.run();
					}
					
				}
	);
	 	
		boardPanel = new BoardPanel(controlPanel, rle);
		addComponent(boardPanel, 1, 1, 1, 1);
		
		makePlayers();
		// Show the frame.
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		pGame.run();
		
		
	}
		
	public void makePlayers(){
		
		players = new LinkedHashMap<String, Player>();
		Player p1 = new Player("Bert", this);
		players.put(p1.GetPlayerID().toString(), p1);

		Player p2 = new Player("Joe", this);
		players.put(p2.GetPlayerID().toString(), p2);

		Player p3 = new Player("Jim", this);
		players.put(p3.GetPlayerID().toString(), p3);

		Player p4 = new Player("Bob", this);
		players.put(p4.GetPlayerID().toString(), p4);
	
        for (Player player : players.values()) {
        	pGame.AddPlayer(player);
        }
        
		playerPanels = new HashMap<String, PlayerPanel>();
		
		int i = 0;
		for (Player player : players.values()) {
			PlayerPanel panel = new PlayerPanel(tbl, rle, player);
			playerPanels.put(player.GetPlayerName(), panel);
			switch (i++) {
			case 0:
				// North position.
				addComponent(panel, 1, 0, 1, 1);
				break;
			case 1:
				// East position.
				addComponent(panel, 2, 1, 1, 1);
				break;
			case 2:
				// South position.
				addComponent(panel, 1, 2, 1, 1);
				break;
			case 3:
				// West position.
				addComponent(panel, 0, 1, 1, 1);
				break;
			default:
				// Do nothing.
			}
		}

	}

	
	private void addComponent(Component component, int x, int y, int width,
			int height) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		getContentPane().add(component, gc);
	}

	@Override
	public void messageReceived(String message) {
        boardPanel.setMessage(message);
        boardPanel.waitForUserInput();
		
	}

	@Override
	public void joinedTable(List<Player> players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handStarted(Player dealer) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void actorRotated(Player actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerUpdated(Player player) {
		System.out.println("PlayerUpdated in Main");
        PlayerPanel playerPanel = playerPanels.get(player.GetPlayerName());
        if (playerPanel != null) {
            playerPanel.update(player);
        }
		
	}

	@Override
	public void boardUpdated(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerActed(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action act(Set<Action> allowedActions) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
