package dz_2;

public class Person implements Comparable<Person> {
    private int age;
    private Sex pol;
    private String name;

    public Person() {
    }

    public Person(int age, Sex pol, String name) {
        this.age = age;
        this.pol = pol;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getPol() {
        return pol;
    }

    public void setPol(Sex pol) {
        this.pol = pol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * метод для сравнения экземпляров Person по правилам:
     * -первые идут мужчины
     * -выше в списке тот, кто более старший
     * -имена сортируются по алфавиту
     * @param o с чем сравнивать объекта класса
     * @return 0 - если равны, 1 - this "больше", -1 - this "меньше"
     */
    public int compareTo(Person o) {
        if (getPol() == Sex.MAN) {
            if (o.getPol() == Sex.WOMAN) {
                //первые идут мужчины
                return 1;
            } else {
                //оба мужчины - выше в списке тот, кто более старший
                return comapreWithOneSex(o);
            }
        } else {
            if (o.getPol() == Sex.MAN) {
                return -1;
            } else {
                comapreWithOneSex(o);
            }
        }
        return 0;
    }

    private int comapreWithOneSex(Person o) {
        if (getAge() > o.getAge()) {
            return 1;
        } else {
            if (getAge() < o.getAge()) {
                return -1;
            } else {
                //возрасты равны - имена сортируются по алфавиту
                if (getName() != null) {
                    if (o.getName() != null) {
                        return String.CASE_INSENSITIVE_ORDER.compare(o.getName(), getName());
                    } else {
                        return 1;
                    }
                } else {
                    return -1;
                }
            }
        }
    }

    /**
     * для вывода объекта
     * @return "mame pol age"
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(getName() + " ");
        b.append(getPol() + " ");
        b.append(getAge());
        return b.toString();
    }
}
