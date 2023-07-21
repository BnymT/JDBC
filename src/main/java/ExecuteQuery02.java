import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        System.out.println("------------Ornek 1-------------");
        // Ornek 1 : Bolumler tablosunda taban puani en yuksek 2. bolumun ismini ve puanini yaziniz
        String sql = "SELECT bolum, taban_puani FROM bolumler ORDER BY taban_puani DESC OFFSET 1 LIMIT 1";
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("bolum") + "--" + resultSet.getInt("taban_puani"));
        }
        // subquery ile cozersek
        String sql1 = "SELECT bolum, taban_puani FROM bolumler WHERE taban_puani = " +
                "(SELECT max(taban_puani) FROM bolumler WHERE taban_puani < (SELECT MAX(taban_puani) FROM bolumler)";

        // Ornek 2 : Bolum isimlerini, kampuslerini ve her bolumde okuyan ogrencilerin en yuksek puanlarini listeleyiniz
        System.out.println("------------Ornek 2-------------");
        String sql2 = "select bolum,kampus,(select max(puan) from ogrenciler o where o.bolum=b.bolum) max_puan " +
                "from bolumler b";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("bolum") + "--" + resultSet2.getString("kampus") + "--" + resultSet2.getInt("max_puan"));
        }
        st.close();
        con.close();
    }


}
