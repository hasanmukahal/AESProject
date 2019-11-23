package aesproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HASAN
 */
public class Polynomial {

    int deg[];

    public Polynomial(int a) {
        deg = new int[9];
        if (a != -1) {
            deg[a] = 1;
        }

    }

    public Polynomial(Polynomial p) {
        this.deg = new int[p.deg.length - 1];
        for (int i = 0; i < this.deg.length; i++) {
            this.deg[i] = p.deg[i];
        }

    }

    public Polynomial plus(Polynomial b) {
        Polynomial c = new Polynomial(-1);
        for (int i = 0; i < this.deg.length; i++) {
            c.deg[i] = (c.deg[i] + this.deg[i]) % 2;
        }
        for (int i = 0; i < b.deg.length; i++) {
            c.deg[i] = (c.deg[i] + b.deg[i]) % 2;
        }
        return c;
    }

    public Polynomial minus(Polynomial b) {
        Polynomial c = new Polynomial(-1);
        for (int i = 0; i < this.deg.length; i++) {
            c.deg[i] = c.deg[i] + this.deg[i] % 2;
        }
        for (int i = 0; i < b.deg.length; i++) {
            c.deg[i] = Math.abs((c.deg[i] - b.deg[i])) % 2;
        }

        return c;
    }

    public Polynomial times(Polynomial b) {
        Polynomial c = new Polynomial(-1);
        for (int i = 0; i < this.deg.length; i++) {
            for (int j = 0; j < b.deg.length; j++) {
                if (this.deg[i] != 0 && b.deg[j] != 0) {
                    c.deg[i + j] = (c.deg[i + j] + 1) % 2;
                }
            }
        }
        return c;
    }

    public Polynomial[] divides(Polynomial value) {
        Polynomial f = this;
        Polynomial q = new Polynomial(-1);
        while (f.IndexOfone() >= value.IndexOfone()) {
            Polynomial extra = new Polynomial(f.IndexOfone() - value.IndexOfone());
            q = q.plus(extra);
            f = f.minus(value.times(extra));

        }
        return new Polynomial[]{q, f};
    }

    public boolean isOne() {
        boolean one = false;
        if (this.deg[0] == 1) {
            one = true;
        }
        boolean other = false;
        for (int i = 1; i < this.deg.length; i++) {
            if (this.deg[i] != 0) {
                other = true;
            }

        }
        if (one && !other) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = deg.length - 1; i >= 0; i--) {
            if (deg[i] == 1) {
                if (i == 1 && this.deg[i] == 1) {
                    s += "x+";
                } else if (i == 0 && this.deg[i] == 1) {
                    s += "1+";
                } else {
                    s += "x^" + i + "+";
                }
            }
        }
        if (s.length() == 0) {
            return "0";
        }
        return s.substring(0, s.length() - 1);
    }

    public static Polynomial Add(String s) {
        Polynomial temp = new Polynomial(-1);
        String arr[] = s.split("\\+");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("0") || arr[i].equals("00")) {
                temp = temp.plus(new Polynomial(-1));
            } else if (arr[i].equals("1")) {
                temp = temp.plus(new Polynomial(0));
            } else if (arr[i].equals("x")) {
                temp = temp.plus(new Polynomial(1));

            } else {
                temp = temp.plus(new Polynomial(Integer.parseInt(arr[i].charAt(2) + "")));
            }

        }

        return temp;
    }

    public boolean isZero() {
        for (int j = 0; j < this.deg.length; j++) {
            if (this.deg[j] != 0) {
                return false;
            }
        }

        return true;
    }

    public static Polynomial findpolyInverse(Polynomial A1, Polynomial A2, Polynomial B1, Polynomial B2) {

        if (B2.isOne() || B2.isZero()) {

            if (B2.isOne()) {
                return B1;
            } else {
                return new Polynomial(-1);
            }
        }

        Polynomial arr[] = A2.divides(B2);
        Polynomial temp = B2;
        B2 = arr[1];
        A2 = temp;
        temp = B1;
        B1 = A1.minus(B1.times(arr[0]));
        A1 = temp;

        return findpolyInverse(A1, A2, B1, B2);

    }

    public int IndexOfone() {
        for (int i = deg.length - 1; i >= 0; i--) {
            if (deg[i] == 1) {
                return i;
            }
        }

        return -1;
    }

}
