package BatallaNaval;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


public class Machine {
    int[][] mainBoard = new int[10][10];
    int[][] mainBoardPrincipalInitial = new int[10][10];
    int boats = 4, destroyer = 3, submarine = 2, portaavionesF = 1, posicionando = 0, seleccionCPU, destroyed;
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
    /**
     * Place the enemy ships dependind of the getSelectionMachine value
     */
    public int getSelectionMachine() {
        Random aleatorio = new Random();
        if (posicionando != 0 && destructores) {
            int numero = aleatorio.nextInt(posiblesD.toArray().length);
            int eleccion = posiblesD.get(numero);

            return eleccion;
        }
        if (posicionando != 0 && submarinos) {
            int numero = aleatorio.nextInt(posiblesS.toArray().length);
            int eleccion = posiblesS.get(numero);

            return eleccion;
        }
        if (posicionando != 0 && portaaviones) {
            int numero = aleatorio.nextInt(posiblesP.toArray().length);
            int eleccion = posiblesP.get(numero);

            return eleccion;
        } else {
            seleccionCPU = aleatorio.nextInt(10);
            return seleccionCPU;
        }
    }
    /**
     * Gets a random position to place the enemy ships
     */
    public void positionMachine() {
        if (!portaaviones) {
            while (!stopMachine()) {
                if (posicionando != 0 && (destructores || submarinos || portaaviones)) {
                    int seleccion = getSelectionMachine();
                    if (seleccion <= 9) {
                        int equis = 0;
                        int ye = seleccion;
                        position(equis, ye);
                    } else {
                        String number = String.valueOf(seleccion);
                        String[] digitos = number.split("(?<=.)");

                        int equis = Integer.parseInt(digitos[0]);
                        int ye = Integer.parseInt(digitos[1]);

                        position(equis, ye);
                    }
                } else {
                    int equis = getSelectionMachine();
                    int ye = getSelectionMachine();
                    position(equis, ye);
                }
            }
        }
    }
    /**
     * Function that stops positionMachine function when all the ships are positioned
     */
    public boolean stopMachine() {
        if (boats == 0 && destroyer == 0 && submarine == 0 && portaavionesF == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function that puts the ships on the enemy territory
     */
    public void position(int X, int Y) {
        if (etapaPosicion) {
            verifyBoat();
            if (fragatas) {
                if (verifyWater(X, Y)) {
                    mainBoard[X][Y] = 2;
                    //JOptionPane.showMessageDialog(null,""+boats);
                    boats--;
                    if (boats == 0) {
                        fragatas = false;
                        destructores = true;
                    }
                }
            }
            if (destructores) {
                if (verifyWater(X, Y)) {
                    if (verifyDestroyer(X, Y)) {
                        if (verifyWater(X, Y) && posicionando == 0) {
                            //JOptionPane.showMessageDialog(null,"destr:"+destroyer);
                            mainBoard[X][Y] = 3;
                            barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                            anterior[0] = X;
                            anterior[1] = Y;
                            posicionando = 1;
                            //JOptionPane.showMessageDialog(null, "" + posiblesD);
                        }
                        if (verifyWater(X, Y) && posicionando == 1) {
                            if (verifyDestroyerTwo(X, Y)) {
                                //JOptionPane.showMessageDialog(null, "funciona");
                                mainBoard[X][Y] = 3;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                                posicionando = 0;
                                destroyer--;
                                if (destroyer == 0) {
                                    destructores = false;
                                    submarinos = true;
                                }
                                posiblesD.clear();
                            } else {
                                //JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                    }
                }
            }
            if (submarinos) { // submarinos
                if (verifyWater(X, Y)) {
                    if (verifysubmarine(X, Y)) {
                        if (verifyWater(X, Y) && posicionando == 0) {
                            mainBoard[X][Y] = 4;
                            barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                            anterior[0] = X;
                            anterior[1] = Y;
                            posicionando = 1;
                            //JOptionPane.showMessageDialog(null, "" + posiblesD);
                        }
                        if (verifyWater(X, Y) && posicionando == 1) {
                            if (verifysubmarineTwo(X, Y)) {
                                if (anterior[0] > X) {
                                    posiblesS.clear();
                                    if (X + 1 == anterior[0]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X - 1) + String.valueOf(Y)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(anterior[0] + 1) + String.valueOf(anterior[1])));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X + 1) + String.valueOf(Y)));
                                    }
                                }
                                if (anterior[0] < X) {
                                    posiblesS.clear();
                                    if (X - 1 == anterior[0]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X + 1) + String.valueOf(Y)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(anterior[0] - 1) + String.valueOf(anterior[1])));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X - 1) + String.valueOf(Y)));
                                    }
                                }
                                if (anterior[1] > Y) {
                                    posiblesS.clear();
                                    if (Y + 1 == anterior[1]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y - 1)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(anterior[0]) + String.valueOf(anterior[1] + 1)));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y + 1)));
                                    }
                                }
                                if (anterior[1] < Y) {
                                    posiblesS.clear();
                                    if (Y - 1 == anterior[1]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y + 1)));
                                        if (anterior[1] != 0) {
                                            posiblesS.add(Integer.parseInt(String.valueOf(anterior[0]) + String.valueOf(anterior[1] - 1)));
                                        }
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y - 1)));
                                    }
                                }
                                mainBoard[X][Y] = 4;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                                posicionando = 2;
                                anterior[2] = X;
                                anterior[3] = Y;
                            } else {
                                //JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(X, Y) && posicionando == 2) {
                            if (verifysubmarineTwo(X, Y)) {
                                mainBoard[X][Y] = 4;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                                posicionando = 0;
                                submarine--;
                                if (submarine == 0) {
                                    submarinos = false;
                                    portaaviones = true;
                                }
                                posiblesS.clear();
                            } else {
                                //JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                    }
                }
            }
            if (portaaviones) { // portaaviones
                if (verifyWater(X, Y)) {
                    if (verifyPortaviones(X, Y)) {
                        if (verifyWater(X, Y) && posicionando == 0) {
                            anterior[0] = X;
                            anterior[1] = Y;
                            mainBoard[X][Y] = 5;
                            barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                            posicionando = 1;

                        }
                        if (verifyWater(X, Y) && posicionando == 1) {
                            if (verifyPortavionesTwo(X, Y)) {

                                posiblesP.clear();

                                if (anterior[0] + 1 == X) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(X + 1) + String.valueOf(Y)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(X + 2) + String.valueOf(Y)));
                                }
                                if (anterior[0] - 1 == X) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(X - 1) + String.valueOf(Y)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(X - 2) + String.valueOf(Y)));
                                }
                                if (anterior[1] + 1 == Y) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y + 1)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y + 2)));
                                }
                                if (anterior[1] - 1 == Y) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y - 1)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y - 2)));
                                }
                                mainBoard[X][Y] = 5;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                                posicionando = 2;
                                anterior[2] = X;
                                anterior[3] = Y;
                            } else {
                                //JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(X, Y) && posicionando == 2) {
                            if (verifyPortavionesTwo(X, Y)) {
                                mainBoard[X][Y] = 5;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                                posicionando = 3;
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(X, Y) && posicionando == 3) {
                            if (verifyPortavionesTwo(X, Y)) {
                                mainBoard[X][Y] = 5;
                                posicionando = 0;
                                portaavionesF--;
                                if (portaavionesF == 0) {
                                    portaaviones = false;
                                }
                                //posiblesP.clear();
                                etapaPosicion = false;
                                barcosEnUso.add(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
                            } else {
                                //JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * verify if the selected position is a empty position to place a ship (Water)
     */
    public boolean verifyWater(int X, int Y) {
        if (mainBoard[X][Y] == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * verify which ship is being positioned
     */
    public void verifyBoat() {
        if (boats == 0) {
            fragatas = false;
            destructores = true;
        }
        if (destroyer == 0) {
            destructores = false;
            submarinos = true;
        }
        if (submarine == 0) {
            submarinos = false;
            portaaviones = true;
        }
        if (portaavionesF == 0) {
            portaaviones = false;
        }
    }
    /**
     * Verify possibilities to put the second part of the ship(destructores)
     */
    public boolean verifyDestroyer(int X, int Y) { // 6 y 4
        if (posiblesD.isEmpty() == true) {
            if (((X + 1) <= 9)) {
                if (mainBoard[X + 1][Y] == 1) {
                    posiblesD.add(((X + 1) * 10) + Y);
                }
            }
            if (((X - 1) >= 0)) {
                if (mainBoard[X - 1][Y] == 1) {
                    posiblesD.add(((X - 1) * 10) + Y);
                }
            }
            if (((Y + 1) <= 9)) {
                if (mainBoard[X][Y + 1] == 1) {
                    posiblesD.add(((X) * 10) + (Y + 1));
                }
            }
            if (((Y - 1) >= 0)) {
                if (mainBoard[X][Y - 1] == 1) {
                    posiblesD.add(((X) * 10) + (Y - 1));
                }
            }
        }
        return !posiblesD.isEmpty();
    }

    /**
     * Verify possibilities to put the second and third part of the ship(submarinos)
     */
    public boolean verifysubmarine(int X, int Y) {
        if (posiblesS.isEmpty() == true) {
            if (((X + 1) <= 9) && ((X + 2) <= 9)) {
                if (mainBoard[X + 1][Y] == 1 && mainBoard[X + 2][Y] == 1) {
                    posiblesS.add(((X + 1) * 10) + Y);
                    posiblesS.add(((X + 2) * 10) + Y);
                }
            }
            if (((X - 1) >= 0 && ((X - 2) >= 0))) {
                if (mainBoard[X - 1][Y] == 1 && mainBoard[X - 2][Y] == 1) {
                    posiblesS.add(((X - 1) * 10) + Y);
                    posiblesS.add(((X - 2) * 10) + Y);
                }
            }
            if (((Y + 1) <= 9 && (Y + 2) <= 9)) {
                if (mainBoard[X][Y + 1] == 1 && mainBoard[X][Y + 2] == 1) {
                    posiblesS.add(((X) * 10) + (Y + 1));
                    posiblesS.add(((X) * 10) + (Y + 2));
                }
            }
            if (((Y - 1) >= 0 && (Y - 2) >= 0)) {
                if (mainBoard[X][Y - 1] == 1 && mainBoard[X][Y - 2] == 1) {
                    posiblesS.add(((X) * 10) + (Y - 1));
                    posiblesS.add(((X) * 10) + (Y - 2));
                }
            }
        }
        return !posiblesS.isEmpty();
    }

    /**
     * Verify possibilities to put the second,third and four part of the ship(portaaviones)
     */
    public boolean verifyPortaviones(int X, int Y) {
        if (posiblesP.isEmpty() == true) {
            if ((X + 1) <= 9 && (X + 2) <= 9 && (X + 3) <= 9) {
                if (mainBoard[X + 1][Y] == 1 && mainBoard[X + 2][Y] == 1 && mainBoard[X + 3][Y] == 1) {
                    posiblesP.add(((X + 1) * 10) + Y);
                }
            }
            if ((X - 1) >= 0 && (X - 2) >= 0 && (X - 3) >= 0) {
                if (mainBoard[X - 1][Y] == 1 && mainBoard[X - 2][Y] == 1 && mainBoard[X - 3][Y] == 1) {
                    posiblesP.add(((X - 1) * 10) + Y);
                }
            }
            if ((Y + 1) <= 9 && (Y + 2) <= 9 && (Y + 3) <= 9) {
                if (mainBoard[X][Y + 1] == 1 && mainBoard[X][Y + 2] == 1 && mainBoard[X][Y + 3] == 1) {
                    posiblesP.add(((X) * 10) + (Y + 1));
                }
            }
            if ((Y - 1) >= 0 && (Y - 2) >= 0 && (Y - 3) >= 0) {
                if (mainBoard[X][Y - 1] == 1 && mainBoard[X][Y - 2] == 1 && mainBoard[X][Y - 3] == 1) {
                    posiblesP.add(((X) * 10) + (Y - 1));
                }
            }
        }
        return !posiblesP.isEmpty();
    }

    /**
     * Verify possibilities to put the part of the ship(destructor)
     */
    public boolean verifyDestroyerTwo(int X, int Y) {
        int indice = posiblesD.indexOf(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify possibilities to put the part of the ship(submarino)
     */
    public boolean verifysubmarineTwo(int X, int Y) {
        int indice = posiblesS.indexOf(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify possibilities to put the part of the ship(destructor)
     */
    public boolean verifyPortavionesTwo(int X, int Y) {
        int indice = posiblesP.indexOf(Integer.parseInt(String.valueOf(X) + String.valueOf(Y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change the values of the matrix to makes us know if the ship has been shot
     */
    public void attack(int X, int Y) {
        verifyingSunk(X, Y);
        int numero = Integer.parseInt(String.valueOf(X) + String.valueOf(Y));
        if (mainBoard[X][Y] == 1) {
            mainBoard[X][Y] = 15;
        }
        if (mainBoard[X][Y] == 3) {
            mainBoard[X][Y] = 35;
        }
        if (mainBoard[X][Y] == 2) {
            mainBoard[X][Y] = 125;
        }
        if (mainBoard[X][Y] == 4) {
            mainBoard[X][Y] = 45;
        }
        if (mainBoard[X][Y] == 5) {
            mainBoard[X][Y] = 55;
        }
        verifyingSunk(X, Y);
    }

    /**
     * Shows the ships that are destroyed
     */
    public void showDestroyed() {
        destroyed = 0;
        for (int i = 0; i < mainBoard.length; i++) {
            for (int j = 0; j < mainBoard[0].length; j++) {
                if (mainBoard[i][j] > 99) {
                    destroyed++;
                }
            }
        }
    }

    /**
     * Show the player win
     */
    public void playerWin() {
        if (destroyed == 20) {
            JOptionPane.showMessageDialog(null, "Ganaste");
        }
    }
    /**
     *Return main board
     */
    public int[][] getmainBoard() {
        return mainBoard;
    }
    public void verifyingSunk(int X, int Y) {
        int numero = Integer.parseInt(String.valueOf(X) + String.valueOf(Y));
        int posicion = barcosEnUso.indexOf(numero);

        if (posicion == 0) {
            int compañero = barcosEnUso.get(1);

            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 1) {
            int compañero = barcosEnUso.get(0);

            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 2) {
            int compañero = barcosEnUso.get(3);

            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 3) {
            int compañero = barcosEnUso.get(2);
            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 4) {
            int compañero = barcosEnUso.get(5);

            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 5) {
            int compañero = barcosEnUso.get(4);

            if (compañero < 10) {
                int equis = 0;
                int ye = compañero;

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            } else {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                int equis = Integer.parseInt(digitos[0]);
                int ye = Integer.parseInt(digitos[1]);

                if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100) {
                    mainBoard[X][Y] = 100 + numero;
                    mainBoard[equis][ye] = 100 + compañero;
                }
            }
        }
        if (posicion == 6) {

            /**PRUEBA**/

            int compañero = barcosEnUso.get(7);
            int compañero1 = barcosEnUso.get(8);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }
        }
        if (posicion == 7) {
            int compañero = barcosEnUso.get(6);
            int compañero1 = barcosEnUso.get(8);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }


        }
        if (posicion == 8) {
            int compañero = barcosEnUso.get(7);
            int compañero1 = barcosEnUso.get(6);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }
        }
        if (posicion == 9) {
            int compañero = barcosEnUso.get(10);
            int compañero1 = barcosEnUso.get(11);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }
        }
        if (posicion == 10) {
            int compañero = barcosEnUso.get(9);
            int compañero1 = barcosEnUso.get(11);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }
        }
        if (posicion == 11) {
            int compañero = barcosEnUso.get(9);
            int compañero1 = barcosEnUso.get(10);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
            }
        }
        if (posicion == 12) {
            int compañero = barcosEnUso.get(13);
            int compañero1 = barcosEnUso.get(14);
            int compañero2 = barcosEnUso.get(15);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }
            if (compañero2 < 10) {
                equis2 = 0;
                ye2 = compañero2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(compañero2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100 &&
                    mainBoard[equis2][ye2] > 10 && mainBoard[equis2][ye2] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
                mainBoard[equis2][ye2] = 100 + compañero2;
            }
        }
        if (posicion == 13) {
            int compañero = barcosEnUso.get(12);
            int compañero1 = barcosEnUso.get(14);
            int compañero2 = barcosEnUso.get(15);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }
            if (compañero2 < 10) {
                equis2 = 0;
                ye2 = compañero2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(compañero2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100 &&
                    mainBoard[equis2][ye2] > 10 && mainBoard[equis2][ye2] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
                mainBoard[equis2][ye2] = 100 + compañero2;
            }

        }
        if (posicion == 14) {
            int compañero = barcosEnUso.get(13);
            int compañero1 = barcosEnUso.get(12);
            int compañero2 = barcosEnUso.get(15);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }
            if (compañero2 < 10) {
                equis2 = 0;
                ye2 = compañero2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(compañero2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100 &&
                    mainBoard[equis2][ye2] > 10 && mainBoard[equis2][ye2] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
                mainBoard[equis2][ye2] = 100 + compañero2;
            }
        }
        if (posicion == 15) {
            int compañero = barcosEnUso.get(13);
            int compañero1 = barcosEnUso.get(14);
            int compañero2 = barcosEnUso.get(12);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (compañero < 10) {
                equis = 0;
                ye = compañero;
            }
            if (compañero1 < 10) {
                equis1 = 0;
                ye1 = compañero1;
            }
            if (compañero2 < 10) {
                equis2 = 0;
                ye2 = compañero2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(compañero);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(compañero1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(compañero2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (mainBoard[X][Y] > 10 && mainBoard[X][Y] < 100 && mainBoard[equis][ye] > 10 && mainBoard[equis][ye] < 100 &&
                    mainBoard[equis1][ye1] > 10 && mainBoard[equis1][ye1] < 100 &&
                    mainBoard[equis2][ye2] > 10 && mainBoard[equis2][ye2] < 100) {
                mainBoard[X][Y] = 100 + numero;
                mainBoard[equis][ye] = 100 + compañero;
                mainBoard[equis1][ye1] = 100 + compañero1;
                mainBoard[equis2][ye2] = 100 + compañero2;
            }
        }
    }




}