package SubstitutionCipher;

public class SubsitutionCipherDemo {

    public static String encryptor(String message){
        StringBuilder finalmessage=new StringBuilder();

        for (int i = 0; i <message.length() ; i++) {
            finalmessage.append((char)((message.charAt(i)+13)%265));
        }
        return finalmessage.toString();
    }

    public static String decryptor(String message){
        StringBuilder finalmessage=new StringBuilder();
        for (int i = 0; i <message.length() ; i++) {
            finalmessage.append((char)((message.charAt(i)-13)%265));
        }
        return finalmessage.toString();
    }


    public static void main(String args[]){
        String message="Yo How you doin?",encrypted,decrypted;
        encrypted=encryptor(message);
        decrypted=decryptor(encrypted);
        System.out.println("Encrypted Message: "+encrypted);
        System.out.println("Decrypted Message: "+decrypted);


    }
















}
