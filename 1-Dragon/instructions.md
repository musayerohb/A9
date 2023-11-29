# A Fractal Dragon

For this project, you will write a program that draws a shape called a [dragon curve](https://en.wikipedia.org/wiki/Dragon_curve). This is a linear fractal that connects two points. A particular dragon can be identified by a number corresponding to its complexity, called its rank or order. The order-0 and order-1 dragons are shown below.

![Order 0 dragon](images/dragon0.png)
![Order 1 dragon](images/dragon1.png)

For order k > 1, the dragon is defined recursively. Consider the diagram below:

![Dragon points](images/dragon_pts.png)

The order k dragon between p1 and p3 consists of two order k-1 dragons, one between points p1 and p2 and the other between points p3 and p2. Thus the order-2 dragon looks like the image below. Note how each of the line segments in the order-1 dragon has been replaced by two shorter segments at a 90 degree angle; each of these is an order-1 dragon. The order-1 dragon itself is composed of two order-0 dragons, which are straight lines.

![Order 2 dragon](images/dragon2.png)

Given points p1 and p3, point p2 may be found via some simple vector computations. Consider the vector p1p3 = (dx,dy). The perpendicular vector is (-dy,dx). Point p2 is thus p1+(dx/2-dy/2,dx/2+dy/2).

## Coding the Dragon

A fractal dragon of order k can easily be displayed using a recursive program. Starting with the initial file `Dragon.java`, your job is to make it happen. (Note: for convenience, this code includes graphics features all in the same class. Also note that your dragon will appear upside-down because the y axis of the graphics plane is inverted.)

When you have your program running, it is interesting to try to predict what will happen if you (temporarily) comment out one or the other of the two recursive calls. Make a prediction in your head and test it out. This is a good trial of whether you truly understand recursion.

## More Fun

The dragon described here is the traditional fractal dragon. If you'd like to explore other fractal shapes, we've also included two others in `Seahorse.java` and `Snowflake.java`.  The fractal seahorse recursively replaces each segment with a three-segment unit.

![Seahorse points](images/seahorse_pts.png)

The coordinates of the points p2 and p3 are given by these formulas:
    
    x2 = x1+dx/4-dy/4
    y2 = y1+dx/4+dy/4
    x3 = x4-dx/4+dy/4
    y3 = y4-dx/4-dy/4

The snowflake replaces each with a four-segment unit.

    x2 = x1+dx/3
    y2 = y1+dy/3
    x3 = x1+dx/2+dy*sqrt(3)/6
    y3 = y1-dx*sqrt(3)/6+dy/2
    x4 = x5-dx/3
    y4 = y5-dy/3