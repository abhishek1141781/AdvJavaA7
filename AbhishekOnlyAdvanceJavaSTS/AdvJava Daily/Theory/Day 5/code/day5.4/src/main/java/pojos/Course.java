package pojos;

public enum Course {
	JAVA(70), REACT(78), FULL_STACK(84), PYTHON(73);

	private int minScore;

	private Course(int minScore) {
		this.minScore = minScore;
	}

	// getter
	public int getMinScore() {
		return minScore;
	}

}
