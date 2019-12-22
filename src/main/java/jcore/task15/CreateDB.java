package jcore.task15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static jcore.task15.Consts.*;

public class CreateDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        String conn = urlConnection + host + ":" + port + "/" + database;
        try (Connection connection = DriverManager.getConnection(conn, user, password);
             Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS \"USER_ROLE\"\n;" +
                    "DROP TABLE IF EXISTS \"USER\"\n;" +
                    "DROP TABLE IF EXISTS \"ROLE\"\n;" +
                    "\n" +
                    "create table \"USER\"\n" +
                    "(\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint user_pk\n" +
                    "\t\t\tprimary key,\n" +
                    "\tname text,\n" +
                    "\tbirthday date,\n" +
                    "\tlogin_id integer,\n" +
                    "\tcity text,\n" +
                    "\temail text,\n" +
                    "\tdescription text\n" +
                    ");\n" +
                    "\n" +
                    "alter table \"USER\" owner to postgres;\n" +
                    "\n" +
                    "create unique index user_login_id_uindex\n" +
                    "\ton \"USER\" (login_id);\n" +
                    "\n" +
                    "create table \"ROLE\"\n" +
                    "(\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint role_pk\n" +
                    "\t\t\tprimary key,\n" +
                    "\tname text,\n" +
                    "\tdescription text\n" +
                    ");\n" +
                    "\n" +
                    "alter table \"ROLE\" owner to postgres;\n" +
                    "\n" +
                    "create unique index role_id_uindex\n" +
                    "\ton \"ROLE\" (id);\n" +
                    "\n" +
                    "create table \"USER_ROLE\"\n" +
                    "(\n" +
                    "\tid serial not null\n" +
                    "\t\tconstraint user_role_pk\n" +
                    "\t\t\tprimary key,\n" +
                    "\tuser_id integer not null\n" +
                    "\t\tconstraint user_role_user_id_fk\n" +
                    "\t\t\treferences \"USER\",\n" +
                    "\trole_id integer not null\n" +
                    "\t\tconstraint user_role_role_id_fk\n" +
                    "\t\t\treferences \"ROLE\"\n" +
                    "\t\t\t\ton update cascade on delete cascade\n" +
                    ");\n" +
                    "\n" +
                    "alter table \"USER_ROLE\" owner to postgres;\n" +
                    "\n" +
                    "create unique index user_role_id_uindex\n" +
                    "\ton \"USER_ROLE\" (id);\n" +
                    "\n");
        }
    }
}
