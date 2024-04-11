import java.util.Date;

public class Ordre {
    int ordreNummer;

    String bestiltePizzaer;

    String note;

    int pris;

    int afhentTid;
    String ready;

    public Ordre(int ordreNummer, String bestiltePizzaer, String note, int pris, int afhentTid, String ready){

        this.ordreNummer = ordreNummer;
        this.bestiltePizzaer = bestiltePizzaer;
        this.note = note;
        this.pris = pris;
        this.afhentTid = afhentTid;
        this.ready = ready;
    }

    public String toString() {
        return("Ordre " + ordreNummer + " :\nBestilling: " + bestiltePizzaer + "\nNote: " +note+ "\nPris: "+ pris+ "\nAfhentningstidspunkt: "+ afhentTid);

    }

    public int getOrdreNummer(){
        return this.ordreNummer;
    }

    public void setReady(String ready){
        this.ready = ready;
    }

    public String getReady() {
        return this.ready;
    }

}
