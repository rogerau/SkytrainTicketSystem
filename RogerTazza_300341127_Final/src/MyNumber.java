
public class MyNumber {
	private int num;

	public MyNumber() {
		this.num = 1000;
	}

	public MyNumber(int num) {
		this.num = num;
	}

	public int digitAt(int pos) {
		String number = String.valueOf(num);
		if (pos == 0) {
			pos = number.length()-1;
		} else if (pos == 1) {
			pos = number.length()-2;
		} else if (pos == 2) {
			pos = number.length()-3;
		} else if (pos == 3) {
			pos = number.length()-4;
		} else if (pos == 4) {
			pos = number.length()-5;
		} else if (pos == 5) {
			pos = number.length()-6;
		} else if (pos == 6) {
			pos = number.length()-7;
		} else {
			pos = number.length()-8;
		}
		return Character.getNumericValue(number.charAt(pos));
	}
}
