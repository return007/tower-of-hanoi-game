public class Disc
{

	public int sx;
	public int height;
	public int width;

	Disc(int x, int a, int b){
		sx = x;
		width = a;
		height = b;
	}

	public int compare(Disc b){
		return this.width-b.width;
	}
}