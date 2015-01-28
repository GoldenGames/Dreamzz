package me.mani.dreamzzconfig;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.google.common.io.Files;

public class ConfigBuilder extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ConfigBuilder();		
	}
	
	public ConfigBuilder() {
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ConfigBuilder");
		
		JButton button = new JButton("Create");
		button.addActionListener((ActionEvent ev) -> createConfig());
		getContentPane().add(button);
		
		pack();
		setVisible(true);
	}
	
	private void createConfig() {
		JFileChooser fileChooser = new JFileChooser();
		File dir;
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showSaveDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION)
			dir = fileChooser.getSelectedFile();
		else
			return;
		
		try {
			File sampleConfig = new File(getClass().getResource("sampleConfig.yml").toURI());
			Files.copy(sampleConfig, new File(dir, "sampleConfig.yml"));
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
