public class Menu {

    String menuKort; 

    public Menu (String menuKort){
        this.menuKort = menuKort;
    }

    public void displayMenu() {
        System.out.println(menuKort);
    }
}