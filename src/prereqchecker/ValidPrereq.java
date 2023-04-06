package prereqchecker;
import java.util.*; 
import java.util.HashMap;


public class ValidPrereq {
    
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }


        StdIn.setFile(args[0]);
        StdOut.setFile(args[2]);
        
        HashMap<String, ArrayList<String>> iding = new HashMap<>();
        HashMap<String, Boolean> markings = new HashMap<>();
        HashingPreReq idnew = new HashingPreReq(iding);


        int first = StdIn.readInt(); 

        for(int i = 0; i < first; i++)
        {
            String second = StdIn.readString(); 
            idnew.inputC(second, iding, markings); 
        }

        int third = StdIn.readInt(); 

        int four = 0;
        
        while(four < third)
        {
            
            String keyString = StdIn.readString(); 
            String preString = StdIn.readString();
            idnew.addingpre(keyString, preString, iding);
            four++;
        }

       
        //validpq
        StdIn.setFile(args[1]);
        String count1 = StdIn.readString();
        String count2 = StdIn.readString();
        HashingPreReq h2 = new HashingPreReq(iding, markings);
        StdOut.println(h2.validpq(count1, count2, markings));
    }
}