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
}

