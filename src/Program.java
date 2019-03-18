import java.util.Scanner;
import java.sql.*;

public class Program{
    public static void main(String[] args) {
        Program program = new Program();
        if(program.open()) {
            //program.insert();
            program.select();
            program.close();
        }
    }

    Connection co;

    boolean open(){
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:c:\\sqlite\\FinalProject.db");
            System.out.println("Connected");
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    void insert(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter genre: ");
            String genre = scanner.nextLine();

            String query =
                    "INSERT INTO Games (Name, Genre) " +
                            "VALUES ('" + name + "','" + genre + "');";

            Statement statement = co.createStatement();
            statement.executeUpdate(query);

            System.out.println("Rows added");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    void select(){
        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT Id, Name, Genre, Age_Category, Price " +
                    "FROM Games";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                int ageCategory = rs.getInt("Age_Category");
                int Price = rs.getInt("Price");
                System.out.println(id + "\t|" + name + "\t|" + ageCategory + "\t|" + Price);
            }
            rs.close();
            statement.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    void close(){
        try{
            co.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}