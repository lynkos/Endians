import java.io.IOException;

import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**Takes user input (hexadecimal/byte) and converts it to
 * Little-Endian or Big-Endian (depending on what's chosen).
 * 
 * @author lynkos
 * @version 1.0
 * */

public class EndianConvert {
	/**
	 * @param remove
	 * @return String without "0x" or "0X" in the beginning
	 */
	public static String no0x(String remove) {
		if(remove.substring(0, 2).equals("0x") || remove.substring(0, 2).equals("0X")) {
			remove = remove.substring(2, remove.length());
			}
		return remove;
		}
	
	/**
	 * @param reverse
	 * @return String who was converted from a String array that had its indexes reversed
	 * */
	public static String reverseArray(String[] reverse) {
		for(int i = 0; i < reverse.length / 2; i++) {
			String temp = reverse[i];
			reverse[i] = reverse[reverse.length - 1 - i];
			reverse[reverse.length - 1 - i] = temp;
			}
		return Arrays.toString(reverse).replaceAll("[\\s+\\,\\[\\]]", "");
		}
	
	public static void main(String[] args) throws IOException {
		/**
		 * Where the user is going to input what is to be converted
		 * */
		JTextField convertBox = new JTextField(10);
		
		/**
		 * The pencil, check, and X icons. Merely for aesthetic purposes
		 * */
		ImageIcon input = new ImageIcon("/Users/visitor/Desktop/Projects/Icons/Input.png");
		ImageIcon check = new ImageIcon("/Users/visitor/Desktop/Projects/Icons/Check.png");
		ImageIcon nope = new ImageIcon("/Users/visitor/Desktop/Projects/Icons/Nope.png");
		
		/**
		 * Initialises the first pop-up box (JPanel). Arranges the input box and text present
		 * */
		JPanel sweg = new JPanel();
        sweg.add(new JLabel("0x"));
		sweg.add(convertBox);
		sweg.add(new JLabel("Convert to: "));
	    String[] choices = { "Little-Endian", "Big-Endian" };
	    JComboBox dropdown = new JComboBox(choices);
	    dropdown.setSelectedIndex(0);
	    sweg.add(dropdown);
	    
	    /**
	     * Creates and shows the JPanel made
	     * */
        int result = JOptionPane.showConfirmDialog(null, sweg, "Enter Hexadecimal or Byte(s) Value", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, input);
        if(result == JOptionPane.OK_OPTION) {
        	/**
        	 * Gets user input and creates a new JPanel
        	 * */
        	String swag = convertBox.getText();
        	JPanel swiggity = new JPanel();
        	
        	/**
        	 * To make ensure that the user did not input nothing or whitespace character
        	 * (which could jeopordise the program)
        	 * */
        	if(!swag.equals("") && !swag.equals(" ")) {
        		String toConvert = no0x(swag.replaceAll("[\\s+\\,\\.]", ""));
        		
        		/**
        		 * If the user chose to convert the input to Big-Endian
        		 * */
        		if(dropdown.getSelectedItem().equals("Big-Endian")) {
        			JTextField orig = new JTextField("0x" + toConvert);
        			swiggity.add(orig);
        			JOptionPane.showConfirmDialog(null, swiggity, "Successfully Converted!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, check);
        			}
        		
        		/**
        		 * If the user chose to convert the input to Little-Endian
        		 * */
        		if(dropdown.getSelectedItem().equals("Little-Endian")) {
        			String[] reverse = new String[toConvert.length() / 2];
        			int j = 0;
        			
        			/**
        			 * Take the input, group it into pairs, and initialise
        			 * into the String array previously made
        			 * */
        			for(int i = 0; i < toConvert.length() / 2; i++) {
        				reverse[i] = toConvert.substring(j, j + 2);
        				j += 2;
        				}
        			
        			/**
        			 * Take the array, reverse, convert to String, then add "0x"
        			 * in front of it
        			 * */
        			String reversed = "0x" + reverseArray(reverse);
                	JTextField toCopy = new JTextField(reversed);
                	swiggity.add(toCopy);
                	JOptionPane.showConfirmDialog(null, swiggity, "Successfully Converted!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, check);
                	}
        		}
        	
        	/**
        	 * If the user did not input anything or only entered a whitespace character
        	 * */
        	if(swag.equals("") || swag.equals(" ")) {
        		JOptionPane.showConfirmDialog(null, "...And so you shall recieve nothing.", "You entered nothing...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, nope);
        		}
        	}
        }
	}
