package task05;

public class Task1 {
    public static void main(String[] args) {
        AnimalsCabinet hranilishe = new AnimalsCabinet();

        //создаем хозяина и животное:
        Person person1 = new Person("Негр", 50, Gender.MAN);
        Animal animal1 = new Animal("Йоу", person1, 10);
        try {
            System.out.println("в картотеке нашлись записи о животном с кличкой: " + animal1.getName());
            try {
                hranilishe.addAnimal(animal1);
                hranilishe.printAnimals(hranilishe.getAnimals(animal1.getName()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nхозяин внес изменения данных о домашнем животном: " + animal1.getName());
            try {
                hranilishe.updateAnimalById(animal1.getUniqueNumber(), new Animal(animal1.getName(), animal1.getPerson(), 20));
                hranilishe.printAnimals(hranilishe.getAnimals(animal1.getName()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nпопытались записать одинаковых животных: " + animal1.getName());
            try {
                Person person2 = new Person("Негр", 50, Gender.MAN);
                Animal animal2 = new Animal("Йоу", person2, 20);
                hranilishe.addAnimal(animal2);
                hranilishe.printAnimals(hranilishe.getAnimals(animal1.getName()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nвывели всех животных из картотеки с сортировкой: ");
            try {
                Person person2 = new Person("Бедросович Филипп", 52, Gender.MAN);
                Animal animal2 = new Animal("Жук-олень", person2, 5);
                hranilishe.addAnimal(animal2);
                hranilishe.printAnimals();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
