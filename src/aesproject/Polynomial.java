/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesproject;

/**
 *
 * @author HASAN
 */
public class Polynomial {
    private int deg[];

	public Polynomial(int b, int a) {
		deg = new int[b + 1];
		if (a != 0)
			deg[b] = 1;

	}

	public Polynomial(Polynomial p) {
		this.deg = new int[p.deg.length];
		for (int i = 0; i < p.deg.length; i++) {
			this.deg[i] = p.deg[i];
		}

	}

	public Polynomial plus(Polynomial b) {
		Polynomial c = new Polynomial(Math.max(this.deg.length - 1, b.deg.length - 1), 0);
		for (int i = 0; i < this.deg.length; i++)
			c.deg[i] = (c.deg[i] + this.deg[i]) % 2;
		for (int i = 0; i < b.deg.length; i++)
			c.deg[i] = (c.deg[i] + b.deg[i]) % 2;
		return c;
	}

	public Polynomial minus(Polynomial b) {
		Polynomial c = new Polynomial(Math.max(this.deg.length - 1, b.deg.length - 1), 0);
		for (int i = 0; i < this.deg.length; i++)
			c.deg[i] = c.deg[i] + this.deg[i] % 2;
		for (int i = 0; i < b.deg.length; i++)
			c.deg[i] = Math.abs((c.deg[i] - b.deg[i])) % 2;
		int j = 0;
		for (int i = 0; i < c.deg.length; i++) {
			if (c.deg[i] == 1)
				j = i;
		}
		Polynomial temp = new Polynomial(j, 0);
		for (int i = 0; i < temp.deg.length; i++) {
			temp.deg[i] = c.deg[i];
		}
		return temp;
	}

	public Polynomial times(Polynomial b) {
		Polynomial c = new Polynomial(this.deg.length - 1 + b.deg.length - 1, 0);
		for (int i = 0; i < this.deg.length; i++) {
			for (int j = 0; j < b.deg.length; j++) {
				if (this.deg[i] != 0 && b.deg[j] != 0)
					c.deg[i + j] = (c.deg[i + j] + 1) % 2;
			}
		}
		return c;
	}

	public Polynomial[] divides(Polynomial b) {
		Polynomial c = new Polynomial(this);
		Polynomial q = new Polynomial(0, 0);
		while (c.deg.length >= b.deg.length) {
			Polynomial extra = new Polynomial(c.deg.length - b.deg.length, 1);
			q = q.plus(extra);
			c = c.minus(b.times(extra));
			
		}
		return new Polynomial[] { q, c };
	}

	public boolean isOne() {
		boolean one = false;
		if (this.deg[0] == 1)
			one = true;
		boolean other = false;
		for (int i = 1; i < this.deg.length; i++) {
			if (this.deg[i] != 0)
				other = true;

		}
		if (one && !other)
			return true;
		else
			return false;
	}

	public String toString() {
		String s = "";
		for (int i = deg.length - 1; i >= 0; i--) {
			if (deg[i] == 1) {
				if (i == 1 && this.deg[i] == 1)
					s += "X+";
				else if (i == 0 && this.deg[i] == 1)
					s += "1+";
				else
					s += "X^" + i + "+";
			}
		}
		if (s.length() == 0)
			return "0";
		return s.substring(0, s.length() - 1);
	}

	public static Polynomial Add(String s) {
		Polynomial temp = new Polynomial(0, 0);
		String arr[] = s.split("\\+");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("1"))
				temp = temp.plus(new Polynomial(0, 1));
			else if (arr[i].equals("x")) {
				temp = temp.plus(new Polynomial(1, 1));

			} else
				temp = temp.plus(new Polynomial(Integer.parseInt(arr[i].charAt(2) + ""), 1));

		}

		return temp;
	}

	public boolean isZero() {
		for (int j = 0; j < this.deg.length; j++) {
			if (this.deg[j] != 0)
				return false;
		}

		return true;
	}
        	public static String findInverse(String q, String s , Polynomial A1 , Polynomial A2 , Polynomial B1 , Polynomial B2 ) {
                    
                    if(B2.isOne() || B2.isZero()){
                    if (B2.isOne())
			return B1.toString();
                    return "no inverse for the input equation";
                    } 
                    
                    
      
                    Polynomial arr[] = A2.divides(B2);
			Polynomial temp = new Polynomial(B2);
			B2 = new Polynomial(arr[1]);
			A2 = new Polynomial(temp);
			temp = new Polynomial(B1);
			B1 = new Polynomial(A1.minus(B1.times(arr[0])));
			A1 = new Polynomial(temp);
                 return findInverse(q,s,A1,A2,B1,B2);
                    
                    
                }

        
        
    
}
