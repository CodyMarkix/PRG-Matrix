import cz.gyarab.util.light.Matrix;

class CubeFace {
    private int dotCount;
    public Matrix matrix = Matrix.createMatrix(3, 3);
    
    // Dle mého názoru je tohle lepší než hardcodenout masivní switch statement
    String numData = "1-1,1;2-2,0:0,2;3-0,2:1,1:2,0;4-0,0:0,2:2,0:2,2;5-0,0:2,0:1,1:0,2:2,2;6-0,0:0,1:0,2:2,0:2,1:2,2";
    /*
     * Každý set souřadnic pro jedno číslo je rozdělen středníkem.
     * V samotném setu je potom číslo, které náelží tomuto setu a samotný set dat, rozděleny pomlčkou.
     * Každá souřadnice je potom rozdělena dvojtečkou
     * 
     * PŘÍKLAD JEDNOHO SETU:
     * 4-
     *  0,0:
     *  0,2:
     *  2,0:
     *  2,2
     */
    
    public CubeFace(int number) {
        this.dotCount = number;
    }

    public int getNumber() {
        return this.dotCount;
    }

    public void setNumber(int newDotCount) throws Exception {
        this.clearMatrix();
        this.dotCount = newDotCount;
        this.drawMatrix();
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

        return new String[0]; // Sem se dostanem jen pokud nenajdem žádné data pro hledané číslo
    }
    
    void showWindow() {
        this.matrix.showWindow();
    }

    void clearMatrix() {
        for (int y = 0; y < this.matrix.getHeight(); y++) {
            for (int x = 0; x < this.matrix.getWidth(); x++) {
                this.matrix.setOff(x, y);
            }
        }
    }

    void drawMatrix() throws Exception {
        String coordData[] = this.getCoordData(this.dotCount);

        for (int i = 0; i < coordData.length; i++) {
            int x = Integer.parseInt(coordData[i].split(",")[0]);
            int y = Integer.parseInt(coordData[i].split(",")[1]);

            System.out.print(x);
            System.out.print(" ");
            System.out.println(y);

            this.matrix.setOn(x, y);
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        CubeFace cb = new CubeFace(1);
        cb.showWindow();

        for (int i = 1; i <= 6; i++) {
            cb.setNumber(i);
            cb.drawMatrix();
            Thread.sleep(2000);
        }
    }
}