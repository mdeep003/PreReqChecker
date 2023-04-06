package prereqchecker;
import java.util.*;
public class HashingPreReq {
    
    private HashMap<String, ArrayList<String>> preReq;
    private HashMap<String, Boolean> mark; 
    

    public HashingPreReq(HashMap<String, ArrayList<String>> preReq)
    {
        this.preReq = preReq; 
    }

    public HashingPreReq(HashMap<String, ArrayList<String>> preReq, HashMap<String, Boolean> mark)
    {
        this.preReq = preReq; 
        this.mark = mark; 
    }

   

    public void addingpre(String id, String pq, HashMap<String, ArrayList<String>> hash)
    {
        ArrayList<String> arrayList = hash.get(id);
        arrayList.add(pq);
    }

    public void inputC(String id, HashMap<String, ArrayList<String>> course, HashMap<String, Boolean> mark)
    {
        course.put(id, new ArrayList<String>());
        mark.put(id, false); 
    }

    

    public String validpq(String id, String preR, HashMap<String, Boolean> mark)
    {
        HashSet<String> please = new HashSet<>();
        DFS(preR, mark, please); 
        if(please.contains(id) || preR.equals(id))
        {
            return "NO";
        }
        else
        {
            return "YES"; 
        }
    }

    public void inputsC(String id, HashMap<String, ArrayList<String>> course)
    {
        course.put(id, new ArrayList<String>()); 
    }

    public HashSet<String> needCourse(String target,HashMap<String, ArrayList<String>> courseMap, HashSet<String> neededCourses,
           HashSet<String> courseSet){
       for (String s : courseMap.get(target))
        {
           if (!courseSet.contains(s)) 
           {
                neededCourses.add(s);
                needCourse(s, courseMap, neededCourses, courseSet);
           }
       }
       return neededCourses;
   }
   public void allPreReqs(String course, HashSet<String> courseSet, HashMap<String, ArrayList<String>> courseMap)
   {
       courseSet.add(course);
       for(String preReq: courseMap.get(course))
       {
           if(!courseSet.contains(preReq))
           {
               allPreReqs(preReq, courseSet, courseMap);
           }
       }
   }

    public void DFS(String courseID, HashMap<String,Boolean> marked, HashSet<String> p)
    {
       for(String u : preReq.get(courseID))
       {
           if(marked.get(u)!=true)
           {
               p.add(u);
               DFS(u, marked, p);
           }
       }   
       mark.put(courseID,true);    
       
    }

    
  public HashMap<Integer, HashSet<String>> webreg(String course, HashSet<String> courseSet, HashingPreReq hashMaps,
   HashMap<String, ArrayList<String>> courseMap, HashSet<String> mustTake,HashSet<String> setOfCourses)
   {
    HashSet<String> needCo = hashMaps.needCourse(course, courseMap, mustTake, setOfCourses);

    HashMap<Integer, HashSet<String>> schedule = new HashMap<>();
    int count = 1;
    HashSet<String> holdCourses = new HashSet<>();
    while(!needCo.isEmpty())
    {
       
        for (String nc : needCo) 
        {


            HashSet<String> allPQ = new HashSet<>();
           
            HashSet<String> mores = new HashSet<>();
            HashSet<String> mores2 = new HashSet<>();
           
            hashMaps.allPreReqs(course, mores, courseMap);
            HashSet<String> nCPreReq = hashMaps.needCourse(nc, courseMap, mores2, setOfCourses);
            allPQ.addAll(nCPreReq);

            if(allPQ.isEmpty())
            {
                holdCourses.add(nc);
            }

            else if(courseMap.get(nc).size()>0)
            {
                for (String s: courseMap.get(nc)) 
                {
                    if(mark.get(s)==true){
                         holdCourses.add(nc);
                    
                    }
                    else
                    {
                        break;
                    }
                }
            }
           
        }
        if(!holdCourses.isEmpty())
        {
            HashSet<String> set2 = new HashSet<>();
            for (String s : holdCourses) 
            {
                 mark.put(s, true);
            }


            set2.addAll(holdCourses);
            schedule.put(count, set2);
            count++;
            needCo.removeAll(set2);
            courseSet.addAll(set2);
            holdCourses.clear();
           
        }
    }
   
    return schedule;
}
   
    
    
    
}