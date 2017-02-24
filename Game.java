import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements MouseListener
{

	private final int W = 900;
	private final int H = 600;
	private Peg peg[];
	private Disc d[];
	private Peg prevPeg,currPeg;
	private int countClicks = 0;
	private int n;

	Game(String name, int o)
	{
		super(name);
		setLayout(null);
		n = o;

		peg = new Peg[3];
		d = new Disc[n];

		setSize(W,H);
		peg[0] = new Peg(W/3,H);
		add(peg[0]);
		peg[1] = new Peg(W/3,H);
		add(peg[1]);
		peg[2] = new Peg(W/3,H);
		add(peg[2]);

		peg[0].setBounds(0,0,W/3,H);
		peg[1].setBounds(W/3,0,W/3,H);
		peg[2].setBounds(2*W/3,0,W/3,H);

		peg[0].addMouseListener(this);
		peg[1].addMouseListener(this);
		peg[2].addMouseListener(this);

		for(int i=0;i<n;i++){
			d[i] = new Disc(((20+4*i)*(W/3))/100 , ((60-8*i)*(W/3))/100, 25);
		}

		for(Disc p : d){
			peg[0].addDisc(p);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Peg getSelectedPeg(MouseEvent e){
		if(peg[0] == e.getSource()){
			return peg[0];
		}
		if(peg[1] == e.getSource()){
			return peg[1];
		}
		if(peg[2] == e.getSource()){
			return peg[2];
		}
		return null;
	}

	public void mouseClicked(MouseEvent e){
		countClicks++;
		if(countClicks == 1){
			prevPeg = getSelectedPeg(e);
			if(prevPeg.equals(null) || prevPeg.isEmpty()){
				countClicks = 0;
			}
			return;
		}

		currPeg = getSelectedPeg(e);
		if(prevPeg != currPeg){

			if(currPeg.equals(null)){
				return;
			}

			if(currPeg.isEmpty()){
				currPeg.addDisc(prevPeg.removeDisc());
			}
			else{
				Disc d1 = currPeg.removeDisc();
				Disc d2 = prevPeg.removeDisc();

				if(d1.compare(d2) > 0){
					currPeg.addDisc(d1);
					currPeg.addDisc(d2);
				}
				else{
					currPeg.addDisc(d1);
					prevPeg.addDisc(d2);
				}
			}
		}
		countClicks = 0;
		this.repaint();

		if(peg[0].isEmpty() && peg[1].isEmpty()){
			System.out.println("You nailed it bro!!! Genius my brother.");
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	public static void main(String args[])
	{
		System.out.print("Enter n : ");
		Scanner sc = new Scanner(System.in);
		int ln = sc.nextInt();
		new Game("Tower Of Hanoi",ln);
	}
}