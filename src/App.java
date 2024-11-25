import cz.gyarab.util.light.Matrix;
import java.util.Base64;

class CubeFace {
    private int dotCount;
    public Matrix matrix = Matrix.createMatrix(3, 3);
    String numData = "1-1,1;2-2,0:0,2;3-0,2:1,1:2,0;4-0,0:0,2:2,0:2,2;5-0,0:0,2:1,1:0,2:2,0;6-0,0:0,1:0,0:2,0:2,1:2,2"; // count1-x1,y1;count2-x1,y1,x2,y2; and on and on...
    
    public CubeFace(int number) {
        this.dotCount = number;
    }

    public int getDotCount() {
        return this.dotCount;
    }

    public void setDotCount(int newDotCount) {
        this.dotCount = newDotCount;
    }

    String[] getCoordData(int number) {
        String numberCoords[] = this.numData.split(";");
        
        for (int i = 0; i < numberCoords.length; i++) {
            int dataId = Integer.parseInt(numberCoords[i].split("-")[0]);
            
            if (dataId == number) {
                return numberCoords[i].split("-")[1].split(":");
            } else {
                continue;
            }
        }

        return new String[0];
    }

    void DrawMatrix() {
        String coordData[] = this.getCoordData(this.dotCount);
        this.matrix.showWindow();

        for (int i = 0; i < coordData.length; i++) {
            System.out.println(coordData[i]);
        }
    }
}


public class App {
    public static void main(String[] args) throws Exception {
        CubeFace cb = new CubeFace(4);
        cb.DrawMatrix();
    }
}
