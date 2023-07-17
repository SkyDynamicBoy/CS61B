public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**Constructor1. 
     * Inicialize the instance variables 
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    

    /**Constructor2. 
     * Take in a Planet object and initialize an identical Planet object 
     */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }


    /** Calculates the distance between two Planets */
    public double calcDistance(Planet otherPlanet) {
        double dx = this.xxPos - otherPlanet.xxPos;
        double dy = this.yyPos - otherPlanet.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }


    private static double GravityConstant = 6.67e-11;


    /** Return the force exerted on this planet by @param Exerte */
    public double calcForceExertedBy(Planet Exerte) {
        double distance = this.calcDistance(Exerte);
        return GravityConstant * this.mass * Exerte.mass / (distance * distance);
    }

    /** Force in x direction and y direction */
    public double calcForceExertedByX(Planet Exerte) {
        double ForceSize = calcForceExertedBy(Exerte);
        double distance = calcDistance(Exerte);
        double dx = Exerte.xxPos - this.xxPos;
        return ForceSize * dx / distance;
    }
    public double calcForceExertedByY(Planet Exerte) {
        double ForceSize = calcForceExertedBy(Exerte);
        double distance = calcDistance(Exerte);
        double dy = Exerte.yyPos - this.yyPos;
        return ForceSize * dy / distance;
    }


    public double calcNetForceExertedByX(Planet[] otherPlanet) {
        int numbers = otherPlanet.length;
        double sum = 0;
        while (numbers > 0) {
            numbers--;
            if(otherPlanet[numbers].equals(this)) {
                continue;
            }
            sum += calcForceExertedByX(otherPlanet[numbers]);
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] otherPlanet) {
        double sum = 0;
        for(Planet p : otherPlanet) {
            if(p.equals(this)) {
                continue;
            }
            sum += calcForceExertedByY(p);
        }
        return sum;
    }

    /** update Velocity and Position of this Planet
     * in @param dt time, by @param Fx x-force and @param Fy y-force
     */
    public void update(double dt, double Fx, double Fy) {
        double Ax = Fx / this.mass;
        double Ay = Fy / this.mass;
        this.xxVel = this.xxVel + Ax * dt;
        this.yyVel = this.yyVel + Ay * dt;
        this.xxPos = this.xxPos + xxVel * dt;
        this.yyPos = this.yyPos + yyVel * dt;
    }


    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}