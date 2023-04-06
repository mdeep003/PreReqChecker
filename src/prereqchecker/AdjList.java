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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 * 
 * You will use this list of prereq connections to construct an adjacency list, then output it to the output file.
    The output file will be formatted as follows:
    a lines, where each line starts with a course ID and a space, 
    then lists all the course’s immediate prerequisites (space separated)
    The order in which you output the lines, as well as the 
    order in which you output the courses in each line after 
    the first DOES NOT MATTER for grading.
 */
public class AdjList {
        public static void main(String[] args) {

            if ( args.length < 2 ) {
                StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
                return;
            }

        StdIn.setFile(args[0]);

        StdOut.setFile(args[1]);
        
        HashMap<String, ArrayList<String>> newMaps = new HashMap<>();

        HashingPreReq forms = new HashingPreReq(newMaps);

        int anew = StdIn.readInt(); 

        for(int i = 0; i < anew; i++)
        {
            String start = StdIn.readString(); 
            forms.inputsC(start, newMaps); 
        }

        int second = StdIn.readInt(); 

        int third = 0;
        while(third < second)
        {
            
            String keyHash = StdIn.readString(); 
            String preHash = StdIn.readString();
            forms.addingpre(keyHash, preHash, newMaps);
            third++;
        }

       
        
        for (String i : newMaps.keySet()) 
        {
            StdOut.print(i);
            for (String j : newMaps.get(i)) 
            {
                StdOut.print(" " + j);
            }
            StdOut.println();
        }
    }
}