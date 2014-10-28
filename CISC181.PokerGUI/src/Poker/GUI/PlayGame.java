package Poker.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.Hand;
import pokerBase.Player;
import pokerEnums.eGame;
import pokerEnums.eRank;
import pokerEnums.eSuit;


public class PlayGame {
	private eGame gme;
	private ArrayList<Player> players = new ArrayList<Player>();

	public PlayGame(eGame gme) {
		this.gme = gme;
	}

	public eGame GetGame() {
		return this.gme;
	}

	public void AddPlayer(Player p) {
		players.add(p);
	}

	public void run() {

		playHand();

	}

	public void playHand() {
		switch (gme) {
		
		
		
		case FiveStud: {

			Deck d = new Deck(0);
			
			for (Player playerr : players) {
				playerr.resetHand();
			}

			for (Player playerrr : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.EvalHand();
				playerrr.SetHand(h);
			}

			// decides the winner on a label
			Collections.sort(players, Player.PlayerRank);
			for (Player PLAYER: players){
				if (PLAYER==players.get(0)){
					PLAYER.setWinner(true);
					}
				else{
					PLAYER.setWinner(false);
				}
						
						}
						
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}
		}
			break;
			
			case FiveStudTwoJoker:{ Deck d = new Deck(2);
			for(Player p: players){
				p.resetHand();
				
				
			}
			for (Player p:players){
				Hand h = new Hand();
				for (int playerr=0; playerr<5;playerr++){
					Card c =d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
			}
			
			// decides the winner on a label

			Collections.sort(players, Player.PlayerRank);
			for (Player PLAYER: players){
				if (PLAYER==players.get(0)){
					PLAYER.setWinner(true);
					}
				else{
					PLAYER.setWinner(false);
				}
						
						}
			for (Player p:players){
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
				
			}
			
			
			} break;
			
	case DeucesWild:{
				ArrayList<Card> wilds= new ArrayList<Card>();
				wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 40));
				wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 1));
				wilds.add(new Card(eSuit.SPADES, eRank.TWO, 14));
				wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 27));
				
				Deck d = new Deck(0, wilds);
				
				
				for (Player pplay:players){
					pplay.resetHand();
				}
				for (Player plyer:players){
					Hand hands = new Hand();
					for (int i=0; i<5;i++){
						Card c =d.drawFromDeck();
						hands.AddCardToHand(c);
					}
					hands.HandleJokerWilds();
					plyer.SetHand(hands);
				}
				
				// decides the winner on a label
				Collections.sort(players, Player.PlayerRank);
				for (Player PLAYER: players){
					if (PLAYER==players.get(0)){
						PLAYER.setWinner(true);
						}
					else{
						PLAYER.setWinner(false);
					}
							
							}
				for (Player p:players){
					p.getClient().playerUpdated(p);
					p.getClient().playerActed(p);
					
				}
				
				

			
			} break;
			
	case FiveDraw: {
				Deck d= new Deck(0);
			
			
			for (Player playerr:players){
				playerr.resetHand();
			}
			for (Player plyer:players){
				Hand h = new Hand();
				for (int x=0; x<5;x++){
					Card c =d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.EvalHand();
				plyer.SetHand(h);
		
			}
			
			// decides the winner on a label

			Collections.sort(players, Player.PlayerRank);
			for (Player PLAYER: players){
				if (PLAYER==players.get(0)){
					PLAYER.setWinner(true);
					}
				else{
					PLAYER.setWinner(false);
				}
						
						}
			for (Player p:players){
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
				
			}
			
	} break;
	
	case SevenDraw: {
		Deck d= new Deck(0);
	
	
	for (Player plyer:players){
		plyer.resetHand();
	}
	for (Player plyer:players){
		Hand h = new Hand();
		for (int i=0; i<7;i++){
			Card c =d.drawFromDeck();
			h.AddCardToHand(c);
		}
		h.EvalHand();
		plyer.SetHand(h);

	}
	
	// decides the winner on a label

	Collections.sort(players, Player.PlayerRank);
	for (Player PLAYER: players){
		if (PLAYER==players.get(0)){
			PLAYER.setWinner(true);
			}
		else{
			PLAYER.setWinner(false);
		}
				
				}
	
	for (Player p:players){
		p.getClient().playerUpdated(p);
		p.getClient().playerActed(p);
		
	}
	
} break;
	
	case TexasHoldEm: {
		Deck d= new Deck(0);
		for (Player p:players){
			p.resetHand();
		}
		
		
		
		for (Player p:players){
			Hand h = new Hand();
			for (int i=0; i<2;i++){
				Card c =d.drawFromDeck();
				h.AddCardToHand(c);
			}
			p.SetHand(h);
	
		}
		
		//community
		
		Hand com = new Hand();
		for (int i=0; i<5; i++){
			Card c = d.drawFromDeck();
			com.AddCardToHand(c);	
		}
		
		// decides the winner on a label

		
		for (Player p:players){
			p.getClient().playerUpdated(p);
			p.getClient().playerActed(p);
			
		}
		
		

	} break;
		
	case Omaha: {
		Deck d= new Deck(0);
		for (Player p:players){
			p.resetHand();
		}
		
		
		
		for (Player p:players){
			Hand h = new Hand();
			for (int i=0; i<4;i++){
				Card c =d.drawFromDeck();
				h.AddCardToHand(c);
			}
			p.SetHand(h);
	
		}
		
		//community
		Hand com = new Hand();
		for (int i=0; i<5; i++){
			Card c = d.drawFromDeck();
			com.AddCardToHand(c);	
		}
		

		for (Player p:players){
			p.getClient().playerUpdated(p);
			p.getClient().playerActed(p);
			
		}
		
		

	} break;
	
		}

	
}}