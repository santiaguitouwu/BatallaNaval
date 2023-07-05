package BatallaNaval;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    /**
     * this class contains the logic of the game
     */
    int[][] posiblyBoard = new int[10][10];
    boolean fragatas = true, destructores = false, submarinos = false, portaaviones = false, positionStage = true;
    int fragatasF = 4, destructoresF = 3, submarinosF = 2, portaavionesF = 1, posicionando = 0, destruidos = 0;
    int[] last = new int[4];
    ArrayList<Integer> posibleeAtack = new ArrayList<>();
    ArrayList<Integer> posiblesP = new ArrayList<>();
    ArrayList<Integer> posiblesD = new ArrayList<>();
    ArrayList<Integer> posiblesS = new ArrayList<>();
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
     * Initialize possiblyBoard to 1(water)
     */
    public void initialBoard(){
        for (int i = 0; i < posiblyBoard.length; i++) {
            for (int j = 0; j < posiblyBoard[0].length; j++) {
                posiblyBoard[i][j] = 1;
            }
        }
    }

    /**
     * Put the ships in the territory
     */
    public void takePosition(int x, int y){
        if (positionStage) {
            verificarBarco();
            if (fragatas) {
                if (verifyWater(x, y)) {
                    posiblyBoard[x][y] = 2;
                    fragatasF--;
                    if (fragatasF == 0) {
                        fragatas = false;
                        destructores = true;
                    }
                }
            }
            if (destructores) {
                if (verifyWater(x, y)) {
                    if (verificarDestructor(x, y)) {
                        if (verifyWater(x, y) && posicionando == 0) {
                            posiblyBoard[x][y] = 3;
                            usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                            last[0] = x;
                            last[1] = y;
                            posicionando = 1;
                        }
                        if (verifyWater(x, y) && posicionando == 1) {
                            if (verificarDestructor2(x, y)) {
                                posiblyBoard[x][y] = 3;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                                posicionando = 0;
                                destructoresF--;
                                if (destructoresF == 0) {
                                    destructores = false;
                                    submarinos = true;
                                }
                                posiblesD.clear();
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                    }
                }
            }
            if (submarinos) { // submarinos
                if (verifyWater(x, y)) {
                    if (verificarSubmarino(x, y)) {
                        if (verifyWater(x, y) && posicionando == 0) {
                            posiblyBoard[x][y] = 4;
                            usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                            last[0] = x;
                            last[1] = y;
                            posicionando = 1;
                        }
                        if (verifyWater(x, y) && posicionando == 1) {
                            if (verificarSubmarino2(x, y)) {
                                if (last[0] > x) {
                                    posiblesS.clear();
                                    if (x + 1 == last[0]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x - 1) + String.valueOf(y)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(last[0] + 1) + String.valueOf(last[1])));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x + 1) + String.valueOf(y)));
                                    }
                                }
                                if (last[0] < x) {
                                    posiblesS.clear();
                                    if (x - 1 == last[0]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x + 1) + String.valueOf(y)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(last[0] - 1) + String.valueOf(last[1])));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x - 1) + String.valueOf(y)));
                                    }
                                }
                                if (last[1] > y) {
                                    posiblesS.clear();
                                    if (y + 1 == last[1]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y - 1)));
                                        posiblesS.add(Integer.parseInt(String.valueOf(last[0]) + String.valueOf(last[1] + 1)));
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y + 1)));
                                    }
                                }
                                if (last[1] < y) {
                                    posiblesS.clear();
                                    if (y - 1 == last[1]) {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y + 1)));
                                        if (last[1] != 0) {
                                            posiblesS.add(Integer.parseInt(String.valueOf(last[0]) + String.valueOf(last[1] - 1)));
                                        }
                                    } else {
                                        posiblesS.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y - 1)));
                                    }
                                }
                                posiblyBoard[x][y] = 4;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                                posicionando = 2;
                                last[2] = x;
                                last[3] = y;
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(x, y) && posicionando == 2) {
                            if (verificarSubmarino2(x, y)) {
                                posiblyBoard[x][y] = 4;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                                posicionando = 0;
                                submarinosF--;
                                if (submarinosF == 0) {
                                    submarinos = false;
                                    portaaviones = true;
                                }
                                posiblesS.clear();
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                    }
                }
            }
            if (portaaviones) { // portaaviones
                if (verifyWater(x, y)) {
                    if (verificarPortaaviones(x, y)) {
                        if (verifyWater(x, y) && posicionando == 0) {
                            last[0] = x;
                            last[1] = y;
                            posiblyBoard[x][y] = 5;
                            usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                            posicionando = 1;
                        }
                        if (verifyWater(x, y) && posicionando == 1) {
                            if (verificarPortaaviones2(x, y)) {

                                posiblesP.clear();

                                if (last[0] + 1 == x) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(x + 1) + String.valueOf(y)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(x + 2) + String.valueOf(y)));
                                }
                                if (last[0] - 1 == x) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(x - 1) + String.valueOf(y)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(x - 2) + String.valueOf(y)));
                                }
                                if (last[1] + 1 == y) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y + 1)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y + 2)));
                                }
                                if (last[1] - 1 == y) {
                                    posiblesP.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y - 1)));
                                    posiblesP.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y - 2)));
                                }
                                posiblyBoard[x][y] = 5;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                                posicionando = 2;
                                last[2] = x;
                                last[3] = y;
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(x, y) && posicionando == 2) {
                            if (verificarPortaaviones2(x, y)) {
                                posiblyBoard[x][y] = 5;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                                posicionando = 3;
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
                            }
                        }
                        if (verifyWater(x, y) && posicionando == 3) {
                            if (verificarPortaaviones2(x, y)) {
                                posiblyBoard[x][y] = 5;
                                posicionando = 0;
                                portaavionesF--;
                                if (portaavionesF == 0) {
                                    portaaviones = false;
                                }
                                //posiblesP.clear();
                                positionStage = false;
                                usedShips.add(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
                            } else {
                                JOptionPane.showMessageDialog(null, "No puedes posicionar aquí");
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
    public boolean verifyWater(int x, int y){
        if (posiblyBoard[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * verify which ship is being positioned
     */
    public void verificarBarco() {
        if (fragatasF == 0) {
            fragatas = false;
            destructores = true;
        }
        if (destructoresF == 0) {
            destructores = false;
            submarinos = true;
        }
        if (submarinosF == 0) {
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
    public boolean verificarDestructor(int x, int y) { // 6 y 4
        if (posiblesD.isEmpty() == true) {
            if (((x + 1) <= 9)) {
                if (posiblyBoard[x + 1][y] == 1) {
                    posiblesD.add(((x + 1) * 10) + y);
                }
            }
            if (((x - 1) >= 0)) {
                if (posiblyBoard[x - 1][y] == 1) {
                    posiblesD.add(((x - 1) * 10) + y);
                }
            }
            if (((y + 1) <= 9)) {
                if (posiblyBoard[x][y + 1] == 1) {
                    posiblesD.add(((x) * 10) + (y + 1));
                }
            }
            if (((y - 1) >= 0)) {
                if (posiblyBoard[x][y - 1] == 1) {
                    posiblesD.add(((x) * 10) + (y - 1));
                }
            }
        }
        return !posiblesD.isEmpty();
    }

    /**
     * Verify possibilities to put the second and third part of the ship(submarinos)
     */
    public boolean verificarSubmarino(int x, int y) {
        if (posiblesS.isEmpty() == true) {
            if (((x + 1) <= 9) && ((x + 2) <= 9)) {
                if (posiblyBoard[x + 1][y] == 1 && posiblyBoard[x + 2][y] == 1) {
                    posiblesS.add(((x + 1) * 10) + y);
                    posiblesS.add(((x + 2) * 10) + y);
                }
            }
            if (((x - 1) >= 0 && ((x - 2) >= 0))) {
                if (posiblyBoard[x - 1][y] == 1 && posiblyBoard[x - 2][y] == 1) {
                    posiblesS.add(((x - 1) * 10) + y);
                    posiblesS.add(((x - 2) * 10) + y);
                }
            }
            if (((y + 1) <= 9 && (y + 2) <= 9)) {
                if (posiblyBoard[x][y + 1] == 1 && posiblyBoard[x][y + 2] == 1) {
                    posiblesS.add(((x) * 10) + (y + 1));
                    posiblesS.add(((x) * 10) + (y + 2));
                }
            }
            if (((y - 1) >= 0 && (y - 2) >= 0)) {
                if (posiblyBoard[x][y - 1] == 1 && posiblyBoard[x][y - 2] == 1) {
                    posiblesS.add(((x) * 10) + (y - 1));
                    posiblesS.add(((x) * 10) + (y - 2));
                }
            }
        }
        return !posiblesS.isEmpty();
    }

    /**
     * Verify possibilities to put the second,third and four part of the ship(portaaviones)
     */
    public boolean verificarPortaaviones(int x, int y) { // 6 y 4
        if (posiblesP.isEmpty() == true) {
            if ((x + 1) <= 9 && (x + 2) <= 9 && (x + 3) <= 9) {
                if (posiblyBoard[x + 1][y] == 1 && posiblyBoard[x + 2][y] == 1 && posiblyBoard[x + 3][y] == 1) {
                    posiblesP.add(((x + 1) * 10) + y);
                }
            }
            if ((x - 1) >= 0 && (x - 2) >= 0 && (x - 3) >= 0) {
                if (posiblyBoard[x - 1][y] == 1 && posiblyBoard[x - 2][y] == 1 && posiblyBoard[x - 3][y] == 1) {
                    posiblesP.add(((x - 1) * 10) + y);
                }
            }
            if ((y + 1) <= 9 && (y + 2) <= 9 && (y + 3) <= 9) {
                if (posiblyBoard[x][y + 1] == 1 && posiblyBoard[x][y + 2] == 1 && posiblyBoard[x][y + 3] == 1) {
                    posiblesP.add(((x) * 10) + (y + 1));
                }
            }
            if ((y - 1) >= 0 && (y - 2) >= 0 && (y - 3) >= 0) {
                if (posiblyBoard[x][y - 1] == 1 && posiblyBoard[x][y - 2] == 1 && posiblyBoard[x][y - 3] == 1) {
                    posiblesP.add(((x) * 10) + (y - 1));
                }
            }
        }
        return !posiblesP.isEmpty();
    }

    /**
     * Verify possibilities to put the part of the ship(destructor)
     */
    public boolean verificarDestructor2(int x, int y) {
        int indice = posiblesD.indexOf(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify possibilities to put the part of the ship(submarino)
     */
    public boolean verificarSubmarino2(int x, int y) {
        int indice = posiblesS.indexOf(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify possibilities to put the part of the ship(destructor)
     */
    public boolean verificarPortaaviones2(int x, int y) {
        int indice = posiblesP.indexOf(Integer.parseInt(String.valueOf(x) + String.valueOf(y)));
        if (indice != -1) {
            return true;
        } else {
            return false;
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
    public void verifySunken(int x, int y) {
        int numero = Integer.parseInt(String.valueOf(x) + String.valueOf(y));
        int posicion = usedShips.indexOf(numero);

        if (posicion == 0) {
            int friend = usedShips.get(1);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 1) {
            int friend = usedShips.get(0);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 2) {
            int friend = usedShips.get(3);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 3) {
            int friend = usedShips.get(2);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 4) {
            int friend = usedShips.get(5);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 5) {
            int friend = usedShips.get(4);

            String number = String.valueOf(friend);

            String[] digitos = number.split("(?<=.)");

            int equis = Integer.parseInt(digitos[0]);
            int ye = Integer.parseInt(digitos[1]);

            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
            }
        }
        if (posicion == 6) {

            /**PRUEBA**/

            int friend = usedShips.get(7);
            int friend1 = usedShips.get(8);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }
        }
        if (posicion == 7) {
            int friend = usedShips.get(6);
            int friend1 = usedShips.get(8);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }


        }
        if (posicion == 8) {
            int friend = usedShips.get(7);
            int friend1 = usedShips.get(6);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }
        }
        if (posicion == 9) {
            int friend = usedShips.get(10);
            int friend1 = usedShips.get(11);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }
        }
        if (posicion == 10) {
            int friend = usedShips.get(9);
            int friend1 = usedShips.get(11);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
                equis = 0;
                ye = friend;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }
        }
        if (posicion == 11) {
            int friend = usedShips.get(9);
            int friend1 = usedShips.get(10);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;

            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
            }
        }
        if (posicion == 12) {

            int friend = usedShips.get(13);
            int friend1 = usedShips.get(14);
            int friend2 = usedShips.get(15);

            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }
            if (friend2 < 10) {
                equis2 = 0;
                ye2 = friend2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(friend2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100 &&
                    posiblyBoard[equis2][ye2] > 10 && posiblyBoard[equis2][ye2] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
                posiblyBoard[equis2][ye2] = 100 + friend2;
            }
        }
        if (posicion == 13) {


            int friend = usedShips.get(12);
            int friend1 = usedShips.get(14);
            int friend2 = usedShips.get(15);


            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }
            if (friend2 < 10) {
                equis2 = 0;
                ye2 = friend2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(friend2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100 &&
                    posiblyBoard[equis2][ye2] > 10 && posiblyBoard[equis2][ye2] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
                posiblyBoard[equis2][ye2] = 100 + friend2;
            }

        }
        if (posicion == 14) {
            int friend = usedShips.get(13);
            int friend1 = usedShips.get(12);
            int friend2 = usedShips.get(15);


            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }
            if (friend2 < 10) {
                equis2 = 0;
                ye2 = friend2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(friend2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100 &&
                    posiblyBoard[equis2][ye2] > 10 && posiblyBoard[equis2][ye2] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
                posiblyBoard[equis2][ye2] = 100 + friend2;
            }
        }
        if (posicion == 15) {
            int friend = usedShips.get(13);
            int friend1 = usedShips.get(14);
            int friend2 = usedShips.get(12);


            int equis = 12;
            int ye = 12;
            int equis1 = 12;
            int ye1 = 12;
            int equis2 = 12;
            int ye2 = 12;


            if (friend < 10) {
                equis = 0;
                ye = friend;
            }
            if (friend1 < 10) {
                equis1 = 0;
                ye1 = friend1;
            }
            if (friend2 < 10) {
                equis2 = 0;
                ye2 = friend2;
            }

            if (equis == 12 && ye == 12) {
                String number = String.valueOf(friend);
                String[] digitos = number.split("(?<=.)");

                equis = Integer.parseInt(digitos[0]);
                ye = Integer.parseInt(digitos[1]);

            }
            if (equis1 == 12 && ye1 == 12) {
                String number1 = String.valueOf(friend1);

                String[] digitos1 = number1.split("(?<=.)");

                equis1 = Integer.parseInt(digitos1[0]);
                ye1 = Integer.parseInt(digitos1[1]);
            }
            if (equis2 == 12 && ye2 == 12) {

                String number2 = String.valueOf(friend2);
                String[] digitos2 = number2.split("(?<=.)");

                equis2 = Integer.parseInt(digitos2[0]);
                ye2 = Integer.parseInt(digitos2[1]);
            }


            if (posiblyBoard[x][y] > 10 && posiblyBoard[x][y] < 100 && posiblyBoard[equis][ye] > 10 && posiblyBoard[equis][ye] < 100 &&
                    posiblyBoard[equis1][ye1] > 10 && posiblyBoard[equis1][ye1] < 100 &&
                    posiblyBoard[equis2][ye2] > 10 && posiblyBoard[equis2][ye2] < 100) {
                posiblyBoard[x][y] = 100 + numero;
                posiblyBoard[equis][ye] = 100 + friend;
                posiblyBoard[equis1][ye1] = 100 + friend1;
                posiblyBoard[equis2][ye2] = 100 + friend2;
            }
        }
    }
    public void verDestruidos() {
        destruidos = 0;
        for (int i = 0; i < tableroPpal.length; i++) {
            for (int j = 0; j < tableroPpal[0].length; j++) {
                if (tableroPpal[i][j] > 99) {
                    destruidos++;
                }
            }
        }
    }
    /**
     * Show us if the CPU has won
     */
    public void ganadorCPU() {
        if (destruidos == 20) {
            JOptionPane.showMessageDialog(null, "Ganó la CPU");
        }
    }
    /**
     * Getters
     */
    public int[][] getPosiblyBoard() {
        return posiblyBoard;
    }
}


