package hw2;

import java.util.Comparator;

public class PolyTerm implements Comparable<PolyTerm>{
	private Scalar s;
	private int exponent;
	
	public PolyTerm(Scalar s, int exponent) {
		this.s = s;
		this.exponent = exponent;
	}
	
	public PolyTerm() {
	this.s = null;
	this.exponent = 0;
}
	
	public Scalar getS() {
		return s;
	}

	public void setS(Scalar s) {
		this.s = s;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	
	public boolean canAdd(PolyTerm pt) {
		return pt.getExponent() == this.getExponent();
	}
	
	public PolyTerm add(PolyTerm pt) {
		if (this.canAdd(pt))
			return new PolyTerm(this.getS().add(pt.getS()), exponent);
		return null;
	}
	
	public PolyTerm mul(PolyTerm pt) {
		return new PolyTerm(this.getS().mul(pt.getS()), this.getExponent()+pt.getExponent());
	}
	
	public Scalar evaluate(Scalar scalar) {
		return scalar.pow(this.getExponent()).mul(this.getS());			
	}
	
	public PolyTerm derivate() {
		if(this.getExponent() == 0)
			return new PolyTerm(new RealScalar(0), 0);
		if(this.getExponent() == 1 && this.getS().getNum() == 0)
			return new PolyTerm(new RationalScalar(this.getS().getA(), this.getS().getB()),0);
		if(this.getExponent() == 1 && this.getS().getNum() != 0)
			return new PolyTerm(new RealScalar(this.getS().getNum()),0);
		if (this.getS().getNum() == 0 )
			return new PolyTerm(new RationalScalar(this.getS().getA()*this.getExponent(), this.getS().getB()), exponent-1);
		return new PolyTerm(this.getS().mul(new RealScalar(this.getExponent())), exponent-1);
	}
	
	public boolean equals(PolyTerm pt) {
		return this.getS() == pt.getS() && this.getExponent() == pt.getExponent();
	}
	
	public String toString() {
		if(this.getExponent()==0)
			return this.s.toString();
		return this.s.toString()+"x^"+this.getExponent();
	}

	@Override
	public int compareTo(PolyTerm p) {
		return (this.getExponent()-p.getExponent());
	}
}
