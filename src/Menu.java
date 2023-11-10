package src;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Database database;
    private final Scanner scanner;

    public Menu(Database database) {
        this.database = database;
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println(":");
                System.out.println("1.   ");
                System.out.println("2.    ");
                System.out.println("3.    ");
                System.out.println("4.    ");
                System.out.println("0. ");
                System.out.print("  : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addNewAnimal();
                    case 2 -> database.displayAllAnimals();
                    case 3 -> displayAnimalCommands();
                    case 4 -> teachNewCommand();
                    case 0 -> {
                        System.out.println(" .");
                        return;
                    }
                    default -> System.out.println(" .  .");
                }
            } catch (InputMismatchException e) {
                System.out.println(":   .  .");
                scanner.nextLine(); //      
            }
        }
    }


    private void addNewAnimal() {
        System.out.println("  :");
        String name = scanner.nextLine();
        System.out.println("    :");
        String skills = scanner.nextLine();

        System.out.println("  :");
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("5. ");
        int animalClass = scanner.nextInt();
        scanner.nextLine();

        Animal animal;
        switch (animalClass) {
            case 1 -> animal = new Dog(name, skills);
            case 2 -> animal = new Cat(name, skills);
            case 3 -> animal = new Hamster(name, skills);
            case 4 -> animal = new Donkey(name, skills);
            case 5 -> animal = new Horse(name, skills);
            default -> {
                System.out.println("   .");
                return;
            }
        }

        database.addAnimal(animal);
        System.out.println("     .");
    }

    private void displayAnimalCommands() {
        System.out.println("  :");
        String name = scanner.nextLine();

        database.displayAnimalCommands(name);
    }

    private void teachNewCommand() {
        System.out.println("  :");
        String name = scanner.nextLine();
        System.out.println("    :");
        String command = scanner.nextLine();

        database.teachNewCommand(name, command);
        System.out.println("    .");
    }
}