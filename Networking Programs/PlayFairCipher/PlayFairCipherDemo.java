package PlayFairCipher;

import java.util.HashMap;
import java.util.StringTokenizer;

public class PlayFairCipherDemo {
static String[] alphabets={"A","B","C","D","E","F","G","H","I","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

public static String encryptor(String message,String[][]key){
        String finalmessage="", temp="";
    for (int i = 0; i <message.length() ; i++) {
        if(message.charAt(i)!=' ')
            temp+=String.valueOf(message.charAt(i));
    }
    message=temp;
        if(message.length()%2==1)
            message+="X";

    int z=0;
        //same row
    while (z<message.length()) {
        String first,second,efirst,esecond;
        int firstrow=-1,firstcol=-1;
        int secondrow=-1,secondcol=-1;
        first=String.valueOf(message.charAt(z));
        z++;
//        System.out.println("First  " +first);
        second=String.valueOf(message.charAt(z));
//        System.out.println("Second  " +second);
        z++;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
               // System.out.println("key[i][j].toUpperCase() "+key[i][j].toUpperCase()+"    irst.toUpperCase("+first.toUpperCase());
                if(key[i][j].toUpperCase().equals(first.toUpperCase())){
                    firstrow=i;firstcol=j;
//                    System.out.println("FOUND ");
                    break;

                }
            }
            if(firstrow!=-1)
                break;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(key[i][j].toUpperCase().equals(second.toUpperCase())){
                    secondrow=i;secondcol=j;
                 //   System.out.println("FOUND second");
                    break;
                }
            }
            if(secondrow!=-1)
                break;
        }
        //same row
        if(firstrow==secondrow){
            efirst=key[firstrow][(firstcol+1)%5].toUpperCase();
            esecond=key[secondrow][(secondcol+1)%5].toUpperCase();
        }
        else if(firstcol==secondcol){
            efirst=key[(firstrow+1)%5][firstcol].toUpperCase();
            esecond=key[(secondrow+1)%5][secondcol].toUpperCase();
        }
        else{
            //same row opposite corner
            efirst=key[firstrow][secondcol].toUpperCase();
            esecond=key[secondrow][firstcol].toUpperCase();
        }
        finalmessage+=efirst+esecond;

    }
        return finalmessage;
    }
    public static String decryptor(String message,String[][]key){
        String finalmessage="", temp="";
        for (int i = 0; i <message.length() ; i++) {
            if(message.charAt(i)!=' ')
                temp+=String.valueOf(message.charAt(i));
        }
        message=temp;
        if(message.length()%2==1)
            message+="X";

        int z=0;
        //same row
        while (z<message.length()) {
            String first,second,efirst,esecond;
            int firstrow=-1,firstcol=-1;
            int secondrow=-1,secondcol=-1;
            first=String.valueOf(message.charAt(z));
            z++;
//            System.out.println("First  " +first);
            second=String.valueOf(message.charAt(z));
//            System.out.println("Second  " +second);
            z++;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    // System.out.println("key[i][j].toUpperCase() "+key[i][j].toUpperCase()+"    irst.toUpperCase("+first.toUpperCase());
                    if(key[i][j].toUpperCase().equals(first.toUpperCase())){
                        firstrow=i;firstcol=j;
//                        System.out.println("FOUND ");
                        break;

                    }
                }
                if(firstrow!=-1)
                    break;
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(key[i][j].toUpperCase().equals(second.toUpperCase())){
                        secondrow=i;secondcol=j;
//                        System.out.println("FOUND second");
                        break;
                    }
                }
                if(secondrow!=-1)
                    break;
            }
            //same row
            if(firstrow==secondrow){
                efirst=key[firstrow][(firstcol-1)%5].toUpperCase();
                esecond=key[secondrow][(secondcol-1)%5].toUpperCase();
            }
            else if(firstcol==secondcol){
                efirst=key[(firstrow-1)%5][firstcol].toUpperCase();
                esecond=key[(secondrow-1)%5][secondcol].toUpperCase();
            }
            else{
                //same row opposite corner
                efirst=key[firstrow][secondcol].toUpperCase();
                esecond=key[secondrow][firstcol].toUpperCase();
            }
            finalmessage+=efirst+esecond;

        }
        return finalmessage;
    }
public static void keyGenerator(String[][]finalkey,String key){

    HashMap<String,Boolean>insertedalphabets=new HashMap<String,Boolean>();
    int c=0,a=0;
    for (int i = 0; i <5 ; i++) {
        for (int j = 0; j <5 ; j++) {
            while (c<key.length()){
                if(!insertedalphabets.containsKey(String.valueOf(key.charAt(c)).toUpperCase()))
                    break;
            c++;
            }
            if(c<key.length()) {
                finalkey[i][j] = String.valueOf(key.charAt(c)).toUpperCase();
                insertedalphabets.put(String.valueOf(key.charAt(c)).toUpperCase(), true);
            }
            else{
                while (a<alphabets.length){
                    if(!insertedalphabets.containsKey(alphabets[a]))
                        break;
                a++;
                }

                finalkey[i][j]=String.valueOf(alphabets[a]).toUpperCase();
                insertedalphabets.put(alphabets[a].toUpperCase(),true);
            }
        }
    }


}
    public static void main(String args[]){
        String[][]finalkey=new String[5][5];
        keyGenerator(finalkey,"playfairexample");
        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(finalkey[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Encrypted Message "+encryptor("Hide the gold in the tree stump",finalkey));
        System.out.println("decrypted Message "+decryptor(encryptor("Hide the gold in the tree stump",finalkey),finalkey));
    }

}
