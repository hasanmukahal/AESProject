/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesproject;

import static aesproject.Polynomial.Add;
import java.util.Arrays;

/**
 *
 * @author HASAN
 */
public class AESproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // System.out.println(Polynomial.findInverse(b,m,A1,A2,B1,B2));
        // System.out.println("hasan");
        // System.out.println(hexa1bytetoBinary('0', 'F'));
        //  System.out.println(BinaryToHexa(hexa1bytetoBinary('0', 'F')));
        //  System.out.println(BinarytoPoly(hexa1bytetoBinary('0', 'F'))); 
        //  System.out.println(polytoBinary(b));
        // System.out.println(SubstituteByteTransformation(Polynomial.findInverse(b,m,A1,A2,B1,B2)));
        //    System.out.println(hexa1bytetoBinary('8', 'A'));
        // System.out.println(getSubbytefrombinary(hexa1bytetoBinary('8', 'A')));
        //System.out.println(SubstituteByteTransformation('0', '0'));
        //   System.out.println(multwoOneByteHexa("8c" , "02"));
        String[][] c = {{"87", "f2", "4d", "97"},
        {"6e", "4c", "90", "ec"},
        {"46", "e7", "4a", "c3"},
        {"a6", "8c", "d8", "95"}
        };
        //printcontentoftwodimetionalarray(mixcol(c));

        String[][] k = {{"2b", "28", "ab", "09"},
        {"7e", "ae", "f7", "cf"},
        {"15", "d2", "15", "4f"},
        {"16", "a6", "88", "3c"}
        };
        String[][] k2 = {{"ef", "a8", "b6", "db"},
        {"44", "52", "71", "0b"},
        {"a5", "5b", "25", "ad"},
        {"41", "7f", "3b", "00"}
        };
        printcontentoftwodimetionalarray(KeyExpantion(k2, 5));

    }

    public static String[][] mixcol(String[][] inputArray) {
        String[][] c = {{"02", "03", "01", "01"},
        {"01", "02", "03", "01"},
        {"01", "01", "02", "03"},
        {"03", "01", "01", "02"}
        };

        String[][] product = {{"00", "00", "00", "00"},
        {"00", "00", "00", "00"},
        {"00", "00", "00", "00"},
        {"00", "00", "00", "00"}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {

                    product[i][j] = xorOneByte(multwoOneByteHexa(c[i][k], inputArray[k][j]), product[i][j]);
                }
            }
        }
        return product;

    }

    public static String findInverse(String b) {
        String m = "x^8+x^4+x^3+x+1";

        Polynomial A1 = new Polynomial(-1);
        Polynomial A2 = Add(m);
        Polynomial B1 = new Polynomial(0);
        Polynomial B2 = Add(b);

        return Polynomial.findpolyInverse(A1, A2, B1, B2).toString();

    }

    public static String hexatoBinary(char a) {

        switch (a) {
            case '0':
                return "0000";

            case '1':
                return "0001";

            case '2':
                return "0010";

            case '3':
                return "0011";

            case '4':
                return "0100";

            case '5':
                return "0101";

            case '6':
                return "0110";

            case '7':
                return "0111";

            case '8':
                return "1000";

            case '9':
                return "1001";

            case 'A':
            case 'a':
                return "1010";

            case 'B':
            case 'b':
                return "1011";

            case 'C':
            case 'c':
                return "1100";

            case 'D':
            case 'd':
                return "1101";

            case 'E':
            case 'e':
                return "1110";

            case 'F':
            case 'f':
                return "1111";

            default:
                return "\nInvalid hexadecimal digit ";

        }
    }

    public static String hexa1bytetoBinary(char a, char b) {
        return hexatoBinary(a) + hexatoBinary(b);

    }

    public static String BinaryToHexa(String binary) {
        int[] hex = new int[2];
        int i = 1, j = 0, rem, dec = 0, bin;

        bin = Integer.parseInt(binary);
        while (bin > 0) {
            rem = bin % 2;
            dec = dec + rem * i;
            i = i * 2;
            bin = bin / 10;
        }
        i = 0;
        while (dec != 0) {
            hex[i] = dec % 16;
            dec = dec / 16;
            i++;
        }
        String hexatext = "";
        for (int q = 1; q >= 0; q--) {
            switch (hex[q]) {
                case 10:
                    hexatext += "A";
                    break;
                case 11:
                    hexatext += "B";
                    break;
                case 12:

                    hexatext += "C";
                    break;
                case 13:
                    hexatext += "D";
                    break;
                case 14:
                    hexatext += "E";
                    break;
                case 15:
                    hexatext += "F";
                    break;
                default:
                    hexatext += hex[q];

            }

        }
        return hexatext;
    }

    public static String BinarytoPoly(String bn) {
        String ret = "";
        boolean flag = true;
        for (int i = 0; i < bn.length(); i++) {
            if (i == bn.length() - 1 && bn.charAt(i) == '1') {
                ret += "1+";
                flag = false;
            } else if (bn.charAt(i) != '0') {
                ret += "x^" + (bn.length() - 1 - i) + "+";
                flag = false;
            }

        }
        if (!flag) {
            return ret.substring(0, ret.length() - 1);
        }
        return ret;

    }

    public static String polytoBinary(String poly) {
        poly = poly.replace('X', 'x');
        char[] b = {'0', '0', '0', '0', '0', '0', '0', '0'};
        if (poly.contains("x^7")) {
            b[0] = '1';
        }
        if (poly.contains("x^6")) {
            b[1] = '1';
        }
        if (poly.contains("x^5")) {
            b[2] = '1';
        }
        if (poly.contains("x^4")) {
            b[3] = '1';
        }
        if (poly.contains("x^3")) {
            b[4] = '1';
        }
        if (poly.contains("x^2")) {
            b[5] = '1';
        }
        if (poly.contains("x^1")) {
            b[6] = '1';
        }
        if (poly.contains("+x+")) {
            b[6] = '1';
        }
        if (poly.contains("x+")) {
            b[6] = '1';
        }
        if (poly.charAt(poly.length() - 1) == 'x') {
            b[6] = '1';
        }
        if (poly.contains("+1")) {
            b[7] = '1';
        }

        return String.valueOf(b);

    }

    public static String getSubbytefrombinary(String bin) {
        String c = "01100011";
        char[] b = new char[8];
        for (int i = 7; i >= 0; i--) {
            int x = invsermodulo(i - 4);
            int y = invsermodulo(i - 5);
            int z = invsermodulo(i - 6);
            int f = invsermodulo(i - 7);

            b[i] = xor(xor(xor(xor(xor(bin.charAt(i), bin.charAt(x)), bin.charAt(y)), bin.charAt(z)), bin.charAt(f)), c.charAt(i));

        }
        return BinaryToHexa(String.copyValueOf(b));

    }

    public static String SubstituteByteTransformation(char a, char b) {
        if (a == '0' && b == '0') {
            return "63";
        }
        String bn = hexa1bytetoBinary(a, b);
        String poly = BinarytoPoly(bn);
        String polyinverse = findInverse(poly);
        String bninverse = polytoBinary(polyinverse);

        return getSubbytefrombinary(bninverse);

    }

    public static int invsermodulo(int i) {

        if (i < 0) {
            return i + 8;
        }
        return i;
    }

    public static char xor(char a, char b) {
        if (a == b) {
            return '0';
        }
        return '1';

    }

    public static String xorOneByte(String hx1, String hx2) {
        String b1 = hexa1bytetoBinary(hx1.charAt(0), hx1.charAt(1));
        String b2 = hexa1bytetoBinary(hx2.charAt(0), hx2.charAt(1));
        String ret = "";
        for (int i = 0; i <= 7; i++) {
            ret += xor(b1.charAt(i), b2.charAt(i));
        }

        return BinaryToHexa(ret);
    }

    public static String multwoOneByteHexa(String hx1, String hx2) {
        String b1 = hexa1bytetoBinary(hx1.charAt(0), hx1.charAt(1));
        String b2 = hexa1bytetoBinary(hx2.charAt(0), hx2.charAt(1));
        String pol1 = BinarytoPoly(b1);
        String pol2 = BinarytoPoly(b2);

        Polynomial p1 = Polynomial.Add(pol1);
        Polynomial p2 = Polynomial.Add(pol2);
        Polynomial mulpol = p1.times(p2);

        Polynomial dividence = Polynomial.Add("x^8+x^4+x^3+x+1");

        Polynomial mulpolmodul = mulpol.divides(dividence)[1];
        String binary = (polytoBinary(mulpolmodul.toString()));
        return BinaryToHexa(binary);

    }

    public static void printcontentoftwodimetionalarray(String[][] a) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static String[][] KeyExpantion(String[][] RoundKey, int roundnum) {
        String hexab = getB(roundnum);
        String[] w0 = getColumn(RoundKey, 0);
        String[] w1 = getColumn(RoundKey, 1);
        String[] w2 = getColumn(RoundKey, 2);
        String[] w3 = getColumn(RoundKey, 3);

        String[] w4 = xor1word(w0, gFunction(w3, hexab));
        String[] w5 = xor1word(w4, w1);
        String[] w6 = xor1word(w5, w2);
        String[] w7 = xor1word(w6, w3);
        String[][] Key = new String[4][4];
        Key[0] = w4;
        Key[1] = w5;
        Key[2] = w6;
        Key[3] = w7;
        //Code to transpose a matrix  
        String[][] transpose = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                transpose[i][j] = Key[j][i];
            }
        }
        return transpose;

    }

    public static String[] gFunction(String[] w0, String hexab) {
        String[] shiftedw0 = new String[w0.length];
        for (int i = 0; i < w0.length - 1; i++) {
            shiftedw0[i] = w0[i + 1];
        }
        shiftedw0[w0.length - 1] = w0[0];
        for (int i = 0; i < w0.length; i++) {
            shiftedw0[i] = SubstituteByteTransformation(shiftedw0[i].charAt(0), shiftedw0[i].charAt(1));
            if (i == 0) {
                shiftedw0[0] = xorOneByte(shiftedw0[0], hexab);
            }
        }
        return shiftedw0;

    }

    public static String getB(int roundnum) {

        switch (roundnum) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "04";
            case 4:
                return "08";
            case 5:
                return "10";
            case 6:
                return "20";
            case 7:
                return "40";
            case 8:
                return "80";
            case 9:
                return "1b";
            case 10:
                return "36";
            default:
                return "00";

        }

    }

    public static String[] xor1word(String[] w1, String[] w2) {
        String[] arr = new String[w1.length];
        for (int i = 0; i < w1.length; i++) {
            arr[i] = xorOneByte(w1[i], w2[i]);

        }

        return arr;
    }

    public static String[] getColumn(String[][] array, int index) {
        String[] column = new String[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

}
