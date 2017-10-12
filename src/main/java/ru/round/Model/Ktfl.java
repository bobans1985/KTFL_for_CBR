package ru.round.Model;

import ru.round.Utils.TypesUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GrishukovVM on 10.02.2016.
 */
public class Ktfl extends Aktfl {

    /*конструкторы*/
    public Ktfl(){
    }
    public Ktfl(ResultSet rs) throws SQLException{
        mapResult(rs);
    }

    /*мапинг*/
    public void mapResult(ResultSet rs) throws SQLException{
        /*this.name=rs.getString("NAME");
        this.inn=rs.getString("INN");
        this.rezident=rs.getString("REZIDENT");*/
        try {
            Field[] fields = Aktfl.class.getDeclaredFields();
            ResultSetMetaData resultSetMetaData =  rs.getMetaData();
            for (Field field : fields) {
                //field.set(this, rs.getString(field.getName()));
                for (int i=1;i<=resultSetMetaData.getColumnCount(); i++) {
                    //System.out.println(resultSetMetaData.getColumnName(i)+" / id="+i+ "/ type="+resultSetMetaData.getColumnType(i));
                    if (resultSetMetaData.getColumnName(i).equals(field.getName().toUpperCase() )){
                        switch (resultSetMetaData.getColumnType(i)) {
                            case 93: field.set(this, TypesUtil.toDate(rs.getTimestamp(field.getName()))); break; /*TimeStamp Types*/
                                 default: field.set(this, rs.getString(field.getName())); break;
                        }
                    }
                }

            }
        } catch (SecurityException
                | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*----------------------*/
    public String toString(){
        StringBuilder out_ret = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Field[] fields = Aktfl.class.getDeclaredFields();
        try {
            for (Field field : fields) {

                if (out_ret.length()>0) {
                    out_ret.append("\t");
                }

                if ( field.getGenericType().getTypeName().toString().equals("java.sql.Timestamp")) {
                    out_ret.append(field.get(this)==null?"":sdf.format(field.get(this)));
                } else  out_ret.append(field.get(this)==null?"":field.get(this).toString().replaceAll("(\r\n|\n)","") );

                /*if ( field.getGenericType().getTypeName().toString().equals("java.sql.Timestamp")) {
                        out_ret.append(field.get(this)==null?"\t":sdf.format(field.get(this)) + "\t");
                } else out_ret.append(field.get(this)==null?"\t":field.get(this) + "\t");*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  out_ret.toString();
    }

}
