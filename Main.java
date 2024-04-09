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
        Pizza pizza1 = new Pizza(1, "Margherita", new String[]{"Tomat", "Mozzarella"}, 60.0);
        Pizza pizza2 = new Pizza(2, "Pepperoni", new String[]{"Tomat", "Pepperoni", "Mozzarella"}, 70.0);

        return new Pizza[]{pizza1, pizza2};
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
}

