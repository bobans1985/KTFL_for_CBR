package ru.round;

import ru.round.Model.Aktfl;
import ru.round.Model.Ktfl;
import ru.round.Response.ktflResponse;
import ru.round.Utils.PrefSettings;
//import oracle.jdbc.driver.OracleDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by GrishukovVM on 09.02.2016.
 */
public class startApp {

    public static void main(String[] args) {

        String DateOn=new PrefSettings().GetDateOn();
        String jdbcString=new PrefSettings().GetJDBCThin();
        String OutputDir=new PrefSettings().GetOutputDir();
        String currentFile="";
        int i=0;
        String arg;

        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            if (arg.equals("-DateOn")) {
                if (i < args.length)
                    DateOn = args[i++];
            }
            else if (arg.equals("-jdbcString")) {
                if (i < args.length)
                    jdbcString = args[i++];
            }
            else if (arg.equals("-OutputDir")) {
                if (i < args.length)
                    OutputDir = args[i++];
            }
    }

        System.out.format("Usage: %s [-DateOn] [-jdbcString] [-OutputDir] \r\n",currentFile);
        System.out.format("default: DateOn=%s \r\n\t\tjdbcString=%s \r\n\t\tOutputDir=%s \r\n",DateOn,jdbcString,OutputDir);
        System.out.println("\r\n");
        System.out.println("-------- Oracle JDBC Connection ------");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found");
            e.printStackTrace();
            return;
        }
        System.out.print("Oracle JDBC Driver found. Version: ");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcString);
            System.out.print(connection.getMetaData().getDriverVersion() + "\r\nConnection successful!\r\n");
        } catch (SQLException e) {
            System.out.println("Connection Failed!" +e);
            e.printStackTrace();
            return;

        }

        try {
            if (connection != null) {
                System.out.println("Start executing....");
                ktflResponse ktflRs = new ktflResponse(connection,DateOn);
                System.out.format("Count records=%d\r\n",ktflRs.getCount() );


                String out_str="";

                /*Header in file*/
                int lastiteranion = 1;
                Field[] fields = Aktfl.class.getDeclaredFields();
                for  (Field field : fields) {
                        out_str +=field.getName().toUpperCase();
                        if (fields.length!=lastiteranion) {
                            out_str +="\t";
                        }
                        lastiteranion++;
                }

                out_str+="\r\n";lastiteranion = 1;
                /*Body in file*/
                for (Ktfl ktfl: ktflRs.getKtflList()) {
                    out_str+=ktfl.toString();
                    if (ktflRs.getCount()!=lastiteranion) {
                        out_str +="\r\n";
                    }
                    lastiteranion++;
                }


                System.out.println(out_str);
                Charset charset = Charset.forName("cp1251");
                Path out_file= Paths.get(OutputDir + "\\ktfl_" + DateOn + ".txt");
                BufferedWriter writer = Files.newBufferedWriter(out_file, charset, StandardOpenOption.CREATE);
                writer.write(out_str, 0, out_str.length());
                writer.flush();



            } else {
                System.out.println("Failed to make connection!");
            }
        }catch (Exception e) {e.printStackTrace(); }

    }


    }

