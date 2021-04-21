public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int n = in.readInt();
		Planet[] pArr = new Planet[n];
		double radius = in.readDouble();
		for (int i = 0; i < n; i += 1) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFile = in.readString();
			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFile);
			pArr[i] = p;
		}
		return pArr;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] pArr = readPlanets(filename);

		String imgFile = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);

		StdDraw.clear();
		StdDraw.picture(0, 0, imgFile);
		for (Planet p: pArr) {
			p.draw();
		}
		StdDraw.show();
		//StdDraw.pause(1000);

		StdDraw.enableDoubleBuffering();

		double cur_time = 0;
		double[] xForce_arr = new double[pArr.length];
		double[] yForce_arr = new double[pArr.length];

		while (cur_time < T) {
			// calculate netForce
			for (int i = 0; i < pArr.length; i += 1) {
				xForce_arr[i] = pArr[i].calcNetForceExertedByX(pArr);
				yForce_arr[i] = pArr[i].calcNetForceExertedByY(pArr);
			}
			// update the location and valocity
			for (int i = 0; i < pArr.length; i += 1) {
				pArr[i].update(dt, xForce_arr[i], yForce_arr[i]);
			}
			// draw the background and the planets
			StdDraw.clear();
			StdDraw.picture(0, 0, imgFile);
			for (Planet p: pArr) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			cur_time += dt;
		}

		StdOut.printf("%d\n", pArr.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < pArr.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  pArr[i].xxPos, pArr[i].yyPos, pArr[i].xxVel,
		                  pArr[i].yyVel, pArr[i].mass, pArr[i].imgFileName);   
		}


	}

}