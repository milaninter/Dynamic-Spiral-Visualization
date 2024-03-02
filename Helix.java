import DrawingPanel.DrawingPanel;
import java.awt.*;
import java.util.*;


public class Helix {
    public static void drawHelix() {
        DrawingPanel pl = new DrawingPanel(400, 400);
        pl.setBackground(Color.BLACK);
        Graphics g = pl.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, 200, 200);
        g.drawString("UTSA - CS1083 - Section 002 - Prj 3 - Helix - Oscar Milan", 45, 50);
        Scanner scan = new Scanner(System.in);


        int speed, move;
        int[] cod = new int[]{100, 100, 300, 300}; // [x1, y1, x2, y2]
        int startMovement = 0; // initial start movement is right




        System.out.print("Please, input the speed (1 - 10): ");
        speed = scan.nextInt();
        while(speed>10 || speed<1){
            System.out.print("Please, input the speed (1 - 10): ");
            speed = scan.nextInt();
        }
        System.out.println();
        System.out.print("Please, input the number of times the line will be shown: ");
        move = scan.nextInt();
        System.out.println();


        g.drawString("Speed: "+ speed, 170,80);



        int i;
/*
For the for loop that iterates for the movements of the line I was a bit confused on if i needed to start
 at 0 or 1 because on the projects instructions it says "Please note that after getting the
 user’s input, it starts a loop that will iterate from 0 to the number input by the user implementing an
emulation of a line movement " if the loop started at 0 and moved the amount of movements inputed this would not
match the same output in the video provided to us as an example
 */
        for ( i = 0; i < move; ++i) {
            int color = getRandomColor();
            g.setColor(getColor(color));
            g.drawLine(cod[0], cod[1], cod[2], cod[3]);
            pl.sleep(100 / speed);


            g.setColor(Color.WHITE); // cover the previous line
            g.drawLine(cod[0], cod[1], cod[2], cod[3]);


            startMovement = newPos(startMovement, cod);
            g.setColor(getColor(color));
            g.drawLine(cod[0], cod[1], cod[2], cod[3]);


            g.setColor(Color.BLACK);
            g.fillRect(100, 300, 400, 200);
            g.setColor(Color.WHITE);
            g.drawString("i: " +(i+1), 190, 320);
            g.drawString("(" + cod[0] + ", " + cod[1] + "), (" + cod[2] + ", " + cod[3] + ")", 145, 360);
        }


    }


    public static int newPos(int lastMovement, int[] coordinates) {


        int dir = lastMovement;
        int x1 = coordinates[0], y1 = coordinates[1];
        int x2 = coordinates[2], y2 = coordinates[3];


        if (x1 == 100 && y1 == 100) {
            dir = 0; // move right
        } else if (x1 == 300 && y1 == 100) {
            dir = 1; // reached top right corner, move down
        } else if (x1 == 300 && y1 == 300) {
            dir = 2; // reached bottom right corner, move left
        } else if (x1 == 100 && y1 == 300) {
            dir = 3; // reached bottom left corner, move up
        }


        switch (dir) {
            case 0:
                x1 += 10;
                x2 -= 10;
                break;
            case 1:
                y1 += 10;
                y2 -= 10;
                break;
            case 2:
                x1 -= 10;
                x2 += 10;
                break;
            case 3:
                y1 -= 10;
                y2 += 10;
                break;
        }


        coordinates[0] = x1;
        coordinates[1] = y1;
        coordinates[2] = x2;
        coordinates[3] = y2;


        return dir;
    }


    public static Color getColor(int cr) {
        switch (cr) {
            case 0: return Color.GREEN;//all the colors provided
            case 1: return Color.GRAY;
            case 2: return Color.YELLOW;
            case 3: return Color.RED;
            case 4: return Color.ORANGE;
            case 5: return Color.PINK;
            case 6: return Color.DARK_GRAY;
            case 7: return Color.BLUE;
            case 8: return Color.BLACK;
            default: return Color.WHITE;
        }
    }


    public static int getRandomColor() {
        Random random = new Random();
        return random.nextInt(9); // returns a random number between 0 and 8
    }


    public static void main(String[] args) {
        System.out.println("UTSA - Fall 2023 - CS1083 - Section 002 - Prj 3 - Helix - written by Oscar Milan");
        drawHelix();
    }
}


