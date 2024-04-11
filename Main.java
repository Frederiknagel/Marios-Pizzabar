import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Pizza[] menu;
        Ordre[] ordreArray;

        /* ****IO menukort ind her**** */
        //displayMenuArray(menu);


        while(true) {
            displayOptions();

            try {
               int option = console.nextInt();
               menu = createMenuArray();
               ordreArray = createOrderArray();
               handleOption(option, menu, console, ordreArray);

            } catch (Exception e) {
                System.out.println("Intet menupunkt valgt. Prøv igen.");
                console.nextLine();
                continue;
            }
        }
    }
    public static Pizza[] createMenuArray() {
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
                pizzaData[i] = new Pizza(Integer.parseInt(pizzaStrings[0]), pizzaStrings[1].trim(), pizzaStrings[2].split(","), Double.parseDouble(pizzaStrings[3].replace(" kr","")));
                i++;
            }
            fileConsole.close();
            pizzaListe = pizzaData;

        } catch (IOException e) {
            System.out.println(e);
        }
        return pizzaListe;
    }

    public static Ordre[] createOrderArray(){
            Ordre[] ordreArray = null;

            Path path = Paths.get("Ordreliste.txt");
            long lines;
            try {
                lines = Files.lines(path).count();
                String[] data = new String[(int)lines];
                Ordre[] ordreData = new Ordre[(int) lines];
                File file = new File("Ordreliste.txt");
                Scanner fileConsole = new Scanner(file);
                int i = 0;
                while (fileConsole.hasNextLine()) {
                    data[i] = fileConsole.nextLine();
                    String[] ordreStrings = data[i].split(":");
                    ordreData[i] = new Ordre(Integer.parseInt(ordreStrings[0]), (ordreStrings[1]), (ordreStrings[2]), Integer.parseInt(ordreStrings[3]), Integer.parseInt(ordreStrings[4]), ordreStrings[5]);
                    i++;
                }
                fileConsole.close();
                ordreArray = ordreData;

            }   catch(IOException e){
                System.out.println(e);
           }
        return ordreArray;
       }


    public static void saveToMenuArray(Pizza[] pizzaArray){
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
    public static void displayOptions() {
        System.out.println("---------------------------------------------");
        System.out.println("Velkommen til Mario's Pizzabar");
        System.out.println("1: Se menukort");
        System.out.println("2: Tilføj pizza til menukort");
        System.out.println("3: Fjern pizza fra menukort");
        System.out.println("4: Indtast ordre");
        System.out.println("5: Vis åbne ordrer");
        System.out.println("6. Afslut ordre");
        System.out.println("7. Udskriv dagens rapport");
        System.out.println("8: Afslut program");
        System.out.println("---------------------------------------------");
    }

    public static void handleOption(int option, Pizza[] menu, Scanner console, Ordre[] ordreArray) {
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
                //Indtast ordre
                addOrder(console, ordreArray);
                break;
            case 5:
                //Vis åbne ordrer
                displayOpenOrders(ordreArray);
                break;
            case 6:
                //Afslut ordre
                removeOrder(console, ordreArray);
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
    public static void displayMenuArray(Pizza[] menu){
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
            // Gemmer opdateret menukort i TXT
            saveToMenuArray(newArray);
        }

        // Hvis ikke bruger bekræfter sletning, kaldes denne metode igen
        // Måske vil vi hellere vil starte programmet forfra i denne situation
        else {
            removePizza(console, pizzaMenu);
        }
    }

    private static void addOrder(Scanner console, Ordre[] orderList){
        System.out.println("Indtast detaljer for den nye ordre: ");

       // System.out.println("Indtast liste på de bestilte pizzaer: ");
        // String bestiltePizzaer = console.nextLine();
        // for loop to match order with pizzas from pizza menu
        System.out.println("Indtast ordrenummer: ");
        int ordreNummer = console.nextInt();
        
        System.out.println("Indtast pizzaer: ");
        String bestiltePizzaer = console.next();
        bestiltePizzaer += console.nextLine();

        System.out.println("Indtast note: ");
        String note = console.next();
        note += console.nextLine();

        System.out.println("Indtast afhentingstidspunk: ");
        int afhentTid = console.nextInt();

        System.out.println("Indtast total pris: ");
        int pris = console.nextInt();

        Ordre newOrder = new Ordre(ordreNummer, bestiltePizzaer, note, pris, afhentTid, "nej");

        // Laver en ny orderarray som er 1 længere end den eksisterende orderarray
        Ordre[] newArray = new Ordre[orderList.length + 1];


        // Kopierer indhold fra den eksisterende array ind i den nye array
        for (int i = 0; i < orderList.length; i++){
            newArray[i] = orderList[i];
        }
        saveAllOrders(newArray);
        System.out.println(Arrays.toString(newArray));
        System.out.println("Order added successfully.");
    }

    private static void removeOrder(Scanner console, Ordre [] orderList){

        System.out.println("Hvilken order skal fjernes? Indtast ordrenummer: ");
        int orderNo = console.nextInt();

        // Bruger skal have mulighed for at fortryde sit valg
        System.out.println("Er du sikker på at du vil slette order nr. " + orderNo + "? j/n: ");
        char confirmed = console.next().charAt(0);

        // Hvis bruger bekræfter, skal ordren slettes
        int deleteIndex = -1;
        if (confirmed == 'j') {
            for (int i = 0; i < orderList.length; i++) {
                if (orderList[i].getOrdreNummer() == orderNo) {
                    deleteIndex = i;
                    break;
                }
            }
            if (deleteIndex != -1){
                orderList[deleteIndex].setReady("ja");
                System.out.println("Ordre "+orderNo+" er afsluttet");
                
            }
            else{
                System.out.println("Ingen order med dette nummer. Prøv igen.");
                
            }

            //saveallOrders(newArray);
            
            // Gemmer opdateret menukort i TXT
            // Marcus kalder metode som skriver hele det nye menu-array til TXT
        }

        // Hvis ikke bruger bekræfter sletning, kaldes denne metode igen
        // Måske vil vi hellere vil starte programmet forfra i denne situation
       
    }
    public static void saveAllOrders(Ordre[] orderArray){
        try {
            FileWriter data = new FileWriter("Ordreliste.txt");
            for (int i = 0; i < orderArray.length; i++) {
                data.write(orderArray[i].toNewString());
                if (i<orderArray.length-1) {
                    data.write("\n");
                }
            }
            data.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void displayOpenOrders(Ordre[] orderArray){
        System.out.println("Åbne ordrer:\n");
        int openorders = 0;
        for (int i = 0; i < orderArray.length; i++){
            if (Objects.equals(orderArray[i].ready, "nej")){
                openorders++;
            }
        }
        Ordre[] openOrderArray = new Ordre[openorders];
        int k = 0;
        for (int i = 0; i < orderArray.length; i++){

            if (Objects.equals(orderArray[i].ready, "nej")){
                openOrderArray[k] = orderArray[i];
                k++;
            }
        }



        for (Ordre order: openOrderArray){
            System.out.println(order);
            System.out.println();
        }
    }
}

