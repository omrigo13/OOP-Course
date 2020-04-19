package hw2;

public abstract class Scalar {
	 private int a;
	 private int b;
	 private double num;
	
	 public Scalar(double num) {
		this.num = num;
	}

	public Scalar(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	/**
     * accepts a scalar argument
	 * @return a scalar which is the sum of the current scalar and the argument
     */
    public abstract Scalar add(Scalar s);

    /**
     * accepts a scalar argument
     * @return a scalar which is the multiplication of the current scalar and the argument
     */
    public abstract Scalar mul(Scalar s);

    /**
     * accepts an integer argument
     * @return a scalar which is the power of the current scalar by the exponent
     */
    public abstract Scalar pow(int exponent);
    
    /**
     * @return a scalar which is the result of multiplying the current scalar by (-1)
     */
    public abstract Scalar neg();
    
    /**
     * @return true if the argument Scalar and the current Scalar have the numeric same value
     */
    public abstract boolean equals(Scalar s);
}
