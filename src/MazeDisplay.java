import java.awt.Color ;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener ;
import java.awt.geom.Path2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable ;
import java.awt.print.PrinterException ;
import java.awt.print.PrinterJob;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazeDisplay extends JPanel implements Printable, ActionListener, KeyListener{
    Maze m1;

    int x = 400;
    int y = 400;
    int offsetX = 10;
    int offsetY = 10;
    int cellSize = 20;
    double scale;

    Integer moveCounter = 0;

    int pointX, pointY, oldX, oldY;
    boolean erase;

    public void setScale(double scale) {
        this.scale = scale;
    }

    public MazeDisplay(){
        m1 = new Maze();
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
        addKeyListener(this);
    }

    public MazeDisplay(Maze m2){
        m1 = m2;
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
        addKeyListener(this);
    }

    MazeDisplay(Maze m2, int cellSize2){
        m1 = m2;
        cellSize = cellSize2;
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
        addKeyListener(this);
    }

    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException{
            if(page > 0){
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());

            doDrawing(g2d);

            return PAGE_EXISTS;
        }

    public void actionPerformed(ActionEvent e){
        myPrint();
    }

    public void myPrint(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        job.setJobName("Maze");
        boolean ok = job.printDialog();
        if (ok){
            try{
                job.print();
            } catch(PrinterException ex){
                System.out.println("Print failed");
            }
        }
    }

    public void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);
        g2d.setTransform(transform);
        this.repaint();

        g2d.setColor(Color.blue);

        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        g2d.setBackground(Color.white);
        g2d.clearRect(0,0, w, h);

        Path2D mazeShape = new Path2D.Double();

        int x, y;
        for(Integer i =0;i<m1.sizeX;i++){
            x = i* cellSize+offsetX;
            for (Integer j=0;j<m1.sizeY;j++){
                y = j*cellSize+offsetY;

                if(m1.cells[i][j].walls[0] == 1){
                    mazeShape.moveTo(x, y);
                    mazeShape.lineTo(x + cellSize, y);
                    g2d.drawLine(x, y, x + cellSize, y);
                }
                if(m1.cells[i][j].walls[1] == 1){
                    mazeShape.moveTo(x + cellSize, y);
                    mazeShape.lineTo(x + cellSize, y + cellSize);
                    g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                }
                if(m1.cells[i][j].walls[2] == 1){
                    mazeShape.moveTo(x, y + cellSize);
                    mazeShape.lineTo(x + cellSize, y + cellSize);
                    g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                }
                if(m1.cells[i][j].walls[3] == 1){
                    mazeShape.moveTo(x, y);
                    mazeShape.lineTo(x, y + cellSize);
                    g2d.drawLine(x, y, x, y + cellSize);
                }
            }
        }

        x = (oldX - offsetX -cellSize / 2) / cellSize;
        y = (oldY - offsetY -cellSize/2) / cellSize;

        if(x >= 0 && x < m1.sizeX && oldX > pointX && m1.cells[x][y].walls[3] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(x >= 0 && x < m1.sizeX && oldX < pointX && m1.cells[x][y].walls[1] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(y >= 0 && y < m1.sizeY && oldY > pointY && m1.cells[x][y].walls[0] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(y >= 0 && y < m1.sizeY && oldY < pointY && m1.cells[x][y].walls[2] == 1){
            pointX = oldX;
            pointY = oldY;
        }

        if (pointX != oldX || pointY != oldY){
            moveCounter++;
        }

        //g2d.drawString("Moves: " + moveCounter.toString(), m1.sizeX * cellSize + offsetX + 20, 20);
        g2d.drawString("Moves: Arrow Keys", m1.sizeX * cellSize + offsetX + 20, 40);
        //g2d.drawString("Moves: P", m1.sizeX * cellSize + offsetX + 20, 60);

        if(y == m1.sizeY - 1 && x == m1.sizeX - 1){
            System.out.println("You Won");
            g2d.drawString("You Won!", m1.sizeX * cellSize + offsetX + 20, 100);
            System.exit(0);
        }

        //g.setColor(Color.GRAY);
        //g.fillRect(oldX - 2, oldY - 2, 4, 4);

        g.setColor(Color.BLACK);
        g.fillRect(pointX - 2, pointY - 2, 4, 4);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
        this.repaint();
    }

    @SuppressWarnings("static-access")
    @Override
    public void keyPressed(KeyEvent key){
        oldX = pointX;
        oldY = pointY;

        if(key.getKeyCode() == key.VK_DOWN){
            pointY = pointY + cellSize;
            setLocation(x, (int) Math.floor(y - (cellSize * 2.5)));
            if (pointY > getBounds().height){
                pointY = getBounds().height;
            } /*else{
                offsetY -= cellSize/2;
            }*/
        }

        if(key.getKeyCode() == key.VK_UP){
            pointY = pointY - cellSize;
            setLocation(x, y + cellSize/2);
            if (pointY < 0){
                pointY = 0;
            } /*else{
                offsetY += cellSize/2;
            }*/
        }

        if(key.getKeyCode() == key.VK_LEFT){
            pointX = pointX - cellSize;
            setLocation(x + cellSize/2, y);
            if (pointX < 0){
                pointX = 0;
            } /*else{
                offsetX += cellSize/2;
            }*/
        }

        if(key.getKeyCode() == key.VK_RIGHT){
            pointX = pointX + cellSize;
            setLocation((int) Math.floor(x - (cellSize * 2.5)), y);
            if (pointX > getBounds().width){
                pointX = getBounds().width;
            } /*else{
                offsetX -= cellSize/2;
            }*/
        }

        if (key.getKeyCode() == key.VK_P){
            myPrint();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0){
    }

    @Override
    public void keyTyped(KeyEvent arg0){
    }

}