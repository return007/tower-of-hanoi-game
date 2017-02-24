import java.awt.*;
import javax.swing.*;

public class Peg extends JComponent
{
	private int height;
	private int width;
	private Disc arr[];
	private int c;
	
	Peg(int w, int h){
		height = h;
		width = w;
		c = 0;
		arr = new Disc[10];
	}

	public void paint(Graphics g){
		g.setColor(new Color(45,23,89));
		g.fillRect((5*width)/100,(80*height)/100,(90*width)/100,30);

		g.setColor(new Color(152,12,2));
		g.fillRect((50*width)/100-10,(15*height)/100,20,(65*height)/100);

		int y = 0;
		for(;y<c;y++){
			g.setColor(Color.YELLOW);
			g.fillRect(arr[y].sx,(80*height)/100-arr[y].height*(y+1),arr[y].width,arr[y].height);
			g.setColor(Color.BLACK);
			g.drawRect(arr[y].sx,(80*height)/100-arr[y].height*(y+1),arr[y].width,arr[y].height);			
		}

	}

	public void addDisc(Disc a){
		arr[c++] = a;
	}

	public Disc removeDisc(){
		if(c > 0){
			c--;
			return arr[c];
		}
		return null;
	}

	public boolean isEmpty(){
		if(c == 0){
			return true;
		}
		return false;
	}
}