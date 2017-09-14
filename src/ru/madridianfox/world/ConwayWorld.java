package ru.madridianfox.world;

public class ConwayWorld extends AbstractWorld {
    public int[][][] cells;

    public ConwayWorld(int width, int height) {
        super(width, height);
        cells = new int[width][height][2];
        cells[1][0][0] = 1;
        cells[2][1][0] = 1;
        cells[0][2][0] = 1;
        cells[1][2][0] = 1;
        cells[2][2][0] = 1;
    }

    @Override
    protected void updateWorld() {
        for(int i=0; i < width(); i++){
            for(int j=0; j < height(); j++){
                cells[i][j][1] = countHeighbors(i, j);
            }
        }
        for(int i=0; i < width(); i++){
            for(int j=0; j < height(); j++){
                if(cells[i][j][0] == 0){
                    if(cells[i][j][1] == 3){
                        cells[i][j][0] = 1;
                    }
                }else{
                    if(cells[i][j][1] < 2 || cells[i][j][1] > 3){
                        cells[i][j][0] = 0;
                    }
                }
            }
        }
    }

    private int countHeighbors(int i, int j){
        int count=0;
        for(int di=-1; di<2; di++){
            for(int dj=-1; dj<2; dj++){
                if(di == 0 && dj == 0) continue;
                count += cells[getRealI(i+di)][getRealJ(j+dj)][0];
            }
        }
        return count;
    }
}
