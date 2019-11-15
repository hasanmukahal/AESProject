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
        System.out.println(SubstituteByteTransformation('9','5'));
    }
   
    public static String findInverse(String b){
            String m="x^8+x^4+x^3+x+1";

    Polynomial A1 = new Polynomial(-1);
		Polynomial A2 = Add(m);
		Polynomial B1 = new Polynomial(0);
		Polynomial B2 = Add(b);
               
return Polynomial.findpolyInverse(A1,A2,B1,B2).toString();
    
    
    
    }
    public static String hexatoBinary(char a){
    
  
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
           return  "0100"; 
        
        case '5': 
          return "0101"; 
         
        case '6': 
           return "0110"; 
            
        case '7': 
          return "0111"; 
           
        case '8': 
          return  "1000"; 
            
        case '9': 
        return "1001"; 
          
        case 'A': 
        case 'a': 
           return "1010"; 
           
        case 'B': 
        case 'b': 
            return"1011"; 
           
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
           return "\nInvalid hexadecimal digit " ;
                 
       
    } 
    }
    public static String hexa1bytetoBinary(char a , char b){
    return hexatoBinary(a)+hexatoBinary(b);
    
    
    }
      public static String BinaryToHexa(String binary){
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
  String hexatext="";
    for(int q=1;q>=0;q--){
    switch(hex[q]){
        case  10 :
        hexatext+="A";
        break;
        case 11 :
         hexatext+="B";
         case  12 :
        hexatext+="C";
        break;
        case  13 :
        hexatext+="D";
        break;
        case  14 :
        hexatext+="E";
        break;
        case  15 :
        hexatext+="F";
        break;
        default:
              hexatext+=hex[q];

    
    }
  
    }
    return hexatext;
    }
      public static String BinarytoPoly(String bn){
      String ret="";
      for(int i=0;i<bn.length();i++){
          if(i==bn.length()-1 && bn.charAt(i)=='1')
              ret+="1+";
     else if(bn.charAt(i)!='0')
          ret+="x^"+(bn.length()-1-i)+"+";
          
      
      }
     return ret.substring(0, ret.length() - 1);
      
      }
      public static String polytoBinary(String poly){
        poly=  poly.replace('X', 'x');
      char[]b={'0','0','0','0','0','0','0','0'};
      if(poly.contains("x^7"))
          b[0]='1';
       if(poly.contains("x^6"))
          b[1]='1';
        if(poly.contains("x^5"))
          b[2]='1';
         if(poly.contains("x^4"))
          b[3]='1';
           if(poly.contains("x^3"))
          b[4]='1';
            if(poly.contains("x^2"))
          b[5]='1';
             if(poly.contains("x^1"))
          b[6]='1';
              if(poly.contains("x"))
          b[6]='1';
               if(poly.contains("+1"))
          b[7]='1';
             
      
      return String.valueOf(b);
      
      
      }
      public static String getSubbytefrombinary(String bin){
     
         String c="01100011";
         char[] b=new char[8];
         for(int i=7;i>=0;i--){
             int  x= invsermodulo(i-4);
             int  y= invsermodulo(i-5);
             int  z= invsermodulo(i-6);
             int  f= invsermodulo(i-7);
             
            
                 
            
         b[i]=xor( xor (xor( xor (xor(bin.charAt(i) , bin.charAt(x) ) , bin.charAt(y)  ) , bin.charAt(z)  ),bin.charAt(f)),c.charAt(i));
         
         }
          System.out.println(String.copyValueOf(b));
         return BinaryToHexa(String.copyValueOf(b));
      
      }
      public static String SubstituteByteTransformation(char a , char b){
         String bn=hexa1bytetoBinary(a, b);
         String poly= BinarytoPoly(bn);
       String polyinverse= findInverse(poly);
       String bninverse=polytoBinary(polyinverse);
       
 
      return getSubbytefrombinary(bninverse);
      
          
      }
      public static int invsermodulo(int i){
      
      if(i<0)
          return i+8;
                  return i;
      }
      
      
      public static char xor(char a , char b){
      if(a==b)
          return '0';
      return '1';
      
      }
     
}
