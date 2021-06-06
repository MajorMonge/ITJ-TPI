/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itj.tpi;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

/**
 *
 * @author luizf
 */
public class ITJTPI {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.awt.FontFormatException
     */
    public static void main(String[] args) throws IOException, FontFormatException {

        System.setProperty("awt.useSystemAAFontSettings", "on");
        MainFrame Menu = new MainFrame();
        Menu.setVisible(true);
    }

}
