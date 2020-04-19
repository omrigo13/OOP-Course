package hw2;

public class RealScalar extends Scalar{
	
	public RealScalar(double num) {
		super(num);
	}

	public RealScalar() {
		super(0);
	}
	
	@Override
	public Scalar add(Scalar s) {
		return new RealScalar(this.getNum()+s.getNum());
	}

	@Override
	public Scalar mul(Scalar s) {
		return new RealScalar(this.getNum()*s.getNum());
	}

	@Override
	public Scalar pow(int exponent) {
		return new RealScalar(Math.pow(this.getNum(), exponent));
	}

	@Override
	public Scalar neg() {
		return new RealScalar(this.getNum()*(-1));
	}

	@Override
	public boolean equals(Scalar s) {
		return this.getNum() == s.getNum();
	}
	
	public String toString() {
		if(this.getNum() == 0)
			return "";
		return Double.toString(this.getNum());
	}
}
