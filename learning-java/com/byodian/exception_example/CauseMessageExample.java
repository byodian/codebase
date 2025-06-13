package com.byodian.exception_example;

import java.io.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author BYJ
 * @since 2025/02/27
 **/

public class CauseMessageExample {
    public static void handleException() throws ClassNotFoundException {
        Reader reader = Reader.nullReader();
        try(BufferedReader br = new BufferedReader(reader)) {
            System.out.println(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Class.forName("ClassNotFoundClass");
    }

    public static void throwEarlyCatchLate(String fileName) {
        Objects.requireNonNull(fileName);
        try(InputStream in = new FileInputStream(fileName)) {
            in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            throwEarlyCatchLate("./BaseException.java");
            handleException();
            throw new SQLException("Database connection failed");
        } catch(SQLException | ClassNotFoundException e) {
            IllegalArgumentException newException = new IllegalArgumentException("Invalid argument provided");
            newException.initCause(e);
            throw newException;
        }
    }
}
