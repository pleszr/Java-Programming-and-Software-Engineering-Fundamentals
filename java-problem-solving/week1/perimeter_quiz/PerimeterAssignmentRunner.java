import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        //AVG Length is the perimeter divided by the nr. of points
        double perimeter = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double doubleNumPoints = (double) numPoints;
        return perimeter / doubleNumPoints;
    }

    public double getLargestSide(Shape s) {
        //length of longest side
        Point prevPt = s.getLastPoint();
        double maxDist = 0.0;
        
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > maxDist) {
                maxDist = currDist;
            }
            prevPt = currPt;
        }
        return maxDist;
    }

    public double getLargestX(Shape s) {
        //highest X value
        Point prevPt = s.getLastPoint();
        double largestX = 0.0;
        
        
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        String largestPerimeterFilename = "";
        for (File f : dr.selectedFiles()) {
            FileResource fa = new FileResource(f);
            Shape s = new Shape(fa);    
            double currentPerimeter = getPerimeter(s);
            if ( currentPerimeter > largestPerimeter ) {
                largestPerimeter = currentPerimeter;
                largestPerimeterFilename = f.getName();
            }
        }
        System.out.println("Largest perimeter of all: " + largestPerimeter + " in " + largestPerimeterFilename ) ;
        System.out.println(largestPerimeterFilename) ;

        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testEverything () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("-------NEW RUN-------");
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numPoints = getNumPoints(s);
        System.out.println("Number of points: = " + numPoints);
        
        double avgLength = getAverageLength(s);
        System.out.println("Average length: = " + avgLength);
        
        double maxDist = getLargestSide(s);
        System.out.println("Longest side: = " + maxDist);
        
        double largestX = getLargestX(s);
        System.out.println("Largest X: = " + largestX);
        
        
        
        
    }    
    
    public void testNumpoints () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int numPoints = getNumPoints(s);
        System.out.println("Number of points: = " + numPoints);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testEverything();
        pr.getLargestPerimeterMultipleFiles();
        
    }
}
