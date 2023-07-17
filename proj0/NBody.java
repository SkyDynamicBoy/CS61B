public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);

        int number = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for(int i = 0; i < num; i++) {
            planets[i] = new Planet(in.readDouble(),in.readDouble(),
                in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return planets;
    }


    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /** double buffing */
        StdAudio.play("audio/2001.mid");
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double t = 0;
        int num = planets.length;
        while (t < T) {
            double[] xForce = new double[num];
            double[] yForce = new double[num];

            for(int i = 0; i < num; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < num; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            /** draw the background */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            /** draw the planets */
            for(Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.clear();

            t = t + dt;
        }

        StdDraw.clear();
        /** Print the information of the universe */
        StdOut.printf("%d\n", num);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < num; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
    }
}