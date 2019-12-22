package jcore.task16;

public class Consts {
    public static String INSERT_USER = "INSERT INTO \"USER\" " +
            "(name, birthday, login_id, city, email) VALUES (?,?,?,?,?)";
    public static String SELECT_USER = "SELECT * from \"USER\" " +
            "WHERE login_id = ? and name = ?";
}
