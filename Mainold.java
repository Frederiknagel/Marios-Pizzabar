import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

public class Mainold {
    
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Pizza[] menu = createMenuArray();

        while(true) {
            displayOptions();
            try {
               int option = console.nextInt();
               handleOption(option, menu, console);

            } catch (Exception e) {
                System.out.println("Intet menupunkt valgt. Prøv igen.");
                console.nextLine();
                continue;
            }
        }
    }
    private static Pizza[] createMenuArray() {

        Pizza[] pizzaListe = null; 

        Path path = Paths.get("menukort.txt");
        long lines;

        try {
            lines = Files.lines(path).count();
            String[] data = new String[(int)lines];
            Pizza[] pizzaData = new Pizza[(int)lines];
            File file = new File("menukort.txt");
            Scanner fileConsole = new Scanner(file);
            int i = 0;
            while (fileConsole.hasNextLine()) {
                data[i] = fileConsole.nextLine();
                String[] pizzaStrings = data[i].split(":|\\.");
                pizzaData[i] = new Pizza(Integer.parseInt(pizzaStrings[0]), pizzaStrings[1], pizzaStrings[2].split(","), (double)0);
                i++;
            }
            fileConsole.close();
            pizzaListe = pizzaData;

        } catch (IOException e) {
            System.out.println(e);
        }
        return pizzaListe;
    }

    private static void saveToMenuArray(Pizza pizza){
        try {
            FileWriter data = new FileWriter("menukort.txt", true);
            data.write("\n");
            data.write(pizza.toString());
            data.close();
            //data.write(pizza.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void displayOptions() {
        String mainMenuText = """
            ---------------------------------------------
            Velkommen til Pizzabiksen
            1: Se menukort
            2: Tilføj pizza fra menukort
            3: Fjern pizza fra menukort 
            4: Indtast bestilling
            5: Vis åbne bestillinger
            6: Fjern bestilling
            7: Udskriv dagens rapport
            8: Afslut program
            ---------------------------------------------
                            """;
            System.out.printf(mainMenuText);
    }

    static void handleOption(int option, Pizza[] menu, Scanner console) {
        switch (option) {
            case 1:
                displayMenuArray(menu);
                break;
            case 2:
                //Tilføj pizza til menukort
                addPizza(console, menu);
                break;
            case 3:
                //Fjern pizza fra menukort 
                removePizza(console, menu);
                break;
            case 4:
                //Indtast bestilling
                break;
            case 5:
                //Vis åbne bestillinger
                break;
            case 6:
                //Fjern bestilling
                break;
            case 7:
                //Udskriv dagens rapport
                break;
            case 8:
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
        saveToMenuArray(newArray[pizzaMenu.length]);

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
                }
            }
            if (deleteIndex == -1){
                System.out.println("Ingen pizza med dette nummer. Prøv igen.");
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

