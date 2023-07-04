package BatallaNaval;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    /**
     * this class contains the logic of the game
     */
    int[][] posiblyBoard = new int[10][10];
    ArrayList<Integer> posibleeAtack = new ArrayList<>();
    ArrayList<Integer> usedShips = new ArrayList<>();

    /*
    *Initialize pisibleeAtack array
     */
    public void fillAttack(){
        for (int i = 0; i<=99; i++){
            posibleeAtack.add(i);
        }
    }

    /**
     * select a random value for the movement of opponent
     * @return number of the select movement
     */
    public int selectAttack(){
        Random random = new Random();

        int number = random.nextInt(posibleeAtack.toArray().length);
        int select = posibleeAtack.get(number);
        posibleeAtack.remove(number);

        return select;
    }

    /**
     * Use selectAttack value for attack
     */
    public void CPUattack(){
        int selection = selectAttack();

        if(selection <= 9){
            int x = 0;
            int y = selection;

            attack(x,y);
        } else{
            String number = String.valueOf(selection);
            String[] numberss = number.split("(?<=.)");

            int x = Integer.parseInt(numberss[0]);
            int y = Integer.parseInt(numberss[1]);

            attack(x,y);
        }
    }

    /**
     * change the values of the matrix to know if the ship has been shot
     * @param x
     * @param y
     */
    public void attack(int x, int y){
        verifySunken(x,y);

        if(posiblyBoard[x][y]==1){
            posiblyBoard[x][y]=15;
        }
        if (posiblyBoard[x][y] == 3) {
            posiblyBoard[x][y] = 35;
        }
        if (posiblyBoard[x][y] == 2) {
            posiblyBoard[x][y] = 125;
        }
        if (posiblyBoard[x][y] == 4) {
            posiblyBoard[x][y] = 45;
        }
        if (posiblyBoard[x][y] == 5) {
            posiblyBoard[x][y] = 55;
        }
        verifySunken(x, y);
    }

    /**
     * change the matrix values to knows if the ship has been sunken
     * @param x
     * @param y
     */
    public void verifySunken(int x, int y){
        int number = Integer.parseInt(String.valueOf(x) + String.valueOf(y));
        int position = usedShips.indexOf(number);

        if(position == 0){
            int friend = usedShips.get(1);
            String numberString = String.valueOf(friend);
            String[] digitos = numberString.split("(?<=.)");

            int xs = Integer.parseInt(digitos[0]);
            int ys = Integer.parseInt(digitos[1]);
        }
    }
}
