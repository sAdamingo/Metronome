import javax.sound.sampled.LineUnavailableException;

public class BeatGenerator extends Thread {
    double[] Beats;
    double Duration;
    AudioBeat audio;

    final int freq = 44100;

    public BeatGenerator() {

        try {
            audio = new AudioBeat(freq);
        }
        catch (LineUnavailableException lue) {
            System.out.println(lue);
        }


    }

    public void set(double bpm, int duration) {
        Duration=30 / bpm;

        double[] beats = new double[(int) ((bpm / 30) * duration)];

        for (int i = 0; i < beats.length; i++) {

            if (i % 2 == 0)
                beats[i] = 0;
            else
                beats[i] = 2;
        }

        double startnote = 440 * Math.pow(2.0, 3.0 / 12.0);

        for (int i = 0; i < beats.length; i++) {
            beats[i] = beats[i] / 12;
            beats[i] = (double) Math.pow(2, beats[i]);
            beats[i] = startnote * beats[i];
            if (i % 2 == 0)
                beats[i] = beats[i] / beats[i] - 1;
        }

        Beats = beats;
    }

    public void run() {

        try {
            makeTone(Beats,Duration);
        }
        catch (LineUnavailableException lue) {
            System.out.println(lue);
        }
    }

    private void makeTone(double[] beats,double duration)
            throws LineUnavailableException {
        double[] x=new double[(int)(Duration*freq)];

        byte[] buf;

        for(int i=0;i<x.length;i++){
            x[i]=(double)(i+1)/freq;
        }

        buf = new byte[x.length];


        audio.start();

        for (int i = 0; i < beats.length; i++) {

            generateBuf(beats[i], x, buf);

            audio.tone(buf);
        }

        audio.stop();

    }

     void generateBuf(double beat, double[] x, byte[] buf) {
        for (int j = 0; j < x.length; j++) {
            buf[j] = (byte) (Math.sin((2 * Math.PI * beat) * x[j]) * 10.0);
        }
    }
}
