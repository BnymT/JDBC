import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/*
Transaction: Databasedeki atomik(parçalanamaz) en küçük işlem
İşlemler başarılı bir şekilde gerçekleşince transaction commit edilir.
En az bir aşamasında problem olursa rollback ile işlemler iptal edilir.
 */
public class Transaction01 {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();
        //hesap 1234 ten hesap no:5678  e 1000 para transfer olsun
        String sql="update hesaplar set bakiye=bakiye+? where hesap_no=?";
        PreparedStatement prst=con.prepareStatement(sql);
        prst.setDouble(1,-1000);
        prst.setInt(2,1234);
        prst.executeUpdate();
        if(true){
            throw new Exception();
        }
        prst.setDouble(1,1000);
        prst.setInt(2,5678);
        prst.executeUpdate();
        prst.close();
        con.close();
    }
}