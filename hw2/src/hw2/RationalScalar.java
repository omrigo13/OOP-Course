package hw2;

public class RationalScalar extends Scalar{
	
	public RationalScalar(int a, int b) {
		super(a,b);
	}
	
	public RationalScalar() {
		super(0);
	}
	
	@Override
	public Scalar add(Scalar s) {
		if(this.getB() == s.getB())
			return new RationalScalar(this.getA()+s.getA(), this.getB());
		if(this.getA() == 0 && this.getB() == 0)
			return new RationalScalar(s.getA(), s.getB());
		return new RationalScalar(this.getA()*s.getB()+s.getA()*this.getB(), this.getB()*s.getB());
	}
	
	@Override
	public Scalar mul(Scalar s) {
		return new RationalScalar(this.getA()*s.getA(), this.getB()*s.getB());
	}
	
	@Override
	public Scalar pow(int exponent) {
		return new RationalScalar((int)(Math.pow(this.getA(), exponent)),(int)(Math.pow(this.getB(), exponent)));
	}
	
	@Override
	public  Scalar neg() {
		return new RationalScalar(this.getA()*(-1), this.getB());
	}
	
	@Override
	public boolean equals(Scalar s) {
		return (double)this.getA()/this.getB() == (double)s.getA()/s.getB();
	}
	
	public String toString() {
		if(this.getA() == 0)
			return "";
		if(this.getB() == 1)
			return "" + this.getA();
		return this.getA()+"/"+this.getB();
	}
}
