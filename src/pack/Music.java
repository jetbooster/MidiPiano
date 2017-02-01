package pack;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import java.util.Arrays;
import java.util.List;

public class Music {

	MidiChannel[] channels;
	Synthesizer synth;
	int channel = 0; // This determines the midi instrument. 0 is piano
	int v = 80; // Velocity?
	int t = 300; // gaps between notes
	int startNote = 72; // This is middle C
	int note = startNote;

	public Music() {

		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			this.channels = synth.getChannels();
			channels[channel].programChange(56);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Music mu = new Music();
		PianoGUI gui = new PianoGUI();
		MidiOut mid = new MidiOut();

		List<Integer> scale = Arrays.asList(0, 2, 2, 1, 2, 2, 2, 1);
		List<Integer> notes = Arrays.asList(86, 86, 86);
		List<Integer> notes2 = Arrays.asList(86);

		List<Integer> chord = Arrays.asList(82, 77);
		List<Integer> chord2 = Arrays.asList(84, 76);
		List<Integer> chord3 = Arrays.asList(86, 78);
		List<Integer> chord4 = Arrays.asList(84, 76);
		List<Integer> chord5 = Arrays.asList(86, 78);


		  /*mid.playNotes(notes, 150); mid.playNotes(notes2, 300);
		  mid.playChord(chord, 500); mid.playChord(chord2, 400);
		  mid.playChord(chord3, 300); mid.playChord(chord4, 300);
		  mid.playChordLong(chord5, 800);
		 */

		mu.synth.close();

	}

	
}
