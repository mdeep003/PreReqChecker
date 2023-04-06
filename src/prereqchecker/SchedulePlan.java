package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }


    StdIn.setFile(args[0]);

    HashMap<String, ArrayList<String>> mapping = Methods.makeAdjList();

    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);

    String tar = StdIn.readString();
    int takes = StdIn.readInt();

    HashSet<String> courseT = new HashSet<>();
    
    for(int i = 0; i<takes; i++)
    {
        String c1 = StdIn.readString();

        courseT = Methods.prereqList(mapping, courseT, c1);
        courseT.add(c1);
    }

    HashSet<String> preT = new HashSet<>();
    preT = Methods.prereqList(mapping, preT, tar);

    HashMap<Integer, ArrayList<String>> semester = new HashMap<>();

    int counter = 0;

    HashSet<String> first = new HashSet<>();
    
    for(String key : preT)
    {
        if(!courseT.contains(key))
        {
            first.add(key);
        }
    }
    
    while(first.size() != 0)
    {
        
      ArrayList<String> list = new ArrayList<String>();
      for(String keyhole : first){
        if(Methods.isEligible(mapping, courseT, keyhole) == true){
            list.add(keyhole);
        }
      }
      for(int a = 0; a<list.size(); a++)
      {
        courseT.add(list.get(a));
      }
      for(int b = 0; b<list.size(); b++)
      {
        
        first.remove(list.get(b));
      }

      semester.put(counter+1,list);
      counter++;
     }

     StdOut.println(counter);
     for(int cou: semester.keySet()){
        
        ArrayList<String> reqs = semester.get(cou);
        for(int i = 0; i<reqs.size(); i++){
            StdOut.print(reqs.get(i) + " ");
        }
        StdOut.println();
    }
    }
}