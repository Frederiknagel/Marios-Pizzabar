import java.util.Arrays;

public class main {

    private static void addOrder(Scanner console, Ordre[] orderList){
        System.out.println("Indtast detaljer for den nye ordre: ");

       // System.out.println("Indtast liste på de bestilte pizzaer: ");
        // String bestiltePizzaer = console.nextLine();
        // for loop to match order with pizzas from pizza menu
        System.out.println("Indtast note: ");
        String note = console.nextLine();


        System.out.println("Indtast afhentingstidspunk: ");
        int afhentTid = console.nextInt();

        System.out.println(" Indtast total pris: ");
        double pris = console.nextDouble();

        System.out.println("er ordren klar til afhentning");
        boolean ready = console.nextBoolean();

        // skal saveallOrders() funktionen ikke bruges til at gemme ordrene i txt?

        Ordre newOrder = new Ordre(bestiltePizzaer, note, pris, afhentTid, ready);

        // saveallOrders(newOrder);

        System.out.println("Order added successfully.");
    }





    }
    private static removeOrder(Scanner console, Ordre [] orderList){

        System.out.println("Hvilken order skal fjernes: ");
        int orderNo = console.NextInt();

        // Bruger skal have mulighed for at fortryde sit valg
        System.out.println("Er du sikker på at du vil slette order nr. " + orderNo + "? j/n: ");
        char confirmed = console.next().charAt(0);

        // Hvis bruger bekræfter, skal ordren slettes
        int deleteIndex = -1;
        if (confirmed == 'j') {
            for (int i = 0; i < orderList.length; i++) {
                if (orderList[i].gettoString() == orderNo) {
                    deleteIndex = i;
                    break;
                }
            }
            if (deleteIndex == -1){
                System.out.println("Ingen order med dette nummer. Prøv igen.");
                removeOrder(console, orderList);
            }

            // Laver en ny array som er 1 kortere end den eksisterende ordrearray
            Ordre [] newArray = new Order[orderList.length-1];

            // Kopierer indhold fra den eksisterende array ind i den nye array frem til deleteIndex
            for (int i = 0; i < deleteIndex; i++) {
                newArray[i] = orderList[i];
            }
            // Kopierer indhold fra den eksisterende array ind i den nye array efter deleteIndex
            for (int i = deleteIndex; i < newArray.length; i++) {
                newArray[i] = orderList[i + 1];
            }
            saveallOrders(newArray);
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





