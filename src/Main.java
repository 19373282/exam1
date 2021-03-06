import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {

    static String[] rwtab = new String[] { "if", "else", "while", "break", "return", "continue", };
    static String[] idtab = new String[] { "Lt", "Gt", "Mult", "Eq", "Assign", "Div","Plus","Semicolon","LPar","RPar","LBrace", "RBrace" };
    static String storage = "";
    static StringBuilder token = new StringBuilder("");

    static char ch;
    static int index;
    static int Snum, row;
    static String Ident;
    static String sum;
    static boolean isNum = false, isLetter = false;
    static boolean flag=true;

    static void analyzer() {
        isNum=false ;
        isLetter=false;
        token.delete(0, token.length());
        if(index==storage.length()){
            flag=false;
            return;
        }
        ch = storage.charAt(index);
        index++;
        while (ch == ' ') {
            if(index==storage.length()){
                flag=false;
                break;
            }
            ch = storage.charAt(index);
            index++;
        }

        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')|| (ch=='_')) {
            while ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')|| (ch=='_')) {
               // if(isNum==true&&((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')|| (ch=='_'))){
                //    break;
             //   }
               // if((ch >= '0' && ch <= '9')){
                //    isNum=true;
                //    isLetter=false;
               // }
                token.append(ch);
                if(index==storage.length()){
                    flag=false;
                    break;
                }
                ch = storage.charAt(index);
                index++;
            }
            index--;
            Snum = 25;
            String s = token.toString();
            for (int i = 0; i < rwtab.length; i++) {
                if (s.equals(rwtab[i])) {
                    Snum = i + 1;
                    Ident = rwtab[i];
                    char[] cs = Ident.toCharArray();
                    cs[0] -= 32;
                    Ident = String.valueOf(cs);
                    break;
                }
            }
        } else if ((ch >= '0' && ch <= '9')) {
            sum = "";
            while ((ch >= '0' && ch <= '9')) {
                sum = sum + ch;
                 if(index==storage.length()){
                    flag=false;
                    break;
                }
                ch = storage.charAt(index);
                index++;
            }
            index--;
            Snum = 26;
        } else
            switch (ch) {

                case '<':
                    token.append(ch);
                    ch = storage.charAt(index++);
                    Ident = "Lt";
                    Snum = 33;
                    index--;
                    break;
                case '>':
                    token.append(ch);
                    ch = storage.charAt(index++);
                    Ident = "Gt";
                    Snum = 36;
                    index--;

                    break;
                case '*':
                    token.append(ch);
                    ch = storage.charAt(index++);
                    Ident = "Mult";
                    Snum = 13;
                    index--;
                    break;
                case '=':
                    token.append(ch);
                    ch = storage.charAt(index++);
                    if (ch == '=') {
                        Ident = "Eq";
                        Snum = 32;
                        token.append(ch);
                    } else {
                        Ident = "Assign";
                        Snum = 38;
                        index--;
                    }
                    break;
                case '/':
                    token.append(ch);
                    ch = storage.charAt(index++);
                    Ident = "Div";
                    Snum = 30;
                    index--;

                    break;
                case '+':
                    Ident = "Plus";
                    Snum = 27;
                    token.append(ch);
                    break;
                case ';':
                    Ident = "Semicolon";
                    Snum = 41;
                    token.append(ch);
                    break;

                case '(':
                    Ident = "LPar";
                    Snum = 42;
                    token.append(ch);
                    break;
                case ')':
                    Ident = "RPar";
                    Snum = 43;
                    token.append(ch);
                    break;
                case '\n':
                    Snum = -2;
                    token.append(ch);
                    break;
                case '{':
                    Ident = "LBrace";
                    Snum = 91;
                    token.append(ch);
                    break;
                case '\t':
                Snum = 100;
                break;
                case '}':
                    Ident = "RBrace";
                    Snum = 92;
                    token.append(ch);
                    break;

                default:
                    Snum = -1;
            }
    }

    public static void main(String[] args) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        Vector<String> output = new Vector<String>();
        index = 0;
        row = 1;
        String tempString;
        try {

            while ((tempString = stdin.readLine()) != null) {

                storage += " "+tempString;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        index = 0;
        do {
            analyzer();
         //   if(flag==false)
          //  break;
            switch (Snum) {
                case 26:
                    output.add("Number(" + sum + ")");
                    break;
                case -1:
                    output.add("Err");
                    Snum = 0;
                    break;
                case -2:
                    break;
                case 100:
                    break;
                case 25:
                output.add("Ident(" + token + ")");
                    break;
                default:
                output.add(Ident);
                }
        } while (Snum != 0&&flag==true);
        String temp = "";
        temp=output.lastElement();
        for(String a : idtab){
            if(a.equals(temp)){
                output.removeElementAt(output.size()-1);
            }
        }
        for(String a : output){
            System.out.println(a);
        }
    }
  

  
}
