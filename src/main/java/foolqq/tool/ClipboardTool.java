package foolqq.tool;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipboardTool {
	
	 public static String getSystemClipboard(){ 
	        Clipboard sysClb=null;  
	        sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();  
	        try {  
	        Transferable t = sysClb.getContents(null);  
	            if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {   
	            String text = (String)t.getTransferData(DataFlavor.stringFlavor);   
	            return text;   
	            }   
	        } catch ( Exception e) {  
	            e.printStackTrace();
	        } 
	        return null;   
	    }  
	
	    public static void setSysClipboardText(String msg) {  
	        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
	        Transferable tText = new StringSelection(msg);  
	        clip.setContents(tText, null);  
	    }  
}
