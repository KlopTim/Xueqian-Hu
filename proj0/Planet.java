public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double distance = Math.pow(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2), 0.5);
		return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double g = 6.67e-11;
		double force = p.mass * mass * g / Math.pow(this.calcDistance(p),2);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double forceX = this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
		return forceX;
	}

	public double calcForceExertedByY(Planet p) {
		double forceY = this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] pArr) {
		double total_forceX = 0;
		for (Planet p: pArr) {
			if (!this.equals(p)) {
				total_forceX += this.calcForceExertedByX(p);
			}
		}
		return total_forceX;
	}

	public double calcNetForceExertedByY(Planet[] pArr) {
		double total_forceY = 0;
		for (Planet p: pArr) {
			if (!this.equals(p)) {
				total_forceY += this.calcForceExertedByY(p);
			}
		}
		return total_forceY;
	}

	public boolean equals(Planet p) {
		if (this == p) {
			return true;
		}
		return false;
	}

	public void update(double seconds, double xForce, double yForce) {
		double xAcc = xForce / mass;
		double yAcc = yForce / mass;
		xxVel += seconds * xAcc;
		yyVel += seconds * yAcc;
		xxPos += seconds * xxVel;
		yyPos += seconds * yyVel;
	}

	public void draw() {
		String imgFile = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imgFile);
	}
}
	