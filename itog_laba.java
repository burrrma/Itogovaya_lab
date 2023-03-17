import java.util.*;
import java.util.Stack;
import java.util.Scanner;

class itog_laba {

    public static void main(String[] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите выражение с дробями, дроби необходимо записать в формате x/y, а выражении соблюдать пробелы между дробью и знаками +,-,*,: . Чтобы завершить прогремму, напиите quit");
            String primer = sc.nextLine();

            if (primer.equals("quit")) {
                System.out.println("До свидания!");
                break;
            } else {
                primer = ' ' + primer + ' ';

                Stack<Character> ch = new Stack<>();
                Stack<Integer> num = new Stack<>();
                Stack<Integer> den = new Stack<>();
                Stack<Character> skob = new Stack<>();
                Stack<Integer> prior = new Stack<>();
                int number;

                for (int i = 1; i < primer.length(); i++) {
                    // положительный числитель
                    if (Character.isDigit(primer.charAt(i)) && primer.charAt(i + 1) == '/'
                            && primer.charAt(i - 1) != '-') {
                        number = Character.digit(primer.charAt(i), 10);
                        num.push(number);
                    }
                    // отрицательный числитель
                    else if (Character.isDigit(primer.charAt(i)) && primer.charAt(i + 1) == '/'
                            && primer.charAt(i - 1) == '-') {
                        number = Character.digit(primer.charAt(i), 10);
                        number = -1 * number;
                        num.push(number);
                    }
                    // положительный знаменатель
                    else if (Character.isDigit(primer.charAt(i)) && (primer.charAt(i - 1) == '/' || i == 0)
                            && primer.charAt(i - 1) != '-' && primer.charAt(i) != '0') {

                        number = Character.digit(primer.charAt(i), 10);
                        den.push(number);
                    }
                    // отрицательный знаменатель
                    else if (Character.isDigit(primer.charAt(i)) && (primer.charAt(i - 1) == '/' || i == 0)
                            && primer.charAt(i - 1) == '-') {

                        number = Character.digit(primer.charAt(i), 10);
                        number = -1 * number;
                        den.push(number);
                    } else if (Character.isDigit(primer.charAt(i)) && primer.charAt(i + 1) == ' '
                            && primer.charAt(i) == '0') {
                        System.out.println("Ошибка! Делить на 0 нельзя!");
                        System.exit(0);
                    }
                    // +
                    else if ((primer.charAt(i - 1) == ' ' || i == 0) && primer.charAt(i) == '+'
                            && primer.charAt(i + 1) == ' ') {
                        ch.push(primer.charAt(i));
                        if (skob.size() == 0 || (skob.size() % 2) != 1) {
                            prior.push(0);
                        } else if (skob.size() != 0 || (skob.size() % 2) == 1) {
                            prior.push(1);
                        }
                    }
                    // -
                    else if ((primer.charAt(i - 1) == ' ' || i == 0) && primer.charAt(i) == '-'
                            && primer.charAt(i + 1) == ' ') {
                        ch.push(primer.charAt(i));
                        if (skob.size() == 0 || (skob.size() % 2) != 1) {
                            prior.push(0);
                        } else if (skob.size() != 0 || (skob.size() % 2) == 1) {
                            prior.push(1);
                        }
                    }
                    // *
                    else if ((primer.charAt(i - 1) == ' ' || i == 0) && primer.charAt(i) == '*'
                            && primer.charAt(i + 1) == ' ') {
                        ch.push(primer.charAt(i));
                        if (skob.size() == 0 || (skob.size() % 2) != 1) {
                            prior.push(0);
                        } else if (skob.size() != 0 || (skob.size() % 2) == 1) {
                            prior.push(1);
                        }
                    }
                    // :
                    else if ((primer.charAt(i - 1) == ' ' || i == 0) && primer.charAt(i) == ':'
                            && primer.charAt(i + 1) == ' ') {
                        ch.push(primer.charAt(i));
                        if (skob.size() == 0 || (skob.size() % 2) != 1) {
                            prior.push(0);
                        } else if (skob.size() != 0 || (skob.size() % 2) == 1) {
                            prior.push(1);
                        }
                    } else if (primer.charAt(i) == '(' || primer.charAt(i) == ')') {
                        skob.push(primer.charAt(i));
                    } else if (primer.charAt(i) == ' '
                            || (primer.charAt(i) == '/'
                                    && (Character.isDigit(primer.charAt(i + 1)) || primer.charAt(i + 1) == '-'))
                            || (primer.charAt(i) == '-' && Character.isDigit(primer.charAt(i + 1)))) {
                        continue;
                    }

                    else {
                        System.out.println("Ошибка. Некорректное выражение");
                        System.exit(0);
                    }

                    System.out.println(num);
                    System.out.println(den);
                    System.out.println(ch);
                    System.out.println(prior);
                    System.out.println(skob);

                }

                for (int n = 0; n < num.size(); n++) {
                    // избавляемся от минусов
                    if (num.elementAt(n) < 0 && den.elementAt(n) < 0) {
                        int number_n = num.elementAt(n);
                        int number_d = den.elementAt(n);
                        number_n = number_n * (-1);
                        number_d = number_d * (-1);
                        num.remove(n);
                        num.add(n, number_n);
                        den.remove(n);
                        den.add(n, number_d);
                    }
                }
                System.out.println(num);
                System.out.println(den);

                int num1 = 0;
                int num2 = 0;
                int den1 = 0;
                int den2 = 0;
                int num_r = 0;
                int den_r = 0;
                int colvo_prior1 = 0;
                int colvo_skob = 0;

                // количество приоритетных операторов в скобках
                for (int v = 0; v < prior.size(); v++) {
                    if ((prior.elementAt(v) == 1)) {
                        colvo_skob++;
                    }

                    else {
                        colvo_skob = colvo_skob;
                    }
                }

                // количество приоритетных операторов * и :
                for (int k = 0; k < ch.size(); k++) {
                    if ((ch.elementAt(k) == '*') || (ch.elementAt(k) == ':')) {
                        colvo_prior1++;

                    } else {
                        colvo_prior1 = colvo_prior1;
                    }
                }

                while (ch.size() != 0) {
                    for (int n = 0; (n + 1) <= ch.size(); n++) {

                        num2 = num.elementAt(n + 1);
                        num1 = num.elementAt(n);
                        den2 = den.elementAt(n + 1);
                        den1 = den.elementAt(n);

                        if (ch.elementAt(n) == '*' && ((colvo_skob <= 0) || (prior.elementAt(n) == 1))) {
                            ch.remove(n);
                            num.remove(n + 1);
                            num.remove(n);
                            den.remove(n + 1);
                            den.remove(n);
                            prior.remove(n);
                            num_r = num1 * num2;
                            den_r = den1 * den2;
                            num.add(n, num_r);
                            den.add(n, den_r);
                            colvo_prior1--;
                            colvo_skob--;

                        } else if (ch.elementAt(n) == ':' && ((colvo_skob <= 0) || (prior.elementAt(n) == 1))) {
                            ch.remove(n);
                            num.remove(n + 1);
                            num.remove(n);
                            den.remove(n + 1);
                            den.remove(n);
                            num_r = num1 * den2;
                            den_r = den1 * num2;
                            num.add(n, num_r);
                            den.add(n, den_r);
                            colvo_prior1--;
                            colvo_skob--;
                            prior.remove(n);

                        } else if (ch.elementAt(n) == '+'
                                && (colvo_prior1 == 0 || ((colvo_skob <= 0) || (prior.elementAt(n) == 1)))) {
                            ch.remove(n);
                            num.remove(n + 1);
                            num.remove(n);
                            den.remove(n + 1);
                            den.remove(n);
                            prior.remove(n);
                            num_r = ((num1 * den2) + (num2 * den1));
                            den_r = den1 * den2;
                            num.add(n, num_r);
                            den.add(n, den_r);
                            colvo_skob--;

                        } else if (ch.elementAt(n) == '-'
                                && (colvo_prior1 == 0 || ((colvo_skob <= 0) || (prior.elementAt(n) == 1)))) {
                            ch.remove(n);
                            num.remove(n + 1);
                            num.remove(n);
                            den.remove(n + 1);
                            den.remove(n);
                            prior.remove(n);
                            num_r = ((num1 * den2) - (num2 * den1));
                            den_r = den1 * den2;
                            num.add(n, num_r);
                            den.add(n, den_r);
                            colvo_skob--;
                        }
                        System.out.println(num);
                        System.out.println(den);
                        System.out.println(ch);
                        System.out.println(prior);
                        System.out.println(num_r + "/" + den_r);
                        System.out.println(colvo_skob);

                    }
                }
                // сокращение дроби
                for (int i = 1; i <= 9; i++) {
                    if ((num_r % i == 0) && (den_r % i == 0)) {
                        num_r = num_r / i;
                        den_r = den_r / i;
                    }
                }

                System.out.println(num_r + "/" + den_r);
            }
        }
    }
}
