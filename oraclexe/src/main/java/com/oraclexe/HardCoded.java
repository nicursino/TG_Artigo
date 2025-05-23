package com.oraclexe;

import java.sql.*;

public class HardCoded {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "system", "1234");
            Statement st = con.createStatement();
            ResultSet rs;
            float amount;
            for (long txid = 1000; txid <= 201000; txid++) {
                String txt = "SELECT amount FROM HR.transactions WHERE txid = " + txid;
                rs = st.executeQuery(txt);
                if (rs.next()) {
                    amount = rs.getFloat(1);
                }
                rs.close();
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("HardCoded - Elapsed (ms): " + (stop - start));
    }
}

