package BatallaNaval;

import javax.swing.*;
import java.util.ArrayList;

public class Machine {
    int[][] mainBoard = new int[10][10];
    int[][] mainBoardPrincipalInitial = new int[10][10];
    int fragatasF = 4, destructoresF = 3, submarinosF = 2, portaavionesF = 1, posicionando = 0, seleccionCPU, destroyed;
    boolean fragatas = true, destructores = false, submarinos = false, portaaviones = false, etapaPosicion = true;
    int[] anterior = new int[4];
    String matrizFinal = " 1 = agua \n 2 = fragata \n 3 = destructor \n 4 = submarino \n 5 = portaaviones \n";
    ArrayList<Integer> posiblesD = new ArrayList<>();
    ArrayList<Integer> posiblesS = new ArrayList<>();
    ArrayList<Integer> posiblesP = new ArrayList<>();
    ArrayList<Integer> barcosEnUso = new ArrayList<>();


    /**
     * Saves the first matrix of the position of the enemy ships
     */
    public void equalTable(){
        for(int i=0;i<mainBoard.length;i++) {
            for (int j = 0; j < mainBoard.length; j++) {
                mainBoardPrincipalInitial[i][j] = mainBoard[i][j];
            }
        }
    }
    /**
     * Shows the enemy territory as a matrix
     */
    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrizFinal += mainBoardPrincipalInitial[j][i];
                matrizFinal += "    ";
            }
            matrizFinal += "\n";
        }
        JOptionPane.showMessageDialog(null, matrizFinal, "Barcos enemigos inicial", 1);
        matrizFinal = " 1 = agua \n 2 = fragata \n 3 = destructor \n 4 = submarino \n 5 = portaaviones \n";
    }
    /**
     * Initialize mainBoard values to 1(Water)
     */
    public void boardsInitials() {
        for (int i = 0; i < mainBoard.length; i++) {
            for (int j = 0; j < mainBoard[0].length; j++) {
                mainBoard[i][j] = 1;
            }
        }
    }




}
