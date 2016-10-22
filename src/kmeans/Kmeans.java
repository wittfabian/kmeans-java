package kmeans;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fabian Witt, Steven Brandt
 */
public class Kmeans {
    
    private static final String C_SEPERATOR = ",";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length==5){
            
            try {
                String infile = args[0];
                String outfile = args[1];
                int xval = Integer.parseInt(args[2]);
                int kstart = Integer.parseInt(args[3]);
                int kend = Integer.parseInt(args[4]);
                
                Load load = new Load(infile + ".txt", C_SEPERATOR);
                
                //iterate over k
                for(int k = kstart; k<=kend;k++){
                    
                    System.out.println("\nK: " + k);
                    
                    ArrayList<String[]> globalResult = new ArrayList<>();
                    
                    for(int i = 1; i <= xval; i++){
                        
                        Data data = new Data(load.getData(), 66.6666667);
                        
                        ArrayList<String[]> trainData = data.getTrainData();
                        ArrayList<String[]> testData = data.getTestData();
                        
                        Classify classify = new Classify(trainData, testData, k);
                        ArrayList<String[]> result = classify.getResult();
                        
                        globalResult.addAll(result);
                    }
                    
                    
                    Matrix confusionMatrix = new Matrix(globalResult,xval);
                    confusionMatrix.printColsolePerc();
                    //confusionMatrix.printColsole();
                    //confusionMatrix.writeFile(outfile);
                    confusionMatrix.writeHtmlFile(outfile + "_xval=" + xval + "_k=" + k + ".html");
                }
            } catch (IOException ex) {
                System.out.println("[ERROR] Input file not found!");
            }
        }
        else{
            System.out.println("[ERROR] Wrong parameters set. (infile outfile xval kstart kend)");
        }
    }
}
