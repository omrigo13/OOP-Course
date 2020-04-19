package hw2;
import java.util.*;

public class Calculator {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int option = 0;
		char option2;
		String first = "",second;
		Scalar scalar1,scalar2;
		int exp1 = 0, exp2 = 0;
		PolyTerm pol1, pol2;
		String[] pl1,pl2;
		Polynomial p1 = new Polynomial(new ArrayList<>());
		Polynomial p2 = new Polynomial(new ArrayList<>());
		ArrayList<PolyTerm> pFirst = new ArrayList<>();
		ArrayList<PolyTerm> pSecond = new ArrayList<>();
		while(option!=5) {
			System.out.println("Please select an operation:");
			System.out.println("1. Addition");
			System.out.println("2. Multiplication");
			System.out.println("3. Evaluation");
			System.out.println("4. Derivate");
			System.out.println("5. Exit");
			option = reader.nextInt();
			if(option == 1 || option == 2) {
				scalar1 = null;
				scalar2 = null;
				System.out.println("Please select the scalar field\r\n" + 
						"Rational (Q) or Real (R)");
				option2 = reader.next().charAt(0);
				System.out.println("Please insert the first polynomial");
				first = reader.next();
				System.out.println("Please insert the second polynomial");
				second = reader.next();
				first = first.replace("-", "+-");
				second = second.replace("-", "+-");
				pl1 = first.split("[+]");
				pl2 = second.split("[+]");
				for(int i=0;i<pl1.length;i++)
					if(pl1[i].contains("/") == true && pl1[i].indexOf("/") == pl1[i].length()-1) {
						pl1[i] += pl1[i+1];
						pl1[i+1] = "";
						if(pl1[i].substring(0, 1).compareTo(pl1[i].substring(pl1[i].indexOf("/")+1,pl1[i].indexOf("/")+2)) == 0)
							pl1[i] = "" + pl1[i].substring(1, pl1[i].indexOf("/")+1) + pl1[i].substring(pl1[i].indexOf("/")+2);
						if(pl1[i].substring(0, 1).compareTo("-") != 0 && pl1[i].substring(pl1[i].indexOf("/")+1,pl1[i].indexOf("/")+2).compareTo("-") == 0)
							pl1[i] = "-" + pl1[i].substring(0, pl1[i].indexOf("/")+1) + pl1[i].substring(pl1[i].indexOf("/")+2);
					}
				for(int i=0;i<pl2.length;i++)
					if(pl2[i].contains("/") == true && pl2[i].indexOf("/") == pl2[i].length()-1) {
						pl2[i] += pl2[i+1];
						pl2[i+1] = "";
						if(pl2[i].substring(0, 1).compareTo(pl2[i].substring(pl2[i].indexOf("/")+1,pl2[i].indexOf("/")+2)) == 0)
							pl2[i] = "" + pl2[i].substring(1, pl2[i].indexOf("/")+1) + pl2[i].substring(pl2[i].indexOf("/")+2);
						if(pl2[i].substring(0, 1).compareTo("-") != 0 && pl2[i].substring(pl2[i].indexOf("/")+1,pl2[i].indexOf("/")+2).compareTo("-") == 0)
							pl2[i] = "-" + pl2[i].substring(0, pl2[i].indexOf("/")+1) + pl2[i].substring(pl2[i].indexOf("/")+2);
					}
				for(String poly: pl1) {
					if(poly.compareTo("") == 0)
						continue;
					if(poly.contains("x") != true) {
						exp1 = 0;
						if(option2 == 'R' || option2 == 'Q') {
							if(poly.contains("/") == false)
								scalar1 = new RealScalar(Double.valueOf(poly.format("%.3f",Double.valueOf(poly))));
							else
								scalar1 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1)));
						}
						pol1 = new PolyTerm(scalar1, exp1);
						pFirst.add(pol1);
						p1 = new Polynomial(pFirst);
					}
					else {
						if(poly.substring(0, poly.indexOf("x")).compareTo("") == 0) {
							if(option2 == 'R' || option2 == 'Q') {
								scalar1 = new RealScalar(1);
								scalar1.setA(1);
								scalar1.setB(1);
							}
							else {
								scalar1 = new RationalScalar(1, 1);
								scalar1.setNum(1);
							}
						}
						else{
							if(option2 == 'R' || option2 == 'Q') {
								if(poly.contains("/") == false)
									scalar1 = new RealScalar(Double.valueOf(poly.substring(0, poly.indexOf("x")).format("%.3f", Double.valueOf(poly.substring(0, poly.indexOf("x"))))));
								else
									scalar1 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1, poly.indexOf("x"))));
							}
						}
						exp1 = Integer.parseInt((poly.substring(poly.length()-1)));
						pol1 = new PolyTerm(scalar1, exp1);
						pFirst.add(pol1);
						p1 = new Polynomial(pFirst);
					}
				}
				for(String poly: pl2) {
					if(poly.compareTo("") == 0)
						continue;
					if(poly.contains("x") != true) {
						exp2 = 0;
						if(option2 == 'R' || option2 == 'Q') {
							if(poly.contains("/") == false)
								scalar2 = new RealScalar(Double.valueOf(poly.format("%.3f",Double.valueOf(poly))));
							else
								scalar2 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1)));
						}
						pol2 = new PolyTerm(scalar2, exp2);
						pSecond.add(pol2);
						p2 = new Polynomial(pSecond);
					}
					else {
						if(poly.substring(0, poly.indexOf("x")).compareTo("") == 0) {
							if(option2 == 'R' || option2 == 'Q') {
								scalar2 = new RealScalar(1);
								scalar2.setA(1);
								scalar2.setB(1);
							}
							else {
								scalar2 = new RationalScalar(1, 1);
								scalar2.setNum(1);
							}
						}
						else{
							if(option2 == 'R'|| option2 == 'Q') {
								if(poly.contains("/") == false)
									scalar2 = new RealScalar(Double.valueOf(poly.substring(0, poly.indexOf("x")).format("%.3f", Double.valueOf(poly.substring(0, poly.indexOf("x"))))));
								else
									scalar2 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1, poly.indexOf("x"))));
							}
						}
						exp2 = Integer.parseInt((poly.substring(poly.length()-1)));
						pol2 = new PolyTerm(scalar2, exp2);
						pSecond.add(pol2);
						p2 = new Polynomial(pSecond);
					}
				}
				System.out.println("The solution is:");
				if (option == 1)
					System.out.println(p1.add(p2).toString());
				else
					System.out.println(p1.mul(p2).toString());
				pFirst = new ArrayList<>();
				pSecond = new ArrayList<>();
			}
			if(option == 3 || option == 4) {
				scalar1 = null;
				scalar2 = null;
				System.out.println("Please select the scalar field\r\n" + 
						"Rational (Q) or Real (R)");
				option2 = reader.next().charAt(0);
				System.out.println("Please insert the polynomial");
				first = reader.next();
				first = first.replace("-", "+-");
				pl1 = first.split("[+]");
				for(int i=0;i<pl1.length;i++)
					if(pl1[i].contains("/") == true && pl1[i].indexOf("/") == pl1[i].length()-1) {
						pl1[i] += pl1[i+1];
						pl1[i+1] = "";
						if(pl1[i].substring(0, 1).compareTo(pl1[i].substring(pl1[i].indexOf("/")+1,pl1[i].indexOf("/")+2)) == 0)
							pl1[i] = "" + pl1[i].substring(1, pl1[i].indexOf("/")+1) + pl1[i].substring(pl1[i].indexOf("/")+2);
						if(pl1[i].substring(0, 1).compareTo("-") != 0 && pl1[i].substring(pl1[i].indexOf("/")+1,pl1[i].indexOf("/")+2).compareTo("-") == 0)
							pl1[i] = "-" + pl1[i].substring(0, pl1[i].indexOf("/")+1) + pl1[i].substring(pl1[i].indexOf("/")+2);
					}
				if(option == 3) {
					System.out.println("Please insert the Scalar");
					second = reader.next();
					String part1;
					String part2;
					if(second.contains("/") == true) {
						part1 = second.substring(0,second.indexOf("/"));
						part2 = second.substring(second.indexOf("/")+1);
						scalar2 = new RationalScalar(Integer.valueOf(part1), Integer.valueOf(part2));
					}
					else
						scalar2 = new RealScalar(Double.valueOf(second.format("%.3f",Double.valueOf(second))));
				}
				for(String poly: pl1) {
					if(poly.compareTo("") == 0)
						continue;
					if(poly.contains("x") != true) {
						exp1 = 0;
						if(option2 == 'R' || option2 == 'Q') {
							if(poly.contains("/") == false)
								scalar1 = new RealScalar(Double.valueOf(poly.format("%.3f",Double.valueOf(poly))));
							else
								scalar1 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1)));
						}
						pol1 = new PolyTerm(scalar1, exp1);
						pFirst.add(pol1);
						p1 = new Polynomial(pFirst);
					}
					else {
						if(poly.substring(0, poly.indexOf("x")).compareTo("") == 0) {
							if(option2 == 'R') {
								scalar1 = new RealScalar(1);
								scalar1.setA(1);
								scalar1.setB(1);
							}
							else {
								scalar1 = new RationalScalar(1, 1);
								scalar1.setNum(1);
							}
						}
						else {
							if(option2 == 'R'|| option2 == 'Q')
								if(poly.contains("/") == false)
									scalar1 = new RealScalar(Double.valueOf(poly.substring(0, poly.indexOf("x")).format("%.3f", Double.valueOf(poly.substring(0, poly.indexOf("x"))))));
								else
									scalar1 = new RationalScalar(Integer.valueOf(poly.substring(0, poly.indexOf("/"))),Integer.valueOf(poly.substring(poly.indexOf("/")+1, poly.indexOf("x"))));
						}
						exp1 = Integer.parseInt((poly.substring(poly.length()-1)));
						pol1 = new PolyTerm(scalar1, exp1);
						pFirst.add(pol1);
						p1 = new Polynomial(pFirst);
					}
				}
				if(option == 3) {
					System.out.println("The solution is:");
					System.out.println(p1.evaluate(scalar2).toString());
				}
				else {
					System.out.println("The derivative polynomial is:");
					System.out.println(p1.derivate().toString());
				}
				pFirst = new ArrayList<>();
			}	
		}
	}
}