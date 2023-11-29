import java.awt.*;
import javax.swing.*;

/**
*  A simple Java Swing component that draws a fractal seahorse.
*
*  @author Nicholas R. Howe
*  @version CSC 112, 29 March 2006
*/
class Seahorse extends JComponent {
  /** The rank of the seahorse */
  protected int rank;

  /** The size of the window */
  public static final int DIM = 600;

  /**
  *  Creates a Seahorse of specified size
  *
  *  @param rank Specifies the rank of the seahorse
  */
  public Seahorse(int rank) {
    this.rank = rank;
    Dimension winsize = new Dimension(DIM,3*DIM/4);
    setPreferredSize(winsize);
    setMinimumSize(winsize);
  }

  /**
  *  Draws a seahorse of the given rank between the given points.
  *
  *  @param rank  The rank of the seahorse
  *  @param p1    One endpoint of the seahorse
  *  @param p4    The other endpoint of the seahorse
  *  @param g     The graphics object to draw into
  */
  private void drawSeahorse(int rank, Point p1, Point p4, Graphics g) {
    // the other points of the seahorse:
    int dx = p4.x - p1.x;
    int dy = p4.y - p1.y;
    Point p2 = new Point(p1.x + dx / 4 - dy / 4, p1.y + dx / 4 + dy / 4);
    Point p3 = new Point(p4.x-dx / 4 + dy / 4, p4.y - dx / 4 - dy / 4);
    
    // x2 = x1+dx/4-dy/4
    // y2 = y1+dx/4+dy/4
    // x3 = x4-dx/4+dy/4
    // y3 = y4-dx/4-dy/4
    

    // WRITE THE RECURSIVE CODE HERE:
    if (rank == 0) {
      g.drawLine(p1.x, p1.y, p4.x, p4.y);
    }
    else {
      drawSeahorse(rank-1, p1, p2, g);
      drawSeahorse(rank-1, p2, p3, g);
      drawSeahorse(rank-1, p3, p4, g);
      //drawSeahorse(rank-1, p4, p3, g);

      // drawSeahorse(rank-1, p2, p1, g);
      // drawSeahorse(rank-1, p3, p2, g);
      // drawSeahorse(rank-1, p2, p1, g);

    }

  }

  ////////////////////////////////////////////
  //// Graphics code below -- do not edit ////
  ////////////////////////////////////////////
  
  /**
  *  Draws the seahorse in the graphics window
  *
  *  @param g The graphics object to draw into
  */
  public void paintComponent(Graphics g) {
    Point p1 = new Point(DIM/4,DIM/4);
    Point p2 = new Point(3*DIM/4,DIM/4);
    drawSeahorse(rank,p1,p2,g);
  }

  /** Create a window with a seahorse in it
   * @param rank (int) The rank of the line being drawn
   */
  public static void createAndShowGUI(int rank) {
    // Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create and set up the window.
    JFrame frame = new JFrame("Fractal Seahorse");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add components
    Seahorse seahorse = new Seahorse(rank);
    frame.getContentPane().add(seahorse);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  /** Call the window creation routine on the event thread */
  public static void main(String[] args) {
    int rank;
    if (args.length > 0) {
      rank = Integer.valueOf(args[0]);
    } else {
      rank = 5;
    }
    createAndShowGUI(rank);
  }
}  // end of Seahorse