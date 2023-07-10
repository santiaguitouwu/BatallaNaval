package BatallaNaval;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;


/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    public static final String MENSAJE_INICIO = "Bienvenido a BattleShip \nOprime el boton 'Jugar' para iniciar el juego\n-El juego consiste en que deberás acabar con la flota enemiga sin verla\n-Posiciona tus barcos pulsando las casillas de tu territorio, teniendo en cuenta que:\n Deberás posicionar 4 fragatas, serán pintadas de color gris (1 casilla cada una)\n Deberás posicionar 3 destructores, serán pintadas de color verde (2 casilla cada una)\n Deberás posicionar 2 submarinos, serán pintadas de color magenta (3 casilla cada una)\n Deberás posicionar 1 portaaviones, serán pintadas de color carne  (4 casilla cada una)\n NOTA: para posicionar submarinos y portaaviones deberás hacerlo de forma ordenada (una tras otra)\n-Para hundir los barcos enemigos deberás usar las casillas del territorio enemigo\n Segun el numero de casillas del barco, podrás dañarlo o hundirlo\n Si se pinta de amarillo, faltan partes por hundir\n Si se pinta de rojo, has hundido ese barco\n-El juego termina cuando uno de los dos hunde todos los barcos del oponente\n-Podrás ver la matriz enemiga (territorio) si pulsas el boton 'Barcos enemigos'";
    private Header headerProject;
    private JButton jugar;
    private JButton ayuda;
    private JButton salir;
    private JPanel panelAliado;
    private JPanel panelEnemigo;
    private JTextArea mensajeSalida;
    private Escucha escucha;
    private int lugar = 0;
    private int lugare = 0;
    private int parar = 0;
    private int equis = 45;
    private int equiz = 330;
    private int ye = 1;
    private int ge = 1;
    private int lugar1 = 0;
    private int lugar0 = 0;
    private int lugar2 = 0;
    private int lugar3 = 0;
    private JLabel[] label = new JLabel[100];
    private JLabel[] label2 = new JLabel[100];
    private Model modelGame;
    public Machine cpu;




    public GUI() {
        this.initGUI();
        this.setTitle("Battleship");
        this.setSize(800, 600);
        this.setBackground(new Color(255, 255, 255, 255));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
    }

    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.escucha = new Escucha();
        this.headerProject = new Header("BattleShip", Color.BLACK);
        this.modelGame = new Model();
        this.cpu = new Machine();
        this.panelAliado = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = 2;
        this.add(this.headerProject, constraints);
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        int[][] tableroPos = this.modelGame.getPosiblyBoard();
        int[][] tableroPpal = this.cpu.getmainBoard();
        this.modelGame.initialBoard();
        this.cpu.boardsInitials();

        int i;
        for(i = 0; i < this.label.length; ++i) {
            this.label[i] = new JLabel();
        }

        for(i = 0; i < this.label2.length; ++i) {
            this.label2[i] = new JLabel();
        }

        JLabel[] var10000;
        String var10001;
        int y;
        for(i = 0; i < tableroPpal.length; ++i) {
            for(y = 0; y < tableroPpal[0].length; ++y) {
                if (tableroPpal[i][y] == 1) {
                    var10000 = this.label;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                    var10000 = this.label;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                }

                if (tableroPpal[i][y] == 2) {
                    var10000 = this.label;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.RED);
                    var10000 = this.label;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                }
            }
        }

        for(i = 0; i < tableroPos.length; ++i) {
            for(y = 0; y < tableroPos[0].length; ++y) {
                if (tableroPos[i][y] == 1) {
                    var10000 = this.label2;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                    var10000 = this.label2;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                }

                if (tableroPos[i][y] == 2) {
                    var10000 = this.label2;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.RED);
                    var10000 = this.label2;
                    var10001 = String.valueOf(i);
                    var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                }
            }
        }

        this.setVisible(true);
        this.ayuda = new JButton("?");
        this.ayuda.addActionListener(this.escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = 0;
        constraints.anchor = 21;
        this.add(this.ayuda, constraints);
        this.salir = new JButton("Barcos enemigos");
        this.salir.setVisible(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = 0;
        constraints.anchor = 22;
        this.add(this.salir, constraints);
        this.panelAliado = new JPanel();
        this.panelAliado.setPreferredSize(new Dimension(300, 300));
        this.panelAliado.setBorder(BorderFactory.createTitledBorder("Tu territorio (Tablero de posición)"));
        this.panelAliado.setLayout((LayoutManager)null);

        for(i = 0; i < this.label.length; ++i) {
            this.label[i].setBorder(border);
            this.label[i].setHorizontalAlignment(0);
            this.label[i].setBounds(new Rectangle(this.equis - 20, this.ye * 25, 25, 25));
            ++this.lugar;
            ++this.ye;
            if (this.lugar == 10) {
                this.equis += 25;
                this.ye = 1;
                this.lugar = 0;
            }

            this.panelAliado.add(this.label[i], (Object)null);
        }

        this.panelEnemigo = new JPanel();
        this.panelEnemigo.setPreferredSize(new Dimension(300, 300));
        this.panelEnemigo.setBorder(BorderFactory.createTitledBorder("Territorio enemigo (Tablero principal)"));
        this.panelEnemigo.setLayout((LayoutManager)null);

        for(i = 0; i < this.label2.length; ++i) {
            this.label2[i].setBorder(border);
            this.label2[i].setHorizontalAlignment(0);
            this.label2[i].setBounds(new Rectangle(this.equiz - 300, this.ge * 25, 25, 25));
            ++this.lugare;
            ++this.ge;
            if (this.lugare == 10) {
                this.equiz += 25;
                this.ge = 1;
                this.lugare = 0;
            }

            this.panelEnemigo.add(this.label2[i], (Object)null);
        }

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelAliado, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = 1;
        constraints.anchor = 10;
        this.add(this.panelEnemigo, constraints);
        this.jugar = new JButton("Jugar");
        this.jugar.addActionListener(this.escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = 0;
        constraints.anchor = 17;
        this.add(this.jugar, constraints);
        this.mensajeSalida = new JTextArea(2, 10);
        this.mensajeSalida.setText("Presiona Jugar para iniciar \n Cuando termines de colocar los barcos puedes empezar a disparar");
        this.mensajeSalida.setBorder(BorderFactory.createTitledBorder("Atención"));
        this.mensajeSalida.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = 0;
        constraints.anchor = 10;
        this.add(this.mensajeSalida, constraints);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GUI();
        });
    }

    private class Escucha implements ActionListener, MouseListener {
        private Escucha() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == GUI.this.jugar) {
                int i;
                for(i = 0; i < GUI.this.label.length; ++i) {
                    GUI.this.label[i].addMouseListener(GUI.this.escucha);
                }

                for(i = 0; i < GUI.this.label.length; ++i) {
                    GUI.this.label2[i].addMouseListener(GUI.this.escucha);
                }

                GUI.this.modelGame.fillAttack();
                GUI.this.cpu.positionMachine();
                GUI.this.cpu.equalTable();
            }

            if (e.getSource() == GUI.this.salir) {
                GUI.this.cpu.print();
            }

            if (e.getSource() == GUI.this.ayuda) {
                JOptionPane.showMessageDialog((Component)null, "Bienvenido a BattleShip \n-El juego consiste en que deberás acabar con la flota enemiga sin verla\n-Posiciona tus barcos pulsando las casillas de tu territorio, teniendo en cuenta que:\n Deberás posicionar 4 fragatas, serán pintadas de color blanco (1 casilla cada una)\n Deberás posicionar 3 destructores, serán pintadas de color amarillo (2 casilla cada una)\n Deberás posicionar 2 submarinos, serán pintadas de color naranja (3 casilla cada una)\n Deberás posicionar 1 portaaviones, serán pintadas de color rosado  (4 casilla cada una)\n NOTA: para posicionar submarinos y portaaviones deberás hacerlo de forma ordenada (una tras otra)\n-Para hundir los barcos enemigos deberás usar las casillas del territorio enemigo\n Si sale una bomba, faltan partes por hundir\n Si sale una llama, has hundido ese barco\n-El juego termina cuando uno de los dos hunde todos los barcos del oponente\n", "Instrucciones", 1);
            }

        }

        public void mouseClicked(MouseEvent e) {
            int[][] tableroPos = GUI.this.modelGame.getPosiblyBoard();
            int[][] tableroPpal = GUI.this.cpu.getmainBoard();

            JLabel[] var10000;
            String var10001;
            int i;
            int x;
            int y;
            for(i = 0; i < GUI.this.label.length; ++i) {
                if (e.getSource() == GUI.this.label[i]) {
                    if (i < 10) {
                        GUI.this.lugar0 = i;
                        GUI.this.lugar1 = 0;
                    }

                    if (i >= 10 && i < 20) {
                        GUI.this.lugar0 = i - 10;
                        GUI.this.lugar1 = 1;
                    }

                    if (i >= 20 && i < 30) {
                        GUI.this.lugar0 = i - 20;
                        GUI.this.lugar1 = 2;
                    }

                    if (i >= 30 && i < 40) {
                        GUI.this.lugar0 = i - 30;
                        GUI.this.lugar1 = 3;
                    }

                    if (i >= 40 && i < 50) {
                        GUI.this.lugar0 = i - 40;
                        GUI.this.lugar1 = 4;
                    }

                    if (i >= 50 && i < 60) {
                        GUI.this.lugar0 = i - 50;
                        GUI.this.lugar1 = 5;
                    }

                    if (i >= 60 && i < 70) {
                        GUI.this.lugar0 = i - 60;
                        GUI.this.lugar1 = 6;
                    }

                    if (i >= 70 && i < 80) {
                        GUI.this.lugar0 = i - 70;
                        GUI.this.lugar1 = 7;
                    }

                    if (i >= 80 && i < 90) {
                        GUI.this.lugar0 = i - 80;
                        GUI.this.lugar1 = 8;
                    }

                    if (i >= 90 && i < 100) {
                        GUI.this.lugar0 = i - 90;
                        GUI.this.lugar1 = 9;
                    }

                    GUI.this.modelGame.takePosition(GUI.this.lugar1, GUI.this.lugar0);



                    for(x = 0; x < tableroPos.length; ++x) {
                        for(y = 0; y < tableroPos[0].length; ++y) {
                            if (tableroPos[x][y] == 1) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 2) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imageFragataPath = "src/resources/1.png";
                                ImageIcon imageFragataIcon = new ImageIcon(imageFragataPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.white);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageFragataIcon);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 3) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imageDestructorPath = "src/resources/3.png";
                                ImageIcon imageDestructorIcon = new ImageIcon(imageDestructorPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.yellow);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageDestructorIcon);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 4) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imageSubmarinePath = "src/resources/2.png";
                                ImageIcon imageSubmarineIcon = new ImageIcon(imageSubmarinePath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.ORANGE);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageSubmarineIcon);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 5) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imagePortaPath = "src/resources/3.png";
                                ImageIcon imagePortaIcon = new ImageIcon(imagePortaPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.PINK);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imagePortaIcon);

                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }
                        }
                    }
                }
            }

            for(i = 0; i < GUI.this.label2.length; ++i) {
                if (e.getSource() == GUI.this.label2[i]) {
                    if (i < 10) {
                        GUI.this.lugar2 = i;
                        GUI.this.lugar3 = 0;
                    }

                    if (i >= 10 && i < 20) {
                        GUI.this.lugar2 = i - 10;
                        GUI.this.lugar3 = 1;
                    }

                    if (i >= 20 && i < 30) {
                        GUI.this.lugar2 = i - 20;
                        GUI.this.lugar3 = 2;
                    }

                    if (i >= 30 && i < 40) {
                        GUI.this.lugar2 = i - 30;
                        GUI.this.lugar3 = 3;
                    }

                    if (i >= 40 && i < 50) {
                        GUI.this.lugar2 = i - 40;
                        GUI.this.lugar3 = 4;
                    }

                    if (i >= 50 && i < 60) {
                        GUI.this.lugar2 = i - 50;
                        GUI.this.lugar3 = 5;
                    }

                    if (i >= 60 && i < 70) {
                        GUI.this.lugar2 = i - 60;
                        GUI.this.lugar3 = 6;
                    }

                    if (i >= 70 && i < 80) {
                        GUI.this.lugar2 = i - 70;
                        GUI.this.lugar3 = 7;
                    }

                    if (i >= 80 && i < 90) {
                        GUI.this.lugar2 = i - 80;
                        GUI.this.lugar3 = 8;
                    }

                    if (i >= 90 && i < 100) {
                        GUI.this.lugar2 = i - 90;
                        GUI.this.lugar3 = 9;
                    }

                    GUI.this.modelGame.CPUattack();
                    GUI.this.cpu.attack(GUI.this.lugar3, GUI.this.lugar2);

                    for(x = 0; x < tableroPpal.length; ++x) {
                        for(y = 0; y < tableroPpal[0].length; ++y) {
                            if (tableroPpal[x][y] < 9) {
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPpal[x][y] == 15) {
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);

                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setForeground(Color.RED);
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setText("X");
                            }

                            if (tableroPpal[x][y] > 15 && tableroPpal[x][y] < 100) {
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                String imageTocadoPath = "src/resources/tocado.png";
                                ImageIcon imageTocadoIcon = new ImageIcon(imageTocadoPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageTocadoIcon);
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPpal[x][y] >= 100) {
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                String imageHundidoPath = "src/resources/hundido.png";
                                ImageIcon imageHundidoIcon = new ImageIcon(imageHundidoPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageHundidoIcon);
                                var10000 = GUI.this.label2;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }
                        }
                    }

                    for(x = 0; x < tableroPos.length; ++x) {
                        for(y = 0; y < tableroPos[0].length; ++y) {
                            if (tableroPos[x][y] < 9) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 15) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setForeground(Color.RED);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setText("X");
                            }

                            if (tableroPos[x][y] > 15 && tableroPpal[x][y] < 100) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imageTocadoPath = "src/resources/tocado.png";
                                ImageIcon imageTocadoIcon = new ImageIcon(imageTocadoPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageTocadoIcon);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] >= 100) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                String imageHundidoPath = "src/resources/hundido.png";
                                ImageIcon imageHundidoIcon = new ImageIcon(imageHundidoPath);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setIcon(imageHundidoIcon);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }
                        }
                    }

                    for(x = 0; x < tableroPos.length; ++x) {
                        for(y = 0; y < tableroPos[0].length; ++y) {
                            if (tableroPos[x][y] == 1) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.CYAN);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 2) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.white);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 3) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.yellow);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 4) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.orange);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }

                            if (tableroPos[x][y] == 5) {
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setBackground(Color.PINK);
                                var10000 = GUI.this.label;
                                var10001 = String.valueOf(x);
                                var10000[Integer.parseInt(var10001 + String.valueOf(y))].setOpaque(true);
                            }
                        }
                    }
                }
            }

            GUI.this.modelGame.verDestruidos();
            GUI.this.cpu.showDestroyed();
            GUI.this.modelGame.ganadorCPU();
            GUI.this.cpu.playerWin();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}

