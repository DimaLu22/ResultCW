package src;

public class Program {
        public static void main(String[] args) {
            Database database = new Database();
            Menu menu = new Menu(database);
            menu.displayMenu();
        }
}