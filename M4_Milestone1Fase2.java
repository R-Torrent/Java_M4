/** Back-end Java
*** M4 - Milestone 1 - Fase 2
*** Roger Torrent */

import java.util.*;
import java.lang.RuntimeException;

class Menu {
	private TreeMap<String, Float> menu;
	
	public Menu() {
		menu = new TreeMap<>(); // TreeMap ordena automàticament (alfabèticamente amb Key)
	}
	
	public void addMenuItem(String plat, float preu) {
		menu.put(plat, preu);
	}
	
	public void printMenu() {
		Set<Map.Entry<String, Float>> items = menu.entrySet();
		for (Map.Entry<String, Float> item : items)
			System.out.format("%,5.2f %s%n", item.getValue(), item.getKey());
	}
}

class Comanda { // Solució temporal durant la Fase 2
	private LinkedList<String> comanda;
	
	public Comanda() {
		comanda = new LinkedList<>();
	}
	
	public void addComandaItem(String plat) {
		comanda.add(plat);
	}
	
	public void printComanda() {
		System.out.println(comanda);
	}
}

enum Bin {
	NO(0), SI(1);
	
	private final byte n;
	
	Bin(int n) { this.n = (byte)n; };
	public boolean bool() { return n == Bin.SI.n; };
}

public class M4_Milestone1Fase2 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Menu carta = new Menu();

		try {
			do { // Omplir la carta
				String s;
				float f;
				System.out.println("Nom del plat:");
				if ((s = sc.nextLine()).isEmpty()) throw new NullPointerException();
				System.out.println("Preu de \'" + s + "\':");
				// .parseFloat inclou NullPointerException cuan String == ""
				f = Float.parseFloat(sc.nextLine());
				carta.addMenuItem(s, f);
			} while (true);
		} catch(RuntimeException e) {}
		
		System.out.println("MENU:");
		carta.printMenu();
		System.out.println("");
		
		Comanda comanda = new Comanda();
		
		try {
			do { // Fer la comanda
				String s;
				System.out.println("Vol demanar menjar? (S/N) [per defecte N]");
				if ((s = sc.nextLine()).isEmpty()) s = "N";
				Bin Op = ( s.toUpperCase().equals("S") ||
						s.toUpperCase().equals("SI") ? Bin.SI : Bin.NO );
				if (Op.bool()) {
					System.out.println("Plat?");
					if ((s = sc.nextLine()).isEmpty()) throw new NullPointerException();
					comanda.addComandaItem(s);
				} else break;
			} while (true);
		} catch(RuntimeException e) {}
		
		sc.close();
		
		System.out.println("COMANDA:");
		comanda.printComanda();
	}
}