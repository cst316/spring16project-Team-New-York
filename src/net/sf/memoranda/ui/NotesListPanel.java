package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*$Id: NotesListPanel.java,v 1.5 2005/01/29 13:55:26 rawsushi Exp $*/
public class NotesListPanel extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  static JScrollPane scrollPane = new JScrollPane();
  public static NotesList notesList = new NotesList();

  public NotesListPanel() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      new ExceptionDialog(ex);
    }
  }
  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    this.add(scrollPane, BorderLayout.CENTER);
    scrollPane.getViewport().add(notesList, null);
  }
  public static void setTheme(Color f, Color b)
  {
	  scrollPane.getViewport().setBackground(b);
	  scrollPane.getViewport().setForeground(f);
	  notesList.setBackground(b);
	  notesList.setForeground(f);
  }
}