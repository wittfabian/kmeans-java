package kmeans;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to generate the confusion matrix for one run (average over 100 xval runs).
 * @author Fabian Witt, Steven Brandt
 */
public class Matrix {
    
    // String[0] = target value, String[1] = calculated value 
    private final ArrayList<String[]> m_data;
    private final ArrayList<String> m_classes;
    private final int[][] m_matrix;
    private final int m_xval;
    
    
    /**
     * initialised the classified data, the classes and the confusion matrix
     *
     * @param data ArrayList with target-values and calculated-values
     * @param xval Number of XVAL runs
     */
    public Matrix(ArrayList<String[]> data, int xval){
        
        m_data = data;
        m_classes = collectClasses();
        m_xval = xval;
        m_matrix = new int[ m_classes.size() ][ m_classes.size() ];
        
        calculate();
    }

    /**
     * returns the confusion matrix
     *
     * @return m_matrix
     */
    public int[][] getMatrix() {
        return m_matrix;
    }
    
    /**
     * collect all classes
     *
     * @return ArrayList with classes
     */
    private ArrayList<String> collectClasses(){
    
        ArrayList<String> result = new ArrayList<>();
        
        Iterator<String[]> iter = m_data.iterator();
        
        while(iter.hasNext()){
            String[] aktIter = iter.next();
            
            for(int i = 0; i < aktIter.length; i++){
                
                if(!result.contains(aktIter[i]))
                    result.add(aktIter[i]);
            }
        }
        
        return result;
    }
    
    /**
     * calculate the confusion matrix
     * 
     */
    private void calculate(){
        
        Iterator<String[]> iter = m_data.iterator();
        
        while(iter.hasNext()){
            String[] aktIter = iter.next();
            
            m_matrix[ m_classes.indexOf(aktIter[0]) ][ m_classes.indexOf(aktIter[1]) ]++;
        } 
    }
    
    /**
     * print only percentage of correct classifications
     * 
     */
    public void printColsolePerc(){
        
        int m_correctClass = 0;
        Iterator<String> iter = m_classes.iterator(); 
        for(int i = 0; i < m_matrix.length; i++){
            for(int j = 0; j < m_matrix[i].length; j++){
                if(i == j)
                    m_correctClass += m_matrix[i][j];
            }
        }
        System.out.println("correct classified: " + Math.round((double)m_correctClass / m_data.size()*10000.0)/100.0 + "%");
    }
    
    /**
     * print the confusion matrix
     * 
     */
    public void printColsole(){
        
        int m_correctClass = 0;
        System.out.print("matrix;");
        Iterator<String> iter = m_classes.iterator();
        while(iter.hasNext()){
            String aktIter = iter.next();
            System.out.print(aktIter + ";");
        } 
        System.out.print("\n");
        for(int i = 0; i < m_matrix.length; i++){
            System.out.print(m_classes.get(i) + ";");
            for(int j = 0; j < m_matrix[i].length; j++){
                if(i == j)
                    m_correctClass += m_matrix[i][j];
                System.out.print(Math.round(m_matrix[i][j]/m_xval) + ";");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.println("correct classified: " + Math.round((double)m_correctClass / m_data.size()*10000.0)/100.0 + "%");
    }
    
 
    
    /**
     * print the confusion matrix in a html file
     * 
     * @param outfile output-File for the matrix
     */
    public void writeHtmlFile(String outfile){
        
        try (PrintWriter writer = new PrintWriter(outfile, "UTF-8")) {
            int m_correctClass = 0;
            
            writer.print("<html>\n<head></head>\n<body>\n"); 
            writer.print("<table>\n<tr>\n");
            writer.print("<td></td>");
            
            Iterator<String> iter = m_classes.iterator();
            while(iter.hasNext()){
                String aktIter = iter.next();
                writer.print("<td><b>" + aktIter + "</b></td>");
            } 
            writer.print("\n</tr>\n");
            for(int i = 0; i < m_matrix.length; i++){
                writer.print("<tr>");
                writer.print("<td><b>" + m_classes.get(i) + "</b></td>");
                for(int j = 0; j < m_matrix[i].length; j++){
                    if(i == j)
                        m_correctClass += m_matrix[i][j];
                    writer.print("<td align='right'>" + Math.round(m_matrix[i][j]/m_xval) + "</td>");
                }
                writer.print("\n</tr>\n");
            }
            writer.print("</table>\n");
            writer.printf("<br>");
            writer.println("<b>correct classified:</b> " + Math.round((double)m_correctClass / m_data.size()*10000.0)/100.0 + "%");
            writer.print("</body>");
            System.out.println("File saved.");
        } catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        } catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException");
        }
    }
}
