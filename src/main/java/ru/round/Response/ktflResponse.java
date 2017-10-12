package ru.round.Response;

import oracle.jdbc.OracleTypes;
import ru.round.Model.Ktfl;
import ru.round.Utils.TypesUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by GrishukovVM on 10.02.2016.
 */
public class ktflResponse {
    List<Ktfl> ktflList=new ArrayList<Ktfl>();
    int count=0;

    public ktflResponse(Connection con,String DateOn) throws SQLException,Exception {
        //String query= "select * from v_ferro_ktfl";
        //Statement stmt = null;

        String queryPackage = "{ ? = call bobans.Get_KTFL_Fields_2017(?)}";
        CallableStatement pkgStmt = null;

        try {
            //stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery(query);

            System.out.println("Return result on date: " +DateOn);
            pkgStmt=con.prepareCall(queryPackage);
            pkgStmt.registerOutParameter(1, OracleTypes.CURSOR);
            pkgStmt.setString(2, DateOn);
            pkgStmt.execute();
            ResultSet rs = (ResultSet) pkgStmt.getObject(1);
            while (rs.next()) {
                Ktfl ktfl=new Ktfl(rs);
                ktflList.add(ktfl);
                count++;
            }
            if (count==0) {   System.out.println("Cursor return null results"); throw new EmptyStackException();}

        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (pkgStmt != null) { pkgStmt.close();}
        }
    }

    public List<Ktfl> getKtflList() {
        return ktflList;
    }

    public int getCount() {
        return count;
    }
}
