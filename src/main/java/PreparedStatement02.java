import java.sql.*;

public class PreparedStatement02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        // Ornek 1 : Prepared Statement kullanarak ogrenciler tablosundan Matematik bölümünde okuyanları siliniz.
        String sql2="insert into bolumler values(?,?,?,?)";
        PreparedStatement prst2=con.prepareStatement(sql2);
        prst2.setInt(1,107);
        prst2.setString(2,"Yazılım Müh.");
        prst2.setInt(3,475);
        prst2.setString(4,"Merkez");
        int inserted=prst2.executeUpdate();
        System.out.println("inserted : "+inserted);
        prst2.close();
        prst2.close();
        st.close();
        con.close();
    }
}
