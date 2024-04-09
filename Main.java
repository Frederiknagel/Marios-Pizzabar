import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Pizza[] menu = createMenuArray();
        displayMenuArray(menu);

        /* ****IO menukort ind her**** */
        String menuTemp = """
        
        1. Vesuvio: tomatsauce, ost, skinke, oregano
        2. Amerikaner: tomatsauce, ost, oksefars, oregano
        3. Cacciatore: tomatsauce, ost, pepperoni, oregano
        4. Carbona: tomatsauce, ost, kødsauce, spaghetti, cocktailpølser, oregano
        5. Dennis: tomatsauce, ost, skinke, pepperoni, cocktailpølser, oregano
        6. Bertil: tomatsauce, ost, bacon, oregano
        7. Silvia: tomatsauce, ost, pepperoni, rød peber, løg, oliven, oregano
        8. Victoria: tomatsauce, ost, skinke, ananas, champignon, løg, oregano
        9. Toronfo: tomatsauce, ost, skinke, bacon, kebab, chili, oregano
        10. Capricciosa: tomatsauce, ost, skinke, champignon, oregano
        11. Hawaii: tomatsauce, ost, skinke, ananas, oregano
        12. Le Blissoia: tomatsauce, ost, skinke, rejer, oregano
        13. Venezia: tomatsauce, ost, skinke, bacon, oregano
        14. Mafia: tomatsauce, ost, pepperoni, bacon, løg, oregano
        15. Nørrebro: shawarma, kebab, dressing
        """;
        Menu menuKort = new Menu(menuTemp);

        while(true) {
            displayOptions();

            try {
               int option = console.nextInt();
               handleOption(option, menuKort);

            } catch (Exception e) {
                System.out.println("Intet menupunkt valgt. Prøv igen.");
                console.nextLine();
                continue;
            }
        }
    }
    private static Pizza[] createMenuArray() {
        // PLACEHOLDER TEKST SÅ JEG KUNNE LAVE displayMenuArray
        Pizza pizza1 = new Pizza(1, "Vesuvio", new String[]{"Tomat", "Mozzarella"}, 60.0);
        Pizza pizza2 = new Pizza(2, "Amerikaner", new String[]{"Tomat", "Pepperoni", "Mozzarella"}, 70.0);
        Pizza pizza3 = new Pizza(3, "Cacciatore", new String[]{"Tomat", "Mozzarella"}, 79);
        Pizza pizza4 = new Pizza(4,"Carbona", new String[]{"Tomat", "Mozzarella"}, 70);
        Pizza pizza5 = new Pizza(5,"Dennis", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza6 = new Pizza(6, "Bertil", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza7 = new Pizza(7, "Silvia", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza8 = new Pizza(8, "Victoria", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza9 = new Pizza(9, "Toronfo", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza10 = new Pizza(10, "Capricciosa", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza11 = new Pizza(11, "Hawaii", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza12 = new Pizza(12, "Le Blissoia", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza13 = new Pizza(13, "Venezia", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza14 = new Pizza(14, "Mafia", new String[]{"Tomat", "Mozzarella"},70);
        Pizza pizza15 = new Pizza(15, "Nørrebro", new String[]{"Tomat", "Mozzarella"},70);

        return new Pizza[]{pizza1, pizza3, pizza4, pizza5, pizza6, pizza7, pizza8, pizza9, pizza10, pizza11, pizza12, pizza13, pizza14};
    }
    static void displayOptions() {
        String mainMenuText = "---------------------------------------------%nVelkommen til Pizzabiksen%n1: Se menukort%n2: Indtast bestilling%n3: Vis åbne bestillinger%n4: Fjern bestilling%n5: Udskriv dagens rapport%n6: Afslut program%n---------------------------------------------%n";
        System.out.printf(mainMenuText);
    }

    static void handleOption(int option, Menu menuKort) {
        switch (option) {
            case 1:
                menuKort.displayMenu();
                break;
            case 2:
                //Indtast bestilling
                break;
            case 3:
                //Vis åbne bestillinger
                break;
            case 4:
                //Fjern bestilling
                break;
            case 5:
                //Udskriv dagens rapport
                break;
            case 6:
                /* ****Gem dagens rapport**** */ 
                System.exit(0);
                break;
            default:
                System.out.println("Intet menupunkt valgt. Prøv igen.");
                break;
        }
    }
    private static void displayMenuArray(Pizza[] menu){
        System.out.println("PizzaMenu:");
        for (Pizza pizza: menu){
            System.out.println(pizza );
            System.out.println();
        }
    }

    public static Pizza[] addPizza(Scanner console, Pizza[] pizzaMenu){

        // Får brugerinput til den nye pizza del 1
        System.out.println("Tilføj en ny pizza.");
        System.out.println("Indtast den nye pizzas nummer: ");
        int pizzaNumber = console.nextInt();
        System.out.println("Indtast den nye pizzas navn: ");
        String pizzaName = console.next();
        System.out.println("Indtast antal ingredienser: ");
        int numberOfIngredients = console.nextInt();

        // Nyt array som skal gemme ingredienserne
        String[] ingredients = new String[numberOfIngredients];

        // Får brugerinput til den nye pizza del 2 og gemmer i array'et
        for (int j = 0; j < numberOfIngredients; j++){
            System.out.println("Indtast ingrediens " + j+1 + ": ");
            ingredients[j] = console.next();
        }

        // Får brugerinput til den nye pizza del 3
        System.out.println("Indtast den nye pizzas pris: ");
        double price = console.nextDouble();

        // Laver en ny pizzaarray som er 1 længere end den eksisterende pizzaarray
        Pizza[] newArray = new Pizza[pizzaMenu.length + 1];

        // Kopierer indhold fra den eksisterende array ind i den nye array
        for (int i = 0; i < pizzaMenu.length; i++){
            newArray[i] = pizzaMenu[i];
        }

        // Tilføjer ny pizza til den nye array
        newArray[pizzaMenu.length] = new Pizza(pizzaNumber, pizzaName, ingredients, price);

        // Returnerer den nye pizzaArray/menukort
        return newArray;
    }

    public static Pizza[] removePizza(Scanner console, Pizza[] pizzaMenu) {

        // Viser menukort til bruger så han kan vælge hvad der skal fjernes
        // Deaktiveret indtil videre da den metode ikke findes
        //System.out.println("Der er følgende pizzaer på menuen:");
        //displayMenuArray();

        // Bruger vælger nr. på den pizza der skal fjernes
        System.out.println("Tast nummer på den pizza du vil slette fra menuen.");
        int pizzaNumber = console.nextInt();

        // Bruger skal have mulighed for at fortryde sit valg
        System.out.println("Er du sikker på at du vil slette pizza nr. " + pizzaNumber + "? j/n: ");
        char confirmed = console.next().charAt(0);

        // Hvis bruger bekræfter, skal pizzaen slettes
        int deleteIndex = -1;
        if (console.next().charAt(0) == 'j') {
            for (int i = 0; i < pizzaMenu.length; i++) {
                if (pizzaMenu[i].getPizzaNummer() == pizzaNumber) {
                    deleteIndex = i;
                    break;
                } else System.out.println("Ingen pizza med dette nummer. Prøv igen.");
                removePizza(console, pizzaMenu);
            }

            // Laver en ny pizzaarray som er 1 kortere end den eksisterende pizzaarray
            Pizza[] newArray = new Pizza[pizzaMenu.length - 1];

            // Kopierer indhold fra den eksisterende array ind i den nye array frem til deleteIndex
            for (int i = 0; i < deleteIndex; i++) {
                newArray[i] = pizzaMenu[i];
            }
            // Kopierer indhold fra den eksisterende array ind i den nye array efter deleteIndex
            for (int i = deleteIndex; i < newArray.length; i++) {
                newArray[i] = pizzaMenu[i + 1];
            }
            // Returnerer den nye pizzaArray/menukort
            return newArray;
        }

        // Hvis ikke bruger bekræfter sletning, kaldes denne metode igen
        // Måske vil vi hellere vil starte programmet forfra i denne situation
        else {
            removePizza(console, pizzaMenu);
            return pizzaMenu;
        }
    }
}

