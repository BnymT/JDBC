import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {
        // 2. Adim : Database e baglama
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");

        // 3. Adim : Statement olusturma. db ye iletim yapmak ve query calistirmasi icin olusturulur
        Statement st = con.createStatement();

        // Ornek : idsi 5 ile 10 arasinda olan ulkelerin "country_name" bilgisini listeleyiniz
        String query1 = "select country_name from countries where id between 5 and 10";
        boolean sql1 = st.execute(query1);

        //kayitlari gormek icin executeQuery() kullanmaliyiz
        ResultSet resultSet = st.executeQuery(query1);
        /*
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        */

        while (resultSet.next()) {
            System.out.println("Ulke ismi : " + resultSet.getString("country_name"));
        }

        System.out.println("------------Ornek2-------------");
        // Ornek 2 :  phone_code u 550 den buyuk olan ulkelerin "phone_code" ve "country_name" bilgisini listeleyiniz
        String query2 = "select country_name, phone_code from countries where phone_code > 550";
        ResultSet rs2 = st.executeQuery(query2);
        while (rs2.next()) {
            System.out.println(rs2.getInt("phone_code") + "--" + rs2.getString("country_name"));
        }
        System.out.println("------------Ornek 3-------------");
        // Ornek 3 : developers tablosunda "salary degerini en dusuk olan developer larin tum bilgileri gosteriniz
        String query3 = "select * from developers where salary=(select min(salary) from developers)";
        ResultSet rs3 = st.executeQuery(query3);
        while (rs3.next()) {
            System.out.println(rs3.getInt("id") + "--" + rs3.getString("name") +
                    "--" + rs3.getInt("salary") + "--" + rs3.getString("prog_lang"));
        }

        System.out.println("------------Ornek 4-------------");
        //  ÖRNEK 4 : Puanı bölümlerin taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz
        String query4 = "select isim,puan from ogrenciler where puan > (select avg(taban_puani) from bolumler)";
        ResultSet rs4 = st.executeQuery(query4);
        while (rs4.next()) {
            System.out.println(rs4.getString("isim") + "--" + rs4.getInt("puan"));
        }

        st.close();
        con.close();

    }
}
