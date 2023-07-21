import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        // ORNEK 1 : Developers tablosunda maasi ortalama maastan az olanlarin maasini ortalama maas ile guncelleyiniz.
        String sql = "update developers set salary = (select avg(salary) from developers) where salary < (select avg(salary) from developers)";
        /*int updated = st.executeUpdate(sql); // update edilen sayiyi gosterir.
        System.out.println("Guncellenen kayit sayisi : " + updated);*/

        System.out.println("----------Ornek 1----------");
        ResultSet rs = st.executeQuery("select id, name, salary from developers");
        while (rs.next()){
            System.out.println(rs.getInt("id") + "--" + rs.getString("name") + "--" + rs.getInt("salary"));
        }

        // Ornek 2 : developers tablosuna yeni bir developer ekleyiniz
        String sql2 = "insert into developers(name, salary, prog_lang) values('Süleyman', 5500, 'Backend')";
        int inserted = st.executeUpdate(sql2);
        System.out.println("Ekleme developer : " + inserted);

        // Ornek 3 : developers tablosundan id'si 11 olanı siliniz.
        /*String sql3 = "delete from developers where id = 11";
        int deleted = st.executeUpdate(sql3);
        System.out.println("Terfi eden developer sayisi : " + deleted);*/

        // Ornek 4 : developers tablosundan prog_lang css olanları siliniz.
        String sql4 = "delete from developers where prog_lang ilike 'css'";
        int deletedrows = st.executeUpdate(sql4);
        System.out.println("Deleted rows" + deletedrows);

        st.close();
        con.close();
    }
}
