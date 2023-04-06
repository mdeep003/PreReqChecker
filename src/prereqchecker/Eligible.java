package prereqchecker;

import java.util.*;

public class Eligible {
    public static void main(String[] args) {
        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
 
        StdIn.setFile(args[0]);
        int numC = StdIn.readInt();

        StdOut.setFile(args[2]);

        HashMap<String,ArrayList<String>> mapCourse = new HashMap<>();
        HashMap<String,Boolean> markS = new HashMap<>();
        HashingPreReq holdHashMaps = new HashingPreReq(mapCourse,markS);


        for(int i = 0; i < numC;i++)
        {
            String name = StdIn.readString();
            holdHashMaps.inputC(name,mapCourse,markS);

        }


        int numsCourses = StdIn.readInt();


        for(int i = 0; i < numsCourses;i++)
        {
            String courseString = StdIn.readString();
            String preReqString = StdIn.readString();

            holdHashMaps.addingpre(courseString,preReqString,mapCourse);

        }


        StdIn.setFile(args[1]);


        HashSet<String> setCourse = new HashSet<>();
        HashSet<String> eligible = new HashSet<>();


        int numofcourse = StdIn.readInt();
        HashingPreReq hashingPreReq = new HashingPreReq(mapCourse, markS);
       
        for(int i = 0; i < numofcourse;i++)
        {

            String c = StdIn.readString();
            hashingPreReq.allPreReqs(c, setCourse, mapCourse);

  
        }
       
        Boolean trueEligi = true;
        for( String a : mapCourse.keySet())
        {
            trueEligi = true;
            if(!setCourse.contains(a))
            {
                for(String prereq: mapCourse.get(a))
                {
                    if(!setCourse.contains(prereq))
                    {
                        trueEligi = false;
                    }
                }
            }
           
            if(trueEligi && !setCourse.contains(a))
            {
                eligible.add(a);
  
            }
           
        }
        for(String course: eligible)
        {
            StdOut.println(course);
        }
  
    }
}