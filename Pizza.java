import java.util.Arrays;

public class Pizza {
    private int pizzaNummer;
    private String pizzaNavn;
    private String[] pizzaIngredienser;
    private double pizzaPris;

    public Pizza(int pizzaNummer, String pizzaNavn, String[] pizzaIngredienser, double pizzaPris) {
        this.pizzaNummer = pizzaNummer;
        this.pizzaNavn = pizzaNavn;
        for (int i = 0; i < pizzaIngredienser.length; i++) {
            pizzaIngredienser[i] = pizzaIngredienser[i].strip();
        }
        this.pizzaIngredienser = pizzaIngredienser;
        this.pizzaPris = pizzaPris;
    }
    
    public int getPizzaNummer() {
        return pizzaNummer;
    }
    
    public void setPizzaNummer(int pizzaNummer) {
        this.pizzaNummer = pizzaNummer;
    }
    
    public String getPizzaNavn() {
        return pizzaNavn;
    }
    
    public void setPizzaNavn(String pizzaNavn) {
        this.pizzaNavn = pizzaNavn;
    }
    
    public String[] getPizzaIngredienser() {
        return pizzaIngredienser;
    }
    
    public void setPizzaIngredienser(String[] pizzaIngredienser) {
        this.pizzaIngredienser = pizzaIngredienser;
    }
    
    public double getPizzaPris() {
        return pizzaPris;
    }
    
    public void setPizzaPris(double pizzaPris) {
        this.pizzaPris = pizzaPris;
    }

    public String toString() {
        return pizzaNummer +
                "." + pizzaNavn +
                ":" + Arrays.toString(pizzaIngredienser).replace("[", "").replace("]", "") +
                ":" + (int) pizzaPris + " kr";
    }
}