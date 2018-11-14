package com.tsystems.javaschool.tasks.calculator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     * parentheses, operations signs '+', '-', '*', '/'<br>
     * Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    final static Character dot = '.';
    final static Character space = ' ';
    final static Character operators[] = {'+', '-', '*', '=', '/'};

    public static String evaluate(String statement) {
        LinkedList<Double> state = new LinkedList<>();
        LinkedList<Character> oper = new LinkedList<>();
        try {
            if (statement.length() > 0 && !statement.equals(null)) {
                for (int i = 0; i < statement.length(); i++) {
                    Character c = statement.charAt(i);
                    if (space.equals(c))
                        i++;
                    if (c == '(')
                        oper.add('(');
                    else if (c == ')') {
                        while (oper.getLast() != '(')
                            processOperator(state, oper.removeLast());
                        oper.removeLast();
                    } else if (isOperator(c)) {
                        while (!oper.isEmpty() && priority(oper.getLast()) >= priority(c))
                            processOperator(state, oper.removeLast());
                        oper.add(c);
                    } else {
                        String operand = "";
                        while (i < statement.length() &&
                                (Character.isDigit(statement.charAt(i)) || dot.equals(statement.charAt(i))))
                            operand += statement.charAt(i++);
                        --i;
                        state.add(Double.parseDouble(operand));
                    }
                }

            } else return null;
            while (!oper.isEmpty())
                processOperator(state, oper.removeLast());
            return convertDoubleInt(state.get(0).toString());
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String convertDoubleInt(String st) {
        String result = st;
        double parsed = Double.parseDouble(st);
        int testValue = Integer.parseInt(st.substring(0, st.indexOf(".")));
        if (parsed - testValue == 0) result = st.substring(0, st.indexOf("."));
        return result;

    }

    public static boolean isOperator(char c) {
        boolean result = false;
        for (Character op : operators)
            if (op.equals(c)) result = true;
        return result;
    }

    static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    static void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
        }
    }

}
