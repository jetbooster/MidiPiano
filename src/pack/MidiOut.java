package pack;

import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiOut {
	MidiChannel[] channels;
	Synthesizer synth;
	int channel = 0; // This determines the midi instrument. 0 is piano
	int v = 80; // Velocity?
	int t = 300; // gaps between notes
	int startNote = 72; // This is middle C
	int note = startNote;

	public MidiOut() {

		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			this.channels = synth.getChannels();
			channels[channel].programChange(0);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void playNote(int note, int length) {

		try {
			Thread.sleep(length / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		channels[channel].noteOn(note, v);
		try {
			Thread.sleep(length / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		channels[channel].noteOff(note);

	}

	public void playChord(List<Integer> chord, int length) {
		channels[channel].allNotesOff();
		try {
			Thread.sleep(length / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i : chord) {
			channels[channel].noteOn(i, v);
		}
		try {
			Thread.sleep(length / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i : chord) {
			channels[channel].noteOff(note + i);
		}

	}

	public void playChordLong(List<Integer> chord, int length) {
		channels[channel].allNotesOff();

		for (int i : chord) {
			channels[channel].noteOn(i, v);
		}
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i : chord) {
			channels[channel].noteOff(note + i);
		}

	}

	public void playScale(List<Integer> scale) {

		for (int i : scale) {
			note += i;
			this.playNote(note, t);
		}
	}

	public void playNotes(List<Integer> notes, int length) {

		for (int i : notes) {
			this.playNote(i, length);
		}
	}

}
