import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

public class BeatGenerator extends Thread {
    double[] Beats;
    double Duration;

    public BeatGenerator(double[] beats, double duration) {
        Beats=beats;
        Duration=duration;
    }

    public void run() {
        try {
            makeTone(Beats,Duration);
        } catch (LineUnavailableException lue) {
            System.out.println(lue);
        }
    }

    private void makeTone(double[] beats,double duration)
            throws LineUnavailableException
    {
        int freq = 44100;
        double[] x=new double[(int)(duration*freq)];

        byte[] buf;
        AudioFormat audioF;

        for(int i=0;i<x.length;i++){
            x[i]=(double)(i+1)/freq;
        }

        buf = new byte[x.length];
        audioF = new AudioFormat(freq,8,1,true,false);

        SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
        sourceDL = AudioSystem.getSourceDataLine(audioF);
        sourceDL.open(audioF);
        sourceDL.start();

        for(int i=0;i<beats.length;i++){
            for (int j=0;j<x.length;j++){
                buf[j]=(byte)(Math.sin((2*Math.PI*beats[i])*x[j])*10.0);
            }
            sourceDL.write(buf,0,buf.length);
        }

        sourceDL.drain();
        sourceDL.stop();
        sourceDL.close();
    }
}
