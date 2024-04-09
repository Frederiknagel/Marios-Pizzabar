import java.util.Arrays;

public class Pizza {
    private int pizzaNummer;
    private String pizzaNavn;
    private String[] pizzaIngredienser;
    private double pizzaPris;

    public Pizza(int pizzaNummer, String pizzaNavn, String[] pizzaIngredienser, double pizzaPris) {
        this.pizzaNummer = pizzaNummer;
        this.pizzaNavn = pizzaNavn;
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
        return "Pizza Nummer: " + pizzaNummer +
                "\nNavn: " + pizzaNavn +
                "\nIngredienser: " + Arrays.toString(pizzaIngredienser) +
                "\nPris: " + pizzaPris + " kr";
    }
}