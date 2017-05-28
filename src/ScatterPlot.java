//// (c) Copyright 1997 Peter Sandborn ALL RIGHTS RESERVED
//
////package savantage.awt;
////import savantage.dialog.*;
////import savantage.util.*;
////import savantage.data.*;
//        import java.awt.*;
//        import java.awt.image.*;
//        import java.lang.Math;
//        import java.applet.*;
////import gjt.Border;
//
//
////------------------------------------------------------------------------------
//// ScatterPlot.java:
////		Creates a dialog box for displaying a scatter plot
////------------------------------------------------------------------------------
//
//public class ScatterPlot extends Frame
//{
//    private Applet parent;
//    private Button ok_button;
//    private Button clear_button;
//    //private Button color_button;
//    private Choice color_choice;
//    private Panel titlePanel;
//
//    private int number_points;
//    private String x_axis;
//    private String y_axis;
//    private double[] x_values;
//    private double[] y_values;
//    private Color[] c_values;
//
//    private double L;
//    private Color point_color;
//
//    double max_x;
//    double min_x;
//    double max_y;
//    double min_y;
//    int bottom_axis;  // y location of horizontal axis
//    int left_axis;  // x location of vertical axis
//    int length_x_axis;
//    int length_y_axis;
//
//    // Constructor
//    //--------------------------------------------------------------------------
//    public ScatterPlot (Applet p,String topic)
//    {
//        super("Scatter Plot");
//        parent = p;
//        L = 9999999.0;
//
//        // Title
//        titlePanel = new Panel();
//        Label label = new Label(topic);
//        label.setFont(new Font("Helvetica",Font.BOLD,14));
//        titlePanel.add(label);
//        add("North",titlePanel);
//
//        // Buttons
//        Panel buttonPanel = new Panel();
//        ok_button = new Button("OK");
//        //Border blackBorder = new Border(ok_button,1);
//        //blackBorder.setLineColor(parent.sdacolor.buttonborder);
//        clear_button = new Button("Clear Plot");
//        //help_button = new helpButton(parent,parent.language.help);
//        //color_button = new Button("Change Color");
//        buttonPanel.setLayout(new FlowLayout());
//        buttonPanel.add(ok_button);
//        buttonPanel.add(clear_button);
//        color_choice = new Choice();
//        color_choice.addItem("Red");
//        color_choice.addItem("Blue");
//        color_choice.addItem("Green");
//        color_choice.addItem("Black");
//        buttonPanel.add(color_choice);
//        //buttonPanel.add(color_button);
//        add("South",buttonPanel);
//
//        //setBackground(parent.sdacolor.dialogbackground);
//        reshape(0,0,150,200);  // does nothing
//    }
//
//
//    // n = number of points
//    // values_x = array of x values
//    // values_y = array of y values
//    // x = x axis label
//    // y = y axis label
//    public void loadScatterPlotData(String x,String y,double max,double mix,double may,double miy)
//    {
//        x_axis = x;
//        y_axis = y;
//        min_x = mix;
//        max_x = max;
//        min_y = miy;
//        max_y = may;
//        number_points = 0;  // initialization
//
//        x_values = new double[1000];
//        y_values = new double[1000];
//        c_values = new Color[1000];
//
//        point_color = Color.red;
//    }
//
//    public void addPoint(double x,double y)
//    {
//        x_values[number_points] = x;
//        y_values[number_points] = y;
//        if(color_choice.getSelectedItem().compareTo("Red") == 0)
//            point_color = Color.red;
//        if(color_choice.getSelectedItem().compareTo("Blue") == 0)
//            point_color = Color.blue;
//        if(color_choice.getSelectedItem().compareTo("Green") == 0)
//            point_color = Color.green;
//        if(color_choice.getSelectedItem().compareTo("Black") == 0)
//            point_color = Color.black;
//        c_values[number_points] = point_color;
//        number_points++;
//        drawScatterPlot();
//    }
//
//
//    public boolean drawScatterPlot()
//    {
//        repaint();
//        return true;
//    }
//
//    public void paint(Graphics g)
//    {
//        Image im;
//        Graphics ig;
//        FilteredImageSource source;
//        Image si;
//        FontMetrics fm = getFontMetrics(getFont());
//
//        // Get drawing area size
//        int top_border;
//        int bottom_border;
//        int left_border;
//        int right_border;
//        //if(System.getProperty("java.vendor").compareTo("Netscape Communications Corporation") == 0 && System.getProperty("os.name").compareTo("SunOS") == 0)  {
//        top_border = 70;
//        bottom_border = 10;
//        //}  else  {
//        //top_border = 30;
//        //bottom_border = 60;
//        //}
//        int dim = 0;  // smallest drawing area dimension
//        Dimension dimension = size();
//        int x_dim = dimension.width;
//        int y_dim = dimension.height-(top_border+bottom_border);
//        dim = x_dim;
//        if(dim > y_dim)  {
//            dim = y_dim;
//        }
//
//        // Set offsets
//        left_border = 60; //35;
//        right_border = 25;
//
//        // Draw plot axes
//        bottom_axis = 3*(y_dim+top_border)/4;  // y location of horizontal axis
//        left_axis = left_border;  // x location of vertical axis
//        length_x_axis = x_dim - left_border - 25;
//        length_y_axis = bottom_axis - top_border;
//        g.drawLine(left_axis,top_border,left_axis,bottom_axis);
//        g.drawLine(left_axis,bottom_axis,left_axis+length_x_axis,bottom_axis);
//
//        // Label axes
//        int j = fm.stringWidth(x_axis);  // length of the x_axis label
//        g.drawString(x_axis,left_border+length_x_axis/2-j/2,bottom_axis+2+3*fm.getHeight());
//        im = createImage(fm.stringWidth(y_axis)+2,fm.getHeight());
//        ig = im.getGraphics();
//        ig.setColor(Color.white);
//        ig.fillRect(0,0,100,100);
//        ig.setColor(Color.black);
//        ig.drawString(y_axis,1,10);
//        source = new FilteredImageSource(im.getSource(),new Rotate90DegreeFilter());
//        si = createImage(source);
//        g.drawImage(si,12,top_border+length_y_axis/2-(fm.stringWidth(y_axis)+2)/2,null);
//
//        int tic_length = 10;
//        // Add scales to x Axis
//        for(int i = 0; i <= 10; i++)  {
//            g.drawLine(left_axis+length_x_axis*i/10,bottom_axis-tic_length/2,left_axis+length_x_axis*i/10,bottom_axis+tic_length/2);
//            Integer ii = new Integer(i*10);
//            int jj = fm.stringWidth(ii.toString());
//            g.drawString(ii.toString(),left_axis+length_x_axis*i/10-jj/2,bottom_axis+4*tic_length/2);
////        }
//
//        // Add scales to y Axis
//        for(int i = 0; i <= 10; i++)  {
//            g.drawLine(left_axis-tic_length/2,bottom_axis-length_y_axis*i/10,left_axis+tic_length/2,bottom_axis-length_y_axis*i/10);
//            Integer ii = new Integer(i*10);
//            int jj = fm.stringWidth(ii.toString());
//            g.drawString(ii.toString(),left_axis-tic_length-jj,bottom_axis-length_y_axis*i/10+fm.getHeight()/2);
//        }
//
//        // Plot points
//        for(int i = 0; i < number_points; i++)  {
//            g.setColor(c_values[i]);
//            g.fillRect(translatex(x_values[i]),translatey(y_values[i]),4,4);
//        }
//
//    }
//
//    // Translate the array data values to screen locations
//    private int translatex(double x)
//    {
//        return((int) (left_axis + ((x-min_x)/(max_x-min_x))*length_x_axis));
//    }
//    private int translatey(double y)
//    {
//        return((int) (bottom_axis - ((y-min_y)/(max_y-min_y))*length_y_axis));
//    }
//
//    // Catch events in the dialog box
//    //--------------------------------------------------------------------------
//    public boolean action(Event event,Object obj)
//    {
//        Object target = event.target;
//
//        if(target instanceof Button)
//        {
//            Button button = (Button)target;
//            String buttonLabel = button.getLabel();
//            if(target == ok_button)
//            {
//                dispose();
//            }
//            if(target == clear_button)
//            {
//                number_points = 0;
//                repaint();
//            }
//
//            return true;
//        }
//        return false;
//
//    }
//
//
//    public boolean keyDown (Event event,int key)
//    {
//        // Regular return (enter) key
//        if(event.id == event.KEY_PRESS && key == '\n')  {
//            hide();
//        }
//        return true;
//    }
//
//    public void popup()
//    {
//        if(System.getProperty("java.vendor").compareTo("Netscape Communications Corporation") == 0 && System.getProperty("os.name").compareTo("SunOS") == 0)  {
//            show();
//            resize(400,400);
//            refresh_dialog();
//            titlePanel.requestFocus();
//        }  else  {
//            show();
//            resize(400,400);
//            titlePanel.requestFocus();
//        }
//    }
//
//    private void refresh_dialog()
//    {
//        // Jitter the size of the form to force a refresh - necessary for NetScape
//
//        // Get actuals here
//        //Dimension dimension = size();
//        int x = 400; //dimension.width;
//        int y = 400; //dimension.height;
//
//        // Resize
//        resize(x+100,y+100);
//        show();
//
//        // Reset current size
//        resize(x,y);
//
//    }
//
//}
