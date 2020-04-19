package hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial{
	private ArrayList<PolyTerm> polynomial = new ArrayList<>();
	
	public Polynomial(ArrayList<PolyTerm> polynomial) {
		this.polynomial = polynomial;
	}
	
	public ArrayList<PolyTerm> getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(ArrayList<PolyTerm> polynomial) {
		this.polynomial = polynomial;
	}

	public Polynomial add(Polynomial poly) {
		Polynomial temp = new Polynomial(new ArrayList<>());
		PolyTerm temp2 = new PolyTerm();
		for(int i=0; i<this.getPolynomial().size();i++) {
			for(int j=0; j<poly.getPolynomial().size();j++) {
				if(this.getPolynomial().get(i).getExponent() == poly.getPolynomial().get(j).getExponent()) {
					temp2 = this.getPolynomial().get(i).add(poly.getPolynomial().get(j));
					temp.getPolynomial().add(temp2);
					break;
				}
				else
					if(j == poly.getPolynomial().size()-1)
						temp.getPolynomial().add(this.getPolynomial().get(i));
			}
		}
		for(int i=0; i<poly.getPolynomial().size();i++) {
			for(int j=0; j<this.getPolynomial().size();j++) {
				if(poly.getPolynomial().get(i).getExponent() == this.getPolynomial().get(j).getExponent())
					break;
				else
					if(j == this.getPolynomial().size()-1)
						temp.getPolynomial().add(poly.getPolynomial().get(i));
			}
		}
		if (this.getPolynomial().size() == 0)
			return poly;
		return temp;
	}

	public Polynomial mul(Polynomial poly) {
		Polynomial temp = new Polynomial(new ArrayList<>());
		PolyTerm temp2 = new PolyTerm();
		boolean check = true;
		for(int i=0;i<this.getPolynomial().size();i++) {
			{
				for(int j=0;j<poly.getPolynomial().size();j++) {
					temp2 = this.getPolynomial().get(i).mul(poly.getPolynomial().get(j));
					for(int m=0;m<temp.getPolynomial().size();m++) {
						if(temp2.getExponent() == temp.getPolynomial().get(m).getExponent()) {
							temp.getPolynomial().set(m,temp.getPolynomial().get(m).add(temp2));
							check = false;
							break;
						}
						else
							check = true;
					}
					if(check == true)
						temp.getPolynomial().add(temp2);
				}
			}
		}
		return temp;
	}
	
	public Scalar evaluate(Scalar scalar) {
		Scalar temp;
		if(this.getPolynomial().get(0).getS().getNum() == 0)
			temp = new RationalScalar();
		else
			temp = new RealScalar();
		for(int i=0;i<this.getPolynomial().size();i++) {
			temp = temp.add(this.getPolynomial().get(i).evaluate(scalar));
		}
		return temp;
	}
	
	public Polynomial derivate() {
		Polynomial temp = new Polynomial(new ArrayList<>());
		PolyTerm temp2 = new PolyTerm();
		for(int i=0;i<this.getPolynomial().size();i++) {
			temp2 = this.getPolynomial().get(i).derivate();
			temp.getPolynomial().add(temp2);
		}
		return temp;
	}
	
	public String toString() {
		String func = "";
		Collections.sort(this.polynomial);
		for(int i=0; i<this.getPolynomial().size();i++) {
			if(this.getPolynomial().get(i).getS().getA() == 0 && this.getPolynomial().get(i).getS().getNum() == 0 && this.getPolynomial().get(i).getExponent() != 0)
				func += "0";
			else {
				if(i-1 < this.getPolynomial().size() && i!=0) {
					if((this.getPolynomial().get(i).getS().getA() > 0 || this.getPolynomial().get(i).getS().getNum() > 0) && 
							(this.getPolynomial().get(i-1).getS().getA() != 0 || this.getPolynomial().get(i-1).getS().getNum() != 0))
								func += "+" + this.getPolynomial().get(i).toString();
					else
						func += this.getPolynomial().get(i).toString();
				}
				else
					func += this.getPolynomial().get(i).toString();
			}
		}
		return func;
	}
}

