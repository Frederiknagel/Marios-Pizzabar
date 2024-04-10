import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Pizza[] menu = createMenuArray();
        // displayMenuArray(menu);

        /* ****IO menukort ind her**** */


        while(true) {
            displayOptions();

            try {
               int option = console.nextInt();
               menu = createMenuArray();
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
                pizzaData[i] = new Pizza(Integer.parseInt(pizzaStrings[0]), pizzaStrings[1].trim(), pizzaStrings[2].trim().split(","), Double.parseDouble(pizzaStrings[3].replace(" kr","")));
                i++;
            }
            fileConsole.close();
            pizzaListe = pizzaData;

        } catch (IOException e) {
            System.out.println(e);
        }
        return pizzaListe;
    }

    private static void saveToMenuArray(Pizza[] pizzaArray){
        try {
            FileWriter data = new FileWriter("menukort.txt");
            for (int i = 0; i < pizzaArray.length; i++) {
                data.write(pizzaArray[i].toString());
                if (i<pizzaArray.length-1) {
                    data.write("\n");
                }
            }
            data.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    static void displayOptions() {
        System.out.println("---------------------------------------------");
        System.out.println("Velkommen til Mario's Pizzabar");
        System.out.println("1: Se menukort");
        System.out.println("2: Tilføj pizza til menukort");
        System.out.println("3: Fjern pizza fra menukort");
        System.out.println("4: Indtast bestilling");
        System.out.println("5: Vis åbne bestillinger");
        System.out.println("6. Annuller bestilling");
        System.out.println("7. Udskriv dagens rapport");
        System.out.println("8: Afslut program");
        System.out.println("---------------------------------------------");
    }

    static void handleOption(int option, Pizza[] menu, Scanner console) {
        switch (option) {
            case 1:
                // Kalder metode som kalder toString for hver pizza i pizzamenuen
                displayMenuArray(menu);
                break;
            case 2:
                addPizza(console, menu);
                break;
            case 3:
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
            System.out.println(pizza);
            System.out.println();
        }
    }

    public static void addPizza(Scanner console, Pizza[] pizzaMenu){

        // Får brugerinput til den nye pizza del 1
        System.out.println("Tilføj en ny pizza.");
        System.out.println("Indtast den nye pizzas nummer: ");
        int pizzaNumber = console.nextInt();
        System.out.println("Indtast den nye pizzas navn i ét ord: ");
        String pizzaName = console.next();
        System.out.println("Indtast antal ingredienser: ");
        int numberOfIngredients = console.nextInt();

        // Nyt array som skal gemme ingredienserne
        String[] ingredients = new String[numberOfIngredients];

        // Får brugerinput til den nye pizza del 2 og gemmer i array'et
        for (int j = 0; j < numberOfIngredients; j++){
            int k = j + 1;
            System.out.println("Indtast ingrediens " + k + ": ");
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
        // Marcus omskriver denne til opdateret metodes kald og parametre
        saveToMenuArray(newArray);

    }

    public static void removePizza(Scanner console, Pizza[] pizzaMenu) {

        // Bruger vælger nr. på den pizza der skal fjernes
        System.out.println("Tast nummer på den pizza du vil slette fra menuen:");
        int pizzaNumber = console.nextInt();

        // Bruger skal have mulighed for at fortryde sit valg
        System.out.println("Er du sikker på at du vil slette pizza nr. " + pizzaNumber + "? j/n: ");
        char confirmed = console.next().charAt(0);

        // Hvis bruger bekræfter, skal pizzaen slettes
        int deleteIndex = -1;
        if (confirmed == 'j') {
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
            saveToMenuArray(newArray);
            // Gemmer opdateret menukort i TXT
            // Marcus kalder metode som skriver hele det nye menu-array til TXT
        }

        // Hvis ikke bruger bekræfter sletning, kaldes denne metode igen
        // Måske vil vi hellere vil starte programmet forfra i denne situation
        else {
            removePizza(console, pizzaMenu);
        }
    }
}

