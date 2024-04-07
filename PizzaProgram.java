import java.util.Scanner;

public class PizzaProgram {
    private boolean running;
    private Scanner console;
    private Menu menu;

    public PizzaProgram() {
        running = true;
        console = new Scanner(System.in);
        menu = new Menu();
    }

    public void start() {
        while (running) {
            displayMainMenu();
            int option = console.nextInt();
            handleOption(option);
        }
        console.close();
    }

    private void displayMainMenu() {
        String mainMenuText = "---------------------------------------------%nVelkommen til Pizzabiksen%n1: Se menukort%n2: Indtast bestilling%n3: Vis åbne bestillinger%n4: Fjern bestilling%n5: Udskriv dagens rapport%n6: Afslut program%n---------------------------------------------%n";
        System.out.printf(mainMenuText);
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                menu.displayMenu();
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
                endProgram();
                break;
            default:
                System.out.println("Intet menupunkt valgt. Prøv igen.");
                break;
        }
    }

    private void endProgram() {
        running = false;
    }

    public static void main(String[] args) {
        PizzaProgram program = new PizzaProgram();
        program.start();
    }
}

class Menu {
    private String menuKort = """
        
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
            """;

    public void displayMenu() {
        System.out.println(menuKort);
    }
}
