
public class ComputeScore {

	String sequence = null;
	int lastFrame;
	
	public ComputeScore(String sequence) {
		this.sequence = sequence;
	}
	
	public int computeScore() {
		
		setLastFrame();
		int sum = 0;
		int frame = 0;
		
		while (frame < lastFrame) {
			sum += getFramePoints(frame);
			frame++;
		}
		return sum;
	}
	
	private void setLastFrame() {
		char[] chars = sequence.toCharArray();
		Character strikeChar = chars[chars.length - 3];
		Character spareChar = chars[chars.length - 2];
		
		if (strikeChar.equals('X')) 
			lastFrame = chars.length - 2;
		else if (spareChar.equals('/'))
			lastFrame = chars.length - 1;
		else 
			lastFrame = chars.length;
	}
	
	private int getFramePoints(int index) {
		Character ch = sequence.charAt(index);
		int points = 0;
		
		switch (ch) {
		case 'X':
			points = getXtrikePoints(index);
			break;
		case '/':
			points = getSparePoints(index);
			break;
		default:
			points = getInteger(sequence.charAt(index));
		}
		return points;
	}

	private int getXtrikePoints(int index) {
		int strikeMax = 10;
		int bonus1 = getValue(index + 1);
		int bonus2 = getValue(index + 2);
		int xtrikePoints = strikeMax + bonus1 + bonus2;
		return xtrikePoints;
	}

	private int getSparePoints(int index) {
		int turn2 = getValue(index);
		int bonus1 = getValue(index + 1);
		int sparePoints = turn2 + bonus1;
		return sparePoints;
	}

	private int getValue(int index) {
		Character ch = sequence.charAt(index);
		int value = 0;
		
		switch (ch) {
		case 'X':
			value = 10;
			break;
		case '/':
			value = 10 - getInteger(sequence.charAt(index - 1));
			break;
		case '-':
			value = getInteger(sequence.charAt(index - 1));
			break;
		default :
				value = getInteger(ch);
		}
		return value;
	}
	
	private int getInteger(Character ch) {
		if (Character.isDigit(ch))
			return Character.getNumericValue(ch);
		else
			return 0;
	}
	
}
