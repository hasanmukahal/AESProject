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
public class AESproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String b="x^7+x+1";
        String m="x^8+x^4+x^3+x+1";
        	Polynomial A1 = new Polynomial(0, 0);
		Polynomial A2 = Polynomial.Add(m);
		Polynomial B1 = new Polynomial(0, 1);
		Polynomial B2 = Polynomial.Add(b);
        System.out.println(Polynomial.findInverse(b,m,A1,A2,B1,B2));
        
    }
    
}
