package com.oraclexe;

import java.sql.*;

public class FirmCoded {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
            ResultSet rs;
            float amount;
            for (long txid = 1000; txid <= 101000; txid++) {
                PreparedStatement ps = con.prepareStatement("SELECT amount FROM transactions WHERE txid = ?");
                ps.setLong(1, txid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    amount = rs.getFloat(1);
                }
                rs.close();
                ps.close();
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("FirmCoded - Elapsed (ms): " + (stop - start));
    }
}

