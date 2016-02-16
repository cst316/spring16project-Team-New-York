package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class WorkPanel extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	static JToolBar toolBar = new JToolBar();
	JPanel panel = new JPanel();
	CardLayout cardLayout1 = new CardLayout();
	boolean active = true;
	static int themeStyle = 0;
	public static JButton notesB = new JButton();
	public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
	public static ResourcesPanel filesPanel = new ResourcesPanel();
	public static JButton agendaB = new JButton();
	public static JButton tasksB = new JButton();
	public static JButton eventsB = new JButton();
	public static JButton filesB = new JButton();
    JFrame dialog = new JFrame("Tips and Tricks");
	JButton currentB = null;
	JPanel dialPanel = new JPanel();
	Border border1;
	

    JLabel dialogLabelEvent = new JLabel("<html><p><div style=\"text-align: center;\">On the Events page you will be able to add events to the Event Manager! <br> <br> You can add a new event by clicking the new event button the event toolbar.<br><br></p></html>", JLabel.CENTER);
    JLabel dialogLabelAgenda = new JLabel("<html><p><div style=\"text-align: center;\">Welcome to Memoranda! <br><br>On the Agenda page you will be able view all your upcoming events, tasks, and notes!<br><br></p></html>", JLabel.CENTER);
    JLabel dialogLabelTasks = new JLabel("<html><p><div style=\"text-align: center;\">On the Tasks page you will be able to add tasks. <br><br> You can add a new task by hitting the new task button. <br><br> You can also create a subtask under an already created task. <br><br> Here you can view your created tasks as well.<br><br></p></html>", JLabel.CENTER);
    JLabel dialogLabelNotes = new JLabel("<html><p><div style=\"text-align: center;\">This is Notes page <br><br> You can add a note simply by typing in the text box below and click on new note to save it! <br><br> The new note will be saved off to the left. <br><br> You can also import and export note files.<br><br></p></html>", JLabel.CENTER);
    JCheckBox ignore = new JCheckBox("Do not show tips anymore");
    ItemListener select = new ItemListener(){
    	public void itemStateChanged(ItemEvent event)
    	{	
    		Object source = event.getItemSelectable();
    		if(source == ignore)
    		{
    			if(ignore.isSelected() == true)
    			{
    				active = false;
    			}
    			else
    				active = true;
    		}
    	}
    };

	public WorkPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	void jbInit() throws Exception {
		border1 =
			BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(
					BevelBorder.LOWERED,
					Color.white,
					Color.white,
					new Color(124, 124, 124),
					new Color(178, 178, 178)),
				BorderFactory.createEmptyBorder(0, 2, 0, 0));

		this.setLayout(borderLayout1);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setBackground(Color.white);

		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		panel.setLayout(cardLayout1);

		agendaB.setBackground(Color.white);
		agendaB.setMaximumSize(new Dimension(60, 80));
		agendaB.setMinimumSize(new Dimension(30, 30));

		agendaB.setFont(new java.awt.Font("Dialog", 1, 10));
		agendaB.setPreferredSize(new Dimension(50, 50));
		agendaB.setBorderPainted(false);
		agendaB.setContentAreaFilled(false);
		agendaB.setFocusPainted(false);
		agendaB.setHorizontalTextPosition(SwingConstants.CENTER);
		agendaB.setText(Local.getString("Agenda"));
		agendaB.setVerticalAlignment(SwingConstants.TOP);
		agendaB.setVerticalTextPosition(SwingConstants.BOTTOM);
		agendaB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaB_actionPerformed(e);
			}
		});
		agendaB.setIcon(
			new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class.getResource(
					"resources/icons/agenda.png")));
		agendaB.setOpaque(false);
		agendaB.setMargin(new Insets(0, 0, 0, 0));
		agendaB.setSelected(true);

		eventsB.setBackground(Color.white);
		eventsB.setMaximumSize(new Dimension(60, 80));
		eventsB.setMinimumSize(new Dimension(30, 30));

		eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
		eventsB.setPreferredSize(new Dimension(50, 50));
		eventsB.setBorderPainted(false);
		eventsB.setContentAreaFilled(false);
		eventsB.setFocusPainted(false);
		eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
		eventsB.setText(Local.getString("Events"));
		eventsB.setVerticalAlignment(SwingConstants.TOP);
		eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
		eventsB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsB_actionPerformed(e);
			}
		});
		eventsB.setIcon(
			new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class.getResource(
					"resources/icons/events.png")));
		eventsB.setOpaque(false);
		eventsB.setMargin(new Insets(0, 0, 0, 0));
		//eventsB.setSelected(true);
		
        dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
    	ignore.addItemListener(select);
        ignore.setSelected(false);
        dialog.setVisible(false);
		
		
		tasksB.setSelected(true);
		tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
		tasksB.setMargin(new Insets(0, 0, 0, 0));
		tasksB.setIcon(
			new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class.getResource(
					"resources/icons/tasks.png")));
		tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
		tasksB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tasksB_actionPerformed(e);
			}
		});
		tasksB.setVerticalAlignment(SwingConstants.TOP);
		tasksB.setText(Local.getString("Tasks"));
		tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
		tasksB.setFocusPainted(false);
		tasksB.setBorderPainted(false);
		tasksB.setContentAreaFilled(false);
		tasksB.setPreferredSize(new Dimension(50, 50));
		tasksB.setMinimumSize(new Dimension(30, 30));
		tasksB.setOpaque(false);
		tasksB.setMaximumSize(new Dimension(60, 80));
		tasksB.setBackground(Color.white);

		notesB.setFont(new java.awt.Font("Dialog", 1, 10));
		notesB.setBackground(Color.white);
		notesB.setBorder(null);
		notesB.setMaximumSize(new Dimension(60, 80));
		notesB.setMinimumSize(new Dimension(30, 30));
		notesB.setOpaque(false);
		notesB.setPreferredSize(new Dimension(60, 50));
		notesB.setBorderPainted(false);
		notesB.setContentAreaFilled(false);
		notesB.setFocusPainted(false);
		notesB.setHorizontalTextPosition(SwingConstants.CENTER);
		notesB.setText(Local.getString("Notes"));
		notesB.setVerticalAlignment(SwingConstants.TOP);
		notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		notesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notesB_actionPerformed(e);
			}
		});
		notesB.setIcon(
			new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class.getResource(
					"resources/icons/notes.png")));
		notesB.setMargin(new Insets(0, 0, 0, 0));
		notesB.setSelected(true);
		this.setPreferredSize(new Dimension(1073, 300));

		filesB.setSelected(true);
		filesB.setMargin(new Insets(0, 0, 0, 0));
		filesB.setIcon(
			new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class.getResource(
					"resources/icons/files.png")));
		filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		filesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filesB_actionPerformed(e);
			}
		});
		filesB.setFont(new java.awt.Font("Dialog", 1, 10));
		filesB.setVerticalAlignment(SwingConstants.TOP);
		filesB.setText(Local.getString("Resources"));
		filesB.setHorizontalTextPosition(SwingConstants.CENTER);
		filesB.setFocusPainted(false);
		filesB.setBorderPainted(false);
		filesB.setContentAreaFilled(false);
		filesB.setPreferredSize(new Dimension(50, 50));
		filesB.setMinimumSize(new Dimension(30, 30));
		filesB.setOpaque(false);
		filesB.setMaximumSize(new Dimension(60, 80));
		filesB.setBackground(Color.white);
		this.add(toolBar, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);
		panel.add(dailyItemsPanel, "DAILYITEMS");
		panel.add(filesPanel, "FILES");
		toolBar.add(agendaB, null);
		toolBar.add(eventsB, null);
		toolBar.add(tasksB, null);
		toolBar.add(notesB, null);
		toolBar.add(filesB, null);
		currentB = agendaB;
		// Default blue color
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);

		toolBar.setBorder(null);
		panel.setBorder(null);
		dailyItemsPanel.setBorder(null);
		filesPanel.setBorder(null);

	}

	public static void setTheme(Color f, Color b)
	{
		toolBar.setForeground(f);
		toolBar.setBackground(b);
		agendaB.setForeground(f);
		eventsB.setForeground(f);
		tasksB.setForeground(f);
		notesB.setForeground(f);
		filesPanel.setForeground(f);
		filesPanel.setBackground(b);
		filesB.setForeground(f);
		filesB.setBackground(b);
	}
	public void selectPanel(String pan) {
		if (pan != null) {
			if (pan.equals("NOTES"))
				notesB_actionPerformed(null);
			else if (pan.equals("TASKS"))
				tasksB_actionPerformed(null);
			else if (pan.equals("EVENTS"))
				eventsB_actionPerformed(null);
			else if (pan.equals("FILES"))
				filesB_actionPerformed(null);
		}
	}

	public void agendaB_actionPerformed(ActionEvent e) {
		String soundFile = "src\\net\\sf\\memoranda\\util\\click.wav";
		Util.playSound(soundFile);
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("AGENDA");
		setCurrentButton(agendaB);
		Context.put("CURRENT_PANEL", "AGENDA");
		if(active == true)
		{
			setDialogAgenda();
		}
	}

	public void notesB_actionPerformed(ActionEvent e) {
		String soundFile = "src\\net\\sf\\memoranda\\util\\click.wav";
		Util.playSound(soundFile);
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("NOTES");
		setCurrentButton(notesB);
		Context.put("CURRENT_PANEL", "NOTES");
		if(active == true)
		{
			setDialogNotes();
		}
	}

	public void tasksB_actionPerformed(ActionEvent e) {
		String soundFile = "src\\net\\sf\\memoranda\\util\\click.wav";
		Util.playSound(soundFile);
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("TASKS");
		setCurrentButton(tasksB);
		Context.put("CURRENT_PANEL", "TASKS");
		if(active == true)
		{
			setDialogTasks();
		}
	}

	public void eventsB_actionPerformed(ActionEvent e) {
		String soundFile = "src\\net\\sf\\memoranda\\util\\click.wav";
		Util.playSound(soundFile);
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("EVENTS");
		setCurrentButton(eventsB);
		Context.put("CURRENT_PANEL", "EVENTS");
		if(active == true)
		{
			setDialogEvent();
		}
	}

	public void filesB_actionPerformed(ActionEvent e) {
		String soundFile = "src\\net\\sf\\memoranda\\util\\click.wav";
		Util.playSound(soundFile);
		cardLayout1.show(panel, "FILES");
		setCurrentButton(filesB);
		Context.put("CURRENT_PANEL", "FILES");
	}

	void setCurrentButton(JButton cb) {
		currentB.setBackground(Color.white);
		currentB.setOpaque(false);
		currentB = cb;
		// Default color blue
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);
	}
	
	public void setDialogEvent()
	{
		dialog.setVisible(false);
		dialPanel.removeAll();
		dialog.setVisible(true);
		dialog.toFront();
        dialPanel.setLayout(new BoxLayout(dialPanel, BoxLayout.Y_AXIS));
        dialPanel.add(dialogLabelEvent);
        dialPanel.add(ignore);
        dialog.getContentPane().add(dialPanel, BorderLayout.CENTER);
        dialog.setSize(300,175);
        dialog.setLocation(800,400);
        dialPanel.revalidate();
        dialPanel.repaint();
	}
	
	public void setDialogAgenda()
	{
		dialog.setVisible(false);
		dialPanel.removeAll();
		dialog.setVisible(true);
		dialog.toFront();
        dialPanel.setLayout(new BoxLayout(dialPanel, BoxLayout.Y_AXIS));
        dialPanel.add(dialogLabelAgenda);
        dialPanel.add(ignore);
        dialog.getContentPane().add(dialPanel, BorderLayout.CENTER);
        dialog.setSize(300,160);
        dialog.setLocation(800,400);
        dialPanel.revalidate();
        dialPanel.repaint();
	}
	
	public void setDialogTasks()
	{
		dialog.setVisible(false);
		dialPanel.removeAll();
		dialog.setVisible(true);
		dialog.toFront();
        dialPanel.setLayout(new BoxLayout(dialPanel, BoxLayout.Y_AXIS));
        dialPanel.add(dialogLabelTasks);
        dialPanel.add(ignore);
        dialog.getContentPane().add(dialPanel, BorderLayout.CENTER);
        dialog.setSize(300,240);
        dialog.setLocation(800,400);
        dialPanel.revalidate();
        dialPanel.repaint();
	}
	
	public void setDialogNotes()
	{
		dialog.setVisible(false);
		dialPanel.removeAll();
		dialog.setVisible(true);
		dialog.toFront();
        dialPanel.setLayout(new BoxLayout(dialPanel, BoxLayout.Y_AXIS));
        dialPanel.add(dialogLabelNotes);
        dialPanel.add(ignore);
        dialog.getContentPane().add(dialPanel, BorderLayout.CENTER);
        dialog.setSize(300,225);
        dialog.setLocation(800,400);
        dialPanel.revalidate();
        dialPanel.repaint();
	}
}