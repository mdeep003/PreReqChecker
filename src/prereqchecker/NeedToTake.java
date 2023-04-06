package prereqchecker;

import java.util.*;


public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
 


        StdIn.setFile(args[0]);

        int courseNumber = StdIn.readInt();
        
        StdOut.setFile(args[2]);

        HashMap<String,ArrayList<String>> iding = new HashMap<>();
        HashMap<String,Boolean> marking = new HashMap<>();
        HashingPreReq hashPQ = new HashingPreReq(iding,marking);

        for(int i = 0; i < courseNumber;i++)
        {
            String CN = StdIn.readString();
            hashPQ.inputC(CN,iding,marking);

        }


        int numbers = StdIn.readInt();

        for(int i = 0; i < numbers;i++)
        {
            String courseString = StdIn.readString();
            String preReqString = StdIn.readString();
            hashPQ.addingpre(courseString,preReqString,iding);
        }
        
        StdIn.setFile(args[1]);
        HashSet<String> setting = new HashSet<>();
        HashSet<String> news = new HashSet<>();
        String tarSet = StdIn.readString();
        int count = StdIn.readInt();
        HashingPreReq h2 = new HashingPreReq(iding, marking);

        for(int i = 0; i < count; i++)
        {
            String course = StdIn.readString();
            h2.allPreReqs(course, news, iding);
        }

        HashSet<String> newHashSet2 = h2.needCourse(tarSet, iding, news, setting);
        for(String coursings : newHashSet2)
        {
            StdOut.println(coursings);
        }


    }
}