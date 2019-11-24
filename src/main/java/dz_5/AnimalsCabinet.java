package dz_5;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class AnimalsCabinet {

    /**
     * животные хранятся по разделам-кличкам, чтобы ускорить поиск по кличке
     * key -> Кличка; value -> Множество карточек сгрупированных по кличке, key -> уникальный номер животного, value -> Животное Animal
     */
    private Map<String, Map<String, Animal>> animalsMap = new HashMap<>();

    public Map<String, Map<String, Animal>> getAnimalsMap() {
        return animalsMap;
    }

    /**
     * добавление животного в картотеку
     *
     * @param animal входные данные
     * @throws Exception если нашлось совпадение в картатеке
     */
    public void addAnimal(Animal animal) throws Exception {
        //проверим нужно ли создавать новый раздел
        if (animalsMap.containsKey(animal.getName())) {
            Map<String, Animal> findedMap = animalsMap.get(animal.getName());
            //проверим наличие животного в картотеке
            if (!findedMap.containsValue(animal)){
            //if (findAnimal(animal) == null) {
                //запишем новое животное
                findedMap.put(animal.getUniqueNumber(), animal);
            } else {
                //необходимо передать на верх причину неудачи добавления животного
                throw new Exception("животное с такими данными уже зарегистрировано");
            }
        } else {
            //сохраняем животину в новый раздел
            Map<String, Animal> newMap = new HashMap<>();
            newMap.put(animal.getUniqueNumber(), animal);
            animalsMap.put(animal.getName(), newMap);
        }
    }

    public Map<String, Animal> getAnimals(String name) throws Exception {
        Map<String, Animal> findedMap = animalsMap.get(name);
        if (findedMap == null || findedMap.isEmpty()) {
            throw new Exception("животное с такими данными не зарегистрировано");
        }
        return findedMap;
    }

    /**
     * печатает раздел животных
     *
     * @param collection
     */
    public void printAnimals(Map<String, Animal> collection) {
        for (Map.Entry<String, Animal> entry : collection.entrySet()) {
            System.out.println(entry.getValue() == null ? "ошибка записи" : entry.getValue().toString());
        }
    }

    /**
     * выводит животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
     */
    public void printAnimals() {
        List<Animal> animalList = new ArrayList<Animal>();
        for (Map.Entry<String, Map<String, Animal>> entry : animalsMap.entrySet()) {
            Set<Animal> tempSet = new HashSet<>(entry.getValue().values());
            animalList.addAll(tempSet);
        }
        Collections.sort(animalList);
        for (Animal animal : animalList) {
            System.out.println(animal);
        }

    }

    /**
     * поиск животного по его кличке
     *
     * @param animal
     * @return
     * @throws Exception
     */
    public Animal findAnimal(Animal animal) throws Exception {
        Animal finded = null;
        if (animal != null) {
            Map<String, Animal> findedMap = getAnimals(animal.getName());
            for (Map.Entry<String, Animal> entry : findedMap.entrySet()) {
                if (animal.compareTo(entry.getValue()) == 0) {
                    finded = entry.getValue();
                    break;
                }
            }
        } else {
            throw new Exception("что то пошло не так при поиске животного: " + animal);
        }
        return finded;
    }

    /**
     * изменение данных животного по его идентификатору
     *
     * @param uuid
     * @param newAnimal
     * @return
     * @throws Exception
     */
    public boolean updateAnimalById(String uuid, Animal newAnimal) throws Exception {
        boolean result = false;
        if (newAnimal != null && StringUtils.isNoneEmpty(uuid)) {
            //ищем нужное животное
            for (Map.Entry<String, Map<String, Animal>> entry : animalsMap.entrySet()) {
                if (!result) {
                    Map<String, Animal> currentMap = entry.getValue();
                    for (Map.Entry<String, Animal> entryAnimals : currentMap.entrySet()) {
                        if (uuid.equals(entryAnimals.getKey())) {
                            //обновляем данные найденного из входных данных
                            Animal finded = entryAnimals.getValue();
                            if (newAnimal.getName() != null)
                                finded.setName(newAnimal.getName());
                            finded.setWeight(newAnimal.getWeight());
                            if (newAnimal.getPerson() != null)
                                finded.setPerson(newAnimal.getPerson());
                            result = true;
                            break;
                        }
                    }
                } else {
                    //прекращаем искать
                    break;
                }
            }
        } else {
            throw new Exception("что то пошло не так при редактировании животного: " + newAnimal);
        }
        return result;
    }
}
