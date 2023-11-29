import java.awt.*;
import javax.swing.*;

/**
 * A simple Java Swing component that draws a fractal dragon.
 *
 * @author Nicholas R. Howe
 * @version CSC 112, 29 March 2006
 */
class Dragon extends JComponent {
  /** The rank of the dragon */
  protected int rank;

  /** The size of the window */
  public static final int DIM = 600;

  /**
   * Creates a Dragon of specified size
   *
   * @param rank Specifies the rank of the dragon
   */
  public Dragon(int rank) {
    this.rank = rank;

    // required for graphics - do not edit:
    Dimension winsize = new Dimension(DIM, 3 * DIM / 4);
    setPreferredSize(winsize);
    setMinimumSize(winsize);
  }

  /**
   * Draws a dragon of the given rank between the given points.
   *
   * @param rank The rank of the dragon
   * @param p1   One endpoint of the dragon
   * @param p3   The other endpoint of the dragon
   * @param g    The graphics object to draw into
   */
  private void drawDragon(int rank, Point p1, Point p3, Graphics g) {
    int dx = p3.x - p1.x;
    int dy = p3.y - p1.y;
    Point p2 = new Point(p1.x + dx / 2 - dy / 2, p1.y + dx / 2 + dy / 2);

    // WRITE THE RECURSIVE CODE HERE:
    // g.drawLine(p1.x,p1.y,p2.x,p2.y);
    // g.drawLine(p3.x,p3.y,p2.x,p2.y);

    if (rank == 0) {
      g.drawLine(p1.x, p1.y, p3.x, p3.y);
    }
    
    else if (rank >= 1) {
      drawDragon(rank-1, p1, p2, g);
      drawDragon(rank-1, p3, p2, g);
    }
  }

  ////////////////////////////////////////////
  //// Graphics code below -- do not edit ////
  ////////////////////////////////////////////

  /**
   * Draws the dragon in the graphics window
   *
   * @param g The graphics object to draw into
   */
  public void paintComponent(Graphics g) {
    Point p1 = new Point(DIM / 4, DIM / 4);
    Point p2 = new Point(3 * DIM / 4, DIM / 4);
    drawDragon(rank, p1, p2, g);
  }

  /** Create a window with a dragon in it
   * @param rank (int) The rank of the line being drawn
   */
  public static void createAndShowGUI(int rank) {
    // Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create and set up the window.
    JFrame frame = new JFrame("Fractal Dragon");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add components
    Dragon dragon = new Dragon(rank);
    frame.getContentPane().add(dragon);

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
      rank = 15;
    }
    createAndShowGUI(rank);
  }
} // end of Dragon