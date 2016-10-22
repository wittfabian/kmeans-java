package kmeans;

import mapping.Overlap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class uses the training data to classify the test data with the Naive
 * Bayes classification.
 * @author Fabian Witt, Steven Brandt
 */
public class Classify {
    
    private final ArrayList<String[]> m_trainData;
    private final ArrayList<String[]> m_testData;
    private final ArrayList<String[]> m_result;
    private final int m_k;

    /**
     * Consturctor to get the data and automatically classify the test data.
     * @param trainData The training data.
     * @param testData The test data.
     * @param k The number of centers.
     */
    public Classify(ArrayList<String[]> trainData, ArrayList<String[]> testData, int k) {
        this.m_trainData = trainData;
        this.m_testData = testData;
        this.m_k = k;
        this.m_result = makeClassification();
    }

    /**
     * Getter, which returns the result of the classification.
     * @return Returns a list with the target and the classification values.
     */
    public ArrayList<String[]> getResult() {
        return m_result;
    }
    
     /**
     * Method to classify the test data.
     * @return Returns a list of all target classes and the classifications.
     */
    private ArrayList<String[]> makeClassification(){
        ArrayList<String[]> result = new ArrayList<>();
        int classpos = m_trainData.get(0).length-1;
        Overlap overlap = new Overlap(m_trainData,m_k);
        
        // iterate through testdata and classify each one
        Iterator iter_test = m_testData.iterator();
        while(iter_test.hasNext()){
            String[] tmp = (String[])iter_test.next();
            String clclass = overlap.getClass(tmp);
            
            String[] resstring = new String[2];
            resstring[0] = tmp[classpos];
            resstring[1] = clclass;
            result.add(resstring);
        }
        return result;
    }
}
