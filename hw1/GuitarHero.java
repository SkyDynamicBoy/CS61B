
import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

public class GuitarHero {
    static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    static final int totalNotes = 37;

    private static double calcFrequency(int index) {
        return 440 * Math.pow(2, (index - 24) / 12.0);
    }

    public static void main(String[] args) {

        GuitarString[] strings = new GuitarString[totalNotes];
        for (int i = 0; i < totalNotes; i++) {
            strings[i] = new GuitarString(calcFrequency(i));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    System.out.println("Please enter correct key");
                    continue;
                }
                strings[index].pluck();
            }

            double sampleSum = 0.0;
            for (GuitarString item : strings) {
                sampleSum += item.sample();
            }

            StdAudio.play(sampleSum);

            for (GuitarString item : strings) {
                item.tic();
            }
        }
    }


}
