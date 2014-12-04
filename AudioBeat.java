import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

public class AudioBeat {

    AudioFormat audioF;
    SourceDataLine sourceDL;

    public AudioBeat(int freq)
            throws LineUnavailableException {

        audioF = new AudioFormat(freq,8,1,true,false);
        sourceDL = AudioSystem.getSourceDataLine(audioF);

    }

    public void start()
            throws LineUnavailableException {

        sourceDL.open(audioF);
        sourceDL.start();

    }

    public void stop()
            throws LineUnavailableException {

        sourceDL.drain();
        sourceDL.stop();
        sourceDL.close();
    }

    public void tone(byte[] buf)
            throws LineUnavailableException {

        sourceDL.write(buf,0,buf.length);

    }

}
