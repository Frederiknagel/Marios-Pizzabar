import java.util.Date;

public class Ordre {


    String [] bestiltePizzaer;

    String note;

    double pris;

    Date afhentTid;
    boolean ready;

    public Ordre(String [] bestiltePizzaer, String note, double pris, Date afhentTid, boolean ready){

        this.bestiltePizzaer = bestiltePizzaer;
        this.note = note;
        this.pris = pris;
        this.afhentTid = afhentTid;
        this.ready = ready;
    }

    public String toString() {
        return("Ordre:\nbestiltePizzaer " +bestiltePizzaer + "\nnote:" +note+ "\npris: "+ pris+ "\nafhentningstidspunk: "+ afhentTid + "\nready: "+ ready);

    }

    public void setReady(boolean ready){
        this.ready = ready;
    }

    public boolean getReady() {
        return ready;
    }

}
