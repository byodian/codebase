package com.byodian;

public class SwitchExpression {
    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FIRDAY, SATURDAY, SUNDAY, UNKNOWN }

    public static void main(String[] args) {
        printDayWithSwitchStatement(Day.UNKNOWN);
        convertDayWithSwitchExpression(Day.UNKNOWN);
        convertDayToNumber(2);
        convertToLabel("1");
    } 

    public static String convertToLabel(String quarter) {
        String quarterLabel = 
            switch(quarter) {
                case "0" -> {
                    System.out.println("Q1 - Winter");
                    yield "Q1 - Winter";
                }
                default -> "Unknown quarter";
        };
        System.out.println(quarterLabel);
        return quarterLabel;
    }

    public static void printDayWithSwitchStatement(Day day) {
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FIRDAY:
                System.out.println("The work day");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("The Holiday");
                break;
        }
    }

    public static String convertDayWithSwitchExpression(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FIRDAY -> {
                System.out.println("The work day");
                yield "The work day";
            }
            case SATURDAY, SUNDAY -> {
                System.out.println("The Holiday");
                yield "The holiday";
            }
            default -> "Unknown day";
        };
    }

    public static String convertDayToNumber(int quarter) {
        return switch(quarter) {
            case 0 : yield "Q1 - Winter";
            case 1 : yield "Q2 - Spring";
            case 2 : yield "Q3 - Summer";
            case 3 : yield "Q3 - Summer";
            default: yield "Unknown quarter";
        };
    }
}