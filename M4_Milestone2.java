/** Back-end Java
*** M4 - Milestone 2
*** Roger Torrent */

// CONFLICTE AMB LES FASES 2 I 3 DEL MILESTONE 1 PER L'ÚS DE LES MATEIXES CLASSES

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
	
	public Float cercaMenuItem(String plat) {
		return menu.get(plat);
	}
	
	public void printMenu() {
		Set<Map.Entry<String, Float>> items = menu.entrySet();
		for (Map.Entry<String, Float> item : items)
			System.out.format("%,5.2f %s%n", item.getValue(), item.getKey());
	}
}

class Comanda {
	private LinkedList<String> comanda;
	
	public Comanda() {
		comanda = new LinkedList<>();
	}
	
	public void addComandaItem(String plat) {
		comanda.add(plat);
	}
	
	public void printComanda() {
		for (String plat : comanda) System.out.println(plat);
	}
	
	Iterator<String> it;
	public String nextComandaItem() {
		if (it == null) it = comanda.iterator();
		if (it.hasNext()) return it.next(); else return null;
	}
}

/* És una llàstima treure aquest enum...
enum Bin {
	NO(0), SI(1);
	
	private final byte n;
	
	Bin(int n) { this.n = (byte)n; };
	public boolean bool() { return n == Bin.SI.n; };
}
*/

public class M4_Milestone2 {
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
				if (s.toUpperCase().equals("S") || s.toUpperCase().equals("SI")) {
					System.out.println("Plat?");
					if ((s = sc.nextLine()).isEmpty())
						throw new NullPointerException();
					try {
						if (carta.cercaMenuItem(s) == null)
							throw new NoSuchElementException("Plat desconegut: \'" + s +"\'");
						comanda.addComandaItem(s);
					} catch (NoSuchElementException e) { System.out.println(e.getMessage()); }
				} else throw new RuntimeException();
			} while (true);
		} catch(RuntimeException e) {}
		
		sc.close();
		
		System.out.println("");
		System.out.println("COMANDA:");
		comanda.printComanda();
		System.out.println("");
		
		String s;
		float preuTotal = 0.0f;
		if ((s=comanda.nextComandaItem()) != null) {
			do {
				Float f = carta.cercaMenuItem(s);
				preuTotal += f;
			} while ((s=comanda.nextComandaItem()) != null);
			System.out.format("Preu total: %,6.2f%n", preuTotal);
		}
		else
			System.out.println("Comanda buida.");
	}
}