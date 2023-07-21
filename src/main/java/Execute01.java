import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Adim : Driver i kaydet
        Class.forName("org.postgresql.Driver"); // Java 7 ile birlikte bunu yazmaya gerek yok

        // 2. Adim : Database e baglama
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

        // 3. Adim : Statement olusturma
        Statement st = con.createStatement();
        //System.out.println("Connection Success");

        // 4. Adim : Query(sorgu) olusturma
        // Ornek 1 : "workers" adinda bir tablo olusturup "worker_id, worker_name, salary" sutunlarini ekleyiniz
        /*
        boolean sql1= st.execute("create table workers(worker_id int,worker_name varchar(50),salary real)");

        System.out.println(sql1);
        */

        //execute DDL(Data Definition Language) ve ya DQL(Data Query Language)
        //DQL icin kullanilirsa ResultSet nesnesi alirsa true almazsa false doner
        //DDL icin kullanilirsa false doner

        // Ornek 2 : "workers" tablosuna VARCHAR(20) tipinde "city" sutunu ekleyiniz
        /*
        String query2 = "Alter table workers add column city varchar(20)";
        st.execute(query2);
        */

        // Ornek 3 : "workers" tablosunu SCHEMA dan siliniz.
        /*
        String query3 = "drop table workers";
        st.execute(query3);
        */

        // 5. Adim : Baglanti ve statement i kapatma
        st.close();
        con.close();
    }

}
