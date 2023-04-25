package ie.tudublin;

import processing.core.PApplet;

public class Resistor
{
    int value;
    UI ui; //this is an instance of the ui class
    Color hc;
    Color tc;
    Color oc;
           /*
            * These colour variables are used in our split resistance to calculate the colour
            */


    /*
     * This Resistor method is passed an instance of ui and the resistance we read in
     */
    public Resistor(UI ui, int resistance)
    {
        /*
         * Inside this class the resistance value is split into its seperate digits and those digits are assigned values
         * 
         */
        this.ui = ui;
        this.value = resistance;
        int hundreds = (resistance / 100);
        int tens = (resistance - (hundreds * 100)) / 10;
        int ones = resistance - ((hundreds * 100)  + (tens * 10));
        hc = ui.findColor(hundreds);
        tc = ui.findColor(tens);
        oc = ui.findColor(ones); //these access the findColour method to return our values
        
    }
    /*
     * This render method takes two values meaning we have values for the x and y positions of our drawn resistors
     */
    public void render(float x, float y)
    {
        /*
         * pushMatrix explanation
         * when called it saves rhe transformation matrix and pushes it on to the matrix stack
         * popMastrix can be called when you want to go back to the original transformation
         * this means everytime an instance of ui is run pushMatrix will save all the changes 
         * translate is then called which applies those changes 
         * this just means the x and y values are just moved for our drawings
         * pop is called at the end which essentially empties the stack
         * meaning we end up with no mix matching of translations
         * 
         */
        ui.pushMatrix();
        ui.translate(x, y);
        //all of this draws the resistor bands (see how theres 3)
        ui.stroke(0);
        ui.line(-100, 0, -50, 0);
        ui.line(-50, 0, -50, -50);
        ui.line(-50, -50, 50, -50);
        
        ui.line(50, -50, 50, 0);
        ui.line(50, 0, 100, 0);
        ui.line(50, 0, 50, 50);
        ui.line(50, 50, -50, 50);
        ui.line(-50, 50, -50, 0);

        // Draw the color bars
        ui.noStroke();
        ui.fill(hc.r, hc.g, hc.b);
        ui.rect(-40, -49, 10, 99);
        
        ui.fill(tc.r, tc.g, tc.b);
        ui.rect(-20, -49, 10, 99);
        
        ui.fill(oc.r, oc.g, oc.b);
        ui.rect(0, -49, 10, 99);
        ui.fill(0);
        //then this aligns and stlyles our text 
        //simples (not really)
        ui.textAlign(PApplet.CENTER, PApplet.CENTER);
        ui.textSize(30);
        ui.text(value, 200, 0);
        ui.popMatrix();
    }
}