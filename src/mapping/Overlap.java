package mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class to map the continious values and get the class with kmeans.
 * @author Steven Brandt, Fabian Witt
 */
public class Overlap {
 
    private final ArrayList<String[]> m_trainData;
    private final int m_k;
    ArrayList<String[]> m_centers;
    
    /**
     * Constructor to fill the attributes.
     * @param m_trainData Training data.
     * @param m_k Number of centers.
     */
    public Overlap(ArrayList<String[]> m_trainData, int m_k) {
        this.m_trainData = m_trainData;
        this.m_k = m_k;
        m_centers = trainlloyd();
        
    }
    
    private ArrayList<String[]> trainlloyd(){
        
        // initialize the centers with random examples
        ArrayList<String[]> initcenter = m_trainData;
        ArrayList<String[]> centers = new ArrayList<>();
        for(int i = 0; i<m_k; i++){
            int rnd = myRandom(0, initcenter.size());
            centers.add(initcenter.get(rnd));
            initcenter.remove(rnd);
        }
        
        // classify data to centers
        ArrayList<String> train_centers = new ArrayList<>();
        //iterate through examples
        Iterator iter_train = m_trainData.iterator();
        while(iter_train.hasNext()){
            String[] tmp_ex = (String[])iter_train.next();
            //iterate through centers
            Iterator iter_centers = centers.iterator();
            HashMap<String,Double> distances = new HashMap<>();
            while(iter_centers.hasNext()){
                int i = 0;
                String[] tmp_cen = (String[])iter_centers.next();
                double dist = getDistance(tmp_ex, tmp_cen);
                String c = "c";
                distances.put((c+=Integer.toString(i)), dist);  
            }
            //get closest center
            Iterator iter_dist = distances.entrySet().iterator();
            Map.Entry pairs = (Map.Entry)iter_dist.next();
            double d_smallest  = (Double)pairs.getValue();
            String s_smallest = (String)pairs.getKey();
            while(iter_dist.hasNext()){
                pairs = (Map.Entry)iter_dist.next();
                if( ((Double)pairs.getValue()) <= d_smallest ){
                    d_smallest = (Double)pairs.getValue();
                    s_smallest = (String)pairs.getKey();
                }
            }
            train_centers.add(s_smallest);   
        }
        
        //update centers
        ArrayList<String[]> newcenters = new ArrayList<>();
        //iterate through old centers
        for(int j = 0; j<centers.size();j++){
            String[] c_tmp = centers.get(j);
            double[] cnew = new double[c_tmp.length];
            int anz = 0;
            //iterate through examples & their centers
            for(int i = 0; i<m_trainData.size();i++){
                String[] ex_tmp = m_trainData.get(i);
                String c = "c";
                c+=j;
                if(train_centers.get(i).equals(c)){
                    //itereate through atts
                    anz++;
                    for(int k = 0;k<ex_tmp.length-1;k++){
                        if(c_tmp.equals(ex_tmp)){
                            cnew[k] += 1.0;
                        }
                        else{
                            cnew[k] += 0.0;
                        }
                    }
                }
                else{
                }
            }
        }
        
        return null;
    }
        
    private double getDistance(String[] ex1, String[] ex2){
        double attanz = ex1.length-1;
        double[] sim = new double[ex1.length-1];
        for(int i = 0; i<attanz;i++){
            if(ex1[i].equals(ex2[i])){
                sim[i] = 1;
            }
            else{
                sim[i] = 0;
            }
        }
        
        double dist = 0.0;
        for(int i = 0;i<sim.length;i++){
            dist = dist + ((1/attanz)*sim[i]);
        }        
        return dist;
    }
    
    /**
     * This method calculates the class.
     * @param example An example from the test data.
     * @return Returns the computed class.
     */
    public String getClass(String[] example){

        
//        // get the k nearest classes
//        ArrayList<String> kclasses = new ArrayList<>();
//        for(int i = 0; i<m_k;i++){
//            int index = getMaxValIndex(dist_matrix);
//            kclasses.add(class_matrix.get(index));
//            dist_matrix.remove(index);
//            class_matrix.remove(index);
//        }
//        
//        //get the count of the classes
//        HashMap<String,Integer> anzclasses = new HashMap<>();
//        for(int i = 0; i<kclasses.size();i++){
//            String current = kclasses.get(i);
//            if(!anzclasses.containsKey(current)){
//                anzclasses.put(current, count(kclasses,current));
//            }
//        }
//        
//        //get the most often class
//        Iterator iter_anzclasses = anzclasses.entrySet().iterator();
//        double maxanz = 0;
//        String maxclass = "";
//        while(iter_anzclasses.hasNext()){
//            Map.Entry classpairs = (Map.Entry)iter_anzclasses.next();
//            if((int)classpairs.getValue() >= maxanz){
//                maxanz = (int)classpairs.getValue();
//                maxclass = (String)classpairs.getKey();
//            }
//        }
//        
        return null;
    }
    
    /**
     * Counts how often a value is in a list.
     * @param list The list.
     * @param value The value.
     * @return Returns the number.
     */
    private int count(ArrayList<String> list, String value){
        
        int result = 0;
        
        Iterator<String> iter = list.iterator();
        
        while(iter.hasNext()){
            String aktIter = iter.next();
            
            if(aktIter.equals(value))
                result++;
        }
        
        return result;
    }
    
    
    /**
     * Finds the maximum value in a list.
     * @param list The list with the values.
     * @return Returns the index of the maximum value.
     */
    private int getMaxValIndex(ArrayList<Double> list){
        double maxval = 0;
        int maxindex = 0;
        
        for(int i = 0; i<list.size()-1;i++){
            double tmp = list.get(i);
            if(maxval<tmp){
                maxval = tmp;
                maxindex = i;
            }
        }
        
        return maxindex;
    }
    
    /**
     * Function, that creates random integer values between some limits.
     * @param low The lower bound.
     * @param high The upper bound.
     * @return Returns the random value.
     */
    private static int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}
}
