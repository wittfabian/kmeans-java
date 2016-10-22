package kmeans;

import static java.lang.StrictMath.random;
import java.util.ArrayList;

/**
 *
 * @author Fabian Witt, Steven Brandt
 */
public class Data {
    
    private final ArrayList<String[]> m_data;
    private final ArrayList<String[]> m_trainData;
    private ArrayList<String[]> m_testData;
    private double m_trainPercentage;
    
    /**
     * divides the data-set into training and test data
     *
     * @param data data-set
     * @param trainPercentage percentage of training data
     */
    public Data(ArrayList<String[]> data, double trainPercentage){
        
        m_trainData = new ArrayList<>(); 
        m_testData = new ArrayList<>();
        
        m_data = data;
        m_trainPercentage = trainPercentage;
        
        createData();
    }
    
    /**
     * returns the size of the data-set
     *
     * @return size of m_data
     */
    public int getSize() {
        return m_data.size();
    }
    
    /**
     * returns the ArrayList of training data
     *
     * @return m_trainData
     */
    public ArrayList<String[]> getTrainData() {
        return m_trainData;
    }
    
    /**
     * returns the ArrayList of test data
     *
     * @return m_testData
     */
    public ArrayList<String[]> getTestData() {
        return m_testData;
    }
    
    /**
     * Set the percentage of training data
     *
     * @param m_trainPercentage percentage of training data
     */
    public void setTrainPercentage(double m_trainPercentage) {
        this.m_trainPercentage = m_trainPercentage;
    }
    
    /**
     * Creates ArrayLists with training and test data
     * 
     */
    private void createData(){
        int anzData = (int) (m_data.size() * (m_trainPercentage * 0.01));
        
        ArrayList<String[]> tempData = new ArrayList<>(m_data);
        
        while(m_trainData.size() < anzData){
            
            int random = (int) (random() * (tempData.size() - 0) + 0); 
            
            m_trainData.add(tempData.remove(random));
        }
        
        m_testData = tempData;
    }   
}