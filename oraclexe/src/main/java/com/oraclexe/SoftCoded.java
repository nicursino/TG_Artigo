package com.oraclexe;

import java.sql.*;

public class SoftCoded {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "system", "1234");
            PreparedStatement ps = con.prepareStatement("SELECT amount FROM HR.transactions WHERE txid = ?");
            ResultSet rs;
            float amount;
            for (long txid = 1000; txid <= 201000; txid++) {
                ps.setLong(1, txid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    amount = rs.getFloat(1);
                }
                rs.close();
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("SoftCoded - Elapsed (ms): " + (stop - start));
    }
}

